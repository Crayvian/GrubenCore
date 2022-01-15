package com.rubygenix.GrubenCore;

import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;
import com.rubygenix.GrubenCore.Events.RankUpCheck;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Main extends JavaPlugin {
    public static Main I;
    public Server server;
    public ConsoleCommandSender console;
    public PluginManager plugMan;
    public RegisteredServiceProvider<Permission> rsp;
    public Permission perms;


    public static com.rubygenix.GrubenCore.Settings.Main settingsData = new com.rubygenix.GrubenCore.Settings.Main();
    public Toml tomlData;
    public File settingsFile = new File(getDataFolder(), "settings.toml");


    @Override
    public void onLoad() {
        I = this;
    }

    @Override
    public void onEnable() {
        server = I.getServer();
        console = server.getConsoleSender();
        plugMan = server.getPluginManager();
        rsp = server.getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();

        plugMan.registerEvents(new RankUpCheck(), I);
        //plugMan.registerEvents(new AFKCounter(), I);
        //plugMan.registerEvents(new SpawnerCheck(), I);
        //plugMan.registerEvents(new BlockTreasureCheck(), I);
        //plugMan.registerEvents(new DeathPenalty(), I);

        saveConfig();
    }

    @Override
    public void onDisable() {
        saveConfig();
    }

    @Override
    public void reloadConfig() {
        saveConfig();
    }

    @Override
    public void saveConfig() {
        saveSettings();
    }

    @Override
    public void saveDefaultConfig() {
        if (settingsFile.exists()){
            settingsFile.delete();
        }
        saveConfig();
    }

    private void saveSettings(){
        if (!settingsFile.exists()){
            settingsData = new com.rubygenix.GrubenCore.Settings.Main();
            tomlData = new Toml();
            settingsFile.getParentFile().mkdirs();
            try {
                settingsFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            tomlData = new Toml().read(settingsFile);
            settingsData = tomlData.to(com.rubygenix.GrubenCore.Settings.Main.class);
        }

        TomlWriter writer = new TomlWriter();

        try {
            writer.write(settingsData, settingsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
