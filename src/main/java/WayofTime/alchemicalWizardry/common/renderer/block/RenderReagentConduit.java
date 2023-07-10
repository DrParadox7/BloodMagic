package WayofTime.alchemicalWizardry.common.renderer.block;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import WayofTime.alchemicalWizardry.api.Int3;
import WayofTime.alchemicalWizardry.common.tileEntity.TEReagentConduit;

public class RenderReagentConduit extends TileEntitySpecialRenderer {

    private static final ResourceLocation field_110629_a = new ResourceLocation(
            "alchemicalwizardry:textures/models/SimpleTransCircle.png");

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double d0, double d1, double d2, float f) {
        if (tileEntity instanceof TEReagentConduit) {
            int renderCount = ((TEReagentConduit) tileEntity).renderCount;
            float key1 = (tileEntity.xCoord * 54f - tileEntity.yCoord * 38.72f + tileEntity.zCoord * 10.432f);
            float key2 = (tileEntity.xCoord * 21.43f - tileEntity.yCoord * 9.96f + tileEntity.zCoord * 12.8f);
            Int3 colourMap = ((TEReagentConduit) tileEntity).getColour();
            GL11.glPushMatrix();
            Tessellator tessellator = Tessellator.instance;
            this.bindTexture(field_110629_a);
            GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, 10497.0F);
            GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, 10497.0F);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_CULL_FACE);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glDepthMask(false);
            tessellator.startDrawingQuads();
            tessellator.setColorRGBA(colourMap.xCoord, colourMap.yCoord, colourMap.zCoord, 200);
            GL11.glTranslated(d0 + 0.5, d1 + 0.5, d2 + 0.5);
            GL11.glRotatef(tileEntity.getWorldObj().getWorldTime() / 3.0f, 0F, 1F, 0F); // Rotate on planar axis
            GL11.glRotatef(renderCount + key1, 0F, 0F, 1F); // Rotate vertical axis
            GL11.glRotatef(renderCount * 2f + key2, 1F, 0F, 0F); // Rotate cylindrically
            tessellator.setBrightness(240);
            tessellator.addVertexWithUV(-0.5d, 0, -0.5d, 0.0d, 0.0d);
            tessellator.addVertexWithUV(0.5d, 0, -0.5d, 1.0d, 0.0d);
            tessellator.addVertexWithUV(0.5d, 0, 0.5d, 1.0d, 1.0d);
            tessellator.addVertexWithUV(-0.5d, 0, 0.5d, 0.0d, 1.0d);

            tessellator.draw();

            GL11.glDepthMask(true);

            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
            GL11.glPopMatrix();
        }
    }
}
