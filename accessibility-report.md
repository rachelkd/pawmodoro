# Accessibility Report for Pawmodoro

## Universal Design Principles

#### 1. Equitable Use
- Simple toggle controls for music playback make it usable for users with varying technical expertise.
- Future improvements: Add screen reader support for users with visual impairments and support multiple input methods (e.g. voice commands)

### 2. Flexibility in Use
- Users can customize study and break intervals to their preferences.
- Music can be toggled on/off based on individual needs
- Future improvement: Add different audio options (e.g. different genres)

### 3. Simple and Intuitive Use
- Clear button labels ("Start Timer"/"Stop Timer")
- Straightforward timer display showing remaining time
- Visual feedback for current session state (study/break)
- Buttons are consistent in their functionality

### 4. Perceptible Information
- Error messages clearly communicate issues with user input in login/signup screens
- Timer displays time in standard format (MM:SS)
- Audio playing state is visually indicated by the updating play/pause button

### 5. Tolerance for Error
- Clear error messages are displayed when the user enters invalid input (e.g. incorrect password, invalid username)

### 6. Low Physical Effort
- Single-click button functionality for all main actions (start/stop timer, toggle music)
- No need to navigate through multiple menus or dialogs to perform an action
- Automatic transition between study and break sessions

### 7. Size and Space for Approach and Use
This principle is less applicable to our project, as it is a web app and the user interface is not constrained by physical space. If we were to think about this principle in the context of an application:
- Further improvements: Add a settings option to adjust size and layout of the UI and ensure the clickable elements are scalable for different display resolutions.

## Target Market
Pawmodoro is designed primarily for students (of any level) and people who struggle with maintaining focus during study or work sessions. Our target users are individuals who enjoy gamification elements and will find motivation through Pawmodoro's virtual pet care mechanics. The app particularly appeals to those who appreciate cute aesthetics and want a more engaging alternative to traditional Pomodoro timers. While the core functionality serves productivity needs, the addition of virtual cats and ambient music makes it especially appealing to users who find conventional productivity techniques too monotonous or uninspiring. This includes university students needing study motivation, remote workers managing work-from-home distractions, and anyone who enjoys combining productivity with motivating games.

## Demographic Accessibility Concerns
Pawmodoro, in its current implementation, presents several barriers to accessibility that may exclude certain demographics. Most notably, users with visual impairments may struggle with our cat sprites and UI elements, as we have not implemented screen reader support or high-contrast options. The audio-based ambient noise feature, while engaging for many users, creates a barrier for deaf or hard-of-hearing users since we do not provide alternative sensory feedback (i.e. visual feedback) other than the timer display. Additionally, our reliance on mouse interactions for most controls may exclude users with motor impairments who rely on alternative input methods (e.g. Sip-and-Puff, keyboard, other assistive input devices). From a socioeconomic perspective, our desktop application might be less accessible to users who primarily access computers through public facilities or have limited access to personal computers. These limitations reflect both the medical model (where individuals impairments affect usage) and the social model (where our design choices create barriers) of disability. To address these concerns and promote relational equality, future versions of Pawmodoro should implement features like keyboard navigation, screen reader compatibility, visual alternatives to audio feedback, and potentially a web-based version for broader accessibility.