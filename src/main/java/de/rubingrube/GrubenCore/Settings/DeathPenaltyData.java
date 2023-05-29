package de.rubingrube.GrubenCore.Settings;

public class DeathPenaltyData {
    public int standardTokenLossMin, standardTokenLossMax, lehrlingTokenLossMin, lehrlingTokenLossMax, geselleTokenLossMin, geselleTokenLossMax, meisterTokenLossMin, meisterTokenLossMax, großmeisterTokenLossMin, großmeisterTokenLossMax;

    public DeathPenaltyData(){
        standardTokenLossMin = 25;
        standardTokenLossMax = 50;

        lehrlingTokenLossMin = 15;
        lehrlingTokenLossMax = 30;

        geselleTokenLossMin = 10;
        geselleTokenLossMax = 20;

        meisterTokenLossMin = 5;
        meisterTokenLossMax = 15;

        großmeisterTokenLossMin = 1;
        großmeisterTokenLossMax = 5;
    }
}
