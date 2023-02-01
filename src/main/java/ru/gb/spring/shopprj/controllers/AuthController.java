package ru.gb.spring.shopprj.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.spring.shopprj.dto.JwtRequest;
import ru.gb.spring.shopprj.dto.JwtResponse;
import ru.gb.spring.shopprj.services.UserService;
import ru.gb.spring.shopprj.utils.JwtTokenUtil;

@RequiredArgsConstructor
@RestController
public class AuthController {
     private final UserService userService;
     private final JwtTokenUtil jwtTokenUtil;
     private final AuthenticationManager authenticationManager;

     @PostMapping("/auth")
     public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest){
         try {
             authenticationManager.authenticate(
                     new UsernamePasswordAuthenticationToken(
                             authRequest.getUsername(), authRequest.getPassword()
                     )
             );
         } catch (BadCredentialsException e){
             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//             return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Incorrect username or Password"), HttpStatus.UNAUTHORIZED);
         }
         UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
         String token = jwtTokenUtil.generateToken(userDetails);
         return ResponseEntity.ok(new JwtResponse(token));
     }
}
