package net.coursesystem.loppuprojekti;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CourseService implements ICourseService{

    List<Student> studentList;
    List<Course> courseList;
    CourseFileService fileService;
    String studentsPath = "students.txt";
    String coursesPath = "courses.txt";

    CourseService()
    {
        fileService = new CourseFileService();

        try 
        {
            studentList = fileService.readStudentsFromFile(studentsPath);
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("WARNING: Was not able to found file for 'students'!");
            e.printStackTrace();
        }    

        try 
        {
            courseList = fileService.readCoursesFromFile(coursesPath);
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("WARNING: Was not able to found file for 'courses'!");
            e.printStackTrace();
        }
    }

    @Override
    public List<Student> getStudents() {
        return studentList;
    }

    @Override
    public List<Course> getCourses() {
        return courseList;
    }

    @Override
    public Student getStudentById(long studentId) {
        for (Student student : studentList) {
            long currentStudentId = student.getId();
            if(currentStudentId == studentId)
            {
                return student;
            }
        }      
        return null;
    }

    @Override
    public Course getCourseById(long courseId) {
        for (Course course : courseList) {
            long currentCourseId = course.getId();
            if(currentCourseId == courseId)
            {
                return course;
            }
        }
        return null;
    }

    @Override
    public List<Course> getCoursesOfStudent(long studentId) {
        List<Course> studentCourses = new ArrayList<>();
        Student student = getStudentById(studentId);
        for (Course course : courseList) 
        {
            for (Student courseStudent : course.getCourseStudents()) {
                if (courseStudent == student) {
                    studentCourses.add(course);
                }
            }
        }
        return studentCourses;
    }

    @Override
    public boolean addStudentToCourse(long studentId, long courseId) {
        return getCourseById(courseId).AddStudent(getStudentById(studentId));
    }
    
}
