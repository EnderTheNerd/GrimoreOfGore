package net.ender.ess_requiem.entity.mobs.summoned_weapon;

import io.redspace.ironsspellbooks.entity.mobs.IAnimatedAttacker;
import io.redspace.ironsspellbooks.entity.mobs.IMagicSummon;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob;
import io.redspace.ironsspellbooks.entity.mobs.goals.*;
import io.redspace.ironsspellbooks.entity.mobs.goals.melee.AttackAnimationData;
import io.redspace.ironsspellbooks.entity.mobs.goals.melee.AttackKeyframe;
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
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.fluids.FluidType;

import java.util.List;

public class SoulmasterSwordEntity  extends AbstractSpellCastingMob implements IMagicSummon, IAnimatedAttacker {

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
                .add(Attributes.ATTACK_DAMAGE, 5.0)
                .add(Attributes.MAX_HEALTH, 25.0)
                .add(Attributes.FOLLOW_RANGE, 50)
                .add(Attributes.FLYING_SPEED, 1.5)
                .add(Attributes.ENTITY_INTERACTION_RANGE, 4)
                .add(Attributes.MOVEMENT_SPEED, .5);

    }

    @Override
    protected PathNavigation createNavigation (Level plevel) {
        FlyingPathNavigation flyingPathNavigation = new FlyingPathNavigation(this, plevel);
        flyingPathNavigation.canFloat();
        flyingPathNavigation.canPassDoors();
        return flyingPathNavigation;
    }




    public SoulmasterSwordEntity(Level level, LivingEntity owner) {
        this(GGEntityRegistry.SOULMASTER_SWORD.get(), level);

    }

    public GenericAnimatedWarlockAttackGoal<SoulmasterSwordEntity> makeAttackGoal() {
        return new GenericAnimatedWarlockAttackGoal<>(this, 1.5, 0, 20)
                .setMoveset(List.of(
                                new AttackAnimationData(36, "summoned_sword_basic_swing", 20),
                                new AttackAnimationData(52, "summoned_sword_basic_dual_swing", 20, 35),
                                new AttackAnimationData(40, "summoned_sword_multistab", 20, 26, 32),
                                AttackAnimationData.builder("summoned_sword_pommel_strike")
                                        .length(24).attacks(new AttackKeyframe(12, new Vec3(0, 0, .45f), new Vec3(0, 0, 1))).build(),
                                AttackAnimationData.builder("summoned_sword_basic_downswing")
                                        .length(45).attacks(new AttackKeyframe(25, new Vec3(0, -0.2, .15f), new Vec3(0, 0, 1))).area(.7f).build()
                        )

                );


    }

    @Override
    protected void registerGoals() {
        attackGoal = makeAttackGoal();
        goalSelector.addGoal(1, attackGoal.setMeleeBias(1f, 1f));
        goalSelector.addGoal(3, new GenericFollowOwnerGoal(this, this::getSummoner, 1, 9, 4, true, 20));
        goalSelector.addGoal(5, new WaterAvoidingRandomFlyingGoal(this, 0.75));

        this.targetSelector.addGoal(1, new GenericOwnerHurtByTargetGoal(this, this::getSummoner));
        this.targetSelector.addGoal(2, new GenericOwnerHurtTargetGoal(this, this::getSummoner));
        this.targetSelector.addGoal(3, new GenericCopyOwnerTargetGoal(this, this::getSummoner));
        this.targetSelector.addGoal(4, (new GenericHurtByTargetGoal(this, (entity) -> entity == getSummoner())).setAlertOthers());
        this.targetSelector.addGoal(5, new GenericProtectOwnerTargetGoal(this, this::getSummoner));
    }

    @Override
    public void playAnimation(String s) {

    }

    @Override
    public void onUnSummon() {

    }
}
