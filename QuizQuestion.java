/**
 * 
 */
public abstract class QuizQuestion {
    /**
     * 
     */
    private Difficulty difficulty;

    /**
     * 
     */
    public QuizQuestion(Difficulty difficulty){
        this.difficulty = difficulty;
    }

    /**
     * 
     */
    public Difficulty getDifficulty(){
        return this.difficulty;
    }
    
    /**
     * 
     */
    public abstract boolean checkAnswer(String answer);
}