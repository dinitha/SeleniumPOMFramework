package test.suite.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class PetStoreAPITest{

    @BeforeTest
    public void setup(){
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    String generatedId;
    @Test(priority = 1, dataProvider = "pet-data-provider")
    public void create_A_Pet_Store_Success_Test(String jsonBody){
        try {

            Response response = RestAssured
                    .given()
                    .header("Content-Type", "application/json")
                    .header("accept", "application/json")
                    .body(jsonBody)
                    .post("/pet");

           response.prettyPrint();
           Assert.assertEquals(HttpStatus.SC_OK,response.getStatusCode());
             generatedId = response.jsonPath().getString("id");
             Assert.assertTrue(Integer.parseInt(generatedId)>0);
            Assert.assertEquals(response.header("Content-Type"),"application/json");

            appendIdToJsonFile(generatedId,"Data/created_pet_id.json");
        } catch (Exception e) {
            e.printStackTrace();
        }

          }

    @Test(priority = 2)
    public void update_A_Pet_Success_Test() throws IOException {
        appendIdToJsonFile(generatedId,"Data/update_a_pet.json");
        String jsonBody = getRequestBodyString("Data/update_a_pet.json");
        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .body(jsonBody)
                .put("/pet");

        response.prettyPrint();
        Assert.assertEquals(HttpStatus.SC_OK,response.getStatusCode());
        Assert.assertEquals(response.jsonPath().getString("id"),generatedId);
        Assert.assertEquals(response.header("Content-Type"),"application/json");

    }
    @Test(priority = 3)
    public void get_Pets_By_Status_Successfully_Test() throws IOException {


        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .queryParam("status", "pending,sold")
                .get("/pet/findByStatus");

        response.prettyPrint();
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        Assert.assertEquals(response.header("Content-Type"), "application/json");
        List<String> statuses = response.jsonPath().getList("status");

        for (String status : statuses) {
            if (status.equals("pending") || status.equals("sold")) {
                Assert.assertTrue(true);
            } else
                Assert.fail();

        }
    }
    @DataProvider(name = "pet-data-provider")
    public Object[][] petDataProvider() throws IOException {

        return new Object[][]{{getRequestBodyString("Data/create_a_new_pet_boxer.json")},
                {getRequestBodyString("Data/create_a_new_pet_bull_dog.json")},
                {getRequestBodyString("Data/create_a_new_pet_german_shepered.json")},
                {getRequestBodyString("Data/create_a_new_pet_rotweiler.json")}};

    }
    private static String getRequestBodyString(String filePath) throws IOException {
        String jsonBody = new String(Files.readAllBytes(Paths.get(filePath)));
        return jsonBody;
    }

    private static void appendIdToJsonFile(String id,String filePath) {
        try  {

            Path path = Paths.get(filePath);
            String jsonContent = new String(Files.readAllBytes(path));
           
            JSONObject jsonObject = new JSONObject(jsonContent);
            jsonObject.put("id", id);

            Files.write(path, jsonObject.toString(4).getBytes());
            System.out.println("Updated JSON: " + jsonObject.toString(4));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
