package WayofTime.alchemicalWizardry.common.renderer.mob;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import WayofTime.alchemicalWizardry.common.entity.mob.EntityWingedFireDemon;

public class RenderWingedFireDemon extends RenderLiving {

    private static final ResourceLocation field_110833_a = new ResourceLocation(
            "alchemicalwizardry",
            "textures/models/WingedFireDemon.png");

    public RenderWingedFireDemon(ModelBase par1ModelBase, float par2) {
        super(par1ModelBase, par2);
    }

    public ResourceLocation func_110832_a(EntityWingedFireDemon par1EntityWingedFireDemon) {
        return field_110833_a;
    }

    public ResourceLocation getEntityTexture(Entity par1Entity) {
        return this.func_110832_a((EntityWingedFireDemon) par1Entity);
    }
}
