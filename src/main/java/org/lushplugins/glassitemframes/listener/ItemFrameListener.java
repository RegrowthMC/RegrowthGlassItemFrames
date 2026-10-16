package org.lushplugins.glassitemframes.listener;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.World;
import org.bukkit.entity.Item;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.lushplugins.glassitemframes.RegrowthGlassItemFrames;
import org.lushplugins.glassitemframes.util.InvisibleItemFrames;

public class ItemFrameListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onHangingPlace(HangingPlaceEvent event) {
        if (event.getEntity() instanceof ItemFrame itemFrame) {
            ItemStack item = event.getItemStack();
            if (item != null && InvisibleItemFrames.isGlassItemFrame(item)) {
                itemFrame.setVisible(false);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onHangingBreak(HangingBreakEvent event) {
        if (!(event.getEntity() instanceof ItemFrame itemFrame) || itemFrame.isVisible()) {
            return;
        }

        event.setCancelled(true);

        World world = itemFrame.getWorld();
        Location dropLocation = itemFrame.getLocation().add(itemFrame.getFacing().getDirection().multiply(0.3));

        itemFrame.remove();
        world.playSound(dropLocation, Sound.ENTITY_ITEM_FRAME_BREAK, SoundCategory.BLOCKS, 1f, 1f);
        Item frameDrop = world.dropItem(dropLocation, InvisibleItemFrames.createInvisibleItemFrame());
        Item drop = world.dropItemNaturally(dropLocation, itemFrame.getItem());

        if (!RegrowthGlassItemFrames.getInstance().callEvent(new EntityDropItemEvent(itemFrame, frameDrop))) {
            frameDrop.remove();
        }

        if (!RegrowthGlassItemFrames.getInstance().callEvent(new EntityDropItemEvent(itemFrame, drop))) {
            drop.remove();
        }
    }
}
