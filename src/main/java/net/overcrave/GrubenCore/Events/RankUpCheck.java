package net.overcrave.GrubenCore.Events;

import com.gamingmesh.jobs.api.JobsLevelUpEvent;
import net.overcrave.GrubenCore.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class RankUpCheck implements Listener {
    @EventHandler
    public void onJobsLevelUp(JobsLevelUpEvent event) {
        Player p = event.getPlayer().getPlayer();
        rankUpCheck(event.getLevel(), p);
    }

    private void rankUpCheck(Integer l, Player p){
        if (l >= 16 && !Main.I.perms.has(p, "lehrling")){
            Bukkit.dispatchCommand(Main.I.console, "lp user " + p.getName() + " parent add lehrling");
            Bukkit.broadcastMessage("" + ChatColor.GOLD + ChatColor.BOLD + p.getDisplayName() + ChatColor.YELLOW + " ist jetzt ein " + ChatColor.YELLOW + ChatColor.BOLD + " Lehrling!");
        }
        if (l >= 32 && !Main.I.perms.has(p, "geselle")){
            Bukkit.dispatchCommand(Main.I.console, "lp user " + p.getName() + " parent add geselle");
            Bukkit.broadcastMessage("" + ChatColor.GOLD + ChatColor.BOLD + p.getDisplayName() + ChatColor.YELLOW + " ist jetzt ein " + ChatColor.GREEN + ChatColor.BOLD + " Geselle!");
        }
        if (l >= 64 && !Main.I.perms.has(p, "meister")){
            Bukkit.dispatchCommand(Main.I.console, "lp user " + p.getName() + " parent add meister");
            Bukkit.broadcastMessage("" + ChatColor.GOLD + ChatColor.BOLD + p.getDisplayName() + ChatColor.YELLOW + " ist jetzt ein " + ChatColor.RED + ChatColor.BOLD + " Meister!");
        }
        if (l >= 100 && !Main.I.perms.has(p, "großmeister")){
            Bukkit.dispatchCommand(Main.I.console, "lp user " + p.getName() + " parent add großmeister");
            Bukkit.broadcastMessage("" + ChatColor.GOLD + ChatColor.BOLD + p.getDisplayName() + ChatColor.YELLOW + " ist jetzt ein " + ChatColor.DARK_RED + ChatColor.BOLD + " Großmeister!");
        }
    }
}
