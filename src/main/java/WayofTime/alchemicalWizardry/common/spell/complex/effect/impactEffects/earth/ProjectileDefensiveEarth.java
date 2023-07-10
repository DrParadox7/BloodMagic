package WayofTime.alchemicalWizardry.common.spell.complex.effect.impactEffects.earth;

import WayofTime.alchemicalWizardry.api.spell.ProjectileImpactEffect;
import WayofTime.alchemicalWizardry.common.spell.complex.effect.SpellHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ProjectileDefensiveEarth extends ProjectileImpactEffect {
    public ProjectileDefensiveEarth(int power, int potency, int cost) {
        super(power, potency, cost);
    }

    @Override
    public void onEntityImpact(Entity mop, Entity proj) {}

    @Override
    public void onTileImpact(World world, MovingObjectPosition mop) {
        int horizRange = this.powerUpgrades;
        int vertRange = this.potencyUpgrades;

        int posX = mop.blockX;
        int posY = mop.blockY;
        int posZ = mop.blockZ;

        for (int i = -horizRange; i <= horizRange; i++) {
            for (int j = -vertRange; j <= vertRange; j++) {
                for (int k = -horizRange; k <= horizRange; k++) {
                    if (!world.isAirBlock(posX + i, posY + j, posZ + k)) {
                        Block block = world.getBlock(posX + i, posY + j, posZ + k);
                        if (block == null || block.getBlockHardness(world, posX + i, posY + j, posZ + k) == -1) {
                            continue;
                        }
                        // block.breakBlock(world, posX+i, posY+j, posZ+k, block.blockID, world.getBlockMetadata(posX+i,
                        // posY+j, posZ+k));
                        // world.destroyBlock(posX+i, posY+j, posZ+k, true);
                        if (world.rand.nextFloat() < 0.6f) {
                            SpellHelper.smashBlock(world, posX + i, posY + j, posZ + k);
                        }
                    }
                }
            }
        }
    }
}
