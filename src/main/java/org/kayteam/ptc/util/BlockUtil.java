package org.kayteam.ptc.util;

import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Random;

public class BlockUtil {

    public static ItemStack getBlockDrop(Block block, ItemStack usedItem){
        Enchantment enchantment = null;
        if(usedItem.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)){
            enchantment = Enchantment.LOOT_BONUS_BLOCKS;
        }
        if(usedItem.containsEnchantment(Enchantment.SILK_TOUCH)){
            enchantment = Enchantment.SILK_TOUCH;
        }
        int enchantmentLevel = usedItem.getEnchantmentLevel(enchantment);
        if (Enchantment.SILK_TOUCH.equals(enchantment)) {
            return new ItemStack(block.getType());
        } else if (Enchantment.LOOT_BONUS_BLOCKS.equals(enchantment)) {
            ItemStack drop = new ItemStack(new ArrayList<>(block.getDrops()).get(0));
            Random random = new Random();
            switch (block.getType()) {
                case COAL_ORE:
                case DIAMOND_ORE:
                case EMERALD_ORE: {
                    drop.setAmount(random.nextInt(1 + enchantmentLevel) + 1);
                    break;
                }
                case LAPIS_ORE: {
                    switch (enchantmentLevel) {
                        case 1: {
                            drop.setAmount(random.nextInt(15) + 4);
                        }
                        case 2: {
                            drop.setAmount(random.nextInt(24) + 4);
                        }
                        case 3: {
                            drop.setAmount(random.nextInt(33) + 4);
                        }
                    }
                    break;
                }
                case REDSTONE_ORE: {
                    switch (enchantmentLevel) {
                        case 1: {
                            drop.setAmount(random.nextInt(3) + 4);
                        }
                        case 2: {
                            drop.setAmount(random.nextInt(4) + 4);
                        }
                        case 3: {
                            drop.setAmount(random.nextInt(5) + 4);
                        }
                    }
                    break;
                }
            }
            return drop;
        }
        return new ArrayList<>(block.getDrops()).get(0);
    }
}
