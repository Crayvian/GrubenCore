package de.rubingrube.GrubenCore;

import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;
import de.rubingrube.GrubenCore.Events.BlockTreasureCheck;
import de.rubingrube.GrubenCore.Events.DeathPenalty;
import de.rubingrube.GrubenCore.Events.DirtPathSpeedUp;
import net.coreprotect.CoreProtect;
import net.coreprotect.CoreProtectAPI;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.Plugin;
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
    public RegisteredServiceProvider<Permission> rspPerm;
    public RegisteredServiceProvider<Economy> rspEcon;
    public Permission perms;
    public Economy econ;
    public CoreProtectAPI coAPI;

    public static de.rubingrube.GrubenCore.Settings.Main settingsData = new de.rubingrube.GrubenCore.Settings.Main();
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
        rspPerm = server.getServicesManager().getRegistration(Permission.class);
        rspEcon = server.getServicesManager().getRegistration(Economy.class);
        perms = rspPerm.getProvider();
        econ = rspEcon.getProvider();
        coAPI = getCoreProtect();

        I.getCommand("grube").setExecutor(new CommandMan());
        console.sendMessage("Befehle wurden registriert!");

        //plugMan.registerEvents(new RankUpCheck(), I);
        plugMan.registerEvents(new DeathPenalty(), I);
        plugMan.registerEvents(new BlockTreasureCheck(), I);
        plugMan.registerEvents(new DirtPathSpeedUp(), I);
        console.sendMessage("Events wurden registriert!");

        saveConfig();
        console.sendMessage("Die Konfiguration wurde geladen!");
    }

    @Override
    public void onDisable() {
        saveConfig();
        console.sendMessage("Die Konfiguration wurde gespeichert!");
    }

    @Override
    public void reloadConfig() {
        saveConfig();
        console.sendMessage("Die Konfiguration wurde neu geladen!");
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
            settingsData = new de.rubingrube.GrubenCore.Settings.Main();
            tomlData = new Toml();
            settingsFile.getParentFile().mkdirs();
            try {
                settingsFile.createNewFile();
                console.sendMessage("Erfolgreich eine neue Konfiguration erstellt!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            tomlData = new Toml().read(settingsFile);
            settingsData = tomlData.to(de.rubingrube.GrubenCore.Settings.Main.class);
            console.sendMessage("Erfolgreich die bestehende Konfiguration geladen!");
        }

        TomlWriter writer = new TomlWriter();

        try {
            writer.write(settingsData, settingsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private CoreProtectAPI getCoreProtect() {
        Plugin plugin = getServer().getPluginManager().getPlugin("CoreProtect");

        // Check that CoreProtect is loaded
        if (plugin == null || !(plugin instanceof CoreProtect)) {
            return null;
        }

        // Check that the API is enabled
        CoreProtectAPI CoreProtect = ((CoreProtect) plugin).getAPI();
        if (CoreProtect.isEnabled() == false) {
            return null;
        }

        // Check that a compatible version of the API is loaded
        if (CoreProtect.APIVersion() < 7) {
            return null;
        }

        return CoreProtect;
    }
}
