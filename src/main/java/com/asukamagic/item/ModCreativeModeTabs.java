package com.asukamagic.item;

import com.asukamagic.AsukaMagic;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AsukaMagic.MOD_ID);

    public static final Supplier<CreativeModeTab> ASUKAMAGIC_TAB = CREATIVE_MODE_TABS.register(
        "asukamagic_tab",
        () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItems.SHORT_STAFF.get()))
            .title(Component.translatable("itemGroup.asukamagic_tab"))
            .displayItems((parameters, output) -> {
                output.accept(ModItems.SHORT_STAFF.get());
                output.accept(ModItems.TEST_MAGIC_BOOK.get());
            })
            .build()
    );

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

}
