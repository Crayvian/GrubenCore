package com.rubygenix.GrubenCore.Events;

import com.gmail.nossr50.datatypes.player.McMMOPlayer;
import com.gmail.nossr50.events.experience.McMMOPlayerLevelUpEvent;
import com.gmail.nossr50.util.player.UserManager;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DirtPathSpeedUp implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player p = event.getPlayer().getPlayer();
        Location loc = event.getTo();
        if(loc.getBlock().getType() == Material.DIRT_PATH){
            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 5, 2));
        }
    }
}
