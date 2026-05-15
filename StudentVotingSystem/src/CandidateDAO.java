import java.sql.*;
import java.util.*;

public class CandidateDAO {

    public List<Candidate> getAllCandidates() {
        List<Candidate> list = new ArrayList<>();
        String sql = "SELECT * FROM candidates";

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Candidate(
                    rs.getInt("candidate_id"),
                    rs.getString("name"),
                    rs.getString("position"),
                    rs.getInt("vote_count")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void voteCandidate(int candidateId) {
        String sql = "UPDATE candidates SET vote_count = vote_count + 1 WHERE candidate_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, candidateId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addCandidate(String name, String position, String partylist) {
        String sql = "INSERT INTO candidates(name, position, partylist) VALUES(?,?,?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, position);
            ps.setString(3, partylist);

            ps.executeUpdate();
            System.out.println("Candidate added!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}