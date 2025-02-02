package task_1;

public class Main {
	 public static void main(String[] args) {
	        TaskManager manager = new TaskManager();
	        manager.addTask("1", "Complete assignment", 2);
	        manager.addTask("2", "Prepare for meeting", 1);
	        manager.addTask("3", "Submit report", 3);
	        
	        System.out.println("Highest Priority Task: " + manager.getHighestPriorityTask());
	        
	        manager.removeTask("3");
	        System.out.println("Highest Priority Task after removal: " + manager.getHighestPriorityTask());
	    }

}
