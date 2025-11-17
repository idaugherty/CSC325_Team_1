import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {

    private static final String DB_URL = "jdbc:sqlite:facts.db";

    public static void initDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String create = """
                CREATE TABLE IF NOT EXISTS cat_facts (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    fact TEXT NOT NULL,
                    length INTEGER NOT NULL
                );
            """;
            Statement stmt = conn.createStatement();
            stmt.execute(create);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveFact(String fact, int length) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "INSERT INTO cat_facts (fact, length) VALUES (?, ?);";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, fact);
            pstmt.setInt(2, length);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printFacts() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "SELECT id, fact, length FROM cat_facts;";
            ResultSet rs = conn.createStatement().executeQuery(sql);

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                        rs.getString("fact") + " (" +
                        rs.getInt("length") + " chars)");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
