package WayofTime.alchemicalWizardry.common.summoning.meteor;

import net.minecraft.item.ItemStack;

public class MeteorParadigmComponent {

    protected int weight;
    protected ItemStack itemStack;

    public MeteorParadigmComponent(ItemStack stack, int weight) {
        this.itemStack = stack;
        this.weight = weight;
    }

    public int getWeight() {
        return this.weight;
    }

    public ItemStack getValidBlockParadigm() {
        return itemStack;
    }
}
