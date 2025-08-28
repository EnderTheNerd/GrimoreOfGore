package net.ender.ess_requiem.item.sword_tier.BloodWeapons;

import io.redspace.ironsspellbooks.api.item.weapons.ExtendedSwordItem;
import io.redspace.ironsspellbooks.api.item.weapons.MagicSwordItem;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.ender.ess_requiem.item.GGSwordTier;
import net.ender.ess_requiem.registries.GGSpellRegistry;
import net.minecraft.world.item.Rarity;

public class ScytheOfRottenDreams extends MagicSwordItem {

    public ScytheOfRottenDreams() {
        super(GGSwordTier.SCYTHE_OF_ROTTEN_DREAMS, ItemPropertiesHelper.equipment().rarity(Rarity.EPIC).attributes(ExtendedSwordItem.createAttributes(GGSwordTier.SCYTHE_OF_ROTTEN_DREAMS)),
                SpellDataRegistryHolder.of(new SpellDataRegistryHolder(GGSpellRegistry.CORPSE_EXPLOSION, 1),
                        new SpellDataRegistryHolder(GGSpellRegistry.DECAYING_WILL, 1))
        );
    }
}
