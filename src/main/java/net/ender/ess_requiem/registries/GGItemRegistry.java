package net.ender.ess_requiem.registries;

import io.redspace.ironsspellbooks.item.curios.CurioBaseItem;
import net.acetheeldritchking.aces_spell_utils.utils.ASRarities;
import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;
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
import net.minecraft.world.item.Item;
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

    public static Collection<DeferredHolder<Item, ? extends Item>> getItems() {
        return ITEMS.getEntries();
    }




    //CRAFTING - She craft on my table till I item
    public static final DeferredItem<Item> FRAGMENT_OF_CLARITY = ITEMS.register("fragment_of_clarity",
            () -> new Item(new Item.Properties().rarity(ASRarities.COSMIC_RARITY_PROXY.getValue())));


    public static final DeferredItem<Item> COMPLETED_CLARITY = ITEMS.register("completed_clarity",
            () -> new Item(new Item.Properties().rarity(ASRarities.COSMIC_RARITY_PROXY.getValue())));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
