package org.lushplugins.glassitemframes.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.lushplugins.glassitemframes.RegrowthGlassItemFrames;

public class ConfigManager {
    private int customModelData;

    public ConfigManager() {
        RegrowthGlassItemFrames.getInstance().saveDefaultConfig();
    }

    public void reload() {
        RegrowthGlassItemFrames.getInstance().reloadConfig();
        FileConfiguration config = RegrowthGlassItemFrames.getInstance().getConfig();

        this.customModelData = config.getInt("custom-model-data", 1);
    }

    public int getCustomModelData() {
        return customModelData;
    }
}
