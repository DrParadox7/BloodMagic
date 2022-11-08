package WayofTime.alchemicalWizardry.common.renderer.model;

// Date: 07/03/2014 9:30:25 PM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.util.ForgeDirection;

public class ModelSpellParadigmBlock extends ModelBase {
    // fields
    ModelRenderer core;
    ModelRenderer outputMain;
    ModelRenderer output1;
    ModelRenderer output2;
    ModelRenderer output3;
    ModelRenderer output4;
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;

    public ModelSpellParadigmBlock() {
        textureWidth = 64;
        textureHeight = 64;

        core = new ModelRenderer(this, 0, 0);
        core.addBox(-3F, -3F, -3F, 6, 6, 6);
        core.setRotationPoint(0F, 16F, 0F);
        core.setTextureSize(64, 64);
        core.mirror = true;
        setRotation(core, 0F, 0F, 0F);
        outputMain = new ModelRenderer(this, 0, 13);
        outputMain.addBox(6F, -2F, -2F, 2, 4, 4);
        outputMain.setRotationPoint(0F, 16F, 0F);
        outputMain.setTextureSize(64, 64);
        outputMain.mirror = true;
        setRotation(outputMain, 0F, 0F, 0F);
        output1 = new ModelRenderer(this, 0, 22);
        output1.addBox(5F, -5F, -5F, 3, 2, 2);
        output1.setRotationPoint(0F, 16F, 0F);
        output1.setTextureSize(64, 64);
        output1.mirror = true;
        setRotation(output1, 0F, 0F, 0F);
        output2 = new ModelRenderer(this, 0, 22);
        output2.addBox(5F, -5F, 3F, 3, 2, 2);
        output2.setRotationPoint(0F, 16F, 0F);
        output2.setTextureSize(64, 64);
        output2.mirror = true;
        setRotation(output2, 0F, 0F, 0F);
        output3 = new ModelRenderer(this, 0, 22);
        output3.addBox(5F, 3F, -5F, 3, 2, 2);
        output3.setRotationPoint(0F, 16F, 0F);
        output3.setTextureSize(64, 64);
        output3.mirror = true;
        setRotation(output3, 0F, 0F, 0F);
        output4 = new ModelRenderer(this, 0, 22);
        output4.addBox(5F, 3F, 3F, 3, 2, 2);
        output4.setRotationPoint(0F, 16F, 0F);
        output4.setTextureSize(64, 64);
        output4.mirror = true;
        setRotation(output4, 0F, 0F, 0F);
        Shape1 = new ModelRenderer(this, 0, 28);
        Shape1.addBox(-5F, -5F, -1F, 10, 1, 2);
        Shape1.setRotationPoint(0F, 16F, 0F);
        Shape1.setTextureSize(64, 64);
        Shape1.mirror = true;
        setRotation(Shape1, 0F, 0F, 0F);
        Shape2 = new ModelRenderer(this, 25, 28);
        Shape2.addBox(-5F, -4F, -4F, 1, 8, 8);
        Shape2.setRotationPoint(0F, 16F, 0F);
        Shape2.setTextureSize(64, 64);
        Shape2.mirror = true;
        setRotation(Shape2, 0F, 0F, 0F);
        Shape3 = new ModelRenderer(this, 0, 33);
        Shape3.addBox(-5F, 4F, -1F, 10, 1, 2);
        Shape3.setRotationPoint(0F, 16F, 0F);
        Shape3.setTextureSize(64, 64);
        Shape3.mirror = true;
        setRotation(Shape3, 0F, 0F, 0F);
        Shape4 = new ModelRenderer(this, 0, 38);
        Shape4.addBox(-5F, -1F, -5F, 10, 2, 1);
        Shape4.setRotationPoint(0F, 16F, 0F);
        Shape4.setTextureSize(64, 64);
        Shape4.mirror = true;
        setRotation(Shape4, 0F, 0F, 0F);
        Shape5 = new ModelRenderer(this, 0, 43);
        Shape5.addBox(-5F, -1F, 4F, 10, 2, 1);
        Shape5.setRotationPoint(0F, 16F, 0F);
        Shape5.setTextureSize(64, 64);
        Shape5.mirror = true;
        setRotation(Shape5, 0F, 0F, 0F);
    }

