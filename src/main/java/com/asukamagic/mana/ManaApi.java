package com.asukamagic.mana;

import net.minecraft.world.entity.player.Player;

public final class ManaApi {

    private ManaApi() {}

    public static ManaData get(Player player) {
        return player.getData(ModAttachments.MANA);
    }

    public static void set(Player player, ManaData data) {
        player.setData(ModAttachments.MANA, data.clamp());
    }

    public static boolean tryConsume(Player player, int cost) {
        ManaData cur = get(player);
        if (!cur.canConsume(cost)) return false;
        set(player, cur.consume(cost));
        return true;
    }

    public static void add(Player player, int amount) {
        set(player, get(player).add(amount));
    }

    public static void setMax(Player player, int max) {
        set(player, get(player).setMax(max));
    }

}
