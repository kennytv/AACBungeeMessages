package eu.kennytv.aacbungeemessages.spigot.command;

import eu.kennytv.aacbungeemessages.spigot.AACBungeeMessagesSpigot;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public final class AACNotifyCommand implements CommandExecutor {
    private final AACBungeeMessagesSpigot plugin;

    public AACNotifyCommand(final AACBungeeMessagesSpigot plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command cmd, final String s, final String[] args) {
        if (!sender.hasPermission("aac.notify")) {
            sender.sendMessage("Unknown command. Type \"/help\" for help.");
            return true;
        }
        if (args.length == 0) return false;

        plugin.sendPluginMessage(getCompleteMessage(args));
        return true;
    }

    private String getCompleteMessage(final String[] args) {
        String message = "";
        for (final String arg : args) {
            if (arg.isEmpty()) continue;
            message += arg + " ";
        }

        return message;
    }
}
