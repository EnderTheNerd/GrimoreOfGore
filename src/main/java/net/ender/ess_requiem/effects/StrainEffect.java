package net.ender.ess_requiem.effects;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.effect.MagicMobEffect;

import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class StrainEffect extends MagicMobEffect {
    public StrainEffect(MobEffectCategory pCategory, int pColor) {

        super(MobEffectCategory.NEUTRAL, 9833512);
        this.addAttributeModifier(Attributes.MAX_HEALTH, ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "strain"), -2,
                AttributeModifier.Operation.ADD_VALUE);
        this.addAttributeModifier(AttributeRegistry.BLOOD_SPELL_POWER, ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "strain"), .05,
                AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
    }


}
