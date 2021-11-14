package net.coursesystem.loppuprojekti;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CourseFileService implements ICourseFileService{

    @Override
    public List<Student> readStudentsFromFile(String filePath) throws FileNotFoundException {
        List<Student> studentList = new ArrayList<>();
        File studentsFile = new File(filePath);
        Scanner textFileScanner = new Scanner(studentsFile);

        long lastStudentId = 0;
        while(textFileScanner.hasNextLine())
        {
            String currentLine = textFileScanner.nextLine();
            String[] firstLastSplit = currentLine.split(" ");
            String readFirstName = firstLastSplit[0];
            String readLastName = firstLastSplit[1];
            studentList.add(new Student(lastStudentId ,readFirstName, readLastName));
            lastStudentId++;
        }
        textFileScanner.close();

        return studentList;
    }

    @Override
    public List<Course> readCoursesFromFile(String filePath) throws FileNotFoundException {
        List<Course> courseList = new ArrayList<>();
        File coursesFile = new File(filePath);
        Scanner textFileScanner = new Scanner(coursesFile);

        long lastCourseId = 0;
        while(textFileScanner.hasNextLine())
        {
            String currentLine = textFileScanner.nextLine();
            String[] courseTeacherTypeRoomSplit = currentLine.split("-");
            String readCourseName = courseTeacherTypeRoomSplit[0];
            String readCourseTeacher = courseTeacherTypeRoomSplit[1];
            String readCourseType = courseTeacherTypeRoomSplit[2];
            String readCourseRoom = courseTeacherTypeRoomSplit[3];

            if (readCourseType.equals("local"))
            {
                courseList.add(new LocalCourse(lastCourseId, readCourseName, readCourseTeacher, readCourseRoom));
            }
            else if(readCourseType.equals("online"))
            {
                courseList.add(new OnlineCourse(lastCourseId, readCourseName, readCourseTeacher, readCourseRoom));
            }
            else
            {
                System.out.print("Trying to read a course from a file with no matching 'local' or 'online' specification!");
            }
            lastCourseId++;
            
        }
        textFileScanner.close();
        
        return courseList;
    }
    
}
