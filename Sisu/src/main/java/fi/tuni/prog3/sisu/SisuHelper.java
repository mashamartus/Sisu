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
     * Structure is generated form given Json files.
     * @param String url as a string
     * @throws MalformedURLException if url is incorrect.
     * @throws IOException if url is not responsive
     * @return JSONobject.
     */
    @Override
    public JsonObject getJsonObjectFromApi(String urlString) {
        try {
            InputStream is = new URL(urlString).openStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    is, Charset.forName("UTF-8")));
            JsonElement jsonElement = JsonParser.parseReader(br);
            if (jsonElement instanceof JsonObject){
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                return jsonObject;
            }
            else {
                JsonArray jsonArray = jsonElement.getAsJsonArray();
                JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();
                return jsonObject;
            }            
        } catch (MalformedURLException ex) {
             System.out.println("The url is not well formed: " + urlString);
        } catch (IOException ex) {
             System.out.println("Cannot find the url: " + urlString);
        }
        return null;
    }
    
    
     /**
     * This method create url from groupId
     * @param String groupId
     * @return url as a string
     */
    private String createUrl(String groupId){
        if(groupId.startsWith("tut") || groupId.startsWith("uta")){
            return "https://sis-tuni.funidata.fi/kori/api/modules/by-group-id?groupId="+groupId+"&universityId=tuni-university-root-id";
        }
        else if (groupId.startsWith("otm")){
            return "https://sis-tuni.funidata.fi/kori/api/modules/"+groupId+"?universityId=tuni-university-root-id";
        }
        else{
            return "";
        }
    }
    
 
     /**
     * This method finds fi name of the course or study module
     * get finnish name of study module or course
     * @param jsonObject jsonObject of a studyModule or course
     * @return finnish name
     */
    private String findNameFi(JsonObject jsonO){
        JsonObject content = jsonO.getAsJsonObject("name");
         if (content.has("fi")){
                return jsonO.getAsJsonObject("name").get("fi").getAsString();
            }
         else{
                return jsonO.getAsJsonObject("name").get("en").getAsString();
         }

    }
    
    /**
     * This method finds en name of the course or study module
     * get english name of study module or course
     * @param jsonObject jsonObject of a studyModule or course
     * @return english name
     */
     private String findNameEn(JsonObject jsonO){
         JsonObject content = jsonO.getAsJsonObject("name");
         if (content.has("en")){
                //System.out.println(jsonO.getAsJsonObject("name").get("en").getAsString());
                return jsonO.getAsJsonObject("name").get("en").getAsString();
            }
         else{
             //System.out.println(jsonO.getAsJsonObject("name").get("fi").getAsString());
                return jsonO.getAsJsonObject("name").get("fi").getAsString();
         }
    }
     
    /**
     * This method finds id of the course or study module
     * get id of study module or course
     * @param jsonObject jsonObject of a studyModule or course
     * @return id
     */ 
    private String findId(JsonObject jsonO){
        return jsonO.get("id").getAsString();
    }
     
     /**
     * This method finds credits of the course or study module
     * get credits  of study module or course
     * @param jsonObject jsonObject of a studyModule or course
     * @return credits
     */
    private int findCredits(JsonObject jsonO){
        if(jsonO.has("targetCredits")){
            return jsonO.getAsJsonObject("targetCredits").get("min").getAsInt();
        } 
        else if (jsonO.has("credits")){
            return jsonO.getAsJsonObject("credits").get("min").getAsInt();
        }
        else {
            return 0;
        }
    } 
    
    /**
     * This method finds code of the course or study module
     * get code of study module or course
     * @param jsonObject jsonObject of a studyModule or course
     * @return code
     */
    private String findCode(JsonObject jsonO){
        if(jsonO.has("code") && !jsonO.get("code").isJsonNull()){
                return jsonO.get("code").getAsString();
        }
        else{
            return "";    
        }

    } 
    
    /**
     * This method finds description of the course or study module
     * get description of study module or course
     * @param jsonObject jsonObject of a studyModule or course
     * @return description
     */
    private String findDescription(JsonObject jsonO){
    
        if(jsonO.has("contentDescription") && !jsonO.get("contentDescription").isJsonNull()){
            if(jsonO.getAsJsonObject("contentDescription").has("fi")){
                return jsonO.getAsJsonObject("contentDescription").get("fi").getAsString();
            }
            else {
                return jsonO.getAsJsonObject("contentDescription").get("en").getAsString();         
            }
     
        }
        else if(jsonO.has("description") && !jsonO.get("description").isJsonNull()){
            return jsonO.getAsJsonObject("description").get("fi").getAsString();   

        }
        else if(jsonO.has("learningOutcomes")&& !jsonO.get("learningOutcomes").isJsonNull()){
            return jsonO.getAsJsonObject("learningOutcomes").get("fi").getAsString();    
        }
        else{
        
            return "";
        }
    
    } 
    
    /**
     * This method finds is course or study module gradable
     * get is study module or course gradable
     * @param jsonObject jsonObject of a studyModule or course
     * @return true or false
     */
    private Boolean findIsGradable(JsonObject jsonO){
    
        if(jsonO.has("gradeScaleId") && !jsonO.get("gradeScaleId").isJsonNull() ){
            return jsonO.get("gradeScaleId").getAsString().equals("sis-0-5");
        }else {
            return false;
        }
    
    } 
    
    /**
     * This method finds is group id part of particular year curriculum
     * @param jsonObject jsonObject of a studyModule or course
     * @return true or false
     */
    private Boolean checkCurriculum(String groupId, String year){
        JsonObject module = getJsonObjectFromApi(createUrl(groupId)); 
        for (int i=0; i<module.getAsJsonArray("curriculumPeriodIds").size();i++){
            if(module.getAsJsonArray("curriculumPeriodIds").get(i).getAsString().contains(year)){
                return true;
            }
        } 
        return false;
    }

    
     /**
     * Create StudyModule to students selected degree program structure
     * It generates a Degree Program structure for Sisu.  
     * @param String groupId
     * @return Study module
     */
    
    public StudyModule createStudyModule(String groupId){
        //System.out.println(groupId+" ");
        JsonObject newStudyModule = getJsonObjectFromApi(createUrl(groupId));
        JsonArray children = new JsonArray();
        if(newStudyModule.getAsJsonObject("rule").get("type").getAsString().equals("CompositeRule")){
             getChildrenFromOneDocument(newStudyModule.getAsJsonObject("rule").get("rules").getAsJsonArray(), children);
        }
        else if (newStudyModule.getAsJsonObject("rule").get("type").getAsString().equals("CreditsRule")) {
            getChildrenFromOneDocument(newStudyModule.getAsJsonObject("rule").getAsJsonObject("rule").get("rules").getAsJsonArray(), children);
        
        }
         StudyModule studyModule = new StudyModule(findNameEn(newStudyModule), findNameFi(newStudyModule), findId(newStudyModule), groupId, findCredits(newStudyModule), findIsGradable(newStudyModule), findDescription(newStudyModule), findCode(newStudyModule));

        for (int i = 0; i<children.size();i++){
            if(children.get(i).getAsJsonObject().get("type").getAsString().equals("CourseUnitRule")){
                Course course = createCourse(children.get(i).getAsJsonObject().get("courseUnitGroupId").getAsString());
                course.setParent(studyModule);
                studyModule.addCourse(course);
            }
            else{
                StudyModule subStudyModule = createStudyModule(children.get(i).getAsJsonObject().get("moduleGroupId").getAsString());                
                subStudyModule.setParent(studyModule);
                studyModule.addStudyModule(subStudyModule);
            }    
        }
        return studyModule;
    }
    
     /**
     * Get direct children of one JSON object
     * It updated ArrayList of children   *
     * @param jsonArray parent
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
    * Create new course to students selected degree program structure 
    * @param String groupId
    * return Course
    */
    public Course createCourse(String groupId) {
        String url = "https://sis-tuni.funidata.fi/kori/api/course-units/by-group-id?groupId="+groupId+"&universityId=tuni-university-root-id";
        JsonObject newCourse = getJsonObjectFromApi(url);
        System.out.println("Course name: " + findNameFi(newCourse) + " : " + groupId);
        String description = "";
        if (!newCourse.get("content").isJsonNull()){
            JsonObject content = newCourse.getAsJsonObject("content");
            if (content.has("en")){
                description = newCourse.getAsJsonObject("content").get("en").getAsString();
            }else{
                description = newCourse.getAsJsonObject("content").get("fi").getAsString();
            }
        }
        return new Course(findNameEn(newCourse), findNameFi(newCourse), findId(newCourse), groupId, findCredits(newCourse), findIsGradable(newCourse), description, findCode(newCourse));
  
    }
    
    
    /**
    * Get list of all degree programs in Tampere Universities in certain year
    * @param int startYear
    * @param String language
    * return all degree programs available
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
            String urlDegreeProgram = "https://sis-tuni.funidata.fi/kori/api/modules/"+id+"?curriculumPeriodId=uta-lvv-"+startYear+"&universityId=tuni-university-root-id";
            JsonObject newDegreeProgram = getJsonObjectFromApi(urlDegreeProgram);
            Program programObj = new Program(findNameEn(newDegreeProgram), findNameEn(newDegreeProgram), findNameFi(newDegreeProgram), groupId, findCredits(newDegreeProgram));
            JsonArray yearsArray = program.get("curriculumPeriodIds").getAsJsonArray();
            for(JsonElement yearEl : yearsArray){
                String[] yearSrtings = yearEl.getAsString().split("-");
                programObj.addYear(Integer.parseInt(yearSrtings[2]));
            }
            allPrograms.add(programObj);
        }
        return allPrograms;        
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
    /*
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
    /*
    public static StudyModule createStudyModuleFromJsonFile(String fileName) throws FileNotFoundException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            JsonObject jsonObject = JsonParser.parseReader(br).getAsJsonObject();

            // Might need to check if exists first.
            String name = jsonObject.getAsJsonObject("name").get("en").getAsString();
            String nameFi = jsonObject.getAsJsonObject("name").get("fi").getAsString();
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
            return new StudyModule(name, nameFi, id, groupId, credits, gradable, description, code);
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
    /*
    public static Course createCourseFromJsonFile(String fileName) throws FileNotFoundException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            JsonObject jsonObject = JsonParser.parseReader(br).getAsJsonObject();

            // might need to check if exists first
            String name = jsonObject.getAsJsonObject("name").get("en").getAsString();
            String nameFi = jsonObject.getAsJsonObject("name").get("fi").getAsString();
            String id = jsonObject.get("id").getAsString();
            String groupId = jsonObject.get("groupId").getAsString();
            String description = jsonObject.getAsJsonObject("content").get("en").getAsString();
            String code = jsonObject.get("code").getAsString();
            int credits = jsonObject.getAsJsonObject("credits").get("min").getAsInt();
            boolean gradable = jsonObject.get("gradeScaleId").getAsString().equals("sis-0-5");

            // create and return the course
            return new Course(name, nameFi, id, groupId, credits, gradable, description, code);
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
   
}
