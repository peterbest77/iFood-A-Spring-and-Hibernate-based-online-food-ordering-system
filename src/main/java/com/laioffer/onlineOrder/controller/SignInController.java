package com.laioffer.onlineOrder.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Controller
public class SignInController {
    //ObjectMapper返回的是json对象
    //底下怎么检验value是不是true的？？？？ 这个值是不是true都行只要有个值就行，下回问问后端老师
    //登陆成功显示什么？？？ Answer: we only process the failed login request here, if login successfully, it will automatically redirect to home page

    private final ObjectMapper objectMapper = new ObjectMapper();
    @RequestMapping(value = "/login")
    public void loginFailureHandler(@RequestParam(value = "error") String error, HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        Map<String, Object> data = new HashMap<>();
        data.put("message", "bad credentials");
        response.getOutputStream()
                .println(objectMapper.writeValueAsString(data));


    }
}
