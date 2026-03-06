package ORA.rpg;

public class DragonBlack extends Character {
    public DragonBlack(String name, int hp, int dmg) {
        super(name,hp,dmg);
    }


    @Override
    public void Hurt(int damage) {
        if (damage > 20) {hp -= damage;}
    }
}
