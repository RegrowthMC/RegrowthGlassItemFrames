package org.lushplugins.glassitemframes.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.lushplugins.glassitemframes.RegrowthGlassItemFrames;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        RegrowthGlassItemFrames.getInstance().getViewer().removeViewer(event.getPlayer());
    }
}
