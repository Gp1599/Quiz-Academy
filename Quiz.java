import java.util.*;

/**
 * 
 */
public abstract class Quiz implements Runnable {

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
    private double currentTime;

    /**
     * 
     */
    public Quiz(String name, Difficulty difficulty, double timeLimit){
        if(name == null) throw new IllegalArgumentException("The name of the quiz can't be null!");
        if(timeLimit < 0) throw new IllegalArgumentException("The time can't be negative");

        this.name = name;

        this.difficulty = difficulty;

        this.earnedPoints = 0;
        this.maximumPoints = 0;

        this.currentTime = timeLimit;
    }

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
        private int id;
        
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
            this.id = 100000; //FIXME: Make it generate a random number between 000000 and 999999
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
     */
    @Override
    public void run(){
        double lastTime = System.nanoTime();
        double markTime = lastTime;
        while(this.currentTime > 0){
            double elapsedTime = System.nanoTime() - lastTime;
            this.currentTime -= elapsedTime;
            markTime += elapsedTime;
            lastTime = markTime;
        }
    }
    /**
     *  Runs the quiz for the player student or user to take.
     * @param input the scanner to read answers from the player user.
     * @param trainingMode
     */
    public void progress(Scanner input, Student player, boolean trainingMode){
        Thread quizThread = new Thread(this);
        quizThread.start();

        while(!this.isDone()){
            QuizQuestion currentQuestion = this.pick();
            System.out.println(currentQuestion);
            System.out.print("Enter answer: ");
            String answer = input.nextLine();

            if(this.currentTime <= 0) {
                System.out.println("Time's up! (You have been automatically exited from the quiz)");
                break;
            }
            this.maximumPoints += currentQuestion.getDifficulty().getReward();
            if(currentQuestion.checkAnswer(answer)){
                this.earnedPoints += currentQuestion.getDifficulty().getReward();
                if(trainingMode){
                    System.out.println("Correct!");
                }
            } else {
                if(trainingMode){
                    System.out.println("Incorrect!");
                }
            }

            System.out.println();
        }

        if(!trainingMode){
            player.getReport().addQuizResults(this);
        }
    }
}