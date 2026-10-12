package dev.franek.blockbreak;

import dev.franek.blockbreak.listener.BlockBreakListener;
import dev.franek.blockbreak.settings.BlockSettings;
import java.nio.file.Path;

/**
 * FranekBlockBreak - ograniczenie niszczenia blokow dla graczy bez op.
 *
 * Plugin made by franek_ - https://github.com/Blejziak
 */
public final class FranekBlockBreak extends org.bukkit.plugin.java.JavaPlugin {

    @Override
    public void onEnable() {
        Path file = getDataFolder().toPath().resolve("config.json");
        BlockSettings settings = BlockSettings.load(file);
        BlockSettings.save(settings, file);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(settings), this);
    }
}
