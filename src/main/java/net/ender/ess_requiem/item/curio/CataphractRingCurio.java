package net.ender.ess_requiem.item.curio;

import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.compat.Curios;
import io.redspace.ironsspellbooks.item.curios.SimpleDescriptiveCurio;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.acetheeldritchking.aces_spell_utils.utils.ASRarities;
import net.ender.ess_requiem.registries.GGSpellRegistry;
import net.minecraft.world.item.Rarity;

import java.util.function.Supplier;

public class CataphractRingCurio extends ImbuableCurio{
    public CataphractRingCurio() {
        super(ItemPropertiesHelper.equipment().stacksTo(1).fireResistant().rarity(Rarity.EPIC), Curios.RING_SLOT,
                SpellDataRegistryHolder.of(new SpellDataRegistryHolder(GGSpellRegistry.EBONY_CATAPHRACT, 1)));
    }

    }

