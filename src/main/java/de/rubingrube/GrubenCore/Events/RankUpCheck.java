package de.rubingrube.GrubenCore.Events;

import com.Zrips.CMI.CMI;
import com.gmail.nossr50.datatypes.player.McMMOPlayer;
import com.gmail.nossr50.events.experience.McMMOPlayerLevelUpEvent;
import com.gmail.nossr50.util.player.UserManager;
import de.rubingrube.GrubenCore.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

//This event tracks the players powerlevel progression and ranks them up if they reach a certain level
public class RankUpCheck implements Listener {

    @EventHandler
    public void onSkillLevelUp(McMMOPlayerLevelUpEvent event) {
        Player p = event.getPlayer().getPlayer();
        McMMOPlayer mcPlayer = UserManager.getPlayer(p);
        assert mcPlayer != null;
        int i = mcPlayer.getPowerLevel();
        rankUpCheck(i, p);
    }

    private void rankUpCheck(Integer l, Player p){
        int lehrlingMin = Main.settingsData.rankData.lehrlingRequirement;
        int geselleMin = Main.settingsData.rankData.geselleRequirement;
        int meisterMin = Main.settingsData.rankData.meisterRequirement;
        int grossmeisterMin = Main.settingsData.rankData.grossmeisterRequirement;

        if (l >= lehrlingMin && !Main.I.perms.has(p, "lehrling")){
            Bukkit.dispatchCommand(Main.I.console, "lp user " + p.getName() + " parent add lehrling");
            CMI.getInstance().broadcastMessage("" + ChatColor.GOLD + ChatColor.BOLD + p.getName() + ChatColor.YELLOW + " ist jetzt ein" + ChatColor.YELLOW + ChatColor.BOLD + " Lehrling!");
        }
        if (l >= geselleMin && !Main.I.perms.has(p, "geselle")){
            Bukkit.dispatchCommand(Main.I.console, "lp user " + p.getName() + " parent add geselle");
            Bukkit.dispatchCommand(Main.I.console, "lp user " + p.getName() + " parent remove lehrling");
            CMI.getInstance().broadcastMessage("" + ChatColor.GOLD + ChatColor.BOLD + p.getName() + ChatColor.YELLOW + " ist jetzt ein" + ChatColor.GREEN + ChatColor.BOLD + " Geselle!");
        }
        if (l >= meisterMin && !Main.I.perms.has(p, "meister")){
            Bukkit.dispatchCommand(Main.I.console, "lp user " + p.getName() + " parent add meister");
            Bukkit.dispatchCommand(Main.I.console, "lp user " + p.getName() + " parent remove lehrling");
            Bukkit.dispatchCommand(Main.I.console, "lp user " + p.getName() + " parent remove geselle");
            CMI.getInstance().broadcastMessage("" + ChatColor.GOLD + ChatColor.BOLD + p.getName() + ChatColor.YELLOW + " ist jetzt ein" + ChatColor.RED + ChatColor.BOLD + " Meister!");
        }
        if (l >= grossmeisterMin && !Main.I.perms.has(p, "großmeister")){
            Bukkit.dispatchCommand(Main.I.console, "lp user " + p.getName() + " parent add großmeister");
            Bukkit.dispatchCommand(Main.I.console, "lp user " + p.getName() + " parent remove lehrling");
            Bukkit.dispatchCommand(Main.I.console, "lp user " + p.getName() + " parent remove geselle");
            Bukkit.dispatchCommand(Main.I.console, "lp user " + p.getName() + " parent remove meister");
            CMI.getInstance().broadcastMessage("" + ChatColor.GOLD + ChatColor.BOLD + p.getName() + ChatColor.YELLOW + " ist jetzt ein" + ChatColor.DARK_RED + ChatColor.BOLD + " Großmeister!");
        }
    }
}
