package net.ender.ess_requiem.spells.spellblade;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.events.SpellSummonEvent;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.SummonManager;
import io.redspace.ironsspellbooks.capabilities.magic.SummonedEntitiesCastData;
import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;
import net.ender.ess_requiem.entity.mobs.summoned_weapon.SoulmasterSwordEntity;
import net.ender.ess_requiem.registries.GGSchoolRegistry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.NeoForge;

import java.util.List;

@AutoSpellConfig
public class SoulmasterSummonSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "soulmaster_summon");


    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of((Component.translatable("ui.irons_spellbooks.damage", getDamageText(spellLevel, caster))
        ));
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.LEGENDARY)
            .setSchoolResource(GGSchoolRegistry.BLADE_RESOURCE)
            .setMaxLevel(6)
            .setCooldownSeconds(20)
            .build();

    public SoulmasterSummonSpell() {
        this.manaCostPerLevel = 20;
        this.baseSpellPower = 8;
        this.spellPowerPerLevel = 6;
        this.castTime = 0;
        this.baseManaCost = 30;
    }

    @Override
    public CastType getCastType() {
        return CastType.INSTANT;
    }


    @Override
    public ResourceLocation getSpellResource() {
        return spellId;
    }

    @Override
    public DefaultConfig getDefaultConfig() {
        return defaultConfig;
    }


    @Override
    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
       SoulmasterSwordEntity weapon = new SoulmasterSwordEntity(world, entity);


        AttributeModifier damageModifier = new AttributeModifier(IronsSpellbooks.id("spell_power_damage_bonus"), getDamage(spellLevel, entity), AttributeModifier.Operation.ADD_VALUE);

        SummonedEntitiesCastData summonedEntitiesCastData = new SummonedEntitiesCastData();
        int summonTime = 20 * 60 * 10;
        weapon.moveTo(entity.position().add(0, 1.2, 0).add(Utils.getRandomVec3(1)));
        weapon.setHealth(weapon.getMaxHealth());
        weapon.getAttribute(Attributes.ATTACK_DAMAGE).addPermanentModifier(damageModifier);

        var creature = NeoForge.EVENT_BUS.post(new SpellSummonEvent<>(entity, weapon, this.spellId, spellLevel)).getCreature();
        world.addFreshEntity(creature);
        SummonManager.initSummon(entity, creature, summonTime, summonedEntitiesCastData);



        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }

    private float getDamage(int spellLevel, LivingEntity entity) {
        return getWeaponDamage(entity);
    }

    private float getWeaponDamage(LivingEntity entity) {
        if (entity == null) {
            return 0;
        }
        float weaponDamage = Utils.getWeaponDamage(entity);
        var weaponItem = entity.getWeaponItem();
        if (!weaponItem.isEmpty() && weaponItem.has(DataComponents.ENCHANTMENTS)) {
            weaponDamage += Utils.processEnchantment(entity.level(), Enchantments.SHARPNESS, EnchantmentEffectComponents.DAMAGE, weaponItem.get(DataComponents.ENCHANTMENTS));
        }
        return weaponDamage;
    }


    private String getDamageText(int spellLevel, LivingEntity entity) {
        if (entity != null) {
            float weaponDamage = Utils.getWeaponDamage(entity);
            String plus = "";
            if (weaponDamage > 0) {
                plus = String.format(" (+%s)", Utils.stringTruncation(weaponDamage, 1));
            }
            String damage = Utils.stringTruncation(getDamage(spellLevel, entity), 1);
            return damage + plus;
        }
        return "" + getSpellPower(spellLevel, entity);
    }


}






