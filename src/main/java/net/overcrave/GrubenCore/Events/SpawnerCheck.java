package net.overcrave.GrubenCore.Events;

import net.overcrave.GrubenCore.Main;
import net.overcrave.GrubenCore.Settings.SpawnerData;
import net.overcrave.GrubenCore.Settings.UserData;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class SpawnerCheck implements Listener {
    public void onSpawnerBreak(BlockBreakEvent event){
        Player p = event.getPlayer();
        UserData d = null;
        Block b = event.getBlock();
        CreatureSpawner c;
        if (b instanceof CreatureSpawner){
            c = (CreatureSpawner) b;

            if (Main.spawnerData != null && Main.spawnerData.spawnerUserData.size() > 0) {
                Boolean userFound = false;
                for (UserData s : Main.spawnerData.spawnerUserData) {
                    if(s.uuid == p.getUniqueId().toString()){
                        userFound = true;
                        d = s;
                    }
                }
                if (!userFound){
                    d = new UserData(p);
                    Main.spawnerData.spawnerUserData.add(d);
                }

                Boolean spawnerInList = false;
                for(SpawnerData sD : d.spawner){
                    if(sD.SpawnerType == c.getSpawnedType().name()){
                        spawnerInList = true;
                        if(sD.Count <= 0){
                            sD.Count = 0;
                        }
                        sD.Count--;
                    }
                }
                if(!spawnerInList){
                    d.spawner.add(new SpawnerData(c.getSpawnedType().name(), 0));
                }
            }
        }
    }

    public void onSpawnerPlace(BlockPlaceEvent event){
        Player p = event.getPlayer();
        UserData d = null;
        Block b = event.getBlock();
        CreatureSpawner c;
        if (b.getState() instanceof CreatureSpawner){
            c = (CreatureSpawner) b.getState();

            if (Main.spawnerData != null && Main.spawnerData.spawnerUserData.size() > 0) {
                Boolean userFound = false;
                for (UserData s : Main.spawnerData.spawnerUserData) {
                    if(s.uuid == p.getUniqueId().toString()){
                        userFound = true;
                        d = s;
                    }
                }
                if (!userFound){
                    d = new UserData(p);
                    Main.spawnerData.spawnerUserData.add(d);
                }

                Boolean spawnerInList = false;
                for(SpawnerData sD : d.spawner){
                    if(sD.SpawnerType == c.getSpawnedType().name()){
                        spawnerInList = true;
                        if(sD.Count >= 4){
                            sD.Count = 4;
                            event.setCancelled(true);
                            p.sendMessage(ChatColor.RED + "[Baugrube] Du hast bereits zu viele Spawner dieser Art platziert!");
                        }
                        sD.Count++;
                    }
                }
                if(!spawnerInList){
                    d.spawner.add(new SpawnerData(c.getSpawnedType().name(), 1));
                }
            }
        }
    }
}
