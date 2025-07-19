package net.ender.ess_requiem.setup;

import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;

import net.ender.ess_requiem.entity.mobs.hopping_skull.HoppingSkullModel;
import net.ender.ess_requiem.entity.mobs.hopping_skull.HoppingSkullRenderer;
import net.ender.ess_requiem.entity.mobs.skull_mass.SkullMassModel;
import net.ender.ess_requiem.entity.mobs.skull_mass.SkullMassRenderer;
import net.ender.ess_requiem.entity.spells.bone_spear.BoneSpearModel;
import net.ender.ess_requiem.entity.spells.bone_spear.BoneSpearRenderer;
import net.ender.ess_requiem.entity.spells.claw.ClawEntityRenderer;
import net.ender.ess_requiem.entity.spells.bone_claw.BoneClawEntityRenderer;
import net.ender.ess_requiem.registries.GGEntityRegistry;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = EndersSpellsAndStuffRequiem.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    @SubscribeEvent
    public static void rendererRegister(EntityRenderersEvent.RegisterRenderers event) {

        event.registerEntityRenderer(GGEntityRegistry.CLAW_ENTITY.get(), ClawEntityRenderer::new);
        event.registerEntityRenderer(GGEntityRegistry.BONE_CLAW_ENTITY.get(), BoneClawEntityRenderer::new);
        event.registerEntityRenderer(GGEntityRegistry.HOPPING_SKULL.get(), context -> {return new HoppingSkullRenderer(context, new HoppingSkullModel());});
        event.registerEntityRenderer(GGEntityRegistry.SKULL_MASS.get(), context -> {return new SkullMassRenderer(context, new SkullMassModel());});
        event.registerEntityRenderer(GGEntityRegistry.BONE_SPEAR.get(), context -> {return new BoneSpearRenderer(context, new BoneSpearModel());});
    }

}
