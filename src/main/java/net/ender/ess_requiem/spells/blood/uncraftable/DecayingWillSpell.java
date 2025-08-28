package net.ender.ess_requiem.spells.blood.uncraftable;

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
import net.acetheeldritchking.aces_spell_utils.utils.ASUtils;
import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;
import net.ender.ess_requiem.registries.GGEffectRegistry;
import net.ender.ess_requiem.registries.GGItemRegistry;
import net.ender.ess_requiem.registries.GGParticleRegistry;
import net.ender.ess_requiem.registries.GGSchoolRegistry;
import net.ender.ess_requiem.spells.blood.NecroticBurstSpell;
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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.registries.DeferredHolder;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

@AutoSpellConfig
public class DecayingWillSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "decaying_will");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {

        return List.of(Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(getDamage(spellLevel, caster), 2)),
                Component.translatable("ui.irons_spellbooks.radius", Utils.stringTruncation(getRadius(spellLevel, caster), 2)));



    }



    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.LEGENDARY)
            .setSchoolResource(SchoolRegistry.BLOOD_RESOURCE)
            .setMaxLevel(1)
            .setCooldownSeconds(85)
            .build();

    public DecayingWillSpell() {
        this.manaCostPerLevel = 0;
        this.baseSpellPower = 10;
        this.spellPowerPerLevel = 0;
        this.castTime = 20;
        this.baseManaCost = 450;
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
        return Optional.of(SoundEvents.WITHER_SPAWN);
    }



    @Override
    public ResourceLocation getSpellResource() {
        return spellId;
    }

    @Override
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        float radius = getRadius(spellLevel, entity);

        if (entity instanceof Player player && ASUtils.hasCurio(player, GGItemRegistry.NAMELESS_RING_CURIO.get())) {
            MagicManager.spawnParticles(level, ParticleTypes.SOUL, entity.getX(), entity.getY() + 1, entity.getZ(), 50, 0, 0, 0, 2, false);
            MagicManager.spawnParticles(level, ParticleTypes.SCULK_SOUL, entity.getX(), entity.getY() + 1, entity.getZ(), 50, 0, 0, 0, 2, false);
            MagicManager.spawnParticles(level, GGParticleRegistry.WITHER_SKULL_SMALL.get(), entity.getX(), entity.getY() + 1, entity.getZ(), 30, 0, 0, 0, 2, false);
            MagicManager.spawnParticles(level, new BlastwaveParticleOptions(GGSchoolRegistry.BLOOD.get().getTargetingColor(), radius), entity.getX(), entity.getY() + .165f, entity.getZ(), 1, 0, 0, 0, 0, true);
            PacketDistributor.sendToPlayersTrackingEntityAndSelf(entity, new ShockwaveParticlesPacket(new Vec3(entity.getX(), entity.getY() + .165f, entity.getZ()), radius, ParticleTypes.END_ROD));
            level.getEntities(entity, entity.getBoundingBox().inflate(radius, 4, radius), (target) -> !DamageSources.isFriendlyFireBetween(target, entity) && Utils.hasLineOfSight(level, entity, target, false)).forEach(target -> {
                int i = getDuration(spellLevel, entity);

                if (target instanceof LivingEntity livingEntity && livingEntity.distanceToSqr(entity) < radius * radius) {
                    DamageSources.applyDamage(target, getDamage(spellLevel, entity) * 1.5F, getDamageSource(entity));
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.WITHER, i, getWitherAmplifier(spellLevel, entity)));
                }

                super.onCast(level, spellLevel, entity, castSource, playerMagicData);
            });
            int i = getDuration(spellLevel, entity);
            entity.addEffect(new MobEffectInstance(GGEffectRegistry.LORD_OF_DECAY, i * 6, 0, false, false, true));
        }
        else {
            MagicManager.spawnParticles(level, ParticleTypes.SOUL, entity.getX(), entity.getY() + 1, entity.getZ(), 50, 0, 0, 0, 2, false);
            MagicManager.spawnParticles(level, ParticleTypes.SCULK_SOUL, entity.getX(), entity.getY() + 1, entity.getZ(), 50, 0, 0, 0, 2, false);
            MagicManager.spawnParticles(level, GGParticleRegistry.WITHER_SKULL_SMALL.get(), entity.getX(), entity.getY() + 1, entity.getZ(), 30, 0, 0, 0, 2, false);
            MagicManager.spawnParticles(level, new BlastwaveParticleOptions(GGSchoolRegistry.BLOOD.get().getTargetingColor(), radius), entity.getX(), entity.getY() + .165f, entity.getZ(), 1, 0, 0, 0, 0, true);
            PacketDistributor.sendToPlayersTrackingEntityAndSelf(entity, new ShockwaveParticlesPacket(new Vec3(entity.getX(), entity.getY() + .165f, entity.getZ()), radius, ParticleTypes.END_ROD));
            level.getEntities(entity, entity.getBoundingBox().inflate(radius, 4, radius), (target) -> !DamageSources.isFriendlyFireBetween(target, entity) && Utils.hasLineOfSight(level, entity, target, false)).forEach(target -> {
                int i = getDuration(spellLevel, entity);
                entity.addEffect(new MobEffectInstance(GGEffectRegistry.DECAYING_MIGHT, i, 0, false, false, true));
                if (target instanceof LivingEntity livingEntity && livingEntity.distanceToSqr(entity) < radius * radius) {
                    DamageSources.applyDamage(target, getDamage(spellLevel, entity), getDamageSource(entity));
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.WITHER, i, getWitherAmplifier(spellLevel, entity)));
                }

                super.onCast(level, spellLevel, entity, castSource, playerMagicData);
            });
            int i = getDuration(spellLevel, entity);
            entity.addEffect(new MobEffectInstance(GGEffectRegistry.DECAYING_MIGHT, i * 6, 0, false, false, true));

        }






    }
    public int getDuration(int spellLevel, LivingEntity caster) {
        return (int) (getSpellPower(spellLevel, caster) * 20);
    }

    public int getWitherAmplifier(int spellLevel, LivingEntity caster) {
        return 5 + spellLevel;
    }


    private float getDamage(int spellLevel, LivingEntity entity)
    {
        float damage = getDamageForAttribute(this, entity, spellLevel, AttributeRegistry.SUMMON_DAMAGE, 1);
        return damage;
    }

    public static float getDamageForAttribute(DecayingWillSpell spell, LivingEntity entity, int spellLevel, DeferredHolder<Attribute, Attribute> attr1, float modifier)
    {
        double attrValue1 = entity.getAttributeValue(AttributeRegistry.SUMMON_DAMAGE);

        float damage = (float) (modifier * (spell.getSpellPower(spellLevel, entity) * 2 + attrValue1));

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

    @Override
    public boolean canBeCraftedBy(Player player) {
        return false;
    }

    @Override
    public boolean allowCrafting() {
        return false;
    }

    @Override
    public boolean allowLooting() {
        return false;
    }

}
