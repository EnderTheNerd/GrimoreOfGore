package net.ender.ess_requiem.spells.blood;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.events.SpellSummonEvent;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.acetheeldritchking.aces_spell_utils.spells.ASSpellAnimations;
import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;

import net.ender.ess_requiem.entity.mobs.hopping_skull.HoppingSkullEntity;

import net.ender.ess_requiem.entity.mobs.skull_mass.SkullMassEntity;
import net.ender.ess_requiem.registries.GGEffectRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;

import net.minecraft.sounds.SoundEvent;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.NeoForge;


import java.util.List;
import java.util.Optional;

@AutoSpellConfig
public class SummonSkullsSpell extends AbstractSpell {
    private final ResourceLocation spellId =  ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "summon_skulls");


    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
        (Component.translatable("ui.irons_spellbooks.summon_count", spellLevel))
        );
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.RARE)
            .setSchoolResource(SchoolRegistry.BLOOD_RESOURCE)
            .setMaxLevel(4)
            .setCooldownSeconds(100)
            .build();

    public SummonSkullsSpell() {
        this.manaCostPerLevel = 10;
        this.baseSpellPower = 5;
        this.spellPowerPerLevel = 1;
        this.castTime = 25;
        this.baseManaCost = 60;
    }

    @Override
    public CastType getCastType() {
        return CastType.LONG;
    }

    @Override
    public DefaultConfig getDefaultConfig() {
        return defaultConfig;
    }

    @Override
    public ResourceLocation getSpellResource() {
        return spellId;
    }

    @Override
    public Optional<SoundEvent> getCastStartSound() {
        return Optional.of(SoundRegistry.RAISE_DEAD_START.get());
    }

    @Override
    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        if (entity.hasEffect(GGEffectRegistry.UNDEAD_RAMPAGE)) {
            int summonTime = 100;

            SkullMassEntity skull = new SkullMassEntity(world, entity);
            skull.setPos(entity.position());
            skull.moveTo(entity.getEyePosition().add(new Vec3(Utils.getRandomScaled(2), 1, Utils.getRandomScaled(2))));
            var event = NeoForge.EVENT_BUS.post(new SpellSummonEvent<SkullMassEntity>(entity, skull, this.spellId, spellLevel));
            world.addFreshEntity(event.getCreature());
            skull.addEffect(new MobEffectInstance(GGEffectRegistry.CURSED_SKULL_TIMER, summonTime, 0, false, false, false));

            int effectAmplifier = spellLevel - 1;
            if (entity.hasEffect(GGEffectRegistry.CURSED_SKULL_TIMER))
                effectAmplifier += entity.getEffect(GGEffectRegistry.CURSED_SKULL_TIMER).getAmplifier() + 1;
            entity.addEffect(new MobEffectInstance(GGEffectRegistry.CURSED_SKULL_TIMER, summonTime, effectAmplifier, false, false, true));
            super.onCast(world, spellLevel, entity, castSource, playerMagicData);



        }
        else {
            int summonTime = 20 * 50 * 8;
            for (int i = -0; i < spellLevel; i++) {
                HoppingSkullEntity skull = new HoppingSkullEntity(world, entity);
                skull.moveTo(entity.getEyePosition().add(new Vec3(Utils.getRandomScaled(2), 1, Utils.getRandomScaled(2))));
                var event = NeoForge.EVENT_BUS.post(new SpellSummonEvent<HoppingSkullEntity>(entity, skull, this.spellId, spellLevel));
                world.addFreshEntity(event.getCreature());
                skull.addEffect(new MobEffectInstance(GGEffectRegistry.CURSED_SKULL_TIMER, summonTime, 0, false, false, false));


                super.onCast(world, spellLevel, entity, castSource, playerMagicData);
            }


        }

    }


    @Override
    public AnimationHolder getCastStartAnimation() {
        return ASSpellAnimations.ANIMATION_CONSTRUCT_SUMMON;
    }
    }





