package net.overcrave.GrubenCore.Events;

import com.gmail.nossr50.datatypes.player.McMMOPlayer;
import com.gmail.nossr50.events.experience.McMMOPlayerLevelChangeEvent;
import com.gmail.nossr50.events.experience.McMMOPlayerLevelUpEvent;
import com.gmail.nossr50.util.player.UserManager;
import net.overcrave.GrubenCore.Main;
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
        int i = mcPlayer.getPowerLevel();
        rankUpCheck(i, p);
    }

    private void rankUpCheck(Integer l, Player p){
        if (l >= 100 && !Main.I.perms.has(p, "lehrling")){
            Bukkit.dispatchCommand(Main.I.console, "lp user " + p.getName() + " parent add lehrling");
            Bukkit.broadcastMessage("" + ChatColor.GOLD + ChatColor.BOLD + p.getDisplayName() + ChatColor.YELLOW + " ist jetzt ein" + ChatColor.YELLOW + ChatColor.BOLD + " Lehrling!");
        }
        if (l >= 500 && !Main.I.perms.has(p, "geselle")){
            Bukkit.dispatchCommand(Main.I.console, "lp user " + p.getName() + " parent add geselle");
            Bukkit.broadcastMessage("" + ChatColor.GOLD + ChatColor.BOLD + p.getDisplayName() + ChatColor.YELLOW + " ist jetzt ein" + ChatColor.GREEN + ChatColor.BOLD + " Geselle!");
        }
        if (l >= 1000 && !Main.I.perms.has(p, "meister")){
            Bukkit.dispatchCommand(Main.I.console, "lp user " + p.getName() + " parent add meister");
            Bukkit.broadcastMessage("" + ChatColor.GOLD + ChatColor.BOLD + p.getDisplayName() + ChatColor.YELLOW + " ist jetzt ein" + ChatColor.RED + ChatColor.BOLD + " Meister!");
        }
        if (l >= 1500 && !Main.I.perms.has(p, "großmeister")){
            Bukkit.dispatchCommand(Main.I.console, "lp user " + p.getName() + " parent add großmeister");
            Bukkit.broadcastMessage("" + ChatColor.GOLD + ChatColor.BOLD + p.getDisplayName() + ChatColor.YELLOW + " ist jetzt ein" + ChatColor.DARK_RED + ChatColor.BOLD + " Großmeister!");
        }
    }
}
