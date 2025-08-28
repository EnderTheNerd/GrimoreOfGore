package net.ender.ess_requiem.events;

import io.redspace.ironsspellbooks.api.magic.MagicData;

import io.redspace.ironsspellbooks.entity.mobs.IMagicSummon;

import net.ender.ess_requiem.registries.GGEffectRegistry;

import net.ender.ess_requiem.registries.GGSoundRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

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
    public static void MindRevive(LivingDeathEvent event) {
        if (event.getEntity() instanceof ServerPlayer livingEntity) {
            if (livingEntity.hasEffect(GGEffectRegistry.PRESERVED_STATE)){

                event.setCanceled(true);
                livingEntity.displayClientMessage(Component.literal(ChatFormatting.ITALIC + "The Mind Preserves")
                        .withStyle(s -> s.withColor(TextColor.fromRgb(15556694))), true);
                livingEntity.removeEffect(GGEffectRegistry.PRESERVED_STATE);
                livingEntity.setHealth(5.0F);
                livingEntity.playSound(GGSoundRegistry.CLOCK_TICKING.get(), 0.8f, 1.3F);
            }}}



    @SubscribeEvent
    public static void onPlayerLivingDamage1(LivingDamageEvent.Post event) {
        var attacker = event.getSource().getDirectEntity();

        if (attacker instanceof LivingEntity livingAttacker && livingAttacker.hasEffect(GGEffectRegistry.CONFUSED)) {
          ((LivingEntity) attacker).addEffect(new MobEffectInstance(MobEffects.HARM, 1, 0));
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


    @SubscribeEvent
    public static void DecayingMightRevive(LivingDeathEvent event) {
        if (event.getEntity() instanceof IMagicSummon) {
            IMagicSummon summon = (IMagicSummon) event.getEntity();
            if (summon.getSummoner() != null && summon.getSummoner() instanceof ServerPlayer) {
                ServerPlayer summoner = (ServerPlayer) summon.getSummoner();
                MagicData magicData = MagicData.getPlayerMagicData(summoner);
                if (summoner.hasEffect(GGEffectRegistry.DECAYING_MIGHT) && magicData.getMana() > 25) {
                    magicData.setMana(magicData.getMana() - 25);


                    summoner.displayClientMessage(Component.literal(ChatFormatting.ITALIC + "Summon Revived from Dust")
                            .withStyle(s -> s.withColor(TextColor.fromRgb(3289650))), true);
                    event.setCanceled(true);
                    event.getEntity().setHealth(event.getEntity().getMaxHealth());
                    if (event.getSource().getEntity() instanceof LivingEntity) {

                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void LordOfDecayRevive(LivingDeathEvent event) {
        if (event.getEntity() instanceof IMagicSummon) {
            IMagicSummon summon = (IMagicSummon) event.getEntity();
            if (summon.getSummoner() != null && summon.getSummoner() instanceof ServerPlayer) {
                ServerPlayer summoner = (ServerPlayer) summon.getSummoner();
                MagicData magicData = MagicData.getPlayerMagicData(summoner);
                if (summoner.hasEffect(GGEffectRegistry.DECAYING_MIGHT) && magicData.getMana() > 15) {
                    magicData.setMana(magicData.getMana() - 15);


                    summoner.displayClientMessage(Component.literal(ChatFormatting.ITALIC + "Summon Commanded to Live")
                            .withStyle(s -> s.withColor(TextColor.fromRgb(3289650))), true);
                    event.setCanceled(true);
                    event.getEntity().setHealth(event.getEntity().getMaxHealth());
                    if (event.getSource().getEntity() instanceof LivingEntity) {

                    }
                }
            }
        }
    }
}





