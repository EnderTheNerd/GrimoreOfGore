package net.ender.grimoire_of_gore.registries;
import net.ender.grimoire_of_gore.GrimoireOfGore;
import net.ender.grimoire_of_gore.effects.BaneOfTheDeadEffect;
import net.ender.grimoire_of_gore.effects.UndeadPactEffect;
import net.ender.grimoire_of_gore.effects.UndeadRampageEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class GGEffectRegistry {
    public static final DeferredRegister<MobEffect> MOB_EFFECT_DEFERRED_REGISTER = DeferredRegister.create(Registries.MOB_EFFECT, GrimoireOfGore.MOD_ID);

    public static void register(IEventBus eventBus) {
        MOB_EFFECT_DEFERRED_REGISTER.register(eventBus);
    }


    public static final DeferredHolder<MobEffect, MobEffect> UNDEAD_PACT = MOB_EFFECT_DEFERRED_REGISTER.register("undead_pact",
            () -> new UndeadPactEffect(MobEffectCategory.NEUTRAL, 5703960));

    public static final DeferredHolder<MobEffect, MobEffect> UNDEAD_RAMPAGE = MOB_EFFECT_DEFERRED_REGISTER.register("undead_rampage",
            ()-> new UndeadRampageEffect(MobEffectCategory.BENEFICIAL, 9833514));

    public static final DeferredHolder<MobEffect, MobEffect> BANE_OF_THE_DEAD = MOB_EFFECT_DEFERRED_REGISTER.register("bane_of_the_dead",
            ()-> new BaneOfTheDeadEffect(MobEffectCategory.HARMFUL,12851990 ));

}
