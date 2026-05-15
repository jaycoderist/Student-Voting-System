import java.sql.*;

public class AdminDAO {

    public boolean login(String username, String password) {
        String sql = "SELECT * FROM admins WHERE username=? AND password=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            return ps.executeQuery().next();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void closeElection() {
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement()) {

            st.executeUpdate("UPDATE election_settings SET status='CLOSED' WHERE id=1");
            System.out.println("Election CLOSED!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openElection() {
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement()) {

            st.executeUpdate("UPDATE election_settings SET status='OPEN' WHERE id=1");
            System.out.println("Election OPEN!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isElectionOpen() {
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT status FROM election_settings WHERE id=1")) {

            if (rs.next()) {
                return rs.getString("status").equalsIgnoreCase("OPEN");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}