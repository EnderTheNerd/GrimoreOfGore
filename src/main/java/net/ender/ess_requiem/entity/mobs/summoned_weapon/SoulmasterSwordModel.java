package net.ender.ess_requiem.entity.mobs.summoned_weapon;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SoulmasterSwordModel extends GeoModel<SoulmasterSwordEntity> {


    @Override
    public ResourceLocation getModelResource(SoulmasterSwordEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "geo/soulmaster_sword.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource (SoulmasterSwordEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "textures/entity/soulmaster_sword.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SoulmasterSwordEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(IronsSpellbooks.MODID, "animations/summoned_weapon_animations.json");
    }



}


