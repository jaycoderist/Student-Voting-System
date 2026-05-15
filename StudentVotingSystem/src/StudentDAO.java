import java.sql.*;

public class StudentDAO {

    public boolean register(String studentNumber, String name, String course, int year, String password) {
        String sql = "INSERT INTO students(student_number, name, course, year_level, password) VALUES(?,?,?,?,?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, studentNumber);
            ps.setString(2, name);
            ps.setString(3, course);
            ps.setInt(4, year);
            ps.setString(5, password);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Registration error.");
            return false;
        }
    }

    public Student login(String studentNumber, String password) {
        String sql = "SELECT * FROM students WHERE student_number=? AND password=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, studentNumber);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Student(
                    rs.getInt("student_id"),
                    rs.getString("student_number"),
                    rs.getString("name"),
                    rs.getString("course"),
                    rs.getInt("year_level"),
                    rs.getBoolean("has_voted")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void markAsVoted(int studentId) {
        String sql = "UPDATE students SET has_voted = TRUE WHERE student_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}