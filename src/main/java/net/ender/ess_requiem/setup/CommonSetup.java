package net.ender.ess_requiem.setup;


import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;

import net.ender.ess_requiem.entity.mobs.hopping_skull.HoppingSkullEntity;
import net.ender.ess_requiem.entity.mobs.skull_mass.SkullMassEntity;
import net.ender.ess_requiem.registries.GGEntityRegistry;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = EndersSpellsAndStuffRequiem.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class CommonSetup {
    @SubscribeEvent
    public static void onAttributeCreate(EntityAttributeCreationEvent event) {

        // You can technically do KeeperEntity.prepareAttributes().build() to get the attributes of the OG entity as an option
        event.put(GGEntityRegistry.HOPPING_SKULL.get(), HoppingSkullEntity.createAttributes().build());
        event.put(GGEntityRegistry.SKULL_MASS.get(), SkullMassEntity.createAttributes().build());
    }
}
