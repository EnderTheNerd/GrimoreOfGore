package net.ender.ess_requiem.entity.spells.wretch_breath;

import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.AbstractConeProjectile;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.ender.ess_requiem.registries.GGEntityRegistry;
import net.ender.ess_requiem.registries.GGSpellRegistry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class WretchBreath extends AbstractConeProjectile {
    public WretchBreath(EntityType<? extends AbstractConeProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public WretchBreath(Level level, LivingEntity entity) {
        super(GGEntityRegistry.WRETCH_BREATH_PROJECTILE.get(), level, entity);
    }

    @Override
    public void spawnParticles() {
        var owner = getOwner();
        if (!level().isClientSide || owner == null) {
            return;
        }
        Vec3 rotation = owner.getLookAngle().normalize();
        var pos = owner.position().add(rotation.scale(1.6));

        double x = pos.x;
        double y = pos.y + owner.getEyeHeight() * .9f;
        double z = pos.z;

        double speed = random.nextDouble() * .35 + .35;
        for (int i = 0; i < 10; i++) {
            double offset = .15;
            double ox = Math.random() * 2 * offset - offset;
            double oy = Math.random() * 2 * offset - offset;
            double oz = Math.random() * 2 * offset - offset;

            double angularness = .5;
            Vec3 randomVec = new Vec3(Math.random() * 2 * angularness - angularness, Math.random() * 2 * angularness - angularness, Math.random() * 2 * angularness - angularness).normalize();
            Vec3 result = (rotation.scale(3).add(randomVec)).normalize().scale(speed);
            level().addParticle(ParticleHelper.BLOOD, x + ox, y + oy, z + oz, result.x, result.y, result.z);
            level().addParticle(ParticleTypes.ASH, x + ox, y + oy, z + oz, result.x, result.y, result.z);
            level().addParticle(ParticleTypes.CRIMSON_SPORE, x + ox, y + oy, z + oz, result.x, result.y, result.z);
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        var entity = entityHitResult.getEntity();
        DamageSources.applyDamage(entity, damage, GGSpellRegistry.WRETCH.get().getDamageSource(this, getOwner()));
    }
}


