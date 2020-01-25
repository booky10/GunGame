package tk.t11e.gungame.listener;
// Created by booky10 in GunGame (22:09 24.01.20)

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import tk.t11e.gungame.manager.GunGameManager;

public class MoveListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if(!event.getPlayer().getWorld().getName().equals(GunGameManager.getWorld().getName())) return;

        if(event.getTo().getBlock().getType().equals(Material.WATER))
            GunGameManager.respawn(event.getPlayer());
    }
}