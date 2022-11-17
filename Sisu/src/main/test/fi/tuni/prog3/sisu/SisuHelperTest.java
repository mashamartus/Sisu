package fi.tuni.prog3.sisu;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

class SisuHelperTest {

    @Test
    void createDegreeProgramFromExample() throws FileNotFoundException {
        DegreeProgram test = SisuHelper.createDegreeProgramFromExample();
        System.out.println(test.getName());
        System.out.println(test.getMinCredits());
        System.out.println(test.getStudyModules().size());
        System.out.println(test.getCode());
        System.out.println(test.getDescription());


        String studyModuleTestFile = SisuHelper.modulePath +"otm-010acb27-0e5a-47d1-89dc-0f19a43a5dca.json";
        String groupingModuleTestFile = SisuHelper.modulePath +"otm-6b575bfa-e488-4ee0-a8d9-877608ce64e9.json";
        String courseTestFile = SisuHelper.coursePath +"otm-1dc4fc64-39fd-4575-aef6-280199870f71.json";

        StudyModule test2 = SisuHelper.createStudyModuleFromJsonFile(studyModuleTestFile);
        System.out.println(test2.getName());
        System.out.println(test2.getMinCredits());
        System.out.println(test2.isGradable());

        StudyModule test3 = SisuHelper.createStudyModuleFromJsonFile(groupingModuleTestFile);
        System.out.println(test3.getName());
        System.out.println(test3.getMinCredits());
        System.out.println(test3.isGradable());

        Course test4 = SisuHelper.createCourseFromJsonFile(courseTestFile);
        System.out.println(test4.getName());
        System.out.println(test4.getMinCredits());
        System.out.println(test4.isGradable());
    }
}