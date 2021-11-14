package net.coursesystem.loppuprojekti;

public class LocalCourse extends Course{
    private String localCourseClassRoom;
    private int maxStudents = 25;

    public LocalCourse(long lastId, String courseName, String teacherName, String classRoom){
        super(lastId, courseName, teacherName);
        localCourseClassRoom = classRoom;
    }

    public String getCourseClassRoom()
    {
            return localCourseClassRoom;
    }
}
