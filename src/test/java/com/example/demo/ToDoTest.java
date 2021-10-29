package com.example.demo;


import com.example.demo.model.ToDo;
import com.example.demo.repository.ToDoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ToDoTest {
    private final ToDo item1 = new ToDo();
    private final ToDo item2 = new ToDo();
    @Autowired
    MockMvc mvc;

    @Autowired
    ToDoRepository repository;

    @BeforeEach
    void setUp(){
        this.item1.setContent("Take out Trash");
        this.item2.setContent("Learn Spring");
        this.item1.setCompleted(false);
        this.item2.setCompleted(true);
        this.repository.save(item1);
        this.repository.save(item2);

    }

    @Transactional
    @Rollback
    @Test
    void createToDoItem() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/api/items")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"content\" : \"Take out Trash\", \"completed\": \"false\"}");
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", is("Take out Trash")));
    }

    @Transactional
    @Rollback
    @Test
    void getAllToDoItems() throws Exception{
        RequestBuilder request = MockMvcRequestBuilders.get("/api/items")
                .contentType(MediaType.APPLICATION_JSON);
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content", is("Take out Trash")));
    }

    @Transactional
    @Rollback
    @Test
    void updateToDoItemContent() throws Exception{
        RequestBuilder request = MockMvcRequestBuilders.patch("/api/items/" +this.item1.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"content\": \"Pick up dog Poop\"}");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", is("Pick up dog Poop")));
    }

    @Transactional
    @Rollback
    @Test
    void deleteToDoItem() throws Exception{
        RequestBuilder request = MockMvcRequestBuilders.delete("/api/items/" + this.item1.getId())
                .contentType(MediaType.APPLICATION_JSON);
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").doesNotExist());
    }
}
