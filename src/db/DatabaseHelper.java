package db;

import model.Flashcard;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {
    private static final String DB_URL = "jdbc:sqlite:flashcards.db";

    static {
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS flashcards (" +
                         "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                         "question TEXT NOT NULL, " +
                         "answer TEXT NOT NULL)";
            stmt.execute(sql);
            System.out.println("✅ Database initialized.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static List<Flashcard> getAllFlashcards() {
        List<Flashcard> cards = new ArrayList<>();
        String sql = "SELECT * FROM flashcards";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                cards.add(new Flashcard(rs.getInt("id"), rs.getString("question"), rs.getString("answer")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cards;
    }

    public static void addFlashcard(Flashcard f) {
        String sql = "INSERT INTO flashcards(question, answer) VALUES (?, ?)";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, f.getQuestion());
            pstmt.setString(2, f.getAnswer());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateFlashcard(Flashcard f) {
        String sql = "UPDATE flashcards SET question = ?, answer = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, f.getQuestion());
            pstmt.setString(2, f.getAnswer());
            pstmt.setInt(3, f.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteFlashcard(int id) {
        String sql = "DELETE FROM flashcards WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
