package net.ender.ess_requiem.entity.mobs.hopping_skull;


import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class HoppingSkullModel extends DefaultedEntityGeoModel<HoppingSkullEntity> {
    public HoppingSkullModel() {
        super(ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "hopping_skull"));
    }

    @Override
    public ResourceLocation getModelResource(HoppingSkullEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "geo/hopping_skull.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(HoppingSkullEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "textures/entity/hopping_skull.png");
    }





}
