package net.ender.ess_requiem.effects;

import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;
import net.ender.ess_requiem.registries.GGAttributeRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class ThoughtEffect extends MagicMobEffect {

    public ThoughtEffect(MobEffectCategory pCategory, int pColor) {
        super(MobEffectCategory.BENEFICIAL, 16773065);
        this.addAttributeModifier(GGAttributeRegistry.MIND_SPELL_POWER, ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "thought"), .1,
                AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
    }

}
