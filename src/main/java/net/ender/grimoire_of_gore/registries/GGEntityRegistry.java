package net.ender.grimoire_of_gore.registries;

import net.ender.grimoire_of_gore.GrimoireOfGore;
import net.ender.grimoire_of_gore.entity.spells.claw.ClawEntity;
import net.ender.grimoire_of_gore.entity.spells.bone_claw.BoneClawEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class GGEntityRegistry {
    private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, GrimoireOfGore.MOD_ID);

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }

    public static final DeferredHolder<EntityType<?>, EntityType<ClawEntity>> CLAW_ENTITY =
            ENTITIES.register("claw", () -> EntityType.Builder.<ClawEntity>of(ClawEntity::new, MobCategory.MISC)
                    .sized(4f, 1f)
                    .clientTrackingRange(64)
                    .build( ResourceLocation.fromNamespaceAndPath(GrimoireOfGore.MOD_ID, "claw").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<BoneClawEntity>> BONE_CLAW_ENTITY =
            ENTITIES.register("bone_claw", () -> EntityType.Builder.<BoneClawEntity>of(BoneClawEntity::new, MobCategory.MISC)
                    .sized(5f, 1f)
                    .clientTrackingRange(64)
                    .build( ResourceLocation.fromNamespaceAndPath(GrimoireOfGore.MOD_ID, "bone_claw").toString()));
}


