package net.overcrave.GrubenCore.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathPenalty implements Listener {
    @EventHandler
    public void onJobsLevelUp(PlayerDeathEvent event) {
        Player p = event.getEntity();
    }
}
