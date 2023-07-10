package WayofTime.alchemicalWizardry.common.tileEntity;

import WayofTime.alchemicalWizardry.api.sacrifice.IIncense;
import WayofTime.alchemicalWizardry.api.sacrifice.PlayerSacrificeHandler;
import WayofTime.alchemicalWizardry.common.spell.complex.effect.SpellHelper;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

public class TECrucible extends TEInventory {
    private int radius = 5;

    public float rColour;
    public float gColour;
    public float bColour;

    public int ticksRemaining = 0;
    public int minValue = 0;
    public int maxValue = 0;
    public float incrementValue = 0;

    public int state =
            0; // 0 is when it gives off gray particles, 1 is when it gives off white particles (player can't use this
    // incense anymore), 2 is the normal colour of the incense, 3 means no particles (it is out)

    public TECrucible() {
        super(1);
        float f = (float) 1.0F;
        float f1 = f * 0.6F + 0.4F;
        float f2 = f * f * 0.7F - 0.5F;
        float f3 = f * f * 0.6F - 0.7F;
        rColour = f1;
        gColour = f2;
        bColour = f3;
    }

    @Override
    public void updateEntity() {
        if (worldObj.isRemote) return;

        boolean stateChanged = false;

        if (ticksRemaining <= 0) {
            ItemStack stack = this.getStackInSlot(0);
            if (stack != null && stack.getItem() instanceof IIncense) {
                IIncense incense = (IIncense) stack.getItem();

                rColour = incense.getRedColour(stack);
                gColour = incense.getGreenColour(stack);
                bColour = incense.getBlueColour(stack);
                ticksRemaining = incense.getIncenseDuration(stack);

                minValue = incense.getMinLevel(stack);
                maxValue = incense.getMaxLevel(stack);

                incrementValue = incense.getTickRate(stack);

                stack.stackSize--;
                if (stack.stackSize <= 0) {
                    this.setInventorySlotContents(0, null);
                }

                stateChanged = true;
            }
        }

        if (ticksRemaining > 0) {
            List<EntityPlayer> playerList =
                    SpellHelper.getPlayersInRange(worldObj, xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, radius, radius);

            if (playerList != null && !playerList.isEmpty()) {
                boolean allAreGood = true;

                for (EntityPlayer player : playerList) {
                    if (ticksRemaining > 0
                            && PlayerSacrificeHandler.incrementIncense(player, minValue, maxValue, incrementValue)) {
                        ticksRemaining--;
                        if (state != 2) {
                            state = 2;
                            stateChanged = true;
                        }

                        allAreGood = false;
                    }
                }

                if (allAreGood && state != 1) {
                    state = 1;
                    stateChanged = true;
                }

            } else {
                if (state != 0) {
                    state = 0;
                    worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                    updateNeighbors();
                }
            }
        } else {
            if (state != 0) {
                state = 0;
                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                updateNeighbors();
            }
        }

        if (stateChanged) {
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

            updateNeighbors();
        }
    }

    private void updateNeighbors() {
        Block block = worldObj.getBlock(xCoord + 1, yCoord, zCoord);
        block.onNeighborBlockChange(worldObj, xCoord + 1, yCoord, zCoord, block);
        block = worldObj.getBlock(xCoord - 1, yCoord, zCoord);
        block.onNeighborBlockChange(worldObj, xCoord - 1, yCoord, zCoord, block);
        block = worldObj.getBlock(xCoord, yCoord + 1, zCoord);
        block.onNeighborBlockChange(worldObj, xCoord, yCoord + 1, zCoord, block);
        block = worldObj.getBlock(xCoord, yCoord - 1, zCoord);
        block.onNeighborBlockChange(worldObj, xCoord, yCoord - 1, zCoord, block);
        block = worldObj.getBlock(xCoord, yCoord, zCoord + 1);
        block.onNeighborBlockChange(worldObj, xCoord, yCoord, zCoord + 1, block);
        block = worldObj.getBlock(xCoord, yCoord, zCoord - 1);
        block.onNeighborBlockChange(worldObj, xCoord, yCoord, zCoord - 1, block);
    }

    public void spawnClientParticle(World world, int x, int y, int z, Random rand) {
        switch (state) {
            case 0:
                world.spawnParticle(
                        "reddust",
                        x + 0.5D + rand.nextGaussian() / 8,
                        y + 0.7D,
                        z + 0.5D + rand.nextGaussian() / 8,
                        0.15,
                        0.15,
                        0.15);
                break;

            case 1:
                world.spawnParticle(
                        "reddust",
                        x + 0.5D + rand.nextGaussian() / 8,
                        y + 0.7D,
                        z + 0.5D + rand.nextGaussian() / 8,
                        1.0,
                        1.0,
                        1.0);
                break;

            case 2:
                world.spawnParticle(
                        "reddust",
                        x + 0.5D + rand.nextGaussian() / 8,
                        y + 0.7D,
                        z + 0.5D + rand.nextGaussian() / 8,
                        rColour,
                        gColour,
                        bColour);
                world.spawnParticle(
                        "flame",
                        x + 0.5D + rand.nextGaussian() / 32,
                        y + 0.7D,
                        z + 0.5D + rand.nextGaussian() / 32,
                        0,
                        0.02,
                        0);
                break;

            case 3:
                // No particles - it is out
                break;
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);

        tag.setInteger("ticksRemaining", ticksRemaining);
        tag.setInteger("minValue", minValue);
        tag.setInteger("maxValue", maxValue);
        tag.setFloat("increment", this.incrementValue);

        this.writeClientNBT(tag);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);

        ticksRemaining = tag.getInteger("ticksRemaining");
        minValue = tag.getInteger("minValue");
        maxValue = tag.getInteger("maxValue");
        incrementValue = tag.getFloat("increment");

        this.readClientNBT(tag);
    }

    public void writeClientNBT(NBTTagCompound tag) {
        tag.setFloat("rColour", rColour);
        tag.setFloat("gColour", gColour);
        tag.setFloat("bColour", bColour);
        tag.setInteger("state", state);

        NBTTagList invList = new NBTTagList();
        for (int i = 0; i < inv.length; i++) {
            if (inv[i] != null) {
                NBTTagCompound stackTag = new NBTTagCompound();
                stackTag.setByte("Slot", (byte) i);
                inv[i].writeToNBT(stackTag);
                invList.appendTag(stackTag);
            }
        }

        tag.setTag("Inventory", invList);
    }

    public void readClientNBT(NBTTagCompound tag) {
        rColour = tag.getFloat("rColour");
        gColour = tag.getFloat("gColour");
        bColour = tag.getFloat("bColour");
        state = tag.getInteger("state");

        NBTTagList invList = tag.getTagList("Inventory", Constants.NBT.TAG_COMPOUND);
        for (int i = 0; i < invList.tagCount(); i++) {
            NBTTagCompound stackTag = invList.getCompoundTagAt(i);
            int slot = stackTag.getByte("Slot");

            if (slot >= 0 && slot < inv.length) inv[slot] = ItemStack.loadItemStackFromNBT(stackTag);
        }
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        writeClientNBT(nbttagcompound);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 90210, nbttagcompound);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
        super.onDataPacket(net, packet);
        readClientNBT(packet.func_148857_g());
    }

    @Override
    public String getInventoryName() {
        return "TECrucible";
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return stack != null ? stack.getItem() instanceof IIncense : false;
    }

    public int getRSPowerOutput() {
        return (state == 1 || state == 0) ? 0 : 15;
    }
}
