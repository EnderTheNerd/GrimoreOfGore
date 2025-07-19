package net.ender.ess_requiem.spells.blood;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;

import net.ender.ess_requiem.registries.GGEffectRegistry;
import net.ender.ess_requiem.registries.GGSoundRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Optional;

@AutoSpellConfig
public class PactOfTheDeadSpell extends AbstractSpell {


        private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "pact_of_the_dead");

        @Override
        public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
            return List.of(
                    Component.translatable("ui.irons_spellbooks.effect_length", Utils.timeFromTicks(getSpellPower(spellLevel, caster) * 16, 1)));

        }

    @Override
    public Optional<SoundEvent> getCastStartSound() {
        return Optional.of(GGSoundRegistry.PACT_SPELL_CAST.get());
    }

    @Override
    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.empty();
    }

        private final DefaultConfig defaultConfig = new DefaultConfig()
                .setMinRarity(SpellRarity.RARE)
                .setSchoolResource(SchoolRegistry.BLOOD_RESOURCE)
                .setMaxLevel(5)
                .setCooldownSeconds(210)
                .build();

        public PactOfTheDeadSpell() {
            this.manaCostPerLevel = 30;
            this.baseSpellPower = 18;
            this.spellPowerPerLevel = 4;
            this.castTime = 0;
            this.baseManaCost = 50;
        }

        @Override
        public CastType getCastType() {
            return CastType.INSTANT;
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
        public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {

            entity.addEffect(new MobEffectInstance(GGEffectRegistry.UNDEAD_PACT, (int) (getSpellPower(spellLevel, entity) * 16), spellLevel - 1, false, false, true));

            super.onCast(level, spellLevel, entity, castSource, playerMagicData);
        }



        @Override
        public AnimationHolder getCastStartAnimation() {
            return SpellAnimations.SELF_CAST_ANIMATION;
        }
    }

