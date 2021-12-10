import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Random;

public class Kiran_3312_Project3 {

    private static StudentEmployee stdArray[];

    public static void main(String args[]) {
        int numberOfStudentEmployees, options;
        boolean exit = false;

        //creating scanner object
        Scanner input = new Scanner(System.in);

        //printing something about management system
        System.out.println("\t\tWelcome to Student and Course Management System!!\n");
        System.out.print("This System will allow you to manage students and courses. Let's start with \nthe number of students this system will have: ");

        //taking number of students from user
        numberOfStudentEmployees = input.nextInt();

        // creating student array
        stdArray = new StudentEmployee[numberOfStudentEmployees];

        System.out.print("\n");

        //run the program until user never Press '0'
        do {
            //printing the student management system menu on console
            System.out.print("\n**Welcome to Student and Course Management System**");
            System.out.print("\nPress ‘1’ for Student Management System (SMS)");
            System.out.print("\nPress ‘2’ for Course Management System (CMS)");
            System.out.print("\nPress '0' to exit the system\n\n");

            //ask the user to press the options
            options = input.nextInt();

            //switch statements with int data type
            switch (options) {
                //if case is 1 then call SMS()
                case 1:
                    //choose student management system
                    SMS();
                    break;
                //if case is 2 then call CMS()
                case 2:
                    //choose course management system
                    CMS();
                    break;
                //if case is 3 then say good bye and exit the system
                case 0:
                    System.out.printf("\nGood Bye!!!\n");
                    exit = true;
                    break;
                //if case wrong case then say invalid choice
                default:
                    System.out.printf("\nInvalid Choice\n");
            }

        } while (!exit);
        //end do while loop
    }

    public static void SMS() {
        boolean exit = false;
        int options;
        //creating scanner object
        Scanner input = new Scanner(System.in);

        //run the program until user never Press '0'
        do {
            //printing the student management system menu on console
            System.out.print("\n***Welcome to SMS***");
            System.out.print("\nPress '1' to add a student");
            System.out.print("\nPress '2' to deactivate a student");
            System.out.print("\nPress '3' to display all students");
            System.out.print("\nPress '4' to search for a student by ID");
            System.out.print("\nPress ‘5’ to assign on-campus job");
            System.out.print("\nPress ‘6’ to display all students with on-campus jobs");
            System.out.print("\nPress '0' to exit SMS\n\n");

            //ask the user to press the options
            options = input.nextInt();

            //switch statements with int data type
            switch (options) {
                //if case is 1 then call add student method
                case 1:
                    //check whether student is added or not if not then print student reached
                    if (!addStudent()) {
                        System.out.printf("\nNumber of students reached\n");
                    }
                    break;
                //if case is 2 then call deactivateStudent() method
                case 2:
                    deactivateStudent();
                    break;
                //if case is 3 then call deactivate student method
                case 3:
                    displayStudent();
                    break;
                //if case is 4 then call searchStrudnt() method
                case 4:
                    searchStudent();
                    break;
                //if case is 5 then call assignJob() method    
                case 5:
                    assignJob();
                    break;
                //if case is 6 then call displayOnCampus() method 
                case 6:
                    displayOnCampus();
                    break;

                //if case is 0 then exit the system
                case 0:
                    //go to top menu
                    exit = true;
                    break;
                //if case wrong case then say invalid choice
                default:
                    System.out.printf("\nInvalid Choice\n");
            }

        } while (!exit);
        //end do while loop
    }

    public static boolean addStudent() {
        //go through all the number of students
        for (int i = 0; i < stdArray.length; i++) {
            //check if student added or not if student not added then it will be null
            if (stdArray[i] == null) {

                //creating scanner object
                Scanner sc = new Scanner(System.in);

                //getting student information
                System.out.print("Enter first name: ");
                String FirstName = sc.nextLine();

                System.out.print("Enter last name: ");
                String LastName = sc.nextLine();;

                System.out.print("Enter level of the student: ");
                String Level = sc.nextLine();

                //create the student with provided information and add that student
                stdArray[i] = new StudentEmployee(FirstName, LastName, Level, null);

                //print student information of student recently added
                System.out.printf("\n%s %s has been added as a %s with ID %d\n", stdArray[i].getFirstName(), stdArray[i].getLastName(), stdArray[i].getLevel(), stdArray[i].getID());

                
                return true;
            }
        }//end of for loop
        return false;

    }

