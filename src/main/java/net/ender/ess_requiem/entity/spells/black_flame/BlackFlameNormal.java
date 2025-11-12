package net.ender.ess_requiem.entity.spells.black_flame;

import io.redspace.ironsspellbooks.entity.spells.AoeEntity;
import net.ender.ess_requiem.entity.spells.pale_flame.PaleFlame;
import net.ender.ess_requiem.registries.GGEntityRegistry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Optional;

public class BlackFlameNormal extends AoeEntity {
    private static final EntityDataAccessor<Boolean> DATA_MIRRORED = SynchedEntityData.defineId(BlackFlameNormal.class, EntityDataSerializers.BOOLEAN);

    public BlackFlameNormal(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }


    LivingEntity target;

    public BlackFlameNormal(Level level, boolean mirrored) {
        this(GGEntityRegistry.BLACK_FLAME_REGULAR.get(), level);
        if (mirrored) {
            this.getEntityData().set(DATA_MIRRORED, true);
        }
    }



    public final int ticksPerFrame = 2;
    public final int deathTime = ticksPerFrame * 4;



    @Override
    public void tick() {
        if (!firstTick) {
            firstTick = true;
        }
        if (tickCount >= deathTime)
            discard();
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        super.defineSynchedData(pBuilder);
        pBuilder.define(DATA_MIRRORED, false);
    }

    public boolean isMirrored() {
        return this.getEntityData().get(DATA_MIRRORED);
    }

    @Override
    public boolean shouldBeSaved() {
        return false;
    }

    @Override
    public void refreshDimensions() {
        return;
    }

    @Override
    public void ambientParticles() {
        return;
    }

    @Override
    public float getParticleCount() {
        return 0;
    }

    @Override
    public Optional<ParticleOptions> getParticle() {
        return Optional.of(ParticleTypes.DRIPPING_OBSIDIAN_TEAR);
    }

    @Override
    public void recreateFromPacket(ClientboundAddEntityPacket pPacket) {
        super.recreateFromPacket(pPacket);
        this.xRotO = this.getXRot();
        this.yRotO = this.getYRot();
    }

    protected Vec3 getInflation() {
        return Vec3.ZERO;
    }

    @Override
    public void applyEffect(LivingEntity livingEntity) {

    }

}
