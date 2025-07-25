package net.ender.ess_requiem.registries;

import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;
import net.ender.ess_requiem.effects.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class GGEffectRegistry {
    public static final DeferredRegister<MobEffect> MOB_EFFECT_DEFERRED_REGISTER = DeferredRegister.create(Registries.MOB_EFFECT, EndersSpellsAndStuffRequiem.MOD_ID);

    public static void register(IEventBus eventBus) {
        MOB_EFFECT_DEFERRED_REGISTER.register(eventBus);
    }


    public static final DeferredHolder<MobEffect, MobEffect> UNDEAD_PACT = MOB_EFFECT_DEFERRED_REGISTER.register("undead_pact",
            () -> new UndeadPactEffect(MobEffectCategory.NEUTRAL, 5703960));

    public static final DeferredHolder<MobEffect, MobEffect> UNDEAD_RAMPAGE = MOB_EFFECT_DEFERRED_REGISTER.register("undead_rampage",
            ()-> new UndeadRampageEffect(MobEffectCategory.BENEFICIAL, 9833514));

    public static final DeferredHolder<MobEffect, MobEffect> BANE_OF_THE_DEAD = MOB_EFFECT_DEFERRED_REGISTER.register("bane_of_the_dead",
            ()-> new BaneOfTheDeadEffect(MobEffectCategory.HARMFUL,12851990 ));

    public static final DeferredHolder<MobEffect, MobEffect> STRAINED = MOB_EFFECT_DEFERRED_REGISTER.register("strained",
            ()-> new StrainEffect(MobEffectCategory.NEUTRAL,12851980 ));

    public static final DeferredHolder<MobEffect, MobEffect> REAPER = MOB_EFFECT_DEFERRED_REGISTER.register("reaper",
            ()-> new ReaperEffect(MobEffectCategory.BENEFICIAL, 12851903));

    public static final DeferredHolder<MobEffect, MobEffect> SOUL_SHIELD = MOB_EFFECT_DEFERRED_REGISTER.register("soul_shield",
            ()-> new SoulShieldEffect(MobEffectCategory.BENEFICIAL, 4487841));

    public static final DeferredHolder<MobEffect, MobEffect> CURSED_SKULL_TIMER = MOB_EFFECT_DEFERRED_REGISTER.register("cursed_skull_timer",
            ()-> new SoulShieldEffect(MobEffectCategory.BENEFICIAL, 13750177));

    public static final DeferredHolder<MobEffect, MobEffect> MENTAL_CRUSHING = MOB_EFFECT_DEFERRED_REGISTER.register("mental_crushing",
        ()-> new MentalCrushingEffect(MobEffectCategory.HARMFUL, 3193312));

    public static final DeferredHolder<MobEffect, MobEffect> CONFUSED = MOB_EFFECT_DEFERRED_REGISTER.register("confused",
            ()-> new ConfusedEffect(MobEffectCategory.HARMFUL,16566413));

    public static final DeferredHolder<MobEffect, MobEffect> THOUGHT = MOB_EFFECT_DEFERRED_REGISTER.register("thought",
            ()-> new ThoughtEffect(MobEffectCategory.BENEFICIAL, 16773065));

    public static final DeferredHolder<MobEffect, MobEffect> WEIGHED_DOWN = MOB_EFFECT_DEFERRED_REGISTER.register("weighed_down",
            ()-> new WeighedDownEffect(MobEffectCategory.HARMFUL, 5533306));

}
