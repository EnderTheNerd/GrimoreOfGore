package net.ender.ess_requiem.registries;

import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class GGSoundRegistry {
    private static final DeferredRegister<SoundEvent> SOUND_EVENT = DeferredRegister.create(Registries.SOUND_EVENT, EndersSpellsAndStuffRequiem.MOD_ID);



    public static DeferredHolder<SoundEvent, SoundEvent> CLAW_SPELL_CAST = registerSoundEvent("claw_attack");

    public static DeferredHolder<SoundEvent, SoundEvent> PACT_SPELL_CAST = registerSoundEvent("pact_cast");

    public static DeferredHolder<SoundEvent, SoundEvent> MIND_GENERIC_CAST = registerSoundEvent("mind_generic_cast");


    private static DeferredHolder<SoundEvent, SoundEvent> registerSoundEvent(String name)
    {
        return SOUND_EVENT.register(name, () -> SoundEvent.createVariableRangeEvent
                (ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus)
    {
        SOUND_EVENT.register(eventBus);
    }


}
