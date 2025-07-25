package net.ender.ess_requiem.spells.mind;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.network.particles.ShockwaveParticlesPacket;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import net.acetheeldritchking.aces_spell_utils.spells.ASSpellAnimations;
import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;
import net.ender.ess_requiem.registries.GGEffectRegistry;
import net.ender.ess_requiem.registries.GGSchoolRegistry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.List;

@AutoSpellConfig
public class ToStandBeforeMeSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "to_stand_before_me");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.radius", Utils.stringTruncation(getRadius(spellLevel, caster), 2)),
                (Component.translatable("ui.irons_spellbooks.damage", getDamage(spellLevel, caster)/2)));
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.EPIC)
            .setSchoolResource(GGSchoolRegistry.MIND_RESOURCE)
            .setMaxLevel(8)
            .setCooldownSeconds(65)
            .build();

    public ToStandBeforeMeSpell() {
        this.manaCostPerLevel = 5;
        this.baseSpellPower = 5;
        this.spellPowerPerLevel = 2;
        this.castTime = 100;
        this.baseManaCost = 8;
    }

    @Override
    public CastType getCastType() {
        return CastType.CONTINUOUS;
    }

    @Override
    public DefaultConfig getDefaultConfig() {
        return defaultConfig;
    }

    @Override
    public ResourceLocation getSpellResource() {
        return spellId;
    }

    @Override
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        float radius = getRadius(spellLevel, entity);

        MagicManager.spawnParticles(level, new BlastwaveParticleOptions(GGSchoolRegistry.MIND.get().getTargetingColor(), radius), entity.getX(), entity.getY() + .165f, entity.getZ(), 1, 0, 0, 0, 0, true);
        PacketDistributor.sendToPlayersTrackingEntityAndSelf(entity, new ShockwaveParticlesPacket(new Vec3(entity.getX(), entity.getY() + .165f, entity.getZ()), radius, ParticleTypes.END_ROD));
        level.getEntities(entity, entity.getBoundingBox().inflate(radius, 4, radius), (target) -> !DamageSources.isFriendlyFireBetween(target, entity) && Utils.hasLineOfSight(level, entity, target, false)).forEach(target -> {
            if (target instanceof LivingEntity livingEntity && livingEntity.distanceToSqr(entity) < radius * radius) {
                int i = getDuration(spellLevel, entity);
                DamageSources.applyDamage(target, getDamage(spellLevel, entity)/2, getDamageSource(entity));
                livingEntity.addEffect(new MobEffectInstance(GGEffectRegistry.WEIGHED_DOWN, i, 0));


            }

        });

        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    public int getDuration(int spellLevel, LivingEntity caster) {
        return (int) (1 + getSpellPower(spellLevel, caster));
    }

    public float getRadius(int spellLevel, LivingEntity caster) {
        return 10 + spellLevel ;
    }

    private float getDamage(int spellLevel, LivingEntity entity) {
        return getSpellPower(spellLevel, entity)/2 ;
    }

    @Override
    public AnimationHolder getCastStartAnimation() {
        return ASSpellAnimations.ANIMATION_MALEVOLENT_HAND_SIGN;
    }


}
