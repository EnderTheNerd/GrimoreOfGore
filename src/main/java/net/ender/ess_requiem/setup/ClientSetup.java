package net.ender.ess_requiem.setup;

import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;

import net.ender.ess_requiem.entity.mobs.hopping_skull.HoppingSkullModel;
import net.ender.ess_requiem.entity.mobs.hopping_skull.HoppingSkullRenderer;

import net.ender.ess_requiem.entity.mobs.skull_mass.SkullMassModel;
import net.ender.ess_requiem.entity.mobs.skull_mass.SkullMassRenderer;
import net.ender.ess_requiem.entity.spells.black_flame.BlackFlameRenderer;
import net.ender.ess_requiem.entity.spells.bone_spear.BoneSpearModel;
import net.ender.ess_requiem.entity.spells.bone_spear.BoneSpearRenderer;
import net.ender.ess_requiem.entity.spells.claw.ClawEntityRenderer;
import net.ender.ess_requiem.entity.spells.bone_claw.BoneClawEntityRenderer;
import net.ender.ess_requiem.entity.spells.pale_flame.PaleFlameRenderer;
import net.ender.ess_requiem.particle.*;
import net.ender.ess_requiem.registries.GGEntityRegistry;
import net.ender.ess_requiem.registries.GGParticleRegistry;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import software.bernie.geckolib.model.GeoModel;

import java.util.function.Supplier;

@EventBusSubscriber(modid = EndersSpellsAndStuffRequiem.MOD_ID, value = Dist.CLIENT)

public class ClientSetup {

    @SubscribeEvent
    public static void rendererRegister(EntityRenderersEvent.RegisterRenderers event ) {

        event.registerEntityRenderer(GGEntityRegistry.PALE_FLAME.get(), PaleFlameRenderer::new);
        event.registerEntityRenderer(GGEntityRegistry.BLACK_FLAME_REGULAR.get(), BlackFlameRenderer::new);
        event.registerEntityRenderer(GGEntityRegistry.BLACK_FLAME_MEDIUM.get(), BlackFlameRenderer::new);
        event.registerEntityRenderer(GGEntityRegistry.BLACK_FLAME_LARGE.get(), BlackFlameRenderer::new);
        event.registerEntityRenderer(GGEntityRegistry.CLAW_ENTITY.get(), ClawEntityRenderer::new);
        event.registerEntityRenderer(GGEntityRegistry.BONE_CLAW_ENTITY.get(), BoneClawEntityRenderer::new);
        event.registerEntityRenderer(GGEntityRegistry.WRETCH_BREATH_PROJECTILE.get(), NoopRenderer::new);
        event.registerEntityRenderer(GGEntityRegistry.CORPSE_PUDDLE.get(), NoopRenderer::new);
        event.registerEntityRenderer(GGEntityRegistry.ETERNAL_BATTLEFIELD.get(), NoopRenderer::new);
        event.registerEntityRenderer(GGEntityRegistry.HOPPING_SKULL.get(), context -> {return new HoppingSkullRenderer(context, new HoppingSkullModel());});
        event.registerEntityRenderer(GGEntityRegistry.SKULL_MASS.get(), context -> {return new SkullMassRenderer(context, new SkullMassModel());});
        event.registerEntityRenderer(GGEntityRegistry.BONE_SPEAR.get(), context -> {return new BoneSpearRenderer(context, new BoneSpearModel());});

    }

    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(GGParticleRegistry.CONFUSION_EYE_PARTICLE.get(), ConfusionEyeParticle.Provider::new);
        event.registerSpriteSet(GGParticleRegistry.WITHER_SKULL_SMALL.get(), WitherSkullSmallParticle.Provider::new);
        event.registerSpriteSet(GGParticleRegistry.DARK_SLASH.get(), DarkSlashParticle.Provider::new);
        event.registerSpriteSet(GGParticleRegistry.CATAPHRACT_SHARD_PARTICLE.get(), CataphractShardParticle.Provider::new);
        event.registerSpriteSet(GGParticleRegistry.CATAPHRACT_SPIRAL_PARTICLE.get(), CataphractSpiralParticle.Provider::new);
        event.registerSpriteSet(GGParticleRegistry.CATAPHRACT_STAR_ONE_PARTICLE.get(), CataphractStarOneParticle.Provider::new);



    }

}
