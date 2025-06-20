package model;

public class Flashcard {
    private int id;
    private String question;
    private String answer;

    public Flashcard(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public Flashcard(int id, String question, String answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String q) {
        this.question = q;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String a) {
        this.answer = a;
    }

    @Override
    public String toString() {
        return question;
    }
}
