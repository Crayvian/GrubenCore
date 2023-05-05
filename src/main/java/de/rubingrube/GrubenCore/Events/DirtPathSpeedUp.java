package de.rubingrube.GrubenCore.Events;

import de.rubingrube.GrubenCore.Main;
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
            if(Main.I.perms.has(p, "großmeister")){
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 5));
            }
            else if(Main.I.perms.has(p, "meister")){
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 4));
            }
            else if(Main.I.perms.has(p, "geselle")){
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 3));
            }
            else if(Main.I.perms.has(p, "lehrling")){
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 2));
            }
            else{
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 1));
            }
        }
    }
}
