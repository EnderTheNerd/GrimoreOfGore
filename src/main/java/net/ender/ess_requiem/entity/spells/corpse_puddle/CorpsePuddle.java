package net.ender.ess_requiem.entity.spells.corpse_puddle;

import io.redspace.ironsspellbooks.entity.spells.AoeEntity;
import io.redspace.ironsspellbooks.registries.ParticleRegistry;
import net.ender.ess_requiem.registries.GGEntityRegistry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class CorpsePuddle extends AoeEntity {
    private DamageSource damageSource;

    public CorpsePuddle(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public CorpsePuddle(Level level) {
        this(GGEntityRegistry.CORPSE_PUDDLE.get(), level);
    }



    @Override
    public void applyEffect(LivingEntity target) {
        target.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 150, 2));
        target.addEffect(new MobEffectInstance(MobEffects.WITHER, 150, 2));
    }
    @Override
    public float getParticleCount() {
        return 1.5f * getRadius();
    }

    @Override
    protected float particleYOffset() {
        return .25f;
    }

    @Override
    protected float getParticleSpeedModifier() {
        return 1.4f;
    }

    @Override
    public Optional<ParticleOptions> getParticle() {
        return Optional.of(ParticleRegistry.BLOOD_PARTICLE.get());
    }

}
