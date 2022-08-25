# WeaponMaster


[![GitHub contributors](https://img.shields.io/github/contributors/greatericontop/weaponmaster?style=for-the-badge)](https://github.com/greatericontop/weaponmaster/graphs/contributors)
[![GitHub repo size](https://img.shields.io/github/repo-size/greatericontop/weaponmaster?style=for-the-badge)](https://github.com/greatericontop/weaponmaster)
[![GitHub release (latest by date)](https://img.shields.io/github/v/release/greatericontop/weaponmaster?style=for-the-badge)](https://github.com/greatericontop/weaponmaster/releases)
[![GitHub issues](https://img.shields.io/github/issues/greatericontop/weaponmaster?style=for-the-badge)](https://github.com/greatericontop/weaponmaster/issues)
[![GitHub](https://img.shields.io/github/license/greatericontop/weaponmaster?style=for-the-badge)](https://github.com/greatericontop/weaponmaster/blob/main/LICENSE)


**WeaponMaster** is a minecraft server plugin made using Spigot and Paper that adds weapons, tools, and armor items to the game. This plugin runs on any Spigot or Paper server running Minecraft 1.18+.


---


- [Installation](#installation)
- [Bulid from Source](#build-from-source)
- [Weapons](#weapons)
- [Commands](#commands)
- [License](#license)


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

```
git clone https://github.com/greatericontop/weaponmaster.git
cd weaponmaster
mvn package
```

The jar file will be built into the `./target` directory of the folder.


<br/>

---

<br/>

# Weapons

[Detailed documentation of the Weapons](WEAPONS.md)


<br/>

---

<br/>


# Commands

## Attribute Modifier

`/weaponmaster attributemodifier <attribute> <operation> <amount> <slot> [<optional uuid>]`

Modifies the item's attributes.

**Arguments**
- **attribute** ≫ Any valid value from the [`Attribute`](https://papermc.io/javadocs/paper/1.18/org/bukkit/attribute/Attribute.html) enum. These are just capitalized java style versions of the normal attribute names.
- **operation** ≫ Any valid value from the [`Operation`](https://papermc.io/javadocs/paper/1.18/org/bukkit/attribute/AttributeModifier.Operation.html) enum. Vanilla equivalents are `ADD_NUMBER = 0`, `ADD_SCALAR = 1`, `MULTIPLY_SCALAR_1 = 2`
- **amount** ≫ any valid double
- **slot** ≫ any valid value from the [`EquipmentSlot`](https://papermc.io/javadocs/paper/1.18/org/bukkit/inventory/EquipmentSlot.html) enum
- **optional uuid** ≫ any uuid (using one that is taken may result in weird behavior), or leave blank to randomly generate a new one

## Force Enchant

`/weaponmaster forceenchant <enchantment> <level>`

Forcefully echants items with enchantments. Allows you to add incompatible or ridiculously high level enchantments to an item.

**Arguments**
- **enchantment** ≫ enchantment with namespaced ID, e.g. `fire_protection`, `sharpness`
- **level** ≫ any integer or `"max" -> 255`, enchants are capped to level 255

## Illegal Stack

`/weaponmaster illegalstack <amount>`

Sets the item in hand to any amount.

**Arguments**
- **amount** ≫ Any signed byte `-128..127`, nonpositive values will delete the item.

Note: Be careful when moving illegally stacked items around. For now, using the hotkey to move them between the offhand and shift clicking will preserve stacks of up to 64 (items stacked to 127 can only be moved to the offhand). Also don't put too many illegally stacked items into shulker boxes, since I banned myself once because of that. Stacking shulker boxes should be ok.


<br/>

---

<br/>


# License

This project is licensed under the terms and conditions of GPL v3.
