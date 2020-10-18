package com.suhas.springboot.jsonconverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.suhas.springboot.domain.Employee;
import com.suhas.springboot.domain.JSONDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericJSONReaderWriter<T> {

    private  ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericJSONReaderWriter.class);

    public <T extends JSONDomain<T>> T convertJSONToObject(
            final String json,
            final Class<T> jsonDomainClassType)  {
        try {
            return objectMapper.readValue(json, jsonDomainClassType);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error while converting JSON to Object", e);
        }

        return null;
    }

    public String convertObjectToJSON(
            final T jsonDomainClassInstance)  {
        try {
            String jsonInString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonDomainClassInstance);
            objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            return jsonInString;


        } catch (JsonProcessingException e) {
            LOGGER.error("Error while converting Object to JSON", e);
        }
        return null;
    }

    public static void main(String[] args) {
        String jsonString = getJSONString();
        GenericJSONReaderWriter<Employee> readerWriter = new GenericJSONReaderWriter<>();

        Employee employee = readerWriter.convertJSONToObject(jsonString, Employee.class);

        System.out.println(employee.getId());
        System.out.println(employee.getName());
        System.out.println(employee.getAddress().getCity());
        System.out.println(employee.getPersonalInformation().getMaritialstatus());
        System.out.println(employee.getPersonalInformation().getUtctimestamp());

        String outputJSON = readerWriter.convertObjectToJSON(employee);
        System.out.println(outputJSON);
    }
    
    private static String getJSONString() {
       return "{\n" +
                "  \"id\": 123,\n" +
                "  \"name\": \"Henry Smith\",\n" +
                "  \"age\": 28,\n" +
                "  \"salary\": 2000,\n" +
                "  \"designation\": \"Programmer\",\n" +
                "  \"address\": {\n" +
                "    \"street\": \"Park Avn.\",\n" +
                "    \"city\": \"Westchester\",\n" +
                "    \"zipcode\": 10583\n" +
                "  },\n" +
                "  \"phoneNumbers\": [\n" +
                "    654321,\n" +
                "    222333\n" +
                "  ],\n" +

                "  \"personalInformation\": {\n" +
                "    \"gender\": \"Male\",\n" +
                "    \"maritialstatus\": \"Married\",\n" +
                "    \"utctimestamp\": \"2012-04-23T18:25:43.511Z\"\n" +
                "  }\n" +
                "}";
    }
}
