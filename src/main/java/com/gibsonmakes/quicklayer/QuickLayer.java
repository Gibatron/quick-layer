package com.gibsonmakes.quicklayer;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.entity.PlayerModelPart;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import java.util.HashMap;

public class QuickLayer implements ClientModInitializer {
    boolean toggle = false;

    @Override
    public void onInitializeClient() {
        HashMap<PlayerModelPart, KeyBinding> keys = new HashMap<>();
        KeyBinding allBind = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.quicklayer.all", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "key.category.quicklayer"));
        keys.put(PlayerModelPart.CAPE, KeyBindingHelper.registerKeyBinding(new KeyBinding("key.quicklayer.cape", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "key.category.quicklayer")));
        keys.put(PlayerModelPart.JACKET, KeyBindingHelper.registerKeyBinding(new KeyBinding("key.quicklayer.jacket", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "key.category.quicklayer")));
        keys.put(PlayerModelPart.LEFT_SLEEVE, KeyBindingHelper.registerKeyBinding(new KeyBinding("key.quicklayer.left_sleeve", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "key.category.quicklayer")));
        keys.put(PlayerModelPart.RIGHT_SLEEVE, KeyBindingHelper.registerKeyBinding(new KeyBinding("key.quicklayer.right_sleeve", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "key.category.quicklayer")));
        keys.put(PlayerModelPart.LEFT_PANTS_LEG, KeyBindingHelper.registerKeyBinding(new KeyBinding("key.quicklayer.left_pants_leg", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "key.category.quicklayer")));
        keys.put(PlayerModelPart.RIGHT_PANTS_LEG, KeyBindingHelper.registerKeyBinding(new KeyBinding("key.quicklayer.right_pants_leg", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "key.category.quicklayer")));
        keys.put(PlayerModelPart.HAT, KeyBindingHelper.registerKeyBinding(new KeyBinding("key.quicklayer.hat", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "key.category.quicklayer")));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (allBind.wasPressed())
            {
                toggle = !toggle;
                client.options.togglePlayerModelPart(PlayerModelPart.JACKET, toggle);
                client.options.togglePlayerModelPart(PlayerModelPart.LEFT_SLEEVE, toggle);
                client.options.togglePlayerModelPart(PlayerModelPart.RIGHT_SLEEVE, toggle);
                client.options.togglePlayerModelPart(PlayerModelPart.LEFT_PANTS_LEG, toggle);
                client.options.togglePlayerModelPart(PlayerModelPart.RIGHT_PANTS_LEG, toggle);
                client.options.togglePlayerModelPart(PlayerModelPart.HAT, toggle);
            }
            for (PlayerModelPart part : keys.keySet()) {
                if (keys.get(part).wasPressed()) {
                    client.options.togglePlayerModelPart(part, !client.options.isPlayerModelPartEnabled(part));
                    for (PlayerModelPart part2 : keys.keySet()) {
                        if (keys.get(part2).equals(keys.get(part)) && part != part2) {
                            client.options.togglePlayerModelPart(part2, !client.options.isPlayerModelPartEnabled(part2));
                        }
                    }
                }
            }
        });
    }
}