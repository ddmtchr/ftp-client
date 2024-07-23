package com.ddmtchr.util;

import com.ddmtchr.model.Student;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class JSONParser {
    public static List<Student> parseStudents(String jsonString) throws JSONException {
        List<Student> students = new ArrayList<>();
        jsonString = jsonString.trim();

        if (jsonString.startsWith("{")) {
            jsonString = jsonString.substring(1);
        }
        if (jsonString.endsWith("}")) {
            jsonString = jsonString.substring(0, jsonString.length() - 1);
        }

        String[] parts = jsonString.split("\"students\"\\s*:\\s*\\[");
        if (parts.length != 2) {
            throw new JSONException("Invalid JSON format");
        }

        String studentsArrayPart = parts[1].trim();
        if (studentsArrayPart.endsWith("]")) {
            studentsArrayPart = studentsArrayPart.substring(0, studentsArrayPart.length() - 1).trim();
        }

        String[] studentObjects = studentsArrayPart.split("\\s*}\\s*,\\s*\\{\\s*");
        for (int i = 0; i < studentObjects.length; i++) {
            String studentObject = studentObjects[i];
            if (!studentObject.startsWith("{")) {
                studentObject = "{" + studentObject;
            }
            if (!studentObject.endsWith("}")) {
                studentObject = studentObject + "}";
            }
            Student student = parseStudent(studentObject);
            students.add(student);
        }

        return students;
    }

    private static Student parseStudent(String studentString) throws JSONException {
        Student student = new Student();
        studentString = studentString.trim();

        // Убираем внешние фигурные скобки
        if (studentString.startsWith("{")) {
            studentString = studentString.substring(1);
        }
        if (studentString.endsWith("}")) {
            studentString = studentString.substring(0, studentString.length() - 1);
        }

        // Парсим поля
        String[] fields = studentString.split("\\s*,\\s*");
        for (String field : fields) {
            String[] keyValue = field.split("\\s*:\\s*");
            if (keyValue.length != 2) {
                throw new JSONException("Invalid JSON format");
            }

            String key = keyValue[0].trim().replaceAll("^\"|\"$", "");
            String value = keyValue[1].trim().replaceAll("^\"|\"$", "");

            switch (key) {
                case "id":
                    student.setId(Integer.parseInt(value));
                    break;
                case "name":
                    student.setName(value);
                    break;
                default:
                    throw new JSONException("Unknown field: " + key);
            }
        }

        return student;
    }

    public static String toJsonString(List<Student> students) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{ \"students\": [");
        int i = 0;
        for (Student student: students) {
            jsonBuilder.append("{");
            jsonBuilder.append("\"id\":").append(student.getId()).append(",");
            jsonBuilder.append("\"name\":\"").append(student.getName()).append("\"");
            jsonBuilder.append("}");
            if (i++ < students.size() - 1) {
                jsonBuilder.append(",");
            }
        }
        jsonBuilder.append("] }");
        return jsonBuilder.toString();
    }

    public static class JSONException extends RuntimeException {
        public JSONException(String message) {
            super(message);
        }
    }
}
