package com.asukamagic.mana;

import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = "asukamagic")
public final class ManaEvents {

    static int REGEN_TICKS = 10;

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        if (event.getEntity().level().isClientSide()) return;
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        if (player.level().getGameTime() % REGEN_TICKS != 0) return;

        ManaData cur = ManaApi.get(player);
        if (cur.mana() < cur.maxMana()) {
            ManaApi.set(player, cur.add(1));
        }

    }

}
