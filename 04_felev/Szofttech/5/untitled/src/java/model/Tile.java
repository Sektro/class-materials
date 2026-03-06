package model;

public class Tile extends GameObject {
    private LandTypes land;

    public Tile(int y, int x, int width, int height, int land) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        switch (land) {
            case 0:
                this.land = LandTypes.WATER;
                break;
            case 1:
                this.land = LandTypes.GRASS;
                break;
            case 2:
                this.land = LandTypes.DIRT;
                break;
            case 3:
                this.land = LandTypes.ROAD;
                break;
            case 4:
                this.land = LandTypes.ENTRANCE;
                break;
            case 5:
                this.land = LandTypes.EXIT;
                break;
            default:
                this.land = LandTypes.DIRT;
        }
    }

    // GETTER
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public LandTypes getLand() {
        return land;
    }

}
