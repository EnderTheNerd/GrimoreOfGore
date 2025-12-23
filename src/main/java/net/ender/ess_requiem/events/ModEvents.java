package net.ender.ess_requiem.events;

import dev.shadowsoffire.apothic_attributes.api.ALObjects;
import io.redspace.ironsspellbooks.api.events.SpellOnCastEvent;
import io.redspace.ironsspellbooks.api.events.SpellPreCastEvent;
import io.redspace.ironsspellbooks.api.magic.MagicData;

import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.entity.mobs.IMagicSummon;

import io.redspace.ironsspellbooks.entity.mobs.frozen_humanoid.FrozenHumanoid;
import io.redspace.ironsspellbooks.player.SpinAttackType;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import io.redspace.ironsspellbooks.registries.ParticleRegistry;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import io.redspace.ironsspellbooks.spells.eldritch.SculkTentaclesSpell;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.ender.ess_requiem.item.sword_tier.BloodWeapons.ArmOfDecay;
import net.ender.ess_requiem.item.sword_tier.BloodWeapons.ScytheOfRottenDreams;
import net.ender.ess_requiem.item.sword_tier.EldritchWeapons.BrokenPromise;
import net.ender.ess_requiem.item.sword_tier.EldritchWeapons.MidnightEmbrace;
import net.ender.ess_requiem.registries.GGEffectRegistry;


import net.ender.ess_requiem.registries.GGItemRegistry;
import net.ender.ess_requiem.registries.GGSoundRegistry;
import net.ender.ess_requiem.registries.GGSpellRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;

import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileDeflection;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.ProjectileImpactEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

import java.util.Objects;

@EventBusSubscriber
public class ModEvents {


    @SubscribeEvent
    public static void CataphractWeaponTransformation(LivingDamageEvent.Pre event) {
        var sourceEntity = event.getSource().getEntity();
        if (sourceEntity instanceof ServerPlayer serverPlayer) {
            ItemStack mainhandItem = ((LivingEntity) serverPlayer).getMainHandItem();

            if (serverPlayer.hasEffect(GGEffectRegistry.EBONY_CATAPHRACT)) {
                if (mainhandItem.getItem() instanceof MidnightEmbrace) {
                    serverPlayer.getInventory().setItem(serverPlayer.getInventory().selected, new ItemStack(GGItemRegistry.BROKEN_PROMISE.get()));
                    serverPlayer.level().playSound(null, serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ(), GGSoundRegistry.MIDNIGHT_EMBRACE_GLASS_SHATTER, SoundSource.NEUTRAL, .8F, 1.3F);
                    serverPlayer.displayClientMessage(Component.literal(ChatFormatting.ITALIC + "Your sword shatters as if it was made of glass.")
                                .withStyle(s -> s.withColor(TextColor.fromRgb(10032177))), true);

                }

            }
        }

    }

    @SubscribeEvent
    public static void WeaponCombining(PlayerInteractEvent.RightClickItem event) {
        var entity = event.getEntity();
        if (entity instanceof ServerPlayer serverPlayer) {
            ItemStack mainhandItem = ((LivingEntity) serverPlayer).getMainHandItem();
            ItemStack offhandItem = ((LivingEntity) serverPlayer).getOffhandItem();
            if (mainhandItem.getItem() instanceof BrokenPromise && offhandItem.getItem() instanceof ScytheOfRottenDreams) {
                    serverPlayer.getInventory().setItem(serverPlayer.getInventory().selected, new ItemStack(GGItemRegistry.ARM_OF_DECAY.get()));
                    serverPlayer.getInventory().offhand.clear();

                }

            }


    }

