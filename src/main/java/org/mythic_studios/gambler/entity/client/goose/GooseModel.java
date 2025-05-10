package org.mythic_studios.gambler.entity.client.goose;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.mythic_studios.gambler.GamblersDreamAlcohol;
import org.mythic_studios.gambler.entity.GooseEntity;

public class GooseModel<T extends GooseEntity> extends SinglePartEntityModel<T> {
    public static final EntityModelLayer GOOSE = new EntityModelLayer(Identifier.of(GamblersDreamAlcohol.MOD_ID, "goose"), "main");

    private final ModelPart Goose;
    private final ModelPart head;

    public GooseModel(ModelPart root) {
        this.Goose = root.getChild("Goose");
        this.head = this.Goose.getChild("head");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData Goose = modelPartData.addChild("Goose", ModelPartBuilder.create(), ModelTransform.pivot(3.0F, 24.0F, 0.0F));

        ModelPartData feet1 = Goose.addChild("feet1", ModelPartBuilder.create().uv(22, 16).cuboid(-4.0F, -3.0F, 0.0F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 23).cuboid(-4.0F, -1.0F, -1.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, 0.0F, 0.0F));

        ModelPartData feet2 = Goose.addChild("feet2", ModelPartBuilder.create().uv(22, 20).cuboid(-4.0F, -3.0F, 0.0F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F))
                .uv(6, 23).cuboid(-4.0F, -1.0F, -1.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(3.0F, 0.0F, 0.0F));

        ModelPartData head = Goose.addChild("head", ModelPartBuilder.create().uv(0, 12).cuboid(-2.0F, -12.0F, -6.0F, 5.0F, 5.0F, 6.0F, new Dilation(0.0F))
                .uv(22, 12).cuboid(-2.0F, -10.0F, -8.0F, 5.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, 0.0F, 0.0F));

        ModelPartData body = Goose.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -7.0F, -3.0F, 7.0F, 4.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(GooseEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(netHeadYaw, headPitch);

        this.animateMovement(GooseAnimations.ANIM_GOOSE_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.updateAnimation(entity.idleAnimationState, GooseAnimations.ANIM_GOOSE_IDLE, ageInTicks, 1f);
    }

    private void setHeadAngles(float headYaw, float headPitch) {
        headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
        headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

        this.head.yaw = headYaw * 0.017453292F;
        this.head.pitch = headPitch * 0.017453292F;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        Goose.render(matrices, vertexConsumer, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return Goose;
    }
}
