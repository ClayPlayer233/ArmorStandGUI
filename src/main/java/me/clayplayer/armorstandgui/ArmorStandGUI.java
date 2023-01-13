package me.clayplayer.armorstandgui;

import me.clayplayer.armorstandgui.commands.ArmorStandCommand;
import me.clayplayer.armorstandgui.listeners.MenuHandler;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class ArmorStandGUI extends JavaPlugin {

    public HashMap<Player, ArmorStand> armorStandHashMap;

    public boolean glow = false;

    public ArmorStandGUI(){
        this.armorStandHashMap = new HashMap<>();
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("armorstandgui").setExecutor(new ArmorStandCommand(this));
        getServer().getPluginManager().registerEvents(new MenuHandler(this),this);
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getLogger().info("插件已启动！");
    }


}
