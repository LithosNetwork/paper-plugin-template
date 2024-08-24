package com.example.template.effect;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import com.example.template.Main;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class JumpNotifier implements Listener {
    public JumpNotifier(Main main) {
        main.parseEventListeners(this); // register the events of this class
    }

    // in this global variable, we cache the total jumps a player has made
    private final Map<UUID, Integer> jumpCount = new ConcurrentHashMap<>();

    public int getJumpCount(UUID uuid) {
        // check if the player already has a cached jump count. if the player
        //  doesn't, it'll cache 0, and return it. if it has already cache
        //  for the player, it'll return that
        return this.jumpCount.computeIfAbsent(uuid, empty -> 0);
    }

    // since the message is always the same, we can already create the variable
    //  in the global scope, so it only creates it once, and can then be used
    private static final Component MESSAGE =
        Component.text("You jumped!")
            .decorate(TextDecoration.ITALIC)
            .color(NamedTextColor.DARK_AQUA);

    // listen to the player jump event, triggered when a player jumps
    @EventHandler
    public void onPlayerJumpEvent(PlayerJumpEvent event) {
        var player = event.getPlayer();
        player.sendActionBar(MESSAGE); // send an actionbar with the message
                                       //  we created earlier

        // increment the total jumps of the player
        var totalJumps = getJumpCount(player.getUniqueId()) + 1;
        this.jumpCount.put(
            player.getUniqueId(),
            totalJumps
        );
    }
}
