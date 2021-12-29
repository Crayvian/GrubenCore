package net.overcrave.GrubenCore;

import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;
import net.milkbowl.vault.permission.Permission;
import net.overcrave.GrubenCore.Events.*;
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

    //public Toml tomlData;
    //public File settingsFile = new File(getDataFolder(), "spawnerData.toml");
    public static net.overcrave.GrubenCore.Settings.Main settingsData = new net.overcrave.GrubenCore.Settings.Main();

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
    }
}
