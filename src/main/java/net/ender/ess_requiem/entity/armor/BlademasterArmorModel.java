package net.ender.ess_requiem.entity.armor;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;
import net.ender.ess_requiem.item.armor.BlademasterArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

public class BlademasterArmorModel   extends DefaultedItemGeoModel<BlademasterArmorItem> {

    public BlademasterArmorModel() {
        super(ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, ""));
    }



    @Override
    public ResourceLocation getModelResource(BlademasterArmorItem object) {
        return  ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "geo/blademaster_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BlademasterArmorItem object) {
        return ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "textures/models/blademaster_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BlademasterArmorItem WizardArmorItem) {
        return  ResourceLocation.fromNamespaceAndPath(IronsSpellbooks.MODID, "animations/wizard_armor_animation.json");
    }



}
