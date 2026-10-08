package gudejunge.mermaid;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

public final class Mermaid extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Mermaid has been loaded!");
        BukkitScheduler scheduler = this.getServer().getScheduler();
        scheduler.runTaskTimer(this, () -> {
            for (Player player : getServer().getOnlinePlayers()) {

                boolean isInWater = player.isInWater();

                ItemStack mainHand = player.getInventory().getItemInMainHand();
                ItemStack offHand = player.getInventory().getItemInOffHand();

                boolean isHoldingHeartOfTheSea = mainHand.getType() == Material.HEART_OF_THE_SEA
                        || offHand.getType() == Material.HEART_OF_THE_SEA;

                if (isInWater && isHoldingHeartOfTheSea) {

                    PotionEffect currentEffect = player.getPotionEffect(PotionEffectType.DOLPHINS_GRACE);

                    if (currentEffect == null || currentEffect.getDuration() <= 30) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 200, 0, true, true, true));
                    }
                }
            }
        }, 0L, 10L);
    }

    @Override
    public void onDisable() {
        getLogger().info("Mermaid has been disabled!");
    }
}
