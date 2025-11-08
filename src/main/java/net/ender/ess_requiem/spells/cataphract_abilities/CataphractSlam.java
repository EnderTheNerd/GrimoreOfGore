package net.ender.ess_requiem.spells.cataphract_abilities;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;
import net.ender.ess_requiem.registries.GGEffectRegistry;
import net.ender.ess_requiem.registries.GGParticleRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.neoforged.neoforge.registries.DeferredHolder;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;


@AutoSpellConfig
public class CataphractSlam extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "cataphract_slam");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.translatable("ui.irons_spellbooks.damage", getDamage(spellLevel, caster)));
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.LEGENDARY)
            .setSchoolResource(SchoolRegistry.EVOCATION_RESOURCE)
            .setMaxLevel(1)
            .setCooldownSeconds(15)
            .build();

    public CataphractSlam() {
        this.manaCostPerLevel = 0;
        this.baseSpellPower = 8;
        this.spellPowerPerLevel = 3;
        this.castTime = 16;
        this.baseManaCost = 0;
    }

    @Override
    public boolean canBeInterrupted(Player player) {
        return false;
    }

    @Override
    public int getEffectiveCastTime(int spellLevel, @Nullable LivingEntity entity) {
        //due to melee animation timing, we do not want cast time attribute to affect this spell
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
    public Optional<SoundEvent> getCastStartSound() {
        return Optional.of(SoundEvents.MACE_SMASH_AIR);
    }

    @Override
    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.of(SoundEvents.MACE_SMASH_GROUND_HEAVY);
    }

    @Override
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        if (entity.hasEffect(GGEffectRegistry.ABILITY_COOLDOWN)) {
            if (entity instanceof ServerPlayer serverPlayer) {
                serverPlayer.connection.send(new ClientboundSetActionBarTextPacket(Component.translatable("ui.ess_requiem.on_cooldown", this.getDisplayName(serverPlayer)).withStyle(ChatFormatting.RED)));
            }
        }
        else {


            float radius = 2.2f;
            float range = 1.7f;
            Vec3 smiteLocation = Utils.raycastForBlock(level, entity.getEyePosition(), entity.getEyePosition().add(entity.getForward().multiply(range, 0, range)), ClipContext.Fluid.NONE).getLocation();
            Vec3 particleLocation = level.clip(new ClipContext(smiteLocation, smiteLocation.add(0, -2, 0), ClipContext.Block.VISUAL, ClipContext.Fluid.NONE, CollisionContext.empty())).getLocation().add(0, 0.1, 0);
            MagicManager.spawnParticles(level, new BlastwaveParticleOptions(SchoolRegistry.BLOOD.get().getTargetingColor(), radius * 2),
                    particleLocation.x, particleLocation.y, particleLocation.z, 1, 0, 0, 0, 0, true);
            MagicManager.spawnParticles(level, ParticleHelper.BLOOD, particleLocation.x, particleLocation.y, particleLocation.z, 50, 0, 0, 0, 1, false);
            MagicManager.spawnParticles(level, GGParticleRegistry.CATAPHRACT_STAR_ONE_PARTICLE.get(), particleLocation.x, particleLocation.y, particleLocation.z, 50, 0, 0, 0, 1, false);
            var entities = level.getEntities(entity, AABB.ofSize(smiteLocation, radius * 2, radius * 4, radius * 2));
            var damageSource = this.getDamageSource(entity);
            for (Entity targetEntity : entities) {

                if (targetEntity.isAlive() && targetEntity.isPickable() && Utils.hasLineOfSight(level, smiteLocation.add(0, 1, 0), targetEntity.getBoundingBox().getCenter(), true)) {
                    if (DamageSources.applyDamage(targetEntity, getDamage(spellLevel, entity), damageSource)) {
                        EnchantmentHelper.doPostAttackEffects((ServerLevel) level, targetEntity, damageSource);
                    }
                }
            }
            entity.addEffect(new MobEffectInstance(GGEffectRegistry.ABILITY_COOLDOWN, 160));
            super.onCast(level, spellLevel, entity, castSource, playerMagicData);
        }

    }

    private float getDamage(int spellLevel, LivingEntity entity)
    {
        float damage = getDamageForAttribute(this, entity, spellLevel, AttributeRegistry.ELDRITCH_SPELL_POWER, 3);
        return damage;
    }

    public static float getDamageForAttribute(CataphractSlam spell, LivingEntity entity, int spellLevel, DeferredHolder<Attribute, Attribute> attr1, float modifier)
    {
        double attrValue1;
        if(entity != null) {
            attrValue1 = entity.getAttributeValue(AttributeRegistry.ELDRITCH_SPELL_POWER);
        } else{
            attrValue1 = 1;
        }

        float damage = (float) (modifier * (spell.getSpellPower(spellLevel, entity) + attrValue1));

        return damage;
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







