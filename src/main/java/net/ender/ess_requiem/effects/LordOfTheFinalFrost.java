package net.ender.ess_requiem.effects;


import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class LordOfTheFinalFrost extends MobEffect {
    public LordOfTheFinalFrost(MobEffectCategory pCategory, int pColor) {
        super(MobEffectCategory.BENEFICIAL, 11131887);
        this.addAttributeModifier(AttributeRegistry.SUMMON_DAMAGE, ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "lord_of_frost"), .25,
                AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
        this.addAttributeModifier(AttributeRegistry.ICE_MAGIC_RESIST, ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "lord_of_frost"), .40,
                AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
        this.addAttributeModifier(AttributeRegistry.ICE_SPELL_POWER, ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "lord_of_frost"), .1,
                AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
    }
}
