package ru.diploma_work.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.diploma_work.demo.contriller.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class DiplomaWorkApplicationTests {

	@Autowired
	AdController adController;
	@Autowired
	AuthController authController;
	@Autowired
	CommentController commentController;
	@Autowired
	FileDownloadController fileDownloadController;
	@Autowired
	UserController userController;

	@Test
	void contextLoads() {
		assertNotNull(adController);
		assertNotNull(commentController);
		assertNotNull(fileDownloadController);
		assertNotNull(authController);
		assertNotNull(userController);
	}

}
