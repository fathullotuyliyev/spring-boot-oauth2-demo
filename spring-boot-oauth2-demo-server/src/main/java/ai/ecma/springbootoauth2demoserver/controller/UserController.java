package ai.ecma.springbootoauth2demoserver.controller;

import ai.ecma.springbootoauth2demoserver.entity.User;
import ai.ecma.springbootoauth2demoserver.exception.ResourceNotFoundException;
import ai.ecma.springbootoauth2demoserver.repository.UserRepository;
import ai.ecma.springbootoauth2demoserver.security.CurrentUser;
import ai.ecma.springbootoauth2demoserver.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @CrossOrigin
    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}
