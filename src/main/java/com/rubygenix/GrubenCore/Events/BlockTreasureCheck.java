package com.rubygenix.GrubenCore.Events;

import com.rubygenix.GrubenCore.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

//This event gives the player some random keys or tokens randomly through mining
public class BlockTreasureCheck implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player p = event.getPlayer().getPlayer();
        Material m = event.getBlock().getType();

        Integer chanceToGetTokens = 50;
        Integer chanceToGetAKey = 50;

        Integer min = 0;
        Integer max = 100;

        if(isCommonResource(m)){
            double tokenKeyChance = (Math.random()*((max-min)+1))+min;
            double chance = (Math.random()*((max-min)+1))+min;
            if(tokenKeyChance >= 50){
                if(Main.I.perms.has(p, "großmeister") && chance <= 2){
                    Bukkit.dispatchCommand(Main.I.console, "crate give p Großmeister 1 " + p.getName());
                }
                else if(Main.I.perms.has(p, "meister") && chance <= 5){
                    Bukkit.dispatchCommand(Main.I.console, "crate give p Meister 1 " + p.getName());
                }
                else if(Main.I.perms.has(p, "geselle") && chance <= 10){
                    Bukkit.dispatchCommand(Main.I.console, "crate give p gesellen 1 " + p.getName());
                }
                else if(Main.I.perms.has(p, "lehrling") && chance <= 20){
                    Bukkit.dispatchCommand(Main.I.console, "crate give p Lehrlings 1 " + p.getName());
                }
            }
            else {
                if(Main.I.perms.has(p, "großmeister") && chance <= 2){
                    Integer tMin = 20;
                    Integer tMax = 100;
                    double tokens = (Math.random()*((max-min)+1))+min;
                    Bukkit.dispatchCommand(Main.I.console, "tm add " + p.getName() + " " + tokens);
                }
                else if(Main.I.perms.has(p, "meister") && chance <= 5){
                    Integer tMin = 10;
                    Integer tMax = 50;
                    double tokens = (Math.random()*((max-min)+1))+min;
                    Bukkit.dispatchCommand(Main.I.console, "tm add " + p.getName() + " " + tokens);
                }
                else if(Main.I.perms.has(p, "geselle") && chance <= 10){
                    Integer tMin = 6;
                    Integer tMax = 30;
                    double tokens = (Math.random()*((max-min)+1))+min;
                    Bukkit.dispatchCommand(Main.I.console, "tm add " + p.getName() + " " + tokens);
                }
                else if(Main.I.perms.has(p, "lehrling") && chance <= 20){
                    Integer tMin = 3;
                    Integer tMax = 15;
                    double tokens = (Math.random()*((max-min)+1))+min;
                    Bukkit.dispatchCommand(Main.I.console, "tm add " + p.getName() + " " + tokens);
                }
                else if(chance <= 30){
                    Integer tMin = 1;
                    Integer tMax = 5;
                    double tokens = (Math.random()*((max-min)+1))+min;
                    Bukkit.dispatchCommand(Main.I.console, "tm add " + p.getName() + " " + tokens);
                }
            }
        }
    }

    private Boolean isCommonResource(Material m){
        return (m == Material.STONE || m == Material.DIRT || m == Material.GRASS || m == Material.SAND || m == Material.GRAVEL);
    }
}
