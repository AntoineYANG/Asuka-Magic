package com.asukamagic.item.books;

import com.asukamagic.item.staves.StaveItem;
import com.asukamagic.mana.ManaApi;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MagicBookItem extends Item {

    public MagicBookItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (hand != InteractionHand.MAIN_HAND) {
            return InteractionResult.PASS;
        }
        
        ItemStack stack = player.getItemInHand(hand);
        ItemStack offhandStack = player.getItemInHand(InteractionHand.OFF_HAND);

        if (!level.isClientSide() && player instanceof ServerPlayer sp) {
            sp.setItemInHand(hand, offhandStack);
			sp.setItemInHand(InteractionHand.OFF_HAND, stack);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }

    public InteractionResult castSpell(Level level, ServerPlayer player) {
        ItemStack stack = player.getItemInHand(InteractionHand.MAIN_HAND);
        ItemStack offhandStack = player.getItemInHand(InteractionHand.OFF_HAND);

        if (!(offhandStack.getItem() instanceof MagicBookItem)) {
            return InteractionResult.PASS;
        }

        if (!level.isClientSide()) {
            if (stack.getItem() instanceof StaveItem) {
                if (ManaApi.tryConsume(player, 10)) {
                    player.getCooldowns().addCooldown(offhandStack, 20);
            
                    player.sendSystemMessage(
                        Component.literal("[AsukaMagic] Right click with wand! " + ManaApi.get(player).mana() + "/" + ManaApi.get(player).maxMana())
                            .withStyle(ChatFormatting.AQUA)
                    );
            
                    return InteractionResult.SUCCESS;
                } else {
                    player.sendSystemMessage(
                        Component.literal("[AsukaMagic] Not enough mana!")
                            .withStyle(ChatFormatting.RED)
                    );
                }
            } else {
                player.sendSystemMessage(
                    Component.literal("[AsukaMagic] Hold a magic book in offhand!")
                        .withStyle(ChatFormatting.RED)
                );
            }
        }

        return InteractionResult.PASS;
    }

}

