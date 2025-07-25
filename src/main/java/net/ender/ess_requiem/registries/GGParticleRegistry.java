package net.ender.ess_requiem.registries;

import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;
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

}
