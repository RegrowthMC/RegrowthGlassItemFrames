package org.lushplugins.regrowthglassitemframes;

import org.bukkit.plugin.java.JavaPlugin;

public final class RegrowthGlassItemFrames extends JavaPlugin {
    private static RegrowthGlassItemFrames plugin;

    @Override
    public void onLoad() {
        plugin = this;
    }

    @Override
    public void onEnable() {
        // Enable implementation
    }

    @Override
    public void onDisable() {
        // Disable implementation
    }

    public static RegrowthGlassItemFrames getInstance() {
        return plugin;
    }
}
