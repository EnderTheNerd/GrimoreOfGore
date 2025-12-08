package net.ender.ess_requiem.spells.spellblade;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.TargetEntityCastData;
import io.redspace.ironsspellbooks.damage.DamageSources;
import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;
import net.ender.ess_requiem.registries.GGSchoolRegistry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;

import javax.annotation.Nullable;
import java.util.List;

@AutoSpellConfig
public class QuickSliceSpell extends AbstractSpell {
        private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "quick_slice");


        @Override
        public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
            return List.of(Component.translatable("ui.irons_spellbooks.damage", getDamageText(spellLevel, caster)),
                    Component.translatable("ui.irons_spellbooks.distance", Utils.stringTruncation(getRange(spellLevel, caster), 1))
            );
        }

        private final DefaultConfig defaultConfig = new DefaultConfig()
                .setMinRarity(SpellRarity.RARE)
                .setSchoolResource(GGSchoolRegistry.BLADE_RESOURCE)
                .setMaxLevel(5)
                .setCooldownSeconds(20)
                .build();


        public QuickSliceSpell() {
            this.manaCostPerLevel = 12;
            this.baseSpellPower = 4;
            this.spellPowerPerLevel = 2;
            this.castTime = 5;
            this.baseManaCost = 90;
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

            if (playerMagicData.getAdditionalCastData() instanceof TargetEntityCastData targetEntityCastData)
            {
                var targetEntity = targetEntityCastData.getTarget((ServerLevel) level);
                if (targetEntity != null)
                {
                    final Vec3 targetPosition = targetEntity.position();


                    entity.teleportTo(targetPosition.x, targetPosition.y, targetPosition.z);



                }



                float radius = 3f;
                float range = 2f;
                Vec3 smiteLocation = Utils.raycastForBlock(level, entity.getEyePosition(), entity.getEyePosition().add(entity.getForward().multiply(range, 0, range)), ClipContext.Fluid.NONE).getLocation();
                Vec3 particleLocation = level.clip(new ClipContext(smiteLocation, smiteLocation.add(0, -2, 0), ClipContext.Block.VISUAL, ClipContext.Fluid.NONE, CollisionContext.empty())).getLocation().add(0, 0.1, 0);

                var entities = level.getEntities(entity, AABB.ofSize(smiteLocation, radius, radius , radius ));
                var damageSource = this.getDamageSource(entity);
                {
                    //double distance = targetEntity.distanceToSqr(smiteLocation);
                    if (targetEntity.isAlive() && targetEntity.isPickable() && Utils.hasLineOfSight(level, smiteLocation.add(0, 1, 0), targetEntity.getBoundingBox().getCenter(), true)) {
                        if (DamageSources.applyDamage(targetEntity, getDamage(spellLevel, entity), damageSource)) {
                            EnchantmentHelper.doPostAttackEffects((ServerLevel) level, targetEntity, damageSource);
                        }


                    }
                }
            }

            super.onCast(level, spellLevel, entity, castSource, playerMagicData);
        }

        @Override
        public void onClientPreCast(Level level, int spellLevel, LivingEntity entity, InteractionHand hand, @Nullable MagicData playerMagicData) {
            super.onClientPreCast(level, spellLevel, entity, hand, playerMagicData);
            Vec3 forward = entity.getForward().normalize();
            for (int i = 0; i < 35; i++) {
                Vec3 motion = forward.scale(Utils.random.nextDouble() * .25f);
                level.addParticle(ParticleTypes.CRIT, entity.getRandomX(.4f), entity.getRandomY(), entity.getRandomZ(.4f), motion.x, motion.y, motion.z);
            }
        }

        @Override
        public boolean checkPreCastConditions(Level level, int spellLevel, LivingEntity entity, MagicData playerMagicData) {
            return Utils.preCastTargetHelper(level, entity, playerMagicData, this, 15 + spellLevel, .35f);
        }




        public float getRange(int spellLevel, LivingEntity caster) {
            return 15 + spellLevel ;
        }

        private float getDamage(int spellLevel, LivingEntity entity) {
            return getSpellPower(spellLevel, entity) * .1F + getWeaponDamage(entity);
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
