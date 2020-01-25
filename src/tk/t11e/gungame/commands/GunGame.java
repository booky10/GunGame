package tk.t11e.gungame.commands;
// Created by booky10 in GunGame (19:50 24.01.20)

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import tk.t11e.gungame.main.Main;
import tk.t11e.gungame.manager.GunGameManager;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("NullableProblems")
public class GunGame implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("gunGame")) {
                if (args.length == 1)
                    if (args[0].equalsIgnoreCase("setLoc1")) {
                        GunGameManager.setLoc1(player.getLocation());
                        player.sendMessage(Main.PREFIX + "§aSuccessfully set location 1!");
                    } else if (args[0].equalsIgnoreCase("setLoc2")) {
                        GunGameManager.setLoc2(player.getLocation());
                        player.sendMessage(Main.PREFIX + "§aSuccessfully set location 2!");
                    } else if (args[0].equalsIgnoreCase("setSpawn")) {
                        GunGameManager.setSpawn(player.getLocation());
                        player.sendMessage(Main.PREFIX + "§aSuccessfully set spawn!");
                    } else
                        return false;
                else if (args.length == 2)
                    if (args[0].equalsIgnoreCase("upgrade")) {
                        Player target = Bukkit.getPlayer(args[1]);
                        if (target != null) {
                            GunGameManager.upgrade(target);
                            player.sendMessage(Main.PREFIX + "§aSuccessfully upgraded player!");
                        } else
                            player.sendMessage(Main.PREFIX + "Player not found!");
                    } else
                        return false;
                else
                    return false;
            } else
                player.sendMessage(Main.NO_PERMISSION);
        } else if (args.length == 2)
            if (args[0].equalsIgnoreCase("upgrade")) {
                Player target = Bukkit.getPlayer(args[1]);
                if (target != null) {
                    GunGameManager.upgrade(target);
                    sender.sendMessage("Successfully upgraded player!");
                } else
                    sender.sendMessage("Player not found!");
            } else
                return false;
        else
            return false;
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> list = new ArrayList<>();
        if (args.length == 1 && sender instanceof Player) {
            list.add("setLoc1");
            list.add("setLoc2");
            list.add("setSpawn");
            list.add("upgrade");
        } else if (args.length == 1)
            list.add("upgrade");
        return list;
    }
}