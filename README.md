# YouPassed

University Admission Management System  - web application made on Java 8 with Spring and Hibernate frameworks.

![](views-demo/index.png)

## Description and Features

The application designed to help an institution of higher education to manage student applications. There are two *User Roles*: Student and Administrator.

**Student**: 
- chooses the profession - i.e. applies for a university major he/she wants to study;
- registers for the required exams; 
- receives a notification about being admitted.

**Administrator**: 
- inputs the marks for the exams that students registered to;
- confirms the admission of the best-ranking applicants for each major or all of them in a batch;
- also, Admin can add/remove majors and exams, edit data about them, change a user's role, etc.

All users can register, login, update their profiles.

*Two localizations available: English and Ukrainian;*

![](views-demo/majors.png)

### Technology Stack and Features

- Java SE 8;
- Spring Core, Boot, MVC, Security;
- MySQL database (and H2 for testing);
- Hibernate / JPA object-relational mapping instruments;
- Thymeleaf template engine and Bootstrap CSS framework for the front-end;
- Slf4j logging;
- Maven build tool.

## Usage

![](views-demo/ranking.png)

![](views-demo/admin-home.png)

![](views-demo/edit-major.png)

