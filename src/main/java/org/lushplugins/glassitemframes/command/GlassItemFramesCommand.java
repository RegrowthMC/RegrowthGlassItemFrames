package org.lushplugins.glassitemframes.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.lushplugins.glassitemframes.RegrowthGlassItemFrames;
import org.lushplugins.glassitemframes.util.InvisibleItemFrames;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Default;
import revxrsal.commands.annotation.Subcommand;
import revxrsal.commands.bukkit.annotation.CommandPermission;

@SuppressWarnings("unused")
@Command("glassitemframes")
public class GlassItemFramesCommand {

    @Subcommand("give")
    @CommandPermission("glassitemframes.give")
    public void give(CommandSender sender, Player player, @Default("1") int amount) {
        ItemStack glassItemFrame = InvisibleItemFrames.createInvisibleItemFrame();
        glassItemFrame.setAmount(amount);

        player.give(glassItemFrame);
    }

    @Subcommand("reload")
    @CommandPermission("glassitemframes.reload")
    public void reload(CommandSender sender) {
        RegrowthGlassItemFrames.getInstance().getConfigManager().reload();

        sender.sendMessage(Component.text()
            .content("GlassItemFrames has been reloaded!")
            .color(TextColor.fromHexString("#b7faa2"))
            .build());
    }
}
