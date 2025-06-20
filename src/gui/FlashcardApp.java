package gui;

import model.Flashcard;
import db.DatabaseHelper;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FlashcardApp extends JFrame {
    private List<Flashcard> flashcards;
    private int currentIndex = 0;

    public FlashcardApp() {
        setTitle("Flashcard App");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));

        JButton addBtn = new JButton("Add Flashcard");
        JButton reviewBtn = new JButton("Review Flashcards");
        JButton editBtn = new JButton("Edit Flashcard");
        JButton deleteBtn = new JButton("Delete Flashcard");

        panel.add(addBtn);
        panel.add(reviewBtn);
        panel.add(editBtn);
        panel.add(deleteBtn);

        addBtn.addActionListener(e -> openAddDialog());
        reviewBtn.addActionListener(e -> openReviewDialog());
        editBtn.addActionListener(e -> openEditDialog());
        deleteBtn.addActionListener(e -> openDeleteDialog());

        add(panel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void openAddDialog() {
        JTextField questionField = new JTextField();
        JTextField answerField = new JTextField();

        Object[] message = {
            "Question:", questionField,
            "Answer:", answerField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add Flashcard", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String q = questionField.getText().trim();
            String a = answerField.getText().trim();
            if (!q.isEmpty() && !a.isEmpty()) {
                DatabaseHelper.addFlashcard(new Flashcard(q, a));
                JOptionPane.showMessageDialog(this, "Flashcard added!");
            } else {
                JOptionPane.showMessageDialog(this, "Fields cannot be empty.");
            }
        }
    }

    private void openReviewDialog() {
        flashcards = DatabaseHelper.getAllFlashcards();
        if (flashcards.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No flashcards to review.");
            return;
        }

        currentIndex = 0;

        JFrame reviewFrame = new JFrame("Review");
        reviewFrame.setSize(400, 250);
        reviewFrame.setLayout(new BorderLayout());

        JLabel questionLabel = new JLabel("", SwingConstants.CENTER);
        JLabel answerLabel = new JLabel("", SwingConstants.CENTER);
        JButton showBtn = new JButton("Show Answer");
        JButton nextBtn = new JButton("Next");
        JButton prevBtn = new JButton("Previous");

        JPanel contentPanel = new JPanel(new GridLayout(3, 1));
        contentPanel.add(questionLabel);
        contentPanel.add(answerLabel);
        contentPanel.add(showBtn);

        JPanel navPanel = new JPanel();
        navPanel.add(prevBtn);
        navPanel.add(nextBtn);

        reviewFrame.add(contentPanel, BorderLayout.CENTER);
        reviewFrame.add(navPanel, BorderLayout.SOUTH);

        updateReview(questionLabel, answerLabel);

        showBtn.addActionListener(e -> answerLabel.setText("A: " + flashcards.get(currentIndex).getAnswer()));

        nextBtn.addActionListener(e -> {
            if (currentIndex < flashcards.size() - 1) {
                currentIndex++;
                updateReview(questionLabel, answerLabel);
            } else {
                JOptionPane.showMessageDialog(this, "End of flashcards.");
            }
        });

        prevBtn.addActionListener(e -> {
            if (currentIndex > 0) {
                currentIndex--;
                updateReview(questionLabel, answerLabel);
            }
        });

        reviewFrame.setLocationRelativeTo(this);
        reviewFrame.setVisible(true);
    }

    private void updateReview(JLabel question, JLabel answer) {
        question.setText("Q: " + flashcards.get(currentIndex).getQuestion());
        answer.setText("");
    }

    private void openEditDialog() {
        flashcards = DatabaseHelper.getAllFlashcards();
        if (flashcards.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No flashcards to edit.");
            return;
        }

        Flashcard selected = (Flashcard) JOptionPane.showInputDialog(
            this, "Select a flashcard to edit:",
            "Edit Flashcard", JOptionPane.PLAIN_MESSAGE,
            null, flashcards.toArray(), flashcards.get(0)
        );

        if (selected != null) {
            JTextField qField = new JTextField(selected.getQuestion());
            JTextField aField = new JTextField(selected.getAnswer());

            Object[] fields = {
                "New Question:", qField,
                "New Answer:", aField
            };

            int result = JOptionPane.showConfirmDialog(this, fields, "Edit Flashcard", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                selected.setQuestion(qField.getText().trim());
                selected.setAnswer(aField.getText().trim());
                DatabaseHelper.updateFlashcard(selected);
                JOptionPane.showMessageDialog(this, "Flashcard updated!");
            }
        }
    }

    private void openDeleteDialog() {
        flashcards = DatabaseHelper.getAllFlashcards();
        if (flashcards.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No flashcards to delete.");
            return;
        }

        Flashcard selected = (Flashcard) JOptionPane.showInputDialog(
            this, "Select a flashcard to delete:",
            "Delete Flashcard", JOptionPane.PLAIN_MESSAGE,
            null, flashcards.toArray(), flashcards.get(0)
        );

        if (selected != null) {
            int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this flashcard?\nQuestion: " + selected.getQuestion(),
                "Confirm Delete", JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                DatabaseHelper.deleteFlashcard(selected.getId());
                JOptionPane.showMessageDialog(this, "Flashcard deleted!");
            }
        }
    }
}