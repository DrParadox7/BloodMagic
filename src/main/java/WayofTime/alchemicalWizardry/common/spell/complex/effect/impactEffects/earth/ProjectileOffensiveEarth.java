package WayofTime.alchemicalWizardry.common.spell.complex.effect.impactEffects.earth;

import WayofTime.alchemicalWizardry.api.spell.ProjectileImpactEffect;
import WayofTime.alchemicalWizardry.common.spell.complex.effect.SpellHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ProjectileOffensiveEarth extends ProjectileImpactEffect {
    public ProjectileOffensiveEarth(int power, int potency, int cost) {
        super(power, potency, cost);
    }

    @Override
    public void onEntityImpact(Entity mop, Entity proj) {
        int horizRange = this.powerUpgrades;
        int vertDepth = 3 * this.potencyUpgrades + 1;

        Vec3 blockVector = SpellHelper.getEntityBlockVector(mop);

        int posX = (int) (blockVector.xCoord);
        int posY = (int) (blockVector.yCoord);
        int posZ = (int) (blockVector.zCoord);

        World world = mop.worldObj;

        for (int i = -horizRange; i <= horizRange; i++) {
            for (int j = -vertDepth; j < 0; j++) {
                for (int k = -horizRange; k <= horizRange; k++) {
                    if (!world.isAirBlock(posX + i, posY + j, posZ + k)) {
                        Block block = world.getBlock(posX + i, posY + j, posZ + k);
                        if (block == null || block.getBlockHardness(world, posX + i, posY + j, posZ + k) == -1) {
                            continue;
                        }
                        if (block == Blocks.stone
                                || block == Blocks.cobblestone
                                || block == Blocks.sand
                                || block == Blocks.gravel
                                || block == Blocks.grass
                                || block == Blocks.dirt) {
                            world.setBlockToAir(posX + i, posY + j, posZ + k);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onTileImpact(World world, MovingObjectPosition mop) {}
}
