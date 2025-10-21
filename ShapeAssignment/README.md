# CSC325_Team_1

## Overview
This project contains Java implementations of various geometric shapes using abstract classes and interfaces. It demonstrates object-oriented principles such as inheritance, abstraction, and interface implementation.

## Structure
- `shapes.abstractdesign/Shape.java`: Abstract base class for all shapes.
- `shapes.abstractdesign/Rectangle1.java`: Rectangle implementation.
- `shapes.abstractdesign/Circle1.java`: Circle implementation.
- `Hexagon.java`: Hexagon implementation.
- `Main.java`: Main entry point to run and test the shapes.
- `shapes.interfacedesign/Drawable.java`, `Calculable.java`, `Describable.java`: Interfaces for shape behaviors.

## How to Run
1. Ensure you have Java 21 or later installed.
2. Compile all `.java` files:
   ```
   javac shapes/abstractdesign/*.java Hexagon.java Main.java shapes/interfacedesign/*.java
   ```
3. Run the main class:
   ```
   java Main
   ```


## Why This Comparison Matters in Real-World Design
Understanding the differences between interfaces and abstract classes is crucial for building flexible, maintainable, and scalable software. Abstract classes allow you to define shared base functionality and state, while interfaces enable you to specify contracts that multiple classes can implement, regardless of their inheritance hierarchy. This comparison helps developers choose the right approach for code reuse, extensibility, and enforcing consistent behavior across diverse components. In real-world projects, making the right choice leads to cleaner architecture, easier testing, and better collaboration within teams.

## Features
- Calculate area for each shape.
- Draw and describe shapes using interface methods.
- Demonstrates use of JavaDoc comments for documentation.

## Authors
- Team 1

## License
This project is for educational purposes.
