package net.ender.ess_requiem.entity.mobs.skull_mass;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.entity.mobs.IAnimatedAttacker;
import io.redspace.ironsspellbooks.entity.mobs.IMagicSummon;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob;
import io.redspace.ironsspellbooks.entity.mobs.goals.*;
import io.redspace.ironsspellbooks.util.OwnerHelper;
import net.ender.ess_requiem.registries.GGEffectRegistry;
import net.ender.ess_requiem.registries.GGEntityRegistry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class SkullMassEntity  extends AbstractSpellCastingMob implements IMagicSummon, GeoAnimatable, IAnimatedAttacker {


    public SkullMassEntity(Level level, LivingEntity owner) {
        this(GGEntityRegistry.SKULL_MASS.get(), level);
        setSummoner(owner);
        this.moveControl = new FlyingMoveControl(this, 20, true);
        this.lookControl = createLookControl();
    }

    public SkullMassEntity(EntityType<? extends AbstractSpellCastingMob> entityType, Level world) {
        super(entityType, world);
        xpReward = 0;
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pSpawnType, @org.jetbrains.annotations.Nullable SpawnGroupData pSpawnGroupData) {
        this.setNoGravity(true);
        return super.finalizeSpawn(pLevel, pDifficulty, pSpawnType, pSpawnGroupData);
    }


    protected LookControl createLookControl() {
        return new LookControl(this) {
            @Override
            protected float rotateTowards(float from, float to, float maxDelta) {
                return super.rotateTowards(from, to, maxDelta * 2.5F);
            }

            @Override
            protected boolean resetXRotOnTick() {
                return getTarget() == null;
            }
        };
    }

    protected LivingEntity cachedSummoner;
    protected UUID summonerUUID;

    //MOB AI and Attributes
    public static AttributeSupplier.Builder createAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(Attributes.ATTACK_DAMAGE, 5)
                .add(Attributes.MAX_HEALTH, 60.0)
                .add(Attributes.FOLLOW_RANGE, 60.0)
                .add(Attributes.ENTITY_INTERACTION_RANGE, 2.0)
                .add(Attributes.MOVEMENT_SPEED, .2)
                .add(AttributeRegistry.BLOOD_SPELL_POWER, .8)
                .add(Attributes.FLYING_SPEED, 1.5);
    }

    @Override
    public void registerGoals() {
        this.goalSelector.addGoal(2, new WizardAttackGoal(this, 1.5, 25, 50)
                .setSpells(
                        List.of(SpellRegistry.ACUPUNCTURE_SPELL.get(), SpellRegistry.BLOOD_NEEDLES_SPELL.get(), SpellRegistry.BLOOD_SLASH_SPELL.get(), SpellRegistry.WITHER_SKULL_SPELL.get(), SpellRegistry.FROSTWAVE_SPELL.get()),
                        List.of(),
                        List.of(SpellRegistry.HEARTSTOP_SPELL.get()),
                        List.of()
                ));

        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(7, new GenericFollowOwnerGoal(this, this::getSummoner, 0.9f, 8, 2, false, 50));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 0.9D));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));

        this.targetSelector.addGoal(1, new GenericOwnerHurtByTargetGoal(this, this::getSummoner));
        this.targetSelector.addGoal(2, new GenericOwnerHurtTargetGoal(this, this::getSummoner));
        this.targetSelector.addGoal(3, new GenericCopyOwnerTargetGoal(this, this::getSummoner));
        this.targetSelector.addGoal(4, (new GenericHurtByTargetGoal(this, (entity) -> entity == getSummoner())).setAlertOthers());

    }

    @Override
    public boolean canDrownInFluidType(FluidType type) {
        return false;
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    protected PathNavigation createNavigation (Level plevel) {
        FlyingPathNavigation flyingPathNavigation = new FlyingPathNavigation(this, plevel);
        flyingPathNavigation.canFloat();
        flyingPathNavigation.canPassDoors();
        return flyingPathNavigation;
    }

    @Override
    public LivingEntity getSummoner() {
        return OwnerHelper.getAndCacheOwner(level(), cachedSummoner, summonerUUID);
    }

    @Override
    public void onUnSummon() {
        if (!this.level().isClientSide) {
            MagicManager.spawnParticles(this.level(), ParticleTypes.ENCHANT,
                    getX(), getY(), getZ(),
                    25, 0.4, 0.8, 0.4, 0.03, false);
            discard();
        }
    }

    public void setSummoner(@Nullable LivingEntity owner) {
        if (owner != null) {
            this.summonerUUID = owner.getUUID();
            this.cachedSummoner = owner;
        }
    }

    @Override
    public boolean isAlliedTo(Entity entityIn) {
        return super.isAlliedTo(entityIn) || this.isAlliedHelper(entityIn);
    }


    @Override
    public void die(DamageSource pDamageSource) {
        this.onDeathHelper();
        super.die(pDamageSource);
    }

    @Override
    public void onRemovedFromLevel() {


        super.onRemovedFromLevel();
    }

    private void onRemovedHelper(SkullMassEntity entity, DeferredHolder<MobEffect, MobEffect> cursedSkullTimer) {
    }

    @Override
    public void remove(RemovalReason pReason) {
        super.remove(pReason);
    }

    //Sounds and Stuff
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.SKELETON_CONVERTED_TO_STRAY;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.BONE_BLOCK_BREAK;
    }

    @Override
    protected @org.jetbrains.annotations.Nullable SoundEvent getAmbientSound() {
        return SoundEvents.WITHER_SKELETON_AMBIENT;
    }

    // NBT Junk
    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.summonerUUID = OwnerHelper.deserializeOwner(pCompound);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        OwnerHelper.serializeOwner(pCompound, summonerUUID);
    }

    //I HATE GECKOLIB
    private final AnimationController<SkullMassEntity> castingAnimationController = new AnimationController<>(this, "casting_controller", 0, this::castingPredicate);

    RawAnimation animationToPlay = null;


    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(castingAnimationController);

    }

    private PlayState castingPredicate(AnimationState<SkullMassEntity> event)
    {

        if (isCasting() && this.animationToPlay == null)
        {
            event.getController().setAnimation(RawAnimation.begin().thenPlay("skeleton_attack"));
            return PlayState.CONTINUE;
        }


        return PlayState.STOP;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    @Override
    public double getTick(Object object) {
        return this.tickCount;
    }

    @Override
    public void playAnimation(String s) {

    }

}
