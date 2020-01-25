package tk.t11e.gungame.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import tk.t11e.gungame.commands.GunGame;
import tk.t11e.gungame.listener.DamageListener;
import tk.t11e.gungame.listener.InventoryListener;
import tk.t11e.gungame.listener.JoinLeaveListener;
import tk.t11e.gungame.listener.MoveListener;

import java.util.Objects;

public class Main extends JavaPlugin {

    public static final String PREFIX = "§7[§bT11E§7]§c ", NO_PERMISSION = PREFIX + "You don't have " +
            "the permissions for this!";

    @Override
    public void onEnable() {
        long milliseconds = System.currentTimeMillis();
        
        saveDefaultConfig();
        initCommands();
        initListener(Bukkit.getPluginManager());

        milliseconds = System.currentTimeMillis() - milliseconds;
        System.out.println("[GunGame] It took " + milliseconds + "ms to initialize this plugin!");
    }

    private void initListener(PluginManager pluginManager) {
        pluginManager.registerEvents(new DamageListener(), this);
        pluginManager.registerEvents(new InventoryListener(), this);
        pluginManager.registerEvents(new MoveListener(), this);
        pluginManager.registerEvents(new JoinLeaveListener(), this);
    }

    private void initCommands() {
        Objects.requireNonNull(getCommand("gunGame")).setExecutor(new GunGame());
    }
}