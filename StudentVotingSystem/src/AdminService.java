import java.util.*;

public class AdminService {

    Scanner sc = new Scanner(System.in);
    CandidateDAO candidateDAO = new CandidateDAO();
    ResultService resultService = new ResultService();
    AdminDAO adminDAO = new AdminDAO();
    AllowedStudentDAO allowedDAO = new AllowedStudentDAO();
    ExportService exportService = new ExportService();

    public void adminMenu() {
          
        while (true) {
            System.out.println("\n=================== Admin Page ===================");
            System.out.println("1. Add Candidate");
            System.out.println("2. View Candidates");
            System.out.println("3. Add Allowed Student");
            System.out.println("4. View Allowed Students");
            System.out.println("5. Close Election");
            System.out.println("6. Open Election");
            System.out.println("7. View Results");
            System.out.println("8. Export Election Report (Excel)");
            System.out.println("9. Logout");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1: addCandidate(); break;
                case 2: viewCandidates(); break;
                case 3: addAllowedStudent(); break;
                case 4: allowedDAO.viewAllowedStudents(); break;
                case 5: adminDAO.closeElection(); break;
                case 6: adminDAO.openElection(); break;
                case 7: resultService.showResults(); break;
                case 8: exportService.exportElectionReport(); break;
                case 9: return;
            }
        }
    }

    private void addCandidate() {
        sc.nextLine();
        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Position: ");
        String position = sc.nextLine();

        System.out.print("Partylist: ");
        String party = sc.nextLine();

        candidateDAO.addCandidate(name, position, party);
    }

    private void viewCandidates() {
        List<Candidate> list = candidateDAO.getAllCandidates();

        for (Candidate c : list) {
            System.out.println(c.getId() + " | " + c.getName() + " | " + c.getPosition());
        }
    }

    private void addAllowedStudent() {
        sc.nextLine();
        System.out.print("Student Number: ");
        String sn = sc.nextLine();

        allowedDAO.addAllowedStudent(sn);
    }
}