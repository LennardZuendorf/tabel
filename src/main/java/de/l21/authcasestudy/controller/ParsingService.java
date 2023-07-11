package de.l21.authcasestudy.controller;

import de.l21.authcasestudy.model.dto.RoleEntity;
import de.l21.authcasestudy.model.dto.UserEntity;
import org.apache.commons.csv.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParsingService {

    static CSVFormat csvFormat = CSVFormat.DEFAULT
            .withFirstRecordAsHeader()
            .withIgnoreHeaderCase()
            .withTrim();

    public static List<RoleEntity> parseRoleCSV(InputStream roleCsvStream, Map<Long, UserEntity> userEntityMap) throws UnsupportedEncodingException {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(roleCsvStream, "UTF-8"));
             CSVParser csvParser = new org.apache.commons.csv.CSVParser(fileReader, csvFormat))
        {

            List<RoleEntity> roleList = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {

                if(userEntityMap.containsKey(Long.parseLong(csvRecord.get("user_id")))){

                    RoleEntity role = RoleEntity.builder()
                            .user(userEntityMap.get(Long.parseLong(csvRecord.get("user_id"))))
                            .role(csvRecord.get("role")).build();

                    roleList.add(role);
                }
            }

            return roleList;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<Long, UserEntity> parseUserCSV(InputStream userCsvStream) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(userCsvStream, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader, csvFormat))
        {

            Map<Long, UserEntity> users = new HashMap<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {

                if(!csvRecord.get("mail").contains("sparkasse")){

                    UserEntity user = UserEntity.builder()
                            .id(Long.parseLong(csvRecord.get("user_id")))
                            .email(csvRecord.get("mail"))
                            .build();

                    users.put(user.getId(), user);
                }
            }

            return users;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
