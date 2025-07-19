package net.ender.ess_requiem.entity.mobs.skull_mass;

import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class SkullMassModel extends  DefaultedEntityGeoModel<SkullMassEntity> {

    public SkullMassModel() {
            super(ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "skull_mass"));
        }

        @Override
        public ResourceLocation getModelResource(SkullMassEntity animatable) {
            return ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "geo/skull_mass.geo.json");
        }

        @Override
        public ResourceLocation getTextureResource(SkullMassEntity animatable) {
            return ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "textures/entity/skull_mass.png");
        }




    }
