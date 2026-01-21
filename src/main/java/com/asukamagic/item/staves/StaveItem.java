package com.asukamagic.item.staves;

import com.asukamagic.item.books.MagicBookItem;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class StaveItem extends Item {

    public StaveItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (hand != InteractionHand.MAIN_HAND) {
            return InteractionResult.PASS;
        }
        
        ItemStack stack = player.getItemInHand(hand);
        ItemStack offhandStack = player.getItemInHand(InteractionHand.OFF_HAND);

        // if (level.isClientSide) {
        //     return InteractionResultHolder.success(stack);
        // }

        // player.getCooldowns().addCooldown(this, 20);

        // // Snowball snowball = new Snowball(level, player);
        // // snowball.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
        // // level.addFreshEntity(snowball);

        // stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(hand));

        // return InteractionResultHolder.consume(stack);

        if (!level.isClientSide() && player instanceof ServerPlayer sp) {
            if (offhandStack.getItem() instanceof MagicBookItem book) {
                InteractionResult result = book.castSpell(level, sp);
                if (result != InteractionResult.SUCCESS) {
                    return result;
                }
                sp.getCooldowns().addCooldown(stack, 20);
            } else {
                sp.sendSystemMessage(
                    Component.literal("[AsukaMagic] Hold a magic book in offhand!")
                        .withStyle(ChatFormatting.RED)
                );
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }

}
