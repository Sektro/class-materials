package ORA.rpg;

public abstract class Character
{
    String name;
    int hp;
    int dmg;

    public Character(String name, int hp, int dmg) {
        this.name = name;
        this.hp = hp;
        this.dmg = dmg;
    }

    void Attack(Character enemy) {
        enemy.Hurt(dmg);
    }
    void Hurt(int damage) {
        hp -= damage;
    }
    boolean isDead() {
        return hp <= 0;
    }
}
