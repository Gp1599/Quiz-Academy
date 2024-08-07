import java.util.*;

/**
 *  Represents that main application for the Trivia Academy progam.
 */
public class QuizAcademyApplication {

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
		System.out.println("Welcome to Quiz Academy! ");
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
			studentID = 10000 + randomIDGenerator.nextInt(89999); 
			
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

		System.out.println();
		System.out.println("Welcome to Quiz Academy, " + playerStudent.getName() + "!");
		
		boolean running = true;
		while(running){
			//Print Main Menu
			System.out.println("Main Menu: ");
			System.out.println("0 - Training");
			System.out.println("1 - Tests");
			System.out.println("2 - My Report");
			System.out.println("q - Quit");
			System.out.print("Enter valid option: ");

			String option = input.next(); 
			switch(option){
				case "0":
					runQuizMenu(input, true);
					System.out.println();
					break;
				case "1":
					runQuizMenu(input, false);
					System.out.println();
					break;
				case "2":
					printStudentReport();
					System.out.println();
					break;
				case "q":
					System.out.println("Thank you for entering Quiz Academy! Bye!");
					running = false;
					break;
			}
		}
	}

	/**
	 * 
	 * @param input
	 * @param training
	 */
	private static void runQuizMenu(final Scanner input, boolean trainingMode){
		

		boolean running = true;
		while(running){
			System.out.println();
			System.out.println("Which quiz would you like to take:");
			System.out.println("0 - Math");
			System.out.println("1 - Memory");
			System.out.println("2 - Maze");
			System.out.println("q - back");
			System.out.print("Enter the option:  ");
			String option = input.next();
			switch(option){
				case "0":
					startQuiz(input, new QuizTest(Difficulty.EASY), trainingMode); //FIXME: Change first parameter 
					break;
				case "1":
					startQuiz(input, new QuizTest(Difficulty.NORMAL), trainingMode); //FIXME: Change second parameter
					break;
				case "2":
					startQuiz(input, new QuizTest(Difficulty.HARD), trainingMode);
					break;
				case "q":
					running = false;
					break;
				default:
					System.out.println("Error: Invalid Option!");
					break;
			}
		}
	}

	/**
	 * 
	 * @param input 
	 * @param quiz
	 * @param trainingMode
	 */
	private static void startQuiz(final Scanner input, Quiz quiz, boolean trainingMode){
		quiz.progress(input, playerStudent, trainingMode);
	}

	/**
	 * 
	 */
	private static void printStudentReport() {
		System.out.println("Cumulative Grade: " + playerStudent.getReport().getCumulativeLetterGrade());
		for(Quiz.Report report : playerStudent.getReport().getQuizReports()){
			System.out.println(report.getName());
			System.out.println("\t Score: " + report.getEarnedPoints() + "/" + report.getMaximumPoints() + "pts ");
			System.out.println("\t Grade: " + report.getLetterGrade());
		}
	}
}
