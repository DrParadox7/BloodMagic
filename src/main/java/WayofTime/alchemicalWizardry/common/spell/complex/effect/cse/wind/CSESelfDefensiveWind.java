package WayofTime.alchemicalWizardry.common.spell.complex.effect.cse.wind;

import WayofTime.alchemicalWizardry.api.spell.ComplexSpellEffect;
import WayofTime.alchemicalWizardry.api.spell.ComplexSpellModifier;
import WayofTime.alchemicalWizardry.api.spell.ComplexSpellType;
import WayofTime.alchemicalWizardry.api.spell.SpellParadigm;
import WayofTime.alchemicalWizardry.api.spell.SpellParadigmSelf;
import WayofTime.alchemicalWizardry.common.spell.complex.effect.impactEffects.wind.SelfDefensiveWind;

public class CSESelfDefensiveWind extends ComplexSpellEffect {
    public CSESelfDefensiveWind() {
        super(ComplexSpellType.WIND, ComplexSpellModifier.DEFENSIVE);
    }

    public CSESelfDefensiveWind(int power, int cost, int potency) {
        this();

        this.powerEnhancement = power;
        this.costEnhancement = cost;
        this.potencyEnhancement = potency;
    }

    @Override
    public void modifyParadigm(SpellParadigm parad) {
        if (parad instanceof SpellParadigmSelf) {
            ((SpellParadigmSelf) parad)
                    .addSelfSpellEffect(new SelfDefensiveWind(
                            this.powerEnhancement, this.potencyEnhancement, this.costEnhancement));
        }
    }

    @Override
    public ComplexSpellEffect copy(int power, int cost, int potency) {
        return new CSESelfDefensiveWind(power, cost, potency);
    }

    @Override
    public int getCostOfEffect() {
        return (int) (500
                * (0.7d * this.powerEnhancement + 1)
                * (0.8 * this.potencyEnhancement + 1)
                * Math.pow(0.85, costEnhancement));
    }
}
