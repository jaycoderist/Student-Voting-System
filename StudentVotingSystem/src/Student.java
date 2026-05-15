public class Student {
    private int id;
    private String studentNumber;
    private String name;
    private String course;
    private int yearLevel;
    private boolean hasVoted;

    public Student(int id, String studentNumber, String name, String course, int yearLevel, boolean hasVoted) {
        this.id = id;
        this.studentNumber = studentNumber;
        this.name = name;
        this.course = course;
        this.yearLevel = yearLevel;
        this.hasVoted = hasVoted;
    }

    public int getId() { return id; }
    public String getStudentNumber() { return studentNumber; }
    public String getName() { return name; }
    public String getCourse() { return course; }
    public int getYearLevel() { return yearLevel; }
    public boolean hasVoted() { return hasVoted; }
}