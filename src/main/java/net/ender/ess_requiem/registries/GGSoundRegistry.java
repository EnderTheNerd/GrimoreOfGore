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


    //BLOOD
    public static DeferredHolder<SoundEvent, SoundEvent> CLAW_SPELL_CAST = registerSoundEvent("claw_attack");

    public static DeferredHolder<SoundEvent, SoundEvent> PACT_SPELL_CAST = registerSoundEvent("pact_cast");

    //MIND
    public static DeferredHolder<SoundEvent, SoundEvent> MIND_GENERIC_CAST = registerSoundEvent("mind_generic_cast");

    public static DeferredHolder<SoundEvent, SoundEvent> CLOCK_TICKING = registerSoundEvent("preserved_clock");

    public static DeferredHolder<SoundEvent, SoundEvent> BONES_SHATTER = registerSoundEvent("bone_shatter");


    //ELDRITCH
    public static DeferredHolder<SoundEvent, SoundEvent> PALE_FLAME_START =registerSoundEvent("pale_flame_start");

    public static DeferredHolder<SoundEvent, SoundEvent> PALE_FLAME_END =registerSoundEvent("pale_flame_end");

    public static DeferredHolder<SoundEvent, SoundEvent> CURSED_REVIVE = registerSoundEvent("cursed_revive");

    public static DeferredHolder<SoundEvent, SoundEvent> EBONY_CATAPHRACT_IMPACT = registerSoundEvent("ebony_cataphract_impact");

    public static DeferredHolder<SoundEvent, SoundEvent> MIDNIGHT_EMBRACE_GLASS_SHATTER = registerSoundEvent("midnight_embrace_shatter_glass");

    public static DeferredHolder<SoundEvent, SoundEvent> BLACK_FLAME_WINDUP = registerSoundEvent("black_flame_windup");

    public static DeferredHolder<SoundEvent, SoundEvent> BLACK_FLAME_FINISH = registerSoundEvent("black_flame_finish");

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
