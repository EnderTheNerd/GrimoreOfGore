package net.ender.ess_requiem.damage;

import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageType;

public class GGDamageTypes {

    public static ResourceKey<DamageType> register(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, name));
    }

    public static final ResourceKey<DamageType> BLADE_MAGIC = register("blade_magic.json");
    public static final ResourceKey<DamageType> DIVINE_MAGIC = register("divine_magic.json");

    public static void bootstrap(BootstrapContext<DamageType> context) {
        context.register(BLADE_MAGIC, new DamageType(BLADE_MAGIC.location().getPath(), DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER, 0f));
        context.register(DIVINE_MAGIC, new DamageType(DIVINE_MAGIC.location().getPath(), DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER, 0f));

    }




}
