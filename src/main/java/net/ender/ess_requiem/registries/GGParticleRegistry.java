package net.ender.ess_requiem.registries;

import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;
import net.ender.ess_requiem.particle.DarkSlashParticle;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class GGParticleRegistry {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(Registries.PARTICLE_TYPE, EndersSpellsAndStuffRequiem.MOD_ID);

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }

    public static final Supplier<SimpleParticleType> CONFUSION_EYE_PARTICLE = PARTICLE_TYPES.register("the_eye", () -> new SimpleParticleType(false));

    public static final Supplier<SimpleParticleType> CATAPHRACT_SHARD_PARTICLE = PARTICLE_TYPES.register("cataphract_shard", () -> new SimpleParticleType(false));

    public static final Supplier<SimpleParticleType> CATAPHRACT_SPIRAL_PARTICLE = PARTICLE_TYPES.register("cataphract_spiral.json", () -> new SimpleParticleType(false));

    public static final Supplier<SimpleParticleType> CATAPHRACT_STAR_ONE_PARTICLE = PARTICLE_TYPES.register("cataphract_star", () -> new SimpleParticleType(false));

    public static final Supplier<SimpleParticleType> WITHER_SKULL_SMALL = PARTICLE_TYPES.register("wither_skull_small", () -> new SimpleParticleType(false));

    public static final Supplier<SimpleParticleType> DARK_SLASH = PARTICLE_TYPES.register("dark_slash", () -> new SimpleParticleType(false));

    public static final Supplier<SimpleParticleType> BIG_SLASH_LEFT = PARTICLE_TYPES.register("big_slash_left", () -> new SimpleParticleType(false));

    public static final Supplier<SimpleParticleType> BIG_SLASH_RIGHT = PARTICLE_TYPES.register("big_slash_right", () -> new SimpleParticleType(false));

}
