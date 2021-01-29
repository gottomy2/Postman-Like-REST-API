package edu.pjatk.postman.controller.main;

import edu.pjatk.postman.Helper;
import edu.pjatk.postman.repository.model.*;
import edu.pjatk.postman.service.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Igor Motowidło (gottomy2)
 * Main Controller, contains all the logics.
 * Its Task is to send all Requests with their Parameters and add Responses to the database
 * TODO: Finish the logics based on PostmanCopy URL granted by Valentyn Kuts.
 */
@RestController
@RequestMapping("/main")
public class MainController {
    private final ScenarioService scenarioService;
    private final RequestService requestService;
    private final ParamService paramService;
    private final HeaderService headerService;
    private final BodyService bodyService;
    private final ResponseService responseService;
    private final UserService userService;

    String POSTMAN_API="http://localhost:3000/";

    @Autowired
    public MainController(ScenarioService scenarioService,RequestService requestService, ParamService paramService, HeaderService headerService, BodyService bodyService, ResponseService responseService, UserService userService) {
        this.scenarioService=scenarioService;
        this.requestService = requestService;
        this.paramService = paramService;
        this.headerService = headerService;
        this.bodyService = bodyService;
        this.responseService = responseService;
        this.userService = userService;
    }

    // Template: ResponseEntity<C> (Class)
    @PostMapping("test/userId/{userId}")
    public void getParamsByUserId(@PathVariable("userId") Long user_id){
        Optional<Request> requestOptional = requestService.getRequestsByUserId(user_id);
        List<Request> requestList = Helper.toList(requestOptional);
//        Param[] paramList = paramService.getParamsByRequestId(user_id);

        for(int i=0;i<requestList.size();i++){
            Request request = requestList.get(i);
//            Param param = paramList[i];
        }
    }

    public static <T> List<T> toList(Optional<T> opt) {
        return opt.isPresent()
                ? Collections.singletonList(opt.get())
                : Collections.emptyList();
    }

    public int sendRequest(Optional<Request> requestOptional){
        Request request = requestOptional.get();
        try{
            URL obj = new URL(POSTMAN_API+"requests/"+request.getId());
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            //Initializing body of the request to send information's about the request:
            JSONObject body = new JSONObject();

            //Initializing parameters parameter to add to body:
            Optional<Param> paramOptional = paramService.getParamsByRequestId(request.getId());
            List<Param> paramList = toList(paramOptional);
            System.out.println("ParamList:" + paramList);
            JSONObject paramsJson = new JSONObject();
            if(!paramList.isEmpty()){
                for(int i =0; i < paramList.size(); i ++) {
                    String name = paramList.get(i).getName();
                    String value = paramList.get(i).getValue();
                    paramsJson.put(name, value);
                }
            }
            //Adding parameters to body:
            body.put("Params",paramsJson);
            System.out.println("ParamsJson: " + paramsJson);

            //Initializing header for request:
            Optional<Header> headerOptional = headerService.getHeadersByRequestId(request.getId());
            List<Header> headerList = toList(headerOptional);
            JSONObject headerJson = new JSONObject();
            if(!headerList.isEmpty()){
                for(int i =0;i<headerList.size();i++){
                    String name = headerList.get(i).getName();
                    String value = headerList.get(i).getValue();
                    headerJson.put(name,value);
                }
            }
            //Adding headers to body:
            body.put("Headers",headerJson);
            System.out.println("HeadersJson: " +headerJson);

            //Initializing body for request:
            Optional<Body> bodyOptional = bodyService.getBodyByRequestId(request.getId());
            List<Body> bodyList = toList(bodyOptional);
            JSONObject bodyJson = new JSONObject();
            if(!bodyList.isEmpty()){
                for (int i = 0; i < bodyList.size(); i++) {
                    String name = bodyList.get(i).getName();
                    String value = bodyList.get(i).getValue();
                    bodyJson.put(name,value);
                }
            }
            //Adding body to body:
            body.put("Body",bodyJson);
            System.out.println("BodyJson:" + bodyJson);


            //Adding method,host,url of request to body of the request:
            body.put("UserId",request.getUserId().toString());
            body.put("Method",request.getType());
            System.out.println("Request TYPE:" + request.getType());
            body.put("Host",request.getHost());
            System.out.println("Request HOST:" + request.getHost());
            body.put("Url",request.getUrl());
            System.out.println("Request URL:" + request.getUrl());

            System.out.println(body.toString());
            //Adding body to the request
            try(OutputStream os = con.getOutputStream()) {
                byte[] input = body.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            //getting the ResponseMessage && saving it on the database:
            String responseMessage = con.getResponseMessage();


            URL obj2 = new URL(POSTMAN_API+"requests/"+request.getId());
            HttpURLConnection con2 = (HttpURLConnection) obj2.openConnection();
            con2.setRequestMethod("GET");
            con2.setRequestProperty("Accept", "application/json");
            con2.setDoOutput(true);

            Response databaseResponse = new Response();
            databaseResponse.setRequestId(request.getId());

            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(con2.getInputStream(), "utf-8"))) {
                StringBuilder getResponse = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    getResponse.append(responseLine.trim());
                }
                System.out.println("RESPONSE: " + getResponse.toString());
                databaseResponse.setResponse(getResponse.toString());
            }

            responseService.createResponse(databaseResponse);

            return con.getResponseCode();
        } catch(IOException e){
            System.out.println(e);
        }
        return 0;
    }

    @PostMapping("/sendRequest/{requestId}")
    public ResponseEntity<Void> sendRequest(@PathVariable("requestId") Long id){
        Optional<Request> requestOptional = requestService.findRequestById(id);
        System.out.println("Request Optional:" +requestOptional);
        if(requestOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            if(sendRequest(requestOptional)==200){
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/sendScenario/{scenarioId}")
    public ResponseEntity<Void> sendScenario(@PathVariable("scenarioId") Long scenarioId){
        Optional<Scenario> scenarioOptional = scenarioService.getScenarioById(scenarioId);
        if(scenarioOptional.isPresent()){
            Scenario scenario = scenarioOptional.get();
            var requestIds = scenario.getRequestIds().split("/");
            System.out.println(requestIds);

            for (int i = 0; i < requestIds.length; i++) {
                Optional<Request> requestOptional = requestService.findRequestById(Long.parseLong(requestIds[i]));
                if(requestOptional.isPresent()){
                    sendRequest(requestOptional);
                }
            }
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
