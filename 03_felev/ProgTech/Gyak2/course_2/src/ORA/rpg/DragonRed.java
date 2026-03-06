package ORA.rpg;

public class DragonRed extends Character {
    public DragonRed(String name, int hp, int dmg) {
        super(name,hp,dmg);
    }

    @Override
    public void Hurt(int damage) {
        if (damage > 60) {hp -= damage;}
    }
}
