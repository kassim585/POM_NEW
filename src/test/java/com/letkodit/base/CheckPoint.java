package com.letkodit.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashMap;

public class CheckPoint {
    private static final Logger log = LogManager.getLogger(CheckPoint.class.getName());
    public static HashMap<String, String> resultMap = new HashMap<>();
    private static  String PASS="PASS";
    private static  String FAIL="FAIL";

    public static void clearHashMap(){
        System.out.println("Clearing results hash map");
        resultMap.clear();
    }
    private static void setStatus(String mapKey, String status){
        resultMap.put(mapKey, status);
       log.info(mapKey + "  :-> "+ resultMap.get(mapKey));
    }

    public static void mark(String testName, boolean result, String resultMessage){
        testName = testName.toLowerCase();
        String mapKey = testName +"."+ resultMessage;
        try{
            if(result){
                setStatus(mapKey, PASS);
            }else {
                setStatus(mapKey,FAIL);
            }
        }catch (Exception e){
            log.error("Exception occurred");
            setStatus(mapKey, FAIL);
            e.printStackTrace();
        }
    }

    public static void markFinal(String testName, boolean result, String resultMessage){
        testName = testName.toLowerCase();
        String mapKey = testName +"."+ resultMessage;
        try{
            if(result){
                setStatus(mapKey, PASS);
            }else {
                setStatus(mapKey,FAIL);
            }
        }catch (Exception e){
            log.error("Exception occurred");
            setStatus(mapKey, FAIL);
            e.printStackTrace();
        }

        ArrayList<String> resultList = new ArrayList<>();

        for(String key: resultMap.keySet()){
            resultList.add(resultMap.get(key));
           log.info("Result list values are  " +resultList);
           }

        for(int i=0; i<resultList.size();i++){
            if(resultList.contains(FAIL)){
                log.error("Test method Failed");
                Assert.assertTrue(false);
            }else {
                log.info("Test method Successful");
                Assert.assertTrue(true);
            }
        }
    }
}
