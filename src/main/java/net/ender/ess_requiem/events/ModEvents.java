package net.ender.ess_requiem.events;

import io.redspace.ironsspellbooks.api.magic.MagicData;

import io.redspace.ironsspellbooks.entity.mobs.IMagicSummon;

import net.ender.ess_requiem.effects.MentalCrushingEffect;
import net.ender.ess_requiem.registries.GGEffectRegistry;

import net.minecraft.core.BlockPos;

import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;

@EventBusSubscriber
public class ModEvents {


    @SubscribeEvent
    public static void PactAttackDay(LivingDamageEvent.Post event) {
        var attacker = event.getSource().getDirectEntity();
        if (attacker instanceof ServerPlayer livingAttacker && livingAttacker.hasEffect(GGEffectRegistry.UNDEAD_PACT) && isUnderSunTick(attacker.level(), (LivingEntity) attacker)) {
            attacker.setRemainingFireTicks(100);
            livingAttacker.addEffect(new MobEffectInstance(GGEffectRegistry.BANE_OF_THE_DEAD, 60, 0));
            livingAttacker.addEffect(new MobEffectInstance(MobEffects.WITHER, 60, 0));
            livingAttacker.addEffect(new MobEffectInstance(MobEffects.HARM, 1, 0));


        }
    }




        @SubscribeEvent
    public static void PactAttackNight(LivingDamageEvent.Post event) {
        var attacker = event.getSource().getDirectEntity();
        if (attacker instanceof ServerPlayer livingAttacker && livingAttacker.hasEffect(GGEffectRegistry.UNDEAD_PACT) && isUnderMoonTick(attacker.level(), (LivingEntity) attacker)) {
            livingAttacker.addEffect(new MobEffectInstance(GGEffectRegistry.UNDEAD_RAMPAGE, 60, 0));
        }
    }





    private static boolean isUnderSunTick(Level level, LivingEntity entity) {
        if (level.isDay() && !level.isClientSide) {
            float f = entity.getLightLevelDependentMagicValue();
            BlockPos blockpos = BlockPos.containing(entity.getX(), entity.getEyeY(), entity.getZ());
            boolean flag = entity.isInWaterRainOrBubble() || entity.isInPowderSnow || entity.wasInPowderSnow;
            if (f > 0.5F && !flag && level.canSeeSky(blockpos)) {
                return true;
            }
        }

        return false;
    }

    private static boolean isUnderMoonTick(Level level, LivingEntity entity) {
        if (level.isNight() && !level.isClientSide) {
            if ( level.isNight()) {
                return true;
            }
        }

        return false;
    }


    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {

        if (event.getEntity() instanceof IMagicSummon summon) {
            if (summon.getSummoner() != null && summon.getSummoner() instanceof ServerPlayer player) {
                MagicData magicData = MagicData.getPlayerMagicData(player);

                if (player.hasEffect(GGEffectRegistry.REAPER) && magicData.getMana() > 100) {
                    magicData.setMana(magicData.getMana() + 50);
                }
                player.addEffect(new MobEffectInstance(MobEffects.HEAL, 1));


            }}}




}





