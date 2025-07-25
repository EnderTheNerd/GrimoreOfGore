package net.ender.ess_requiem.spells.mind;

import com.gametechbc.spelllib.particle.CylinderParticleManager;
import com.gametechbc.spelllib.particle.ParticleDirection;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.capabilities.magic.TargetEntityCastData;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.network.particles.ShockwaveParticlesPacket;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import net.acetheeldritchking.aces_spell_utils.spells.ASSpellAnimations;
import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;
import net.ender.ess_requiem.registries.GGEffectRegistry;
import net.ender.ess_requiem.registries.GGParticleRegistry;
import net.ender.ess_requiem.registries.GGSchoolRegistry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;


import java.util.List;

@AutoSpellConfig
public class OverloadSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "overload");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                (Component.translatable("ui.irons_spellbooks.damage", getDamage(spellLevel, caster)/2)));

    }


    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.UNCOMMON)
            .setSchoolResource(GGSchoolRegistry.MIND_RESOURCE)
            .setMaxLevel(8)
            .setCooldownSeconds(48)
            .build();

    public OverloadSpell() {
        this.manaCostPerLevel = 5;
        this.baseSpellPower = 5;
        this.spellPowerPerLevel = 6;
        this.castTime = 55;
        this.baseManaCost = 10;
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
    public boolean checkPreCastConditions(Level level, int spellLevel, LivingEntity entity, MagicData playerMagicData) {
        return Utils.preCastTargetHelper(level, entity, playerMagicData, (AbstractSpell) this, (int) (20 + getSpellPower(spellLevel, entity)), 1f);
    }

    @Override
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        if (playerMagicData.getAdditionalCastData() instanceof TargetEntityCastData targetData) {
            float radius = 1.5F;
            var target = targetData.getTarget((ServerLevel) level);
            if (target instanceof LivingEntity) {
                DamageSources.applyDamage(target, getDamage(spellLevel, entity)/2, getDamageSource(entity));
                CylinderParticleManager.spawnParticles(level, target, 30 * spellLevel, GGParticleRegistry.CONFUSION_EYE_PARTICLE.get(), ParticleDirection.INWARD, (double)radius, 6, -1.0D);
                target.addEffect(new MobEffectInstance(GGEffectRegistry.CONFUSED, 10, 0));
            }

            super.onCast(level, spellLevel, entity, castSource, playerMagicData);
        }


    }
    private float getDamage(int spellLevel, LivingEntity entity) {
        return getSpellPower(spellLevel, entity)/2 ;
    }

    @Override
    public AnimationHolder getCastStartAnimation() {
        return SpellAnimations.ANIMATION_CONTINUOUS_CAST;
    }

}
