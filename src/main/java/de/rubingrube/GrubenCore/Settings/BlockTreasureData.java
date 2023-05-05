package de.rubingrube.GrubenCore.Settings;

public class BlockTreasureData {
    public double standardTokenChance, lehrlingTokenChance, geselleTokenChance, meisterTokenChance, grossmeisterTokenChance;
    public int standardTokenMin, standardTokenMax, lehrlingTokenMin, lehrlingTokenMax, geselleTokenMin, geselleTokenMax,
            meisterTokenMin, meisterTokenMax, grossmeisterTokenMin, grossmeisterTokenMax, commonRange, rareRange;

    public BlockTreasureData(){
        commonRange = 1000;
        rareRange = 100;

        standardTokenChance = 0.008;
        standardTokenMin = 2;
        standardTokenMax = 8;

        lehrlingTokenChance = 0.009;
        lehrlingTokenMin = 4;
        lehrlingTokenMax = 16;

        geselleTokenChance = 0.01;
        geselleTokenMin = 8;
        geselleTokenMax = 32;

        meisterTokenChance = 0.015;
        meisterTokenMin = 16;
        meisterTokenMax = 64;

        grossmeisterTokenChance = 0.02;
        grossmeisterTokenMin = 32;
        grossmeisterTokenMax = 128;
    }
}
