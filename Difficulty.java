/**
 * 
 */
public enum Difficulty {
    /**
     * 
     */
    EASY(5), 
    
    /**
     * 
     */
    NORMAL(10), 
    
    /**
     * 
     */
    HARD(20), 
    
    /**
     * 
     */
    EXPERT(40);

    /**
     * 
     */
    private int reward;

    /**
     * 
     */
    private Difficulty(int reward){
        this.reward = reward;
    }

    /**
     * 
     */
    public int getReward(){
        return this.reward;
    }
}