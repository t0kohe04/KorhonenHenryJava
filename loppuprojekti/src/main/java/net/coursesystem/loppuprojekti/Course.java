package net.coursesystem.loppuprojekti;

import java.util.ArrayList;
import java.util.List;


public abstract class Course{
    private long courseId;
    private String courseName;
    private String courseTeacher;
    private List<Student> courseStudents;

    private long generateNewUniqueId(long lastId)
    {
        long newId = lastId++;
        return newId;
    }

    public Course(long lastId, String nameCourse, String nameTeacher) 
    {
        courseId = generateNewUniqueId(lastId);
        courseName = nameCourse;
        courseTeacher = nameTeacher;
        courseStudents = new ArrayList<>();
    }

    public long getId(){
        return courseId;
    }

    public String getCourseName()
    {
        return courseName;
    }

    public String getCourseTeacher()
    {
        return courseTeacher;
    }

    public List<Student> getCourseStudents()
    {
        return courseStudents;
    }

    public Boolean AddStudent(Student student)
    {
        // Cheks if student already exists in the course list
        for (Student courseStudent : courseStudents) 
        {
            if (student == courseStudent) {
                return false;
            }
        }
        courseStudents.add(student);
        return true;
    }

    public Boolean RemoveStudent(Student student)
    {
        long studentId = student.getId();
        for (Student courseStudent : courseStudents) 
        {
            if (studentId == courseStudent.getId()) {
                courseStudents.remove(student);
                return true;
            }
        }
        return false;
    }
}
