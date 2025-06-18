package net.ender.grimoire_of_gore.entity.spells.claw;

import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.AoeEntity;
import net.ender.grimoire_of_gore.registries.GGEntityRegistry;
import net.ender.grimoire_of_gore.registries.GGSpellRegistry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class ClawEntity extends AoeEntity {
    private static final EntityDataAccessor<Boolean> DATA_MIRRORED = SynchedEntityData.defineId(ClawEntity.class, EntityDataSerializers.BOOLEAN);

    public ClawEntity(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public int strengthLevel;
    LivingEntity target;

    public ClawEntity(Level level, boolean mirrored) {
        this(GGEntityRegistry.CLAW_ENTITY.get(), level);
        if (mirrored) {
            this.getEntityData().set(DATA_MIRRORED, true);
        }
    }

    @Override
    public void applyEffect(LivingEntity target) {
        if (target == this.target) {
            if (DamageSources.applyDamage(target, getDamage(), GGSpellRegistry.CLAW.get().getDamageSource(this, getOwner())) && getOwner() instanceof LivingEntity livingOwner) {
                if (target.isDeadOrDying()) {
                    var StrengthOld = livingOwner.getEffect(MobEffects.DAMAGE_BOOST);
                    var addition = -1;
                    if (StrengthOld != null)
                        addition = StrengthOld.getAmplifier() + 1;
                    livingOwner.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 20, Math.min(strengthLevel + addition, 8), false, false, true));

                }
            }
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
        return Optional.empty();
    }

    @Override
    public void recreateFromPacket(ClientboundAddEntityPacket pPacket) {
        super.recreateFromPacket(pPacket);
        this.xRotO = this.getXRot();
        this.yRotO = this.getYRot();
    }

}
