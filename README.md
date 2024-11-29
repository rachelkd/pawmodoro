# 🐱 Pawmodoro

A productivity app that combines the *pomodoro* technique with virtual cat care to make studying more engaging and rewarding.

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Team Members](#team-members)
- [Installation](#installation)
- [Usage Guide](#usage-guide)
- [Contributing](#contributing)
- [Feedback](#feedback)
- [License](#license)

## Overview

Pawmodoro is a unique study companion that helps you maintain focus while caring for virtual cats. By completing study intervals, you earn rewards to feed and interact with your virtual cats, creating a fun and motivating study environment. 

This project was developed to help students stay focused and motivated while studying, but can be used for any task that requires focus and breaks.

### Why Pawmodoro?
- 🕒 Customizable study and break intervals
- 🐈 Virtual cat companions that respond to your study habits
- 🎵 Integrated music player for focused studying
- 📊 Track your progress and cat's well-being

## Features

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

## Team Members

-   [Allyssa Chiu (@chiually)](https://github.com/chiually) - Virtual Cat Care
-   [Rachel Deng (@rachelkd)](https://github.com/rachelkd) - Cat Interaction
-   [Jeha Park (@jehapark)](https://github.com/jehapark) - Music Integration
-   [Manahill Sajid (@manahillsajid)](https://github.com/manahillsajid) - Study Session Customization
-   [Jinny Yoo (@yhj050224)](https://github.com/yhj050224) - Timer Management

## Installation

Pawmodoro is a Java Swing application, so it requires a Java Runtime Environment on any operating system to run. This project uses Maven to manage dependencies. 

### Prerequisites
- Java JDK 17 or higher
- Maven 3.6 or higher

### Dependencies
Our project uses the following packages:

#### Core Dependencies
- **org.json:json** (v20240303) - JSON processing
- **[Okhttp](https://square.github.io/okhttp/)** (v4.12.0) - HTTP client
- **[Jackson Core](https://github.com/FasterXML/jackson-core)** (v2.15.2) - JSON processing core
- **[Jackson Databind](https://github.com/FasterXML/jackson-databind)** (v2.15.2) - JSON data binding
- **[PostgresJDBC](https://github.com/pgjdbc/pgjdbc)** (v42.7.1) - PostgreSQL database connector
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

## Usage Guide

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

## Contributing

We welcome contributions to Pawmodoro! Whether you’re fixing a bug, adding features, or improving documentation, follow the steps below.

1. **How to Contribute**  
   - Fork the repository on GitHub.  
   - Create a feature branch for your changes.  
   - Submit a pull request (PR) with a clear description of your changes.

2. **Instructions for Forking**  
   - Go to the [Pawmodoro](https://github.com/rachelkd/pawmodoro) repository.  
   - Click the "Fork" button at the top right.  
   - Clone your forked repository to your local machine:

     ```bash
     git clone https://github.com/your-username/pawmodoro.git
     cd pawmodoro
     ```
   - Create a new branch:

     ```bash
     git checkout -b feature/your-feature-name
     ```

3. **Guidelines for a Good Merge Request**  
   - Write a clear and descriptive title and PR message.  
   - Ensure the PR is focused on one feature or fix.  
   - Include any relevant test cases or documentation updates.

4. **Contribution Rules**  
   - Contributions must align with project goals and scope.  
   - Avoid submitting incomplete features unless marked as "work in progress (WIP)."  
   - All code changes must include tests to verify functionality.

5. **Closed Contributions**  
   - If contributions are temporarily closed, this README will be updated.

## Feedback

We value your input! Please share your experience:

1. **How to Give Feedback**
    - Open an [issue](https://github.com/rachelkd/pawmodoro/issues) on GitHub to submit bugs or feature requests.

2. **Rules for Feedback**
    - Be specific: Include details about the issue or suggestion (e.g., exact steps to reproduce a bug, photos, or videos).
    - Be constructive: Focus on how to improve rather than just pointing out flaws.
    - Avoid unrelated or offensive comments.

3. **What to Expect When Submitting Feedback**
    - Follow-up: If more details are needed, a team member may reach out to you.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---
