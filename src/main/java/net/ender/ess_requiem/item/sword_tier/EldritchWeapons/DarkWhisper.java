package net.ender.ess_requiem.item.sword_tier.EldritchWeapons;

import io.redspace.ironsspellbooks.api.item.weapons.ExtendedSwordItem;
import io.redspace.ironsspellbooks.api.item.weapons.MagicSwordItem;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.ender.ess_requiem.item.GGSwordTier;
import net.ender.ess_requiem.registries.GGSpellRegistry;
import net.minecraft.world.item.Rarity;

public class DarkWhisper extends MagicSwordItem {


    public DarkWhisper() {
        super(GGSwordTier.DARK_WHISPER, ItemPropertiesHelper.equipment().rarity(Rarity.COMMON).attributes(ExtendedSwordItem.createAttributes(GGSwordTier.DARK_WHISPER)),
                SpellDataRegistryHolder.of(new SpellDataRegistryHolder(GGSpellRegistry.EBONY_ARMOR, 1))
        );
    }
}
