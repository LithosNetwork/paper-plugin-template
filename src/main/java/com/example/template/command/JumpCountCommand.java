package com.example.template.command;

import com.example.template.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class JumpCountCommand
    implements CommandExecutor
{
    private final Main main;
    private final PluginCommand command;

    public JumpCountCommand (Main main)
    {
        this.main = main; // we use the main class to access the class JumpNotifier, which
                          //  tracks our statistics
        this.command = main.getCommand("jump-count"); // defined in plugin.yml! is a must!
    }

    @Override
    public boolean onCommand (@NotNull CommandSender sender,
                              @NotNull Command command,
                              @NotNull String label,
                              @NotNull String[] args)
    {
        if (!(sender instanceof Player player)) // if sender is not a player
        {
            sender.sendMessage("You must be a player to get your total jump count.");
            return true;
        }

        // get the total count from the JumpNotifier class
        var total = this.main.getJumpNotifier().getJumpCount(player.getUniqueId());
        sender.sendMessage(
            "You have a total of %s jump%s!".formatted(
                total,
                total == 1? "" : "s" // you don't want '1 jumps'
            )
        );

        return true;
    }

    public PluginCommand getCommand ()
    {
        return command;
    }
}
