package net.overcrave.GrubenCore.Settings;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class UserData {
    public String uuid;
    public List<SpawnerData> spawner;

    public UserData(Player player){
        uuid = player.getUniqueId().toString();
        spawner = new ArrayList<SpawnerData>();
    }
}
