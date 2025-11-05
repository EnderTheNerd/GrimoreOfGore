package net.ender.ess_requiem.spells.eldrtich.uncraftable;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.player.SpinAttackType;
import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;
import net.ender.ess_requiem.registries.GGEffectRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.RelativeMovement;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;

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
        }else{
            impulse.add(0, 0.25, 0);
        }
        entity.setDeltaMovement(new Vec3(
                Mth.lerp(.8f, entity.getDeltaMovement().x, impulse.x),
                Mth.lerp(.8f, entity.getDeltaMovement().y, impulse.y),
                Mth.lerp(.8f, entity.getDeltaMovement().z, impulse.z)
        ));
        entity.hurtMarked = true;

        entity.addEffect(new MobEffectInstance(GGEffectRegistry.CATAPHRACT_TACKLE, 10, getDamageSource(spellLevel, entity), false, false, false));
        entity.invulnerableTime = 20;
        playerMagicData.getSyncedData().setSpinAttackType(SpinAttackType.RIPTIDE);
        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }

    private int getDamageSource(int spellLevel, LivingEntity caster) {
        return (int) (15 + getSpellPower(spellLevel, caster));
    }
}
