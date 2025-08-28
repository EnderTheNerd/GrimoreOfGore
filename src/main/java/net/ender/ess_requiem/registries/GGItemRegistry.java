package net.ender.ess_requiem.registries;

import io.redspace.ironsspellbooks.item.curios.CurioBaseItem;
import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;
import net.ender.ess_requiem.item.curio.NamelessRingCurio;
import net.ender.ess_requiem.item.sword_tier.BloodWeapons.RottenSickle;
import net.ender.ess_requiem.item.sword_tier.BloodWeapons.WhisperingHarvester;
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

//CURIO

    public static final DeferredItem<CurioBaseItem> NAMELESS_RING_CURIO = ITEMS.register("nameless_ring", NamelessRingCurio::new);

    public static Collection<DeferredHolder<Item, ? extends Item>> getItems() {
        return ITEMS.getEntries();
    }


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
