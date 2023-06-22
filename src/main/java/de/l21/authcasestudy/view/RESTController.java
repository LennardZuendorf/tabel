package de.l21.authcasestudy.view;

import de.l21.authcasestudy.controller.DBService;
import de.l21.authcasestudy.model.dto.RoleEntity;
import de.l21.authcasestudy.model.dto.UserEntity;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class RESTController {

    private final DBService service;
    Logger log = LoggerFactory.getLogger(RESTController.class);

    @PostMapping
    public ResponseEntity<List<Long>> uploadUsers(
            @RequestParam("users") MultipartFile usersFile,
            @RequestParam("roles") MultipartFile rolesFile) {
        try{
            List<Long> userIds = service.parseAndSave(usersFile.getInputStream(), rolesFile.getInputStream());
            return new ResponseEntity<>(userIds, HttpStatus.OK);
        } catch (Exception e){
            log.atError().log("Error while uploading files: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserEntity>> getUsers() {
        try{
            return new ResponseEntity<>(service.findAllUsers(), HttpStatus.OK);
        } catch (Exception e){
            log.atError().log("Error while getting users: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/roles")
    public ResponseEntity<List<RoleEntity>> getRoles() {
        try{
            return new ResponseEntity<>(service.findAllRoles(), HttpStatus.OK);
        } catch (Exception e){
            log.atError().log("Error while getting roles: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
