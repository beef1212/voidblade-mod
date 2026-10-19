package com.example.voidblade;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class VoidbladeMod implements ModInitializer {
    public static final String MOD_ID = "voidblade";
    public static final Item VOIDBLADE = Registry.register(
        Registries.ITEM, Identifier.of(MOD_ID, "voidblade"), new VoidbladeItem(
            ToolMaterials.NETHERITE, new Item.Settings().maxDamage(1800).fireproof()
        )
    );

    @Override public void onInitialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> entries.add(VOIDBLADE));
    }
}
