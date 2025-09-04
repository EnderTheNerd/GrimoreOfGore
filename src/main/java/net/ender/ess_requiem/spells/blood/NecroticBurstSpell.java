package net.ender.ess_requiem.spells.blood;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.network.particles.ShockwaveParticlesPacket;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;
import net.ender.ess_requiem.registries.GGParticleRegistry;
import net.ender.ess_requiem.registries.GGSchoolRegistry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.registries.DeferredHolder;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

@AutoSpellConfig
public class NecroticBurstSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "necrotic_burst");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {

        return List.of(Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(getDamage(spellLevel, caster), 2)),
                Component.translatable("ui.irons_spellbooks.radius", Utils.stringTruncation(getRadius(spellLevel, caster), 2))
        );

    }



    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.EPIC)
            .setSchoolResource(SchoolRegistry.BLOOD_RESOURCE)
            .setMaxLevel(5)
            .setCooldownSeconds(65)
            .build();

    public NecroticBurstSpell() {
        this.manaCostPerLevel = 22;
        this.baseSpellPower = 6;
        this.spellPowerPerLevel = 3;
        this.castTime = 24;
        this.baseManaCost = 90;
    }



    @Override
    public DefaultConfig getDefaultConfig() {
        return defaultConfig;
    }

    @Override
    public CastType getCastType() {
        return CastType.LONG;
    }

    @Override
    public int getEffectiveCastTime(int spellLevel, @Nullable LivingEntity entity) {
        //due to animation timing, we do not want cast time attribute to affect this spell
        return getCastTime(spellLevel);
    }


    @Override
    public Optional<SoundEvent> getCastStartSound() {
        return Optional.of(SoundEvents.WITHER_DEATH);
    }



    @Override
    public ResourceLocation getSpellResource() {
        return spellId;
    }

    @Override
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        float radius = getRadius(spellLevel, entity);

        MagicManager.spawnParticles(level, ParticleTypes.SOUL, entity.getX(), entity.getY() + 1, entity.getZ(), 50, 0, 0, 0, 1, false);
        MagicManager.spawnParticles(level, GGParticleRegistry.WITHER_SKULL_SMALL.get(), entity.getX(), entity.getY() + 1, entity.getZ(), 30, 0, 0, 0, 2, false);
        MagicManager.spawnParticles(level, new BlastwaveParticleOptions(GGSchoolRegistry.BLOOD.get().getTargetingColor(), radius), entity.getX(), entity.getY() + .165f, entity.getZ(), 1, 0, 0, 0, 0, true);
        PacketDistributor.sendToPlayersTrackingEntityAndSelf(entity, new ShockwaveParticlesPacket(new Vec3(entity.getX(), entity.getY() + .165f, entity.getZ()), radius, ParticleTypes.END_ROD));
        level.getEntities(entity, entity.getBoundingBox().inflate(radius, 4, radius), (target) -> !DamageSources.isFriendlyFireBetween(target, entity) && Utils.hasLineOfSight(level, entity, target, false)).forEach(target -> {
            if (target instanceof LivingEntity livingEntity && livingEntity.distanceToSqr(entity) < radius * radius) {
                int i = getDuration(spellLevel, entity);
                DamageSources.applyDamage(target, getDamage(spellLevel, entity), getDamageSource(entity));
                livingEntity.addEffect(new MobEffectInstance(MobEffects.WITHER, i, getWitherAmplifier(spellLevel, entity)));


            }

        });

        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }




    public int getDuration(int spellLevel, LivingEntity caster) {
        return (int) (getSpellPower(spellLevel, caster) * 20);
    }

    public int getWitherAmplifier(int spellLevel, LivingEntity caster) {
        return 1 + spellLevel;
    }


    private float getDamage(int spellLevel, LivingEntity entity)
    {
        float damage = getDamageForAttribute(this, entity, spellLevel, AttributeRegistry.SUMMON_DAMAGE, 1);

        return damage;
    }

    public static float getDamageForAttribute(NecroticBurstSpell spell, LivingEntity entity, int spellLevel, DeferredHolder<Attribute, Attribute> attr1, float modifier)
    {
        double attrValue1;
        if(entity != null) {
            attrValue1 = entity.getAttributeValue(AttributeRegistry.SUMMON_DAMAGE);
        }else{
            attrValue1 = 1;
        }

        float damage = (float) (modifier * (spell.getSpellPower(spellLevel, entity) + attrValue1));

        return damage;
    }

    @Override
    public AnimationHolder getCastStartAnimation() {
        return SpellAnimations.PREPARE_CROSS_ARMS;
    }

    @Override
    public AnimationHolder getCastFinishAnimation() {
        return SpellAnimations.CAST_T_POSE;
    }

    public float getRadius(int spellPower, LivingEntity caster) {
        return 5 + spellPower * .5f;
    }



}
