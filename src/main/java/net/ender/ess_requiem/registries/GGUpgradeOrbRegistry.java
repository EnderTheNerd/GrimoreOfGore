package net.ender.ess_requiem.registries;

import io.redspace.ironsspellbooks.item.armor.UpgradeOrbType;
import io.redspace.ironsspellbooks.registries.UpgradeOrbTypeRegistry;
import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public class GGUpgradeOrbRegistry {
    public static ResourceKey<UpgradeOrbType> SPELLBLADE_SPELL_POWER = ResourceKey.create(UpgradeOrbTypeRegistry.UPGRADE_ORB_REGISTRY_KEY, ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "spellblade_spell_power"));
}
