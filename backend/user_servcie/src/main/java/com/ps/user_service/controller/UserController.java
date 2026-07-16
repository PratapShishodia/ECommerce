package com.ps.user_service.controller;

import com.ps.user_service.constants.UserConstants;
import com.ps.user_service.model.dto.UpdatePasswordDTO;
import com.ps.user_service.model.dto.UserRequestDTO;
import com.ps.user_service.model.dto.UserResponseDTO;
import com.ps.user_service.model.dto.common.*;
import com.ps.user_service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<LoginResponseDTO> signup(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        LoginResponseDTO responseDTO = userService.signUp(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(userService.login(loginDTO));
    }

    @GetMapping("/getAll")
    public ResponseEntity<PageResponseDTO> getAll(@RequestParam(defaultValue = "0") int page_num, @RequestParam(defaultValue = "10") int page_size) {
        return ResponseEntity.ok(userService.findAll(page_num,page_size));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> update(@RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO responseDTO = userService.updateUser(userRequestDTO);
        if(responseDTO == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(UserConstants.STATUS_500,UserConstants.MESSAGE_500,null));
        }
        else {
            return ResponseEntity.ok(new ResponseDTO(UserConstants.STATUS_200,UserConstants.MESSAGE_200,responseDTO));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> delete(@RequestParam String email) {
        if(userService.deleteUser(email)){
            return ResponseEntity.ok(new ResponseDTO(UserConstants.STATUS_200,UserConstants.MESSAGE_200,null));
        }
        else{
            return ResponseEntity.ok(new ResponseDTO(UserConstants.STATUS_500,UserConstants.MESSAGE_500,null));
        }
    }

    @PatchMapping("/updatePassword")
    public ResponseEntity<ResponseDTO> updatePassword(@Valid @RequestBody UpdatePasswordDTO updatePasswordDTO) {
        if(userService.changePassword(updatePasswordDTO)){
            return ResponseEntity.ok(new ResponseDTO(UserConstants.STATUS_200,UserConstants.MESSAGE_200,null));
        }
        else{
            return ResponseEntity.ok(new ResponseDTO(UserConstants.STATUS_500,UserConstants.MESSAGE_500,null));
        }
    }

    @PostMapping("/sendOTP")
    public ResponseEntity<ResponseDTO> sendOTP(@RequestParam String email) {
        if(userService.sendOTP(email)){
            return ResponseEntity.ok(new ResponseDTO(UserConstants.STATUS_200,UserConstants.MESSAGE_200,null));
        }
        else{
            return ResponseEntity.ok(new ResponseDTO(UserConstants.STATUS_500,UserConstants.MESSAGE_500,null));
        }
    }

    @PostMapping("/verifyOTP")
    public ResponseEntity<ResponseDTO> verifyOTP(@RequestParam String email,@RequestParam String OTP) {
        if(userService.verifyOTP(email,OTP)){
            return ResponseEntity.ok(new ResponseDTO(UserConstants.STATUS_200,UserConstants.MESSAGE_200,null));
        }
        else {
            return ResponseEntity.ok(new ResponseDTO(UserConstants.STATUS_500,UserConstants.MESSAGE_500,null));
        }
    }

    @PostMapping("/forgetPassword")
    public ResponseEntity<ResponseDTO> forgetPassword(@RequestParam String email,@Valid @RequestBody UpdatePasswordDTO updatePasswordDTO) {
        if(userService.ForgetPassword(email,updatePasswordDTO)){
            return ResponseEntity.ok(new ResponseDTO(UserConstants.STATUS_200,UserConstants.MESSAGE_200,null));
        }
        else{
            return ResponseEntity.ok(new ResponseDTO(UserConstants.STATUS_500,UserConstants.MESSAGE_500,null));
        }
    }

    @GetMapping("/activate")
    public ResponseEntity<ResponseDTO> activateUser(@RequestParam String token) {
        if(userService.activateUser(token)){
            return ResponseEntity.ok(new ResponseDTO(UserConstants.STATUS_200,UserConstants.MESSAGE_200,null));
        }
        else {
            return ResponseEntity.ok(new ResponseDTO(UserConstants.STATUS_500,UserConstants.MESSAGE_500,null));
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDTO> refreshToken(@RequestBody RefreshRequest refreshRequest) {
        return ResponseEntity.ok(userService.refreshToken(refreshRequest));
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> me() {
        return ResponseEntity.ok(userService.getLoggedUser());
    }
}
