package net.ender.ess_requiem.entity.spells.bone_spear;

import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BoneSpearModel extends GeoModel<BoneSpearEntity> {


    @Override
    public ResourceLocation getModelResource(BoneSpearEntity object) {
        return ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "geo/bone_spear.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BoneSpearEntity object) {
        return ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "textures/entity/bone_spear.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BoneSpearEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "animations/entity/bone_spear.animation.json");
    }

}


