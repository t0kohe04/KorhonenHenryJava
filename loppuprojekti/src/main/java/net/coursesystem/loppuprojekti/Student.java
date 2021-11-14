package net.coursesystem.loppuprojekti;

public class Student {
    private long studentId;
    private String studentFirstName;
    private String studentLastName;

    private long generateNewUniqueId(long lastId)
    {
        long newId = lastId++;
        return newId;
    }

    public Student(long lastId, String firstName, String lastName)
    {
        studentId = generateNewUniqueId(lastId);
        studentFirstName = firstName;
        studentLastName = lastName;
    }

    public long getId(){
        return studentId;
    }

    public String getFirstName(){
        return studentFirstName;
    }

    public String getLastName(){
        return studentLastName;
    }
}
