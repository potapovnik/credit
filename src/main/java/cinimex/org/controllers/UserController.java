package cinimex.org.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/current")
    public String user() {
        return  ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();


    }
}
