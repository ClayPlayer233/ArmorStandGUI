package me.clayplayer.armorstandgui.commands;

import me.clayplayer.armorstandgui.ArmorStandGUI;
import me.clayplayer.armorstandgui.Utils.GuiUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class ArmorStandCommand implements CommandExecutor {

    private final ArmorStandGUI plugin;
    private final GuiUtils utils;

    public ArmorStandCommand(ArmorStandGUI plugin) {
        this.plugin = plugin;
        this.utils = new GuiUtils(plugin);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0){
            if (sender instanceof Player p){
                utils.ArmorStandGUI(p);
            }
        } else if (args.length == 1) {
            if (args[0].equals("reload")){
                plugin.reloadConfig();
                if (sender instanceof Player p){
                    p.sendMessage(ChatColor.GREEN+"成功重载配置文件");
                }else{
                    plugin.getLogger().info("成功重载配置文件");
                }
            }
        }
        return true;
    }
}
