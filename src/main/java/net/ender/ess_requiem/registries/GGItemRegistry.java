package net.ender.ess_requiem.registries;

import io.redspace.ironsspellbooks.item.curios.CurioBaseItem;
import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;
import net.ender.ess_requiem.item.curio.NamelessRingCurio;
import net.ender.ess_requiem.item.sword_tier.BloodWeapons.RottenSickle;
import net.ender.ess_requiem.item.sword_tier.BloodWeapons.ScytheOfRottenDreams;
import net.ender.ess_requiem.item.sword_tier.BloodWeapons.WhisperingHarvester;
import net.ender.ess_requiem.item.sword_tier.IceWeapons.ScytheOfFrozenDreams;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Collection;

public class GGItemRegistry {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(EndersSpellsAndStuffRequiem.MOD_ID);

   //BLOOD
    public static final DeferredItem<Item> ROTTEN_SICKLE = ITEMS.register("rotten_sickle", RottenSickle::new);

    public static final DeferredItem<Item> WHISPERING_HARVESTER = ITEMS.register("whispering_harvester", WhisperingHarvester::new);

    public static final DeferredItem<Item> SCYTHE_OF_ROTTEN_DREAMS = ITEMS.register("scythe_of_rotten_dreams", ScytheOfRottenDreams::new);

    //ICE
    public static final DeferredItem<Item> SCYTHE_OF_FROZEN_DREAMS = ITEMS.register("scythe_of_frozen_dreams", ScytheOfFrozenDreams::new);

   //CURIO

    public static final DeferredItem<CurioBaseItem> NAMELESS_RING_CURIO = ITEMS.register("nameless_ring", NamelessRingCurio::new);

    public static Collection<DeferredHolder<Item, ? extends Item>> getItems() {
        return ITEMS.getEntries();
    }

    //CRAFTING -She craft on my table till I item

    public static final DeferredItem<Item> FRAGMENT_OF_CLARITY = ITEMS.register("fragment_of_clarity",
            () -> new Item(new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
