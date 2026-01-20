package com.asukamagic.item;

import com.asukamagic.AsukaMagic;
import com.asukamagic.item.staves.ShortStaffItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AsukaMagic.MOD_ID);

    public static final DeferredItem<Item> SHORT_STAFF = ITEMS.registerItem(
        "short_staff",
        ShortStaffItem::new,
        new Item.Properties().stacksTo(1).durability(255)
    );

    public static final DeferredItem<Item> TEST_MAGIC_BOOK = ITEMS.registerItem(
        "test_magic_book",
        Item::new,
        new Item.Properties().stacksTo(1)
    );

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
