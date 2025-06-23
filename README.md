# 🏛️ Columbus Project: New Heaven

## 📌 Overview
**Columbus Project: New Heaven** is a **turn-based strategy game** inspired by *Catan*, developed as part of the **Programming Methodology** course @Chulalongkorn University. The game is designed using **Java and JavaFX**, implementing **Object-Oriented Programming (OOP) principles** such as **inheritance, polymorphism, encapsulation, and abstraction**.

Set in a post-apocalyptic world, players take on the role of factions competing to colonize *New Heaven*, a habitable planet discovered after Earth's destruction. Players must strategically build structures, manage resources, and engage in combat to ensure their civilization's survival.

## 🎮 Features
- 🌍 **Hexagonal Tile Map** – Players build on and control hexagonal tiles.
- 🏗️ **Strategic City Building** – Construct and upgrade buildings with resources.
- ⚔️ **Combat System** – Attack opponents using **Military Camps** and **Missile Fortresses**.
- 🎲 **Resource Management** – Dice rolls determine resource distribution.
- 🔄 **Turn-Based Gameplay** – Players take turns choosing actions like building, attacking, and trading resources.
- 🏆 **Victory Conditions** – The game ends when a player's **Colony HP** reaches zero.

## 🏗️ Technologies Used
- **Language**: Java  
- **Framework**: JavaFX  
- **Programming Concepts**: Object-Oriented Programming (OOP), Data Structures  
- **Design Patterns**: Factory Pattern, Singleton Pattern  

## 🛠️ OOP Concepts Applied
- **Encapsulation** – Game logic is modularized within well-structured classes.
- **Inheritance** – Different buildings (Quarry, Factory, Military Camp) extend a base **Building** class.
- **Polymorphism** – Various game actions (attack, produce, build) share common interfaces.
- **Abstraction** – Clear separation of concerns between UI, game logic, and data models.

## 🎲 Game Rules & Mechanics
- Each player starts with a **Colony (20 HP)** at opposite ends of the map.
- Players can perform **one action per turn**:
  - 🏗 **Build/Upgrade**: Construct buildings (e.g., **Quarries**, **Factories**, **Military Camps**).
  - 🏭 **Produce**: Generate resources from existing buildings.
  - ⚔️ **Attack**: Target opponent’s structures using attackable buildings.
  - 🔄 **Alchemize**: Convert one resource type into another.
- Players roll **two dice** at the end of their turn to generate resources.
- The game ends when a player’s **Colony HP reaches zero**.

## 📜 How to Play
1. **Start the game** – Players begin with a **Colony** and limited resources.
2. **Expand and build** – Collect resources, build structures, and upgrade buildings.
3. **Strategize** – Balance economy, defense, and offense against the opponent.
4. **Engage in combat** – Attack to weaken the opponent’s colony while protecting your own.
5. **Win the game** – The first player to reduce their opponent’s **Colony HP to 0** wins.
   
## 📄 Documentation

- 📘 [Game Document and Rules (PDF)](Project Report Comlunbus Project.pdf)

## 🔧 Installation & Setup
1. Clone the repository
2. cd to the folder
3. use this command in cmd to run the game : java -jar --module-path <your javaFX lib> --add-modules javafx.controls,javafx.graphics,javafx.media,javafx.fxml Columbus.jar
