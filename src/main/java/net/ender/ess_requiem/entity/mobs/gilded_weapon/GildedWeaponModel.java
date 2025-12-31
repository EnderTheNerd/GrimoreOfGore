package net.ender.ess_requiem.entity.mobs.gilded_weapon;

import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;
import net.ender.ess_requiem.entity.mobs.summoned_weapon.SoulmasterSwordEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;

public class GildedWeaponModel extends GeoModel<GildedWeaponEntity> {

    @Override
    public ResourceLocation getModelResource(GildedWeaponEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "geo/gilded_sword.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource (GildedWeaponEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "textures/entity/gilded_sword.png");
    }

    @Override
    public void setCustomAnimations(GildedWeaponEntity animatable, long instanceId, AnimationState<GildedWeaponEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);
    }

    @Override
    public ResourceLocation getAnimationResource(GildedWeaponEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "animations/entity/gilded_animation.json");
    }


}
