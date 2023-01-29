package WayofTime.alchemicalWizardry.common.spell.complex.effect.impactEffects.wind;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import WayofTime.alchemicalWizardry.api.spell.SelfSpellEffect;

public class SelfDefaultWind extends SelfSpellEffect {

    public SelfDefaultWind(int power, int potency, int cost) {
        super(power, potency, cost);
    }

    @Override
    public void onSelfUse(World world, EntityPlayer player) {
        player.extinguish();
        player.fallDistance = 0;
    }
}
