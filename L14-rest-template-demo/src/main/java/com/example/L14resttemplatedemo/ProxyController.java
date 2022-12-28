package com.example.L14resttemplatedemo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/proxy")
public class ProxyController {

    @Autowired
    private RestTemplate restTemplate;

//    @GetMapping("/blog/{id}")
//    public ResponseEntity<String> getBlog(@PathVariable Long id){
//        String url="http://localhost:8080/blog/"+id;
//        String response = restTemplate.getForObject(url,String.class); // typecast the response to String type,
                                                                            // as any data once in network are in the form of byte,
                                                                            // even though the response send by the url server is in JSON.
                                                                            // Here, rest template converting the byte data
                                                                            // from the network to String form
//        return ResponseEntity.ok(response);
//    }


    @GetMapping("/blog/{id}")
    public ResponseEntity<BlogResponseBody> getBlog(@PathVariable Long id){
        String url="http://localhost:8080/blog/"+id; // URL of our redis demo project
        BlogResponseBody response = restTemplate.getForObject(url,BlogResponseBody.class); // here rest template converts
                                                                                            // the data to BlogResponseBody type
        return ResponseEntity.ok(response);
    }
}
