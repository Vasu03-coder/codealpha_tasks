# Stock Trading Platform

A professional Java console-based stock trading simulation that demonstrates core Object-Oriented Programming (OOP) principles. Users can view available stocks, buy and sell shares, manage their portfolio, track transactions, and monitor their account balance.

## Features

- **User Management** – Default user with stored username, account balance, and owned stocks.
- **Stock Market** – 10 pre-loaded Indian stocks (TCS, Infosys, Reliance, etc.) with IDs and current prices displayed in a formatted table.
- **Buy Stock** – Select a stock, enter quantity, validate balance, and execute purchase.
- **Sell Stock** – Select an owned stock, enter quantity, validate holdings, and execute sale.
- **Portfolio Dashboard** – View holdings with quantity, purchase price, current price, profit/loss per stock, total investment, current value, and overall P/L.
- **Account Balance** – Display current available balance.
- **Transaction History** – Full audit trail with transaction ID, date/time, stock name, BUY/SELL type, quantity, and price.
- **File Persistence** – Save and load portfolio and transaction data to/from `portfolio.txt` and `transactions.txt`.
- **Exception Handling** – Graceful handling of invalid inputs, insufficient balance, negative quantities, and unavailable stocks.

## OOP Concepts Used

| Concept       | Implementation |
|---------------|----------------|
| Encapsulation | Private fields with public getters/setters in all model classes |
| Abstraction   | Clean method interfaces hiding internal complexity |
| Inheritance   | (Extensible design patterns ready for future expansion) |
| Polymorphism  | Method overloading in constructors and utility methods |
| Composition   | Portfolio contains User and StockHolding objects |
| Collections   | ArrayList, HashMap for stocks, holdings, and transactions |

## Project Structure

```
StockTradingPlatform/
├── Main.java           # Entry point, menu loop, business logic
├── Stock.java          # Stock data model (ID, name, price)
├── User.java           # User data model (username, balance)
├── Portfolio.java      # Portfolio management with StockHolding inner class
├── Transaction.java    # Transaction data model with file I/O support
├── StockMarket.java    # Market initialization and stock lookup
├── FileManager.java    # File save/load for portfolio and transactions
├── Utils.java          # Input validation and formatting utilities
└── README.md           # Project documentation
```

## Requirements

- Java 17 or above
- Standard Java libraries only (no external dependencies)

## How to Run

1. **Clone or download** the project.

2. **Open a terminal** in the `StockTradingPlatform` directory.

3. **Compile** all Java files:
   ```
   javac *.java
   ```

4. **Run** the application:
   ```
   java Main
   ```

## Screenshots

*(Screenshots to be added – run the application to see the console UI in action.)*

## Usage

1. **View Stocks** – Displays all available stocks with IDs and current prices.
2. **Buy Stock** – Enter stock ID and quantity to purchase (balance checked automatically).
3. **Sell Stock** – Enter stock name and quantity to sell (validated against holdings).
4. **View Portfolio** – Shows owned stocks with detailed P/L analysis.
5. **View Balance** – Displays current account balance.
6. **Transaction History** – Complete log of all buy/sell transactions.
7. **Save Data** – Persists portfolio and transactions to text files.
8. **Load Data** – Reloads saved data from files.
9. **Exit** – Closes the application.

## Future Improvements

- **Database Integration** – Replace text file storage with MySQL or PostgreSQL.
- **JavaFX GUI** – Build a graphical user interface for a richer experience.
- **REST API** – Expose trading functionality via RESTful web services.
- **Live Stock Price API** – Integrate real-time stock prices using a third-party API.
- **User Login** – Multi-user support with authentication.
- **Admin Panel** – Administrative interface for managing stocks and users.
- **Advanced Charting** – Portfolio performance charts and analytics.

## License

This project is submitted as part of an internship assignment and is available for educational and portfolio purposes.
