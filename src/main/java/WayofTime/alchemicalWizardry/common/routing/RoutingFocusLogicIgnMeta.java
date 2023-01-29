package WayofTime.alchemicalWizardry.common.routing;

import net.minecraft.item.ItemStack;

import WayofTime.alchemicalWizardry.api.RoutingFocusLogic;

public class RoutingFocusLogicIgnMeta extends RoutingFocusLogic {

    @Override
    public boolean getDefaultMatch(ItemStack keyStack, ItemStack checkedStack) {
        return (keyStack != null ? checkedStack != null && keyStack.getItem() == checkedStack.getItem() : false);
    }
}