    public static boolean deactivateStudent() {
        int id;
        System.out.print("Enter the ID for the student you want to deactivate: ");
        Scanner sc = new Scanner(System.in);

        //taking student id
        id = sc.nextInt();

        //go through all the number of students 
        for (int i = 0; i < stdArray.length; i++) {
            //check for the student and if the id is equal to user input then it will not be null and student will be found
            if (stdArray[i] != null && stdArray[i].getID() == id) {
                //deactivate the student
                stdArray[i].setActive(false);
                System.out.printf("\n%s %s has been deactivated\n", stdArray[i].getFirstName(), stdArray[i].getLastName());
                return true;
            }
        }//end of for loop
        return false;
    }

    public static void displayStudent() {
        ArrayList<StudentEmployee> list = sortArray();
        //go through all the number of students 
        for (int i = 0; i < list.size(); i++) {
            //check for student 
            if (list.get(i) != null) {

                // print student information
                System.out.printf("\nName: %s %s", list.get(i).getFirstName(), list.get(i).getLastName());
                System.out.printf("\nID: %d", list.get(i).getID());
                System.out.printf("\nLevel: %s", list.get(i).getLevel());
                //check the status of student and print according to status
                if (list.get(i).isActive()) {
                    System.out.printf("\nStatus: Active\n");
                } else {
                    System.out.printf("\nStatus: Inactive\n");
                }
            }
        }//end of for loop

        addStudentInFile(list);

    }

    public static boolean searchStudent() {
        int id;
        System.out.print("Enter the student ID: ");
        Scanner sc = new Scanner(System.in);
        //taking student id from user
        id = sc.nextInt();
        //go through all the number of students
        for (int i = 0; i < stdArray.length; i++) {
            //check for the student and if the id is equal to user input then it will not be null and student will be found
            if (stdArray[i] != null && stdArray[i].getID() == id) {

                //print the student information
                System.out.printf("\n%s %s", stdArray[i].getFirstName(), stdArray[i].getLastName());
                System.out.printf("\nID: %d", stdArray[i].getID());
                System.out.printf("\nLevel: %s", stdArray[i].getLevel());
                if (stdArray[i].isActive()) {
                    System.out.printf("\nStatus: Active\n");
                } else {
                    System.out.printf("\nStatus: Inactive\n");
                }
                //return true 
                return true;
            }
        }//end of for loop
        return false;
    }

    public static boolean assignJob() {
        //go through all the number of students 
        int id;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Student ID: ");
        //taking student id
        id = sc.nextInt();

        //go through all the number of students 
        for (int i = 0; i < stdArray.length; i++) {
            //check for the student and if the id is equal to user input then it will not be null and student will be found
            if (stdArray[i] != null && stdArray[i].getID() == id) {

                //creating scanner object
                sc = new Scanner(System.in);

                //getting student information
                System.out.print("Enter job: ");
                String job = sc.nextLine();;

                System.out.print("Enter jop-type: ");
                String jobType = sc.nextLine();

                stdArray[i].setJob(job);
                stdArray[i].setJobType(jobType);
                stdArray[i].setJobAssigned(true);

                //print information for student recently added
                System.out.printf("\n%s %s has been assigned %s %s job\n", stdArray[i].getFirstName(), stdArray[i].getLastName(), stdArray[i].getJobType(), stdArray[i].getJob());

                //return true
                return true;
            }
        }//end of for loop
        return false;

    }

    public static void displayOnCampus() {
        StudentEmployee.displayOnCampusStudent(stdArray);
    }

    public static ArrayList<StudentEmployee> sortArray() {
        ArrayList<StudentEmployee> list = new ArrayList<>();
        //convert array into array list
        for (int i = 0; i < stdArray.length; i++) {
            if (stdArray[i] != null) {
                list.add(stdArray[i]);
            }
        }

        //sort converted arraylist by first name using collection frame work
        Collections.sort(list, new Comparator<StudentEmployee>() {
            public int compare(StudentEmployee s1, StudentEmployee s2) {
                return s1.getFirstName().compareToIgnoreCase(s2.getFirstName());
            }
        });

        //return arraylist
        return list;
    }

    public static void addStudentInFile(ArrayList<StudentEmployee> data) {

        PrintWriter writer = null;
        try {
            //Create file object with file path as a constructor
            File f = new File("StudentReport.txt");
            //Check file already exist or not if not then create new file
            if (!f.exists()) {
                f.createNewFile();
            }   //Create writer object of created file
            writer = new PrintWriter(f);
            //Delete all data from file
            writer.print("");
            //Now write newly data into File
            //'@@' will use as a column separator
            for (int i = 0; i < data.size(); i++) {
                String temp = data.get(i).getFirstName() + "@@" + data.get(i).getLastName() + "@@" + data.get(i).getJobType() + "@@" + data.get(i).getJob();

                if (i == data.size() - 1) {
                    writer.append(temp);
                } else {
                    writer.append(temp + "\n");
                }
            }   //Close the File
            writer.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Student: File Write Error");
        } catch (IOException ex) {
            System.out.println("Student: File Creation Error");
        } finally {
            writer.close();
        }
    }

