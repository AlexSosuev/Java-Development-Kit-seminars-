package animation_test.common;

import animation_test.common.MainCanvas;

import java.awt.*;

public interface CanvasRepaintListener {
    void onDrawFrame(MainCanvas canvas, Graphics g, float deltaTime);
}