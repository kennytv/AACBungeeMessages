package eu.kennytv.aacbungeemessages.bungee;

import eu.kennytv.aacbungeemessages.bungee.listener.MessagingListener;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class AACBungeeMessages extends Plugin {

    @Override
    public void onEnable() {
        getLogger().info("Plugin by KennyTV");
        if (!getDescription().getAuthor().equals("KennyTV")) return;

        getProxy().getPluginManager().registerListener(this, new MessagingListener(this));
    }

    public void sendToBukkit(final String message) {
        final ByteArrayOutputStream stream = new ByteArrayOutputStream();
        final DataOutputStream out = new DataOutputStream(stream);
        try {
            out.writeUTF("AACBungeeMessages:Notification");
            out.writeUTF(message);
        } catch (final IOException e) {
            e.printStackTrace();
        }

        getProxy().getServers().values().forEach(server -> server.sendData("Return", stream.toByteArray()));
    }
}
