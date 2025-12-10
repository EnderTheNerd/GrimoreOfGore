package net.ender.ess_requiem.entity.spells.summoned_weapon;

import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.entity.mobs.goals.SpellBarrageGoal;
import io.redspace.ironsspellbooks.entity.mobs.goals.WizardAttackGoal;
import io.redspace.ironsspellbooks.entity.mobs.goals.melee.AttackAnimationData;
import io.redspace.ironsspellbooks.entity.mobs.goals.melee.AttackKeyframe;
import io.redspace.ironsspellbooks.entity.mobs.wizards.GenericAnimatedWarlockAttackGoal;
import io.redspace.ironsspellbooks.entity.spells.summoned_weapons.SummonedWeaponEntity;
import net.ender.ess_requiem.registries.GGEntityRegistry;
import net.ender.ess_requiem.registries.GGSpellRegistry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class SoulmasterSwordEntity extends SummonedWeaponEntity {


    public static AttributeSupplier.Builder prepareAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(Attributes.ATTACK_KNOCKBACK, 1.0)
                .add(Attributes.ATTACK_DAMAGE, 5.0)
                .add(Attributes.MAX_HEALTH, 25.0)
                .add(Attributes.FOLLOW_RANGE, 40.0)
                .add(Attributes.FLYING_SPEED, 1.5)
                .add(Attributes.ENTITY_INTERACTION_RANGE, 4)
                .add(Attributes.MOVEMENT_SPEED, .5);

    }

    public SoulmasterSwordEntity(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }


    public SoulmasterSwordEntity(Level level, LivingEntity owner) {
        this(GGEntityRegistry.SOULMASTER_SWORD.get(), level);
        setSummoner(owner);
    }

    @Override
    public GenericAnimatedWarlockAttackGoal<SoulmasterSwordEntity> makeAttackGoal() {
        return new GenericAnimatedWarlockAttackGoal<>(this, 1.5, 0, 20)
                .setMoveset(List.of(
                                new AttackAnimationData(36, "summoned_sword_basic_swing", 20),
                                new AttackAnimationData(52, "summoned_sword_basic_dual_swing", 20, 35),
                                new AttackAnimationData(40, "summoned_sword_multistab", 20, 26, 32),
                                AttackAnimationData.builder("summoned_sword_pommel_strike")
                                        .length(24).attacks(new AttackKeyframe(12, new Vec3(0, 0, .45f), new Vec3(0, 0, 1))).build(),
                                AttackAnimationData.builder("summoned_sword_basic_downswing")
                                        .length(45).attacks(new AttackKeyframe(25, new Vec3(0, -0.2, .15f), new Vec3(0, 0, 1))).area(.7f).build()
                        )

                );


    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new SpellBarrageGoal(this, GGSpellRegistry.SLASH.get(), 3, 5, 100, 250, 1));
        this.goalSelector.addGoal(3, new WizardAttackGoal(this, 1.25f, 50, 75)
                .setSpells(
                        List.of(SpellRegistry.FLAMING_STRIKE_SPELL.get()),
                        List.of(GGSpellRegistry.SLAM.get()),
                        List.of(GGSpellRegistry.UPPERCUT.get()),
                        List.of()
                ).setSingleUseSpell(SpellRegistry.EARTHQUAKE_SPELL.get(), 80, 400, 6, 6));

    }

}
