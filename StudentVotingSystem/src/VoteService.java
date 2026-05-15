import java.sql.*;

public class VoteService {

    public void vote(String studentNumber, int candidateId) {
    String sql = "INSERT INTO votes(student_number, candidate_id) VALUES(?,?)";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, studentNumber); 
        ps.setInt(2, candidateId);

        ps.executeUpdate();

    } catch (Exception e) {
        e.printStackTrace();
    }
}
}