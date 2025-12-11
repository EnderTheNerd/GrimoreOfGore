package net.ender.ess_requiem.entity.mobs.summoned_weapon;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.entity.mobs.IAnimatedAttacker;
import io.redspace.ironsspellbooks.entity.mobs.IMagicSummon;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob;
import io.redspace.ironsspellbooks.entity.mobs.goals.*;
import io.redspace.ironsspellbooks.entity.mobs.goals.melee.AttackAnimationData;
import io.redspace.ironsspellbooks.entity.mobs.wizards.GenericAnimatedWarlockAttackGoal;
import net.ender.ess_requiem.registries.GGEntityRegistry;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.neoforged.neoforge.fluids.FluidType;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;

public class SoulmasterSwordEntity  extends AbstractSpellCastingMob implements IMagicSummon, IAnimatedAttacker {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    GenericAnimatedWarlockAttackGoal<SoulmasterSwordEntity> attackGoal;


    public SoulmasterSwordEntity(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.moveControl = new FlyingMoveControl(this, 20, true);
        this.lookControl = createLookControl();
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pSpawnType, @org.jetbrains.annotations.Nullable SpawnGroupData pSpawnGroupData) {
        this.setNoGravity(true);
        return super.finalizeSpawn(pLevel, pDifficulty, pSpawnType, pSpawnGroupData);
    }

    @Override
    public boolean canDrownInFluidType(FluidType type) {
        return false;
    }

    @Override
    public boolean fireImmune() {
        return true;
    }


    public static AttributeSupplier.Builder createAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(Attributes.ATTACK_KNOCKBACK, 1.0)
                .add(Attributes.ATTACK_DAMAGE, 0)
                .add(Attributes.MAX_HEALTH, 30.0)
                .add(Attributes.FOLLOW_RANGE, 50)
                .add(Attributes.FLYING_SPEED, 2.5)
                .add(Attributes.ENTITY_INTERACTION_RANGE, 4)
                .add(Attributes.MOVEMENT_SPEED, .5);

    }

    @Override
    protected PathNavigation createNavigation(Level pLevel) {
        FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, pLevel);
        flyingpathnavigation.setCanOpenDoors(false);
        flyingpathnavigation.setCanFloat(true);
        flyingpathnavigation.setCanPassDoors(true);
        return flyingpathnavigation;
    }



    public SoulmasterSwordEntity(Level level, LivingEntity owner) {
        this(GGEntityRegistry.SOULMASTER_SWORD.get(), level);

    }
    @Override
    protected void registerGoals() {

        goalSelector.addGoal(3, new GenericFollowOwnerGoal(this, this::getSummoner, 1, 9, 4, true, 20));
        goalSelector.addGoal(5, new WaterAvoidingRandomFlyingGoal(this, 0.75));
        this.goalSelector.addGoal(1, new GenericAnimatedWarlockAttackGoal<>(this, 1.5F, 5, 10)
                .setMoveset(List.of(
                        new AttackAnimationData(13, "basic_swing", 3),
                        new AttackAnimationData(13, "basic_swipe", 5),
                        new AttackAnimationData(13, "grand_slam", 5)
                ))
                .setComboChance(3.5f)
                .setMeleeAttackInverval(5, 10)
                .setMeleeBias(1.0f, 1.0f)
                .setMeleeMovespeedModifier(1.0f)
        );
        this.targetSelector.addGoal(1, new GenericOwnerHurtByTargetGoal(this, this::getSummoner));
        this.targetSelector.addGoal(2, new GenericOwnerHurtTargetGoal(this, this::getSummoner));
        this.targetSelector.addGoal(3, new GenericCopyOwnerTargetGoal(this, this::getSummoner));
        this.targetSelector.addGoal(4, (new GenericHurtByTargetGoal(this, (entity) -> entity == getSummoner())).setAlertOthers());
        this.targetSelector.addGoal(5, new GenericProtectOwnerTargetGoal(this, this::getSummoner));
    }



    @Override
    public void onUnSummon() {

    }
    // Geckolib & Animations
    RawAnimation animationToPlay = null;

    private final AnimationController<SoulmasterSwordEntity> attackAnimationController = new AnimationController<>(this, "attack_controller", 0, this::attackPredicate);

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(attackAnimationController);
    }



    private PlayState attackPredicate(AnimationState<SoulmasterSwordEntity> event)
    {
        var controller = event.getController();

        if (this.animationToPlay != null)
        {
            controller.forceAnimationReset();
            controller.setAnimation(animationToPlay);
            animationToPlay = null;
        }

        return PlayState.CONTINUE;
    }

    @Override
    public void playAnimation(String animationId) {
        try {
            animationToPlay = RawAnimation.begin().thenPlay(animationId);
        } catch (Exception ignored) {
            IronsSpellbooks.LOGGER.error("Entity {} Failed to play animation: {}", this, animationId);
        }
    }

    @Override
    public boolean isAnimating() {
        return attackAnimationController.getAnimationState() != AnimationController.State.STOPPED || super.isAnimating();
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public double getTick(Object object) {
        return this.tickCount;
    }

}

