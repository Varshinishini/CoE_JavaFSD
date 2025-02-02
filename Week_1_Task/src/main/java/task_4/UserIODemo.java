package task_4;

public class UserIODemo {
    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        userManager.addUser("Alice", "alice@example.com");
        userManager.addUser("Bob", "bob@example.com");

        String filename = "users.txt";

        userManager.saveUsersToFile(filename);
        userManager.loadUsersFromFile(filename);
        userManager.displayUsers();
    }
}