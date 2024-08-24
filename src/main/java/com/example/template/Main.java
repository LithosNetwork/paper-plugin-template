package com.example.template;

import com.example.template.command.JumpCountCommand;
import com.example.template.effect.JumpNotifier;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().log(Level.INFO, "Enabling!");

        // load the classes that we want to register
        var notifier = new JumpNotifier(this);
        new JumpCountCommand(this, notifier); // we can pass along the counter class
    }

    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "Disabling!");
    }

    public void parseEventListeners(Listener listener) {
        // register the events from the given class
        getServer().getPluginManager().registerEvents(listener, this);
    }

    public void parseCommand(CommandExecutor executor, String name) {
        PluginCommand command = getCommand(name);
        if (command == null) {
            return;
        }

        // register command by name
        command.setExecutor(executor);
    }
}