    public void render(
            Entity entity,
            float f,
            float f1,
            float f2,
            float f3,
            float f4,
            float f5,
            ForgeDirection input,
            ForgeDirection output) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        float xInputRot = 0.0f;
        float yInputRot = 0.0f;
        float zInputRot = 0.0f;
        float xOutputRot = 0.0f;
        float yOutputRot = 0.0f;
        float zOutputRot = 0.0f;

        switch (input) {
            case NORTH:
                xInputRot = 0.0f;
                yInputRot = 0.0f;
                zInputRot = 0.0f;
                break;

            case EAST:
                xInputRot = 0.0f;
                yInputRot = (float) (0.5f * Math.PI);
                zInputRot = 0.0f;
                break;

            case SOUTH:
                xInputRot = 0.0f;
                yInputRot = (float) (1.0f * Math.PI);
                zInputRot = 0.0f;
                break;

            case WEST:
                xInputRot = 0.0f;
                yInputRot = (float) (-0.5f * Math.PI);
                zInputRot = 0.0f;
                break;

            case UP:
                xInputRot = (float) (-0.5f * Math.PI);
                yInputRot = 0.0f;
                zInputRot = 0.0f;
                break;

            case DOWN:
                xInputRot = (float) (0.5f * Math.PI);
                yInputRot = 0.0f;
                zInputRot = 0.0f;
                break;

            default:
                break;
        }

        switch (output) {
            case NORTH:
                xOutputRot = 0.0f;
                yOutputRot = (float) (0.5f * Math.PI);
                zOutputRot = 0.0f;
                break;

            case EAST:
                xOutputRot = 0.0f;
                yOutputRot = (float) (1.0f * Math.PI);
                zOutputRot = 0.0f;
                break;

            case SOUTH:
                xOutputRot = 0.0f;
                yOutputRot = (float) (-0.5f * Math.PI);
                zOutputRot = 0.0f;
                break;

            case WEST:
                xOutputRot = 0.0f;
                yOutputRot = 0.0f;
                zOutputRot = 0.0f;
                break;

            case UP:
                xOutputRot = 0.0f;
                yOutputRot = 0.0f;
                zOutputRot = (float) (-0.5f * Math.PI);
                break;

            case DOWN:
                xOutputRot = 0.0f;
                yOutputRot = 0.0f;
                zOutputRot = (float) (0.5f * Math.PI);
                break;

            default:
                break;
        }

        this.setRotation(outputMain, xOutputRot, yOutputRot, zOutputRot);
        this.setRotation(output1, xOutputRot, yOutputRot, zOutputRot);
        this.setRotation(output2, xOutputRot, yOutputRot, zOutputRot);
        this.setRotation(output3, xOutputRot, yOutputRot, zOutputRot);
        this.setRotation(output4, xOutputRot, yOutputRot, zOutputRot);
        this.setRotation(Shape1, xOutputRot, yOutputRot, zOutputRot);
        this.setRotation(Shape2, xOutputRot, yOutputRot, zOutputRot);
        this.setRotation(Shape3, xOutputRot, yOutputRot, zOutputRot);
        this.setRotation(Shape4, xOutputRot, yOutputRot, zOutputRot);
        this.setRotation(Shape5, xOutputRot, yOutputRot, zOutputRot);
        core.render(f5);
        outputMain.render(f5);
        output1.render(f5);
        output2.render(f5);
        output3.render(f5);
        output4.render(f5);
        Shape1.render(f5);
        Shape2.render(f5);
        Shape3.render(f5);
        Shape4.render(f5);
        Shape5.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }
}
