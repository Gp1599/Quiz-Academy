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
    public Quiz(String name, QuizQuestionDifficulty difficulty){
        this.name = name;
        this.questions = new HashMap<>();
        for(QuizQuestionDifficulty diff : QuizQuestionDifficulty.values()){
            this.questions.put(diff, new ArrayList<>());
        }

        this.difficulty = difficulty;
        this.questionPtr = -1;

        this.earnedPoints = 0;
        this.maximumPoints = 0;
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
}