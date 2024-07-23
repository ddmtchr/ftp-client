package com.ddmtchr;

import com.ddmtchr.model.Student;
import com.ddmtchr.util.FTPWorker;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class CrudTest {
    private FTPWorker client;

    @BeforeClass
    public void setUp() throws IOException {
        client = new FTPWorker("ftp://ftpuser:qwerty@192.168.3.23:21/"); // depends on server
    }

    @Test
    public void testAddStudent() {
        int initialSize = client.getStudents().size();
        client.addStudent("New student");
        Assert.assertEquals(client.getStudents().size(), initialSize + 1);
    }

    @Test
    public void testRemoveStudent() {
        client.addStudent("New student");
        long id = client.getStudents().get(client.getStudents().size() - 1).getId();
        client.deleteStudent(id);
        Assert.assertNull(client.getStudentById(id));
    }

    @Test
    public void testGetStudentById() {
        client.addStudent("New student");
        long id = client.getStudents().get(client.getStudents().size() - 1).getId();
        Student student = client.getStudentById(id);
        Assert.assertNotNull(student);
        Assert.assertEquals(student.getName(), "New student");
    }
}
