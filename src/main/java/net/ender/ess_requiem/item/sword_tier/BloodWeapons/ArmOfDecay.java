package net.ender.ess_requiem.item.sword_tier.BloodWeapons;

import io.redspace.ironsspellbooks.api.item.weapons.ExtendedSwordItem;
import io.redspace.ironsspellbooks.api.item.weapons.MagicSwordItem;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.acetheeldritchking.aces_spell_utils.utils.ASRarities;
import net.ender.ess_requiem.item.GGSwordTier;
import net.ender.ess_requiem.registries.GGSpellRegistry;
import net.minecraft.world.item.Rarity;

public class ArmOfDecay extends MagicSwordItem {

    public ArmOfDecay() {
        super(GGSwordTier.ARM_OF_DECAY, ItemPropertiesHelper.equipment().rarity(ASRarities.COSMIC_RARITY_PROXY.getValue()).attributes(ExtendedSwordItem.createAttributes(GGSwordTier.ARM_OF_DECAY)),
                SpellDataRegistryHolder.of(
                        new SpellDataRegistryHolder(GGSpellRegistry.FINALITY_OF_DECAY, 1),
                        new SpellDataRegistryHolder(GGSpellRegistry.CORPSE_EXPLOSION, 1),
                        new SpellDataRegistryHolder(GGSpellRegistry.DECAYING_WILL, 1)));

    }
}
