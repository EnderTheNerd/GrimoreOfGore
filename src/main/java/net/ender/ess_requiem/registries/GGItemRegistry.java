package net.ender.ess_requiem.registries;

import io.redspace.ironsspellbooks.item.UpgradeOrbItem;
import io.redspace.ironsspellbooks.item.curios.CurioBaseItem;
import io.redspace.ironsspellbooks.registries.ComponentRegistry;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.acetheeldritchking.aces_spell_utils.utils.ASRarities;
import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;
import net.ender.ess_requiem.item.armor.BlademasterArmorItem;
import net.ender.ess_requiem.item.curio.CataphractRingCurio;
import net.ender.ess_requiem.item.curio.NamelessRingCurio;
import net.ender.ess_requiem.item.sword_tier.BloodWeapons.ArmOfDecay;
import net.ender.ess_requiem.item.sword_tier.BloodWeapons.RottenSickle;
import net.ender.ess_requiem.item.sword_tier.BloodWeapons.ScytheOfRottenDreams;
import net.ender.ess_requiem.item.sword_tier.BloodWeapons.WhisperingHarvester;
import net.ender.ess_requiem.item.sword_tier.EldritchWeapons.BrokenPromise;
import net.ender.ess_requiem.item.sword_tier.EldritchWeapons.DarkWhisper;
import net.ender.ess_requiem.item.sword_tier.EldritchWeapons.Inevitability;
import net.ender.ess_requiem.item.sword_tier.EldritchWeapons.MidnightEmbrace;
import net.ender.ess_requiem.item.sword_tier.HolyWeapons.Hope;
import net.ender.ess_requiem.item.sword_tier.IceWeapons.ScytheOfFrozenDreams;
import net.ender.ess_requiem.item.sword_tier.SpellbladeWeapons.Potential;
import net.ender.ess_requiem.item.sword_tier.SpellbladeWeapons.Practice;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.time.OffsetTime;
import java.util.Collection;

public class GGItemRegistry {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(EndersSpellsAndStuffRequiem.MOD_ID);

   //BLOOD
    public static final DeferredItem<Item> ROTTEN_SICKLE = ITEMS.register("rotten_sickle", RottenSickle::new);

    public static final DeferredItem<Item> WHISPERING_HARVESTER = ITEMS.register("whispering_harvester", WhisperingHarvester::new);

    public static final DeferredItem<Item> SCYTHE_OF_ROTTEN_DREAMS = ITEMS.register("scythe_of_rotten_dreams", ScytheOfRottenDreams::new);

    public static final DeferredItem<Item> ARM_OF_DECAY = ITEMS.register("arm_of_decay", ArmOfDecay::new);

    //ICE
    public static final DeferredItem<Item> SCYTHE_OF_FROZEN_DREAMS = ITEMS.register("scythe_of_frozen_dreams", ScytheOfFrozenDreams::new);

    //ELDRITCH
    public static final DeferredItem<Item> DARK_WHISPER = ITEMS.register("dark_whisper", DarkWhisper::new);

    public static final DeferredItem<Item> MIDNIGHT_EMBRACE = ITEMS.register("midnight_embrace", MidnightEmbrace::new);

    public static final DeferredItem<Item> BROKEN_PROMISE = ITEMS.register("broken_promise", BrokenPromise::new);

    public static final DeferredItem<Item> INEVITABILITY = ITEMS.register("inevitability", Inevitability::new);

    //HOLY
    public static final DeferredItem<Item> HOPE = ITEMS.register("hope", Hope::new);

   //CURIO
    public static final DeferredItem<CurioBaseItem> NAMELESS_RING_CURIO = ITEMS.register("nameless_ring", NamelessRingCurio::new);

    public static final DeferredItem<CurioBaseItem> CATAPHRACT_RING_CURIO = ITEMS.register("cataphract_ring", CataphractRingCurio::new);

    //SPELLBLADE
    public static final DeferredHolder<Item, Item> BLADEMASTER_HELMET = ITEMS.register("blademaster_helmet", () -> new BlademasterArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper.equipment(1).durability(ArmorItem.Type.HELMET.getDurability(37))));
    public static final DeferredHolder<Item, Item> BLADEMASTER_CHESTPLATE = ITEMS.register("blademaster_chestplate", () -> new BlademasterArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper.equipment(1).durability(ArmorItem.Type.CHESTPLATE.getDurability(37))));
    public static final DeferredHolder<Item, Item> BLADEMASTER_LEGGINGS = ITEMS.register("blademaster_leggings", () -> new BlademasterArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper.equipment(1).durability(ArmorItem.Type.LEGGINGS.getDurability(37))));
    public static final DeferredHolder<Item, Item> BLADEMASTER_BOOTS = ITEMS.register("blademaster_boots", () -> new BlademasterArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper.equipment(1).durability(ArmorItem.Type.BOOTS.getDurability(37))));

    public static final DeferredHolder<Item, Item> SPELLBLADE_UPGRADE_ORB = ITEMS.register("spellblade_upgrade_orb",
         () -> new UpgradeOrbItem(ItemPropertiesHelper.material().rarity(Rarity.UNCOMMON).component(ComponentRegistry.UPGRADE_ORB_TYPE, GGUpgradeOrbRegistry.SPELLBLADE_SPELL_POWER)));

    public static final DeferredItem<Item> POTENTIAL = ITEMS.register("potential", Potential::new);
    public static final DeferredItem<Item> PRACTICE = ITEMS.register("practice", Practice::new);





    //CRAFTING - She craft on my table till I item
    public static final DeferredItem<Item> FRAGMENT_OF_CLARITY = ITEMS.register("fragment_of_clarity",
            () -> new Item(new Item.Properties().rarity(ASRarities.COSMIC_RARITY_PROXY.getValue())));

    public static final DeferredItem<Item> SPELLBLADE_RUNE = ITEMS.register("spellblade_rune",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> COMPLETED_CLARITY = ITEMS.register("completed_clarity",
            () -> new Item(new Item.Properties().rarity(ASRarities.COSMIC_RARITY_PROXY.getValue())));


 public static Collection<DeferredHolder<Item, ? extends Item>> getItems() {
  return ITEMS.getEntries();
 }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
