# FEATURES

WeaponMaster adds many more features other than new weapons and items. This is the official wiki for the new WeaponMaster features.

<br/>

---

<br/>

# WeaponMaster Dragon

WeaponMaster Dragon is a dragon that has special abilities. 

Difficulty Level: 6/5. Don't expect to beat this without custom gear.

It has 1,000 health. It regenerates health with end crystals and naturally (as high as 1 health per 3 seconds when the dragon is low).

## Summoning

In addition to the four crystals, you must place 3 additional crystals within the 3x3 box around the fountain.

For example, you could do this:

![Example](assets/dragon/summon_example1.png)

Or this:

![Example](assets/dragon/summon_example2_side1.png)

![Example](assets/dragon/summon_example2_side2.png)

![Example](assets/dragon/summon_example2_side3.png)

All you need are 3 crystals that are in the 3x3 box, as shown here.

![Box](assets/dragon/summon_example2_top.png)

## Weight

Weight represents how much work you've done during the fight. Up to 1,375 weight can be obtained per fight, which is used to determine loot.

| **Action**    | Weight | Maximum |
|---------------|--------|---------|
| End Crystal   | 30     | 300     |
| Damage Dragon | 1\*    | 1000    |
| Damager Bonus | 75\*\* | 75      |

\* You get 1 weight for every 1 HP damage to the dragon, but your weight can decrease if the total damage is more (since only 1,000 weight can be given). For example, if you dealt 700 damage, but there was a total damage of 1400, you would only receive 500 weight, instead of 700.

\*\* Only one player can get the damager bonus if they have dealt at least 200 damage and are at least 30 damage above 2nd place.

## Attacks

### Hive Anger

The Dragon angers endermen to attack nearby players.

### Call Help

Summons an End Guard that gets stronger over time. Kill it before it's too late. 

### Lightning Attack

Summons lightning on nearby players that does true damage (unavoidable damage). If you're unlucky, it can deal up to 9 hearts of damage.

### Fireball Storm

The Dragon shoots tons of dragon fireballs at the ground around.

### Toxic Storm

Effects all the players around with `Weakness I`, `Posion I`, `Hunger I`, and `Mining Fatigue I` for 10 seconds.

### Summon Ghosts

Summons Ghostly Phantoms to ruin your day.

### Summon Sniper

Summons a skilled skeleton sniper to assassinate players. 

### Call Agents

Summons highly skilled and trained agents to go after nearby players.

## Loot

The loot that the dragon drops are player specific. It cannot be picked up by another player. These items do not despawn and cannot be blown up or burned.

### Dragon Horn

This is the strongest and rarest item. These are used make powerful dragon items, like `Dragon Armor` and `Dragon Elytra`.

At least `600` weight is required for this item to drop.

### Dragon Scale

This rare item is used to upgrade dragon items to improve their stats permanently, like `Dragon Sword` and `Dragon Elytra`.

At least `550` weight is required for this item to drop.

### Dragon Wing

This rare item is used to craft many dragon items. You need wings to craft items like `Dragon Armor` and `Dragon Elytra`.

At least `400` weight is required for this item to drop.


### Minor Loot

Some less important loot like shulker shells, ender pearls, and obsidian blocks are also drops.


<br/>

---

<br/>

# Dragon's Descent

Dragon's Descent is a late game upgrade system that upgrades your stats.

`/dragon-descent` - opens the `Dragon's Descent` GUI menu.

![A fully maxed out Dragon's Descent](assets/descent/max.png)

<br/>

---

<br/>

# Admin Commands

## Add Potion Effect

`/weaponmaster addpotioneffect <effect type> <duration> <amplifier>`

Adds a custom potion effect to any item that supports it.  
You can add these effects to items like potions, splash potions, lingering potions, and tipped arrows.

**Arguments**

- **effect type** ≫ potion effect type with namespaced ID, e.g. `strength`, `instant_damage`
- **duration** ≫ duration of the effect in seconds, e.g. `3.5` for 3.5 seconds or 70 ticks
- **amplifier** ≫ amplifier (potency) of the effect, e.g. `1` for level 2

Example: `/weaponmaster addpotioneffect strength 180 4` adds a potion effect of strength 5 for 3 minutes.

## Attribute Modifier

`/weaponmaster attributemodifier <attribute> <operation> <amount> <slot> [<optional uuid>]`

Modifies the item's attributes.

**Arguments**

- **attribute** ≫ Any valid value from the [`Attribute`](https://papermc.io/javadocs/paper/1.18/org/bukkit/attribute/Attribute.html) enum. These are just capitalized java style versions of the normal attribute names (like `GENERIC_ATTACK_DAMAGE` or `GENERIC_MAX_HEALTH` for example).
- **operation** ≫ Any valid value from the [`Operation`](https://papermc.io/javadocs/paper/1.18/org/bukkit/attribute/AttributeModifier.Operation.html) enum. Vanilla equivalents are `ADD_NUMBER = 0`, `ADD_SCALAR = 1`, `MULTIPLY_SCALAR_1 = 2`
- **amount** ≫ any valid double
- **slot** ≫ any valid value from the [`EquipmentSlot`](https://papermc.io/javadocs/paper/1.18/org/bukkit/inventory/EquipmentSlot.html) enum
- **optional uuid** ≫ any uuid (using one that is taken may result in weird behavior), or leave blank to randomly generate a new one

Example: `/weaponmaster attributemodifier GENERIC_ATTACK_DAMAGE ADD_NUMBER 5.0 HAND` adds 5 attack damage when the item is in the main hand.

## Force Enchant

`/weaponmaster forceenchant <enchantment> <level>`

Forcefully enchants items with enchantments. Allows you to add incompatible or ridiculously high level enchantments to an item.

**Arguments**
- **enchantment** ≫ enchantment with namespaced ID, e.g. `fire_protection`, `sharpness`
- **level** ≫ any integer or `"max" -> 255`, enchants are capped to level 255

Example: `/weaponmaster forceenchant protection 10` adds protection 10.

## Illegal Stack

`/weaponmaster illegalstack <amount>`

Sets the item in hand to any amount.

**Arguments**

- **amount** ≫ Any signed byte `-128..127`, nonpositive values will delete the item.

Example: `/weaponmaster illegalstack 64` sets the item in hand to 64 (even if it usually can't stack that high).

Be careful when moving illegally stacked items around. For now, using the hotkey to move them between the offhand and shift clicking will preserve stacks of up to 64 (items stacked to 127 can only be moved to the offhand). Also don't put too many illegally stacked items into shulker boxes, since I banned myself once because of that. Stacking shulker boxes should be ok.
