package com.asukamagic.item.staves;

// import net.minecraft.world.InteractionHand;
// import net.minecraft.world.InteractionResultHolder;
// import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ShortStaffItem extends Item {

    public ShortStaffItem(Properties properties) {
        super(properties);
    }

    // @Override
    // public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
    //     ItemStack stack = player.getItemInHand(hand);

    //     // if (level.isClientSide) {
    //     //     return InteractionResultHolder.success(stack);
    //     // }

    //     // player.getCooldowns().addCooldown(this, 20);

    //     // // Snowball snowball = new Snowball(level, player);
    //     // // snowball.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
    //     // // level.addFreshEntity(snowball);

    //     // stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(hand));

    //     // return InteractionResultHolder.consume(stack);

    //     if (!level.isClientSide() && player instanceof ServerPlayer sp) {
    //         sp.sendSystemMessage(
    //             Component.literal("[AsukaMagic] Right click with wand!")
    //                 .withStyle(ChatFormatting.AQUA)
    //         );
    //     }

    //     return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    // }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }

}
