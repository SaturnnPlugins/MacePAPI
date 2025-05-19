package krezen.macePAPI;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class MacePAPI extends JavaPlugin {

    private String maceHolder = "None";

    @Override
    public void onEnable() {
        new MacePlaceholder().register();

        new BukkitRunnable() {
            @Override
            public void run() {
                maceHolder = "None";
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (hasMace(player)) {
                        maceHolder = player.getName();
                        break;
                    }
                }
            }
        }.runTaskTimer(this, 0L, 1L); // check every tick
    }

    public boolean hasMace(Player player) {
        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && item.getType() == Material.MACE) {
                return true;
            }
        }
        return false;
    }

    public String getMaceHolder() {
        return maceHolder;
    }

    class MacePlaceholder extends PlaceholderExpansion {

        @Override
        public String getIdentifier() {
            return "maceholder";
        }

        @Override
        public String getAuthor() {
            return "YourName";
        }

        @Override
        public String getVersion() {
            return "1.0";
        }

        @Override
        public String onPlaceholderRequest(Player player, String identifier) {
            if (identifier.equalsIgnoreCase("player")) {
                return getMaceHolder();
            }
            return null;
        }
    }
}
