package com.sun.cloud.web;

import com.sun.cloud.framework.annotation.Log;
import com.sun.cloud.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloControler
{

    @Autowired
    HelloService helloService;

    @GetMapping(value = "/hi")
    @Log()
    public String hi(@RequestParam String name)
    {
            return helloService.hiService(name);
    }

}