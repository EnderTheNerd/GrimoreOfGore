package net.ender.ess_requiem.entity.mobs.hopping_skull;

import com.mojang.blaze3d.vertex.PoseStack;

import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class HoppingSkullRenderer extends GeoEntityRenderer<HoppingSkullEntity> {

    public HoppingSkullRenderer(EntityRendererProvider.Context renderManager, GeoModel<HoppingSkullEntity> model) {
        super(renderManager, model);
        this.shadowRadius = 0.5f;
    }

    @Override
    public ResourceLocation getTextureLocation( HoppingSkullEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "textures/entity/hopping_skull.png");
    }

    @Override
    public void render(HoppingSkullEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
