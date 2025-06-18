package net.ender.grimoire_of_gore.setup;

import net.ender.grimoire_of_gore.GrimoireOfGore;
import net.ender.grimoire_of_gore.entity.spells.claw.ClawEntityRenderer;
import net.ender.grimoire_of_gore.entity.spells.bone_claw.BoneClawEntityRenderer;
import net.ender.grimoire_of_gore.registries.GGEntityRegistry;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = GrimoireOfGore.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    @SubscribeEvent
    public static void rendererRegister(EntityRenderersEvent.RegisterRenderers event) {

        event.registerEntityRenderer(GGEntityRegistry.CLAW_ENTITY.get(), ClawEntityRenderer::new);
        event.registerEntityRenderer(GGEntityRegistry.BONE_CLAW_ENTITY.get(), BoneClawEntityRenderer::new);
    }

}
