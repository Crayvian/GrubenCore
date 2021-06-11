package net.overcrave.GrubenCore;

import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;
import net.milkbowl.vault.permission.Permission;
import net.overcrave.GrubenCore.Events.AFKCounter;
import net.overcrave.GrubenCore.Events.RankUpCheck;
import net.overcrave.GrubenCore.Events.SpawnerCheck;
import org.bukkit.ChatColor;
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

    public Toml tomlSpawnerData;
    public File spawnerDataFile = new File(getDataFolder(), "spawnerData.toml");
    public static net.overcrave.GrubenCore.Settings.Main spawnerData = new net.overcrave.GrubenCore.Settings.Main();

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

        //TokenManager api = (TokenManager) Bukkit.getServer().getPluginManager().getPlugin("TokenManager");

        plugMan.registerEvents(new RankUpCheck(), I);
        plugMan.registerEvents(new AFKCounter(), I);
        //plugMan.registerEvents(new SpawnerCheck(), I);
        //plugMan.registerEvents(new BlockTreasureCheck(), I);
        //plugMan.registerEvents(new DeathPenalty(), I);

        //saveConfig();
    }

    @Override
    public void onDisable() {
        //saveConfig();
    }

    @Override
    public void reloadConfig() {
        //saveConfig();
    }

    @Override
    public void saveConfig() {
        //saveSettings();
    }

    @Override
    public void saveDefaultConfig() {
        //if (spawnerDataFile.exists()){
        //    spawnerDataFile.delete();
        //}
        //saveConfig();
    }

    private void saveSettings(){
        if (!spawnerDataFile.exists()){
            spawnerData = new net.overcrave.GrubenCore.Settings.Main();
            tomlSpawnerData = new Toml();
            spawnerDataFile.getParentFile().mkdirs();
            try {
                spawnerDataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            tomlSpawnerData = new Toml().read(spawnerDataFile);
            spawnerData = tomlSpawnerData.to(net.overcrave.GrubenCore.Settings.Main.class);
        }

        TomlWriter writer = new TomlWriter();

        try {
            writer.write(spawnerData, spawnerDataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
