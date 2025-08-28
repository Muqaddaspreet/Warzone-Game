# 🗺️ Warzone Game

A turn-based strategy game inspired by the popular board game Risk, implemented in Java using Object-Oriented Programming principles and multiple design patterns such as Strategy, State, Command, Observer, and Adapter.

Players conquer territories, issue commands, deploy and move armies, and use special cards to outmaneuver opponents. The system supports both human and AI players (Aggressive, Benevolent, Cheater, Random) and features tournament mode, map editing, game persistence, and dynamic player behaviors.

---

## 🧩 Features

- **Turn-Based Gameplay** – Players take turns issuing and executing commands.
- **Tournament Mode** – Run automated matches using varying player strategies.
- **Map Editor** – Create and load custom maps (supports Domination and Conquest formats).
- **Game Save/Load** – Persist and reload game state using `GameService`.
- **Player Behaviors** – Strategy pattern for dynamic behavior: Aggressive, Benevolent, Cheater, Random, and Human.
- **Card System** – Use Bomb, Blockade, Airlift, and Diplomacy cards for tactical advantage.
- **Flexible Command Engine** – Commands like Deploy and Advance follow the Command pattern.
- **Phase-Based Engine** – Issue orders, execute moves, and transition across game phases using State pattern.
- **Custom Maps via Adapter** – Easily add new map formats with Adapter Pattern support.
- **Logger (Observer Pattern)** – Logs gameplay events automatically during each phase.
- **Exception Handling** – Catch invalid commands and maps via custom exceptions.

---

## 🛠️ Tech Stack

- **Language**: Java
- **Build Tool**: Maven (`pom.xml`)
- **Design Patterns**: Strategy, State, Command, Observer, Adapter, Builder
- **Architecture**: MVC

---

## 🗃️ Project Structure

```text
.
├── src/
│   ├── models/         # Core game entities: Player, Map, Country, GameState
│   ├── view/           # UI layer: ShowMap, TournamentView, LogView
│   ├── controller/     # GameEngineController & phase management
│   ├── services/       # MapService, GameService, PlayerService
│   ├── strategies/     # PlayerBehaviorStrategy (Aggressive, Random, etc.)
│   ├── commands/       # Deploy, Advance (Command Pattern)
│   ├── cards/          # Bomb, Blockade, Airlift, Diplomacy
│   ├── maps/           # Adapter support: DominationMap, ConquestMap
│   ├── constants/      # AppConstants
│   ├── exceptions/     # InvalidMap, InvalidCommand
│   ├── common/         # CommonCode, Command handler
│   └── logger/         # Observer-based logging
├── documentation/      # Class diagrams, refactor reports
├── pom.xml             # Maven project config
└── README.md
```

---

## 🧠 Design Patterns Used

- **Strategy Pattern** – For dynamic player behaviors.
- **State Pattern** – For transitioning between game phases (Startup, IssueOrder, OrderExecution).
- **Command Pattern** – To encapsulate commands (Advance, Deploy).
- **Observer Pattern** – For centralized logging of game events.
- **Adapter Pattern** – To support multiple map formats (Domination, Conquest).
- **Builder Pattern** – To improve command creation logic (recommended refactor).

---

## 🛠️ Refactoring Highlights

- **Encapsulation**: Converted public variables to private with getters.
- **Error Handling**: Used custom exceptions and improved IO error tracing.
- **Decomposition**: Split large methods like `showMap()` and `updatePlayers()` into smaller units.
- **Dependency Injection**: Applied to services like `MapService` for better testing and decoupling.
- **Java 8 Streams**: Introduced for improved readability in collections.
- **Optional API**: Used in place of `null` where applicable (e.g., `findCountryByName`).
- **GameService**: Separated game state logic (save/load) from phase logic.

---

## 🧪 Testing

Unit tests were created/modified for:

- Strategy Behaviors (`AggressiveTest`, `BenevolentTest`)
- Map Adapters (`ConquestMapTest`, `DominationMapTest`)
- Game State Save/Load (`GameEngineControllerTest`)
- Tournament logic (View classes)
- Phase transitions and player commands

---

## 🚀 Getting Started

### Prerequisites

- Java 8+
- Maven

### Build & Run

```bash
# Clone the repo
git clone https://github.com/<your-username>/Warzone-Game.git
cd Warzone-Game

# Build
mvn clean install

# Run the game
mvn exec:java -Dexec.mainClass="main.GameEngineController"
```

Replace `main.GameEngineController` with your actual entry point if different.

---

## 🗺️ Gameplay Overview

- **Startup Phase** – Players added, map loaded/edited.
- **Issue Order Phase** – Each player issues orders (Deploy, Advance).
- **Order Execution Phase** – Orders are executed sequentially.
- **Card Effects** – Players use earned cards strategically.
- **Win Condition** – When a single player dominates all territories.

---

## 📦 Deployment

This is a standalone Java application. You can:

- Package it into a `.jar` using `mvn package`
- Run using:

```bash
java -jar target/warzone-game.jar
```

---

## 📌 Roadmap

- GUI (JavaFX or Swing)
- Multiplayer over network
- Persistent storage (JSON or DB-based save)
- AI improvements
- Map validation tools
- Replay system

---

## 🙌 Acknowledgements

- Java Design Patterns
- UML Class Diagrams & Clean Code Principles
- Risk Board Game Mechanics
