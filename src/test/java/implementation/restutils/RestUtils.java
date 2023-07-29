package implementation.restutils;

import implementation.specbuilder.RequestSpecBuilder;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RestUtils {
    public static Response getData(String basePath){
        return given(RequestSpecBuilder.getSpecificationWithoutAuth())
                .when()
                .get(basePath)
                .then()
                .log()
                .all()
                .extract()
                .response();
    }
    public static Response postData(Object payload,String basePath){
        return given(RequestSpecBuilder.getSpecificationWithoutAuth())
                .body(payload)
                .when()
                .post(basePath)
                .then()
                .log()
                .all()
                .extract()
                .response();
    }
    public static Response putData(String bookingId,Object payload,String basePath){
        return given(RequestSpecBuilder.getSpecification())
                .body(payload)
                .when()
                .put(basePath+bookingId)
                .then()
                .log()
                .all()
                .extract()
                .response();
    }
    public static Response patchData(String bookingId,Object payload,String basePath){
        return given(RequestSpecBuilder.getSpecification())
                .body(payload)
                .patch(basePath+bookingId)
                .then()
                .log()
                .all()
                .extract()
                .response();
    }
    public static Response deleteData(String bookingId,String basePath){
        return given(RequestSpecBuilder.getSpecification())
                .delete(basePath+bookingId)
                .then()
                .log()
                .all()
                .extract()
                .response();
    }

    public static Response getDataGoRest(String basePath){
        return given(RequestSpecBuilder.getSpecificationGoRest())
                .when()
                .get(basePath)
                .then()
                .log()
                .all()
                .extract()
                .response();
    }

    public static Response postDataGoRest(Object payload,String basePath){
        return given(RequestSpecBuilder.getSpecificationGoRest())
                .body(payload)
                .when()
                .post(basePath)
                .then()
                .log()
                .all()
                .extract()
                .response();
    }
}
