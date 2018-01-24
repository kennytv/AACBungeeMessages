package eu.kennytv.aacbungeemessages.bungee;

import eu.kennytv.aacbungeemessages.bungee.listener.MessagingListener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public final class AACBungeeMessages extends Plugin {

    @Override
    public void onEnable() {
        getLogger().info("Plugin by KennyTV");
        final PluginManager pm = getProxy().getPluginManager();
        if (!getDescription().getAuthor().equals("KennyTV")) return;

        pm.registerListener(this, new MessagingListener(this));
    }
}
