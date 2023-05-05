package de.rubingrube.GrubenCore.Events;

import de.rubingrube.GrubenCore.Main;
import de.rubingrube.GrubenCore.Settings.DeathPenaltyData;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Random;

//This event makes the player lose some tokens on his death
public class DeathPenalty implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player p = event.getEntity();
        playerCheck(p);
    }

    private void playerCheck(Player p){
        OfflinePlayer oP = Main.I.server.getOfflinePlayer(p.getUniqueId());
        double bal = Main.I.econ.getBalance(oP);
        if (bal < 10.0){
            return;
        }

        DeathPenaltyData deathData = Main.settingsData.deathPenaltyData;
        if (Main.I.perms.has(p, "meister")){
            int tokenloss = getRandomNumber(deathData.meisterTokenLossMin, deathData.meisterTokenLossMax);
            if (tokenloss != 0){
                Main.I.econ.withdrawPlayer(oP, tokenloss);
                p.sendMessage(ChatColor.RED + "Du hast beim Tod " + ChatColor.DARK_RED + ChatColor.BOLD + tokenloss +" Tokens " + ChatColor.RED + "verloren!");
            }
        }
        else if (Main.I.perms.has(p, "geselle")){
            int tokenloss = getRandomNumber(deathData.geselleTokenLossMin, deathData.geselleTokenLossMax);
            Main.I.econ.withdrawPlayer(oP, tokenloss);
            p.sendMessage(ChatColor.RED + "Du hast beim Tod " + ChatColor.DARK_RED + ChatColor.BOLD + tokenloss +" Tokens " + ChatColor.RED + "verloren!");
        }
        else if (Main.I.perms.has(p, "lehrling")){
            int tokenloss = getRandomNumber(deathData.lehrlingTokenLossMin, deathData.lehrlingTokenLossMax);
            Main.I.econ.withdrawPlayer(oP, tokenloss);
            p.sendMessage(ChatColor.RED + "Du hast beim Tod " + ChatColor.DARK_RED + ChatColor.BOLD + tokenloss +" Tokens " + ChatColor.RED + "verloren!");
        }
        else{
            int tokenloss = getRandomNumber(deathData.standardTokenLossMin, deathData.standardTokenLossMax);
            Main.I.econ.withdrawPlayer(oP, tokenloss);
            p.sendMessage(ChatColor.RED + "Du hast beim Tod " + ChatColor.DARK_RED + ChatColor.BOLD + tokenloss +" Tokens " + ChatColor.RED + "verloren!");
        }
    }

    private int getRandomNumber(int min, int max) {
        max++;
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}
