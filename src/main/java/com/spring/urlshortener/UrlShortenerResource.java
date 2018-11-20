package com.spring.urlshortener;

import com.google.common.hash.Hashing;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

@RequestMapping("/rest/url")
@RestController
public class UrlShortenerResource {

    @Autowired
    StringRedisTemplate redisTemplate;

    @GetMapping("/{id}")
    public String getUrl(@PathVariable String id) {
        String url = redisTemplate.opsForValue().get(id);
        System.out.println("URL retrieved: " + url);
        if (null == url) {
            throw new RuntimeException("There is no URL for the id: " + id);
        }
        return url;
    }

    @PostMapping()
    public String createUrl(@RequestBody String url) {

        UrlValidator validator = new UrlValidator(new String[]{
                "http", "https"
        });

        if (validator.isValid(url)) {
            String id = Hashing.murmur3_32().hashString(url, StandardCharsets.UTF_8).toString();

            System.out.println("URL ID generated: " + id);

            redisTemplate.opsForValue().set(id, url);

            return id;
        }

        throw new RuntimeException("URL Invalid" + url);

    }
}
