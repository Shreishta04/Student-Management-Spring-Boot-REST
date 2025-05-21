# Student-Management-Spring-Boot-REST

Overview
The Student Management API is a Spring Boot-based RESTful API designed to manage students, courses, and teachers within an educational institution. It provides endpoints to perform CRUD (Create, Read, Update, Delete) operations on students, courses, and teachers, as well as functionality to register students for courses.

Project Structure
The API is built using Spring Boot and follows a layered architecture:

Controllers: Handle HTTP requests and responses. _codec- Controllers: Handle HTTP requests and responses.
Services: Contain business logic (e.g., CourseServiceImpl, StudentServiceImpl, TeacherServiceImpl).
Models: Represent entities like Course, Student, and Teacher.
DTOs: Data Transfer Objects for request and response payloads (e.g., CourseDTO, StudentDTO, TeacherDTO).
Exceptions: Custom exceptions like EntityNotFoundException and UniqueEntityException for error handling.
API Endpoints
Course EndTable: Course Endpoints
Base URL: /api/courses

GET /: Retrieve all courses. Returns a list of Course objects. Status: 200.
POST /create: Create a new course. Request body: CourseDTO. Returns the created Course object. Status: 200, 400, 409.
PUT /update/{id}: Update a course by ID. Request body: CourseUpdateDTO. Returns the updated Course object. Status: 200, 404, 409.
DELETE /delete/{id}: Delete a course by ID. Request body: CourseDeleteDTO. Returns a success message (String). Status: 200, 404.
Student Endpoints
Base URL: /api/students

GET /: Retrieve all students. Returns a list of Student objects. Status: 200.
GET /{id}: Retrieve a student by ID. Returns a Student object. Status: 200, 400.
POST /create: Create a new student. Request body: StudentDTO. Returns the created Student object. Status: 200, 400.
PUT /update/{id}: Update a student by ID. Request body: StudentDTO. Returns the updated Student object. Status: 200, 404.
DELETE /delete/{id}: Delete a student by ID. Returns a success message (String). Status: 200, 404.
POST /register: Register a student for a course. Request body: CourseRegisterDTO. Returns a success message (String). Status: 200, 404, 409.
Teacher Endpoints
Base URL: /api/teachers

GET /: Retrieve all teachers. Returns a list of Teacher objects. Status: 200.
POST /create: Create a new teacher. Request body: TeacherDTO. Returns the created Teacher object. Status: 200, 400.
PUT /update/{id}: Update a teacher by ID. Request body: TeacherDTO. Returns the updated Teacher object. Status: 200, 404.
DELETE /delete/{id}: Delete a teacher by ID. Returns a success message (String). Status: 200, 404.
Status Codes
200 OK: Request was successful.
400 Bad Request: Invalid request data (e.g., missing required fields).
404 Not Found: Requested resource (e.g., student, course, teacher) not found.
409 Conflict: Attempt to create or update with duplicate data (e.g., course with an existing name).

Error Handling
The API uses custom exceptions:

EntityNotFoundException: Thrown when a requested resource is not found.
UniqueEntityException: Thrown when a duplicate entity (e.g., course name) is detected.
