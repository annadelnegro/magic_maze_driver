# **Magic Maze Solver Project**

This project consists of a Java-based program to solve maze puzzles and a Python script for testing its functionality. The main objective is to navigate through a series of maze files and verify the solution's correctness.

---

## **Project Structure**

### **Java Files**
- **`MagicMazeDriver.java`**
  - Main driver program for solving mazes.
  - Responsible for loading maze files, applying solving logic, and displaying the results.

### **Python Files**
- **`p2testscript.py`**
  - Automated test script written in Python.
  - Tests the maze solver's correctness by comparing its outputs against expected solutions for several maze files.

### **Sample Solution**
- **`samplesolutionp2.txt`**
  - Contains the results of the Python test script.
  - Demonstrates successful maze solving for all test cases provided.

---

## **Getting Started**

### **Prerequisites**
- Java Development Kit (JDK) 8 or later.
- Python 3.x.
- A text editor or IDE for Java development (e.g., IntelliJ IDEA, Eclipse).
- Python package manager (`pip`) for running the test script.

### **Setup**
1. Clone or download this repository.
2. Place the Java file (`MagicMazeDriver.java`) into a project folder in your Java IDE.
3. Ensure the Python script (`p2testscript.py`) and sample solution (`samplesolutionp2.txt`) are in the same directory.
4. Add the maze files (`maze1.txt`, `maze2.txt`, etc.) to the project directory for testing.

---

## **Usage**

### **Running the Maze Solver**
1. Compile the Java file:
   ```bash
   javac MagicMazeDriver.java
