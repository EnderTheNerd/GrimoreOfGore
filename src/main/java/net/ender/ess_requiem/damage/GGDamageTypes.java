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

    public static final ResourceKey<DamageType> MIND_MAGIC = register("mind_magic.json");

    public static void bootstrap(BootstrapContext<DamageType> context) {
        context.register(MIND_MAGIC, new DamageType(MIND_MAGIC.location().getPath(), DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER, 0f));

    }
}
