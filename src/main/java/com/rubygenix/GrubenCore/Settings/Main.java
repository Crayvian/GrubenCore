package com.rubygenix.GrubenCore.Settings;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public RankUpData rankData;
    public DeathPenaltyData deathPenaltyData;
    public BlockTreasureData blockTreasureData;

    public Main(){
        rankData = new RankUpData();
        deathPenaltyData = new DeathPenaltyData();
        blockTreasureData = new BlockTreasureData();
    }
}
