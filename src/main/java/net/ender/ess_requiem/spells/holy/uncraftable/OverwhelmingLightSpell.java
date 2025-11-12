package net.ender.ess_requiem.spells.holy.uncraftable;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.magic.SpellSelectionManager;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.*;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.sunbeam.SunbeamEntity;
import io.redspace.ironsspellbooks.entity.spells.void_tentacle.VoidTentacle;
import io.redspace.ironsspellbooks.entity.spells.wisp.WispEntity;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.acetheeldritchking.aces_spell_utils.spells.ASSpellAnimations;
import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;
import net.ender.ess_requiem.entity.spells.pale_flame.PaleFlame;
import net.ender.ess_requiem.registries.GGEffectRegistry;
import net.ender.ess_requiem.registries.GGSoundRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

@AutoSpellConfig
public class OverwhelmingLightSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "overwhelming_light");



    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(getDamage(spellLevel, caster), 2))

        );
    }
    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.LEGENDARY)
            .setSchoolResource(SchoolRegistry.HOLY_RESOURCE)
            .setMaxLevel(1)
            .setCooldownSeconds(100)
            .build();

    public OverwhelmingLightSpell() {
        this.manaCostPerLevel = 0;
        this.baseSpellPower = 25;
        this.spellPowerPerLevel = 0;
        this.castTime = 0;
        this.baseManaCost = 250;
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
    public Optional<SoundEvent> getCastStartSound() {
        return Optional.of(SoundRegistry.SUNBEAM_WINDUP.get());
    }

    @Override
    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.of(SoundRegistry.HOLY_CAST.get());
    }

    @Override
    public int getRecastCount(int spellLevel, @Nullable LivingEntity entity) {
        return 9;
    }


    private static boolean isUnderSunTick(Level level, LivingEntity entity) {
        if (level.isDay() && !level.isClientSide) {
            return level.isDay();
        }
        else {
            return false;
        }

    }

    @Override
    public boolean checkPreCastConditions(Level level, int spellLevel, LivingEntity entity, MagicData playerMagicData) {
        boolean b = Utils.preCastTargetHelper(level, entity, playerMagicData, this, 80, 0.25F);
        if (b) {
            ICastData castData = playerMagicData.getAdditionalCastData() ;
            if (castData instanceof TargetEntityCastData targetEntityCastData )  {
                PlayerRecasts recasts = playerMagicData.getPlayerRecasts();
                if (recasts.hasRecastForSpell(this.getSpellId())) {
                    RecastInstance instance = recasts.getRecastInstance(this.getSpellId());
                    if (instance != null) {
                        ICastDataSerializable var10 = instance.getCastData();
                        if (var10 instanceof MultiTargetEntityCastData targetingData) {
                            if (targetingData.getTargets().contains(targetEntityCastData.getTargetUUID())) return false;
                        }
                    }
                }
            }
        }
        return b;
    }

    @Override
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        ICastData var7 = playerMagicData.getAdditionalCastData();
        if (entity instanceof ServerPlayer player && isUnderSunTick(entity.level(), (LivingEntity) entity)) {


            if (var7 instanceof TargetEntityCastData targetEntityCastData) {
                PlayerRecasts recasts = playerMagicData.getPlayerRecasts();
                if (!recasts.hasRecastForSpell(this.getSpellId())) {
                    recasts.addRecast(new RecastInstance(this.getSpellId(), spellLevel, this.getRecastCount(spellLevel, entity), 100, castSource, new MultiTargetEntityCastData(targetEntityCastData.getTarget((ServerLevel)level))), playerMagicData);
                }
                else {
                    RecastInstance instance = recasts.getRecastInstance(this.getSpellId());
                    if (instance != null) {
                        ICastDataSerializable var10 = instance.getCastData();
                        if (var10 instanceof MultiTargetEntityCastData targetingData) {
                            targetingData.addTarget(targetEntityCastData.getTargetUUID());
                        }
                    }
                }
            }

            super.onCast(level, spellLevel, entity, castSource, playerMagicData);
        }
        else {
            if (entity instanceof ServerPlayer serverPlayer) {
                serverPlayer.connection.send(new ClientboundSetActionBarTextPacket(Component.translatable("ui.ess_requiem.not_day", this.getDisplayName(serverPlayer)).withStyle(ChatFormatting.RED)));
            }}
        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }
    @Override
    public void onRecastFinished(ServerPlayer serverPlayer, RecastInstance recastInstance, RecastResult recastResult, ICastDataSerializable castDataSerializable) {
        super.onRecastFinished(serverPlayer, recastInstance, recastResult, castDataSerializable);
        Level level = serverPlayer.level();
        Vec3 origin = serverPlayer.getEyePosition().add(serverPlayer.getForward().normalize().scale(0.20000000298023224));
        level.playSound(null, origin.x, origin.y, origin.z, SoundRegistry.CLEANSE_CAST, SoundSource.PLAYERS, 2.0F, 1.0F);
        if (castDataSerializable instanceof MultiTargetEntityCastData targetingData) {
            targetingData.getTargets().forEach((uuid) -> {
                LivingEntity target = (LivingEntity) ((ServerLevel) serverPlayer.level()).getEntity(uuid);
                if (target != null) {
                    var playerMagicData = MagicData.getPlayerMagicData(serverPlayer);
                    var damageSource = this.getDamageSource(serverPlayer);
                    int spellLevel = spellPowerPerLevel;
                    boolean mirrored = playerMagicData.getCastingEquipmentSlot().equals(SpellSelectionManager.OFFHAND);

                    serverPlayer.addEffect(new MobEffectInstance(GGEffectRegistry.BASTION_OF_LIGHT, 800));

                    DamageSources.applyDamage(target, getDamage(spellLevel, serverPlayer), damageSource);

                    SunbeamEntity sunbeam = new SunbeamEntity(level);
                    sunbeam.setOwner(serverPlayer);
                    sunbeam.moveTo(target.position());
                    sunbeam.setDamage(getDamage(spellLevel, serverPlayer));
                    level.addFreshEntity(sunbeam);
                    level.playSound(null, sunbeam.blockPosition(), SoundRegistry.SUNBEAM_WINDUP.get(), SoundSource.NEUTRAL, 3.5f, 1);


                    WispEntity wispEntity = new WispEntity(level, serverPlayer, getSpellPower(spellLevel, serverPlayer));
                    wispEntity.setTarget(target);
                    wispEntity.setPos(Utils.getPositionFromEntityLookDirection(serverPlayer, 2).subtract(0, .2, 0));
                    level.addFreshEntity(wispEntity);
                }

            });

        }

    }
    @Override
    public ICastDataSerializable getEmptyCastData() {
        return new MultiTargetEntityCastData();
    }

    public float getDamage(int spellLevel, LivingEntity caster) {
        return getSpellPower(spellLevel, caster);
    }

    @Override
    public AnimationHolder getCastStartAnimation() {
        return ASSpellAnimations.ANIMATION_DEFENSIVE_SWORD_STANCE_START;
    }

    @Override
    public AnimationHolder getCastFinishAnimation() {
        return ASSpellAnimations.ANIMATION_DEFENSIVE_SWORD_STANCE_FINISH;
    }


    @Override
    public boolean canBeCraftedBy(Player player) {
        return false;
    }

    @Override
    public boolean allowCrafting() {
        return false;
    }

    @Override
    public boolean allowLooting() {
        return false;
    }

}
