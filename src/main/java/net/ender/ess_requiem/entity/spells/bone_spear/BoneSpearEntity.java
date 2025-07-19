package net.ender.ess_requiem.entity.spells.bone_spear;

import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import net.ender.ess_requiem.registries.GGEntityRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;
import java.util.Optional;

public class BoneSpearEntity extends AbstractMagicProjectile implements GeoEntity {

    public double prevDeltaMovementX;
    public double prevDeltaMovementY;
    public double prevDeltaMovementZ;
    public BoneSpearEntity(Level level, LivingEntity shooter) {
    this(GGEntityRegistry.BONE_SPEAR.get(), level);
        setOwner(shooter);
    }


    public BoneSpearEntity(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    public void trailParticles() {

    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        discard();
    }


    @Override
    public void impactParticles(double x, double y, double z) {
        MagicManager.spawnParticles(level(), ParticleTypes.SPIT, x, y, z, 75, .1, .1, .1, 2, true);
    }

    @Override
    public float getSpeed() {
        return 1.5F;
    }

//ACE tysm
    @Override
    public void travel() {
        this.setPos(this.position().add(this.getDeltaMovement()));
        if (!this.isNoGravity())
        {
            Vec3 vec3 = this.getDeltaMovement();
            this.setDeltaMovement(vec3.x, vec3.y - 0.05000000074505806, vec3.z);
        }
    }

    @Override
    public void tick() {
        this.prevDeltaMovementX = getDeltaMovement().x;
        this.prevDeltaMovementY = getDeltaMovement().y;
        this.prevDeltaMovementZ = getDeltaMovement().z;

        setYRot(-((float) Mth.atan2(getDeltaMovement().x, getDeltaMovement().z)) * (180F / (float) Math.PI));


    }

    @Override
    public Optional<Holder<SoundEvent>> getImpactSound () {
        return Optional.of((Holder<SoundEvent>) SoundEvents.WITHER_DEATH);
    }

    private final AnimationController<BoneSpearEntity> blankAnimationController = new AnimationController<>(this, "casting_controller", 0, this::blank);

    RawAnimation animationToPlay = null;


    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(blankAnimationController);

    }

    private PlayState blank(AnimationState<BoneSpearEntity> event)
    {
        event.getController().setAnimation(RawAnimation.begin().thenPlay("spear_stab"));
            return PlayState.CONTINUE;
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

}
