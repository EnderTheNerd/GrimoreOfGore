package net.ender.ess_requiem.setup;

import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;

import net.ender.ess_requiem.entity.mobs.battle_standard.BattleStandardModel;
import net.ender.ess_requiem.entity.mobs.battle_standard.BattleStandardRenderer;
import net.ender.ess_requiem.entity.mobs.hopping_skull.HoppingSkullModel;
import net.ender.ess_requiem.entity.mobs.hopping_skull.HoppingSkullRenderer;

import net.ender.ess_requiem.entity.mobs.skull_mass.SkullMassModel;
import net.ender.ess_requiem.entity.mobs.skull_mass.SkullMassRenderer;
import net.ender.ess_requiem.entity.spells.black_flame.BlackFlameRenderer;
import net.ender.ess_requiem.entity.spells.bone_spear.BoneSpearModel;
import net.ender.ess_requiem.entity.spells.bone_spear.BoneSpearRenderer;
import net.ender.ess_requiem.entity.spells.claw.ClawEntityRenderer;
import net.ender.ess_requiem.entity.spells.bone_claw.BoneClawEntityRenderer;
import net.ender.ess_requiem.entity.spells.dismantle.DismantleProjectileRenderer;
import net.ender.ess_requiem.entity.spells.overwhelming_force.OverwhelmingForceRenderer;
import net.ender.ess_requiem.entity.spells.pale_flame.PaleFlameRenderer;
import net.ender.ess_requiem.entity.mobs.summoned_weapon.SoulmasterSwordModel;
import net.ender.ess_requiem.entity.mobs.summoned_weapon.SoulmasterSwordRenderer;
import net.ender.ess_requiem.entity.spells.spellblade_cut.SpellbladeCutRenderer;
import net.ender.ess_requiem.particle.*;
import net.ender.ess_requiem.registries.GGEntityRegistry;
import net.ender.ess_requiem.registries.GGParticleRegistry;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

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
        event.registerEntityRenderer(GGEntityRegistry.OVERWHELMING_FORCE.get(), OverwhelmingForceRenderer::new);
        event.registerEntityRenderer(GGEntityRegistry.SOULMASTER_SWORD.get(), context -> {return new SoulmasterSwordRenderer(context, new SoulmasterSwordModel());});
        event.registerEntityRenderer(GGEntityRegistry.HOPPING_SKULL.get(), context -> {return new HoppingSkullRenderer(context, new HoppingSkullModel());});
        event.registerEntityRenderer(GGEntityRegistry.BATTLE_STANDARD.get(), context -> {return new BattleStandardRenderer(context, new BattleStandardModel());});
        event.registerEntityRenderer(GGEntityRegistry.SKULL_MASS.get(), context -> {return new SkullMassRenderer(context, new SkullMassModel());});
        event.registerEntityRenderer(GGEntityRegistry.BONE_SPEAR.get(), context -> {return new BoneSpearRenderer(context, new BoneSpearModel());});
        event.registerEntityRenderer(GGEntityRegistry.DISMANTLE.get(),DismantleProjectileRenderer::new);
        event.registerEntityRenderer(GGEntityRegistry.SPELLBLADE_CUT.get(), SpellbladeCutRenderer::new);

        }



    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(GGParticleRegistry.CONFUSION_EYE_PARTICLE.get(), ConfusionEyeParticle.Provider::new);
        event.registerSpriteSet(GGParticleRegistry.WITHER_SKULL_SMALL.get(), WitherSkullSmallParticle.Provider::new);
        event.registerSpriteSet(GGParticleRegistry.DARK_SLASH.get(), DarkSlashParticle.Provider::new);
        event.registerSpriteSet(GGParticleRegistry.CATAPHRACT_SHARD_PARTICLE.get(), CataphractShardParticle.Provider::new);
        event.registerSpriteSet(GGParticleRegistry.CATAPHRACT_SPIRAL_PARTICLE.get(), CataphractSpiralParticle.Provider::new);
        event.registerSpriteSet(GGParticleRegistry.CATAPHRACT_STAR_ONE_PARTICLE.get(), CataphractStarOneParticle.Provider::new);
        event.registerSpriteSet(GGParticleRegistry.BIG_SLASH_LEFT.get(), BigSlashLeftParticle.Provider::new);
        event.registerSpriteSet(GGParticleRegistry.BIG_SLASH_RIGHT.get(), BigSlashRightParticle.Provider::new);

    }

}
