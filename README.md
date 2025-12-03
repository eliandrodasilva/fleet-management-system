# Fleet Management System

A **fleet management system developed in Java with JPA and Hibernate**, demonstrating database persistence. Implements route optimization via Dijkstra's algorithm, vehicle and driver management with validations, and a Swing graphical interface.

## Features

- **Vehicle Management**: Track vehicles by license plate, model, year, mileage, and operational status
- **Driver Management**: Manage drivers with CPF and CNH validation
- **Locations & Route Segments**: Define locations and distance-based connections between them
- **Route Optimization**: Automatically calculate shortest paths between locations using Dijkstra's algorithm
- **Scheduled Services**: Schedule maintenance and inspections for vehicles
- **Database Seeding**: Populate the database with sample data via integrated UI panel
- **Responsive GUI**: Tabbed interface for registration, reporting, and administration

## Tech Stack

- **Language**: Java 22
- **ORM**: Hibernate 5.6 + JPA 2.2
- **Database**: MySQL 8.0
- **GUI**: Swing
- **Build Tool**: Maven
- **Design Pattern**: DAO (Data Access Object) with generics
