package net.coursesystem.loppuprojekti;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    CourseService courseService;

    Controller()
    {
        courseService = new CourseService();
    }

    @GetMapping("students")
    public String getStudents(){
        String students = "";
        HashMap<String, String> studentsMap = new HashMap<>();
        for (Student student : courseService.getStudents()) 
        {
            studentsMap.put("id", Long.toString(student.getId()));
            studentsMap.put("firstName", student.getFirstName());
            studentsMap.put("lastName", student.getLastName());

            students += studentsMap.toString();
        }
        return students;
    }

    @GetMapping("courses")
    public String getCourses(){
        String courses = "";
        HashMap<String, String> coursesMap = new HashMap<>();
        for (Course course : courseService.getCourses()) 
        {
            coursesMap.put("id", Long.toString(course.getId()));   
            coursesMap.put("courseName", course.getCourseName());
            coursesMap.put("teacherName", course.getCourseTeacher());

            String students = "";
            HashMap<String, String> studentsMap = new HashMap<>();
            for (Student student : course.getCourseStudents()) 
            {
                studentsMap.put("id", Long.toString(student.getId()));
                studentsMap.put("firstName", student.getFirstName());
                studentsMap.put("lastName", student.getLastName());
    
                students += studentsMap.toString();
            }
            
            coursesMap.put("students", students); 
            courses += coursesMap.toString();
        }
        return courses;
    }

    @GetMapping("onlinecourses")
    public String getOnlineCourses(){
        return new String("onlinecourses");
    }

    @GetMapping("students/{id}")
    public String getStudentsWithId(@PathVariable String id){
        Long longId = Long.parseLong(id);
        Student student = courseService.getStudentById(longId);
        String studentInfo = "";
        HashMap<String, String> studentsMap = new HashMap<>();
        studentsMap.put("id", Long.toString(student.getId()));
        studentsMap.put("firstName", student.getFirstName());
        studentsMap.put("lastName", student.getLastName());

        studentInfo = studentsMap.toString();
        return studentInfo;
    }

    @GetMapping("courses/{id}")
    public String getCoursesWithId(@PathVariable String id){
        Long longId = Long.parseLong(id);
        Course course = courseService.getCourseById(longId);
        String courseInfo = "";
        HashMap<String, String> coursesMap = new HashMap<>();
        coursesMap.put("id", Long.toString(course.getId()));   
        coursesMap.put("courseName", course.getCourseName());
        coursesMap.put("teacherName", course.getCourseTeacher());

        String students = "";
        HashMap<String, String> studentsMap = new HashMap<>();
        for (Student student : course.getCourseStudents()) 
        {
            studentsMap.put("id", Long.toString(student.getId()));
            studentsMap.put("firstName", student.getFirstName());
            studentsMap.put("lastName", student.getLastName());
    
            students += studentsMap.toString();
        }
            
        coursesMap.put("students", students); 
        courseInfo = coursesMap.toString();

        return courseInfo;
    }

    @PostMapping("add")
    public String postStudentToCourse(@RequestBody String body) throws JsonMappingException, JsonProcessingException{
        // TODO: Fix JSON parsing from request body
        Boolean wasAddedToCourse = false;
        ObjectMapper jsonMapper = new ObjectMapper();
        JsonNode bodyData = jsonMapper.readTree(body);
        Long studentId = Long.parseLong(bodyData.get("sid").toString());
        Long courseId = Long.parseLong(bodyData.get("cid").toString());

        Student student = courseService.getStudentById(studentId);
        Course course = courseService.getCourseById(courseId);
        
        wasAddedToCourse = courseService.addStudentToCourse(studentId, courseId);

        return wasAddedToCourse.toString();
    }
}
