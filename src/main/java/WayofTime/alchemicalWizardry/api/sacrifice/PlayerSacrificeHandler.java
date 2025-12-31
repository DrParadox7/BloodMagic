package WayofTime.alchemicalWizardry.api.sacrifice;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import WayofTime.alchemicalWizardry.AlchemicalWizardry;
import WayofTime.alchemicalWizardry.api.spell.APISpellHelper;
import WayofTime.alchemicalWizardry.api.tile.IBloodAltar;

public class PlayerSacrificeHandler {

    public static float scalingOfSacrifice = 0.001f;
    public static int soulFrayDuration = 400;
    public static Potion soulFrayId;

    public static float getPlayerIncense(EntityPlayer player) {
        return APISpellHelper.getCurrentIncense(player);
    }

    public static void setPlayerIncense(EntityPlayer player, float amount) {
        APISpellHelper.setCurrentIncense(player, amount);
    }

    public static boolean incrementIncense(EntityPlayer player, float min, float max, float increment) {
        float amount = getPlayerIncense(player);
        if (amount < min || amount >= max) {
            return false;
        }

        amount = amount + Math.min(increment, max - amount);
        setPlayerIncense(player, amount);

        // System.out.println("Amount of incense: " + amount + ", Increment: " + increment);

        return true;
    }

    public static boolean sacrificePlayerHealth(EntityPlayer player) {
        if (player.isPotionActive(soulFrayId)) {
            return false;
        }

        float amount = getPlayerIncense(player);

        if (amount >= 0) {
            float health = player.getHealth();
            float minHealth = (float) (Math.max(player.getMaxHealth() * 0.1, 1));

            if (health > minHealth) {
                DamageSourceBloodMagic damageSrc = DamageSourceBloodMagic.INSTANCE;
                float sacrificedHealth = health - minHealth;

                if (findAndFillAltar(
                        player.getEntityWorld(),
                        player,
                        (int) (sacrificedHealth * AlchemicalWizardry.lpPerSacrificeIncense * getModifier(amount)))) {

                    if (!player.getEntityWorld().isRemote) {
                        player.hurtResistantTime = 0;
                        player.attackEntityFrom(damageSrc, sacrificedHealth);

                        soulFrayDuration = (int) (10 * sacrificedHealth * (1 - (amount / 4000)));
                        player.addPotionEffect(new PotionEffect(soulFrayId.id, soulFrayDuration));

                        setPlayerIncense(player, 0);
                    }

                    return true;
                }
            }
        }

        return false;
    }

    public static float getModifier(float amount) {
        return 1 + amount * scalingOfSacrifice;
    }

    public static boolean findAndFillAltar(World world, EntityPlayer player, int amount) {
        int posX = (int) Math.round(player.posX - 0.5f);
        int posY = (int) player.posY;
        int posZ = (int) Math.round(player.posZ - 0.5f);
        IBloodAltar altarEntity = getAltar(world, posX, posY, posZ);

        if (altarEntity == null) {
            return false;
        }

        altarEntity.sacrificialDaggerCall(amount, false);
        altarEntity.startCycle();

        return true;
    }

    public static IBloodAltar getAltar(World world, int x, int y, int z) {
        TileEntity tileEntity;

        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                for (int k = -2; k <= 1; k++) {
                    tileEntity = world.getTileEntity(i + x, k + y, j + z);

                    if (tileEntity instanceof IBloodAltar) {
                        return (IBloodAltar) tileEntity;
                    }
                }
            }
        }

        return null;
    }
}
