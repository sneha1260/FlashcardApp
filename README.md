# Flashcard Study App

A simple Java Swing-based application to create, review, edit, and delete flashcards for studying.

## Features
- Add new flashcards
- Review flashcards with questions and answers
- Edit existing flashcards
- Delete flashcards
- Data stored persistently using SQLite

## Technologies Used
- Java
- Swing for GUI
- SQLite (via JDBC)

## How to Run
1. Clone the repository
2. Compile:

javac -cp "lib/sqlite-jdbc-3.49.1.0.jar" -d bin src***.java

3. Run:

java -cp "bin;lib/sqlite-jdbc-3.49.1.0.jar" Main