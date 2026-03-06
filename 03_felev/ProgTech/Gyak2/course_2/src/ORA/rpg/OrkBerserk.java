package ORA.rpg;

public class OrkBerserk extends Ork {
    public OrkBerserk(String name, int hp, int dmg) {
        super(name,hp,dmg);
    }

    @Override
    public void Hurt(int damage) {
        hp -= damage * 2;
    }
}
