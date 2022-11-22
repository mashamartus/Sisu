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
import java.util.HashMap;
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
     * Create StudyModule
     * It generates a Degree Program structure for Sisu.
     * Structure is generated form given Json files.     *
     * @throws FileNotFoundException if any file is missing.
     * @return DegreeProgram the generated DegreeProgram.
     */
    
    public StudyModule createStudyModule(String groupId){
        //System.out.print(groupId+" ");
        
        String url;
        if(groupId.startsWith("tut") || groupId.startsWith("uta")){
            url =" https://sis-tuni.funidata.fi/kori/api/modules/by-group-id?groupId="+groupId+"&universityId=tuni-university-root-id&curriculumPeriodId=uta-lvv-2021";
        }
        else if (groupId.startsWith("otm")){
            url = "https://sis-tuni.funidata.fi/kori/api/modules/"+groupId+"?universityId=tuni-university-root-id&curriculumPeriodId=uta-lvv-2021";
        }
        else{
            url = "";
        }
 
        JsonObject newStudyModule = getJsonObjectFromApi(url);
        String name;
        JsonObject content = newStudyModule.getAsJsonObject("name");
         if (content.has("en")){
                name = newStudyModule.getAsJsonObject("name").get("en").getAsString();
            }else{
                name = newStudyModule.getAsJsonObject("name").get("fi").getAsString();
            }
        //System.out.println("StudyModule: "+name+" : "+groupId);
        String id = newStudyModule.get("id").getAsString();
        //System.out.println("ID: "+id);
        String code = "";
        if(newStudyModule.has("code")){
            if(!newStudyModule.get("code").isJsonNull()){
                code = newStudyModule.get("code").getAsString();
            }
        }
        
        //System.out.println("code: "+code);
        int credits;
        if(newStudyModule.has("credits")){
            credits = newStudyModule.getAsJsonObject("targetCredits").get("min").getAsInt();
        }else{
            credits = 0;
        }
        //System.out.println("credit: " + credits);

        String description; 
        if(newStudyModule.has("learningOutcomes")){
            description = newStudyModule.getAsJsonObject("learningOutcomes").get("fi").getAsString();    
        }else{
            description = "";
        }
        //System.out.println("description: "+description);
        Boolean gradable = false;
        if(newStudyModule.has("gradeScaleId")){
            if(!newStudyModule.get("gradeScaleId").isJsonNull()){
                gradable = newStudyModule.get("gradeScaleId").getAsString().equals("sis-0-5");
            }   
        }
        //System.out.println(gradable);
        JsonArray children = new JsonArray();
        if(newStudyModule.getAsJsonObject("rule").get("type").getAsString().equals("CompositeRule")){
             getChildrenFromOneDocument(newStudyModule.getAsJsonObject("rule").get("rules").getAsJsonArray(), children);
        }
        else if (newStudyModule.getAsJsonObject("rule").get("type").getAsString().equals("CreditsRule")) {
            getChildrenFromOneDocument(newStudyModule.getAsJsonObject("rule").getAsJsonObject("rule").get("rules").getAsJsonArray(), children);
        
        }
        //System.out.println(children);
        StudyModule studyModule = new StudyModule(name, id, groupId, credits, gradable, description, code);
        
        for (int i = 0; i<children.size();i++){
            if(children.get(i).getAsJsonObject().get("type").getAsString().equals("CourseUnitRule")){
                Course course = createCourse(children.get(i).getAsJsonObject().get("courseUnitGroupId").getAsString());
                course.setParent(studyModule);
                studyModule.addCourse(course);
                
            }
            else{
                //System.out.print(" "+groupId);
                StudyModule subStudyModule = createStudyModule(children.get(i).getAsJsonObject().get("moduleGroupId").getAsString());
                subStudyModule.setParent(studyModule);
                studyModule.addStudyModule(subStudyModule);
            }    
        }
        return studyModule;
    }
    
     /**
     * Get direct children of one JSON object
     * It updated ArrayList of children
     * Structure is generated form given Json files.     *
     * @param jsonArray JSON objects rules
     * @param jsonArray children
     */
    public void getChildrenFromOneDocument(JsonArray rules, JsonArray children){
        for (int i = 0; i<rules.size();i++){
            if(rules.get(i).getAsJsonObject().get("type").getAsString().matches("ModuleRule|CourseUnitRule")){
                    children.add(rules.get(i));
            }else if (rules.get(i).getAsJsonObject().get("type").getAsString().equals("CompositeRule")) {
                getChildrenFromOneDocument(rules.get(i).getAsJsonObject().get("rules").getAsJsonArray(),children);
            }
        }
    }
    
 
    /**
    * Get direct children of one JSON object
    * It updated ArrayList of children
    * Structure is generated form given Json files.     *
    * @param jsonArray JSON objects rules
    * @param jsonArray children
    */
    public Course createCourse(String groupId) {
        String url = "https://sis-tuni.funidata.fi/kori/api/course-units/by-group-id?groupId="+groupId+"&universityId=tuni-university-root-id";
        JsonObject newCourse = getJsonObjectFromApi(url);
        String name = newCourse.getAsJsonObject("name").get("en").getAsString();
        //System.out.println("Course name: " + name + " : " + groupId);
        String id = newCourse.get("id").getAsString();
        //System.out.println("Course id: "+id);
        String description = "";
        if (!newCourse.get("content").isJsonNull()){
            JsonObject content = newCourse.getAsJsonObject("content");
            if (content.has("en")){
                description = newCourse.getAsJsonObject("content").get("en").getAsString();
            }else{
                description = newCourse.getAsJsonObject("content").get("fi").getAsString();
            }
        }
 
        //System.out.println("Description: " + description);
        String code = newCourse.get("code").getAsString();
        //System.out.println("Code: " + code);
        int credits = newCourse.getAsJsonObject("credits").get("min").getAsInt();
        //System.out.println("Credits: " + credits);
        boolean gradable = newCourse.get("gradeScaleId").getAsString().equals("sis-0-5");
        //System.out.println("gradable: " + gradable);
        return new Course(name, id, groupId, credits, gradable, description, code);
  
    }
    
       /**
     * Create DegreeProgram  NOT NEEDED WE WILL USE STUDYMODULE
     * It generates a Degree Program structure for Sisu.
     * Structure is generated form given Json files.     *
     * @throws FileNotFoundException if any file is missing.
     * @return DegreeProgram the generated DegreeProgram.
     */
  
    /*
    public DegreeProgram createDegreeProgram(String groupId) {
        String url = "https://sis-tuni.funidata.fi/kori/api/modules/by-group-id?groupId="+groupId+"&universityId=tuni-university-root-id&curriculumPeriodId=uta-lvv-2021";
        JsonObject newDegreeProgram = getJsonObjectFromApi(url);
        
        String name = newDegreeProgram.getAsJsonObject("name").get("en").getAsString();
        String id = newDegreeProgram.get("id").getAsString();
        String description = newDegreeProgram.getAsJsonObject("learningOutcomes").get("fi").getAsString();
        String code = newDegreeProgram.get("code").getAsString();
        int credits = newDegreeProgram.getAsJsonObject("targetCredits").get("min").getAsInt();
        ArrayList <StudyModule> subModules = new ArrayList <> ();
        JsonArray rules = getChildrenFromOneDocument(newDegreeProgram.getAsJsonObject("rule")).getAsJsonArray();
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
    }*/
    
    /*
    public JsonArray getChildrenFromOneDocument(JsonObject rule){
        JsonArray listOfSubModulesOrCourses = new JsonArray();
        // if its compositeRule it will have submodules / courses underneath (best quesss)
        
        if(rule.get("type").getAsString().equals("CompositeRule")){
            //System.out.print(rule.get("rules"));
            JsonArray temp = rule.get("rules").getAsJsonArray();
            for (int i = 0; i<temp.size();i++){
                if(temp.get(i).getAsJsonObject().get("type").getAsString().matches("ModuleRule|CourseUnitRule")){

                    listOfSubModulesOrCourses.add(temp.get(i));
                }
            }
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
    */
    
    ///////////////////////////////////////////////////////////////////////////////////////////
    
    /**
     * This static method is for the minimum requirement and help testing Sisu.
     * It generates a Degree Program structure for Sisu.
     * Structure is generated form given Json files.     *
     * @throws FileNotFoundException if any file is missing.
     * @return DegreeProgram the generated DegreeProgram.
     */
    /*
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
    }*/


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
    
    /**
     * Getting all programs from Sisu database for relevant year. 
     * Method return all programs where curriculum exists for given year 
     * @param startYear the start year of the studies. From 
     * @return list of @see fi.tuni.prog3.sisu.Program for all programs relevant 
     * for given year
     * @author mariia
     */
    public ArrayList<Program> getAllPrograms(int startYear, String language) {
        ArrayList<Program> allPrograms = new ArrayList<>();
        
        String url = "https://sis-tuni.funidata.fi/kori/api/module-search?curriculumPeriodId=uta-lvv-" + 
                startYear + "&universityId=tuni-university-root-id&moduleType=DegreeProgramme&limit=1000";

        JsonObject programsJSON = getJsonObjectFromApi(url);
        JsonArray programsArray = programsJSON.get("searchResults").getAsJsonArray();
        for(JsonElement programJSON : programsArray){
            JsonObject program = programJSON.getAsJsonObject();
            
            String groupId = program.get("groupId").getAsString();
            String id = program.get("id").getAsString();
            //System.out.println(id);
            // get language dependent name
            String urlDegreeProgram = "https://sis-tuni.funidata.fi/kori/api/modules/"+id+"?curriculumPeriodId=uta-lvv-"+startYear+"&universityId=tuni-university-root-id";
            JsonObject newDegreeProgram = getJsonObjectFromApi(urlDegreeProgram);
            String name = newDegreeProgram.getAsJsonObject("name").get("en").getAsString();
            //System.out.println(id + " : " + name);
            JsonObject content = newDegreeProgram.getAsJsonObject("name");
            String nameFi;
            if (content.has("fi")){
                nameFi = newDegreeProgram.getAsJsonObject("name").get("fi").getAsString();
            } 
            else {
                nameFi = newDegreeProgram.getAsJsonObject("name").get("en").getAsString();    
            }

            int minCredits = program.getAsJsonObject("credits").get("min").getAsInt();
            Program programObj = new Program(name, name, nameFi, groupId, minCredits);
            JsonArray yearsArray = program.get("curriculumPeriodIds").getAsJsonArray();
            for(JsonElement yearEl : yearsArray){
                String[] yearSrtings = yearEl.getAsString().split("-");
                programObj.addYear(Integer.parseInt(yearSrtings[2]));
            }
            allPrograms.add(programObj);
        }
        return allPrograms;        
    }
}
