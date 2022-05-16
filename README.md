# Bug-Tracker
A bug tracking software which organizes the project and track each bug within each project with a professional GUI and database.

## Table of Content


## Project Functionalities
1. Supports users with different roles (Admin, Project Manager, Developer).
2. Authentication and Authorization.
3. Database connection.
4. Different dashboard for each user (Admin, Project Manager, Developer).
5. Adding projects and assigning them to developers.
6. Adding bugs tickets withing each project and assigning them to certain developer.
7. Adding comments to each bug ticket.

## Dependencies
**1. mysql-connector-java-8.0.26**

Open your IDE (preferably IntelliJ IDEA), File->Project Structure->Libraries. Then click on the plus(+) sign and select From Maven....

After you'll get a search box. There you should put:

    mysql:mysql-connector-java:8.0.26

**2. javafx-sdk-17**

This library is already downloaded in the repo, you won't have to download it.


## Tools
1. IntelliJ IDEA (IDE)
2. Java
3. JavaFX
4. MYSQL to manage database
5. MYSQL J Connector to connect to the database
6. freemysqlhosting.net to host the database
## Deployment
**1. Setting your Database**

  Inside "dbUtil" folder, you will find "DBConnection" class. You can change the value of the following variables and assign them to your database.

1. url
Change this variable to the url of your database
2. USERNAME
Change this variable to the username of your database
3. PASSWORD
Change this variable to the password of your database

**2. Running your main class**

  Run the "GUIStart" class inside View folder as your main class

## Design Pattern
**1. Model-View-Controller Design Pattern**

## Database model


## Author
1. [Yousef Kotp] (https://github.com/yousefkotp)

## Common Errors
> You should open your IDE. File->Project Structure->Module. You should mark each folder you find inside "src" folder as a source. If you don't do so you can face many errors.

**1. Error: JavaFX runtime components are missing, and are required to run this application**
> File >> Project Structure >> Modules >> Dependency >> + (on left-side of window)

clicking the "+" sign will let you designate the directory where you have unpacked JavaFX's "lib" folder.

Scope is Compile (which is the default.) You can then edit this to call it JavaFX by double-clicking on the line.

then in:

> Run >> Edit Configurations

Add this line to VM Options:

> --module-path "javafx-sdk-17\lib" --add-modules=javafx.controls,javafx.fxml 

## Screenshots

![Screenshot (316)](https://user-images.githubusercontent.com/41492875/134807539-e8cad699-50c7-4b40-b1ce-92d91c8c7ad0.png)

![Screenshot (319)](https://user-images.githubusercontent.com/41492875/134807538-d7120601-6619-4162-9b68-0453ca2d2d11.png)

![Screenshot (320)](https://user-images.githubusercontent.com/41492875/134807537-907aa061-aaab-4027-9694-fa7c4fc5a7ab.png)

![Screenshot (321)](https://user-images.githubusercontent.com/41492875/134807536-d11ab801-8054-462e-8eab-8494ac46d3eb.png)

![Screenshot (322)](https://user-images.githubusercontent.com/41492875/134807535-72b72d35-c526-4de2-8422-38aceb84b850.png)

![Screenshot (323)](https://user-images.githubusercontent.com/41492875/134807534-27caade9-368b-4fb7-a238-b751468cfdf4.png)

![Screenshot (324)](https://user-images.githubusercontent.com/41492875/134807532-93c3a4db-b23a-4838-bbd1-a3b16769725b.png)

![Screenshot (325)](https://user-images.githubusercontent.com/41492875/134807531-657b40dd-9ca9-4d20-b246-cd266fce0f90.png)

![Screenshot (326)](https://user-images.githubusercontent.com/41492875/134807530-668c692d-48dd-4806-8a2a-71e909aa7b63.png)

![Screenshot (327)](https://user-images.githubusercontent.com/41492875/134807529-af9197d5-9d61-4ae9-ae83-221f0450ab6a.png)

![Screenshot (328)](https://user-images.githubusercontent.com/41492875/134807526-feb99d5d-4dc1-4780-b685-4f9d10b4210f.png)




