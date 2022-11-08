package WayofTime.alchemicalWizardry.common.routing;

import WayofTime.alchemicalWizardry.common.items.routing.ILimitedRoutingFocus;
import net.minecraft.item.ItemStack;

public class RoutingFocusLogicLimitDefault extends RoutingFocusLogicLimit {
    public int limit = 0;

    public RoutingFocusLogicLimitDefault(ItemStack stack) {
        if (stack != null && stack.getItem() instanceof ILimitedRoutingFocus) {
            limit = ((ILimitedRoutingFocus) stack.getItem()).getRoutingFocusLimit(stack);
        } else {
            limit = 0;
        }
    }

    @Override
    public int getRoutingLimit() {
        return limit;
    }
}
