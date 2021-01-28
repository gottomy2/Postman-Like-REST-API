package edu.pjatk.postman.controller.request;

import edu.pjatk.postman.controller.request.model.GetRequestResponse;
import edu.pjatk.postman.controller.request.model.GetRequestsResponses;
import edu.pjatk.postman.controller.request.model.PostRequestRequest;
import edu.pjatk.postman.controller.request.model.PutRequestRequest;
import edu.pjatk.postman.repository.model.*;
import edu.pjatk.postman.service.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Igor Motowidło (gottomy2)
 * Complete API for request entities
 */

@RestController
@RequestMapping("/request")
public class RequestController {
    private final RequestService requestService;
    private final ParamService paramService;
    private final HeaderService headerService;
    private final BodyService bodyService;
    private final ResponseService responseService;
    private final UserService userService;

    String POSTMAN_API="http://localhost:3000/";

    @Autowired
    public RequestController(RequestService requestService, ParamService paramService, HeaderService headerService, BodyService bodyService, ResponseService responseService, UserService userService) {
        this.requestService = requestService;
        this.paramService=paramService;
        this.headerService=headerService;
        this.bodyService=bodyService;
        this.responseService=responseService;
        this.userService=userService;
    }

    /**
     * Finds Request by its id in the database.
     * @param id - id of the request to find
     * @return STATUS CODE: 200 on success with Request within body | STATUS CODE: 404 on failure.
     */
    @GetMapping("/getRequestById/{requestId}")
    public ResponseEntity<GetRequestResponse> getRequest(@PathVariable("requestId") Long id) {
        Optional<Request> request = requestService.findRequestById(id);
        return request.map(value -> ResponseEntity.ok(new GetRequestResponse(value.getId(),value.getUserId(),value.getHost(),value.getUrl(),value.getType())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Finds ids of all requests in the database and returns a list of Longs
     * @return List of all Long ids from database
     */
    @GetMapping("/getAllIds")
    public GetRequestsResponses getAllRequestsIds(){
        return new GetRequestsResponses(requestService.findAllRequests());
    }

    //TODO: solve "type=Method Not Allowed, status=405" problem.
    /**
     * Creates new Request entity in the database
     * @param requestRequest new request parameters values declared in body.
     * @return STATUS CODE: 404 on failure | STATUS CODE: 201 on success with API call of getRequest() method.
     */
    @PostMapping("/createRequest")
    public ResponseEntity<Void> createRequest(@RequestBody PostRequestRequest requestRequest){
        Optional<User> checkUser = userService.findUserById(requestRequest.getUserId());

        if(checkUser.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Request request = new Request(requestRequest.getUserId(),requestRequest.getHost(),requestRequest.getUrl(),requestRequest.getType());
        System.out.println(request);
        requestService.createRequest(request);
        return ResponseEntity.created(URI.create("http://localhost:9090/request/getRequestById/"+request.getId())).build();
    }

    public static <T> List<T> toList(Optional<T> opt) {
        return opt.isPresent()
                ? Collections.singletonList(opt.get())
                : Collections.emptyList();
    }

    @PostMapping("/sendRequest/{requestId}")
    public ResponseEntity<Void> sendRequest(@PathVariable("requestId") Long id){
        Optional<Request> requestOptional = requestService.findRequestById(id);
        System.out.println("Request Optional:" +requestOptional);
        if(requestOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            Request request = requestOptional.get();
            try{
                URL obj = new URL(POSTMAN_API+"requests/"+id);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json; utf-8");
                con.setRequestProperty("Accept", "application/json");
                con.setDoOutput(true);

                //Initializing body of the request to send information's about the request:
                JSONObject body = new JSONObject();

                //Initializing parameters parameter to add to body:
                Optional<Param> paramOptional = paramService.getParamsByRequestId(id);
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
                Optional<Header> headerOptional = headerService.getHeadersByRequestId(id);
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
                Optional<Body> bodyOptional = bodyService.getBodyByRequestId(id);
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


                //Adding body to the request
                try(OutputStream os = con.getOutputStream()) {
                    byte[] input = body.toString().getBytes("utf-8");
                    os.write(input, 0, input.length);
                }

                //getting the ResponseMessage && saving it on the database:
//                String responseMessage = con.getResponseMessage();


                URL obj2 = new URL(POSTMAN_API+"requests/"+id);
                HttpURLConnection con2 = (HttpURLConnection) obj2.openConnection();
                con2.setRequestMethod("GET");
                con2.setRequestProperty("Accept", "application/json");
                con2.setDoOutput(true);

                Response databaseResponse = new Response();
                databaseResponse.setRequestId(id);

                try(BufferedReader br = new BufferedReader(
                        new InputStreamReader(con2.getInputStream(), "utf-8"))) {
                    StringBuilder getResponse = new StringBuilder();
                    String responseLine = null;
                    while ((responseLine = br.readLine()) != null) {
                        getResponse.append(responseLine.trim());
                    }
                    System.out.println(getResponse.toString());
                    databaseResponse.setResponse(getResponse.toString());
                }

                responseService.createResponse(databaseResponse);

                if(con.getResponseCode() == 200){
                    return ResponseEntity.ok().build();
                }
            } catch(IOException e){
                System.out.println(e);
            }
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Updates existing request in the database
     * @param putRequestRequest request entity declared in body of Put REQUEST
     * @return STATUS CODE: 404 on non existing entity | STATUS CODE: 200 on success
     */
    @PutMapping("/updateRequest")
    public ResponseEntity<Void> updateRequest(@RequestBody PutRequestRequest putRequestRequest){
        System.out.println(putRequestRequest.getId());
        Optional<Request> request = requestService.findRequestById(putRequestRequest.getId());
        Optional<User> checkUser = userService.findUserById(putRequestRequest.getUserId());

        if(request.isEmpty() || checkUser.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        request.get().setId(putRequestRequest.getId());
        request.get().setUrl(putRequestRequest.getUrl());
        request.get().setUserId(putRequestRequest.getUserId());
        requestService.updateRequest(request.get());
        return ResponseEntity.ok().build();
    }

    /**
     * Removes existing Request entity from the database
     * @param id id of request to remove
     * @return STATUS CODE: 404 if entity does not exist | STATUS CODE: 200 on success
     */
    @DeleteMapping("/deleteRequest/{requestId}")
    public ResponseEntity<Void> deleteRequest(@PathVariable("requestId") Long id){
        Optional<Request> request = requestService.findRequestById(id);
        if(request.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            requestService.deleteRequest(request.get());
            Optional<Param> param = paramService.getParamsByRequestId(id);
            param.ifPresent(paramService::deleteParam);

            return ResponseEntity.ok().build();
        }
    }
}
