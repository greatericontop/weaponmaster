package io.github.greatericontop.weaponmaster.utils;

import org.bukkit.EntityEffect;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class TrueDamageHelper {

    public static void dealTrueDamage(LivingEntity target, double amount) {
        double absorptionAmount = target.getAbsorptionAmount();
        double leftAmount = amount - absorptionAmount;
        if (leftAmount <= 0) {
            // all the damage was used on absorption hearts
            target.setAbsorptionAmount(absorptionAmount - amount);
        } else {
            target.setAbsorptionAmount(0.0);
            // damage left over for health
            double newHealth = target.getHealth() - leftAmount;
            if (newHealth <= 0.000_001) {
                target.setHealth(0.0);
                return;
            }
            target.setHealth(newHealth);
        }
        target.playEffect(EntityEffect.HURT);
        target.getWorld().playSound(target, Sound.ENTITY_PLAYER_HURT, SoundCategory.PLAYERS, 1.0F, 1.0F);
    }

}
