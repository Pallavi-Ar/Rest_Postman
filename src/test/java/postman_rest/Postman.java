package postman_rest;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class Postman {
    @Test
    void getInfo() {
        RestAssured.baseURI = "https://api.getpostman.com";

        given()
                .header("X-API-key","PMAK-64ba19baa6fb7d002ad4d123-a03cc06c8cc215d3620ff10dcec170aa45")
                .contentType("application/json")
                .when()
                .get("/workspaces")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    void create() {
        RestAssured.baseURI = "https://api.getpostman.com";

        HashMap<String, Object> payload = new HashMap<>();
        HashMap<String, Object> innermap = new HashMap<>();
        innermap.put("name","creating");
        innermap.put("type","personal");
        innermap.put("description","new workspace");
        payload.put("workspace",innermap);

        given()
                .header("X-API-key", "PMAK-64ba19baa6fb7d002ad4d123-a03cc06c8cc215d3620ff10dcec170aa45")
                .contentType("application/json")
                .body(payload)
                .when()
                .post("/workspaces")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    void update() {
        RestAssured.baseURI = "https://api.getpostman.com";

        HashMap<String,Object> updatePayload = new HashMap<>();
        HashMap<String,Object> innerData = new HashMap<>();

        innerData.put("name","UpdatedName");
        innerData.put("type","personal");
        innerData.put("description","updated data");

        updatePayload.put("workspace",innerData);

        given()
                .header("X-API-key", "PMAK-64ba19baa6fb7d002ad4d123-a03cc06c8cc215d3620ff10dcec170aa45")
                .contentType("application/json")
                .pathParam("id","25caa243-f95d-492c-b923-badae350f4e9")
                .body(updatePayload)
                .when()
                .put("/workspaces/{id}")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    void del() {
        RestAssured.baseURI = "https://api.getpostman.com";

        given()
                .header("X-API-key", "PMAK-64ba19baa6fb7d002ad4d123-a03cc06c8cc215d3620ff10dcec170aa45")
                .contentType("application/json")
                .pathParam("id","32c638b6-ff42-4de7-a1c7-35e27592d034")
                .when()
                .delete("/workspaces/{id}")
                .then()
                .statusCode(200)
                .log().all();
    }
}
