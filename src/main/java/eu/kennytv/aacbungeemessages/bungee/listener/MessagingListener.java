package eu.kennytv.aacbungeemessages.bungee.listener;

import eu.kennytv.aacbungeemessages.bungee.AACBungeeMessages;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public final class MessagingListener implements Listener {
    private final AACBungeeMessages plugin;

    public MessagingListener(final AACBungeeMessages plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void whitelistMessage(final PluginMessageEvent event) {
        if (!event.getTag().equals("BungeeCord")) return;

        final DataInputStream in = new DataInputStream(new ByteArrayInputStream(event.getData()));

        String channel = "";
        try {
            channel = in.readUTF();
        } catch (final IOException e) {
            e.printStackTrace();
        }

        if (!channel.equals("AACBungeeMessages:Notification")) return;

        String input = "";
        try {
            input = in.readUTF();
        } catch (final IOException e) {
            e.printStackTrace();
        }

        plugin.sendToBukkit(input);
    }
}
