package fee_Report_Software;


import java.util.List;
import java.util.Scanner;

public class FeeReportApp {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("1. Login as Admin\n2. Register Admin\n3. Login as Accountant");
        int choice = sc.nextInt();
        sc.nextLine(); // Consume newline
        
        if (choice == 1) {
            // Admin Login
            System.out.print("Enter Admin Username: ");
            String username = sc.nextLine();
            System.out.print("Enter Password: ");
            String password = sc.nextLine();

            if (UserService.validateAdmin(username, password)) {
                System.out.println("\nAdmin Login Successful!\n");
                adminMenu(sc);
            } else {
                System.out.println("Invalid Admin Credentials.");
            }
        }else if (choice == 2) {
            System.out.print("Enter New Admin Username: ");
            String newUsername = sc.nextLine();
            System.out.print("Enter New Admin Password: ");
            String newPassword = sc.nextLine();
            UserService.registerAdmin(newUsername, newPassword);
        } 
        else if (choice == 3) {
            // Accountant Login
            System.out.print("Enter Accountant Email: ");
            String email = sc.nextLine();
            System.out.print("Enter Password: ");
            String password = sc.nextLine();

            if (UserService.validateAccountant(email, password)) {
                System.out.println("\nAccountant Login Successful!\n");
                accountantMenu(sc);
            } else {
                System.out.println("Invalid Accountant Credentials.");
            }
        }
        
        sc.close();
    }
	
	private static void adminMenu(Scanner sc) {
        while (true) {
            System.out.println("\n*** Admin Menu:***");
            System.out.println("1. Add Accountant");
            System.out.println("2. View Accountants");
            System.out.println("3. Edit Accountant");
            System.out.println("4. Delete Accountant");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            int option = sc.nextInt();
            sc.nextLine();
            
            switch (option) {
                case 1:
                    System.out.print("Enter Accountant Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();
                    System.out.print("Enter Phone: ");
                    String phone = sc.nextLine();
                    System.out.print("Enter Password: ");
                    String password = sc.nextLine();
                    AdminService.addAccountant(name, email, phone, password);
                    break;
                
                case 2:
                    AdminService.viewAccountants();
                    break;

                case 3:
                    System.out.print("Enter Accountant ID to Edit: ");
                    int editId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter New Name: ");
                    String newName = sc.nextLine();
                    System.out.print("Enter New Email: ");
                    String newEmail = sc.nextLine();
                    System.out.print("Enter New Phone: ");
                    String newPhone = sc.nextLine();
                    System.out.print("Enter New Password: ");
                    String newPassword = sc.nextLine();
                    AdminService.editAccountant(editId, newName, newEmail, newPhone, newPassword);
                    break;

                case 4:
                    System.out.print("Enter Accountant ID to Delete: ");
                    int deleteId = sc.nextInt();
                    AdminService.deleteAccountant(deleteId);
                    break;

                case 5:
                    System.out.println("Logging out...");
                    return;

                default:
                    System.out.println("Invalid option! Try again.");
            }
        }}
        
        
        private static void accountantMenu(Scanner sc) {
            while (true) {
                System.out.println("\n**** Accountant Menu:****");
                System.out.println("1. Add Student");
                System.out.println("2. View Students");
                System.out.println("3. Edit Student");
                System.out.println("4. Delete Student");
                System.out.println("5. Check Due Fee");
                System.out.println("6. Logout");
                System.out.print("Choose an option: ");
                int option = sc.nextInt();
                sc.nextLine(); // Consume newline
                
                switch (option) {
                    case 1:
                        System.out.print("Enter Student Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Email: ");
                        String email = sc.nextLine();
                        System.out.print("Enter Course: ");
                        String course = sc.nextLine();
                        System.out.print("Enter Total Fee: ");
                        double fee = sc.nextDouble();
                        System.out.print("Enter Paid Fee: ");
                        double paid = sc.nextDouble();
                        sc.nextLine();
                        System.out.print("Enter Address: ");
                        String address = sc.nextLine();
                        System.out.print("Enter Phone: ");
                        String phone = sc.nextLine();
                        AccountantService.addStudent(name, email, course, fee, paid, address, phone);
                        break;
                    
                    case 2:
                        AccountantService.viewStudents();
                        break;

                    case 3:
                        System.out.print("Enter Student ID to Edit: ");
                        int editId = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter New Name: ");
                        String newName = sc.nextLine();
                        System.out.print("Enter New Email: ");
                        String newEmail = sc.nextLine();
                        System.out.print("Enter New Course: ");
                        String newCourse = sc.nextLine();
                        System.out.print("Enter New Fee: ");
                        double newFee = sc.nextDouble();
                        System.out.print("Enter New Paid Amount: ");
                        double newPaid = sc.nextDouble();
                        sc.nextLine();
                        System.out.print("Enter New Address: ");
                        String newAddress = sc.nextLine();
                        System.out.print("Enter New Phone: ");
                        String newPhone = sc.nextLine();
                        AccountantService.editStudent(editId, newName, newEmail, newCourse, newFee, newPaid, newAddress, newPhone);
                        break;

                    case 4:
                        System.out.print("Enter Student ID to Delete: ");
                        int deleteId = sc.nextInt();
                        AccountantService.deleteStudent(deleteId);
                        break;

                    case 5:
                        AccountantService.checkDueFee();
                        break;

                    case 6:
                        System.out.println("Logging out...");
                        return;

                    default:
                        System.out.println("Invalid option! Try again.");
                }
            }
        
        
}}
