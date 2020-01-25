package tk.t11e.gungame.manager;
// Created by booky10 in GunGame (20:04 24.01.20)


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import tk.t11e.gungame.main.Main;

import java.util.Objects;
import java.util.UUID;

@SuppressWarnings("RedundantIfStatement")
public class GunGameManager {

    private final static Main main = Main.getPlugin(Main.class);

    public static void respawn(Player player) {
        player.sendMessage(Main.PREFIX+"You died!");

        downgrade(player);
        player.setExp(0);
        player.teleport(getSpawn());
        player.setHealth(Objects.requireNonNull(player
                .getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue());
    }

    public static Material getItem(Integer level) {
        switch (level){
            case 0:
                return Material.WOODEN_AXE;
            case 2:
                return Material.WOODEN_SWORD;
            case 4:
                return Material.STONE_AXE;
            case 6:
                return Material.STONE_SWORD;
            case 8:
                return Material.GOLDEN_AXE;
            case 10:
                return Material.GOLDEN_SWORD;
            case 14:
                return Material.IRON_AXE;
            case 18:
                return Material.IRON_SWORD;
            case 28:
                return Material.DIAMOND_AXE;
            case 38:
                return Material.DIAMOND_SWORD;
            default:
                return Material.BARRIER;
        }
    }

    public static void updateItems(Integer level, Player player) {
        if(getItem(level).equals(Material.BARRIER)) return;

        ItemStack itemStack = new ItemStack(getItem(level));
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setUnbreakable(true);
        itemMeta.addItemFlags(ItemFlag.values());
        itemStack.setItemMeta(itemMeta);

        player.getInventory().setItem(0,itemStack);
    }

    public static void upgrade(Player player) {
        setLevel(player.getUniqueId(),getLevel(player.getUniqueId())+1);
        updateItems(getLevel(player.getUniqueId()),player);
    }

    public static void downgrade(Player player) {
        if (getLevel(player.getUniqueId()) > 0)
            setLevel(player.getUniqueId(), getLevel(player.getUniqueId()) - 1);
        updateItems(getLevel(player.getUniqueId()), player);
    }

    public static Boolean isLocationInSpawn(Location location) {
        double X1 = getLoc1().getX();
        double Y1 = getLoc1().getY();
        double Z1 = getLoc1().getZ();

        double X2 = getLoc2().getX();
        double Y2 = getLoc2().getY();
        double Z2 = getLoc2().getZ();

        if (location.getX() > X1 && location.getX() < X2)
            return true;
        if (location.getY() > Y1 && location.getY() < Y2)
            return true;
        if (location.getZ() > Z1 && location.getZ() < Z2)
            return true;
        return false;
    }

    public static void setLevel(UUID uuid, Integer level) {
        FileConfiguration config = main.getConfig();

        config.set("Level."+uuid.toString(),level);

        main.saveConfig();
    }

    public static Integer getLevel(UUID uuid) {
        FileConfiguration config = main.getConfig();

        return config.getInt("Level."+uuid.toString());
    }

    public static void setKills(UUID uuid, Integer kills) {
        FileConfiguration config = main.getConfig();

        config.set("Kills."+uuid.toString(),kills);

        main.saveConfig();
    }

    public static Integer getKills(UUID uuid) {
        FileConfiguration config = main.getConfig();

        return config.getInt("Kills."+uuid.toString());
    }

    public static void setDeaths(UUID uuid, Integer deaths) {
        FileConfiguration config = main.getConfig();

        config.set("Deaths."+uuid.toString(),deaths);

        main.saveConfig();
    }

    public static Integer getDeaths(UUID uuid) {
        FileConfiguration config = main.getConfig();

        return config.getInt("Deaths."+uuid.toString());
    }

    public static void setLoc1(Location location) {
        FileConfiguration config = main.getConfig();

        config.set("Locations.Loc1.X",location.getBlockX()+0.5);
        config.set("Locations.Loc1.Y",location.getBlockY()+0.5);
        config.set("Locations.Loc1.Z",location.getBlockZ()+0.5);

        main.saveConfig();
    }

    public static void setLoc2(Location location) {
        FileConfiguration config = main.getConfig();

        config.set("Locations.Loc2.X",location.getBlockX()+0.5);
        config.set("Locations.Loc2.Y",location.getBlockY()+0.5);
        config.set("Locations.Loc2.Z",location.getBlockZ()+0.5);

        main.saveConfig();
    }

    public static Location getLoc1() {
        FileConfiguration config = main.getConfig();

        double x = config.getDouble("Locations.Loc1.X");
        double y = config.getDouble("Locations.Loc1.Y");
        double z = config.getDouble("Locations.Loc1.Z");

        return new Location(getWorld(),x,y,z,0,0);
    }

    public static Location getLoc2() {
        FileConfiguration config = main.getConfig();

        double x = config.getDouble("Locations.Loc2.X");
        double y = config.getDouble("Locations.Loc2.Y");
        double z = config.getDouble("Locations.Loc2.Z");

        return new Location(getWorld(),x,y,z,0,0);
    }

    public static Location getSpawn() {
        FileConfiguration config = main.getConfig();

        double x = config.getDouble("Locations.Spawn.X");
        double y = config.getDouble("Locations.Spawn.Y");
        double z = config.getDouble("Locations.Spawn.Z");

        return new Location(getWorld(),x,y,z,0,0);
    }

    public static void setSpawn(Location location) {
        FileConfiguration config = main.getConfig();

        config.set("World",location.getWorld().getName());
        config.set("Locations.Spawn.X",location.getBlockX()+0.5);
        config.set("Locations.Spawn.Y",location.getBlockY()+0.5);
        config.set("Locations.Spawn.Z",location.getBlockZ()+0.5);

        main.saveConfig();
    }

    public static World getWorld() {
        FileConfiguration config = main.getConfig();

        return Bukkit.getWorld(Objects.requireNonNull(config.getString("World")));
    }
}