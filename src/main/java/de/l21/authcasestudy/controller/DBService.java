package de.l21.authcasestudy.controller;

import de.l21.authcasestudy.model.dao.RoleRepository;
import de.l21.authcasestudy.model.dao.UserRepository;
import de.l21.authcasestudy.model.dto.RoleEntity;
import de.l21.authcasestudy.model.dto.UserEntity;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class DBService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    public List<Long> parseAndSave(InputStream userCsvStream, InputStream roleCsvStream) throws UnsupportedEncodingException {
        try {
            userRepository.deleteAll();

            Map<Long, UserEntity> users = ParsingService.parseUserCSV(userCsvStream);
            userRepository.saveAll(users.values());

            List<RoleEntity> roles=roleRepository.saveAll(ParsingService.parseRoleCSV(roleCsvStream, users));
            System.out.println("Saved "+users.size()+" users and their "+roles.size()+" roles");

            return List.copyOf(users.keySet());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<UserEntity> findAllUsers(){
        return userRepository.findAll();
    }

    public List<RoleEntity> findAllRoles(){
        return roleRepository.findAll();
    }
}
