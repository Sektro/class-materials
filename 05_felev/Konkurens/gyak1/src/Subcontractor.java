public class Subcontractor implements SalariedEntity {
    @Override
    public double getSalary() {
        return salary;
    }
    protected long taxNumber;
    protected double salary;

    public long getTaxNumber() {
        return taxNumber;
    }

    public Subcontractor(long taxNumber, double salary)  {
        this.taxNumber = taxNumber;
        this.salary = salary;
    }

    public void IncreaseSalary(double percentage) {
        salary = salary + salary * percentage * 0.01;
    }
}
