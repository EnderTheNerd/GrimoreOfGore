package net.ender.grimoire_of_gore.effects;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.ender.grimoire_of_gore.GrimoireOfGore;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class UndeadPactEffect extends MagicMobEffect {
    public UndeadPactEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
        this.addAttributeModifier(AttributeRegistry.HOLY_SPELL_POWER, ResourceLocation.fromNamespaceAndPath(GrimoireOfGore.MOD_ID, "undead_pact"), -.2,
                AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
        this.addAttributeModifier(AttributeRegistry.HOLY_MAGIC_RESIST, ResourceLocation.fromNamespaceAndPath(GrimoireOfGore.MOD_ID, "undead_pact"), -.15,
                AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
    }






}
