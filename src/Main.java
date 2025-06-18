import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Employee;

public class Main {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        List<Employee> list = new ArrayList<>();

        System.out.print("How many employees will be registered? ");
        int N = sc.nextInt(); // Quantidade de funcionários que serão registrados.

        for (int i=0; i<N; i++) { // Laço for registra a quantidade de funcionários fornecidos pela variável N.

            System.out.println();
            System.out.println("Emplyoee #" + (i + 1) + ":");
            System.out.print("Id: ");
            Integer id = sc.nextInt();
            while (hasId(list, id)) { // Laço While verifica se o ID digitado já está sendo usado por outro funcionário.
                System.out.println("This ID already exist! Try again: ");
                System.out.print("Id: ");
                id = sc.nextInt();
            }

            System.out.print("Name: ");
            sc.nextLine();
            String name = sc.nextLine();
            System.out.print("Salary: ");
            Double salary = sc.nextDouble();

            Employee emp = new Employee(id, name, salary); // Funcionário recebe os dados digitados pelo usuário.

            list.add(emp); // Lista recebe o funcionário.
        }

        System.out.println();
        System.out.print("Enter the employee id that will have salary increase : ");
        int selectedId = sc.nextInt(); // Seleciona o ID do funcionário que vai ter o salário modificado.

        Employee emp = list.stream().filter(x -> x.getId() == selectedId).findFirst().orElse(null);

        if (emp == null) {
            System.out.println("This id does not exist!"); // Caso o ID não exista, exibe a mensagem e o programa é encerrado.
        }
        else {
            System.out.print("Enter the percentage: "); // Se o ID for válido, entra a porcentagem para modificar no salário.
            double percent = sc.nextDouble();
            emp.increaseSalary(percent);
        }

        System.out.println();
        System.out.println("List of employees:");
        for (Employee e : list) {
            System.out.println(e);
        }

        sc.close();
    }

    // Função para verificar se o ID já existe na lista.
    public static boolean hasId(List<Employee> list, int id) {
        Employee emp = list.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
        return emp != null;
    }
}