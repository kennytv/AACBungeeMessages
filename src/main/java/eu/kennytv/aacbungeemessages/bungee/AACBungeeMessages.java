package eu.kennytv.aacbungeemessages.bungee;

import eu.kennytv.aacbungeemessages.bungee.listener.MessagingListener;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.*;
import java.nio.file.Files;

public final class AACBungeeMessages extends Plugin {
    private String prefix;

    @Override
    public void onEnable() {
        getLogger().info("Plugin by KennyTV");
        if (!getDescription().getAuthor().equals("KennyTV")) return;

        registerConfig();
        getProxy().getPluginManager().registerListener(this, new MessagingListener(this));
    }

    private void registerConfig() {
        if (!getDataFolder().exists())
            getDataFolder().mkdirs();

        final File file = new File(getDataFolder(), "config.yml");
        if (!file.exists()) {
            try (final InputStream in = getResourceAsStream("config.yml")) {
                Files.copy(in, file.toPath());
            } catch (final IOException e) {
                throw new RuntimeException("Unable to create config.yml file for AACBungeeMessages", e);
            }
        }

        try {
            prefix = net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', net.md_5.bungee.config.YamlConfiguration
                    .getProvider(net.md_5.bungee.config.YamlConfiguration.class)
                    .load(new InputStreamReader(new FileInputStream(file), "UTF8")).getString("prefix"));
        } catch (final FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getPrefix() {
        return prefix;
    }
}
