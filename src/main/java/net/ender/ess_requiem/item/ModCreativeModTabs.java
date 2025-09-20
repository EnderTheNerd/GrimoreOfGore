package net.ender.ess_requiem.item;

import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;
import net.ender.ess_requiem.registries.GGItemRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EndersSpellsAndStuffRequiem.MOD_ID);


    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TAB.register(eventBus);
    }

    public static final Supplier<CreativeModeTab> ROTTEN_ITEMS_TAB = CREATIVE_MODE_TAB.register("rotten_sickle_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(GGItemRegistry.ROTTEN_SICKLE.get()))
                    .title(Component.translatable("creativetab.ess_requiem.rotten_sickle"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(GGItemRegistry.FRAGMENT_OF_CLARITY);
                        output.accept(GGItemRegistry.ROTTEN_SICKLE);
                        output.accept(GGItemRegistry.WHISPERING_HARVESTER);
                        output.accept(GGItemRegistry.SCYTHE_OF_ROTTEN_DREAMS);
                        output.accept(GGItemRegistry.SCYTHE_OF_FROZEN_DREAMS);
                        output.accept(GGItemRegistry.NAMELESS_RING_CURIO);

                    })

                    .build()    );
}
