# Grand Palace Hotel - Hotel Reservation System

A professional, fully offline **Java console-based Hotel Reservation System** built for the
**CodeAlpha Java Programming Internship (Task 4)**.

The application manages hotel rooms, customer information, bookings, cancellations, billing and
booking history with clean Object-Oriented Programming principles and file-based persistence.

---

## Project Overview

- **Hotel Name:** Grand Palace Hotel
- **Total Rooms:** 30
  - 101 - 110: Standard (Rs 1500/night)
  - 201 - 210: Deluxe (Rs 2500/night)
  - 301 - 310: Suite (Rs 4000/night)
- **Billing:** 18% GST applied on room charges
- **Data Persistence:** All bookings are saved to and loaded from `bookings.txt`
- **Works completely offline** - no external APIs, libraries or internet connection required

---

## Features

| # | Feature | Description |
|---|---------|-------------|
| 1 | View Available Rooms | Shows all rooms with number, type, price and availability |
| 2 | Book Room | Collects customer details, assigns room, generates booking ID and bill |
| 3 | Cancel Booking | Cancels a booking by ID and makes the room available again |
| 4 | View Booking Details | Displays complete information of a single booking |
| 5 | View All Bookings | Displays all bookings in a formatted table |
| 6 | Search Booking | Search by Booking ID, Customer Name or Phone Number |
| 7 | Generate Bill | Prints a professional invoice with GST breakdown |
| 8 | Booking Statistics | Total/booked/available rooms, occupancy %, revenue, today's bookings |
| 9 | Save Bookings | Manually saves all bookings to `bookings.txt` |
| 10 | Exit | Auto-saves and shows the exit screen |

### Additional Features

- Random, unique auto-generated Booking IDs (e.g. `BK483920`)
- Professional formatted invoices with 18% GST breakdown
- Room occupancy percentage display
- Today's bookings display in statistics
- Duplicate booking prevention (unique Booking IDs, cannot re-book occupied rooms)
- Professional welcome and exit screens
- Input validation (phone, email, age, dates, menu choices)

---

## Technologies Used

- **Language:** Java (JDK 8 or higher)
- **Concepts:** Classes, Objects, Encapsulation, Inheritance, Polymorphism, Abstraction
- **Collections:** `ArrayList`, `HashMap`
- **File Handling:** `FileReader`, `FileWriter`, `BufferedReader`, `BufferedWriter`
- **Date Handling:** `LocalDate`, `ChronoUnit`, `DateTimeFormatter`
- **Others:** `Scanner`, `Random`, `String.format`, Exception Handling, Switch, Loops

---

## Folder Structure

```
HotelReservationSystem/
│── Main.java            Entry point, console menu, user interaction
│── Hotel.java           Hotel details, room creation and room management
│── Room.java            Abstract Room + StandardRoom, DeluxeRoom, SuiteRoom (inheritance)
│── Customer.java        Abstract Person + Customer (encapsulation)
│── Booking.java         Booking entity with pricing, GST and status
│── BookingManager.java  Booking logic, search, statistics (ArrayList + HashMap)
│── FileManager.java     Save/load bookings from bookings.txt
│── BillGenerator.java   Invoice printing
│── Utils.java           Input helpers, validation and formatting
│── bookings.txt         Booking data file (auto-generated at runtime)
│── README.md            This documentation
```

---

## How to Compile

Make sure **JDK 8 or higher** is installed and `javac` is available on your PATH.

```bash
cd HotelReservationSystem
javac Main.java
```

Or compile all Java files at once:

```bash
javac *.java
```

> This creates `.class` files in the same directory (no IDE required).

---

## How to Run

```bash
java Main
```

On first launch the application automatically loads existing bookings from `bookings.txt`
(if present) and creates all 30 rooms in memory.

---

## Sample Output

### Welcome Screen

```
========================================
        GRAND PALACE HOTEL
     Hotel Reservation System
========================================
            Welcome!
```

### Main Menu

```
========================================
        GRAND PALACE HOTEL
     Hotel Reservation System
========================================
1. View Available Rooms
2. Book Room
3. Cancel Booking
4. View Booking Details
5. View All Bookings
6. Search Booking
7. Generate Bill
8. Booking Statistics
9. Save Bookings
10. Exit
----------------------------------------
Choose Option:
```

### Booking Success

```
========================================
        BOOKING SUCCESSFUL
========================================
Booking ID      : BK483920
Customer        : Alice Sharma
Room Assigned   : 101 (Standard)
Check-In        : 2026-08-10
Check-Out       : 2026-08-14
Number of Days  : 4
Total Bill      : Rs 7080.00
Status          : ACTIVE
========================================
```

### Invoice (Feature 7)

```
========================================
         HOTEL INVOICE
         GRAND PALACE HOTEL
----------------------------------------
Booking ID      : BK483920
Customer Name   : Alice Sharma
----------------------------------------
Room Number     : 101
Room Type       : Standard
Check-In        : 2026-08-10
Check-Out       : 2026-08-14
Days            : 4
Price Per Night : Rs 1500.00
----------------------------------------
Room Charges    : Rs 6000.00
GST (18%)       : Rs 1080.00
----------------------------------------
Final Amount    : Rs 7080.00
========================================
Thank you for choosing Grand Palace Hotel!
        Visit Again!
========================================
```

---

## Validation Rules

- **Phone Number:** must contain exactly 10 digits
- **Email:** must be a valid format containing `@`
- **Age:** must be between 1 and 120
- **Dates:** Check-Out must be after Check-In; Check-In cannot be in the past
- **Room Availability:** occupied rooms cannot be booked
- **Booking ID:** auto-generated and guaranteed unique

---

## Data Persistence (bookings.txt)

Each booking is stored on one line with `;;` as the field separator:

```
BK483920;;Alice Sharma;;28;;Female;;9876543210;;alice@example.com;;12 Park Street, Mumbai;;101;;Standard;;2026-08-10;;2026-08-14;;4;;1500.0;;7080.0;;ACTIVE
```

- Bookings are **loaded automatically** when the application starts
- Bookings are **saved automatically** when you exit, or manually via option 9
- Corrupt/malformed lines are skipped with a warning (robust exception handling)

---

## Future Enhancements

- Add room maintenance status (Out of Service / Under Repair)
- Admin login and role-based access control
- Advance payments / refunds for cancellations
- Loyalty points and discount coupons
- Graphical User Interface (JavaFX / Swing)
- Database persistence (MySQL / SQLite) instead of a text file
- CSV export and printable PDF invoices
- Room service, dining and other additional charges on the bill

---

## Author

Built as part of the **CodeAlpha Java Programming Internship - Task 4**.

Suitable for GitHub, resumes, interviews and internship submission.
