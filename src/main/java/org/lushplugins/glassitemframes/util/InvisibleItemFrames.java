package org.lushplugins.glassitemframes.util;

import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.CustomModelData;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.lushplugins.glassitemframes.RegrowthGlassItemFrames;

import java.util.Collection;

public class InvisibleItemFrames {
    private static final ItemStack GLASS_ITEM_FRAME;

    static {
        RegrowthGlassItemFrames plugin = RegrowthGlassItemFrames.getInstance();

        ItemStack glassItemFrame = ItemStack.of(Material.ITEM_FRAME);
        glassItemFrame.setData(DataComponentTypes.CUSTOM_NAME, Component.text("Glass Item Frame")
            .decorationIfAbsent(TextDecoration.ITALIC, TextDecoration.State.FALSE));
        glassItemFrame.setData(DataComponentTypes.CUSTOM_MODEL_DATA, CustomModelData.customModelData()
            .addFloat(plugin.getConfigManager().getCustomModelData())
            .build());
        GLASS_ITEM_FRAME = glassItemFrame;
    }

    public static ItemStack createInvisibleItemFrame() {
        return GLASS_ITEM_FRAME.clone();
    }

    public static boolean isGlassItemFrame(ItemStack item) {
        if (item.getType() != Material.ITEM_FRAME) {
            return false;
        }

        CustomModelData customModelData = item.getData(DataComponentTypes.CUSTOM_MODEL_DATA);
        float invisibleItemFrameModelData = RegrowthGlassItemFrames.getInstance().getConfigManager().getCustomModelData();
        return customModelData != null && customModelData.floats().contains(invisibleItemFrameModelData);
    }

    public static boolean isInvisibleItemFrame(Entity entity) {
        return entity instanceof ItemFrame frame && !frame.isVisible();
    }

    public static Collection<ItemFrame> getNearbyInvisibleFrames(Player player, int radius) {
        return player.getWorld().getNearbyEntities(player.getLocation(), radius, radius, radius, InvisibleItemFrames::isInvisibleItemFrame).stream()
            .map(entity -> (ItemFrame) entity)
            .toList();
    }
}
