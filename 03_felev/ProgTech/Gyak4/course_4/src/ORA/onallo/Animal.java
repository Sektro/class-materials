package ORA.onallo;

import java.util.List;

public abstract class Animal {
    String name;
    int weight;
    int meals;
    List<Integer> food;

    public boolean isSkinny() {
        return true;
    }

    public boolean moreThanKilo() {
        int sum = 0;
        for (int k : food) {
            sum += k;
        }
        sum = sum / 100;
        return sum > 1;
    }

    public void addMeal(int amount) {
        food.add(amount);
    }

    public String getName() {
        return name;
    }
    public int getWeight() {
        return weight;
    }
    public int getMeals() {
        return meals;
    }
    public List<Integer> getFood() {
        return food;
    }
}
