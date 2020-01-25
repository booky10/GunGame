package tk.t11e.gungame.listener;
// Created by booky10 in GunGame (21:57 24.01.20)

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import tk.t11e.gungame.manager.GunGameManager;

public class DamageListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (!event.getEntityType().equals(EntityType.PLAYER)) return;
        if (!event.getDamager().getType().equals(EntityType.PLAYER)) return;
        if (!event.getEntity().getWorld().getName().equals(GunGameManager.getWorld().getName())) return;

        if (GunGameManager.isLocationInSpawn(event.getEntity().getLocation())) {
            event.setCancelled(true);
            return;
        }

        Player player = (Player) event.getEntity();
        Player killer = (Player) event.getDamager();

        if (event.getFinalDamage() >= player.getHealth()) {
            GunGameManager.respawn(player);
            GunGameManager.setDeaths(player.getUniqueId(), GunGameManager.getDeaths(player
                    .getUniqueId()) + 1);

            GunGameManager.upgrade(killer);
            GunGameManager.setKills(killer.getUniqueId(), GunGameManager.getKills(killer
                    .getUniqueId()) + 1);

            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onFallDamage(EntityDamageEvent event) {
        if(!event.getEntityType().equals(EntityType.PLAYER)) return;
        if(!event.getEntity().getWorld().getName().equals(GunGameManager.getWorld().getName())) return;
        if(!event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onFoodChange(FoodLevelChangeEvent event) {
        if(!event.getEntity().getWorld().getName().equals(GunGameManager.getWorld().getName())) return;

        event.setFoodLevel(20);
        event.setCancelled(true);
    }
}