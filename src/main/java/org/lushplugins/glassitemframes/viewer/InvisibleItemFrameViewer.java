package org.lushplugins.glassitemframes.viewer;

import com.google.common.collect.HashMultimap;
import fr.skytasul.glowingentities.GlowingEntities;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.lushplugins.glassitemframes.RegrowthGlassItemFrames;
import org.lushplugins.glassitemframes.util.InvisibleItemFrames;

import java.util.logging.Level;

public class InvisibleItemFrameViewer {
    private final GlowingEntities glowingEntities;
    private final HashMultimap<Player, GlowingEntities.EntityIdentifier> glowingFrames;

    public InvisibleItemFrameViewer(RegrowthGlassItemFrames plugin) {
        this.glowingEntities = new GlowingEntities(plugin);
        this.glowingFrames = HashMultimap.create();
        Bukkit.getScheduler().runTaskTimer(plugin, this::updateViewers, 20, 20);
    }

    public void updateViewers() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            ItemStack mainHand = player.getInventory().getItemInMainHand();
            ItemStack offHand = player.getInventory().getItemInOffHand();

            if (InvisibleItemFrames.isGlassItemFrame(mainHand) || InvisibleItemFrames.isGlassItemFrame(offHand)) {
                for (ItemFrame frame : InvisibleItemFrames.getNearbyInvisibleFrames(player, 5)) {
                    try {
                        // TODO: Swap glowing for temporarily displaying the frame as visible to the player
                        glowingEntities.setGlowing(frame, player, ChatColor.WHITE);

                        GlowingEntities.EntityIdentifier entityId = new GlowingEntities.EntityidentifierRealEntity(frame);
                        glowingFrames.put(player, entityId);
                    } catch (ReflectiveOperationException e) {
                        RegrowthGlassItemFrames.getInstance().getLogger().log(Level.WARNING, "Something went wrong whilst making an item frame glow: ", e);
                    }
                }
            } else if (glowingFrames.containsKey(player)) {
                for (GlowingEntities.EntityIdentifier entityId : glowingFrames.removeAll(player)) {
                    try {
                        glowingEntities.unsetGlowing(entityId, player);
                    } catch (ReflectiveOperationException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public void removeViewer(Player player) {
        glowingFrames.removeAll(player);
    }
}
