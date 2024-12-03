package ru.diploma_work.demo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;
import ru.diploma_work.demo.dto.RegisterDTO;
import ru.diploma_work.demo.dto.UpdateUserDTO;
import ru.diploma_work.demo.dto.mapper.UserMapper;
import ru.diploma_work.demo.exception.EntityModelNotFoundException;
import ru.diploma_work.demo.model.UserModel;
import ru.diploma_work.demo.repository.UserModelRepository;
import ru.diploma_work.demo.service.UserService;
import ru.diploma_work.demo.utils.ValidationUtils;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Value("${avatars.dir.path:./data}")
    private String avatarsDir;
    @Value("${avatars.download.url}")
    private String avatarDownloadURL;
    private final UserModelRepository userModelRepository;
    private final UserMapper userMapper;
    private final ValidationUtils validationUtils;

    public UserServiceImpl(UserModelRepository userModelRepository, UserMapper userMapper, ValidationUtils validationUtils) {
        this.userModelRepository = userModelRepository;
        this.userMapper = userMapper;
        this.validationUtils = validationUtils;
    }

    public void createUserWithRegistrationInfo(UserDetails userDetails, RegisterDTO register) {
        UserModel creatingUser = new UserModel();
        creatingUser.setEmail(userDetails.getUsername());
        creatingUser.setPassword(userDetails.getPassword());
        creatingUser.setFirstName(register.getFirstName());
        creatingUser.setLastName(register.getLastName());
        creatingUser.setRole(register.getRole());
        creatingUser.setPhone(register.getPhone());
        userModelRepository.save(creatingUser);
    }

    @Override
    public UserModel findUserByUserName(String username) throws EntityModelNotFoundException {
        return userModelRepository.findOneByEmailIgnoreCase(username)
                .orElseThrow(() -> new EntityModelNotFoundException("User = " + username + " not found"));

    }

    @Override
    public UserModel updateUser(String username, UpdateUserDTO update) throws EntityModelNotFoundException {
        UserModel existingUser = findUserByUserName(username);
        userMapper.mapUpdateUserDTOToUserModel(update, existingUser);
        userModelRepository.save(existingUser);
        log.info("Additional info for user " + username + " is updated with following parameters: {}", update);
        return existingUser;
    }

    @Override
    public void updateUserAvatar(String username, MultipartFile file) throws IOException {

        UserModel existingUser = findUserByUserName(username);

        if (file.getOriginalFilename() != null) {
            Path filePath = Path.of(avatarsDir, "avatar" + existingUser.getId() + "." + validationUtils.getFileExtension(file));
            Files.deleteIfExists(filePath);
            try {
                Files.createDirectory(filePath.getParent());
            } catch (FileAlreadyExistsException exception) {
                log.info("Directory already exists");
            }

            try (InputStream is = file.getInputStream();
                 OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                 BufferedInputStream bis = new BufferedInputStream(is, 1024);
                 BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
            ) {
                bis.transferTo(bos);
                log.info("User avatar file saved in system directory " + avatarsDir + ". File name is 'avatar"
                        + existingUser.getId() + ".jpg'");
            }
        }
        existingUser.setImage(avatarDownloadURL + existingUser.getId());
        userModelRepository.save(existingUser);
    }

}

