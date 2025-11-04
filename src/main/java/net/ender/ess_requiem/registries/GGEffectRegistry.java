package net.ender.ess_requiem.registries;

import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;
import net.ender.ess_requiem.effects.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Mob;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class GGEffectRegistry {
    public static final DeferredRegister<MobEffect> MOB_EFFECT_DEFERRED_REGISTER = DeferredRegister.create(Registries.MOB_EFFECT, EndersSpellsAndStuffRequiem.MOD_ID);

    public static void register(IEventBus eventBus) {
        MOB_EFFECT_DEFERRED_REGISTER.register(eventBus);
    }

//BLOOD EFFECTS

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

    public static final DeferredHolder<MobEffect, MobEffect> BOILED_MANA = MOB_EFFECT_DEFERRED_REGISTER.register("boiled_mana",
            ()-> new BoilingManaEffect(MobEffectCategory.HARMFUL, 9833512));

    public static final DeferredHolder<MobEffect, MobEffect> DECAYING_MIGHT = MOB_EFFECT_DEFERRED_REGISTER.register("decaying_might",
            ()-> new DecayingMightEffect(MobEffectCategory.NEUTRAL,10392961 ));

    public static final DeferredHolder<MobEffect, MobEffect> LORD_OF_DECAY = MOB_EFFECT_DEFERRED_REGISTER.register("lord_of_decay",
            ()-> new LordOfDecayEffect(MobEffectCategory.BENEFICIAL,3353638));

    public static final DeferredHolder<MobEffect, MobEffect> FINALITY_OF_DECAY = MOB_EFFECT_DEFERRED_REGISTER.register("finality_of_decay",
            ()-> new FinalityOfDecayEffect(MobEffectCategory.BENEFICIAL,3353638));

    public static final DeferredHolder<MobEffect, MobEffect> MARK_OF_DECAY = MOB_EFFECT_DEFERRED_REGISTER.register("mark_of_decay",
            ()-> new MarkOfDecayEffect(MobEffectCategory.HARMFUL,9833512));



    //Eldritch Effects

    public static final DeferredHolder<MobEffect, MobEffect> EBONY_ARMOR = MOB_EFFECT_DEFERRED_REGISTER.register("ebony_armor",
            () -> new EbonyArmorEffect(MobEffectCategory.NEUTRAL, 2367002));

    public static final DeferredHolder<MobEffect, MobEffect> PROTECTION_OF_ASHES = MOB_EFFECT_DEFERRED_REGISTER.register("protection_of_ash",
            () -> new ProtectionOfAshesEffect(MobEffectCategory.BENEFICIAL, 1973790));

    public static final DeferredHolder<MobEffect, MobEffect> CURSED_IMMORTALITY = MOB_EFFECT_DEFERRED_REGISTER.register("cursed_immortality",
            () -> new CursedImmortalityEffect(MobEffectCategory.NEUTRAL, 2033979));

    public static final DeferredHolder<MobEffect, MobEffect> CURSED_VOW = MOB_EFFECT_DEFERRED_REGISTER.register("cursed_vow",
            () -> new CursedVowEffect(MobEffectCategory.NEUTRAL, 338225));

    public static final DeferredHolder<MobEffect, MobEffect> EBONY_CATAPHRACT = MOB_EFFECT_DEFERRED_REGISTER.register("ebony_cataphract",
            () -> new EbonyCataphractEffect(MobEffectCategory.NEUTRAL, 13111603));


    //ICE EFFECTS
    public static final DeferredHolder<MobEffect, MobEffect> LORD_OF_FROST = MOB_EFFECT_DEFERRED_REGISTER.register("lord_of_frost",
            () -> new LordOfTheFinalFrost(MobEffectCategory.BENEFICIAL, 11131887));
}
