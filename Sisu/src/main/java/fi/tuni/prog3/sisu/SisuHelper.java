package fi.tuni.prog3.sisu;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
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
            JsonElement jsonElement = JsonParser.parseReader(br);
            //System.out.println("this is elemment" + temp);
            if (jsonElement instanceof JsonObject){
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                //System.out.println("this is json" + jsonObject);
                return jsonObject;
            }
            else {
                JsonArray jsonArray = jsonElement.getAsJsonArray();
                //System.out.println("this is jsonarray" + jsonArray);
                JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();
                //System.out.println("this is json" + jsonObject);
                return jsonObject;
            }            
           
        } catch (MalformedURLException ex) {
             System.out.println("The url is not well formed: " + urlString);
        } catch (IOException ex) {
             System.out.println("The url is not well formed: " + urlString);
        }
        return null;
    }
    
    /**
     * Create DegreeProgram 
     * It generates a Degree Program structure for Sisu.
     * Structure is generated form given Json files.     *
     * @throws FileNotFoundException if any file is missing.
     * @return DegreeProgram the generated DegreeProgram.
     */
    public DegreeProgram createDegreeProgram(String groupId) {
        String url = "https://sis-tuni.funidata.fi/kori/api/modules/by-group-id?groupId="+groupId+"&universityId=tuni-university-root-id&curriculumPeriodId=uta-lvv-2021";
        JsonObject newDegreeProgram = getJsonObjectFromApi(url);
        
        String name = newDegreeProgram.getAsJsonObject("name").get("en").getAsString();
        String id = newDegreeProgram.get("id").getAsString();
        String description = newDegreeProgram.getAsJsonObject("learningOutcomes").get("fi").getAsString();
        String code = newDegreeProgram.get("code").getAsString();
        int credits = newDegreeProgram.getAsJsonObject("targetCredits").get("min").getAsInt();
        ArrayList <StudyModule> subModules = new ArrayList <> ();
        JsonArray rules = getAllSubModulesOrCourses(newDegreeProgram.getAsJsonObject("rule")).getAsJsonArray();
        for (int i = 0; i < rules.size(); i++){
            JsonObject submodule = rules.get(i).getAsJsonObject();
            if(submodule.get("type").getAsString().equals("ModuleRule")){
                String nextModule = submodule.get("moduleGroupId").getAsString();
                // start to create the hierarcy
                subModules.add(createStudyModule(nextModule));
            }
        }
        DegreeProgram degreeProgram = new DegreeProgram(name, id, groupId, credits, description, code);
        return degreeProgram ; 
    }

    
    public StudyModule createStudyModule(String groupId){
        String url;
        if(groupId.startsWith("tut")){
            url =" https://sis-tuni.funidata.fi/kori/api/modules/by-group-id?groupId="+groupId+"&universityId=tuni-university-root-id";
        }
        else {
            url = "https://sis-tuni.funidata.fi/kori/api/modules/"+groupId+"?universityId=tuni-university-root-id&curriculumPeriodId=uta-lvv-2021";
        }
        
        JsonObject newStudyModule = getJsonObjectFromApi(url);
        
        String name = newStudyModule.getAsJsonObject("name").get("en").getAsString();
        //System.out.println(name);
        String id = newStudyModule.get("id").getAsString();
        //System.out.println(id);
        String code = newStudyModule.get("code").getAsString();
        //System.out.println(code);
        int credits = newStudyModule.getAsJsonObject("targetCredits").get("min").getAsInt();
        //System.out.println(credits);
        Boolean gradable;
        if(newStudyModule.get("gradeScaleId").isJsonNull()){
            gradable = false;
        }else{
            gradable = newStudyModule.get("gradeScaleId").getAsString().equals("sis-0-5");
        }

        JsonArray rules = getAllSubModulesOrCourses(newStudyModule.getAsJsonObject("rule")).getAsJsonArray();
        //System.out.println(rules);
        //System.out.print(newStudyModule.getAsJsonObject("rule"));
        StudyModule studyModule = new StudyModule(name, id, groupId, credits, gradable, "", code);
        return studyModule;
    }
    
    // This could be probably implemented better
    public JsonArray getAllSubModulesOrCourses(JsonObject rule){
        JsonArray listOfSubModulesOrCourses = new JsonArray();
        // if its compositeRule it will have submodules / courses underneath (best quesss)
        
        if(rule.get("type").getAsString().equals("CompositeRule")){
            //System.out.print(rule.get("rules"));
            return rule.get("rules").getAsJsonArray();
        }
        
        else if(rule.get("type").getAsString().equals("CreditsRule")){
            
            JsonArray subRules = rule.getAsJsonObject("rule").get("rules").getAsJsonArray();
            //System.out.println(subRules.size());
            for (int i = 0; i < subRules.size(); i++){
                // More stuff can be found
                if(subRules.get(i).getAsJsonObject().get("type").getAsString().equals("CompositeRule")){
                    //System.out.println(subRules.get(i).getAsJsonObject().get("rules").getAsJsonArray());
                    //listOfSubModulesOrCourses = subRules.get(i).getAsJsonObject().get("rules").getAsJsonArray();
                    
                    return subRules.get(i).getAsJsonObject().get("rules").getAsJsonArray();
                }
                // No more stuff
                if(subRules.get(i).getAsJsonObject().get("type").getAsString().equals("ModuleRule")){

                    listOfSubModulesOrCourses.add(subRules.get(i));
                }
            } 
        }
        return listOfSubModulesOrCourses;
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////////////////
    
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
