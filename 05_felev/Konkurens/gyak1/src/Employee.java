public abstract class Employee implements SalariedEntity {
    protected String name;
    protected double salary;

    public String getName() {
        return name;
    }

    public Employee(String name, double salary)  {
        this.name = name;
        this.salary = salary;
    }

    public void IncreaseSalary(double percentage) {
        salary = salary + salary * percentage * 0.01;
    }
}
