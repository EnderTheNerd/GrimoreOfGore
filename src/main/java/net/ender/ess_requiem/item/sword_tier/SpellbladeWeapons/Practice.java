package net.ender.ess_requiem.item.sword_tier.SpellbladeWeapons;

import io.redspace.ironsspellbooks.api.item.weapons.ExtendedSwordItem;
import io.redspace.ironsspellbooks.api.item.weapons.MagicSwordItem;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.ender.ess_requiem.item.GGSwordTier;
import net.ender.ess_requiem.registries.GGSpellRegistry;
import net.minecraft.world.item.Rarity;

public class Practice extends MagicSwordItem {

    public Practice() {
        super(GGSwordTier.PRACTICE, ItemPropertiesHelper.equipment().rarity(Rarity.RARE).attributes(ExtendedSwordItem.createAttributes(GGSwordTier.PRACTICE)),
                SpellDataRegistryHolder.of(new SpellDataRegistryHolder(GGSpellRegistry.SLASH, 3),
                        new SpellDataRegistryHolder(GGSpellRegistry.SLAM, 2))
        );
    }
}
