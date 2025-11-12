package net.ender.ess_requiem.item.sword_tier.EldritchWeapons;

import io.redspace.ironsspellbooks.api.item.weapons.ExtendedSwordItem;
import io.redspace.ironsspellbooks.api.item.weapons.MagicSwordItem;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.acetheeldritchking.aces_spell_utils.utils.ASRarities;
import net.ender.ess_requiem.item.GGSwordTier;
import net.ender.ess_requiem.registries.GGSpellRegistry;
import net.minecraft.world.item.Rarity;

public class Inevitability extends MagicSwordItem {

    public Inevitability() {
        super(GGSwordTier.INEVITABILITY, ItemPropertiesHelper.equipment().rarity(ASRarities.COSMIC_RARITY_PROXY.getValue()).attributes(ExtendedSwordItem.createAttributes(GGSwordTier.INEVITABILITY)),
                SpellDataRegistryHolder.of(
                        new SpellDataRegistryHolder(GGSpellRegistry.EBONY_CATAPHRACT, 1),
                        new SpellDataRegistryHolder(GGSpellRegistry.NIGHT_VEIL, 1),
                        new SpellDataRegistryHolder(GGSpellRegistry.DAMNATION, 1)
                        )
        );
    }
}
