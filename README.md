# 🐱 Pawmodoro

A productivity app that combines the *pomodoro* technique with virtual cat care to make studying more engaging and rewarding.

## 📚 Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Team Members](#team-members)
- [Installation](#installation)
- [Usage Guide](#usage-guide)
- [Contributing](#contributing)
- [Feedback](#feedback)
- [License](#license)

## 🎯 Overview

Pawmodoro is a unique study companion that helps you maintain focus while caring for virtual cats. By completing study intervals, you earn rewards to feed and interact with your virtual cats, creating a fun and motivating study environment. 

This project was developed to help students stay focused and motivated while studying, but can be used for any task that requires focus and breaks.

### Why Pawmodoro?
- 🕒 Customizable study and break intervals
- 🐈 Virtual cat companions that respond to your study habits
- 🎵 Integrated music player for focused studying
- 📊 Track your progress and cat's well-being

## ✨ Features

### 1. Timer Management
- Flexible study and break duration settings
- Visual progress tracking

### 2. Virtual Cat Care System
- Feed and interact with virtual cats
- Monitor cat happiness and hunger levels
- Earn rewards through completed study sessions
- Random cat facts

### 3. Music Integration
- Built-in music player
- Volume control
- Study-friendly music recommendations

### 4. Progress Tracking
- Cat well-being metrics


### Features to Come

- Study session statistics
- Level progression system

## 👥 Team Members

-   [Allyssa Chiu (@chiually)](https://github.com/chiually) - Virtual Cat Care
-   [Rachel Deng (@rachelkd)](https://github.com/rachelkd) - Cat Interaction
-   [Jeha Park (@jehapark)](https://github.com/jehapark) - Music Integration
-   [Manahill Sajid (@manahillsajid)](https://github.com/manahillsajid) - Study Session Customization
-   [Jinny Yoo (@yhj050224)](https://github.com/yhj050224) - Timer Management

## 🚀 Installation

Pawmodoro is a Java Swing application, so it requires a Java Runtime Environment on any operating system to run. This project uses Maven to manage dependencies. 

### Prerequisites
- Java JDK 17 or higher
- Maven 3.6 or higher

### Dependencies
Our project uses the following packages:

#### Core Dependencies
- **org.json:json** (v20240303) - JSON processing
- **[com.squareup.okhttp3:okhttp](https://square.github.io/okhttp/)** (v4.12.0) - HTTP client
- **[com.fasterxml.jackson.core:jackson-core](https://github.com/FasterXML/jackson-core)** (v2.15.2) - JSON processing core
- **[com.fasterxml.jackson.core:jackson-databind](https://github.com/FasterXML/jackson-databind)** (v2.15.2) - JSON data binding
- **[org.postgresql:postgresql](https://github.com/pgjdbc/pgjdbc)** (v42.7.1) - PostgreSQL database connector
  - Note that the JDBC driver was initially used for development, but replaced with a PostgreSQL REST API. You may still need to install the JDBC driver to build the project.

#### Testing Dependencies
- **[JUnit 5](https://junit.org/junit5/)** (v5.8.1) - Unit testing framework

### Environment Setup
1. Clone the repository:
   ```bash
   git clone https://github.com/rachelkd/pawmodoro.git
   cd pawmodoro
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Start studying in the Java Swing application!

### Common Installation Issues

#### Network Issues

- An internet connection is required to download Maven dependencies and connect to the database

For any other issues, please check our [Issues page](https://github.com/rachelkd/pawmodoro/issues) or open a new issue.

## 📖 Usage Guide

1. **Getting Started**
   - Create an account or log in
   - Customize your study and break durations
   - Select your first virtual cat companion

2. **During Study Sessions**
   - Start the timer
   - Monitor your progress
   - Complete intervals to earn rewards

3. **Cat Care**
   - Feed your cat with earned rewards
   - Interact with your cat during breaks
   - Monitor happiness and hunger levels

## 🤝 Contributing

We welcome contributions! Please follow these steps:

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to your branch
5. Open a Pull Request

## 📣 Feedback

We value your input! Please share your experience:
- Open an [issue](https://github.com/rachelkd/pawmodoro/issues) on GitHub
  - Report bugs or request features

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---
Made with ❤️ by the Pawmodoro Team
