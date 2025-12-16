package net.ender.ess_requiem.effects;


import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class HonedEdgeEffect extends MobEffect {
    public HonedEdgeEffect(MobEffectCategory category, int color) {
        super(MobEffectCategory.BENEFICIAL, 13528209);
    }

    public HonedEdgeEffect() {
        super(MobEffectCategory.BENEFICIAL,13528209);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE, ResourceLocation.parse("honed_edge"),
                3, AttributeModifier.Operation.ADD_VALUE);
    }


}

