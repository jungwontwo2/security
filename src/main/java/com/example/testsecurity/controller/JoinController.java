package com.example.testsecurity.controller;

import com.example.testsecurity.dto.JoinDTO;
import com.example.testsecurity.exception.BadRequestException;
import com.example.testsecurity.service.JoinService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JoinController {
    @Autowired
    private JoinService joinService;

    @GetMapping("/join")
    public String joinP(){
        return "join";
    }

    @PostMapping("/joinProc")
    public String joinProcess(JoinDTO joinDTO, HttpServletRequest request){
        System.out.println("joinDTO = " + joinDTO.getUsername());
        try {
            joinService.joinProcess(joinDTO);
        }catch (RuntimeException e){
            request.setAttribute("msg","아이디가 이미 존재합니다.");
            request.setAttribute("redirectUrl","/join");
            return "/common/messageRedirect";
        }
        return "redirect:/login";
    }
}
