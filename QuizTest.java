
/**
 *  Represents a quiz type used for testing the functionality of quizzes
 */
public class QuizTest extends Quiz {
    
    /**
     *  Creates the testing quiz
     * @param difficulty
     * @param timeLimit
     */
    public QuizTest(Difficulty difficulty){
        super("Testing Quiz!", difficulty);
    }

    /**
     *  Picks the question
     */
    @Override 
    public QuizQuestion pick(){
        return new QuizQuestionTest(this.difficulty);
    }

    /**
     *  @return returns that the quiz is never done; which means students can answer as many questions as they can until the timer is up
     */
    @Override
    public boolean isDone() {
        return false;
    }

    /**
     * 
     */
    private static class QuizQuestionTest extends QuizQuestion {
        
        /**
         *  
         */
        public QuizQuestionTest(Difficulty difficulty){
            super(difficulty);
        }

        /**
         * 
         */
        @Override
        public String toString(){
            return "Can you answer this question?: ";
        }

        /**
         *   
         */
        @Override
        public boolean checkAnswer(String answer){
            if(answer.equals("Yes")){
                return true;
            } else {
                return false;
            }
        } 
    }

}