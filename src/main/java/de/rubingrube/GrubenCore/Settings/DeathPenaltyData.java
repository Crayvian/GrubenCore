package de.rubingrube.GrubenCore.Settings;

public class DeathPenaltyData {
    public int standardTokenLossMin, standardTokenLossMax, lehrlingTokenLossMin, lehrlingTokenLossMax, geselleTokenLossMin, geselleTokenLossMax, meisterTokenLossMin, meisterTokenLossMax;

    public DeathPenaltyData(){
        standardTokenLossMin = 2;
        standardTokenLossMax = 8;
        lehrlingTokenLossMin = 2;
        lehrlingTokenLossMax = 6;
        geselleTokenLossMin = 1;
        geselleTokenLossMax = 5;
        meisterTokenLossMin = 0;
        meisterTokenLossMax = 3;
    }
}
