package fi.tuni.prog3.sisu;

import com.google.gson.JsonObject;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * TODO
 * Teacher class, this is an additional feature.
 */
public class Teacher implements iAPI{
    
    
    /**
     * default construct
     */
    public Teacher(){}

    @Override
    public JsonObject getJsonObjectFromApi(String urlString) {
        return null;
    }

    //@Override
    public boolean readFromFile(String fileName) throws FileNotFoundException {
        return false;
    }

   // @Override
    public boolean writeToFile(String fileName) throws IOException {
        return false;
    }
}
