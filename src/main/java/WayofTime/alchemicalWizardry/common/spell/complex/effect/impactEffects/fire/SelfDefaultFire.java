package WayofTime.alchemicalWizardry.common.spell.complex.effect.impactEffects.fire;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import WayofTime.alchemicalWizardry.api.spell.SelfSpellEffect;

public class SelfDefaultFire extends SelfSpellEffect {

    public SelfDefaultFire(int power, int potency, int cost) {
        super(power, potency, cost);
    }

    @Override
    public void onSelfUse(World world, EntityPlayer player) {
        player.setFire((int) (10 * Math.pow(1.5, powerUpgrades + 1.5 * potencyUpgrades)));
    }
}
