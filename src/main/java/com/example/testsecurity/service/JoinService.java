package com.example.testsecurity.service;

import com.example.testsecurity.dto.JoinDTO;
import com.example.testsecurity.entity.UserEntity;
import com.example.testsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

//    public void joinProcess(JoinDTO joinDTO) throws BadRequestException {
//        System.out.println(userRepository.existsByUsername(joinDTO.getUsername()));
//        if(userRepository.existsByUsername(joinDTO.getUsername())){
//            System.out.println("아이디 이미 존재");
//            throw new RuntimeException("아이디가 이미 존재합니다.");
//        }
//        UserEntity data = new UserEntity();
//        data.setUsername(joinDTO.getUsername());
//        data.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));
//        data.setRole("ROLE_ADMIN");
//
//        userRepository.save(data);
//    }

    public void joinProcess(JoinDTO joinDTO) {
        boolean isUser = userRepository.existsByUsername(joinDTO.getUsername());
        if(isUser){
            return;
        }
        UserEntity data = new UserEntity();
        data.setUsername(joinDTO.getUsername());
        data.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));
        data.setRole("ROLE_ADMIN");
        userRepository.save(data);
    }
}
