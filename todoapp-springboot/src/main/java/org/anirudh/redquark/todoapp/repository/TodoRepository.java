/**
 * 
 */
package org.anirudh.redquark.todoapp.repository;

import org.anirudh.redquark.todoapp.model.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author anirshar
 * 
 * We’ve extended TodoRepository with MongoRepository interface provided by spring-data-mongodb. 
 * The MongoRepository interface defines methods for all the CRUD operations on the Document like 
 * findAll(), findOne(), save(), delete() etc.
	Spring Boot automatically plugs in an implementation of MongoRepository interface called SimpleMongoRepository 
	at runtime. So, All of the CRUD methods defined by MongoRepository are readily available without doing anything.
 *
 */
public interface TodoRepository extends MongoRepository<Todo, String> {

}