    @SubscribeEvent
    public static void UncombiningWeapons(PlayerInteractEvent.RightClickItem event) {
        var entity = event.getEntity();
        if (entity instanceof ServerPlayer serverPlayer) {
            ItemStack mainhandItem = ((LivingEntity) serverPlayer).getMainHandItem();
            ItemStack offhandItem = ((LivingEntity) serverPlayer).getOffhandItem();
            if (mainhandItem.getItem() instanceof ArmOfDecay) {
                serverPlayer.getInventory().setItem(serverPlayer.getInventory().selected, new ItemStack(GGItemRegistry.SCYTHE_OF_ROTTEN_DREAMS.get()));
                serverPlayer.getInventory().setItem(serverPlayer.getInventory().getFreeSlot(), new ItemStack(GGItemRegistry.BROKEN_PROMISE.get()));

            }

        }


    }

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
    public static void VeilAttack(LivingDamageEvent.Pre event) {
        var attacker = event.getSource().getDirectEntity();
        if (attacker instanceof ServerPlayer livingAttacker && livingAttacker.hasEffect(GGEffectRegistry.NIGHT_VEIL) && isUnderMoonTick(attacker.level(), (LivingEntity) attacker) && livingAttacker.hasEffect(MobEffectRegistry.TRUE_INVISIBILITY)) {
            event.setNewDamage(event.getOriginalDamage() * 2.5F);
        }
    }



    @SubscribeEvent
    public static void PactAttackNight(LivingDamageEvent.Post event) {
        var attacker = event.getSource().getDirectEntity();
        if (attacker instanceof ServerPlayer livingAttacker && livingAttacker.hasEffect(GGEffectRegistry.UNDEAD_PACT) && isUnderMoonTick(attacker.level(), (LivingEntity) attacker)) {
            livingAttacker.addEffect(new MobEffectInstance(GGEffectRegistry.UNDEAD_RAMPAGE, 60, 0));
        }
    }

    @SubscribeEvent
    public static void Damned(LivingDamageEvent.Pre event) {
        var attacker = event.getSource().getDirectEntity();
        if (attacker instanceof ServerPlayer livingAttacker && livingAttacker.hasEffect(GGEffectRegistry.DAMNED)) {
            MagicData magicData = MagicData.getPlayerMagicData(livingAttacker);
            if (magicData.getMana() < 100) {
             event.setNewDamage(0);
            }
            magicData.setMana(magicData.getMana() - 25);
        }
    }



