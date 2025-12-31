package net.ender.ess_requiem.registries;

import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;

import net.ender.ess_requiem.entity.mobs.battle_standard.BattleStandardEntity;
import net.ender.ess_requiem.entity.mobs.gilded_weapon.GildedWeaponEntity;
import net.ender.ess_requiem.entity.mobs.hopping_skull.HoppingSkullEntity;
import net.ender.ess_requiem.entity.mobs.skull_mass.SkullMassEntity;
import net.ender.ess_requiem.entity.spells.black_flame.BlackFlameLarge;
import net.ender.ess_requiem.entity.spells.black_flame.BlackFlameMedium;
import net.ender.ess_requiem.entity.spells.black_flame.BlackFlameNormal;
import net.ender.ess_requiem.entity.spells.bone_spear.BoneSpearEntity;
import net.ender.ess_requiem.entity.spells.claw.ClawEntity;
import net.ender.ess_requiem.entity.spells.bone_claw.BoneClawEntity;
import net.ender.ess_requiem.entity.spells.corpse_puddle.CorpsePuddle;
import net.ender.ess_requiem.entity.spells.dismantle.DismantleProjectile;
import net.ender.ess_requiem.entity.spells.eternal_battlefield.EternalBattlefield;
import net.ender.ess_requiem.entity.spells.overwhelming_force.OverwhelmingForce;
import net.ender.ess_requiem.entity.spells.pale_flame.PaleFlame;
import net.ender.ess_requiem.entity.mobs.summoned_weapon.SoulmasterSwordEntity;
import net.ender.ess_requiem.entity.spells.spellblade_cut.SpellbladeCutProjectile;
import net.ender.ess_requiem.entity.spells.wretch_breath.WretchBreath;
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

    public static final DeferredHolder<EntityType<?>, EntityType<WretchBreath>> WRETCH_BREATH_PROJECTILE =
            ENTITIES.register("wretch_breath", () -> EntityType.Builder.<WretchBreath>of(WretchBreath::new, MobCategory.MISC)
                    .sized(1f, 1f)
                    .clientTrackingRange(64)
                    .build( ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "wretch_breath").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<CorpsePuddle>> CORPSE_PUDDLE =
            ENTITIES.register("corpse_puddle", () -> EntityType.Builder.<CorpsePuddle>of(CorpsePuddle::new, MobCategory.MISC)
                    .sized(4f, 1f)
                    .clientTrackingRange(64)
                    .build( ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "corpse_puddle").toString()));

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

    public static final DeferredHolder<EntityType<?>, EntityType<SoulmasterSwordEntity>> SOULMASTER_SWORD =
            ENTITIES.register("soulmaster_sword", () -> EntityType.Builder.<SoulmasterSwordEntity>of(SoulmasterSwordEntity::new, MobCategory.CREATURE)
                    .sized(1f, 1f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "soulmaster_sword").toString())
            );

    public static final DeferredHolder<EntityType<?>, EntityType<GildedWeaponEntity>> GILDED_SWORD =
            ENTITIES.register("gilded_sword", () -> EntityType.Builder.<GildedWeaponEntity>of(GildedWeaponEntity::new, MobCategory.CREATURE)
                    .sized(1f, 1f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "gilded_sword").toString())
            );

    public static final DeferredHolder<EntityType<?>, EntityType<SkullMassEntity>> SKULL_MASS =
            ENTITIES.register("skull_mass", () -> EntityType.Builder.<SkullMassEntity>of(SkullMassEntity::new, MobCategory.CREATURE)
                    .sized(1f, 1f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "skull_mass").toString())
            );

    public static final DeferredHolder<EntityType<?>, EntityType<BattleStandardEntity>> BATTLE_STANDARD =
            ENTITIES.register("battle_standard", () -> EntityType.Builder.<BattleStandardEntity>of(BattleStandardEntity::new, MobCategory.CREATURE)
                    .sized(1f, 3f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "battle_standard").toString())

            );


    public static final DeferredHolder<EntityType<?>, EntityType<PaleFlame>> PALE_FLAME =
            ENTITIES.register("pale_flame", () -> EntityType.Builder.<PaleFlame>of(PaleFlame::new, MobCategory.MISC)
                    .sized(5f, 1f)
                    .clientTrackingRange(64)
                    .build( ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "pale_flame").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<BlackFlameNormal>> BLACK_FLAME_REGULAR =
            ENTITIES.register("black_flame_normal", () -> EntityType.Builder.<BlackFlameNormal>of(BlackFlameNormal::new, MobCategory.MISC)
                    .sized(5f, 1f)
                    .clientTrackingRange(64)
                    .build( ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "black_flame_normal").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<BlackFlameMedium>> BLACK_FLAME_MEDIUM =
            ENTITIES.register("black_flame_medium", () -> EntityType.Builder.<BlackFlameMedium>of(BlackFlameMedium::new, MobCategory.MISC)
                    .sized(7f, 1f)
                    .clientTrackingRange(64)
                    .build( ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "black_flame_medium").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<BlackFlameLarge>> BLACK_FLAME_LARGE =
            ENTITIES.register("black_flame_large", () -> EntityType.Builder.<BlackFlameLarge>of(BlackFlameLarge::new, MobCategory.MISC)
                    .sized(12f, 1f)
                    .clientTrackingRange(64)
                    .build( ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "black_flame_large").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<EternalBattlefield>> ETERNAL_BATTLEFIELD =
            ENTITIES.register("eternal_battlefield", () -> EntityType.Builder.<EternalBattlefield>of(EternalBattlefield::new, MobCategory.MISC)
                    .sized(4f, 1f)
                    .clientTrackingRange(64)
                    .build( ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "eternal_battlefield").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<OverwhelmingForce>> OVERWHELMING_FORCE=
            ENTITIES.register("overwhelm", () -> EntityType.Builder.<OverwhelmingForce>of(OverwhelmingForce::new, MobCategory.MISC)
                    .sized(8f, 1f)
                    .clientTrackingRange(64)
                    .build( ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "overwhelm").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<DismantleProjectile>> DISMANTLE =
            ENTITIES.register("dismantle", () -> EntityType.Builder.<DismantleProjectile>of(DismantleProjectile::new, MobCategory.MISC)
                    .sized(5f, 2f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "dismantle").toString())
            );


    public static final DeferredHolder<EntityType<?>, EntityType<SpellbladeCutProjectile>> SPELLBLADE_CUT =
            ENTITIES.register("spellblade_cut", () -> EntityType.Builder.<SpellbladeCutProjectile>of(SpellbladeCutProjectile::new, MobCategory.MISC)
                    .sized(1f, 1f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "spellblade_cut").toString())
            );

}


