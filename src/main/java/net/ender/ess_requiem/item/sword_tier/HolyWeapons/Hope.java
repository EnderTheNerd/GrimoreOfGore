package net.ender.ess_requiem.item.sword_tier.HolyWeapons;

import io.redspace.ironsspellbooks.api.item.weapons.ExtendedSwordItem;
import io.redspace.ironsspellbooks.api.item.weapons.MagicSwordItem;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.acetheeldritchking.aces_spell_utils.utils.ASRarities;
import net.ender.ess_requiem.item.GGSwordTier;
import net.ender.ess_requiem.registries.GGSpellRegistry;
import net.minecraft.world.item.Rarity;

public class Hope extends MagicSwordItem {

    public Hope() {
        super(GGSwordTier.HOPE, ItemPropertiesHelper.equipment().rarity(ASRarities.GLACIAL_RARITY_PROXY.getValue()).attributes(ExtendedSwordItem.createAttributes(GGSwordTier.HOPE)),
                SpellDataRegistryHolder.of(new SpellDataRegistryHolder(GGSpellRegistry.BASTION_OF_LIGHT, 1),
                        new SpellDataRegistryHolder(GGSpellRegistry.OVERWHELMING_LIGHT, 1))
        );
    }
}

