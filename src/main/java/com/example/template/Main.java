package com.example.template;

import com.example.template.command.JumpCountCommand;
import com.example.template.effect.JumpNotifier;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class Main
    extends JavaPlugin
{
    @Override
    public void onEnable ()
    {
        this.getLogger().log(Level.INFO, "Enabling!");

        var pluginManager = getServer().getPluginManager();

        this.jumpNotifier = new JumpNotifier();
        pluginManager.registerEvents(this.jumpNotifier, this); // register the events in the class

        var jumpCountCommand = new JumpCountCommand(this);
        jumpCountCommand.getCommand().setExecutor(jumpCountCommand); // register command
    }

    @Override
    public void onDisable ()
    {
        this.getLogger().log(Level.INFO, "Disabling!");
    }

    private JumpNotifier jumpNotifier;

    public JumpNotifier getJumpNotifier ()
    {
        return jumpNotifier;
    }
}
