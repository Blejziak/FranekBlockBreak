package dev.franek.blockbreak.settings;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.bukkit.Material;

/**
 * Ustawienia pluginu trzymane w config.json.
 */
public final class BlockSettings {

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .disableHtmlEscaping()
            .create();

    public Options options = new Options();

    /** Wczytuje config.json; gdy pliku brak lub jest uszkodzony, bierze wartosci domyslne. */
    public static BlockSettings load(Path file) {
        BlockSettings settings = null;
        if (Files.exists(file)) {
            try {
                String json = new String(Files.readAllBytes(file), StandardCharsets.UTF_8);
                settings = GSON.fromJson(json, BlockSettings.class);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (settings == null || settings.options == null || settings.options.breakableBlocks == null) {
            settings = new BlockSettings();
        }
        return settings;
    }

    public static void save(BlockSettings settings, Path file) {
        try {
            Files.createDirectories(file.getParent());
            Files.write(file, GSON.toJson(settings).getBytes(StandardCharsets.UTF_8));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final class Options {
        /** Bloki ktore gracz bez uprawnienia op moze niszczyc. Wszystkie inne sa zablokowane. */
        public Set<Material> breakableBlocks = new HashSet<>(
                Arrays.asList(Material.DIAMOND_BLOCK, Material.DIAMOND_ORE));
    }
}
