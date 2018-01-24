package eu.kennytv.aacbungeemessages.spigot.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public final class SpigotMessagingListener implements PluginMessageListener {

    @Override
    public void onPluginMessageReceived(final String s, final Player player, final byte[] bytes) {
        final DataInputStream in = new DataInputStream(new ByteArrayInputStream(bytes));
        try {
            final String channel = in.readUTF();
            if (!channel.equals("AACBungeeMessages:Notification")) return;

            final String message = in.readUTF();
            Bukkit.getOnlinePlayers().stream().filter(p -> p.hasPermission("aac.notify"))
                    .forEach(p -> p.sendMessage(message));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}