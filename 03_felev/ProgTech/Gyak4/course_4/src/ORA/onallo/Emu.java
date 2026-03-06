package ORA.onallo;

import java.util.ArrayList;
import java.util.List;

public class Emu extends Animal {
    public Emu(String name, int weight) {
        this.name = name;
        this.weight = weight;
        meals = 0;
        food = new ArrayList<Integer>();
    }

    @Override
    public boolean isSkinny() {
        return weight < 20;
    }
}
