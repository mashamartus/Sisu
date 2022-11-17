
package fi.tuni.prog3.sisu;



public final class StudentDataExport {
    
    private String studentID;

    public StudentDataExport(String studentID)  {
        this.studentID = studentID;
        
    }
    
    // TODO
    
    
    
    
    
    /*
    public void createJSON() 
            throws MalformedURLException, ProtocolException, IOException {
        URL url = new URL("https://sis-tuni.funidata.fi/kori/api/module-search?curriculumPeriodId=uta-lvv-2021&universityId=tuni-university-root-id&moduleType=DegreeProgramme&limit=2000");
        
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        //Check if connect is made
        int responseCode = conn.getResponseCode();

        // 200 OK
        if (responseCode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        } else {
            StringBuilder informationString = new StringBuilder();
            Scanner scanner = new Scanner(url.openStream());
            
            while (scanner.hasNext()) {
                informationString.append(scanner.nextLine());
            }
        //Close the scanner
        scanner.close();
        System.out.println(informationString);

            }
    }*/
    
}

    


    

