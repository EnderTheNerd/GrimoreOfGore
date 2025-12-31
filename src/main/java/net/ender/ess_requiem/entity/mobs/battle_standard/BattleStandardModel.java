package net.ender.ess_requiem.entity.mobs.battle_standard;

import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animation.AnimationState;

import software.bernie.geckolib.model.GeoModel;

public class BattleStandardModel extends GeoModel<BattleStandardEntity> {

    @Override
    public ResourceLocation getModelResource(BattleStandardEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "geo/battle_standard.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource (BattleStandardEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "textures/entity/standard.png");
    }

    @Override
    public void setCustomAnimations(BattleStandardEntity animatable, long instanceId, AnimationState<BattleStandardEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);
    }

    @Override
    public ResourceLocation getAnimationResource(BattleStandardEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "animations/entity/standard_animation.json");
    }



}
