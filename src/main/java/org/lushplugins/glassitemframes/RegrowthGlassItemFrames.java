package org.lushplugins.glassitemframes;

import org.lushplugins.glassitemframes.command.GlassItemFramesCommand;
import org.lushplugins.glassitemframes.config.ConfigManager;
import org.lushplugins.glassitemframes.listener.ItemFrameListener;
import org.lushplugins.glassitemframes.listener.PlayerListener;
import org.lushplugins.glassitemframes.viewer.InvisibleItemFrameViewer;
import org.lushplugins.lushlib.utils.plugin.SpigotPlugin;
import revxrsal.commands.bukkit.BukkitLamp;

public final class RegrowthGlassItemFrames extends SpigotPlugin {
    private static RegrowthGlassItemFrames plugin;


    private ConfigManager configManager;
    private InvisibleItemFrameViewer viewer;

    @Override
    public void onLoad() {
        plugin = this;
    }

    @Override
    public void onEnable() {
        this.configManager = new ConfigManager();
        configManager.reload();

        this.viewer = new InvisibleItemFrameViewer(this);

        registerListeners(
            new ItemFrameListener(),
            new PlayerListener()
        );

        BukkitLamp.builder(this)
            .build()
            .register(new GlassItemFramesCommand());
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public InvisibleItemFrameViewer getViewer() {
        return viewer;
    }

    public static RegrowthGlassItemFrames getInstance() {
        return plugin;
    }
}
