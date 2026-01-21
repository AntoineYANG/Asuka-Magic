package com.asukamagic.mana;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

import static com.asukamagic.AsukaMagic.MOD_ID;

public final class ModAttachments {

    private ModAttachments() {}

    private static final DeferredRegister<AttachmentType<?>> ATTACHMENTS =
        DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, MOD_ID);
    public static final Supplier<AttachmentType<ManaData>> MANA = ATTACHMENTS.register(
        "mana",
        () -> AttachmentType.builder(() -> ManaData.DEFAULT)
            // persistence
            .serialize(ManaData.CODEC.fieldOf("mana"))
            .copyOnDeath()
            // sync to self
            .sync((holder, to) -> holder == to, ManaData.STREAM_CODEC)
            .build()
    );

    public static void register(IEventBus modBus) {
        ATTACHMENTS.register(modBus);
    }

}
