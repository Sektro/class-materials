package ORA.rpg;

public class OrkShield extends Ork {
    public OrkShield(String name, int hp, int dmg) {
        super(name,hp,dmg);
    }

    @Override
    public void Hurt(int damage) {
        hp -= damage / 2;
    }
}
