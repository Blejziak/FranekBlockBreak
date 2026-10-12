package dev.franek.blockbreak.listener;

import dev.franek.blockbreak.settings.BlockSettings;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

/**
 * Blokuje niszczenie blokow spoza listy "breakableBlocks" graczom bez uprawnienia op.
 */
public final class BlockBreakListener implements Listener {

    private final BlockSettings settings;

    public BlockBreakListener(BlockSettings settings) {
        this.settings = settings;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getPlayer().isOp()) {
            return;
        }
        if (!settings.options.breakableBlocks.contains(event.getBlock().getType())) {
            event.setCancelled(true);
        }
    }
}
