/**
 *  This represents a student account for Quiz Academy
 */
public class Student {
    /**
     * Represents the student's name
     */
    private String name;
    
    /**
     * Represents the student's ID
     */
    private int ID;

    /**
     * 
     */
    private StudentReport report;

    /**
     * Initializes the student with the name and ID
     * @param name
     * @param id
     */
    public Student(int id, String name){
        this.name = name;
        this.ID = id;
        this.report = new StudentReport();
    }

    /**
     * 
     * @return the student's name
     */
    public String getName(){
        return this.name;
    }

    /**
     * 
     * @return the student's id number
     */
    public int getID(){
        return this.ID;
    }

    /**
     * @report
     */
    public StudentReport getReport(){
        return this.report;
    }
}