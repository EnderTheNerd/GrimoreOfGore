package net.ender.ess_requiem.effects;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class BoilingManaEffect extends MagicMobEffect {

    public BoilingManaEffect(MobEffectCategory pCategory, int pColor) {
        super(MobEffectCategory.HARMFUL, 9833512);
        this.addAttributeModifier(AttributeRegistry.MANA_REGEN, ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "boiled_mana"), -.25,
                AttributeModifier.Operation.ADD_VALUE);
        this.addAttributeModifier(AttributeRegistry.BLOOD_MAGIC_RESIST, ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "boiled_mana"), -.15,
                AttributeModifier.Operation.ADD_VALUE);
    }

}
