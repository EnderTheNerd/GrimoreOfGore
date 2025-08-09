package net.ender.ess_requiem.spells.blood;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.events.SpellSummonEvent;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.RecastInstance;
import io.redspace.ironsspellbooks.capabilities.magic.RecastResult;
import io.redspace.ironsspellbooks.capabilities.magic.SummonManager;
import io.redspace.ironsspellbooks.capabilities.magic.SummonedEntitiesCastData;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.acetheeldritchking.aces_spell_utils.spells.ASSpellAnimations;
import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;

import net.ender.ess_requiem.entity.mobs.hopping_skull.HoppingSkullEntity;

import net.ender.ess_requiem.entity.mobs.skull_mass.SkullMassEntity;
import net.ender.ess_requiem.registries.GGEffectRegistry;
import net.ender.ess_requiem.registries.GGSoundRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.NeoForge;


import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

@AutoSpellConfig
public class SummonSkullsSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "summon_skulls");


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
    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.of(SoundRegistry.RAISE_DEAD_START.get());
    }



    @Override
    public int getRecastCount(int spellLevel, @Nullable LivingEntity entity) {
        return 2;
    }

    @Override
    public void onRecastFinished(ServerPlayer serverPlayer, RecastInstance recastInstance, RecastResult recastResult, ICastDataSerializable castDataSerializable) {
        if (SummonManager.recastFinishedHelper(serverPlayer, recastInstance, recastResult, castDataSerializable)) {
            super.onRecastFinished(serverPlayer, recastInstance, recastResult, castDataSerializable);
        }
    }

    @Override
    public ICastDataSerializable getEmptyCastData() {
        return new SummonedEntitiesCastData();
    }

    public int getSummonCount(int spellLevel, LivingEntity caster) {
        return spellLevel;
    }

    @Override
    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        if (entity.hasEffect(GGEffectRegistry.UNDEAD_RAMPAGE)) {

        var recasts = playerMagicData.getPlayerRecasts();
        if (!recasts.hasRecastForSpell(this)) {
            SummonedEntitiesCastData summonedEntitiesCastData = new SummonedEntitiesCastData();
            int summonTime = 20 * 60 * 10;
            int count = 1;
            for (int i = 0; i < count; i++) {
                SkullMassEntity skull = new SkullMassEntity(world, entity);
                skull.moveTo(entity.getEyePosition().add(new Vec3(Utils.getRandomScaled(2), 1, Utils.getRandomScaled(2))));
                skull.finalizeSpawn((ServerLevel) world, world.getCurrentDifficultyAt(skull.getOnPos()), MobSpawnType.MOB_SUMMONED, null);
                var creature = NeoForge.EVENT_BUS.post(new SpellSummonEvent<>(entity, skull, this.spellId, spellLevel)).getCreature();
                world.addFreshEntity(creature);
                SummonManager.initSummon(entity, creature, summonTime, summonedEntitiesCastData);
            }
            RecastInstance recastInstance = new RecastInstance(this.getSpellId(), spellLevel, getRecastCount(spellLevel, entity), summonTime, castSource, summonedEntitiesCastData);
            recasts.addRecast(recastInstance, playerMagicData);
        }
        super.onCast(world, spellLevel, entity, castSource, playerMagicData);



    }

        else {
            var recasts = playerMagicData.getPlayerRecasts();
            if (!recasts.hasRecastForSpell(this)) {
                SummonedEntitiesCastData summonedEntitiesCastData = new SummonedEntitiesCastData();
                int summonTime = 20 * 60 * 10;
                int count = getSummonCount(spellLevel, entity);
                for (int i = 0; i < count; i++) {
                    HoppingSkullEntity skull = new HoppingSkullEntity(world, entity);
                    skull.moveTo(entity.getEyePosition().add(new Vec3(Utils.getRandomScaled(2), 1, Utils.getRandomScaled(2))));
                    skull.finalizeSpawn((ServerLevel) world, world.getCurrentDifficultyAt(skull.getOnPos()), MobSpawnType.MOB_SUMMONED, null);
                    var creature = NeoForge.EVENT_BUS.post(new SpellSummonEvent<>(entity, skull, this.spellId, spellLevel)).getCreature();
                    world.addFreshEntity(creature);
                    SummonManager.initSummon(entity, creature, summonTime, summonedEntitiesCastData);
                }
                RecastInstance recastInstance = new RecastInstance(this.getSpellId(), spellLevel, getRecastCount(spellLevel, entity), summonTime, castSource, summonedEntitiesCastData);
                recasts.addRecast(recastInstance, playerMagicData);
            }
            super.onCast(world, spellLevel, entity, castSource, playerMagicData);


        }

    }

}







