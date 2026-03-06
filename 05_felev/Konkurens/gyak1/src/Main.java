//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("|---------------------------|");
        Manager manager = new Manager("Wu Sensei", 100);
        Subcontractor subcontractor = new Subcontractor(20010911,100);
        Subordinate subordinate = new Subordinate("Kai", 100);

        System.out.println("Manager salary: " + manager.getSalary());
        System.out.println("Subcontractor (" + subcontractor.getTaxNumber() + ") salary: " + subcontractor.getSalary());
        System.out.println("Subordinate (" + subordinate.getName() + ") salary: " + subordinate.getSalary());

        manager.addEmployee(subordinate);
        manager.addEmployee(subcontractor);
        System.out.println("Manager salary (after hiring): " + manager.getSalary());

        subordinate.IncreaseSalary(20);
        System.out.println("Subordinate (" + subordinate.getName() + ") salary (after promotion): " + subordinate.getSalary());
        subcontractor.IncreaseSalary(20);
        System.out.println("Subcontractor (" + subcontractor.getTaxNumber() + ") salary (after promotion): " + subcontractor.getSalary());

        manager.removeEmployee(subcontractor);
        System.out.println("Manager salary (after layoffs): " + manager.getSalary());
        System.out.println("|---------------------------|");
    }
}