package net.ender.ess_requiem.spells.spellblade.uncraftable;

import com.gametechbc.spelllib.particle.CylinderParticleManager;
import com.gametechbc.spelllib.particle.ParticleDirection;
import com.gametechbc.spelllib.particle.SphereParticleManager;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.StompAoe;
import io.redspace.ironsspellbooks.entity.spells.blood_needle.BloodNeedle;
import io.redspace.ironsspellbooks.registries.ParticleRegistry;
import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;
import net.ender.ess_requiem.entity.spells.dismantle.DismantleProjectile;
import net.ender.ess_requiem.entity.spells.spellblade_cut.SpellbladeCutProjectile;
import net.ender.ess_requiem.registries.GGParticleRegistry;
import net.ender.ess_requiem.registries.GGSchoolRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import org.joml.sampling.UniformSampling;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

@AutoSpellConfig
public class CleaveSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "cleave");
    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.translatable("ui.irons_spellbooks.damage", getDamageText(spellLevel, caster)));
    }


    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.COMMON)
            .setSchoolResource(GGSchoolRegistry.BLADE_RESOURCE)
            .setMaxLevel(5)
            .setCooldownSeconds(35)
            .build();

    public CleaveSpell() {
        this.manaCostPerLevel = 10;
        this.baseSpellPower = 5;
        this.spellPowerPerLevel = 2;
        this.castTime = 10;
        this.baseManaCost = 55;
    }

    @Override
    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.of(SoundEvents.MACE_SMASH_GROUND_HEAVY);
    }


    @Override
    public CastType getCastType() {
        return CastType.LONG;
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
    public boolean canBeInterrupted(@Nullable Player player) {
        return false;
    }

    @Override
    public int getEffectiveCastTime(int spellLevel, @Nullable LivingEntity entity) {

        return getCastTime(spellLevel);
    }



    @Override
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        float radius2 = 3;
        float radius = 3f;

        Vec3 forward = entity.getForward();
        float range = 2f;
        Vec3 smiteLocation = Utils.raycastForBlock(level, entity.getEyePosition(), entity.getEyePosition().add(entity.getForward().multiply(range, 0, range)), ClipContext.Fluid.NONE).getLocation();
        var entities = level.getEntities(entity, AABB.ofSize(smiteLocation, radius, radius , radius ));
        var damageSource = this.getDamageSource(entity);
        for (Entity targetEntity : entities) {
            if (targetEntity instanceof LivingEntity && targetEntity.isAlive() && entity.isPickable() && targetEntity.position().subtract(entity.getEyePosition()).dot(forward) >= 0 && entity.distanceToSqr(targetEntity) < radius * radius && Utils.hasLineOfSight(level, entity.getEyePosition(), targetEntity.getBoundingBox().getCenter(), true)) {

                //Took this RIGHT from the signet of the betrayer
                var victimMaxMana = ((LivingEntity) targetEntity).getAttributeValue(AttributeRegistry.MAX_MANA);
                var victimBaseMana = ((LivingEntity) targetEntity).getAttributeBaseValue(AttributeRegistry.MAX_MANA);
                var manaAboveBase = victimMaxMana - victimBaseMana;
                double conversionRatioPer100 = 0.10;
                double totalExtraDamagePercent = 0;
                while (manaAboveBase > 0 && conversionRatioPer100 > 0) {
                    var step = Math.clamp(manaAboveBase, 0, 100) * .01;
                    totalExtraDamagePercent += step * conversionRatioPer100;
                    manaAboveBase -= 100;
                    conversionRatioPer100 -= 0.01;
                }
                var count = 6;
                if (DamageSources.applyDamage(targetEntity, (float) (getDamage(spellLevel, entity) + totalExtraDamagePercent), damageSource)) {
                    EnchantmentHelper.doPostAttackEffects((ServerLevel) level, targetEntity, damageSource);
                    Vec3 center = targetEntity.position().add(0, targetEntity.getEyeHeight() / 2, 0);

                    float degreesPerNeedle = 360f / count;
                    for (int i = 0; i < count; i++) {
                        Vec3 offset = new Vec3(0, Math.random(), .55).normalize().scale(targetEntity.getBbWidth() + 2.75f).yRot(degreesPerNeedle * i * Mth.DEG_TO_RAD);
                        Vec3 spawn = center.add(offset);
                        Vec3 motion = center.subtract(spawn).normalize();

                        SpellbladeCutProjectile cut = new SpellbladeCutProjectile(level, entity);
                        cut.moveTo(spawn);
                        cut.shoot(motion.scale(.35f));
                        cut.setScale(.6f);
                        cut.setDamage(0);
                        level.addFreshEntity(cut);
                    }
                }
                }
            }


        Vec3 spawn = Utils.moveToRelativeGroundLevel(level, entity.getEyePosition().add(entity.getForward().multiply(1f, 0, 1f)), 1);
        var bpos = BlockPos.containing(spawn);
        ((ServerLevel) level).sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, level.getBlockState(bpos)).setPos(bpos), spawn.x, spawn.y, spawn.z, 40, 0.0D, 0.0D, 0.0D, (double) 0.20 + 0.05F * spellLevel);



        var stomp = new StompAoe(level, getRange(spellLevel, entity), entity.getYRot());
        stomp.moveTo(spawn);
        stomp.setDamage(getDamage(spellLevel, entity)/2);
        stomp.setExplosionRadius(getEntityPowerMultiplier(entity) * 2);
        stomp.setOwner(entity);
        level.addFreshEntity(stomp);

        super.onCast(level, spellLevel, entity, castSource, playerMagicData);

    }




    private float getDamage(int spellLevel, LivingEntity entity) {
        return getSpellPower(spellLevel, entity) + getAdditionalDamage(entity);
    }

    private int getRange(int spellLevel, LivingEntity caster) {
        return 5;
    }



    private float getAdditionalDamage(LivingEntity entity) {
        if (entity == null) {
            return 0;
        }
        float weaponDamage = Utils.getWeaponDamage(entity);
        var weaponItem = entity.getWeaponItem();
        if (!weaponItem.isEmpty() && weaponItem.has(DataComponents.ENCHANTMENTS)) {
            weaponDamage += Utils.getEnchantmentLevel(entity.level(), Enchantments.SHARPNESS, weaponItem.get(DataComponents.ENCHANTMENTS));
        }
        return weaponDamage;
    }

    private String getDamageText(int spellLevel, LivingEntity entity) {
        if (entity != null) {
            float weaponDamage = Utils.getWeaponDamage(entity);
            String plus = "";
            if (weaponDamage > 0) {
                plus = String.format(" (+%s)", Utils.stringTruncation(weaponDamage, 1));
            }
            String damage = Utils.stringTruncation(getDamage(spellLevel, entity), 1);
            return damage + plus;
        }
        return "" + getSpellPower(spellLevel, entity);
    }

    @Override
    public AnimationHolder getCastStartAnimation() {
        return SpellAnimations.OVERHEAD_MELEE_SWING_ANIMATION;
    }

    @Override
    public AnimationHolder getCastFinishAnimation() {
        return AnimationHolder.pass();
    }


}
