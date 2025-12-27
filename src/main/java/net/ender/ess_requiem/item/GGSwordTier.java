package net.ender.ess_requiem.item;

import dev.shadowsoffire.apothic_attributes.api.ALObjects;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import io.redspace.ironsspellbooks.item.weapons.ExtendedWeaponTier;
import io.redspace.ironsspellbooks.item.weapons.IronsWeaponTier;
import io.redspace.ironsspellbooks.registries.ItemRegistry;
import net.ender.ess_requiem.registries.GGAttributeRegistry;
import net.ender.ess_requiem.registries.GGItemRegistry;
import net.ender.ess_requiem.registries.GGSoundRegistry;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.checkerframework.checker.units.qual.A;

import java.util.function.Supplier;

public class GGSwordTier implements Tier, IronsWeaponTier {

//BLOOD
    public static ExtendedWeaponTier ROTTEN_SICKLE = new ExtendedWeaponTier(250, 4f, -2.2f, 15,
            BlockTags.INCORRECT_FOR_IRON_TOOL,
            () -> Ingredient.of(Items.BONE),
            new AttributeContainer(AttributeRegistry.BLOOD_SPELL_POWER, 0.05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));


    public static ExtendedWeaponTier WHISPERING_HARVESTER = new ExtendedWeaponTier(500, 6f, -2.4f, 20,
            BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
            () -> Ingredient.of(ItemRegistry.BLOOD_RUNE.get()),
            new AttributeContainer(AttributeRegistry.BLOOD_SPELL_POWER, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

    public static ExtendedWeaponTier SCYTHE_OF_ROTTEN_DREAMS = new ExtendedWeaponTier(2500, 12f, -2.2f, 35,
            BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
            () -> Ingredient.of(ItemRegistry.BLOODY_VELLUM.get()),
            new AttributeContainer(AttributeRegistry.BLOOD_SPELL_POWER, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));


    public static ExtendedWeaponTier ARM_OF_DECAY = new ExtendedWeaponTier(3000, 13f, -2.6f, 40,
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
            () -> Ingredient.of(GGItemRegistry.FRAGMENT_OF_CLARITY),
            new AttributeContainer(AttributeRegistry.BLOOD_SPELL_POWER, 0.20, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
            new AttributeContainer(AttributeRegistry.SUMMON_DAMAGE, 0.20, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));


   //ICE
   public static ExtendedWeaponTier SCYTHE_OF_FROZEN_DREAMS = new ExtendedWeaponTier(2500, 13f, -2.4f, 30,
       BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
           () -> Ingredient.of(ItemRegistry.FROZEN_BONE_SHARD.get()),
           new AttributeContainer(AttributeRegistry.ICE_SPELL_POWER, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));



   //ELDRITCH
   public static ExtendedWeaponTier DARK_WHISPER = new ExtendedWeaponTier(450, 4f, -2.1f, 15,
           BlockTags.INCORRECT_FOR_IRON_TOOL,
           () -> Ingredient.of(Items.POLISHED_DEEPSLATE),
           new AttributeContainer(AttributeRegistry.ELDRITCH_SPELL_POWER, 0.05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

   public static ExtendedWeaponTier MIDNIGHT_WHISPER = new ExtendedWeaponTier(630, 6f, -2.3f, 20,
            BlockTags.INCORRECT_FOR_IRON_TOOL,
            () -> Ingredient.of(Items.POLISHED_DEEPSLATE),
            new AttributeContainer(AttributeRegistry.ELDRITCH_SPELL_POWER, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

    public static ExtendedWeaponTier BROKEN_PROMISE = new ExtendedWeaponTier(2400, 11f, -2.6f, 15,
            BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
            () -> Ingredient.of(Items.POLISHED_DEEPSLATE),
            new AttributeContainer(AttributeRegistry.ELDRITCH_SPELL_POWER, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

    public static ExtendedWeaponTier INEVITABILITY = new ExtendedWeaponTier(3400, 13f, -2.5f, 15,
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
            () -> Ingredient.of(Items.POLISHED_DEEPSLATE),
            new AttributeContainer(AttributeRegistry.ELDRITCH_SPELL_POWER, 0.20, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
            new AttributeContainer(ALObjects.Attributes.ARMOR_PIERCE, .10, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

    //HOLY
    public static ExtendedWeaponTier HOPE = new ExtendedWeaponTier(2600, 10f, -2.7f, 30,
            BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
            () -> Ingredient.of(ItemRegistry.DIVINE_PEARL.get()),
            new AttributeContainer(AttributeRegistry.HOLY_SPELL_POWER, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));


    //SPELLBLADE
    public static ExtendedWeaponTier POTENTIAL = new ExtendedWeaponTier(250, 5f, -2.2f, 15,
            BlockTags.INCORRECT_FOR_IRON_TOOL,
            () -> Ingredient.of(Items.IRON_INGOT),
            new AttributeContainer(GGAttributeRegistry.BLADE_SPELL_POWER, 0.05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

    public static ExtendedWeaponTier PRACTICE = new ExtendedWeaponTier(540, 7f, -2.1f, 20,
            BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
        () -> Ingredient.of(GGItemRegistry.SPELLBLADE_RUNE),
            new AttributeContainer(GGAttributeRegistry.BLADE_SPELL_POWER, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

    public static ExtendedWeaponTier EXPERTISE = new ExtendedWeaponTier(2500, 12f, -1.9f, 35,
            BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
            () -> Ingredient.of(GGItemRegistry.SPELLBLADE_RUNE),
            new AttributeContainer(GGAttributeRegistry.BLADE_SPELL_POWER, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));


    public static ExtendedWeaponTier INTERTWINED_PEAK = new ExtendedWeaponTier(5000, 15f, -2f, 0,
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
            () -> Ingredient.of(GGItemRegistry.FRAGMENT_OF_CLARITY),
            new AttributeContainer(GGAttributeRegistry.BLADE_SPELL_POWER, 0.20, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
            new AttributeContainer(ALObjects.Attributes.PROT_PIERCE, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

    public static ExtendedWeaponTier SKYFALLS_CAUSE = new ExtendedWeaponTier(5000, 16f, -2.2f, 0,
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
            () -> Ingredient.of(GGItemRegistry.FRAGMENT_OF_CLARITY),
            new AttributeContainer(GGAttributeRegistry.BLADE_SPELL_POWER, 0.20, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
            new AttributeContainer(ALObjects.Attributes.CRIT_DAMAGE, 0.20, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

    public static ExtendedWeaponTier SWIFT_DEMISE = new ExtendedWeaponTier(5000, 14f, -1.8f, 0,
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
            () -> Ingredient.of(GGItemRegistry.FRAGMENT_OF_CLARITY),
            new AttributeContainer(GGAttributeRegistry.BLADE_SPELL_POWER, 0.20, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
            new AttributeContainer(ALObjects.Attributes.DODGE_CHANCE, 0.20, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));



    int uses;
    float damage;
    float speed;
    int enchantmentValue;
    TagKey<Block> incorrectBlocksForDrops;
    Supplier<Ingredient> repairIngredient;
    AttributeContainer[] attributes;

    public void ExtendedWeaponTier(int uses, float damage, float speed, int enchantmentValue, TagKey<Block> incorrectBlocksForDrops, Supplier<Ingredient> repairIngredient, AttributeContainer... attributes) {
        this.uses = uses;
        this.damage = damage;
        this.speed = speed;
        this.enchantmentValue = enchantmentValue;
        this.incorrectBlocksForDrops = incorrectBlocksForDrops;
        this.repairIngredient = repairIngredient;
        this.attributes = attributes;
    }

    @Override
    public int getUses() {
        return uses;
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return damage;
    }

    @Override
    public TagKey<Block> getIncorrectBlocksForDrops() {
        return incorrectBlocksForDrops;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient.get();
    }

    @Override
    public AttributeContainer[] getAdditionalAttributes() {
        return this.attributes;
    }
}
