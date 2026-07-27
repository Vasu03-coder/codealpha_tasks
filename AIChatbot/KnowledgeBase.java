package AIChatbot;

import java.util.*;

public class KnowledgeBase {

    private final Map<String, String> faq;
    private final Map<String, List<String>> keywordMap;
    private final String[] jokes;
    private final String[] quotes;

    public KnowledgeBase() {
        faq = new HashMap<>();
        keywordMap = new HashMap<>();
        jokes = new String[25];
        quotes = new String[25];
        loadFAQ();
        loadKeywordMap();
        loadJokes();
        loadQuotes();
    }

    private void loadFAQ() {
        faq.put("what is java", "Java is a high-level, object-oriented programming language developed by Sun Microsystems in 1995. It is platform-independent, secure, and widely used for enterprise applications, Android development, and web services.");
        faq.put("what is oop", "Object-Oriented Programming (OOP) is a programming paradigm based on the concept of objects, which contain data and methods. Key principles include Encapsulation, Inheritance, Polymorphism, and Abstraction.");
        faq.put("what is encapsulation", "Encapsulation is the mechanism of wrapping data and methods together as a single unit, restricting direct access to some components. It is achieved through private fields and public getters/setters.");
        faq.put("what is inheritance", "Inheritance is an OOP concept where a class inherits properties and methods from another class, promoting code reusability. In Java, it is achieved using the 'extends' keyword.");
        faq.put("what is polymorphism", "Polymorphism allows objects to take multiple forms. In Java, it is achieved through method overloading (compile-time) and method overriding (runtime).");
        faq.put("what is abstraction", "Abstraction hides implementation details and shows only essential features. In Java, it is achieved using abstract classes and interfaces.");
        faq.put("what is data structure", "A data structure is a way of organizing and storing data efficiently. Common types include arrays, linked lists, stacks, queues, trees, and graphs.");
        faq.put("what is array", "An array is a collection of elements of the same type stored in contiguous memory locations. It has a fixed size and provides O(1) random access.");
        faq.put("what is linked list", "A linked list is a linear data structure where elements (nodes) are connected via pointers. Each node contains data and a reference to the next node.");
        faq.put("what is stack", "A stack is a linear data structure that follows the LIFO (Last In, First Out) principle. Operations include push, pop, and peek.");
        faq.put("what is queue", "A queue is a linear data structure that follows the FIFO (First In, First Out) principle. Elements are added at the rear and removed from the front.");
        faq.put("what is tree", "A tree is a hierarchical data structure with a root node and child nodes. Binary trees, binary search trees, and AVL trees are common types.");
        faq.put("what is graph", "A graph is a non-linear data structure consisting of vertices (nodes) and edges (connections). It can be directed or undirected, weighted or unweighted.");
        faq.put("what is algorithm", "An algorithm is a step-by-step procedure for solving a problem or performing a computation. Examples include sorting, searching, and graph traversal algorithms.");
        faq.put("what is sorting", "Sorting is the process of arranging elements in a specific order (ascending or descending). Common algorithms include bubble sort, merge sort, and quicksort.");
        faq.put("what is binary search", "Binary search is an efficient algorithm for finding an element in a sorted array by repeatedly dividing the search interval in half. It has O(log n) time complexity.");
        faq.put("what is time complexity", "Time complexity measures the amount of time an algorithm takes to run as a function of input size. It is expressed using Big O notation, e.g., O(1), O(n), O(log n).");
        faq.put("what is database", "A database is an organized collection of structured data stored electronically. It is managed by a Database Management System (DBMS).");
        faq.put("what is sql", "SQL (Structured Query Language) is a standard language for managing and manipulating relational databases. Commands include SELECT, INSERT, UPDATE, and DELETE.");
        faq.put("what is mysql", "MySQL is an open-source relational database management system. It uses SQL and is widely used for web applications.");
        faq.put("what is git", "Git is a distributed version control system for tracking changes in source code during software development. It was created by Linus Torvalds in 2005.");
        faq.put("what is github", "GitHub is a web-based platform for hosting Git repositories. It provides collaboration features like pull requests, issues, and project management.");
        faq.put("what is version control", "Version control is a system that records changes to files over time, allowing developers to revert to previous versions and collaborate efficiently.");
        faq.put("what is html", "HTML (HyperText Markup Language) is the standard markup language for creating web pages. It structures content using tags like <div>, <p>, and <h1>.");
        faq.put("what is css", "CSS (Cascading Style Sheets) is a stylesheet language used to describe the presentation of HTML documents, including layout, colors, and fonts.");
        faq.put("what is javascript", "JavaScript is a high-level, interpreted programming language primarily used for adding interactivity to web pages. It runs in the browser and on servers via Node.js.");
        faq.put("what is python", "Python is a high-level, interpreted programming language known for its readability and simplicity. It is widely used in data science, AI, web development, and automation.");
        faq.put("what is artificial intelligence", "Artificial Intelligence (AI) is the simulation of human intelligence by machines, including learning, reasoning, problem-solving, and decision-making.");
        faq.put("what is machine learning", "Machine Learning (ML) is a subset of AI that enables systems to learn and improve from experience without being explicitly programmed.");
        faq.put("what is deep learning", "Deep Learning is a subset of machine learning that uses neural networks with multiple layers to model complex patterns in data.");
        faq.put("what is natural language processing", "Natural Language Processing (NLP) is a field of AI that enables computers to understand, interpret, and generate human language.");
        faq.put("what is codealpha", "CodeAlpha is an organization that provides virtual internships in software development, data science, and other technology domains. It offers hands-on experience to students and aspiring developers.");
        faq.put("what is operating system", "An operating system is system software that manages computer hardware, software resources, and provides common services for computer programs.");
        faq.put("what is computer network", "A computer network is a set of interconnected devices that communicate and share resources. Networks can be LAN, WAN, MAN, or PAN.");
        faq.put("what is cybersecurity", "Cybersecurity is the practice of protecting systems, networks, and data from digital attacks, theft, and damage.");
        faq.put("what is software engineering", "Software Engineering is the systematic application of engineering principles to software development, including design, development, testing, and maintenance.");
        faq.put("what is sdlc", "SDLC (Software Development Life Cycle) is a process used for planning, creating, testing, and deploying software. Phases include Requirements, Design, Implementation, Testing, Deployment, and Maintenance.");
        faq.put("what is agile", "Agile is an iterative approach to software development that focuses on collaboration, customer feedback, and rapid delivery of small, functional increments.");
        faq.put("what is scrum", "Scrum is an Agile framework for managing complex projects. It uses sprints, daily stand-ups, and roles like Product Owner, Scrum Master, and Development Team.");
        faq.put("what is api", "API (Application Programming Interface) is a set of rules that allows different software applications to communicate with each other.");
        faq.put("what is rest api", "REST (Representational State Transfer) API is an architectural style for designing networked applications. It uses HTTP methods like GET, POST, PUT, and DELETE.");
        faq.put("what is json", "JSON (JavaScript Object Notation) is a lightweight data-interchange format that is easy for humans to read and write and easy for machines to parse and generate.");
        faq.put("what is framework", "A framework is a reusable set of libraries, tools, and best practices that provides a foundation for developing software applications.");
        faq.put("what is spring", "Spring is a popular Java framework for building enterprise applications. It provides dependency injection, aspect-oriented programming, and MVC architecture support.");
        faq.put("what is hibernate", "Hibernate is an Object-Relational Mapping (ORM) framework for Java that simplifies database interaction by mapping Java objects to database tables.");
        faq.put("what is multithreading", "Multithreading is a programming technique where multiple threads run concurrently within a single process, sharing resources and improving performance.");
        faq.put("what is exception handling", "Exception handling is a mechanism to handle runtime errors gracefully. In Java, it uses try, catch, finally, throw, and throws keywords.");
        faq.put("what is java collection", "Java Collection Framework provides a set of interfaces and classes for storing and manipulating groups of data, including List, Set, Queue, and Map.");
        faq.put("what is hashmap", "HashMap is a part of Java Collection Framework that stores key-value pairs. It uses hashing for fast retrieval and allows null values and one null key.");
        faq.put("what is arraylist", "ArrayList is a resizable array implementation in Java. It provides dynamic array functionality with methods like add, remove, and get.");
        faq.put("what is recursion", "Recursion is a programming technique where a method calls itself to solve a problem. It requires a base case and a recursive case to avoid infinite loops.");
        faq.put("what is big o notation", "Big O notation describes the upper bound of an algorithm's time or space complexity in terms of input size. Common complexities are O(1), O(n), O(n^2), O(log n).");
        faq.put("what is variable", "A variable is a named memory location that stores a value. In Java, every variable has a data type and a scope.");
        faq.put("what is compiler", "A compiler is a program that translates source code written in a high-level language into machine code or bytecode. Java source code is compiled into bytecode by javac.");
        faq.put("what is jvm", "JVM (Java Virtual Machine) is an abstract computing machine that executes Java bytecode. It provides platform independence for Java applications.");
        faq.put("what is jdk", "JDK (Java Development Kit) is a software development kit for developing Java applications. It includes JRE, compilers, and development tools.");
        faq.put("what is jre", "JRE (Java Runtime Environment) provides the libraries and JVM needed to run Java applications. It does not include development tools.");
        faq.put("what is interface", "An interface in Java is a reference type that contains abstract methods. A class implements an interface to provide method implementations, supporting multiple inheritance.");
        faq.put("what is constructor", "A constructor is a special method that initializes objects when they are created. It has the same name as the class and no return type.");
        faq.put("what is object", "An object is an instance of a class that has state (fields) and behavior (methods). Everything in Java is associated with objects and classes.");
        faq.put("what is class", "A class is a blueprint for creating objects. It defines fields, methods, and constructors for objects of that type.");
    }

