package homework_4;

import java.util.ArrayList;
import java.util.List;

public class Employee_Directory {
    private List<Employee> employees;

    public Employee_Directory() {
        employees = new ArrayList<>();
    }

    //Метод добавление нового сотрудника в справочник
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    //Метод, который ищет сотрудника по стажу (может быть список)
    public List<Employee> findEmployeeByExperience(int experience) {
        List<Employee> foundEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getExperience() == experience) {
                foundEmployees.add(employee);
            }
        }
        return foundEmployees;
    }

    //Метод, который возвращает номер телефона сотрудника по имени (может быть список)
    public List<String> getPhoneNumbersByName(String name) {
        List<String> phoneNumbers = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getName().equals(name)) {
                phoneNumbers.add(employee.getPhoneNumber());
            }
        }
        return phoneNumbers;
    }

    //Метод, который ищет сотрудника по табельному номеру
    public Employee findEmployeeByPersonnelNumber(int personnelNumber) {
        for (Employee employee : employees) {
            if (employee.getPersonnelNumber() == personnelNumber) {
                return employee;
            }
        }
        throw new RuntimeException("Сотрудника с таким табельным номером - НЕТ!!!");
    }
}