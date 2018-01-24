package eu.kennytv.aacbungeemessages.spigot;

import com.google.common.collect.Iterables;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import eu.kennytv.aacbungeemessages.spigot.command.AACNotifyCommand;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class AACBungeeMessagesSpigot extends JavaPlugin {
    private String prefix;

    @Override
    public void onEnable() {
        getLogger().info("Plugin by KennyTV");
        if (!getDescription().getAuthors().contains("KennyTV"))
            getServer().getPluginManager().disablePlugin(this);

        getCommand("aacnotifyspigot").setExecutor(new AACNotifyCommand(this));
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        saveDefaultConfig();
        prefix = ChatColor.translateAlternateColorCodes('&',
                YamlConfiguration.loadConfiguration(new File(getDataFolder(), "config.yml")).getString("prefix"));
    }

    public void sendPluginMessage(final String message) {
        final ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("AACBungeeMessages:Notification");
        out.writeUTF(message);
        Iterables.getFirst(getServer().getOnlinePlayers(), null).sendPluginMessage(this, "BungeeCord", out.toByteArray());
    }

    public String getPrefix() {
        return prefix;
    }
}
