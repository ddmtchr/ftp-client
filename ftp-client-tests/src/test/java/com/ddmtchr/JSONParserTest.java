package com.ddmtchr;

import com.ddmtchr.model.Student;
import com.ddmtchr.util.JSONParser;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class JSONParserTest {
    @Test
    public void testParseStudents() {
        String jsonString = "{ \"students\": [ { \"id\": 1, \"name\": \"Student1\" }, { \"id\": 2, \"name\": \"Student2\" } ] }";
        try {
            List<Student> students = JSONParser.parseStudents(jsonString);
            Assert.assertEquals(students.size(), 2);
            Assert.assertEquals(students.get(0).getId(), 1);
            Assert.assertEquals(students.get(0).getName(), "Student1");
            Assert.assertEquals(students.get(1).getId(), 2);
            Assert.assertEquals(students.get(1).getName(), "Student2");
        } catch (JSONParser.JSONException e) {
            Assert.fail("Failed to parse JSON.");
        }
    }

    @Test
    public void testToJsonString() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Student1"));
        students.add(new Student(2, "Student2"));

        String jsonString = JSONParser.toJsonString(students);
        String expectedJsonString = "{ \"students\": [{\"id\":1,\"name\":\"Student1\"},{\"id\":2,\"name\":\"Student2\"}] }";
        Assert.assertEquals(jsonString, expectedJsonString);
    }
}
