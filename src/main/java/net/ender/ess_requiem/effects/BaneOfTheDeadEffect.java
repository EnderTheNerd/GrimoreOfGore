package net.ender.ess_requiem.effects;

import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class BaneOfTheDeadEffect extends MobEffect {
    public BaneOfTheDeadEffect(MobEffectCategory category, int color) {
        super(MobEffectCategory.HARMFUL, 12851990);

        this.addAttributeModifier(Attributes.ATTACK_DAMAGE, ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "bane"), -8,
                AttributeModifier.Operation.ADD_VALUE);
        this.addAttributeModifier(Attributes.ATTACK_SPEED, ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "bane"), -.2,
                AttributeModifier.Operation.ADD_MULTIPLIED_BASE);

    }


}
