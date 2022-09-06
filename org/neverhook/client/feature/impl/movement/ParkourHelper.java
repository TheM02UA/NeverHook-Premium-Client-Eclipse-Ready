package org.neverhook.client.feature.impl.movement;

import org.neverhook.client.event.EventTarget;
import org.neverhook.client.event.events.impl.player.EventUpdate;
import org.neverhook.client.feature.Feature;
import org.neverhook.client.feature.impl.Type;

public class ParkourHelper extends Feature {

    public ParkourHelper() {
        super("ParkourHelper", "Автоматически прыгает на конце блока", Type.Movement);
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        if (mc.world.getCollisionBoxes(mc.player, mc.player.getEntityBoundingBox().offset(0, -0.5, 0).expand(-0.001, 0, -0.001)).isEmpty() && mc.player.onGround && !mc.gameSettings.keyBindJump.isKeyDown()) {
            mc.player.jump();
        }
    }
}
