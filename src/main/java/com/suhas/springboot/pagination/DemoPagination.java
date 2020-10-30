package com.suhas.springboot.pagination;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DemoPagination {

    private static Integer rowLimit = 6;
    private static List<Map<String, Object>> studentDetails = new ArrayList<>();

    static {
        for(int i=1; i<=25; i++) {
            studentDetails.add(usingFlattenMap(i));
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        char exitChar='y';
        do {
            System.out.println("Enter your page number you want to see :");
            Integer rowOffset = Integer.parseInt(reader.readLine());

            List<Map<String, Object>> pagination = getPaginationList(rowOffset);
            System.out.println("The resultant List ::" + pagination);

            System.out.println("Enter y to continue and any character apart from y to exit");
            exitChar = reader.readLine().charAt(0);
        }while(exitChar == 'y');
    }

    private static List<Map<String, Object>> getPaginationList(Integer rowOffset) {
        int remainingRecords =  studentDetails.size()%rowLimit;
        int numberOfPages = studentDetails.size()/rowLimit;
        if(remainingRecords > 0) {
            numberOfPages = numberOfPages+1 ;
        }

        int startIndex = (rowLimit*(rowOffset -1));
        int endIndex;
        if(numberOfPages != rowOffset) {
            endIndex = startIndex + rowLimit;
        } else {
            endIndex = startIndex + remainingRecords;
        }

        return studentDetails.subList(startIndex, endIndex);
    }

    private static Map<String, Object> usingFlattenMap(
            Integer counter) {
        Map<String, Object> studentInfoMap = new LinkedHashMap<>();
        studentInfoMap.put("rollnumber", counter);
        studentInfoMap.put("name", "Suhas Naik" + counter);
        studentInfoMap.put("age", 32);
        studentInfoMap.put("dob", "1987-09-16T01:01:56.300Z");
        studentInfoMap.put("subjectid", 1);
        studentInfoMap.put("subjectname", "Hindi");
        return  studentInfoMap;
    }
}
