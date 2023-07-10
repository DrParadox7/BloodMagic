package WayofTime.alchemicalWizardry.common.items.routing;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;

public class InputRoutingFocus extends RoutingFocus {
    public InputRoutingFocus() {
        super();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon("AlchemicalWizardry:InputRoutingFocus");
    }
}
