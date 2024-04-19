import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[][] employees = {
                {"Продавец 1", "Продавец"},
                {"Продавец 2", "Продавец"},
                {"Директор", "Директор"}
        };
        EmployeeManager manager = new EmployeeManager();
        boolean exit = false;
        while (!exit) {
            System.out.println("Меню:");
            System.out.println("1. Вывести список сотрудников");
            System.out.println("2. Добавить сотрудника");
            System.out.println("3. Удалить сотрудника");
            System.out.println("4. Изменить имя сотрудника");
            System.out.println("5. Вывод зарплат");
            System.out.println("6. Счет зарплаты за год");
            System.out.println("7. Захоронить сотрудника");
            System.out.println("8. Захоронить директора");
            System.out.println("9. Вывод зарплат");
            System.out.println("10. Выход");

            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    printEmployees(employees);
                    break;
                case 2:
                    System.out.print("Введите имя нового сотрудника: ");
                    String newName = scanner.nextLine();
                    System.out.print("Введите роль нового сотрудника: ");
                    String newRole = scanner.nextLine();
                    employees = addEmployee(employees, newName, newRole);
                    break;
                case 3:
                    System.out.print("Введите индекс сотрудника для удаления: ");
                    int indexToRemove = scanner.nextInt();
                    employees = removeEmployee(employees, indexToRemove);
                    break;
                case 4:
                    System.out.print("Введите индекс сотрудника для изменения имени: ");
                    int indexToChangeName = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Введите новое имя: ");
                    String newEmployeeName = scanner.nextLine();
                    employees = changeEmployeeName(employees, indexToChangeName, newEmployeeName);
                    break;
                case 5:
                    manager.printSalaryByRole();
                    break;
                case 6:
                    manager.calculateAnnualSalary(employees);
                    break;
                case 7:
                    System.out.print("Введите имя сотрудника для захоронения: ");
                    String nameToBury = scanner.nextLine();
                    employees = manager.buryEmployee(employees, nameToBury);
                    break;
                case 8:
                    System.out.print("Введите имя директора для захоронения: ");
                    String namToBury = scanner.nextLine();
                    employees = manager.buryDirector(employees, namToBury);
                    break;
                case 9:
                    System.out.print("ВОТ ЗАРПЛАТА ДИРЕКТОРА В ДАЛЬНЕЙШЕМ ОБРАЗЕ ОНА БУДЕТ НАСЛЕДОВАТЬСЯ ПОКА ЧИКИ ЧОКИ");
                    manager.calculateAnnualSalary(employees);
                    break;
                case 10:
                    exit = true;
                    break;
                default:
                    System.out.println("Некорректный ввод. Попробуйте снова.");
            }
        }
        System.out.println("Программа завершена.");
    }
    public static void printEmployees(String[][] employees) {
        System.out.println("Список сотрудников:");
        for (int i = 0; i < employees.length; i++) {
            System.out.println(i + ". Имя: " + employees[i][0] + ", Должность: " + employees[i][1]);
        }
    }
    public static String[][] addEmployee(String[][] employees, String name, String role) {
        String[][] newEmployees = Arrays.copyOf(employees, employees.length + 1);
        newEmployees[employees.length] = new String[]{name, role};
        return newEmployees;
    }

    public static String[][] removeEmployee(String[][] employees, int index) {
        if (index < 0 || index >= employees.length) {
            System.out.println("Некорректный индекс сотрудника.");
            return employees;
        }
        String[][] newEmployees = new String[employees.length - 1][2];
        for (int i = 0, j = 0; i < employees.length; i++) {
            if (i != index) {
                newEmployees[j++] = employees[i];
            }
        }
        return newEmployees;
    }
    public static String[][] changeEmployeeName(String[][] employees, int index, String newName) {
        if (index < 0 || index >= employees.length) {
            System.out.println("Некорректный индекс сотрудника.");
            return employees;
        }
        employees[index][0] = newName;
        return employees;
    }
}
class EmployeeManager {
    private final int[] salaryByRole;
    public EmployeeManager() {
        salaryByRole = new int[]{20000, 100000};
    }
    public String[][] buryEmployee(String[][] employees, String name) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i][0].equals(name)) {
                System.out.println("Сотрудник " + name + " захоронен.");
                return removeEmployee(employees, i);
            }
        }
        System.out.println("Сотрудник с именем " + name + " не найден.");
        return employees;
    }
    public String[][] buryDirector(String[][] employees, String name) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i][0].equals(name)) {
                System.out.println("Директор " + name + " захоронен. ПОЗДРАВЛЯЮ НАСТУПИЛА АНАРХИЯ ВАМИ НИКТО НЕ ПРАВИТ УРА");
                return removeEmployee(employees, i);
            }
        }
        System.out.println("Сотрудник с именем " + name + " не найден.");
        return employees;
    }

    private String[][] removeEmployee(String[][] employees, int index) {
        if (index < 0 || index >= employees.length) {
            System.out.println("Некорректный индекс сотрудника.");
            return employees;
        }
        String[][] newEmployees = new String[employees.length - 1][2];
        for (int i = 0, j = 0; i < employees.length; i++) {
            if (i != index) {
                newEmployees[j++] = employees[i];
            }
        }
        return newEmployees;
    }
    public void printSalaryByRole() {
        System.out.println("Зарплаты по должностям:");
        System.out.println("Продавец: " + salaryByRole[0]);
        System.out.println("Директор: " + salaryByRole[1]);
    }

    public void calculateAnnualSalary(String[][] employees) {
        int totalSalary = 0;
        for (String[] employee : employees) {
            String role = employee[0];
            if (role.equals("Продавец")) {
                totalSalary += salaryByRole[0] * 12;
            } else if (role.equals("Директор")) {
                totalSalary += salaryByRole[1] * 12;
            }
        }
        System.out.println("Общая зарплата за год: " + totalSalary);
    }
}
