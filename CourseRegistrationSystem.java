import java.util.ArrayList;  // Import ArrayList class
import java.util.HashMap;   // Import HashMap class
import java.util.List;      // Import List interface
import java.util.Scanner;   // Import Scanner class for user input

// Class to store course information
class Course {
    String code;          // Course code
    String title;         // Course title
    String description;   // Course description
    int capacity;         // Maximum number of students that can register
    int availableSlots;   // Number of available slots remaining
    String schedule;      // Course schedule

    // Constructor to initialize the course object
    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.availableSlots = capacity;  // Initially, available slots are equal to capacity
        this.schedule = schedule;
    }

    // Method to display course information
    public void displayCourse() {
        System.out.println("Course Code: " + code);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Capacity: " + capacity);
        System.out.println("Available Slots: " + availableSlots);
        System.out.println("Schedule: " + schedule);
    }
}

// Class to store student information
class Student {
    String id;                      // Student ID
    String name;                    // Student name
    List<Course> registeredCourses; // List of courses the student is registered for

    // Constructor to initialize the student object
    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();  // Initialize the list of registered courses
    }

    // Method to register a student for a course
    public void registerCourse(Course course) {
        if (course.availableSlots > 0) {  // Check if there are available slots in the course
            registeredCourses.add(course);  // Add the course to the student's list of registered courses
            course.availableSlots--;        // Decrease the number of available slots in the course
            System.out.println(name + " successfully registered for " + course.title);
        } else {
            System.out.println("No available slots for " + course.title);  // Print message if no slots are available
        }
    }

    // Method to drop a course
    public void dropCourse(Course course) {
        if (registeredCourses.remove(course)) {  // Try to remove the course from the student's list of registered courses
            course.availableSlots++;             // Increase the number of available slots in the course
            System.out.println(name + " successfully dropped " + course.title);
        } else {
            System.out.println("You are not registered for " + course.title);  // Print message if the student is not registered for the course
        }
    }

    // Method to display the courses the student is registered for
    public void displayRegisteredCourses() {
        System.out.println("Registered Courses for " + name + ":");
        for (Course course : registeredCourses) {  // Iterate through the list of registered courses
            System.out.println(course.title);      // Print the title of each course
        }
    }
}

// Main class to manage the system
public class CourseRegistrationSystem {
    static HashMap<String, Course> courseDatabase = new HashMap<>();  // Database to store courses
    static HashMap<String, Student> studentDatabase = new HashMap<>(); // Database to store students

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // Scanner object to read user input

        // Sample data: adding courses to the course database
        courseDatabase.put("MCAE", new Course("MCAE", "MCA Electives", "Various elective courses for MCA", 40, "MWF 2-3PM"));
        courseDatabase.put("MCAI", new Course("MCAI", "MCA ISMS", "Information Systems Management", 35, "TTh 9-10:30AM"));
        courseDatabase.put("MCAS", new Course("MCAS", "MCA SCT", "Software Construction Techniques", 30, "MWF 11AM-12PM"));
        courseDatabase.put("MCAA", new Course("MCAA", "MCA AI ML", "Artificial Intelligence and Machine Learning", 25, "TTh 1-2:30PM"));

        // Sample data: adding students to the student database
        studentDatabase.put("S001", new Student("S001", "John Doe"));
        studentDatabase.put("S002", new Student("S002", "Jane Smith"));

        // Infinite loop to keep the program running until user chooses to exit
        while (true) {
            System.out.println("1. Display available courses");
            System.out.println("2. Register for a course");
            System.out.println("3. Drop a course");
            System.out.println("4. Display registered courses");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();  // Read user choice
            scanner.nextLine();  // Consume newline character

            switch (choice) {
                case 1:
                    displayAvailableCourses();  // Call method to display available courses
                    break;
                case 2:
                    System.out.print("Enter Student ID: ");
                    String studentId = scanner.nextLine();  // Read student ID from user
                    System.out.print("Enter Course Code: ");
                    String courseCode = scanner.nextLine();  // Read course code from user
                    registerForCourse(studentId, courseCode);  // Call method to register the student for the course
                    break;
                case 3:
                    System.out.print("Enter Student ID: ");
                    studentId = scanner.nextLine();  // Read student ID from user
                    System.out.print("Enter Course Code: ");
                    courseCode = scanner.nextLine();  // Read course code from user
                    dropCourse(studentId, courseCode);  // Call method to drop the course for the student
                    break;
                case 4:
                    System.out.print("Enter Student ID: ");
                    studentId = scanner.nextLine();  // Read student ID from user
                    displayRegisteredCourses(studentId);  // Call method to display registered courses for the student
                    break;
                case 5:
                    scanner.close();  // Close the scanner
                    System.exit(0);  // Exit the program
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");  // Print message for invalid choice
            }
        }
    }

    // Method to display available courses
    public static void displayAvailableCourses() {
        for (Course course : courseDatabase.values()) {  // Iterate through the course database
            course.displayCourse();  // Display each course
            System.out.println();
        }
    }

    // Method to register a student for a course
    public static void registerForCourse(String studentId, String courseCode) {
        Student student = studentDatabase.get(studentId);  // Get the student object from the database
        Course course = courseDatabase.get(courseCode);   // Get the course object from the database

        if (student != null && course != null) {  // Check if both student and course exist
            student.registerCourse(course);  // Register the student for the course
        } else {
            System.out.println("Invalid student ID or course code.");  // Print message if student ID or course code is invalid
        }
    }

    // Method to drop a course for a student
    public static void dropCourse(String studentId, String courseCode) {
        Student student = studentDatabase.get(studentId);  // Get the student object from the database
        Course course = courseDatabase.get(courseCode);   // Get the course object from the database

        if (student != null && course != null) {  // Check if both student and course exist
            student.dropCourse(course);  // Drop the course for the student
        } else {
            System.out.println("Invalid student ID or course code.");  // Print message if student ID or course code is invalid
        }
    }

    // Method to display the courses a student is registered for
    public static void displayRegisteredCourses(String studentId) {
        Student student = studentDatabase.get(studentId);  // Get the student object from the database

        if (student != null) {  // Check if the student exists
            student.displayRegisteredCourses();  // Display the registered courses for the student
        } else {
            System.out.println("Invalid student ID.");  // Print message if student ID is invalid
        }
    }
}
