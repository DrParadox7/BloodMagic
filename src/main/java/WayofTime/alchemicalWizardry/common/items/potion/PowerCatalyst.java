package WayofTime.alchemicalWizardry.common.items.potion;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

import org.lwjgl.input.Keyboard;

import WayofTime.alchemicalWizardry.AlchemicalWizardry;
import WayofTime.alchemicalWizardry.api.alchemy.AlchemyRecipeRegistry;
import WayofTime.alchemicalWizardry.common.ICatalyst;

public class PowerCatalyst extends Item implements ICatalyst {

    private int catalystStrength;

    public PowerCatalyst(int catalystStrength) {
        super();
        this.catalystStrength = catalystStrength;
        setCreativeTab(AlchemicalWizardry.tabBloodMagic);
    }

    @Override
    public int getCatalystLevel() {
        return catalystStrength;
    }

    @Override
    public boolean isConcentration() {
        return true;
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
            par3List.add(
                    "-" + StatCollector.translateToLocal("tooltip.alchemy.press")
                            + " "
                            + EnumChatFormatting.BLUE
                            + StatCollector.translateToLocal("tooltip.alchemy.shift")
                            + EnumChatFormatting.GRAY
                            + " "
                            + StatCollector.translateToLocal("tooltip.alchemy.forrecipe")
                            + "-");
        }
    }
}
