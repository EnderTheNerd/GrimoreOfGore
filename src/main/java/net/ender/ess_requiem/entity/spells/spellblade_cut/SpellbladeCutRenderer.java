package net.ender.ess_requiem.entity.spells.spellblade_cut;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import net.minecraft.world.phys.Vec3;

import org.joml.Matrix4f;



public class SpellbladeCutRenderer extends EntityRenderer<SpellbladeCutProjectile> {
    private static final ResourceLocation[] TEXTURES = {
            ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "textures/entity/spellblade_cut/spellblade_cut_1.png"),
            ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "textures/entity/spellblade_cut/spellblade_cut_2.png"),
            ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "textures/entity/spellblade_cut/spellblade_cut_3.png"),
            ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "textures/entity/spellblade_cut/spellblade_cut_4.png"),
    };

    public SpellbladeCutRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(SpellbladeCutProjectile entity, float yaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int light) {
        poseStack.pushPose();

        PoseStack.Pose pose = poseStack.last();
        Vec3 motion = entity.getDeltaMovement();
        float xRot = -((float) (Mth.atan2(motion.horizontalDistance(), motion.y) * (double) (180F / (float) Math.PI)) - 90.0F);
        float yRot = -((float) (Mth.atan2(motion.z, motion.x) * (double) (180F / (float) Math.PI)) + 90.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(yRot));
        poseStack.mulPose(Axis.XP.rotationDegrees(xRot));
        poseStack.mulPose(Axis.ZP.rotationDegrees(entity.getZRot() + (entity.tickCount + partialTicks) * 40));
        float width = 2.5f;
        poseStack.mulPose(Axis.XP.rotationDegrees(45));
        float scale = entity.getScale();
        poseStack.scale(scale, scale, scale);
        drawSlash(pose, entity, bufferSource, light, width);

        poseStack.popPose();

        super.render(entity, yaw, partialTicks, poseStack, bufferSource, light);
    }

    private void drawSlash(PoseStack.Pose pose, SpellbladeCutProjectile entity, MultiBufferSource bufferSource, int light, float width) {
        Matrix4f poseMatrix = pose.pose();


        VertexConsumer consumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(getTextureLocation(entity)));
        float halfWidth = width * .5f;

        consumer.addVertex(poseMatrix, 0, -halfWidth, -halfWidth).setColor(90, 0, 10, 255).setUv(0f, 1f).setOverlay(OverlayTexture.NO_OVERLAY).setLight(light).setNormal(0f, 1f, 0f);
        consumer.addVertex(poseMatrix, 0, halfWidth, -halfWidth).setColor(90, 0, 10, 255).setUv(1f, 1f).setOverlay(OverlayTexture.NO_OVERLAY).setLight(light).setNormal(0f, 1f, 0f);
        consumer.addVertex(poseMatrix, 0, halfWidth, halfWidth).setColor(90, 0, 10, 255).setUv(1f, 0f).setOverlay(OverlayTexture.NO_OVERLAY).setLight(light).setNormal(0f, 1f, 0f);
        consumer.addVertex(poseMatrix, 0, -halfWidth, halfWidth).setColor(90, 0, 10, 255).setUv(0f, 0f).setOverlay(OverlayTexture.NO_OVERLAY).setLight(light).setNormal(0f, 1f, 0f);
    }

    @Override
    public ResourceLocation getTextureLocation(SpellbladeCutProjectile entity) {
     int frame = (entity.tickCount / 4) % TEXTURES.length;

        return TEXTURES[frame];
    }
}
