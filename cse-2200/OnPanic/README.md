# OnPanic - Study Friend

## Overview
**OnPanic** is a Java-based productivity application designed to help students manage their study sessions and tasks effectively. It combines time-based interval sessions with a task management system to improve focus and reduce academic panic.

## Key Features

### 1. **Smart Interval Timer**
- **Default Duration**: 25 minutes (standard focus session)
- **Preset Options**:
  - 25-minute focus session
  - 50-minute deep work session
  - 5-minute break timer
- **Display Format**: MM:SS digital clock format (monospaced, bold, 70pt font)
- **Start/Pause Controls**: Users can start and pause the timer at any time
- **Alert System**: When time expires, the app displays a notification saying "Time's up! Take a deep breath."

### 2. **Study Task Checklist**
- **Task Input Field**: Users can type study goals or tasks
- **Add Functionality**: Tasks can be added via:
  - Clicking the "Add Goal" button
  - Pressing the Enter key
- **Checkbox Interface**: Each task appears as a checkbox for tracking completion
- **Scrollable Panel**: Long task lists are supported with scroll functionality

## Technical Architecture

### UI Components
- **Framework**: Java Swing (JFrame-based)
- **Layout Design**: BorderLayout for main structure
- **Color Scheme**: 
  - Background: Light blue-gray (#E0E5EC)
  - Text: Dark blue (#323C50)
  - Task panel: White background

### Core Classes and Methods

| Component | Purpose |
|-----------|---------|
| `OnPanicApp` | Main application class extending JFrame |
| `setupUI()` | Initializes all UI components |
| `setTimer(int seconds)` | Sets/resets timer duration |
| `startTimer()` | Begins countdown with 1-second intervals |
| `updateTimerLabel()` | Updates display in MM:SS format |
| `addTask()` | Adds new checkbox task to the list |
| `createStyledButton(String text)` | Factory method for styled buttons |

### Timer Implementation
- Uses `javax.swing.Timer` with 1000ms intervals
- Decrements `remainingSeconds` each second
- Automatically stops and alerts when reaching 0
- Supports multiple start/stop cycles

### Data Storage
- **In-Memory**: Currently stores tasks and timer state in memory
- No persistent storage (data lost on application exit)
- `remainingSeconds` tracks current countdown value

## User Interface Layout

```
┌─────────────────────────────────────┐
│   onPanic - Study Friend            │
├─────────────────────────────────────┤
│          [Digital Timer Display]    │
│              25:00                  │
├─────────────────────────────────────┤
│  [25m Focus] [50m Deep] [5m Break]  │
├─────────────────────────────────────┤
│     [Start]           [Pause]       │
├─────────────────────────────────────┤
│        Study Goals                  │
│  [Task Input Field] [Add Goal]      │
├─────────────────────────────────────┤
│  ☐ Task 1                           │
│  ☐ Task 2                           │
│  ☑ Task 3 (completed)               │
│  (Scrollable list)                  │
└─────────────────────────────────────┘
```

## How It Works

1. **Initialize**: App launches with 25-minute timer
2. **Set Duration**: Click preset buttons to select focus duration
3. **Add Tasks**: Type goals and press Enter or click "Add Goal"
4. **Start Focus**: Click "Start" to begin countdown
5. **Track Progress**: Check off tasks as they're completed
6. **Get Alert**: Receive notification when timer ends

## Code Quality Observations

### Strengths
- Clear separation of UI setup and logic
- Use of helper methods for code reusability
- Proper font rendering with antialiasing
- User-friendly alert messages
- Keyboard shortcut support (Enter key for task input)

### Areas for Enhancement
- **Persistence**: No save/load functionality for tasks and timer state
- **Customizable Presets**: Timer durations are hardcoded
- **Sound Alerts**: Visual alerts only (no audio notification)
- **Task Deletion**: No way to delete or clear tasks
- **Statistics**: No tracking of completed sessions or productivity metrics
- **Test Coverage**: No unit tests included

## Dependencies
- `javax.swing.*` - GUI framework
- `java.awt.*` - Layout and graphics utilities
- Java 8+ (uses lambda expressions)

## Running the Application

```bash
javac OnPanic.java
java OnPanicApp
```

## Application Properties
- **Window Size**: 500x700 pixels
- **Appearance**: Native look and feel
- **Platform**: Cross-platform (Java-based)

## Target Users
- Students preparing for exams
- Learners using interval-based study techniques
- Anyone seeking a distraction-free study companion

---

**Note**: This is an offline-only application with no internet requirements or external API dependencies.
