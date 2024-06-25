/**
 * 
 */
public class Util {

    /**
     * 
     */
    public static char calculateGradeLetter(Quiz.Report report){
        return calculateGradeLetter(report.getEarnedPoints(), report.getMaximumPoints());
    }

    /**
     * 
     */
    public static char calculateAverageGradeLetter(Iterable<Quiz.Report> reports){
        int earnedPoints = 0;
        int maximumPoints = 0;

        for(Quiz.Report report : reports){
            earnedPoints += report.getEarnedPoints();
            maximumPoints += report.getMaximumPoints();
        }

        return calculateGradeLetter(earnedPoints, maximumPoints);
    }

    /**
     * 
     */
    private static char calculateGradeLetter(int currentPoints, int maximumPoints){
        if(currentPoints > maximumPoints) throw new IllegalArgumentException();
        if(currentPoints <= 0 && maximumPoints <= 0){
            return 'N';
        }

        float normalizedGrade = (float) currentPoints / (float) maximumPoints;

        if(normalizedGrade >= 0.98){
            return 'S';
        } else if(normalizedGrade >= 0.9){
            return 'A';
        } else if(normalizedGrade >= 0.8){
            return 'B';
        } else if(normalizedGrade >= 0.7){
            return 'C';
        } else if(normalizedGrade >= 0.6){
            return 'D';
        } else {
            return 'F';
        }
    }
}