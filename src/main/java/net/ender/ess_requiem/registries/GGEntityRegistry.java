package net.ender.ess_requiem.registries;

import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;

import net.ender.ess_requiem.entity.mobs.hopping_skull.HoppingSkullEntity;
import net.ender.ess_requiem.entity.mobs.skull_mass.SkullMassEntity;
import net.ender.ess_requiem.entity.spells.bone_spear.BoneSpearEntity;
import net.ender.ess_requiem.entity.spells.claw.ClawEntity;
import net.ender.ess_requiem.entity.spells.bone_claw.BoneClawEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class GGEntityRegistry {
    private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, EndersSpellsAndStuffRequiem.MOD_ID);

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }

    public static final DeferredHolder<EntityType<?>, EntityType<ClawEntity>> CLAW_ENTITY =
            ENTITIES.register("claw", () -> EntityType.Builder.<ClawEntity>of(ClawEntity::new, MobCategory.MISC)
                    .sized(4f, 1f)
                    .clientTrackingRange(64)
                    .build( ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "claw").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<BoneSpearEntity>> BONE_SPEAR =
            ENTITIES.register("bone_spear", () -> EntityType.Builder.<BoneSpearEntity>of(BoneSpearEntity::new, MobCategory.MISC)
                    .sized(2f, 1f)
                    .clientTrackingRange(64)
                    .build( ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "bone_spear").toString()));


    public static final DeferredHolder<EntityType<?>, EntityType<BoneClawEntity>> BONE_CLAW_ENTITY =
            ENTITIES.register("bone_claw", () -> EntityType.Builder.<BoneClawEntity>of(BoneClawEntity::new, MobCategory.MISC)
                    .sized(5f, 1f)
                    .clientTrackingRange(64)
                    .build( ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "bone_claw").toString()));


    public static final DeferredHolder<EntityType<?>, EntityType<HoppingSkullEntity>> HOPPING_SKULL =
            ENTITIES.register("hopping_skull", () -> EntityType.Builder.<HoppingSkullEntity>of(HoppingSkullEntity::new, MobCategory.CREATURE)
                    .sized(1f, 1f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "hopping_skull").toString())
            );

    public static final DeferredHolder<EntityType<?>, EntityType<SkullMassEntity>> SKULL_MASS =
            ENTITIES.register("skull_mass", () -> EntityType.Builder.<SkullMassEntity>of(SkullMassEntity::new, MobCategory.CREATURE)
                    .sized(1f, 1f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "skull_mass").toString())
            );

}


