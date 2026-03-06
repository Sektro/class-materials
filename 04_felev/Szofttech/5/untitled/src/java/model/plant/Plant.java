package model.plant;

import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.JButton;
import view.Camera;
import model.GameConstants;

public class Plant {
    protected double nutrition;
    protected PlantType plantType;
    private int x;
    private int y;
    protected JButton btn;
    

    public Plant(double nutrition, PlantType plantType, int x, int y) {
        this.nutrition = nutrition;
        this.plantType = plantType;
        this.y = y;
        this.x = x;
        this.btn = new JButton();
        this.btn.setText(this.plantType.name());
        this.btn.setBounds(x, y, 80, 50);
    }

    public double getNutrition() {
        if (plantType.equals(PlantType.PalmTree)) {
            nutrition = 3;
            return nutrition;
        } else if (plantType.equals(PlantType.BaobabTree)) {
            nutrition = 4;
            return nutrition;
        }
        else if (plantType.equals(PlantType.Bush)) {
            nutrition = 5;
            return nutrition;
        }
        else {
            return 0;
        }
    }

    public int getPosX(){
        return x;
    }

    public int getPosY(){
        return y;
    }

    public JButton img(){
        return btn;
    }


    public PlantType getPlantType() {
        return plantType;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}
