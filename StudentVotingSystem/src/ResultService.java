import java.sql.*;

public class ResultService {

    public void showResults() {
        String sql = "SELECT name, position, vote_count FROM candidates ORDER BY position";

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("\n--- RESULTS ---");

            while (rs.next()) {
                System.out.println(
                    rs.getString("position") + " - " +
                    rs.getString("name") + " : " +
                    rs.getInt("vote_count") + " votes"
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}