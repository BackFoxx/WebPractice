package com.example.WebPractice.controller;

import com.example.WebPractice.Model.UserEntity;
import com.example.WebPractice.dto.ResponseDTO;
import com.example.WebPractice.dto.UserDTO;
import com.example.WebPractice.security.TokenProvider;
import com.example.WebPractice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private TokenProvider tokenProvider;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        try {
            UserEntity user = UserEntity.builder()
                    .email(userDTO.getEmail())
                    .username(userDTO.getUsername())
                    .password(userDTO.getPassword()).build();

            UserEntity registerUser = userService.create(user);
            UserDTO responseUserDTO = UserDTO.builder()
                    .email(registerUser.getEmail())
                    .id(registerUser.getId())
                    .username(registerUser.getUsername())
                    .build();

            return ResponseEntity.ok().body(responseUserDTO);
        } catch (org.hibernate.PropertyValueException e) {
            String error = "입력하지 않은 항목이 있군요";
            ResponseDTO response = ResponseDTO.builder().error(error).build();
            System.out.println(error);
            return ResponseEntity.badRequest().body(response);
        }
        catch (Exception e) {
            String error = e.getMessage();
            ResponseDTO response = ResponseDTO.builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@RequestBody UserDTO userDTO) {
        System.out.println("userDTO : "+userDTO);
        UserEntity user =
                userService.getByCredentials(userDTO.getEmail(),
                        userDTO.getPassword(),
                        passwordEncoder);

        System.out.println("user : "+user);
        if(user != null) {
            final String Token = tokenProvider.create(user);
            final UserDTO responseUserDTO = UserDTO.builder()
                    .email(user.getEmail())
                    .username(user.getUsername())
                    .id(user.getId())
                    .password(user.getPassword())
                    .token(Token)
                    .build();
            System.out.println("responseUserDTO : "+responseUserDTO);
            return ResponseEntity.ok().body(responseUserDTO);
        } else {
            ResponseDTO responseDTO = ResponseDTO.builder().error("로그인 실패").build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }
}
