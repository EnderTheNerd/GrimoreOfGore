package net.ender.ess_requiem.Util;

import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class GGTags {
    public static final TagKey<Item> BLADE_FOCUS = ItemTags.create(ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "blade_focus"));
    public static final TagKey<Item> DIVINE_FOCUS = ItemTags.create(ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "divine_focus"));
}
