package org.mythic_studios.gambler.entity.client.goose;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.mythic_studios.gambler.GamblersDreamAlcohol;
import org.mythic_studios.gambler.entity.GooseEntity;

public class GooseRenderer extends MobEntityRenderer<GooseEntity, GooseModel<GooseEntity>> {
    public GooseRenderer(EntityRendererFactory.Context context) {
        super(context, new GooseModel<>(context.getPart(GooseModel.GOOSE)), 0.75f);
    }

    @Override
    public Identifier getTexture(GooseEntity entity) {
        return Identifier.of(GamblersDreamAlcohol.MOD_ID, "textures/entity/goose/goose_default_texture.png");
    }

    @Override
    public void render(GooseEntity livingEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        if(livingEntity.isBaby()) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrixStack.scale(1f, 1f, 1f);
        }

        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
