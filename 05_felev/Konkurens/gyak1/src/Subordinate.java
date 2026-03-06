public class Subordinate extends Employee{
    @Override
    public double getSalary() {
        return salary;
    }

    public Subordinate(String name, double salary) {
        super(name, salary);
    }
}
