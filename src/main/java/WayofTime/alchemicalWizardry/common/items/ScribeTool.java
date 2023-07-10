package WayofTime.alchemicalWizardry.common.items;

import WayofTime.alchemicalWizardry.AlchemicalWizardry;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ScribeTool extends EnergyItems {
    private int meta;

    public ScribeTool(int inkType) {
        super();
        setMaxStackSize(1);
        setCreativeTab(AlchemicalWizardry.tabBloodMagic);
        setMaxDamage(10);
        setEnergyUsed(10);
        this.meta = inkType;
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        par3List.add(StatCollector.translateToLocal("tooltip.scribetool.desc"));

        if (!(par1ItemStack.getTagCompound() == null)) {
            par3List.add(StatCollector.translateToLocal("tooltip.owner.currentowner") + " "
                    + par1ItemStack.getTagCompound().getString("ownerName"));
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (EnergyItems.checkAndSetItemOwner(par1ItemStack, par3EntityPlayer)) {
            if (par1ItemStack.getItemDamage() > 0) {
                par1ItemStack.setItemDamage(par1ItemStack.getItemDamage() - 1);
            }
        }
        return par1ItemStack;
    }

    public int getType() {
        return this.meta;
    }
}
