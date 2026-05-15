import java.sql.*;

public class AllowedStudentDAO {

    public boolean isAllowed(String studentNumber) {
        String sql = "SELECT * FROM allowed_students WHERE student_number=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, studentNumber);
            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void addAllowedStudent(String studentNumber) {
        String sql = "INSERT INTO allowed_students(student_number) VALUES(?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, studentNumber);
            ps.executeUpdate();
            System.out.println("Student allowed!");

        } catch (Exception e) {
            System.out.println("Already exists!");
        }
    }

    public void viewAllowedStudents() {
        String sql = "SELECT * FROM allowed_students";

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("\n---------- Student Numbers List ----------");
            while (rs.next()) {
                System.out.println(rs.getString("student_number"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}