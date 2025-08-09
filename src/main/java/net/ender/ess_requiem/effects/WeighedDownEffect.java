package net.ender.ess_requiem.effects;

import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;
import net.ender.ess_requiem.registries.GGAttributeRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class WeighedDownEffect extends MagicMobEffect {
    public WeighedDownEffect(MobEffectCategory pCategory, int pColor) {
        super(MobEffectCategory.HARMFUL, 5533306);
        this.addAttributeModifier(Attributes.GRAVITY, ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "thought"), +.5,
                AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "thought"), -.5,
                AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
    }
}
