package WayofTime.alchemicalWizardry.common.items.potion;

import WayofTime.alchemicalWizardry.AlchemicalWizardry;
import WayofTime.alchemicalWizardry.api.alchemy.AlchemyRecipeRegistry;
import WayofTime.alchemicalWizardry.common.IBindingAgent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Keyboard;

public class StandardBindingAgent extends Item implements IBindingAgent {
    public StandardBindingAgent() {
        super();
        setCreativeTab(AlchemicalWizardry.tabBloodMagic);
    }

    @Override
    public float getSuccessRateForPotionNumber(int potions) {
        return (float) Math.pow(0.65, potions);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon("AlchemicalWizardry:StandardBindingAgent");
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        par3List.add(StatCollector.translateToLocal("tooltip.alchemy.usedinalchemy"));

        if (Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
            ItemStack[] recipe = AlchemyRecipeRegistry.getRecipeForItemStack(par1ItemStack);

            if (recipe != null) {
                par3List.add(EnumChatFormatting.BLUE + StatCollector.translateToLocal("tooltip.alchemy.recipe"));

                for (ItemStack item : recipe) {
                    if (item != null) {
                        par3List.add("" + item.getDisplayName());
                    }
                }
            }
        } else {
            par3List.add("-" + StatCollector.translateToLocal("tooltip.alchemy.press") + " " + EnumChatFormatting.BLUE
                    + StatCollector.translateToLocal("tooltip.alchemy.shift") + EnumChatFormatting.GRAY + " "
                    + StatCollector.translateToLocal("tooltip.alchemy.forrecipe") + "-");
        }
    }
}
