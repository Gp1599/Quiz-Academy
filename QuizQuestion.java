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
    @Override
    public abstract String toString();

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