    private void loadKeywordMap() {
        keywordMap.put("java", Arrays.asList(
            "Java is a high-level, object-oriented programming language developed by Sun Microsystems in 1995. It is platform-independent, secure, and widely used for enterprise applications, Android development, and web services.",
            "Java uses JVM to achieve platform independence. Java source code is compiled to bytecode, which runs on any platform with a JVM installed.",
            "Java features include automatic garbage collection, multithreading, exception handling, and a rich standard library."
        ));
        keywordMap.put("oop", Arrays.asList(
            "Object-Oriented Programming (OOP) is a programming paradigm based on objects containing data and methods. The four pillars are Encapsulation, Inheritance, Polymorphism, and Abstraction.",
            "OOP helps in organizing code, improving reusability, and modeling real-world entities in software."
        ));
        keywordMap.put("python", Arrays.asList(
            "Python is a high-level, interpreted programming language known for its readability and simplicity. It is widely used in data science, AI, web development, and automation.",
            "Python features dynamic typing, automatic memory management, and a large standard library."
        ));
        keywordMap.put("javascript", Arrays.asList(
            "JavaScript is a high-level, interpreted programming language primarily used for adding interactivity to web pages. It runs in browsers and on servers via Node.js.",
            "JavaScript supports event-driven, functional, and object-oriented programming styles."
        ));
        keywordMap.put("html", Arrays.asList(
            "HTML (HyperText Markup Language) is the standard markup language for creating web pages. It structures content using tags.",
            "HTML5 introduced semantic elements like <header>, <footer>, <article>, and <section>."
        ));
        keywordMap.put("css", Arrays.asList(
            "CSS (Cascading Style Sheets) is used to style HTML documents. It controls layout, colors, fonts, and responsive design.",
            "CSS frameworks like Bootstrap and Tailwind help speed up web development."
        ));
        keywordMap.put("algorithm", Arrays.asList(
            "An algorithm is a step-by-step procedure for solving a problem. Efficiency is measured using time and space complexity.",
            "Common algorithm categories include sorting, searching, dynamic programming, and greedy algorithms."
        ));
        keywordMap.put("database", Arrays.asList(
            "A database is an organized collection of structured data. DBMS manages databases and provides CRUD operations.",
            "Relational databases use tables and SQL, while NoSQL databases use documents, key-value pairs, or graphs."
        ));
        keywordMap.put("git", Arrays.asList(
            "Git is a distributed version control system created by Linus Torvalds. It tracks changes and enables team collaboration.",
            "Common Git commands: git init, git clone, git add, git commit, git push, git pull, git branch, git merge."
        ));
        keywordMap.put("ai", Arrays.asList(
            "Artificial Intelligence simulates human intelligence in machines. Subfields include Machine Learning, Deep Learning, NLP, and Robotics.",
            "AI applications include virtual assistants, recommendation systems, autonomous vehicles, and healthcare diagnostics."
        ));
        keywordMap.put("programming", Arrays.asList(
            "Programming is the process of creating instructions for computers to execute. It involves problem-solving, logic, and creativity.",
            "Popular programming languages include Java, Python, JavaScript, C++, C#, and Go. Each has strengths for different domains."
        ));
        keywordMap.put("codealpha", Arrays.asList(
            "CodeAlpha is an organization that provides virtual internships in software development, data science, and other technology domains.",
            "CodeAlpha offers hands-on experience with real-world projects to help students build their skills and portfolios."
        ));
    }

