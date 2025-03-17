package com.openclassrooms.blogdatalayer.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.openclassrooms.blogdatalayer.model.LightTutorial;
import com.openclassrooms.blogdatalayer.model.Tutorial;

@Repository
public interface TutorialRepository extends MongoRepository<Tutorial, String> {

	List<Tutorial> findByName(String name);
	
	@Query(value= "{}", fields="{_id : 1, name : 1}", sort = "{date : -1}")
	List<LightTutorial> findIdAndNameExcludeOthers();
	
}
