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

        DeathPenaltyData deathData = Main.settingsData.deathPenaltyData;
        if (Main.I.perms.has(p, "großmeister")){
            int maxAmount = (int)(bal*(deathData.großmeisterTokenLossMax/100.0f));
            int tokenloss = getRandomNumber(deathData.großmeisterTokenLossMin, maxAmount);
            Main.I.econ.withdrawPlayer(oP, tokenloss);
            p.sendMessage(ChatColor.RED + "Du hast beim Tod " + ChatColor.DARK_RED + ChatColor.BOLD + tokenloss +" Tokens " + ChatColor.RED + "verloren!");
        }
        else if (Main.I.perms.has(p, "meister")){
            int maxAmount = (int)(bal*(deathData.meisterTokenLossMax/100.0f));
            int tokenloss = getRandomNumber(deathData.meisterTokenLossMin, maxAmount);
            Main.I.econ.withdrawPlayer(oP, tokenloss);
            p.sendMessage(ChatColor.RED + "Du hast beim Tod " + ChatColor.DARK_RED + ChatColor.BOLD + tokenloss +" Tokens " + ChatColor.RED + "verloren!");
        }
        else if (Main.I.perms.has(p, "geselle")){
            int maxAmount = (int)(bal*(deathData.geselleTokenLossMax/100.0f));
            int tokenloss = getRandomNumber(deathData.geselleTokenLossMin, maxAmount);
            Main.I.econ.withdrawPlayer(oP, tokenloss);
            p.sendMessage(ChatColor.RED + "Du hast beim Tod " + ChatColor.DARK_RED + ChatColor.BOLD + tokenloss +" Tokens " + ChatColor.RED + "verloren!");
        }
        else if (Main.I.perms.has(p, "lehrling")){
            int maxAmount = (int)(bal*(deathData.lehrlingTokenLossMax/100.0f));
            int tokenloss = getRandomNumber(deathData.lehrlingTokenLossMin, maxAmount);
            Main.I.econ.withdrawPlayer(oP, tokenloss);
            p.sendMessage(ChatColor.RED + "Du hast beim Tod " + ChatColor.DARK_RED + ChatColor.BOLD + tokenloss +" Tokens " + ChatColor.RED + "verloren!");
        }
        else{
            int maxAmount = (int)(bal*(deathData.standardTokenLossMax/100.0f));
            int tokenloss = getRandomNumber(deathData.standardTokenLossMin, maxAmount);
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
