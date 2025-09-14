package net.ender.ess_requiem.registries;

import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.AutoSpellConfig;
import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;

import net.ender.ess_requiem.spells.blood.*;
import net.ender.ess_requiem.spells.blood.uncraftable.CorpseExplosionSpell;
import net.ender.ess_requiem.spells.blood.uncraftable.DecayingWillSpell;
import net.ender.ess_requiem.spells.eldrtich.*;
import net.ender.ess_requiem.spells.ice.uncraftable.GlacialStatueSpell;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import javax.print.attribute.standard.Sides;
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
    public static final Supplier <AbstractSpell> BOILING_BLOOD =registerSpell(new BoilingBloodSpell());
    public static final Supplier<AbstractSpell> NECROTIC_BURST =registerSpell(new NecroticBurstSpell());

    //UNCRAFTABLE BLOOD
    public static final Supplier <AbstractSpell> DECAYING_WILL = registerSpell(new DecayingWillSpell());
    public static final Supplier <AbstractSpell> CORPSE_EXPLOSION = registerSpell(new CorpseExplosionSpell());
    //CRAFTABLE MIND

    //IMPLEMENTED, NOT FINISHED

    //CRAFTABLE ELDRITCH
    public static final Supplier <AbstractSpell> EBONY_ARMOR = registerSpell(new EbonyArmorSpell());
    public static final Supplier <AbstractSpell> PROTECTION_OF_THE_FALLEN = registerSpell(new ProtectionOfTheFallenSpell());
    public static final Supplier <AbstractSpell> SPIKES_OF_AGONY = registerSpell(new SpikesOfAgonySpell());
    public static final Supplier <AbstractSpell> TENTACLE_WHIP = registerSpell(new TentacleWhipSpell());
    public static final Supplier <AbstractSpell> PALE_FLAME = registerSpell(new PaleFlameSpell());

    //UNCRAFTABLE ICE
    public static final Supplier <AbstractSpell> GLACIAL_SCULPTING = registerSpell(new GlacialStatueSpell());




    public static void register(IEventBus eventBus)
    {
        SPELLS.register(eventBus);
    }
}
