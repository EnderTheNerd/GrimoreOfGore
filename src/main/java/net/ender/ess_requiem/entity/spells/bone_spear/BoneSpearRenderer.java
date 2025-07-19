package net.ender.ess_requiem.entity.spells.bone_spear;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BoneSpearRenderer extends GeoEntityRenderer<BoneSpearEntity> {
    public BoneSpearRenderer(EntityRendererProvider.Context context, BoneSpearModel boneSpearModel) {
        super(context, new BoneSpearModel());
        this.shadowRadius = 0.5f;
    }
}