    @SubscribeEvent
    public static void BastionOfLight(LivingIncomingDamageEvent event) {
        var livingEntity = event.getEntity();
        if (livingEntity instanceof ServerPlayer player && player.hasEffect(GGEffectRegistry.BASTION_OF_LIGHT)) {
            MagicData magicData = MagicData.getPlayerMagicData(livingEntity);
            if (magicData.getMana() > 250) {
               event.setCanceled(true);
                livingEntity.level().playSound(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), SoundRegistry.CLEANSE_CAST, SoundSource.NEUTRAL, .8F, 1.3F);
                magicData.setMana(magicData.getMana() - 100);
            }
            else {
                event.setCanceled(false);
            }

        }
    }

    @SubscribeEvent
    public static void ParryMelee(LivingIncomingDamageEvent event) {
        var livingEntity = event.getEntity();
        if (livingEntity instanceof ServerPlayer player && player.hasEffect(GGEffectRegistry.PARRYING)) {
            event.setCanceled(true);
            MagicManager.spawnParticles(livingEntity.level(), ParticleHelper.FIERY_SPARKS, livingEntity.getX(), livingEntity.getY() + 1, livingEntity.getZ(), 30, 0, 0, 0, 1, false);
            livingEntity.level().playSound(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), GGSoundRegistry.PARRY, SoundSource.NEUTRAL, .8F, 1.3F);

        }

        }

    @SubscribeEvent
    public static void ParryRanged(ProjectileImpactEvent event) {
        var parried_projectile = event.getProjectile();
        if (event.getRayTraceResult() instanceof EntityHitResult result && result.getEntity() instanceof LivingEntity entity) {
             if (entity.hasEffect(GGEffectRegistry.PARRYING)){
                 event.setCanceled(true);
                 entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), GGSoundRegistry.PARRY, SoundSource.NEUTRAL, .8F, 1.3F);
                 event.getProjectile().deflect(ProjectileDeflection.AIM_DEFLECT, entity, entity, entity instanceof Player);
             }
        }
    }
    @SubscribeEvent
    public static void CursedRevive(LivingDeathEvent event) {
        if (event.getEntity() instanceof LivingEntity livingEntity) {
            if (livingEntity.hasEffect(GGEffectRegistry.CURSED_IMMORTALITY)){


                event.setCanceled(true);

                livingEntity.removeEffect(GGEffectRegistry.CURSED_IMMORTALITY);


                livingEntity.setHealth(10.0F);

                livingEntity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 80));
                livingEntity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 80));
                livingEntity.addEffect(new MobEffectInstance(MobEffectRegistry.ABYSSAL_SHROUD, 25));

                livingEntity.level().playSound(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), GGSoundRegistry.CURSED_REVIVE, SoundSource.NEUTRAL, .8F, 1.3F);


                if (event.getEntity() instanceof ServerPlayer player){
                    player.displayClientMessage(Component.literal(ChatFormatting.ITALIC + "You're not done until I say so.")
                     .withStyle(s -> s.withColor(TextColor.fromRgb(14806476))), true);
                    MagicData magicData = MagicData.getPlayerMagicData(player);

                    magicData.setMana(0);

                }

                }

            }}

    @SubscribeEvent
    public static void UndyneReference(LivingDeathEvent event) {
        if (event.getEntity() instanceof LivingEntity livingEntity) {
            if (livingEntity.hasEffect(GGEffectRegistry.SOUL_STRENGTH)){
                event.setCanceled(true);

                livingEntity.removeEffect(GGEffectRegistry.SOUL_STRENGTH);

                livingEntity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 100));
                livingEntity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 100));
                livingEntity.addEffect(new MobEffectInstance(GGEffectRegistry.FADING, 100));
                livingEntity.addEffect(new MobEffectInstance(GGEffectRegistry.UNDYING_DREAD, 2500));


                livingEntity.setHealth(livingEntity.getMaxHealth());


                livingEntity.level().playSound(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), GGSoundRegistry.SURVIVING, SoundSource.NEUTRAL, .8F, 1.3F);

                if (event.getEntity() instanceof ServerPlayer player){
                    player.displayClientMessage(Component.literal(ChatFormatting.ITALIC + "Not...Yet..I won't...die..here")
                            .withStyle(s -> s.withColor(TextColor.fromRgb(3020845))), true);
                }

            }

        }}

    @SubscribeEvent
    public static void UndyneReference2(MobEffectEvent.Expired event) {
        assert event.getEffectInstance() != null;
        if (event.getEffectInstance().is(GGEffectRegistry.UNDYING_DREAD) && event.getEntity() instanceof ServerPlayer player)
    {
        player.kill();

        player.displayClientMessage(Component.literal(ChatFormatting.ITALIC + "Even that...wasn't strong enough...")
                .withStyle(s -> s.withColor(TextColor.fromRgb(13383279))), true);

    }


    }


    @SubscribeEvent
    public static void FadingDisable(LivingIncomingDamageEvent event) {
        var livingEntity = event.getEntity();
        if (livingEntity instanceof ServerPlayer player && player.hasEffect(GGEffectRegistry.FADING)) {
            event.setCanceled(true);
        }

    }

    @SubscribeEvent
    public static void FadingDisable2(SpellPreCastEvent event) {
        var livingEntity = event.getEntity();
        if (livingEntity instanceof ServerPlayer player && player.hasEffect(GGEffectRegistry.FADING)) {
            event.setCanceled(true);
        }

    }

    @SubscribeEvent
    public static void FadingDisable3(AttackEntityEvent event) {
        var livingEntity = event.getEntity();
        if (livingEntity instanceof ServerPlayer player && player.hasEffect(GGEffectRegistry.FADING)) {
            event.setCanceled(true);
        }

    }

    @SubscribeEvent
    public static void FinalityOfDecay(MobEffectEvent.Expired event) {
        assert event.getEffectInstance() != null;
        if (event.getEffectInstance().is(GGEffectRegistry.FINALITY_OF_DECAY) && event.getEntity() instanceof LivingEntity livingEntity)
        {


                livingEntity.hurt(livingEntity.damageSources().magic(), 15);
                livingEntity.addEffect(new MobEffectInstance(MobEffects.WITHER, 300, 5));
                livingEntity.addEffect(new MobEffectInstance(GGEffectRegistry.MARK_OF_DECAY, 300, 0));

                livingEntity.playSound(GGSoundRegistry.CLOCK_TICKING.get(), 0.8f, 1.3F);

                if (livingEntity instanceof ServerPlayer serverPlayer){
                serverPlayer.displayClientMessage(Component.literal(ChatFormatting.ITALIC + "The Clock Strikes Nil")
                        .withStyle(s -> s.withColor(TextColor.fromRgb(15556694))), true);
                    serverPlayer.playSound(GGSoundRegistry.CLOCK_TICKING.get(), 0.8f, 1.3F);
                }
            }}

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
            return level.isNight();
        }
        else {
            return false;
        }

    }
    @SubscribeEvent
    public static void Reaper(LivingDeathEvent event) {

        if (event.getEntity() instanceof IMagicSummon summon) {
            if (summon.getSummoner() != null && summon.getSummoner() instanceof ServerPlayer player) {
                MagicData magicData = MagicData.getPlayerMagicData(player);

                if (player.hasEffect(GGEffectRegistry.REAPER) && magicData.getMana() > 100) {
                    magicData.setMana(magicData.getMana() + 150);
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
                if (summoner.hasEffect(GGEffectRegistry.LORD_OF_DECAY) && magicData.getMana() > 15) {
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

    @SubscribeEvent
    public static void FreezeStatueExplode(LivingDeathEvent event) {
        if (event.getEntity() instanceof IMagicSummon) {
            IMagicSummon summon = (IMagicSummon) event.getEntity();
            if (summon.getSummoner() != null && summon.getSummoner() instanceof ServerPlayer) {
                ServerPlayer summoner = (ServerPlayer) summon.getSummoner();
                MagicData magicData = MagicData.getPlayerMagicData(summoner);
                if (summoner.hasEffect(GGEffectRegistry.LORD_OF_FROST) && magicData.getMana() > 10) {
                    magicData.setMana(magicData.getMana() - 10);
                    summoner.displayClientMessage(Component.literal(ChatFormatting.ITALIC + "Summon condemned to frost")
                            .withStyle(s -> s.withColor(TextColor.fromRgb(11131887))), true);

                    FrozenHumanoid iceClone = new FrozenHumanoid(summoner.level(), (LivingEntity) summon);
                    iceClone.setSummoner(summoner);
                    iceClone.setShatterDamage(25);
                    iceClone.setDeathTimer(5);
                    summoner.level().addFreshEntity(iceClone);
                    iceClone.deathTime = 1000;
                    iceClone.playSound(SoundRegistry.FROSTBITE_FREEZE.get(), 2, Utils.random.nextInt(9, 11) * .1f);
                }
            }
        }
    }


    @SubscribeEvent
    public static void EbonyArmor(SpellPreCastEvent event) {
        var entity = event.getEntity();
        boolean hasEbonyEffect = entity.hasEffect(GGEffectRegistry.EBONY_ARMOR);
        if (entity instanceof ServerPlayer player && !player.level().isClientSide) {
            if (hasEbonyEffect) {
                event.setCanceled(true);
                int time = Objects.requireNonNull(player.getEffect(GGEffectRegistry.EBONY_ARMOR)).getDuration();
                String formattedTime = convertTicksToTime(time);
                player.displayClientMessage(Component.literal(ChatFormatting.BOLD + "Your body trembles, and your spells do not work for : " + formattedTime)
                        .withStyle(s -> s.withColor(TextColor.fromRgb(3289650))), true);
                player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.WITHER_HURT, SoundSource.PLAYERS, 0.3f, 1f);
            }
        }
    }

    @SubscribeEvent
    public static void EbonyCataphractTackle(SpellPreCastEvent event) {
        var entity = event.getEntity();
        boolean hasEbonyEffect = entity.hasEffect(GGEffectRegistry.EBONY_CATAPHRACT);
        if (entity instanceof ServerPlayer player && !player.level().isClientSide) {
            if (hasEbonyEffect) {
                event.setCanceled(true);
                GGSpellRegistry.CATAPHRACT_TACKLE.get().castSpell(event.getEntity().level(), 1, (ServerPlayer) player, CastSource.SPELLBOOK, true);
            }
        }
    }

    @SubscribeEvent
    public static void EbonyCataphractSlam(PlayerInteractEvent.RightClickBlock event) {
        var entity = event.getEntity();
        boolean hasEbonyEffect = entity.hasEffect(GGEffectRegistry.EBONY_CATAPHRACT);
        if (entity instanceof ServerPlayer player && !player.level().isClientSide) {
            if (hasEbonyEffect) {
                if (player.isCrouching()) {
                    GGSpellRegistry.CATAPHRACT_HEAL.get().castSpell(event.getEntity().level(), 1, (ServerPlayer) player, CastSource.SPELLBOOK, true);
                } else {
                    GGSpellRegistry.CATAPHRACT_SLAM.get().castSpell(event.getEntity().level(), 1, (ServerPlayer) player, CastSource.SPELLBOOK, true);
                }
            }
        }
    }
//THANKS ACE!!!
    public static String convertTicksToTime(int ticks) {
        // Convert ticks to seconds
        int totalSeconds = ticks / 20;

        // Calculate minutes and seconds
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;

        // Format the result as mm:ss
        return String.format("%02d:%02d", minutes, seconds);
    }




    @SubscribeEvent
    public static void AshesOfTheFallen(LivingDamageEvent.Pre event) {
        var attacked = event.getEntity();
        var attacker = event.getSource().getDirectEntity();

        MagicData magicData = MagicData.getPlayerMagicData(attacked);
        if ((attacked.hasEffect(GGEffectRegistry.PROTECTION_OF_ASHES) && magicData.getMana() > 75)) {

            if (attacker instanceof Projectile projectile) {
                var attacker2 = projectile.getOwner();
                assert attacker2 != null;

                magicData.setMana(magicData.getMana() - 75);
                attacker2.hurt(attacker2.damageSources().magic(), 3);
                attacked.level().playSound(null, attacked.getX(), attacked.getY(), attacked.getZ(),
                        SoundRegistry.KEEPER_SWORD_IMPACT, SoundSource.PLAYERS, 0.3f, 1f);
                event.setNewDamage(event.getOriginalDamage() * .5F);


                MagicManager.spawnParticles(attacked.level(), ParticleTypes.FALLING_OBSIDIAN_TEAR, attacker2.getX(), attacker2.getY() + .25f, attacker2.getZ(), 100, .03, .4, .03, .4, false);

            }

            else {  magicData.setMana(magicData.getMana() - 75);
                assert attacker != null;
                attacker.hurt(attacker.damageSources().magic(), 3);
                attacked.level().playSound(null, attacked.getX(), attacked.getY(), attacked.getZ(),
                        SoundRegistry.KEEPER_SWORD_IMPACT, SoundSource.PLAYERS, 0.3f, 1f);
                event.setNewDamage(event.getOriginalDamage() * .5F);


                MagicManager.spawnParticles(attacked.level(), ParticleTypes.FALLING_OBSIDIAN_TEAR, attacker.getX(), attacker.getY() + .25f, attacker.getZ(), 100, .03, .4, .03, .4, false);}



        }
    }

}