    public static void CMS() {

        boolean exit = false;
        int options;
        //creating scanner object
        Scanner input = new Scanner(System.in);

        //run the program until user never Press '0'
        do {
            //printing the student management system menu on console
            System.out.print("\n***Welcome to CMS***");
            System.out.print("\nPress ‘1’ to add a new course");
            System.out.print("\nPress ‘2’ to assign student a new course");
            System.out.print("\nPress ‘3’ to display student with assigned courses");
            System.out.print("\nPress '0' to exit CMS\n\n");

            //ask the user to press the options
            options = input.nextInt();

            //switch statements with int data type
            switch (options) {
                case 1:
                    //add course
                    addCourse();
                    break;
                //if case is 2 then call below method
                case 2:
                    assignCourse();
                    break;
                //if case is 3 then call below method
                case 3:
                    displayStudentWithAssignedCourse();
                    break;
                //if case is 0 got to top menu
                case 0:
                    //got to top main menu
                    exit = true;
                    break;
                //if case wrong case then say  invalid choice
                default:
                    System.out.printf("\nInvalid Choice\n");
            }

        } while (!exit);
        //end do while loop
    }

    public static boolean addCourse() {
        //creating scanner object
        Scanner sc = new Scanner(System.in);
        ArrayList<String> courseTempList = ReadCourse("Course.txt");
        //getting student information
        System.out.print("Enter course ID: ");
        String courseID = sc.nextLine();

        System.out.print("Enter course name: ");
        String courseName = sc.nextLine();
        Course course = new Course(courseID, courseName);

        boolean contain = false;
        for (int i = 0; i < courseTempList.size(); i++) {
            if (courseTempList.get(i).contains(course.getCourseID())) {
                contain = true;
            }
        }
        if (!contain) {
            courseTempList.add(course.getCourseID() + "@@" + course.getCourseName());
            //print information for students recently added
            System.out.printf("\nConfirmation: New course %s has been added\n", courseID);
        }
        addCourseInFile(courseTempList, "Course.txt");

        return true;
    }

    public static void assignCourse() {

        boolean flag = true;
        //creating scanner object
        Scanner sc = new Scanner(System.in);
        //  read all the text from files line by line
        ArrayList<String> data = ReadCourse("Course.txt");
        ArrayList<String> data1 = ReadCourse("CourseAssignment.txt");
        //getting student information
        System.out.print("Enter Student ID: ");
        int studentID = sc.nextInt();

        sc = new Scanner(System.in);
        System.out.print("Enter course ID: ");
        String courseID = sc.nextLine();

        for (int i = 0; i < stdArray.length; i++) {
            if (stdArray[i] != null) {
                if (stdArray[i].getID() == studentID) {
                    for (int j = 0; j < data.size(); j++) {
                        if (data.get(j).contains(courseID)) {
                            stdArray[i].setCourseID(courseID);
                            String temp = stdArray[i].getFirstName() + "@@" + stdArray[i].getLastName() + "@@" + stdArray[i].getID() + "@@" + stdArray[i].getCourseID();
                            data1.add(temp);
                            addCourseInFile(data1, "CourseAssignment.txt");
                            System.out.printf("\nConfirmation:%s %s has been assigned course %s\n", stdArray[i].getFirstName(), stdArray[i].getLastName(), courseID);
                        }
                    }
                }
            }
        }

    }

    public static void displayStudentWithAssignedCourse() {

        //  read all the text from files line by line
        ArrayList<String> data = ReadCourse("CourseAssignment.txt");

        for (int i = 0; i < data.size(); i++) {
            String temp[] = data.get(i).split("@@");
            System.out.printf("\nName: %s %s", temp[0], temp[1]);
            System.out.printf("\nID: %s", temp[2]);
            System.out.printf("\nCourses: %s\n", temp[3]);
        }
    }

    public static void addCourseInFile(ArrayList<String> data, String path) {

        PrintWriter writer = null;
        try {
            //Create file object with file path as a constructor
            File f = new File(path);
            //Check file already exist or not if not then create new file
            if (!f.exists()) {
                f.createNewFile();
            }   //Create writer object of created file
            writer = new PrintWriter(f);
            //Delete all data from file
            writer.print("");
            //Now write newly data into File
            //'@@' will use as a column separator
            for (int i = 0; i < data.size(); i++) {

                if (i == data.size() - 1) {
                    writer.append(data.get(i));
                } else {
                    writer.append(data.get(i) + "\n");
                }
            }   //Close the File
            writer.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Student: File Write Error");
        } catch (IOException ex) {
            System.out.println("Student: File Creation Error");
        } finally {
            writer.close();
        }
    }

