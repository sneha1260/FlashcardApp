#  Flashcard Study App

A Java-based desktop application that allows users to create, review, edit, and delete flashcards for study and revision purposes. Built with **Swing for GUI** and **SQLite** for persistent data storage.

---

##  Features

-  **Add Flashcards** – Input custom question-answer pairs
-  **Review Flashcards** – View questions and show answers interactively
-  **Edit Flashcards** – Modify existing flashcards easily
-  **Delete Flashcards** – Remove unwanted flashcards
-  **Persistent Storage** – Uses SQLite database via JDBC
-  **Simple User Interface** – GUI built using Java Swing

---

## Technologies Used

- Java
- Swing (for GUI)
- SQLite (via JDBC)
- JDBC Driver: [`sqlite-jdbc-3.49.1.0.jar`](https://github.com/xerial/sqlite-jdbc)

---

##  Project Structure

FlashcardApp/
├── src/
│ ├── Main.java
│ ├── model/
│ │ └── Flashcard.java
│ ├── db/
│ │ └── DatabaseHelper.java
│ └── gui/
│ └── FlashcardApp.java
├── lib/
│ └── sqlite-jdbc-3.49.1.0.jar
├── bin/ (compiled classes)
├── flashcards.db (auto-generated)
└── README.md


---

## How to Run

###  Prerequisites:
- Java JDK installed (version 8 or above)
- VS Code or any IDE
- SQLite JDBC JAR (`lib/sqlite-jdbc-3.49.1.0.jar`)

###  Compile the Project:

```bash
javac -cp "lib/sqlite-jdbc-3.49.1.0.jar" -d bin src\Main.java src\model\Flashcard.java src\db\DatabaseHelper.java src\gui\FlashcardApp.java

## Run the App:

java -cp "bin;lib/sqlite-jdbc-3.49.1.0.jar" Main

 A SQLite database file (flashcards.db) will be automatically created in the root directory after you run the app.

## JAR Packaging (Optional)
If you want to package this app into a runnable JAR:

jar cfe FlashcardApp.jar Main -C bin .

Then run it with:

java -cp "FlashcardApp.jar;lib/sqlite-jdbc-3.49.1.0.jar" Main