package WayofTime.alchemicalWizardry.common.renderer.mob;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import WayofTime.alchemicalWizardry.common.entity.mob.EntityFallenAngel;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderFallenAngel extends RenderLiving {

    private static final ResourceLocation field_110833_a = new ResourceLocation(
            "alchemicalwizardry",
            "textures/models/WingedAngel.png");

    public RenderFallenAngel(ModelBase par1ModelBase, float par2) {
        super(par1ModelBase, par2);
    }

    public ResourceLocation func_110832_a(EntityFallenAngel par1EntityFallenAngel) {
        return field_110833_a;
    }

    public ResourceLocation getEntityTexture(Entity par1Entity) {
        return this.func_110832_a((EntityFallenAngel) par1Entity);
    }
}
