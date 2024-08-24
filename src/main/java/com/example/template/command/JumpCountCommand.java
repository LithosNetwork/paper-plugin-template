package com.example.template.command;

import com.example.template.Main;
import com.example.template.effect.JumpNotifier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class JumpCountCommand implements CommandExecutor {
    private final JumpNotifier notifier;

    public JumpCountCommand(Main main, JumpNotifier notifier) {
        this.notifier = notifier; // used to access the counter
        main.parseCommand(this, "jump-count"); // defined in plugin.yml! is a must!
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender,
                             @NotNull Command command,
                             @NotNull String label,
                             @NotNull String[] args) {
        if (!(sender instanceof Player player)) { // if sender is not a player
            sender.sendMessage("You must be a player to get your total jump count.");
            return true;
        }

        // get the total count from the JumpNotifier class
        var total = notifier.getJumpCount(player.getUniqueId());
        sender.sendMessage("You have a total of %s jump%s!".formatted(
            total,
            total == 1? "" : "s" // you don't want '1 jumps'
        ));

        return true;
    }
}
