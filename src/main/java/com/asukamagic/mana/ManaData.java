package com.asukamagic.mana;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

public record ManaData(int mana, int maxMana) {

    public static final ManaData DEFAULT = new ManaData(0, 100);

    public static final Codec<ManaData> CODEC = RecordCodecBuilder.create(inst -> inst.group(
        Codec.INT.fieldOf("mana").forGetter(ManaData::mana),
        Codec.INT.fieldOf("max").forGetter(ManaData::maxMana)
    ).apply(inst, ManaData::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, ManaData> STREAM_CODEC = new StreamCodec<>() {
        @Override
        public ManaData decode(RegistryFriendlyByteBuf buf) {
            int mana = buf.readVarInt();
            int max = buf.readVarInt();
            return new ManaData(mana, max);
        }

        @Override
        public void encode(RegistryFriendlyByteBuf buf, ManaData value) {
            buf.writeVarInt(value.mana);
            buf.writeVarInt(value.maxMana);
        }
    };

    public ManaData clamp() {
        int m = Math.max(0, Math.min(mana, maxMana));
        int mx = Math.max(1, maxMana);
        return (m == mana && mx == maxMana) ? this : new ManaData(m, mx);
    }

    public boolean canConsume(int cost) {
        return cost >= 0 && mana >= cost;
    }

    public ManaData consume(int cost) {
        return new ManaData(mana - cost, maxMana).clamp();
    }

    public ManaData add(int amount) {
        return new ManaData(mana + amount, maxMana).clamp();
    }

    public ManaData setMax(int newMax) {
        return new ManaData(mana, newMax).clamp();
    }

}
