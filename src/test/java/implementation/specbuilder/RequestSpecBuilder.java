package implementation.specbuilder;

import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class RequestSpecBuilder {
    public static Properties properties = new Properties();
    public static String apiUrl;
    public static String token;
    public static String restUrl;


    public static void properties(){
        try (InputStream inputStream = new FileInputStream("src/config.properties")) {
            properties.load(inputStream);

// Access the URL and string by their keys
            apiUrl = properties.getProperty("url");
            token=properties.getProperty("authToken");
            restUrl=properties.getProperty("goRestUrl");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static RequestSpecification getSpecificationWithoutAuth(){
        properties();
        return new io.restassured.builder.RequestSpecBuilder()
                .setBaseUri(apiUrl)
                .addHeader("Content-Type","application/json")
                .addHeader("Accept","application/json")
                .log(LogDetail.ALL)
                .build();
    }

    public static RequestSpecification getSpecification(){
        properties();
        return new io.restassured.builder.RequestSpecBuilder()
                .setBaseUri(apiUrl)
                .addHeader("Content-Type","application/json")
                .addHeader("Accept","application/json")
                .addHeader("Authorization",token)
                .log(LogDetail.ALL)
                .build();
    }
    public static RequestSpecification getSpecificationGoRest(){
        properties();
        return new io.restassured.builder.RequestSpecBuilder()
                .setBaseUri(restUrl)
                .addHeader("Content-Type","application/json")
                .addHeader("Accept","application/json")
                .addHeader("Authorization","Bearer 5a794999e307a66eae682d166bd415f0f2d31c1cdb9dcea3b2970c1c9241af84")
                .log(LogDetail.ALL)
                .build();
    }
}
