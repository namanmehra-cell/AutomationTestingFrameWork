package com.Data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Parsejson {
    
   @SuppressWarnings("deprecation")
   public List<HashMap<String, String>> getJsonConvertToMap(String pathAddress) throws IOException{//"D:\\Automation testing\\SeleniumFrameworkDesign\\src\\test\\java\\com\\Data\\DataFile.json"
        String jsonConvert=FileUtils.readFileToString(new File(pathAddress),StandardCharsets.UTF_8);
        ObjectMapper obj = new ObjectMapper();
        List<HashMap<String,String>> data = obj.readValue(jsonConvert, new TypeReference<List<HashMap<String,String>>>() {});
        return data;
        
    }
    
}
