package org.anirudh.redquark.todoapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.anirudh.redquark.todoapp.model.Todo;
import org.anirudh.redquark.todoapp.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author anirshar
 *
 *The @CrossOrigin annotation in the above controller is used to enable Cross Origin requests. 
 *This is required because we’ll be accessing the apis from angular’s front-end server.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class TodoController {

	@Autowired
	private TodoRepository repository;
	
	/**
	 * @return List<Todo>
	 */
	@GetMapping("/todos")
	public List<Todo> getAllTodos() {
		Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
		return repository.findAll(sortByCreatedAtDesc);
	}

	/**
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/todos/{id}")
	public ResponseEntity<Todo> getTodoById(@PathVariable String id) {
		Todo todo = repository.findOne(id);

		if (todo == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Todo>(todo, HttpStatus.OK);
		}
	}

	/**
	 * @param todo
	 * @return
	 */
	@PostMapping("/todos")
	public Todo createTodo(@Valid @RequestBody Todo todo) {
		todo.setCompleted(false);
		return repository.save(todo);
	}

	/**
	 * @param id
	 * @param todo
	 * @return
	 */
	@PutMapping(value = "/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable("id") String id, @Valid @RequestBody Todo todo) {
		Todo todoData = repository.findOne(id);
		if (todoData == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		todoData.setTitle(todo.getTitle());
		todoData.setCompleted(todo.isCompleted());
		Todo updatedTodo = repository.save(todoData);
		return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
	}

	/**
	 * @param id
	 */
	@DeleteMapping(value = "/todos/{id}")
	public void deleteTodo(@PathVariable("id") String id) {
		repository.delete(id);
	}
}
