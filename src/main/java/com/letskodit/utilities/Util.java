package com.letskodit.utilities;

import com.google.common.collect.Ordering;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Util {
    public static void sleep(long msec, String info) {
        if (info != null) {
            System.out.println("Wait  " + msec + "for " + info);
        }
        try {
            Thread.sleep(msec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleep(long msec) {
        sleep(msec, null);
    }

    public static int getRandomNumber(int min, int max) {
        int diff = max - min;
        return (int) (min + Math.random() * diff);
    }

    public static int getRandomNumber(int number) {
        return getRandomNumber(1, number);
    }

    public static String getRandomString(int length) {
        StringBuilder sBuilder = new StringBuilder();
        String chars = "sfshrtrgfsaskkowjenbvjhf56523202525000";
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * chars.length());
            sBuilder.append(chars.charAt(index));
        }
        return sBuilder.toString();
    }


    public static String getRandomString() {
        return getRandomString(10);
    }

    public static String getSimpleDateFormat(String format) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String formattedDate = formatter.format(date);
        System.out.println("Date with format :: " + format + formattedDate);
        return formattedDate;
    }

    public static String getCurrentDateTime() {
        Calendar currDate = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = formatter.format(currDate.getTime()).replace("/", "_");
        date = date.replace(":", "_");
        System.out.println("Date and Time :: " + date);
        return date;
    }


    public static boolean verifyTextContains(String actualText, String expText) {
        if (actualText.toLowerCase().contains(expText.toLowerCase())) {
            System.out.println("Actual Text from web Application Ui --> : " + actualText);
            System.out.println("Expected Text from web Application Ui --> : " + expText);
            System.out.println("### Verification Contains !!!");
            return true;
        } else {
            System.out.println("Actual Text from web Application Ui --> : " + actualText);
            System.out.println("Expected Text from web Application Ui --> : " + expText);
            System.out.println("### Verification DOES NOT Contains !!!");
            return false;
        }

    }

    public static String getReportName() {
        String localDateTime = getCurrentDateTime();
        StringBuilder name = new StringBuilder()
                .append("Automation Report_")
                .append(localDateTime)
                .append(".html");
        return name.toString();
    }

    public boolean verifyTextMatch(String actualText, String expText) {
        if (actualText.equals(expText)) {
            System.out.println("Actual Text from web Application Ui --> : " + actualText);
            System.out.println("Expected Text from web Application Ui --> : " + expText);
            System.out.println("### Verification Contains !!!");
            return true;
        } else {
            System.out.println("Actual Text from web Application Ui --> : " + actualText);
            System.out.println("Expected Text from web Application Ui --> : " + expText);
            System.out.println("### Verification DOES NOT Contains !!!");
            return false;
        }

    }

    public  boolean verifyListContains(List<String> actList, List<String> expList) {
        int expListSize = expList.size();
        for (int i = 0; i < expListSize; i++) {
            if (!actList.contains(expList)) {
                return false;
            }
        }
        System.out.println("Actual List contains expected list");
        return true;
    }

    public boolean verifyListMatch(List<String> actList, List<String> expList) {
        boolean found = false;
        int expListSize = expList.size();
        int actListSize = actList.size();

        if (actListSize != expListSize) {
            return false;
        }
        for (int i = 0; i < actListSize; i++) {
            found = false;
            for (int j = 0; j < expListSize; j++) {
                if (verifyTextMatch(actList.get(i), expList.get(i))) {
                    found = true;
                    break;
                }
            }

        }
        if (found) {
            System.out.println("Actual List Matches Expected List !!!");
            return true;
        }
        else {
            System.out.println("Actual List DOES NOT Match Expected List !!!");
            return false;
        }
    }

    public boolean verifyItemPresentInList(List<String> actList, String item){
        int actListSize = actList.size();
        for(int i=0; i< actListSize;i++){
            if(!actList.contains(item)){
                System.out.println("Item is not present in List !!");
                return  false;
            }
        }
        System.out.println("Item is present in the List !! ");
        return true;
    }

    public boolean isListAscendingOrder(List<Long> list){
        boolean sorted = Ordering.natural().isOrdered(list);
        return sorted;
    }

    public static String getScreenshotName(String methodName, String browserName) {
        String localDateTime = getCurrentDateTime();
        StringBuilder name = new StringBuilder()
                .append(browserName)
                .append("_")
                .append(methodName)
                .append("_")
                .append(localDateTime)
                .append(".png");
        return name.toString();
    }

}
