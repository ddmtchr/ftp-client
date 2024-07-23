package com.ddmtchr.util;

import com.ddmtchr.Main;
import com.ddmtchr.model.Student;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.stream.Collectors;

public class FTPWorker {
    private URLConnection connection;
    private List<Student> students;
    private final String serverUrl;

    public FTPWorker(String serverUrl) throws IOException {
        this.serverUrl = serverUrl;
        URL url = new URL(serverUrl + "/students.json");
        connection = url.openConnection();
        loadStudents();
    }

    private void loadStudents() throws IOException {
        InputStream inputStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder jsonBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonBuilder.append(line);
        }
        String jsonString = jsonBuilder.toString();
        students = JSONParser.parseStudents(jsonString);
        reader.close();
    }

    private long generateId() {
        long maxId = 0;
        for (Student s: students) {
            if (s.getId() > maxId) maxId = s.getId();
        }
        return maxId + 1;
    }

    public String listStudents() {
        return students.stream().map(Student::getName).sorted().collect(Collectors.joining("\n"));
    }

    public Student getStudentById(long id) {
        return students.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
    }

    public String infoById(long id) {
        Student student = getStudentById(id);
        if (student != null) return student.toString();
        return null;
    }

    public void addStudent(String name) {
        Student student = new Student(generateId(), name);
        students.add(student);
    }

    public void deleteStudent(long id) {
        students.remove(getStudentById(id));
    }

    public void saveAndExit() {
        try {
            connection = new URL(serverUrl + "/students.json").openConnection();
            String jsonString = JSONParser.toJsonString(students);
            OutputStream outputStream = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            writer.write(jsonString);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Error during saving file to the server");
        }
        Main.isRunning = false;
    }

    public void printHelp() {
        System.out.println("Available commands:\n" +
                "list - list all students\n" +
                "info <id> - info about student by id\n" +
                "add <name> - add student\n" +
                "delete <id> - delete student by id\n" +
                "exit - save and close connection");
    }

    public List<Student> getStudents() {
        return students;
    }
}
