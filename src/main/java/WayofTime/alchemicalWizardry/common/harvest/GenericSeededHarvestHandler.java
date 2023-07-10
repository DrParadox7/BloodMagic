package WayofTime.alchemicalWizardry.common.harvest;

import WayofTime.alchemicalWizardry.api.harvest.IHarvestHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

public class GenericSeededHarvestHandler implements IHarvestHandler {
    public Block harvestBlock;
    public int harvestMeta;
    public IPlantable harvestSeed;

    public GenericSeededHarvestHandler(String block, int meta, String seed) {
        harvestBlock = getBlockForString(block);
        harvestMeta = meta;
        Item testSeed = getItemForString(seed);
        if (testSeed instanceof IPlantable) {
            harvestSeed = (IPlantable) testSeed;
        } else {
            harvestSeed = null;
        }
    }

    public boolean isHarvesterValid() {
        return harvestBlock != null && harvestSeed != null;
    }

    public static Block getBlockForString(String str) {
        String[] parts = str.split(":");
        String modId = parts[0];
        String name = parts[1];
        return GameRegistry.findBlock(modId, name);
    }

    public static Item getItemForString(String str) {
        String[] parts = str.split(":");
        String modId = parts[0];
        String name = parts[1];
        return GameRegistry.findItem(modId, name);
    }

    public boolean canHandleBlock(Block block) {
        return block == harvestBlock;
    }

    public int getHarvestMeta(Block block) {
        return harvestMeta;
    }

    @Override
    public boolean harvestAndPlant(World world, int xCoord, int yCoord, int zCoord, Block block, int meta) {
        if (!this.canHandleBlock(block) || meta != this.getHarvestMeta(block)) {
            return false;
        }

        IPlantable seed = this.getSeedItem(block);

        if (seed == null) {
            world.func_147480_a(xCoord, yCoord, zCoord, true);

            return true;
        } else {
            int fortune = 0;

            List<ItemStack> list = block.getDrops(world, xCoord, yCoord, zCoord, meta, fortune);
            boolean foundAndRemovedSeed = false;

            for (ItemStack stack : list) {
                if (stack == null) {
                    continue;
                }

                Item item = stack.getItem();
                if (item == seed) {
                    int itemSize = stack.stackSize;
                    if (itemSize > 1) {
                        stack.stackSize--;
                        foundAndRemovedSeed = true;
                        break;
                    } else if (itemSize == 1) {
                        list.remove(stack);
                        foundAndRemovedSeed = true;
                        break;
                    }
                }
            }

            if (foundAndRemovedSeed) {
                int plantMeta = seed.getPlantMetadata(world, xCoord, yCoord, zCoord);
                Block plantBlock = seed.getPlant(world, xCoord, yCoord, zCoord);

                world.func_147480_a(xCoord, yCoord, zCoord, false);

                world.setBlock(xCoord, yCoord, zCoord, plantBlock, plantMeta, 3);

                for (ItemStack stack : list) {
                    EntityItem itemEnt = new EntityItem(world, xCoord, yCoord, zCoord, stack);

                    world.spawnEntityInWorld(itemEnt);
                }
            }

            return false;
        }
    }

    public IPlantable getSeedItem(Block block) {
        return harvestSeed;
    }
}
