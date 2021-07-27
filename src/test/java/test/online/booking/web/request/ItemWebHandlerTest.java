/*
 * Copyright 2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package test.online.booking.web.request;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.greaterThan;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring-dispatecher-servlet.xml" , "file:src/main/webapp/WEB-INF/spring-data-configuration.xml"})
public class ItemWebHandlerTest extends RestAssured {

    @Before
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    public void whenLogRequest_thenOK() {
        given().log().all()
                .when().get("/product/list")
                .then().statusCode(200);
    }

    @Test
    public void whenLogResponse_thenOK() {
        when().get("/product/list")
                .then().log().body().statusCode(200);
    }

    @Test
    public void whenLogResponseIfErrorOccurred_thenSuccess() {

        when().get("/product/list")
                .then().log().ifError();
        when().get("/product/list")
                .then().log().ifStatusCodeIsEqualTo(500);
        when().get("/product/list")
                .then().log().ifStatusCodeMatches(greaterThan(200));
    }

    @Test
    public void whenLogOnlyIfValidationFailed_thenSuccess() {
        when().get("/product/list")
                .then().log().ifValidationFails().statusCode(200);

        given().log().ifValidationFails()
                .when().get("/product/list")
                .then().statusCode(200);
    }

}
