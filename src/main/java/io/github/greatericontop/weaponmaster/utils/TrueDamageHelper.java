package io.github.greatericontop.weaponmaster.utils;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class TrueDamageHelper {

    public static void dealTrueDamage(LivingEntity target, double amount, Player debugPlayer) {
        double absorptionAmount = target.getAbsorptionAmount();
        double leftAmount = amount - absorptionAmount;
        if (leftAmount <= 0) {
            // all the damage was used on absorption hearts
            target.setAbsorptionAmount(absorptionAmount - amount);
        } else {
            // damage left over for health
            double newHealth = target.getHealth() - leftAmount;
            if (newHealth <= 0.000_001) {
                debugPlayer.sendMessage("§7[Debug] dealing a million damage!");
                target.damage(1_000_000.0);
                return;
            }
            target.setHealth(newHealth);
        }
        target.damage(0.000_001);
    }

}
