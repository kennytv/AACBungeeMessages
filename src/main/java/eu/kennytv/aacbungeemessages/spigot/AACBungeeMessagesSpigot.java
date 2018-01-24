package eu.kennytv.aacbungeemessages.spigot;

import com.google.common.collect.Iterables;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import eu.kennytv.aacbungeemessages.spigot.command.AACNotifyCommand;
import eu.kennytv.aacbungeemessages.spigot.listener.SpigotMessagingListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class AACBungeeMessagesSpigot extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Plugin by KennyTV");
        if (!getDescription().getAuthors().contains("KennyTV"))
            getServer().getPluginManager().disablePlugin(this);

        getCommand("aacnotify").setExecutor(new AACNotifyCommand(this));
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getMessenger().registerIncomingPluginChannel(this, "Return", new SpigotMessagingListener());
    }

    public void sendPluginMessage(final String message) {
        final ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("AACBungeeMessages:Notification");
        out.writeUTF(message);
        Iterables.getFirst(getServer().getOnlinePlayers(), null).sendPluginMessage(this, "BungeeCord", out.toByteArray());
    }
}
