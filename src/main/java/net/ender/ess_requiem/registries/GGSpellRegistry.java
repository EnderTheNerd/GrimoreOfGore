package net.ender.ess_requiem.registries;

import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.AutoSpellConfig;
import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;

import net.ender.ess_requiem.spells.blood.*;
import net.ender.ess_requiem.spells.mind.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.checkerframework.checker.units.qual.A;

import java.util.function.Supplier;

@AutoSpellConfig
public class GGSpellRegistry {
    public static final DeferredRegister<AbstractSpell> SPELLS =
            DeferredRegister.create(io.redspace.ironsspellbooks.api.registry.SpellRegistry.SPELL_REGISTRY_KEY, EndersSpellsAndStuffRequiem.MOD_ID);

    public static DeferredHolder<AbstractSpell, AbstractSpell> registerSpell(AbstractSpell spell) {
        return SPELLS.register(spell.getSpellName(), () -> spell);
    }




    //CRAFTABLE BLOOD
    public static final Supplier <AbstractSpell> UNDEAD_PACT = registerSpell (new PactOfTheDeadSpell());
    public static final Supplier <AbstractSpell> CLAW = registerSpell(new RipAndTearSpell());
    public static final Supplier <AbstractSpell> STRAIN = registerSpell(new StrainSpell());
    public static final Supplier <AbstractSpell> REAPER = registerSpell(new ReaperSpell());
    public static final Supplier <AbstractSpell> SKULLS =registerSpell(new SummonSkullsSpell());
    public static final Supplier <AbstractSpell> WRETCH =registerSpell(new WretchSpell());

    //CRAFTABLE MIND
    public static final Supplier <AbstractSpell> WEIGHT_OF_THE_WORLD = registerSpell(new WeightOfTheWorldSpell());
    public static final Supplier <AbstractSpell> OVERLOAD = registerSpell(new OverloadSpell());
    public static final Supplier<AbstractSpell> TO_STAND_BEFORE_ME =registerSpell(new ToStandBeforeMeSpell());
    public static final Supplier<AbstractSpell> A_MOMENT_IN_TIME =registerSpell(new AMomentInTimeSpell());
    public static final Supplier<AbstractSpell> MENTAL_SHATTER =registerSpell(new MentalShatterSpell());


    public static void register(IEventBus eventBus)
    {
        SPELLS.register(eventBus);
    }
}
