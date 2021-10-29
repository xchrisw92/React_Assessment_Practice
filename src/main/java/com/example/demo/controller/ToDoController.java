package com.example.demo.controller;

import com.example.demo.model.ToDo;
import com.example.demo.repository.ToDoRepository;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class ToDoController {

    private final ToDoRepository repository;

    public ToDoController(ToDoRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/api/items")
    public ToDo createNewToDoItem(@RequestBody ToDo newItem){
        this.repository.save(newItem);
        return this.repository.findById(newItem.getId()).get();
    }

    @GetMapping("/api/items")
    public Iterable<ToDo> getAllToDoItems(){
        return this.repository.findAll();
    }

    @PatchMapping("/api/items/{id}")
    public ToDo updateToDoItem(@PathVariable Long id, @RequestBody ToDo updateItem){
        ToDo originalItem = this.repository.findById(id).get();

        if(originalItem != null){
            if(updateItem.getContent() != null){
                originalItem.setContent(updateItem.getContent());
            }
            if(updateItem.isCompleted() != originalItem.isCompleted()){
                originalItem.setCompleted(updateItem.isCompleted());
            }
        }
        this.repository.save(originalItem);
        return this.repository.findById(id).get();
    }

    @DeleteMapping("/api/items/{id}")
    public void deleteToDoItem(@PathVariable Long id){
        this.repository.deleteById(id);
    }
}