    public String findExactAnswer(String question) {
        String cleaned = NLPProcessor.cleanInput(question);
        if (faq.containsKey(cleaned)) {
            return faq.get(cleaned);
        }
        for (Map.Entry<String, String> entry : faq.entrySet()) {
            if (cleaned.contains(entry.getKey()) || entry.getKey().contains(cleaned)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public String findByKeywords(String question) {
        List<String> keywords = NLPProcessor.extractKeywords(question);
        Map<String, Integer> keywordCount = new HashMap<>();
        for (String keyword : keywords) {
            for (String mapKey : keywordMap.keySet()) {
                if (keyword.contains(mapKey) || mapKey.contains(keyword)) {
                    keywordCount.put(mapKey, keywordCount.getOrDefault(mapKey, 0) + 1);
                }
            }
        }
        String bestKey = null;
        int bestCount = 0;
        for (Map.Entry<String, Integer> entry : keywordCount.entrySet()) {
            if (entry.getValue() > bestCount) {
                bestCount = entry.getValue();
                bestKey = entry.getKey();
            }
        }
        if (bestKey != null && keywordMap.containsKey(bestKey)) {
            List<String> responses = keywordMap.get(bestKey);
            return responses.get(Utils.getRandomInt(responses.size()));
        }
        for (String keyword : keywords) {
            for (Map.Entry<String, String> entry : faq.entrySet()) {
                if (entry.getKey().contains(keyword)) {
                    return entry.getValue();
                }
            }
        }
        for (String keyword : keywords) {
            String lowerKeyword = keyword.toLowerCase();
            for (String mapKey : keywordMap.keySet()) {
                if (lowerKeyword.contains(mapKey) || mapKey.contains(lowerKeyword)) {
                    return keywordMap.get(mapKey).get(0);
                }
            }
        }
        return null;
    }

    public String getRandomJoke() {
        return Utils.getRandomElement(jokes);
    }

    public String getRandomQuote() {
        return Utils.getRandomElement(quotes);
    }

    private void loadJokes() {
        jokes[0] = "Why do Java developers wear glasses? Because they can't C#!";
        jokes[1] = "Why did the programmer quit his job? Because he didn't get arrays!";
        jokes[2] = "A SQL query goes into a bar, walks up to two tables and asks: 'Can I join you?'";
        jokes[3] = "Why do programmers prefer dark mode? Because light attracts bugs!";
        jokes[4] = "How many programmers does it take to change a light bulb? None, that's a hardware problem!";
        jokes[5] = "Why was the JavaScript developer sad? Because he didn't know how to 'null' his feelings!";
        jokes[6] = "What's a computer's favorite snack? Microchips!";
        jokes[7] = "Why did the developer go broke? Because he used up all his cache!";
        jokes[8] = "What do you call a programmer from Finland? Nerdic!";
        jokes[9] = "There are 10 types of people in the world: those who understand binary, and those who don't.";
        jokes[10] = "Why do Java developers wear glasses? Because they can't see sharp without C#!";
        jokes[11] = "A programmer's wife tells him: 'Go to the store and buy a loaf of bread. If they have eggs, buy a dozen.' He comes back with 12 loaves of bread.";
        jokes[12] = "Why did the Python programmer cross the road? To find the other path!";
        jokes[13] = "What is a programmer's favorite place? The Foo Bar!";
        jokes[14] = "Why was the computer cold? It left its Windows open!";
        jokes[15] = "What did the Java code say to the Python code? 'You're nothing but a bunch of indentation!'";
        jokes[16] = "Why did the algorithm break up with the data structure? Too many conflicts!";
        jokes[17] = "How do you comfort a JavaScript bug? You console it!";
        jokes[18] = "What's the object-oriented way to become wealthy? Inheritance!";
        jokes[19] = "I told my computer I needed a break. Now it won't stop sending me vacation ads.";
        jokes[20] = "Why did the developer go to therapy? He had too many unresolved dependencies!";
        jokes[21] = "What do you call a fake noodle? An impasta!";
        jokes[22] = "Why do programmers always mix up Halloween and Christmas? Because Oct 31 equals Dec 25!";
        jokes[23] = "A SQL developer walked into a bar. He saw two tables and left without ordering.";
        jokes[24] = "What is a computer's favorite beat? An algorithm!";
    }

    private void loadQuotes() {
        quotes[0] = "The best way to predict the future is to create it. - Alan Kay";
        quotes[1] = "Any fool can write code that a computer can understand. Good programmers write code that humans can understand. - Martin Fowler";
        quotes[2] = "First, solve the problem. Then, write the code. - John Johnson";
        quotes[3] = "Experience is the name everyone gives to their mistakes. - Oscar Wilde";
        quotes[4] = "In programming, the hard part isn't solving problems, but deciding what problems to solve. - Paul Graham";
        quotes[5] = "Programming isn't about what you know; it's about what you can figure out. - Chris Pine";
        quotes[6] = "The only way to learn a new programming language is by writing programs in it. - Dennis Ritchie";
        quotes[7] = "Sometimes it's better to leave something alone, to pause, and that's very true of programming. - Joyce Wheeler";
        quotes[8] = "Code is like humor. When you have to explain it, it's bad. - Cory House";
        quotes[9] = "Make it work, make it right, make it fast. - Kent Beck";
        quotes[10] = "Simplicity is the soul of efficiency. - Austin Freeman";
        quotes[11] = "The best error message is the one that never shows up. - Thomas Fuchs";
        quotes[12] = "It's not a bug; it's an undocumented feature. - Anonymous";
        quotes[13] = "Software is a great combination between artistry and engineering. - Bill Gates";
        quotes[14] = "Talk is cheap. Show me the code. - Linus Torvalds";
        quotes[15] = "Every great developer you know got there by solving problems they were unqualified to solve until they actually did it. - Patrick McKenzie";
        quotes[16] = "The function of good software is to make the complex appear to be simple. - Grady Booch";
        quotes[17] = "Before software can be reusable it first has to be usable. - Ralph Johnson";
        quotes[18] = "Programming is the art of telling another human being what one wants the computer to do. - Donald Knuth";
        quotes[19] = "The computer was born to solve problems that did not exist before. - Bill Gates";
        quotes[20] = "Learning to write programs stretches your mind and helps you think better. - Bill Gates";
        quotes[21] = "Don't comment bad code; rewrite it. - Brian Kernighan";
        quotes[22] = "The most dangerous phrase in the language is: 'We've always done it this way.' - Grace Hopper";
        quotes[23] = "Testing leads to failure, and failure leads to understanding. - Burt Rutter";
        quotes[24] = "The best programs are written so that computing machines can perform them quickly and so that human beings can understand them clearly. - Donald Knuth";
    }

    public String[] getAllFAQKeys() {
        return faq.keySet().toArray(new String[0]);
    }
}
