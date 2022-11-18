package fi.tuni.prog3.sisu;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
//import java.util.logging.Level;
//import java.util.logging.Logger;

/**
 * SisuHelper class. (Stupid class name?)
 * The class has static methods to give Sisu the degree structures.
 * from KORI API and also from the example files.
 */
public class SisuHelper implements iAPI {
    //path to the json files.
    public static final String modulePath = "../json/modules/";
    public static final String coursePath = "../json/courseunits/";
    public static final String jsonFile = ".json";


    /**
     * This method create JSON object from url
     * Structure is generated form given Json files.     *
     * @throws MalformedURLException if url is incorrect.
     * @return JSONobject.
     */
    @Override
    public JsonObject getJsonObjectFromApi(String urlString) {
        try {
            InputStream is = new URL(urlString).openStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    is, Charset.forName("UTF-8")));
            JsonArray jsonArray = JsonParser.parseReader(br).getAsJsonArray();
            JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();
            System.out.println("this is json" + jsonObject);
            return jsonObject;
        } catch (MalformedURLException ex) {
             System.out.println("The url is not well formed: " + urlString);
        } catch (IOException ex) {
             System.out.println("The url is not well formed: " + urlString);
        }
        return null;
    }


    /**
     * This static method is for the minimum requirement and help testing Sisu.
     * It generates a Degree Program structure for Sisu.
     * Structure is generated form given Json files.     *
     * @throws FileNotFoundException if any file is missing.
     * @return DegreeProgram the generated DegreeProgram.
     */
    public static DegreeProgram createDegreeProgramFromExample() throws FileNotFoundException {
        // This file is the DegreeProgram root
        String fileName = modulePath + "otm-3990be25-c9fd-4dae-904c-547ac11e8302.json";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            JsonObject jsonObject = JsonParser.parseReader(br).getAsJsonObject();

            // might need to check if the fields exists first.
            String name = jsonObject.getAsJsonObject("name").get("en").getAsString();
            String id = jsonObject.get("id").getAsString();
            String groupId = jsonObject.get("groupId").getAsString();
            String description = jsonObject.getAsJsonObject("learningOutcomes").get("fi").getAsString();
            String code = jsonObject.get("code").getAsString();
            int credits = jsonObject.getAsJsonObject("rule")
                    .getAsJsonObject("credits").get("min").getAsInt();

            // create new DegreeProgram
            DegreeProgram degreeProgram = new DegreeProgram(name, id, groupId, credits, description, code);

            // navigate(jsonObject);

            return degreeProgram;
        }
        catch (IOException e) {
            throw new FileNotFoundException(String.format("File %s not found!", fileName));
        }
    }


    /**
     * TODO
     * navigate the jsonObject with recursion, and store the data into the degree program.
     * @param jsonObject jsonObject to be navigated.
     * @throws FileNotFoundException json file not found.
     */
    public static void navigate(JsonObject jsonObject) throws FileNotFoundException {
        //System.out.println(jsonObject);
        if (jsonObject.has("rule" )) {
            navigate(jsonObject.get("rule").getAsJsonObject());
        }
        if (jsonObject.has("type")) {
            if (jsonObject.get("type").getAsString().equals("ModuleRule")) {
                String filepath = modulePath + jsonObject.get("moduleGroupId").getAsString() + jsonFile;
                //System.out.println(filepath);
                File f = new File(filepath);

                if(f.exists()) {
                    System.out.println(filepath);
                    createStudyModuleFromJsonFile(filepath);
                }
                //else System.out.printf("File %s not found!%n", filepath);

                filepath = modulePath + jsonObject.get("localId").getAsString() + jsonFile;
                f = new File(filepath);

                if(f.exists()) {
                    System.out.println(filepath);
                    createStudyModuleFromJsonFile(filepath);
                }
                //else System.out.printf("File %s not found!%n", filepath);
            }
        }
        if (jsonObject.has("rules")){
            var rules = jsonObject.getAsJsonArray("rules");

            for (var rule : rules) {
                //System.out.println(rule.getAsJsonObject());
                navigate(rule.getAsJsonObject());
            }
        }
    }


    /**
     * Creates a StudyModule (GropingModule is a StudyModule without some attributes only)
     * from the give json filename.
     * @param fileName  filename to json file.
     * @return StudyModule
     * @throws FileNotFoundException file not found
     */
    public static StudyModule createStudyModuleFromJsonFile(String fileName) throws FileNotFoundException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            JsonObject jsonObject = JsonParser.parseReader(br).getAsJsonObject();

            // Might need to check if exists first.
            String name = jsonObject.getAsJsonObject("name").get("en").getAsString();
            String id = jsonObject.get("id").getAsString();
            String groupId = jsonObject.get("groupId").getAsString();

            // A Grouping module doesn't have these fields
            String code = null;
            String description = null;
            int credits = 0;
            boolean gradable = false;

            // If the module is a studymodule
            if (jsonObject.get("type").getAsString().equals("StudyModule")) {
                code = jsonObject.get("code").getAsString();
                credits = jsonObject.getAsJsonObject("targetCredits").get("min").getAsInt();
                description = "";
                if (!jsonObject.get("contentDescription").isJsonNull()) {
                    description = jsonObject.getAsJsonObject("contentDescription").get("fi").getAsString();
                }
                gradable = jsonObject.get("gradeScaleId").getAsString().equals("sis-0-5");
            }

            // create and return the module
            return new StudyModule(name, id, groupId, credits, gradable, description, code);
        }
        catch (IOException e) {
            throw new FileNotFoundException(String.format("File %s not found!", fileName));
        }
    }


    /**
     * @param fileName
     * @return
     * @throws FileNotFoundException
     */
    public static Course createCourseFromJsonFile(String fileName) throws FileNotFoundException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            JsonObject jsonObject = JsonParser.parseReader(br).getAsJsonObject();

            // might need to check if exists first
            String name = jsonObject.getAsJsonObject("name").get("en").getAsString();
            String id = jsonObject.get("id").getAsString();
            String groupId = jsonObject.get("groupId").getAsString();
            String description = jsonObject.getAsJsonObject("content").get("en").getAsString();
            String code = jsonObject.get("code").getAsString();
            int credits = jsonObject.getAsJsonObject("credits").get("min").getAsInt();
            boolean gradable = jsonObject.get("gradeScaleId").getAsString().equals("sis-0-5");

            // create and return the course
            return new Course(name, id, groupId, credits, gradable, description, code);
        }
        catch (IOException e) {
            throw new FileNotFoundException(String.format("File %s not found!", fileName));
        }
    }
}
