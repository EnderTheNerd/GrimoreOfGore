package net.ender.ess_requiem.effects;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;


public class EbonyArmorEffect extends MobEffect {

    public EbonyArmorEffect(MobEffectCategory category, int color) {
        super(MobEffectCategory.NEUTRAL, 2367002);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "ebony_armor"),
                .10, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE, ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "ebony_armor"),
                .10, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
        this.addAttributeModifier(Attributes.ARMOR, ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "ebony_armor"),
                .10, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
        this.addAttributeModifier(Attributes.MAX_HEALTH,ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "ebony_armor"),
                5, AttributeModifier.Operation.ADD_VALUE);
        this.addAttributeModifier(Attributes.ATTACK_SPEED, ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "ebony_armor"),
                .15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
        this.addAttributeModifier(Attributes.ARMOR_TOUGHNESS, ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "ebony_armor"), .05,
                AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
        this.addAttributeModifier(AttributeRegistry.MANA_REGEN, ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "ebony_armor"), -1,
                AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
        this.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "ebony_armor"), .04,
                AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
        this.addAttributeModifier(AttributeRegistry.SPELL_RESIST, ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "ebony_armor"), .05,
                AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
    }
}
