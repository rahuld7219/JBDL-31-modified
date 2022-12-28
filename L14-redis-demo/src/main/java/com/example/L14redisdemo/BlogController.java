package com.example.L14redisdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blog")
public class BlogController {

    private String visitorQueueKey = "Visitor_Queue";

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private BlogService blogService;


    @PostMapping
    public ResponseEntity<Long> createBlog(@RequestBody Blog blog){
        Long id = blogService.createBlog(blog);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlog(@PathVariable Long id){
        Blog blog = blogService.getBlog(id);
        return ResponseEntity.ok(blog);
    }


    /**
     * add visitor to a list (which can be used as stack or queue, depending on the operation performed)
     *
     * @param name
     * @return
     */
    @PostMapping("/addVisitor")
    public ResponseEntity<Long> addVisitor(@RequestParam String name){
        Long size = redisTemplate.opsForList().leftPush(visitorQueueKey,name); // also create the list, if not exist
        return ResponseEntity.ok(size);
    }



}
