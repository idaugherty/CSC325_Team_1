# JavaFX Drawing Application

**Assignment 5 – JavaFX GUI + 2D Graphics Drawing App**  
**Course: CSC325**  
**Student Name: Bella Daugherty**  
**Submission Date:** [YYYY-MM-DD]  
**Grade Tier Completed:** ✅ Level 1 | ✅ Level 2 | ✅ Level 3  

## 🖌️ Feature Overview

This JavaFX-based drawing app allows users to draw **rectangles** and **circles** on a canvas by clicking. It includes features across all three levels of the assignment, including shape info display, keyboard shortcuts, and full professional structure.

---

## ✅ Level 1 – Core Features Implemented
- Draw **rectangles** or **circles** with mouse clicks.
- Select shape type via **RadioButtons** grouped in a **ToggleGroup**.
- Clear canvas using a **Button**.
- JavaFX GUI built with **Stage**, **Scene**, **BorderPane**, **VBox**, and **Canvas**.
- Drawing handled using **GraphicsContext**.
- Event handling with `setOnMouseClicked`, `setOnAction`, etc.
- Shapes stored in an **ObservableList**.
- Code organized into:
  - `MainApp` – JavaFX entry point.
  - `DrawingController` – Manages user input and canvas logic.
  - `DrawableShape` – Interface for drawable shapes.
  - `CircleShape` / `RectShape` – Concrete implementations.

---

## 🌟 Level 2 – Feature Expansion Implemented
- Live shape info displayed via a `Label`:
  - Current number of shapes
  - Total area of all shapes
- JavaFX **bindings** used for real-time updates:
  - `IntegerProperty` bound to shape count
  - `DoubleProperty` bound to computed total area
- Tooltips and status text show currently selected shape type
- Basic **MVC structure** implemented:
  - Model: `DrawableShape` and subclasses
  - View: JavaFX UI elements
  - Controller: `DrawingController`
- **Reusable patterns**:
  - `Strategy Pattern`: Shape drawing logic via `DrawableShape` interface
  - `Observer Pattern`: Bindings for real-time updates

---

## 🚀 Level 3 – Professional Polish Implemented
- **Keyboard controls**:
  - `R`: Switch to rectangle tool
  - `C`: Switch to circle tool
  - `X`: Clear canvas
- JavaDoc comments added for all public classes and methods
- Professional documentation (this `README.md`)
- **MenuBar** with keyboard accelerators for:
  - New drawing
  - Clear canvas
  - Exit
- Optional polish:
  - Basic **CSS styling** for UI elements
  - Export canvas as PNG using `WritableImage` + `SnapshotParameters`
- **Video walkthrough** provided (1-minute Loom or screen capture)

---

## 🛠️ How to Compile and Run

### Requirements
- Java 11 or higher
- JavaFX SDK properly installed

### Run via Command Line
```bash
javac --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml *.java
java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml MainApp

### Requirements
- Java 11 or higher
- JavaFX SDK properly installed and configured in your IDE (or via command line)

### Run via Command Line
```bash
javac --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml *.java
java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml MainApp

