package WayofTime.alchemicalWizardry.common.harvest;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import WayofTime.alchemicalWizardry.api.harvest.IHarvestHandler;

public class GourdHarvestHandler implements IHarvestHandler {

    public boolean canHandleBlock(Block block) {
        return block == Blocks.melon_block || block == Blocks.pumpkin;
    }

    @Override
    public boolean harvestAndPlant(World world, int xCoord, int yCoord, int zCoord, Block block, int meta) {
        if (!this.canHandleBlock(block)) {
            return false;
        }
        world.func_147480_a(xCoord, yCoord, zCoord, true);
        return true;
    }
}
