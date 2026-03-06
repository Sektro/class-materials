package view;

import model.GameConstants;
import model.GameObject;
import controller.KeyHandler;

import java.awt.*;

public class Camera {
    private int x;
    private int y;
    private int width;
    private int height;

    private Rectangle viewBounds;

    public Camera(int y, int x, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        updateViewBounds();
    }

    private void updateViewBounds() {
        this.viewBounds = new Rectangle(x - width / 2,y - height / 2,width,height);
    }

    public void update(KeyHandler cameraKey) {
        // CAMERA
        if (cameraKey.upPressed && y > GameConstants.CAMERA_HEIGHT/2) {
            y -= GameConstants.CAMERA_SPEED;
        }
        if (cameraKey.downPressed && y < GameConstants.MAP_HEIGHT - GameConstants.CAMERA_HEIGHT/2) {
            y += GameConstants.CAMERA_SPEED;
        }
        if (cameraKey.leftPressed && x > GameConstants.CAMERA_WIDTH/2) {
            x -= GameConstants.CAMERA_SPEED;
        }
        if (cameraKey.rightPressed && x < GameConstants.MAP_WIDTH - GameConstants.CAMERA_WIDTH/2) {
            x += GameConstants.CAMERA_SPEED;
        }
        updateViewBounds();
    }

    public boolean isInView(GameObject gameObject) {
        return viewBounds.intersects(
                gameObject.getX() * gameObject.getWidth(),
                gameObject.getY() * gameObject.getHeight(),
                gameObject.getWidth(),
                gameObject.getHeight()
        );
    }

    // GETTER
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}
