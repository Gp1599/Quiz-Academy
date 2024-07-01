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
    private final HashMap<QuizQuestionDifficulty, ArrayList<QuizQuestion>> questions;

    /**
     * 
     */
    private QuizQuestionDifficulty difficulty;
    
    /**
     * 
     */
    private int questionPtr;

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
    public Quiz(String name, QuizQuestionDifficulty difficulty, float currentTime){
        if(name == null) throw new IllegalArgumentException("The name of the quiz can't be null!");
        if(currentTime >= 0) throw new IllegalArgumentException("The time can't be negative");

        this.name = name;
        this.questions = new HashMap<>();
        for(QuizQuestionDifficulty diff : QuizQuestionDifficulty.values()){
            this.questions.put(diff, new ArrayList<>());
        }

        this.difficulty = difficulty;
        this.questionPtr = -1;

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
    private QuizQuestion pick(){
        this.questionPtr++;
        if(this.questionPtr >= this.questions.get(this.difficulty).size()){
            this.questionPtr = 0;
        }
        return this.questions.get(this.difficulty).get(this.questionPtr);
    }

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
     * 
     * @return whether or not the object obeys the representation invariant.
    */
    public boolean obeysRepInvariant(){
        if(!this.questions.isEmpty()){
            QuizQuestionDifficulty[] allDifficulties = QuizQuestionDifficulty.values();
            for(QuizQuestionDifficulty difficulty : allDifficulties){
                if(!this.questions.get(difficulty).isEmpty()){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *  Runs the quiz for the player student or user to take.
     * @param input the scanner to read answers from the player user.
     */
    public void run(Scanner input){
        QuizQuestion currentQuestion = this.pick();

        while(true){
            System.out.println(currentQuestion);
            System.out.print("Enter answer: ");
            String answer = input.nextLine();
            if(this.currentTime < 0) {
                System.out.println("Time is up! (You have been automatically exited from the quiz)");
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