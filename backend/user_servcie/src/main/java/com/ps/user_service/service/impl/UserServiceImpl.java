package com.ps.user_service.service.impl;

import com.ps.user_service.customExceptions.ResourceNotFoundException;
import com.ps.user_service.model.dto.UpdatePasswordDTO;
import com.ps.user_service.model.dto.UserRequestDTO;
import com.ps.user_service.model.dto.UserResponseDTO;
import com.ps.user_service.model.dto.common.LoginDTO;
import com.ps.user_service.model.dto.common.LoginResponseDTO;
import com.ps.user_service.model.dto.common.PageResponseDTO;
import com.ps.user_service.model.dto.common.RefreshRequest;
import com.ps.user_service.model.entity.Users;
import com.ps.user_service.model.mapper.UserDTOMapper;
import com.ps.user_service.repository.UserRepo;
import com.ps.user_service.service.UserService;
import com.ps.user_service.util.CustomUserDetails;
import com.ps.user_service.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AuthenticationManager authenticationManager;
    private final UserRepo userRepo;
    private final BCryptPasswordEncoder encoder;
    private final JWTUtil jwtUtil;
    private final EmailServiceImpl emailService;
    private final UserDetailsService userDetailsService;

    public LoginResponseDTO signUp(UserRequestDTO userRequestDTO) {
        Users user = UserDTOMapper.toEntity(userRequestDTO);
        user.setPassword(encoder.encode(userRequestDTO.getPassword()));
        user.setActivationToken(UUID.randomUUID().toString());
        if(userRequestDTO.getRole() != null){
            user.setRole(userRequestDTO.getRole());
        }
        else {
            user.setRole("USER");
        }
        Users saveduser = userRepo.save(user);
        String activationLink = "http://localhost:8080/api/user/activate?token=" + saveduser.getActivationToken();
        String to = saveduser.getEmail();
        String subject = "Profile Activation Link";
        String body = """
                Hi %s,
                Welcome to MoneyManager! Please activate your account by clicking the link below:
                %s
                If you didn't create this account, you can safely ignore this email.
                """.formatted(saveduser.getFirstName(), activationLink);
        boolean mailSent = emailService.sendEmail(to, subject, body);
        if (mailSent) {
            System.out.println("Activation Mail Sent Successfully");
        } else {
            System.out.println("Activation Mail Not Sent");
        }
        return new LoginResponseDTO("SignUp Successful. An activation mail is send to registered Email", UserDTOMapper.toDTO(saveduser), null,null);
    }

    public LoginResponseDTO login(@NonNull LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.email(),loginDTO.password()));
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        if(customUserDetails == null){
            throw new RuntimeException("Some Error Occurred");
        }
        return new LoginResponseDTO("Login Successfully",UserDTOMapper.toDTO(customUserDetails.getUser()),jwtUtil.generateRefreshToken(customUserDetails.getUser()), jwtUtil.generateAccessToken(customUserDetails.getUser()));
    }

