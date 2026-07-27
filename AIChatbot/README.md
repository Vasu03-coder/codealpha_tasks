# AI Chatbot

A professional Java console-based Artificial Intelligence Chatbot built for the CodeAlpha Java Programming Internship (Task 3). The chatbot interacts with users using Natural Language Processing (NLP) techniques, rule-based responses, keyword matching, and conversation history.

## Features

- **Natural Language Processing**: Text cleaning, tokenization, keyword extraction, and normalization
- **Greeting & Farewell Detection**: Recognizes greetings (hello, hi, good morning) and farewells (bye, exit, goodbye)
- **FAQ Knowledge Base**: 60+ predefined questions and answers covering Java, OOP, Programming, Data Structures, Algorithms, Databases, Git, GitHub, HTML, CSS, JavaScript, Python, AI, Machine Learning, and more
- **Keyword Matching**: Intelligent fallback that searches keywords when an exact match is not found
- **Math Calculator**: Evaluates simple arithmetic expressions (addition, subtraction, multiplication, division)
- **Date & Time**: Displays current time, date, and day of the week
- **Random Joke Generator**: 25 programming jokes for entertainment
- **Motivation Quotes**: 25 inspiring programming quotes
- **Conversation History**: Saves all interactions to `chat_history.txt`
- **Session Statistics**: Tracks questions asked, greetings exchanged, unknown questions, and session duration
- **Help Command**: Displays all available commands
- **Fully Offline**: No external APIs or internet connection required

## Technologies Used

- **Java** (JDK 8+)
- **Java Collections** (HashMap, ArrayList)
- **File I/O** (BufferedReader, BufferedWriter)
- **Date/Time API** (LocalDateTime)
- **Object-Oriented Programming** principles

## Project Structure

```
AIChatbot/
│
├── Main.java                 # Entry point and user interface loop
├── ChatBot.java              # Response coordination and generation
├── NLPProcessor.java         # Text cleaning, tokenization, keyword extraction
├── KnowledgeBase.java        # FAQ database, jokes, quotes, keyword mapping
├── ConversationManager.java  # Chat history, session statistics, tracking
├── FileManager.java          # File read/write operations for chat history
├── Utils.java               # Banner, formatting, date/time, random utilities
├── chat_history.txt          # Saved conversation history
└── README.md                 # Project documentation
```

## How to Compile

```bash
cd AIChatbot
javac Main.java
```

Or compile all files individually:

```bash
javac Utils.java NLPProcessor.java KnowledgeBase.java FileManager.java ConversationManager.java ChatBot.java Main.java
```

## How to Run

```bash
java Main
```

**Note:** Ensure you are in the `AIChatbot` directory when running the commands.

## Sample Output

```
========================================
         WELCOME TO AI CHATBOT
    CodeAlpha Java Programming Internship
========================================

Session started at: 2025-01-15 14:30:00
========================================
           AI CHATBOT
    CodeAlpha Java Programming Internship
========================================

Type your question.
Type HELP for commands.
Type EXIT to quit.
----------------------------------------
User : Hello
Bot  : Hello! How can I assist you today?
----------------------------------------
User : What is Java?
Bot  : Java is a high-level, object-oriented programming language developed by Sun Microsystems in 1995...
----------------------------------------
User : Tell me a joke
Bot  : Why do Java developers wear glasses? Because they can't C#!
----------------------------------------
User : 12+8
Bot  : The answer is: 20
----------------------------------------
User : What time is it?
Bot  : Current time is: 14:35:22
----------------------------------------
User : bye
Bot  : Goodbye! Have a great day!
----------------------------------------

SESSION STATISTICS
----------------------------------------
Questions Asked    : 4
Greetings Exchanged: 1
Unknown Questions  : 0
Total Responses    : 5
Session Duration   : 0h 5m 22s
----------------------------------------

Thank you for using AI Chatbot!
Session ended at: 2025-01-15 14:35:22
----------------------------------------
```

## OOP Design

| Class | Responsibility |
|-------|---------------|
| `Main.java` | Entry point, menu loop, user interaction |
| `ChatBot.java` | Coordinates chatbot components, generates responses |
| `NLPProcessor.java` | Text cleaning, normalization, keyword extraction |
| `KnowledgeBase.java` | FAQ HashMap, keyword mappings, jokes, quotes |
| `ConversationManager.java` | Tracks history, statistics, session details |
| `FileManager.java` | Read/write operations for `chat_history.txt` |
| `Utils.java` | Banner, formatting, date/time, random utilities |

## Available Commands

- Ask questions about programming, technology, etc.
- Say **hello**, **hi**, **good morning** (Greetings)
- Say **bye**, **exit**, **goodbye** (Exit chatbot)
- Type **HELP** to see the help menu
- Type **JOKE** to hear a programming joke
- Type **QUOTE** for an inspiring quote
- Type **TIME** to see current time
- Type **DATE** to see today's date
- Type **STATS** to see session statistics
- Type **HISTORY** to see conversation history
- Type math expressions (e.g., `2+5`, `12*3`, `100/5`) for calculations

## Future Enhancements

- Add sentiment analysis for user emotions
- Implement more advanced NLP using regular expressions
- Add support for multiple languages
- Integrate a simple GUI
- Add machine learning-based responses using pre-trained models
- Implement text-to-speech for bot responses
- Add user profile management

## License

This project is created for educational purposes as part of the CodeAlpha Java Programming Internship.