    public static ArrayList<String> ReadCourse(String path) {
        ArrayList<String> data = new ArrayList<>();
        try {

            File f = new File(path);
            //Check file already exist or not if not then create new file
            if (!f.exists()) {

                f.createNewFile();

            }
            //  read all the text from files line by line
            data = (ArrayList<String>) Files.readAllLines(Paths.get(f.getAbsolutePath()));

        } catch (IOException ex) {
            System.out.println("Error In reading Course");
        }

        return data;
    }

}//end of driver class

//student interface
interface StudentInterface {

    //get student ID
    public int getID();

    //set student ID
    public void setID(int ID);

    //get student First Name
    public String getFirstName();

    //set student First Name
    public void setFirstName(String FirstName);

    //get student LastName
    public String getLastName();

    //set student LastName
    public void setLastName(String LastName);

    //get student level
    public String getLevel();

    //set student level
    public void setLevel(String Level);

    //Is student Active
    public boolean isActive();

    //set student Active
    public void setActive(boolean Active);
}//end of student interface

//student class
class Student implements StudentInterface {

    //instance variable
    private int ID;
    private String FirstName;
    private String LastName;
    private String Level;
    private boolean Active;

    //Student constructor with first, last name and level parameters
    public Student(String FirstName, String LastName, String Level) {
        //initiate instance variables

        //set student id Randomly
        this.ID = new Random().nextInt(99);
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Level = Level;
        this.Active = true;
    }

    //get student ID
    public int getID() {
        return ID;
    }

    //set student ID
    public void setID(int ID) {
        this.ID = ID;
    }

    //get student First Name
    @Override
    public String getFirstName() {
        return FirstName;
    }

    //set student First Name
    @Override
    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    //get student LastName
    @Override
    public String getLastName() {
        return LastName;
    }

    //set student LastName
    @Override
    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    //get student level
    @Override
    public String getLevel() {
        return Level;
    }

    //set student level
    @Override
    public void setLevel(String Level) {
        this.Level = Level;
    }

    //Is student Active
    @Override
    public boolean isActive() {
        return Active;
    }

    //set student Active
    @Override
    public void setActive(boolean Active) {
        this.Active = Active;
    }

}//end of student class

//start of StudentEmployee class
class StudentEmployee extends Student {

    private String job = "no job";
    private boolean jobAssigned = false;
    private String jobType = "";
    private String courseID = "";

    public StudentEmployee(String FirstName, String LastName, String Level, String job) {
        super(FirstName, LastName, Level);
        this.job = job;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public boolean isJobAssigned() {
        return jobAssigned;
    }

    public void setJobAssigned(boolean jobAssigned) {
        this.jobAssigned = jobAssigned;
    }

    public static void displayOnCampusStudent(StudentEmployee studentEmployee[]) {
        for (int i = 0; i < studentEmployee.length; i++) {
            if (studentEmployee[i] != null) {
                if (studentEmployee[i].isJobAssigned()) {

                    // print student information
                    System.out.printf("\nName: %s %s", studentEmployee[i].getFirstName(), studentEmployee[i].getLastName());
                    System.out.printf("\nID: %d", studentEmployee[i].getID());
                    System.out.printf("\nOn Campuse Job: %s %s\n", studentEmployee[i].getJobType(), studentEmployee[i].getJob());

                }
            }
        }
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}//end of StudentEmployee class


//course interface
interface CourseInterface {
    // all are the abstract methods.

    //get course ID, type String
    public String getCourseID();

    //set course ID, type String
    public void setCourseID(String courseID);

    //return course name, type String
    public String getCourseName();

    //set course name, type String
    public void setCourseName(String courseName);
}//end of course interface


//course calls which have course id and course name
class Course implements CourseInterface{

    //global variable
    private String courseID;
    private String courseName;

    //variable parameterized constructor
    public Course(String courseID, String courseName) {
        this.courseID = courseID;
        this.courseName = courseName;
    }

    //get course ID, type String
    @Override
    public String getCourseID() {
        return courseID;
    }

    //set course ID, type String
    @Override
    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    //return course name, type String
    @Override
    public String getCourseName() {
        return courseName;
    }

    //set course name, type String
    @Override
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

}//end of course class