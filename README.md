# Pawmodoro

## Team Members

-   [Allyssa Chiu (@chiually)](https://github.com/chiually)
-   [Rachel Deng (@rachelkd)](https://github.com/rachelkd)
-   [Jeha Park (@jehapark)](https://github.com/jehapark)
-   [Manahill Sajid (@manahillsajid)](https://github.com/manahillsajid)
-   [Jinny Yoo (@yhj050224)](https://github.com/yhj050224)

## User Stories and Use Cases

### Authentication (Team)

**User Story:** As a new user, I want to create a Pawmodoro account to increase my productivity while studying.

**Use Cases:**

-   Register new user (create username/password)
-   Login user
-   Logout user
-   Manage user profile

### Timer Management (Assigned to: Jinny)

**User Story:** As a user, I want a timer displaying my progress that can be set to intervals of my choosing.

**Use Cases:**

-   Start/pause/resume timer
-   Set custom interval duration
-   Track interval progress
-   Notify user when interval completes

### Study Session Customization (Assigned to: Manahill)

**User Story:** As a user, I want to be able to choose the length of my study and break sessions independently. That is, the amount of time I have for a break doesn’t depend on how much I study for, and vice versa.

**Use Cases:**

-   Customize study duration
-   Customize break duration
-   Save timer preferences
-   Switch between study/break modes

### Virtual Cat Care (Assigned to: Allyssa)

**User Story:** As a user, I want to be able to feed a cat and manage its happiness based on my study performance. I want the system to decrease a cat’s happiness when I stop studying as a penalty.

**Use Cases:**

-   Feed cat after completed intervals
-   Track cat happiness
-   Apply happiness penalties
-   Handle cat status changes

### Cat Interaction (Assigned to: Rachel)

**User Stories:**

1. As a user, I want to interact with my virtual cat through clicks to hear meows and learn cat facts.
2. As a user, I want to view my cat's statistics (hunger and happiness levels) when I click on the cat.

**Use Cases:**

-   Display cat animations
-   Show random cat facts
-   Display cat statistics
    -   Show current happiness level
    -   Show current hunger level
-   Update statistics display in real-time

**Interactions:**

-   `CatInteractionInteractor` → `InteractionController` → `InteractionPresenter`
-   `FactDisplayInteractor` → `FactController` → `FactPresenter`
-   `CatStatisticsInteractor` → `StatisticsController` → `StatisticsPresenter`

**Data Flow:**

1. User clicks cat
2. System randomly decides between:
    - Playing meow sound and animation
    - Displaying a cat fact
    - Showing cat statistics
3. Statistics view includes:
    - Visual representation of happiness (0-100%)
    - Visual representation of hunger (0-100%)
    - Basic cat information

### Music Integration (Assigned to: Jeha)

**User Story:** As a user, I want to be able to listen to music while completing a study interval.

**Use Cases:**

-   Play/pause music
-   Select playlists
-   Adjust volume
-   Track music preferences

## Entities

### User

-   `username`
-   `password`
-   `level`
-   `levelProgress`
-   `totalFocusTime`
-   `List<Cat> cats`

### Cat

-   `name`
-   `happiness` (0-100)
-   `hunger` (0-100)

### Timer

-   `workDuration`
-   `shortBreakDuration`
-   `longBreakDuration`
-   `currentState` (enum: WORKING, SHORT_BREAK, LONG_BREAK, PAUSED)
-   `startTime`
-   `completedIntervals`
-   `cyclePosition` (0-3: tracks position in the work-break cycle)

### Food (Interface)

Methods:

-   `getHungerRestoration()`
-   `getHappinessBoost()`
-   `getName()`

Implementations:

-   WetFood
-   DryFood
-   Milk

### Statistics

-   `totalFocusMinutes`
-   `totalBreakMinutes`
-   `completedWorkIntervals`
-   `completedFullCycles`
-   `interruptedSessions`
-   `date`
-   `foodItemsUsed`
-   `previousCats` (tracks runaway cats)

### MusicPlayer

-   `currentPlaylist`
-   `isPlaying`
-   `volume`
-   `currentTrack`
-   `spotifyAccessToken`
