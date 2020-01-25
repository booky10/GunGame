package tk.t11e.gungame.listener;
// Created by booky10 in GunGame (22:12 24.01.20)

import org.bukkit.GameMode;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import tk.t11e.gungame.manager.GunGameManager;

public class InventoryListener implements Listener {

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        if(!event.getPlayer().getWorld().getName().equals(GunGameManager.getWorld().getName())) return;
        if(event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) return;

        System.out.println(11);
        event.setCancelled(true);
    }

    @EventHandler
    public void onSwap(PlayerSwapHandItemsEvent event) {
        if(!event.getPlayer().getWorld().getName().equals(GunGameManager.getWorld().getName())) return;
        if(event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) return;

        System.out.println(111);
        event.setCancelled(true);
    }

    @EventHandler
    public void onInventory(InventoryClickEvent event) {
        if(!event.getWhoClicked().getType().equals(EntityType.PLAYER)) return;
        if(!event.getWhoClicked().getWorld().getName().equals(GunGameManager.getWorld().getName())) return;
        if(event.getWhoClicked().getGameMode().equals(GameMode.CREATIVE)) return;

        System.out.println(1);
        event.setCancelled(true);
    }
}