package com.todobackend.demo.controller

import com.todobackend.demo.dto.ShoppingList
import com.todobackend.demo.dto.Todo
import com.todobackend.demo.repository.ShoppingRepository
import com.todobackend.demo.repository.TodoRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:8008"])
@RestController
@RequestMapping("/api")
class CrudController(private val todoRepository: TodoRepository,
                     private val shoppingRepository: ShoppingRepository) {

    @CrossOrigin
    @GetMapping("/todos")
    fun getAllTodos(): MutableIterable<Todo> {
        return todoRepository.findAll()
    }

    @PostMapping("/todos")
    fun createNewTodo(@Validated @RequestBody todo: Todo):Todo{
        return todoRepository.save(todo)
    }

    @PutMapping("/todos/{id}")
    fun updateTodo(@PathVariable(value = "id") todoId: Long, @Validated @RequestBody newTodo: Todo): ResponseEntity<Todo>{

        return todoRepository.findById(todoId).map { existingTodo ->
                val updatedTodo:Todo = existingTodo
                        .copy(description = newTodo.description, done = newTodo.done)
                ResponseEntity.ok().body(todoRepository.save(updatedTodo))
        }.orElse(ResponseEntity.notFound().build())
    }

    @CrossOrigin
    @DeleteMapping("/todos/{id}")
    fun deleteTodo(@PathVariable(value = "id") todoId: Long): ResponseEntity<Void> {
        return todoRepository.findById(todoId) .map { todo ->
            todoRepository.delete(todo)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }

    @CrossOrigin
    @GetMapping("/shoppingList")
    fun getAllShoppingListItems(): MutableIterable<ShoppingList> {
        return shoppingRepository.findAll()
    }
}