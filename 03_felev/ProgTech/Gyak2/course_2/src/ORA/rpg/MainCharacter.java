package ORA.rpg;

public class MainCharacter extends Character {

    int def;

    public MainCharacter(String name, int hp, int dmg, int def) {
        super(name,hp,dmg);
        this.def = def;
    }

    @Override
    public void Hurt(int damage) {
        hp -= damage / def;
    }
}
