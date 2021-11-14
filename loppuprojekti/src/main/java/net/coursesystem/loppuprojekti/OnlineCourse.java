package net.coursesystem.loppuprojekti;

import java.util.List;

public class OnlineCourse extends Course{

    private String onlineCourseAddress;

    public OnlineCourse(long lastId, String courseName, String teacherName, String courseAddress)
    {
        super(lastId, courseName, teacherName);
        onlineCourseAddress = courseAddress;
    }

    public String getCourseAddress()
    {
            return onlineCourseAddress;
    }
}