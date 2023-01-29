package WayofTime.alchemicalWizardry.common.demonVillage.demonHoard;

import net.minecraft.world.World;

import WayofTime.alchemicalWizardry.common.demonVillage.tileEntity.TEDemonPortal;

public abstract class DemonHoardPacket {

    public DemonHoardPacket() {}

    public abstract int summonDemons(TEDemonPortal teDemonPortal, World world, int x, int y, int z, DemonType type,
            int tier, boolean spawnGuardian);

    public abstract boolean canFitType(DemonType type);

    public abstract float getRelativeChance(DemonType type, int tier, boolean spawnGuardian);
}
