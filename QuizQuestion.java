/**
 * 
 */
public abstract class QuizQuestion {
    /**
     * 
     */
    private QuizQuestionDifficulty difficulty;

    /**
     * 
     */
    public QuizQuestion(QuizQuestionDifficulty difficulty){
        this.difficulty = difficulty;
    }

    /**
     * 
     */
    public QuizQuestionDifficulty getDifficulty(){
        return this.difficulty;
    }
    
    /**
     * 
     */
    public abstract boolean checkAnswer(String answer);
}