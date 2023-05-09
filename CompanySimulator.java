package pgdp.company;

import java.util.Scanner;

public class CompanySimulation {
    public static void main(String[] args) {
        simulation("Novak","ATP");
    }

    private static void simulation(String ceoName, String companyName) {
        Employee ceo = new Employee(ceoName,null);
        ceo.setID(0);
        Company company = new Company(companyName,ceo);
        Scanner scanner = new Scanner(System.in);
        String numberOfQueries = scanner.nextLine();

        for (int k = 0; k < Integer.parseInt(numberOfQueries); k++) {
            String query = scanner.nextLine();
            try {
                
                if(isPrefix("EmployeeWithID ", query)) {
                    
                    int ID = Integer.valueOf(query.substring("EmployeeWithID ".length()));
                    
                    System.out.println(company.findByID(ID).toString());
                    
                } else if(isPrefix("Add ", query)) {
                    
                    int spaceIndex = query.indexOf(' ', "Add ".length());
                    String name = query.substring("Add ".length(), spaceIndex);
                    int ID = Integer.valueOf(query.substring(spaceIndex + 1));
                    
                    Employee boss = company.findByID(ID);
                    if(boss == null) {
                        throw new Exception("No employee with such ID");
                    }
                    
                    Employee newEmployee = new Employee(name, boss);
                    company.addEmployee(newEmployee);
                    
                } else if(isPrefix("Fire ", query)) {
                    
                    int ID = Integer.valueOf(query.substring("Fire ".length()));
                    
                    company.fireEmployee(ID);
                    
                } else if(isPrefix("FindCommonBoss ", query)) {
                    
                    int spaceIndex = query.indexOf(' ', "FindCommonBoss ".length());
                    int ID1 = Integer.valueOf(query.substring("FindCommonBoss ".length(), spaceIndex));
                    int ID2 = Integer.valueOf(query.substring(spaceIndex + 1));
                    
                    Employee E1 = company.findByID(ID1);
                    Employee E2 = company.findByID(ID2);
                    if(E1 == null || E2 == null) {
                        throw new Exception("No such employee(s)");
                    } else {
                        System.out.println(company.findCommonBoss(E1, E2).toString());
                    }
                    
                } else {
                    throw new Exception("Invalid input form");
                }
                
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        scanner.close();
    }
    
    private static boolean isPrefix(String prefix, String s) {
        if(s.length() < prefix.length()) {
            return false;
        }
        return s.substring(0, prefix.length()).equals(prefix);
    }
}
