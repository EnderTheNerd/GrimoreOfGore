package net.ender.ess_requiem.effects;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class MarkOfDecayEffect extends MagicMobEffect {
    public MarkOfDecayEffect(MobEffectCategory pCategory, int pColor) {
        super(MobEffectCategory.HARMFUL, 9833512);
        this.addAttributeModifier(AttributeRegistry.BLOOD_MAGIC_RESIST, ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "mark_of_decay"), -.3,
                AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
    }

}