package net.ender.grimoire_of_gore.registries;

import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.AutoSpellConfig;
import net.ender.grimoire_of_gore.GrimoireOfGore;
import net.ender.grimoire_of_gore.spells.PactOfTheDeadSpell;
import net.ender.grimoire_of_gore.spells.RipAndTearSpell;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

@AutoSpellConfig
public class GGSpellRegistry {
    public static final DeferredRegister<AbstractSpell> SPELLS =
            DeferredRegister.create(io.redspace.ironsspellbooks.api.registry.SpellRegistry.SPELL_REGISTRY_KEY, GrimoireOfGore.MOD_ID);

    public static DeferredHolder<AbstractSpell, AbstractSpell> registerSpell(AbstractSpell spell) {
        return SPELLS.register(spell.getSpellName(), () -> spell);
    }

    //UNCRAFTABLE
    public static final Supplier <AbstractSpell> CLAW = registerSpell(new RipAndTearSpell());

    //CRAFTABLE
    public static final Supplier <AbstractSpell> UNDEAD_PACT = registerSpell (new PactOfTheDeadSpell());

    public static void register(IEventBus eventBus)
    {
        SPELLS.register(eventBus);
    }
}
