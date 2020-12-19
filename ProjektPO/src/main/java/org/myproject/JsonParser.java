package org.myproject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonParser {
    String fileName;
    public JsonParser(String fileName){
        this.fileName=fileName;
    }
    public List<WorldDescription> makeWorlds() throws FileNotFoundException {
//        open file and return it as a WorldDescription Object
        List<WorldDescription> worlds=new ArrayList<>();
        GsonBuilder builder=new GsonBuilder();
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(this.fileName));
            worlds = new Gson().fromJson(reader, new TypeToken<List<WorldDescription>>() {}.getType());
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return worlds;
    }
}
