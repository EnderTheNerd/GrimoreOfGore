package net.ender.ess_requiem.registries;

import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.AutoSpellConfig;
import io.redspace.ironsspellbooks.spells.lightning.BallLightningSpell;
import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;

import net.ender.ess_requiem.spells.blood.*;
import net.ender.ess_requiem.spells.blood.uncraftable.CorpseExplosionSpell;
import net.ender.ess_requiem.spells.blood.uncraftable.DecayingWillSpell;
import net.ender.ess_requiem.spells.blood.uncraftable.TheFinalityOfDecaySpell;
import net.ender.ess_requiem.spells.cataphract_abilities.CataphractHeal;
import net.ender.ess_requiem.spells.cataphract_abilities.CataphractSlam;
import net.ender.ess_requiem.spells.eldrtich.*;
import net.ender.ess_requiem.spells.cataphract_abilities.CataphractTackle;
import net.ender.ess_requiem.spells.eldrtich.uncraftable.DamnationSpell;
import net.ender.ess_requiem.spells.eldrtich.uncraftable.EbonyCataphractSpell;
import net.ender.ess_requiem.spells.eldrtich.uncraftable.UnderTheCoverOfNightSpell;
import net.ender.ess_requiem.spells.holy.uncraftable.BastionOfLightSpell;
import net.ender.ess_requiem.spells.holy.uncraftable.OverwhelmingLightSpell;
import net.ender.ess_requiem.spells.ice.uncraftable.GlacialStatueSpell;
import net.ender.ess_requiem.spells.ice.uncraftable.LordOfTheFinalFrostSpell;
import net.ender.ess_requiem.spells.spellblade.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

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
    public static final Supplier <AbstractSpell> FINALITY_OF_DECAY = registerSpell(new TheFinalityOfDecaySpell());


    //CRAFTABLE ELDRITCH
    public static final Supplier <AbstractSpell> EBONY_ARMOR = registerSpell(new EbonyArmorSpell());
    public static final Supplier <AbstractSpell> PROTECTION_OF_THE_FALLEN = registerSpell(new ProtectionOfTheFallenSpell());
    public static final Supplier <AbstractSpell> SPIKES_OF_AGONY = registerSpell(new SpikesOfAgonySpell());
    public static final Supplier <AbstractSpell> TENTACLE_WHIP = registerSpell(new TentacleWhipSpell());
    public static final Supplier <AbstractSpell> PALE_FLAME = registerSpell(new PaleFlameSpell());
    public static final Supplier <AbstractSpell> CURSED_IMMORTALITY = registerSpell(new CursedImmortalitySpell());
    public static final Supplier <AbstractSpell> ETERNAL_BATTLEFIELD = registerSpell(new EternalBattlefieldSpell());
    public static final Supplier <AbstractSpell> TWILIGHT_ASSAULT = registerSpell(new TwilightAssaultSpell());

    //UNCRAFTABLE ELDRITCH
    public static final Supplier <AbstractSpell> EBONY_CATAPHRACT = registerSpell(new EbonyCataphractSpell());
    public static final Supplier <AbstractSpell> NIGHT_VEIL = registerSpell(new UnderTheCoverOfNightSpell());
    public static final Supplier <AbstractSpell> DAMNATION = registerSpell(new DamnationSpell());

    //CRAFTABLE SPELLBLADE
    public static final Supplier <AbstractSpell> SLASH = registerSpell(new SlashSpell());
    public static final Supplier <AbstractSpell> SLAM = registerSpell(new SlamSpell());
    public static final Supplier <AbstractSpell> QUICK_SLICE = registerSpell(new QuickSliceSpell());
    public static final Supplier <AbstractSpell> UPPERCUT = registerSpell(new UppercutSpell());
    public static final Supplier <AbstractSpell> PARRY = registerSpell(new SwordStanceSpell());

    //UNCRAFTABLE HOLY
    public static final Supplier<AbstractSpell> BASTION_OF_LIGHT = registerSpell(new BastionOfLightSpell());
    public static final Supplier<AbstractSpell> OVERWHELMING_LIGHT = registerSpell(new OverwhelmingLightSpell());

    //UNCRAFTABLE ICE
    public static final Supplier <AbstractSpell> GLACIAL_SCULPTING = registerSpell(new GlacialStatueSpell());
    public static final Supplier <AbstractSpell> LORD_OF_FROST = registerSpell(new LordOfTheFinalFrostSpell());

    //CREATIVE ONLY/ABILITIES
    public static final Supplier <AbstractSpell> CATAPHRACT_TACKLE = registerSpell(new CataphractTackle());
    public static final Supplier <AbstractSpell> CATAPHRACT_SLAM = registerSpell(new CataphractSlam());
    public static final Supplier <AbstractSpell> CATAPHRACT_HEAL = registerSpell(new CataphractHeal());


    public static void register(IEventBus eventBus)
    {
        SPELLS.register(eventBus);
    }
}
