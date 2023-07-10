package WayofTime.alchemicalWizardry.common.items.sigil.holding;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.MouseEvent;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ScrollHelper {

    @SubscribeEvent
    public void onMouseEvent(MouseEvent event) {
        EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;

        if (event.dwheel != 0 && player != null && player.isSneaking()) {
            ItemStack stack = player.getCurrentEquippedItem();

            if (stack != null) {
                Item item = stack.getItem();

                if (item instanceof SigilOfHolding) {
                    cycleSigil(stack, player, event.dwheel);
                    event.setCanceled(true);
                }
            }
        }
    }

    private void cycleSigil(ItemStack stack, EntityPlayer player, int dWheel) {
        int mode = SigilOfHolding.getCurrentItem(stack);
        mode = dWheel < 0 ? SigilOfHolding.next(mode) : SigilOfHolding.prev(mode);
        SigilOfHolding.cycleSigil(stack, mode);
        HoldingPacketHandler.INSTANCE.sendToServer(new HoldingPacketProcessor(player.inventory.currentItem, mode));
    }
}
