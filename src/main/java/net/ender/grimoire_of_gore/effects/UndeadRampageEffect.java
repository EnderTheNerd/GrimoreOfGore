package net.ender.grimoire_of_gore.effects;

import dev.shadowsoffire.apothic_attributes.api.ALObjects;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.ender.grimoire_of_gore.GrimoireOfGore;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class UndeadRampageEffect extends MagicMobEffect {
    public UndeadRampageEffect(MobEffectCategory pCategory, int pColor) {
        super(MobEffectCategory.BENEFICIAL, 9833514);

        this.addAttributeModifier(ALObjects.Attributes.LIFE_STEAL, ResourceLocation.fromNamespaceAndPath(GrimoireOfGore.MOD_ID, "undead_rampage"), .12,
                AttributeModifier.Operation.ADD_VALUE);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE, ResourceLocation.fromNamespaceAndPath(GrimoireOfGore.MOD_ID, "undead_rampage"), 5,
                AttributeModifier.Operation.ADD_VALUE);
        this.addAttributeModifier(Attributes.ATTACK_SPEED, ResourceLocation.fromNamespaceAndPath(GrimoireOfGore.MOD_ID, "undead_rampage"), .15,
                AttributeModifier.Operation.ADD_MULTIPLIED_BASE);


    }

}
