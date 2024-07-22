import java.util.*;

/**
 * 
 */
public class StudentReport {
    /**
     * 
     */
    private final ArrayList<Quiz.Report> quizReports;
    
    /**
     * 
     */
    public StudentReport(){
        this.quizReports = new ArrayList<>();
    }

    /**
     * 
     */
    public void addQuizResults(Quiz quiz){
        this.quizReports.add(quiz.createReport());
    }
    
    /**
     * 
     */
    public char getCumulativeLetterGrade(){
        return Util.calculateAverageGradeLetter(this.quizReports);
    }

    /**
     * @return 
     */
    public Iterable<Quiz.Report> getQuizReports(){
        return this.quizReports;
    }
}