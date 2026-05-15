import java.util.*;

public class Main {

    static Scanner sc = new Scanner(System.in);

    static StudentDAO studentDAO = new StudentDAO();
    static CandidateDAO candidateDAO = new CandidateDAO();
    static VoteService voteService = new VoteService();
    static ResultService resultService = new ResultService();
    static AdminDAO adminDAO = new AdminDAO();
    static AdminService adminService = new AdminService();
    static AllowedStudentDAO allowedDAO = new AllowedStudentDAO();

    public static void main(String[] args) {
        
        while (true) {
            System.out.println("\n============ Welcome to Our Voting System ============");
            System.out.println("\n1. Register\n2. Login\n3. Admin Login\n4. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: register(); break;
                case 2: login(); break;
                case 3: adminLogin(); break;
                case 4: System.exit(0);
            }
        }
    }

    static void register() {
        System.out.println("\n============== Student Registration ==============");
        sc.nextLine();
        System.out.print("Student Number: ");
        String sn = sc.nextLine();

        if (!allowedDAO.isAllowed(sn)) {
            System.out.println("Not allowed!");
            return;
        }

        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Course: ");
        String course = sc.nextLine();

        System.out.print("Year: ");
        int year = sc.nextInt();

        sc.nextLine();
        System.out.print("Password: ");
        String pass = sc.nextLine();

        if (studentDAO.register(sn, name, course, year, pass)) {
            System.out.println("Registered!");
        }
    }

    static void login() {
        System.out.println("\n================== Student Login ==================");
        sc.nextLine();
        System.out.print("Student Number: ");
        String sn = sc.nextLine();

        if (!allowedDAO.isAllowed(sn)) {
            System.out.println("Not allowed!");
            return;
        }

        System.out.print("Password: ");
        String pass = sc.nextLine();

        Student student = studentDAO.login(sn, pass);

        if (student != null) {
            System.out.println("Welcome " + student.getName());

            if (!adminDAO.isElectionOpen()) {
                System.out.println("Voting CLOSED!");
                return;
            }

            if (!student.hasVoted()) {
                vote(student);
            } else {
                System.out.println("Already voted.");
            }

            resultService.showResults();
        } else {
            System.out.println("Invalid login!");
        }
    }

    static void vote(Student student) {
        List<Candidate> candidates = candidateDAO.getAllCandidates();

        for (Candidate c : candidates) {
            System.out.println(c.getId() + ". " + c.getName() + " (" + c.getPosition() + ")");
        }

        System.out.print("Enter Candidate ID: ");
        int cid = sc.nextInt();

        try {
            voteService.vote(student.getStudentNumber(), cid); 

            candidateDAO.voteCandidate(cid); 
            studentDAO.markAsVoted(student.getId()); 

            System.out.println("Vote successful!");

        } catch (Exception e) {
            System.out.println("Vote failed (maybe already voted)");
        }
    }

    static void adminLogin() {
        System.out.println("\n=================== Admin Login ===================");
        sc.nextLine();
        System.out.print("Username: ");
        String user = sc.nextLine();

        System.out.print("Password: ");
        String pass = sc.nextLine();

        if (adminDAO.login(user, pass)) {
            adminService.adminMenu();
        } else {
            System.out.println("Invalid admin!");
        }
    }
}