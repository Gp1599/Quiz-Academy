import java.util.*;

/**
 * 
 */
public abstract class Quiz {

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    protected final Difficulty difficulty;

    /**
     * 
     */
    private int earnedPoints, maximumPoints;

    /**
     * 
     */
    private float currentTime;

    /**
     * 
     */
    public Quiz(String name, Difficulty difficulty, float currentTime){
        if(name == null) throw new IllegalArgumentException("The name of the quiz can't be null!");
        if(currentTime >= 0) throw new IllegalArgumentException("The time can't be negative");

        this.name = name;

        this.difficulty = difficulty;

        this.earnedPoints = 0;
        this.maximumPoints = 0;

        this.currentTime = currentTime;
    }

    /**
     *  @returns the collection of questions to be added to the quiz.
     */
    protected abstract Iterable<QuizQuestion> generateQuestions();

    /**
     * 
     * @return a randomly selection question for the user to answer next.
     */
    protected abstract QuizQuestion pick();

    /**
     * 
     */
    protected abstract boolean isDone();

    /**
     * 
     */
    public Quiz.Report createReport(){
        return new Quiz.Report(this);
    }

    /**
     * 
     */
    public static class Report {
        /**
         * 
         */
        private String name;
        
        /**
         * 
         */
        private int earnedPoints;

        /**
         * 
         */
        private int maximumPoints;

        /**
         * 
         */
        public Report(Quiz quiz){
            this.name = quiz.name;
            this.earnedPoints = quiz.earnedPoints;
            this.maximumPoints = quiz.maximumPoints;
        }

        /**
         * 
         */
        public String getName(){
            return this.name;
        }

        /**
         * 
         */
        public int getEarnedPoints(){
            return this.earnedPoints;
        }

        /**
         * 
         */
        public int getMaximumPoints(){
            return this.maximumPoints;
        }

        /**
         * 
         */
        public char getLetterGrade(){
            return Util.calculateGradeLetter(this);
        }
    }

    /**
     *  Runs the quiz for the player student or user to take.
     * @param input the scanner to read answers from the player user.
     */
    public void run(Scanner input){
        QuizQuestion currentQuestion = this.pick();

        while(!this.isDone()){
            System.out.println(currentQuestion);
            System.out.print("Enter answer: ");
            String answer = input.nextLine();
            if(this.currentTime < 0) {
                System.out.println("Time's up! (You have been automatically exited from the quiz)");
                break;
            }
            this.maximumPoints += currentQuestion.getDifficulty().getReward();
            if(currentQuestion.checkAnswer(answer)){
                this.earnedPoints += currentQuestion.getDifficulty().getReward();
            }
            currentQuestion = this.pick();
        }
    }
}