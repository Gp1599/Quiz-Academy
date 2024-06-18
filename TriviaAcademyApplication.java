import java.util.*;

/**
 *  Represents that main application for the Trivia Academy progam.
 */
public class TriviaAcademyApplication {

	/**
	 * 	Represents the maximum number of ligin attempts for the player
	 */
	private static final int MAXIMUM_LOGIN_ATTEMPTS = 3;

	/**
	 * Represents the student who will be played by the user. 
	 */
	private static Student playerStudent;
	
	/**
	 * Indicates whether or not a user wants to create a new student account or is using the Trivia Academy application.
	 */
	private static boolean firstTime = false;

	/**
	 * Runs the Trivia Academy program.
	 * @param args the specified command-line arguments.
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		//
		System.out.print("Welcome to Trivia Academy! ");
		checkFirstTime(input);
		login(input);
	}

	/**
	 *  
	 * @param id the specified ID number to check if the database has.
	 * @return whether the student with the specified id is in the database.
	 */
	private static boolean findsStudent(int id){
		return false;
	}

	/**
	 * Represents the first step of the program: prompts the user for whether or not he or she wants to create a student account.
	 * @param input the scanner for listening to user input while the application is running.
	 */
	private static void checkFirstTime(final Scanner input){
		while(true){
			System.out.print("Is this your first time?: ");
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
	}

	/**
	 * Respesents the second step of the program, prompting the user to log in if his or her account is already created, or giving him or her the ID and automatically logging him or her in otherwise.
	 * @param input the scanner to listen to user input while the application is running.
	 */
	private static void login(final Scanner input){
		int studentID = -1;
		String studentName = "";
		Random randomIDGenerator = new Random();

		if(firstTime){
			System.out.print("Please enter your name: ");
			studentName = input.nextLine();
			studentID = 10000 + randomIDGenerator.nextInt(89999); // FIXME: Generate the student's ID
			
			System.out.println("Your student ID number is: " + studentID);
		} else {
			int loginAttempts;
			for(loginAttempts = MAXIMUM_LOGIN_ATTEMPTS; loginAttempts > 0; loginAttempts--){
				System.out.print("Please enter you ID (Login Attempts: " + loginAttempts + "): ");
				try {
					studentID = Integer.parseInt(input.nextLine());
				} catch(NumberFormatException e){
					System.out.println("Error: ID not valid (must be an integer)!");
					continue;
				}
				if(findsStudent(studentID)){
					break;
				} else {
					System.out.println("Error: ID not found!");
				}
			}
			if(loginAttempts <= 0){
				System.out.println("You ran out of login attempts! Try again later!");
				return;
			}
		}
		runAcademy(input, studentName, studentID);
	}
	/**
	 * Represents the third step of the program, presenting the main menu of Trivia Academy to the user that provides gameplay options for him/her.
	 * @param input the scanner to listen to user input while the application is running.
	 * @param studentName the student name that is created after the login process.
	 * @param studentID the student ID number that is created after the login process.
	 */
	private static void runAcademy(final Scanner input, String studentName, int studentID){
		playerStudent = new Student(studentID, studentName);

		System.out.println("Welcome to Quiz Academy, " + playerStudent.getName() + "!");
		System.out.println("Main Menu: ");
		System.out.println("0 - Training");
		System.out.println("1 - Tests");
		System.out.println("2 - My Report");
		System.out.println("q - Quit");

		System.out.print("Enter valid option: ");

		String option = input.next(); 
		if(option.equals("q")){
			System.out.println("Thank you for entering Trivia Academy! Bye!");
		}
	}
}
