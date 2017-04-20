package com.crazy.controller;

import com.crazy.config.JwtAuthenticationRequest;
import com.crazy.config.JwtAuthenticationResponse;
import com.crazy.entity.Account;
import com.crazy.repository.RoleRepository;
import com.crazy.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jieping on 2017-04-17.
 */
@RequestMapping(value = "/auth")
@RestController
public class AuthController {
    @Value("Authorization")
    private String tokenHeader;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest) throws Exception{
        final String token = authService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        // Return the token
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(
            HttpServletRequest request) throws Exception{
        String token = request.getHeader(tokenHeader);
        String refreshedToken = authService.refresh(token);
        if(refreshedToken == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Account register(@RequestBody Account addedUser) throws  Exception{
         return  authService.register(addedUser);
     //   return new ResJsonTemplate("200","successful");
    }
    @PreAuthorize("hasRole('user')")
    @RequestMapping(value = "/testuser", method = RequestMethod.GET)
    public String user(@RequestParam(value = "username") String username) {
        return "user";
    }

    @PreAuthorize("hasRole('admin')")
    @RequestMapping(value = "/testadmin", method = RequestMethod.GET)
    public String admin(@RequestParam(value = "username") String username) {
        return "admin";
    }

    @RequestMapping(value = "/role", method = RequestMethod.GET)
    public String getRole(@RequestParam(value = "username") Long roleId) {
        return roleRepository.findByRoleId(roleId).getRole_name();
    }

}