package fi.tuni.prog3.sisu;

import com.google.gson.JsonObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

/**
 * TODO
 * Student class
 */
public class Student implements iAPI, iReadAndWriteToFile{
    private String studentID;
    // Student may have multiple DegreePrograms?
    private HashMap<String, DegreeProgram> degreePrograms = new HashMap<>();

    public Student(String studentID) {
        this.studentID = studentID;
    }

    @Override
    public JsonObject getJsonObjectFromApi(String urlString) {
        return null;
    }

    @Override
    public boolean readFromFile(String fileName) throws FileNotFoundException {
        return false;
    }

    @Override
    public boolean writeToFile(String fileName) throws IOException {
        return false;
    }
}
