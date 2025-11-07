package net.ender.ess_requiem.spells.cataphract_abilities;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.player.SpinAttackType;
import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;
import net.ender.ess_requiem.registries.GGEffectRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.RelativeMovement;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.List;
import java.util.Optional;

@AutoSpellConfig
public class CataphractTackle extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "cataphract_tackle");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.translatable("ui.irons_spellbooks.damage", getDamageSource(spellLevel, caster)));
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.LEGENDARY)
        .setSchoolResource(SchoolRegistry.EVOCATION_RESOURCE)
            .setMaxLevel(1)
            .setCooldownSeconds(10)
            .build();

    public CataphractTackle() {
        this.manaCostPerLevel = 5;
        this.baseSpellPower = 1;
        this.spellPowerPerLevel = 1;
        this.castTime = 0;
        this.baseManaCost = 30;
    }

    @Override
    public Optional<SoundEvent> getCastStartSound() {
        return Optional.of(SoundEvents.BREEZE_WHIRL);
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
    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        if (entity.hasEffect(GGEffectRegistry.ABILITY_COOLDOWN)) {
            if (entity instanceof ServerPlayer serverPlayer) {
                serverPlayer.connection.send(new ClientboundSetActionBarTextPacket(Component.translatable("ui.ess_requiem.on_cooldown", this.getDisplayName(serverPlayer)).withStyle(ChatFormatting.RED)));
            }
        }
        else {

            entity.hasImpulse = true;
            float multiplier = (15 + getSpellPower(spellLevel, entity)) / 20f;

            Vec3 forward = entity.getLookAngle();

            var upwardness = forward.dot(new Vec3(0, 1, 0));
            var remap = 1 - (Math.max(0, upwardness) * 0.6f);
            var impulse = forward.scale(3 * multiplier).multiply(1, remap, 1);

            if (entity.onGround()) {
                entity.move(MoverType.SELF, new Vec3(0.0, 1.1999999F, 0.0));
                if (entity instanceof ServerPlayer serverPlayer) {
                    serverPlayer.connection.send(new ClientboundPlayerPositionPacket(0.0, 1.1999999F, 0.0, 0, 0, RelativeMovement.ALL, serverPlayer.getId()));
                }
                impulse.add(0, 0.5, 0);
            } else {
                impulse.add(0, 0.25, 0);
            }
            entity.setDeltaMovement(new Vec3(
                    Mth.lerp(.8f, entity.getDeltaMovement().x, impulse.x),
                    Mth.lerp(.8f, entity.getDeltaMovement().y, impulse.y),
                    Mth.lerp(.8f, entity.getDeltaMovement().z, impulse.z)
            ));
            entity.hurtMarked = true;

            entity.addEffect(new MobEffectInstance((Holder<MobEffect>) GGEffectRegistry.CATAPHRACT_TACKLE, 10, (int) getDamageSource(spellLevel, entity), false, false, false));
            entity.invulnerableTime = 20;
            playerMagicData.getSyncedData().setSpinAttackType(SpinAttackType.RIPTIDE);
            entity.addEffect(new MobEffectInstance(GGEffectRegistry.ABILITY_COOLDOWN, 80));
            super.onCast(world, spellLevel, entity, castSource, playerMagicData);
        }
    }
    private float getDamageSource(int spellLevel, LivingEntity entity)
    {
        float damage = getDamageForAttribute(this, entity, spellLevel, AttributeRegistry.ELDRITCH_SPELL_POWER, 3);
        return damage;
    }

    public static float getDamageForAttribute(CataphractTackle spell, LivingEntity entity, int spellLevel, DeferredHolder<Attribute, Attribute> attr1, float modifier)
    {
        double attrValue1;
        if(entity != null) {
            attrValue1 = entity.getAttributeValue(AttributeRegistry.ELDRITCH_SPELL_POWER);
        }else{
            attrValue1 = 1;
        }

        float damage = (float) (modifier * (spell.getSpellPower(spellLevel, entity) + attrValue1));

        return damage;
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