//    public UserResponseDTO findByEmail(String email) {
//        return UserDTOMapper.toDTO(userRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User","email",email)));
//    }

    public PageResponseDTO<UserResponseDTO> findAll(int page_num,int page_size) {
        Pageable pageable = PageRequest.of(page_num,page_size);
        Page<Users> usersPage = userRepo.findAll(pageable);
        List<UserResponseDTO> userList = usersPage.getContent().stream().map(UserDTOMapper::toDTO).toList();
        PageResponseDTO<UserResponseDTO> response = new PageResponseDTO<>();
        response.setContent(userList);
        response.setPageNumber(usersPage.getNumber());
        response.setPageSize(usersPage.getSize());
        response.setTotalElements(usersPage.getTotalElements());
        response.setTotalPages(usersPage.getTotalPages());
        response.setLastPage(usersPage.isLast());
        return response;
    }

    public UserResponseDTO updateUser(UserRequestDTO requestDTO) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users updatedUser = null;
        if (userDetails != null) {
            Users user = userDetails.getUser();
            if (userRepo.existsByEmailAndUserIdNot(requestDTO.getEmail(), user.getUserId())) {
                throw new RuntimeException("Email ID already registered");
            }
            if (requestDTO.getFirstName() != null && !Objects.equals(user.getFirstName(), requestDTO.getFirstName())) {
                System.out.println("Update First Name");
                user.setFirstName(requestDTO.getFirstName());
            }
            if (requestDTO.getLastName() != null && !Objects.equals(user.getLastName(), requestDTO.getLastName())) {
                System.out.println("Update Last Name");
                user.setLastName(requestDTO.getLastName());
            }
            if (requestDTO.getMobileNumber() != null && !Objects.equals(user.getMobileNumber(), requestDTO.getMobileNumber())) {
                System.out.println("Update Mobile Number");
                user.setMobileNumber(requestDTO.getMobileNumber());
            }
            if (requestDTO.getEmail() != null && !Objects.equals(user.getEmail(), requestDTO.getEmail())) {
                System.out.println("Update Email");
                user.setEmail(requestDTO.getEmail());
            }
//            if (requestDTO.getProfileImageURL() != null && !Objects.equals(user.getProfileImageURL(), requestDTO.getProfileImageURL())) {
//                System.out.println("Update Profile Pic");
//                user.setProfileImageURL(requestDTO.getProfileImageURL());
//            }
            updatedUser = userRepo.save(user);
        }
        if (updatedUser == null) {
            throw new RuntimeException("Unable to Update");
        }
        return UserDTOMapper.toDTO(updatedUser);
    }

    public boolean deleteUser(String email) {
        Users user =  userRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User","email",email));
        try{
            userRepo.delete(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean changePassword(UpdatePasswordDTO passwordDTO) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails != null) {
            Users user = userDetails.getUser();
            if (!encoder.matches(passwordDTO.oldPassword(), user.getPassword())) {
                throw new RuntimeException("Old Password do not match");
            }
            user.setPassword(encoder.encode(passwordDTO.newPassword()));
            userRepo.save(user);
            return true;
        }
        return false;
    }

    public boolean sendOTP(String email) {
        Users user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User Not Found with Email: " + email));
        String OTP = String.valueOf(100000 + new SecureRandom().nextInt(900000));
        user.setOTP(OTP);
        user.setOTP_EXPIRATION(LocalDateTime.now().plusMinutes(15));
        user.setOTPVerified(false);
        userRepo.save(user);
        String subject = "Forget Password";
        String body = "Your OTP to reset your password is <b>" + OTP + "</b>. It is valid for 15 minutes.<br><br>If you didn't request this, please ignore this email.";
        return emailService.sendEmail(email, subject, body);
    }

    public boolean verifyOTP(String email, String OTP) {
        Users user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getOTP() == null) {
            throw new RuntimeException("Something Went Wrong!");
        }

        if (!user.getOTP().equals(OTP))
            throw new RuntimeException("Invalid OTP");

        if (user.getOTP_EXPIRATION().isBefore(LocalDateTime.now()))
            throw new RuntimeException("OTP expired");

        user.setOTPVerified(true);

        userRepo.save(user);
        return true;
    }

    public boolean ForgetPassword(String email, UpdatePasswordDTO requestDTO) {
        Users user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.isOTPVerified())
            throw new RuntimeException("OTP verification required");

        user.setPassword(encoder.encode(requestDTO.newPassword()));

        user.setOTP(null);
        user.setOTP_EXPIRATION(null);
        user.setOTPVerified(false);

        userRepo.save(user);
        return true;
    }

    public boolean activateUser(String activationToken) {
        Users user = userRepo.findByActivationToken(activationToken).orElseThrow(() -> new RuntimeException("User Not Found"));
        user.setActive(true);
        userRepo.save(user);
        return true;
    }

    public LoginResponseDTO refreshToken(RefreshRequest request) {
        String refreshToken = request.refreshToken();
        String username = jwtUtil.extractUsername(refreshToken);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (!jwtUtil.isValid(refreshToken, userDetails)) {
            throw new RuntimeException("Invalid Refresh Token");
        }
        Users user = ((CustomUserDetails) userDetails).getUser();
        return new LoginResponseDTO(
                "Token Refreshed Successfully",
                UserDTOMapper.toDTO(user),
                refreshToken,
                jwtUtil.generateAccessToken(user)
        );
    }

    @Override
    public UserResponseDTO getLoggedUser() {
        CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return UserDTOMapper.toDTO(user.getUser());
    }
}
