import java.util.ArrayList;

public class Manager extends Employee {
    @Override
    public double getSalary() {
        double actualSalary = salary;
        for (SalariedEntity e : employees) {
            actualSalary += e.getSalary() * 0.05;
        }
        return actualSalary;
    }

    private final ArrayList<SalariedEntity> employees;

    public Manager(String name, double salary) {
        super(name, salary);
        employees = new ArrayList<>();
    }

    public void addEmployee(SalariedEntity employee) {
        employees.add(employee);
    }

    public void removeEmployee(SalariedEntity employee) {
        employees.remove(employee);
    }
}
