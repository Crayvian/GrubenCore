package com.rubygenix.GrubenCore.Events;

import com.rubygenix.GrubenCore.Main;
import com.rubygenix.GrubenCore.Settings.BlockTreasureData;
import net.coreprotect.CoreProtectAPI;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.List;
import java.util.Random;

//This event gives the player some random keys or tokens randomly through mining
public class BlockTreasureCheck implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player p = event.getPlayer().getPlayer();
        Material m = event.getBlock().getType();
        Location loc = event.getBlock().getLocation();

        List<String[]> lookup = Main.I.coAPI.performLookup(31000000, null, null, null, null, null, 1, loc.toBlockLocation());
        if (lookup!=null){
            for (String[] value : lookup){
                CoreProtectAPI.ParseResult result = Main.I.coAPI.parseResult(value);
                if(result.getX() == loc.getBlockX() && result.getY() == loc.getBlockY() && result.getZ() == loc.getBlockZ()){
                    if(result.getActionString() == "place"){
                        return;
                    }
                }
            }
        }

        BlockTreasureData treasureData = Main.settingsData.blockTreasureData;

        double commonChance = Math.random()*treasureData.commonRange;
        double rareChance = Math.random()*treasureData.rareRange;

        OfflinePlayer oP = Main.I.server.getOfflinePlayer(p.getUniqueId());

        if(Main.I.perms.has(p, "großmeister")){
            int gain = getRandomNumber(treasureData.grossmeisterTokenMin, treasureData.grossmeisterTokenMax);
            if(isCommonResource(m)){
                if(commonChance < treasureData.grossmeisterTokenChance*treasureData.commonRange){
                    Main.I.econ.depositPlayer(oP, gain);
                    p.sendMessage(ChatColor.GREEN + "Du hast in diesem Block " + ChatColor.GOLD + gain + " Tokens" + ChatColor.GREEN + " gefunden!");
                }
            }
            else if(isRareResource(m)){
                if(rareChance < treasureData.grossmeisterTokenChance*treasureData.rareRange){
                    Main.I.econ.depositPlayer(oP, gain);
                    p.sendMessage(ChatColor.GREEN + "Du hast in diesem Block " + ChatColor.GOLD + gain + " Tokens" + ChatColor.GREEN + " gefunden!");
                }
            }
        }
        else if(Main.I.perms.has(p, "meister")){
            int gain = getRandomNumber(treasureData.meisterTokenMin, treasureData.meisterTokenMax);
            if(isCommonResource(m)){
                if(commonChance < treasureData.meisterTokenChance*treasureData.commonRange){
                    Main.I.econ.depositPlayer(oP, gain);
                    p.sendMessage(ChatColor.GREEN + "Du hast in diesem Block " + ChatColor.GOLD + gain + " Tokens" + ChatColor.GREEN + " gefunden!");
                }
            }
            else if(isRareResource(m)){
                if(rareChance < treasureData.meisterTokenChance*treasureData.rareRange){
                    Main.I.econ.depositPlayer(oP, gain);
                    p.sendMessage(ChatColor.GREEN + "Du hast in diesem Block " + ChatColor.GOLD + gain + " Tokens" + ChatColor.GREEN + " gefunden!");
                }
            }
        }
        else if(Main.I.perms.has(p, "geselle")){
            int gain = getRandomNumber(treasureData.geselleTokenMin, treasureData.geselleTokenMax);
            if(isCommonResource(m)){
                if(commonChance < treasureData.geselleTokenChance*treasureData.commonRange){
                    Main.I.econ.depositPlayer(oP, gain);
                    p.sendMessage(ChatColor.GREEN + "Du hast in diesem Block " + ChatColor.GOLD + gain + " Tokens" + ChatColor.GREEN + " gefunden!");
                }
            }
            else if(isRareResource(m)){
                if(rareChance < treasureData.geselleTokenChance*treasureData.rareRange){
                    Main.I.econ.depositPlayer(oP, gain);
                    p.sendMessage(ChatColor.GREEN + "Du hast in diesem Block " + ChatColor.GOLD + gain + " Tokens" + ChatColor.GREEN + " gefunden!");
                }
            }
        }
        else if(Main.I.perms.has(p, "lehrling")){
            int gain = getRandomNumber(treasureData.lehrlingTokenMin, treasureData.lehrlingTokenMax);
            if(isCommonResource(m)){
                if(commonChance < treasureData.lehrlingTokenChance*treasureData.commonRange){
                    Main.I.econ.depositPlayer(oP, gain);
                    p.sendMessage(ChatColor.GREEN + "Du hast in diesem Block " + ChatColor.GOLD + gain + " Tokens" + ChatColor.GREEN + " gefunden!");
                }
            }
            else if(isRareResource(m)){
                if(rareChance < treasureData.lehrlingTokenChance*treasureData.rareRange){
                    Main.I.econ.depositPlayer(oP, gain);
                    p.sendMessage(ChatColor.GREEN + "Du hast in diesem Block " + ChatColor.GOLD + gain + " Tokens" + ChatColor.GREEN + " gefunden!");
                }
            }
        }
        else{
            int gain = getRandomNumber(treasureData.standardTokenMin, treasureData.standardTokenMax);
            if(isCommonResource(m)){
                if(commonChance < treasureData.standardTokenChance*treasureData.commonRange){
                    Main.I.econ.depositPlayer(oP, gain);
                    p.sendMessage(ChatColor.GREEN + "Du hast in diesem Block " + ChatColor.GOLD + gain + " Tokens" + ChatColor.GREEN + " gefunden!");
                }
            }
            else if(isRareResource(m)){
                if(rareChance < treasureData.standardTokenChance*treasureData.rareRange){
                    Main.I.econ.depositPlayer(oP, gain);
                    p.sendMessage(ChatColor.GREEN + "Du hast in diesem Block " + ChatColor.GOLD + gain + " Tokens" + ChatColor.GREEN + " gefunden!");
                }
            }
        }
    }

    private int getRandomNumber(int min, int max) {
        max++;
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    private Boolean isCommonResource(Material m){
        return (m == Material.DEEPSLATE || m == Material.DEEPSLATE_COAL_ORE || m == Material.DEEPSLATE_IRON_ORE || m == Material.DEEPSLATE_COPPER_ORE || m == Material.DEEPSLATE_LAPIS_ORE || m == Material.DEEPSLATE_REDSTONE_ORE
                || m == Material.STONE || m == Material.COAL_ORE || m == Material.IRON_ORE || m == Material.COPPER_ORE || m == Material.LAPIS_ORE || m == Material.REDSTONE_ORE);
    }

    private Boolean isRareResource(Material m){
        return (m == Material.DEEPSLATE_DIAMOND_ORE || m == Material.DEEPSLATE_GOLD_ORE || m == Material.DEEPSLATE_EMERALD_ORE
                || m == Material.ANCIENT_DEBRIS || m == Material.DIAMOND_ORE || m == Material.GOLD_ORE || m == Material.EMERALD_ORE);
    }
}
