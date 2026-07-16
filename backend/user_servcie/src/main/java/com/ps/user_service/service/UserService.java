package com.ps.user_service.service;

import com.ps.user_service.model.dto.UpdatePasswordDTO;
import com.ps.user_service.model.dto.UserRequestDTO;
import com.ps.user_service.model.dto.UserResponseDTO;
import com.ps.user_service.model.dto.common.LoginDTO;
import com.ps.user_service.model.dto.common.LoginResponseDTO;
import com.ps.user_service.model.dto.common.PageResponseDTO;
import com.ps.user_service.model.dto.common.RefreshRequest;

public interface UserService {
    LoginResponseDTO signUp(UserRequestDTO userRequestDTO);
    LoginResponseDTO login(LoginDTO loginDTO);
//    UserResponseDTO findByEmail(String email);
    PageResponseDTO<UserResponseDTO> findAll(int page_num,int page_size);
    UserResponseDTO updateUser(UserRequestDTO userRequestDTO);
    boolean deleteUser(String email);
    boolean changePassword(UpdatePasswordDTO passwordDTO);
    boolean sendOTP(String email);
    boolean verifyOTP(String email, String OTP);
    boolean ForgetPassword(String email, UpdatePasswordDTO requestDTO);
    boolean activateUser(String activationToken);
    LoginResponseDTO refreshToken(RefreshRequest request);
    UserResponseDTO getLoggedUser();
}
