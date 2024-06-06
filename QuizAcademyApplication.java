import java.util.*;

public class QuizAcademyApplication {
	
	private static final int MAXIMUM_LOGIN_ATTEMPTS = 3;

	public static void main(String[] args) {
		run();
	}
	
	public static void run() {
		Scanner input = new Scanner(System.in);
		//
		System.out.print("Welcome to Quiz Academy! ");
		
		boolean firstTime = false;
		while(true){
			System.out.print("Is this your first time in Quiz Academy?: ");
			String answer =	input.nextLine();
			if(answer.equals("y") || answer.equals("yes") || answer.equals("Y") || answer.equals("Yes")){
				firstTime = true;
				break;
			} else if(answer.equals("n") || answer.equals("no") || answer.equals("N") || answer.equals("No")){
				break;
			} else {
				System.out.println("Error: Invalid answer (must be y, yes, Y, Yes, n, no, N, or No)!");
			}
		}
		int ID = -1;
		String name = "";
		if(firstTime){
			System.out.print("Please enter your name: ");
			name = input.nextLine();

			ID = 0; // FIXME: Generate the student's ID
			
			System.out.println("Your student ID number is: " + ID);
			progress(input, ID, name);
		} else {
			int loginAttempts;
			for(loginAttempts = MAXIMUM_LOGIN_ATTEMPTS; loginAttempts > 0; loginAttempts--){
				System.out.print("Please enter you ID (Login Attempts: " + loginAttempts + "): ");
				try {
					ID = Integer.parseInt(input.nextLine());
				} catch(NumberFormatException e){
					System.out.println("Error: ID not valid (must be an integer)!");
					continue;
				}
				if(findsStudent(ID)){
					break;
				} else {
					System.out.println("Error: ID not found!");
				}
			}
			if(loginAttempts > 0){
				progress(input, ID, name);
			} else {
				System.out.println("You ran out of login attempts! Try again later!");
			}
		}
	}

	private static boolean findsStudent(int id){
		return false;
	}

	private static void progress(final Scanner input, final int ID, final String name){
		Student student = new Student(ID, name);
		System.out.println("Welcome to Quiz Academy, " + student.getName() + "!");

	}
}
