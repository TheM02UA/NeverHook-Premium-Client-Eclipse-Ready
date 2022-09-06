package org.neverhook.client.feature.impl.movement;

import org.neverhook.client.event.EventTarget;
import org.neverhook.client.event.events.impl.player.EventPreMotion;
import org.neverhook.client.feature.Feature;
import org.neverhook.client.feature.impl.Type;
import org.neverhook.client.helpers.player.MovementHelper;
import org.neverhook.client.settings.impl.ListSetting;
import org.neverhook.client.settings.impl.NumberSetting;

public class FastClimb extends Feature {

    public static ListSetting ladderMode;
    public static NumberSetting ladderSpeed;

    public FastClimb() {
        super("FastClimb", "Позволяет быстро забираться по лестницам и лианам", Type.Movement);
        ladderMode = new ListSetting("FastClimb Mode", "Matrix", () -> true, "Matrix", "Vanilla");
        ladderSpeed = new NumberSetting("Ladder Speed", 0.5F, 0.1F, 2F, 0.1F, () -> ladderMode.currentMode.equals("Vanilla"));
        addSettings(ladderMode);
    }

    @EventTarget
    public void onPreMotion(EventPreMotion event) {
        this.setSuffix(ladderMode.getCurrentMode());
        if (mc.player == null || mc.world == null)
            return;
        switch (ladderMode.getOptions()) {
            case "Matrix":
                if (mc.player.isOnLadder() && mc.player.isCollidedHorizontally && MovementHelper.isMoving()) {
                    mc.player.motionY += 0.312f;
                    event.setOnGround(true);
                }
                break;
            case "Vanilla":
                if (mc.player.isOnLadder() && mc.player.isCollidedHorizontally && MovementHelper.isMoving()) {
                    mc.player.motionY += ladderSpeed.getNumberValue();
                }
                break;
        }
    }
}

