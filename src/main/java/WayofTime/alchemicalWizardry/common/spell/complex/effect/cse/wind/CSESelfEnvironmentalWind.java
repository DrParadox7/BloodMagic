package WayofTime.alchemicalWizardry.common.spell.complex.effect.cse.wind;

import WayofTime.alchemicalWizardry.api.spell.ComplexSpellEffect;
import WayofTime.alchemicalWizardry.api.spell.ComplexSpellModifier;
import WayofTime.alchemicalWizardry.api.spell.ComplexSpellType;
import WayofTime.alchemicalWizardry.api.spell.SpellParadigm;
import WayofTime.alchemicalWizardry.api.spell.SpellParadigmSelf;
import WayofTime.alchemicalWizardry.common.spell.complex.effect.impactEffects.wind.SelfEnvironmentalWind;

public class CSESelfEnvironmentalWind extends ComplexSpellEffect {
    public CSESelfEnvironmentalWind() {
        super(ComplexSpellType.WIND, ComplexSpellModifier.ENVIRONMENTAL);
    }

    public CSESelfEnvironmentalWind(int power, int cost, int potency) {
        this();

        this.powerEnhancement = power;
        this.costEnhancement = cost;
        this.potencyEnhancement = potency;
    }

    @Override
    public void modifyParadigm(SpellParadigm parad) {
        if (parad instanceof SpellParadigmSelf) {
            ((SpellParadigmSelf) parad)
                    .addSelfSpellEffect(new SelfEnvironmentalWind(
                            this.powerEnhancement, this.potencyEnhancement, this.costEnhancement));
        }
    }

    @Override
    public ComplexSpellEffect copy(int power, int cost, int potency) {
        return new CSESelfEnvironmentalWind(power, cost, potency);
    }

    @Override
    public int getCostOfEffect() {
        return (int) (500
                * (0.7d * this.powerEnhancement + 1)
                * (0.2 * this.potencyEnhancement + 1)
                * Math.pow(0.85, costEnhancement));
    }
}
