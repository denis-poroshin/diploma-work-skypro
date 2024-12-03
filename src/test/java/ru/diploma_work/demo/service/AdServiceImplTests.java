package ru.diploma_work.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import ru.diploma_work.demo.dto.CreateOrUpdateAdDTO;
import ru.diploma_work.demo.dto.Role;
import ru.diploma_work.demo.dto.mapper.AdMapper;
import ru.diploma_work.demo.exception.EntityModelNotFoundException;
import ru.diploma_work.demo.model.AdModel;
import ru.diploma_work.demo.model.UserModel;
import ru.diploma_work.demo.repository.AdModelRepository;
import ru.diploma_work.demo.service.impl.AdServiceImpl;
import ru.diploma_work.demo.utils.ValidationUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = AdServiceImpl.class)
@ExtendWith(MockitoExtension.class)
class AdServiceImplTests {

    @MockBean
    AdModelRepository adModelRepository;
    @SpyBean
    AdMapper adMapper;
    @MockBean
    UserService userService;
    @MockBean
    ValidationUtils validationUtils;

    @Autowired
    AdService adService;

    UserModel testUserModel;
    AdModel testAdModel;

    @Value("${images.dir.path}")
    private String testImageDir;
    @Value("${images.download.url}")
    private String testImageDownloadURL;

    @BeforeEach
    void init() {
        testUserModel = new UserModel("test@test.com", "testPassword", "testFirstName",
                "testLastName", "+7(000)000-00-00", Role.ADMIN);
        testUserModel.setId(1);

        testAdModel = new AdModel(10000, "testTitle", "testDescription");
        testAdModel.setId(1);
    }

    @Test
    void createAd() {
        CreateOrUpdateAdDTO testAdProperties = new CreateOrUpdateAdDTO();
        testAdProperties.setDescription("testDescription");
        testAdProperties.setTitle("testTitle");
        testAdProperties.setPrice(10000);
        when(userService.findUserByUserName("test@test.com")).thenReturn(testUserModel);
        when(adModelRepository.save(eq(testAdModel))).thenReturn(testAdModel);

        AdModel actualCreatedAd = adService.createAd("test@test.com", testAdProperties);

        verify(userService, times(1)).findUserByUserName("test@test.com");
        verify(adModelRepository, times(1)).save(any(AdModel.class));
        assertNotNull(actualCreatedAd);
        assertEquals(testAdModel, actualCreatedAd);
    }

    @Test
    void setImageToAd() throws IOException {
        byte[] testFileContent = Files.readAllBytes(Path.of(testImageDir, "TestImage.jpg"));
        MultipartFile testImage = new MockMultipartFile("TestImage.jpg", testFileContent);
        when(validationUtils.getFileExtension(testImage)).thenReturn("jpg");

        adService.setImageToAd(testAdModel, testImage);

        assertEquals(testImageDownloadURL + "1", testAdModel.getImage());
        assertTrue(Files.exists(Path.of(testImageDir, "image1.jpg")));
        verify(adModelRepository, times(1)).save(any(AdModel.class));
    }

    @Test
    void findAdByIdPositive() {
        when(adModelRepository.findById(1)).thenReturn(Optional.of(testAdModel));

        AdModel actualAdModel = adService.findAdById(1);

        assertEquals(testAdModel, actualAdModel);
    }

    @Test
    void findAdByIdNegative() {
        assertThrows(EntityModelNotFoundException.class, () ->
                adService.findAdById(0));
    }

    @Test
    void updateAd() {
        CreateOrUpdateAdDTO testAdProperties = new CreateOrUpdateAdDTO();
        testAdProperties.setDescription("updatedDescription");
        testAdProperties.setTitle("updatedTitle");
        testAdProperties.setPrice(10);
        when(adModelRepository.findById(1)).thenReturn(Optional.of(testAdModel));

        AdModel updatedAd = adService.updateAd(1, testAdProperties);

        assertEquals("updatedDescription", updatedAd.getDescription());
        assertEquals("updatedTitle", updatedAd.getTitle());
        assertEquals(10, updatedAd.getPrice());
        verify(adModelRepository, times(1)).save(eq(updatedAd));
    }

    @Test
    void deleteAd() {
        when(adModelRepository.findById(1)).thenReturn(Optional.of(testAdModel));

        adService.deleteAd(1);

        verify(adModelRepository, times(1)).delete(testAdModel);
    }
}

