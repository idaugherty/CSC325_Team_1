📘 Cat Facts API Client — README

This project is a simple Java application that retrieves random cat facts from a public API and stores them in a local SQLite database. It includes a small GUI/console interface to fetch a fact, save it, and display all stored facts.

🐱 Chosen API
Cat Facts API

Endpoint used:

https://catfact.ninja/fact

Stored Data Fields
API Field	Description	Stored in DB
fact	The cat fact text	✔
length	Length of the fact text	✔
id (auto-increment)	Database primary key, not from API	✔

Your SQLite table should look like:

CREATE TABLE IF NOT EXISTS cat_facts (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    fact TEXT,
    length INTEGER
);

🚀 How to Run the Application
✔ 1. Requirements

Java 8 or higher

SQLite JDBC driver
Example file:

sqlite-jdbc-3.51.0.0.jar

✔ 2. Compile the Program

From inside the project directory:

javac -cp ".;sqlite-jdbc-3.51.0.0.jar" *.java

✔ 3. Run the Program
java -cp ".;sqlite-jdbc-3.51.0.0.jar" APIClientGUI


If you're on macOS/Linux, replace semicolon ; with a colon ::

java -cp ".:sqlite-jdbc-3.51.0.0.jar" APIClientGUI

🗄 Database Setup
✔ Automatic Method

If your project contains this in Database.java:

CREATE TABLE IF NOT EXISTS cat_facts (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    fact TEXT,
    length INTEGER
);


Then the database will be created automatically.

❗ Troubleshooting: “no such column: length”

If you modified the schema recently, fix the error by deleting the old DB:

cat_facts.db


Or manually add the missing column:

ALTER TABLE cat_facts ADD COLUMN length INTEGER;

🧠 Major Code Function Explanations
APIClient.java
// Calls the Cat Facts API and returns a JSON string
public static String getCatFact() { ... }

APIClientGUI.java
// Controls program flow: fetch fact, save it, then print all stored facts
public static void main(String[] args) { ... }

Database.java
// Initializes database connection and creates table if needed
private static Connection connect() { ... }

// Inserts one fact into the database
public static void saveFact(String fact, int length) { ... }

// Prints all stored facts from the database
public static void printFacts() { ... }

📸 Sample Output
Fetching fact from API...

Cat fact saved!

--- STORED FACTS ---
1 | Cats have five toes on their front paws, but only four on the back. | length = 72
2 | A house cat can reach speeds of up to 30 mph. | length = 45