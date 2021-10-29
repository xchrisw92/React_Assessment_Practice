package com.example.demo;

import com.example.demo.repository.ToDoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class TodoDemoApplicationTests {

	@Autowired
	MockMvc mvc;

	@Autowired
	ToDoRepository repository;

	@Test
	void contextLoads() {
	}

}
