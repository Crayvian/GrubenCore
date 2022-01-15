package com.rubygenix.GrubenCore.Settings;

public class SpawnerData {
    public String SpawnerType;
    public String InternalData;
    public Integer Count;

    public SpawnerData(String mobName, Integer newPlacedCount){
        SpawnerType = mobName;
        InternalData = "";
        Count = newPlacedCount;
    }
}
