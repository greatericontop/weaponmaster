# WeaponMaster


[![GitHub contributors](https://img.shields.io/github/contributors/greatericontop/weaponmaster?style=for-the-badge)](https://github.com/greatericontop/weaponmaster/graphs/contributors)
[![GitHub repo size](https://img.shields.io/github/repo-size/greatericontop/weaponmaster?style=for-the-badge)](https://github.com/greatericontop/weaponmaster)
[![GitHub release (latest by date)](https://img.shields.io/github/v/release/greatericontop/weaponmaster?style=for-the-badge)](https://github.com/greatericontop/weaponmaster/releases)
[![GitHub issues](https://img.shields.io/github/issues/greatericontop/weaponmaster?style=for-the-badge)](https://github.com/greatericontop/weaponmaster/issues)
[![GitHub](https://img.shields.io/github/license/greatericontop/weaponmaster?style=for-the-badge)](https://github.com/greatericontop/weaponmaster/blob/main/LICENSE)


**WeaponMaster** is a minecraft server plugin that adds weapons, tools, and armor items to the game. It also adds lots of new gameplay features. This plugin runs on any Spigot (or Paper and its forks, which are required for some additional features) server running Minecraft 1.18. Earlier and later versions aren't tested but will likely work.

<br/>

---

<br/>


# Installation

To install the project, first download the `jar` file from the [releases](https://github.com/greatericontop/weaponmaster/releases) page. 

Paste the jar file into the server `./plugins` directory.

For better aesthetic, you can also install the [WeaponMasterOverlay](https://github.com/Gerseneck/weaponmasterresources) pack.


<br/>

---

<br/>

# Build from Source

We use Maven for compilation.

```
git clone https://github.com/greatericontop/weaponmaster.git
cd weaponmaster
mvn package
```

The jar file will be in `./target`.


<br/>

---

<br/>

# Features

[Detailed Documentation of the Items](WEAPONS.md)

[Detailed Documentation of Other Features](FEATURES.md)

<br/>

---

<br/>


# Commands

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


<br/>

---

<br/>


# License

This project is licensed under the terms and conditions of GPL v3.
