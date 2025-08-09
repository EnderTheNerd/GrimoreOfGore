package net.ender.ess_requiem.spells.blood;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
import io.redspace.ironsspellbooks.entity.spells.AbstractConeProjectile;
import io.redspace.ironsspellbooks.entity.spells.fire_breath.FireBreathProjectile;
import io.redspace.ironsspellbooks.spells.EntityCastData;
import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;
import net.ender.ess_requiem.entity.spells.wretch_breath.WretchBreath;
import net.ender.ess_requiem.registries.GGEffectRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

@AutoSpellConfig
public class WretchSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "wretch");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {

        return List.of(
                (Component.translatable("ui.irons_spellbooks.damage", getDamage(spellLevel, caster))),
                (Component.translatable("ui.ess_requiem.desperate_damage", getDamage(spellLevel, caster) * 1.5F)));


    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.EPIC)
            .setSchoolResource(SchoolRegistry.BLOOD_RESOURCE)
            .setMaxLevel(6)
            .setCooldownSeconds(15)
            .build();

    public WretchSpell() {
        this.manaCostPerLevel = 6;
        this.baseSpellPower = 1;
        this.spellPowerPerLevel = 2;
        this.castTime = 100;
        this.baseManaCost = 2;
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
    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        final float MAX_HEALTH = entity.getMaxHealth();
        float baseHealth = entity.getHealth();
        double percent = (baseHealth / MAX_HEALTH) * 100;


if (percent <= 50)
        if (playerMagicData.isCasting() && playerMagicData.getCastingSpellId().equals(this.getSpellId())
                && playerMagicData.getAdditionalCastData() instanceof EntityCastData entityCastData
                && entityCastData.getCastingEntity() instanceof AbstractConeProjectile cone) {
            cone.setDealDamageActive();
        } else {
            WretchBreath wretch = new WretchBreath(world, entity);
            wretch.setPos(entity.position().add(0, entity.getEyeHeight() * .7, 0));
            wretch.setDamage( (getDamage(spellLevel, entity) * 1.5F));
            world.addFreshEntity(wretch);

            playerMagicData.setAdditionalCastData(new EntityCastData(wretch));
        }

else {
    if (playerMagicData.isCasting() && playerMagicData.getCastingSpellId().equals(this.getSpellId())
            && playerMagicData.getAdditionalCastData() instanceof EntityCastData entityCastData
            && entityCastData.getCastingEntity() instanceof AbstractConeProjectile cone) {
        cone.setDealDamageActive();
    } else {
        WretchBreath wretch = new WretchBreath(world, entity);
        wretch.setPos(entity.position().add(0, entity.getEyeHeight() * .7, 0));
        wretch.setDamage( (getDamage(spellLevel, entity)));
        world.addFreshEntity(wretch);


        playerMagicData.setAdditionalCastData(new EntityCastData(wretch));
    }

}
        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }




    @Override
    public SpellDamageSource getDamageSource(@Nullable Entity projectile, Entity attacker) {
        return super.getDamageSource(projectile, attacker);
    }

    public float getDamage(int spellLevel, LivingEntity caster) {

        return  (1 + getSpellPower(spellLevel, caster));
    }



    @Override
    public AnimationHolder getCastStartAnimation() {
        return SpellAnimations.ANIMATION_CONTINUOUS_CAST;
    }
}
