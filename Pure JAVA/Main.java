package com.restaurant;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();
        ReservationService reservationService = new ReservationService();
        AuthenticationService authService = new AuthenticationService(userService);

        while (true) {
            System.out.println("\n--- Restaurant Management System ---");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    registerUser(scanner, userService);
                    break;
                case 2:
                    loginUser(scanner, authService, reservationService);
                    break;
                case 3:
                    System.out.println("Exiting the system. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void registerUser(Scanner scanner, UserService userService) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter role (CUSTOMER/STAFF): ");
        String role = scanner.nextLine();

        User user = new User(username, password, email, role);
        userService.saveUser(user);
        System.out.println("User registered successfully!");
    }

    private static void loginUser(Scanner scanner, AuthenticationService authService, ReservationService reservationService) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = authService.authenticate(username, password);
        if (user != null) {
            System.out.println("Login successful! Welcome, " + user.getUsername() + ".");
            if (user.getRole().equalsIgnoreCase("CUSTOMER")) {
                customerMenu(scanner, reservationService, user);
            } else if (user.getRole().equalsIgnoreCase("STAFF")) {
                staffMenu(scanner, reservationService);
            }
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    private static void customerMenu(Scanner scanner, ReservationService reservationService, User user) {
        while (true) {
            System.out.println("\n--- Customer Dashboard ---");
            System.out.println("1. Make a Reservation");
            System.out.println("2. View Reservations");
            System.out.println("3. Logout");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    makeReservation(scanner, reservationService, user);
                    break;
                case 2:
                    viewReservations(reservationService);
                    break;
                case 3:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void staffMenu(Scanner scanner, ReservationService reservationService) {
        while (true) {
            System.out.println("\n--- Staff Dashboard ---");
            System.out.println("1. View All Reservations");
            System.out.println("2. Logout");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    viewReservations(reservationService);
                    break;
                case 2:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void makeReservation(Scanner scanner, ReservationService reservationService, User user) {
        System.out.print("Enter reservation date (yyyy-MM-dd): ");
        String date = scanner.nextLine();
        System.out.print("Enter reservation time (HH:mm): ");
        String time = scanner.nextLine();
        System.out.print("Enter party size: ");
        int partySize = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter special requests: ");
        String specialRequests = scanner.nextLine();

        Reservation reservation = new Reservation(date, time, partySize, specialRequests, user.getUsername());
        reservationService.saveReservation(reservation);
        System.out.println("Reservation made successfully!");
    }

    private static void viewReservations(ReservationService reservationService) {
        System.out.println("\n--- All Reservations ---");
        for (Reservation reservation : reservationService.getAllReservations()) {
            System.out.println("Date: " + reservation.getDate() +
                    ", Time: " + reservation.getTime() +
                    ", Party Size: " + reservation.getPartySize() +
                    ", Special Requests: " + reservation.getSpecialRequests() +
                    ", Reserved By: " + reservation.getReservedBy());
        }
    }
}