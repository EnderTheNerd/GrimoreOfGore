package net.ender.ess_requiem.spells.blood;

import dev.shadowsoffire.apothic_attributes.api.ALObjects;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.magic.SpellSelectionManager;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.capabilities.magic.RecastInstance;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;

import net.ender.ess_requiem.entity.spells.bone_claw.BoneClawEntity;
import net.ender.ess_requiem.entity.spells.claw.ClawEntity;
import net.ender.ess_requiem.registries.GGEffectRegistry;
import net.ender.ess_requiem.registries.GGSoundRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

@AutoSpellConfig
public class RipAndTearSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "rip_and_tear");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                (Component.translatable("ui.irons_spellbooks.damage", getDamage(spellLevel, caster))));

    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.COMMON)
            .setSchoolResource(SchoolRegistry.BLOOD_RESOURCE)
            .setMaxLevel(8)
            .setCooldownSeconds(80)
            .build();

    public RipAndTearSpell() {
        this.manaCostPerLevel = 8;
        this.baseSpellPower = 2;
        this.spellPowerPerLevel = 1;
        this.castTime = 5;
        this.baseManaCost = 50;
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
    public int getRecastCount(int spellLevel, @Nullable LivingEntity entity) {
        return 3;
    }

    @Override
    public Optional<SoundEvent> getCastStartSound() {
        return Optional.of(GGSoundRegistry.CLAW_SPELL_CAST.get());
    }

    @Override
    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.empty();
    }


    @Override
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        final boolean ISEMPTYHAND = entity.getMainHandItem().isEmpty();

        if (ISEMPTYHAND) {
            if (!playerMagicData.getPlayerRecasts().hasRecastForSpell(getSpellId())) {
                playerMagicData.getPlayerRecasts().addRecast(new RecastInstance(getSpellId(), spellLevel, getRecastCount(spellLevel, entity), 80, castSource, null), playerMagicData);
            }

            if (entity.hasEffect(GGEffectRegistry.UNDEAD_RAMPAGE)) {
                float radius = 3.25f;
                float distance = 1.9f;
                Vec3 forward = entity.getForward();
                Vec3 hitLocation = entity.position().add(0, entity.getBbHeight() * .3f, 0).add(forward.scale(distance));
                var entities = level.getEntities(entity, AABB.ofSize(hitLocation, radius * 2, radius, radius * 2));
                var damageSource = this.getDamageSource(entity);
                for (
                        Entity targetEntity : entities) {
                    if (targetEntity instanceof LivingEntity && targetEntity.isAlive() && entity.isPickable() && targetEntity.position().subtract(entity.getEyePosition()).dot(forward) >= 0 && entity.distanceToSqr(targetEntity) < radius * radius && Utils.hasLineOfSight(level, entity.getEyePosition(), targetEntity.getBoundingBox().getCenter(), true)) {
                        Vec3 offsetVector = targetEntity.getBoundingBox().getCenter().subtract(entity.getEyePosition());
                        int i = getDuration(baseSpellPower, entity);
                        if (offsetVector.dot(forward) >= 0) {
                            if (DamageSources.applyDamage(targetEntity, getDamage (spellLevel, entity) * 1.5F, damageSource)) {
                                ((LivingEntity) targetEntity).addEffect(new MobEffectInstance(ALObjects.MobEffects.GRIEVOUS, i , 0));
                                ((LivingEntity) targetEntity).addEffect(new MobEffectInstance(ALObjects.MobEffects.BLEEDING, i , 0));
                                MagicManager.spawnParticles(level, ParticleHelper.BLOOD_GROUND, targetEntity.getX(), targetEntity.getY() + targetEntity.getBbHeight() * .5f, targetEntity.getZ(), 50, targetEntity.getBbWidth() * .5f, targetEntity.getBbHeight() * .5f, targetEntity.getBbWidth() * .5f, .03, false);
                                MagicManager.spawnParticles(level, ParticleTypes.SCULK_SOUL, targetEntity.getX(), targetEntity.getY() + targetEntity.getBbHeight() * .5f, targetEntity.getZ(), 50, targetEntity.getBbWidth() * .5f, targetEntity.getBbHeight() * .5f, targetEntity.getBbWidth() * .5f, .03, false);
                                EnchantmentHelper.doPostAttackEffects((ServerLevel) level, targetEntity, damageSource);
                            }
                        }
                    }
                }
                boolean mirrored = playerMagicData.getCastingEquipmentSlot().equals(SpellSelectionManager.OFFHAND);
               BoneClawEntity bone = new BoneClawEntity(level, mirrored);
                bone.moveTo(hitLocation);
                bone.setYRot(entity.getYRot());
                bone.setXRot(entity.getXRot());
                level.addFreshEntity(bone);
                level.getEntities(entity, entity.getBoundingBox().inflate(radius, 4, radius), (target) -> !DamageSources.isFriendlyFireBetween(target, entity) && Utils.hasLineOfSight(level, entity, target, true)).forEach(target -> {
                    super.onCast(level, spellLevel, entity, castSource, playerMagicData);

                });

            }


             else {
                float radius = 3.25f;
                float distance = 1.9f;
                Vec3 forward = entity.getForward();
                Vec3 hitLocation = entity.position().add(0, entity.getBbHeight() * .3f, 0).add(forward.scale(distance));
                var entities = level.getEntities(entity, AABB.ofSize(hitLocation, radius * 2, radius, radius * 2));
                var damageSource = this.getDamageSource(entity);
                for (
                        Entity targetEntity : entities) {
                    if (targetEntity instanceof LivingEntity && targetEntity.isAlive() && entity.isPickable() && targetEntity.position().subtract(entity.getEyePosition()).dot(forward) >= 0 && entity.distanceToSqr(targetEntity) < radius * radius && Utils.hasLineOfSight(level, entity.getEyePosition(), targetEntity.getBoundingBox().getCenter(), true)) {
                        Vec3 offsetVector = targetEntity.getBoundingBox().getCenter().subtract(entity.getEyePosition());
                        int i = getDuration(baseSpellPower, entity);
                        if (offsetVector.dot(forward) >= 0) {
                            if (DamageSources.applyDamage(targetEntity, getDamage (spellLevel, entity), damageSource)) {
                                ((LivingEntity) targetEntity).addEffect(new MobEffectInstance(ALObjects.MobEffects.BLEEDING, i , 0));
                                MagicManager.spawnParticles(level, ParticleHelper.BLOOD_GROUND, targetEntity.getX(), targetEntity.getY() + targetEntity.getBbHeight() * .5f, targetEntity.getZ(), 50, targetEntity.getBbWidth() * .5f, targetEntity.getBbHeight() * .5f, targetEntity.getBbWidth() * .5f, .03, false);
                                EnchantmentHelper.doPostAttackEffects((ServerLevel) level, targetEntity, damageSource);
                            }
                        }
                    }
                }
                boolean mirrored = playerMagicData.getCastingEquipmentSlot().equals(SpellSelectionManager.OFFHAND);
                ClawEntity bone = new ClawEntity(level, mirrored);
                bone.moveTo(hitLocation);
                bone.setYRot(entity.getYRot());
                bone.setXRot(entity.getXRot());
                level.addFreshEntity(bone);
                level.getEntities(entity, entity.getBoundingBox().inflate(radius, 4, radius), (target) -> !DamageSources.isFriendlyFireBetween(target, entity) && Utils.hasLineOfSight(level, entity, target, true)).forEach(target -> {
                    super.onCast(level, spellLevel, entity, castSource, playerMagicData);

                });

            }


        }


        else {
            if (entity instanceof ServerPlayer serverPlayer) {
                serverPlayer.connection.send(new ClientboundSetActionBarTextPacket(Component.translatable("ui.enders_spells.not_empty_hand", this.getDisplayName(serverPlayer)).withStyle(ChatFormatting.RED)));
            }
            super.onCast(level, spellLevel, entity, castSource, playerMagicData);
        }

    }

    public int getDuration(int spellLevel, LivingEntity caster) {
        return (int) (getSpellPower(spellLevel, caster) * 30);
    }

    private float getDamage(int spellLevel, LivingEntity entity) {
        return getSpellPower(spellLevel, entity) ;
    }


    @Override
    public AnimationHolder getCastStartAnimation() {
        return SpellAnimations.SLASH_ANIMATION;
    }


}









