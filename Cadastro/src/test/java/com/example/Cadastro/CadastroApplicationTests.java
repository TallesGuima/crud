package com.example.Cadastro;

import com.example.Cadastro.controllers.UserControllerTest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(value = "test")
class CadastroApplicationTests {

	@Mock
	UserControllerTest userControllerTest;

	@Test
	void contextLoads() {
	}


	@Test
	void allTests(){
		userControllerTest.testFindAllUsersReturnsExpectedList();
		userControllerTest.testFindUserByIdExpectedObjectUser();
		userControllerTest.testFindUsersByNameExpectedObjectName();
	}
}
