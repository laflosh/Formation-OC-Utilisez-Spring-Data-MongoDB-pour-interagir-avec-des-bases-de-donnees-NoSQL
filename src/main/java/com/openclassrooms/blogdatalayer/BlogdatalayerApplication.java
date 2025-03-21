package com.openclassrooms.blogdatalayer;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.openclassrooms.blogdatalayer.model.LightPost;
import com.openclassrooms.blogdatalayer.model.Post;
import com.openclassrooms.blogdatalayer.model.Tag;
import com.openclassrooms.blogdatalayer.model.Tutorial;
import com.openclassrooms.blogdatalayer.repository.PostRepository;
import com.openclassrooms.blogdatalayer.repository.TutorialRepository;

@SpringBootApplication
public class BlogdatalayerApplication implements CommandLineRunner{

    private final Logger logger = LoggerFactory.getLogger(BlogdatalayerApplication.class);

    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private TutorialRepository tutorialRespository;

    public static void main(String[] args) {
    SpringApplication.run(BlogdatalayerApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {

	  Optional<Post> p = postRepository.findById("6177a31824f1d205e0b0692d");
	  
	      if (p.isPresent()) {
	              logger.info(p.get().getContent());
	      } else {
	              logger.info("Post not found");
	      }
	  
	  Optional<Tutorial> t = tutorialRespository.findById("6192c22d783f4a2a0a7d9bf3");
	  
      if (t.isPresent()) {
              logger.info(t.get().getContent());
      } else {
              logger.info("Post not found");
      }
      
      List<Post> allPosts = postRepository.findAll();
      allPosts.stream().forEach((post) -> logger.info(post.getName()));

      List<Tutorial> allTutorials = tutorialRespository.findAll();
      allTutorials.stream().forEach((tutorial) -> logger.info(tutorial.getName()));
      
      List<Post> result = postRepository.findByName("Welcome!");
      result.stream().forEach(post -> logger.info(post.getName()));
      
      List<Tutorial> resultTutorial = tutorialRespository.findByName("How to use MongoRepository");
      resultTutorial.stream().forEach(tutorial -> logger.info(tutorial.getName()));
      
      List<LightPost> lightPosts = postRepository.findByOrderByDateDesc();
      lightPosts.stream().forEach(post -> logger.info(post.getName()));
      
      List<String> names = postRepository.findAllName();
      names.stream().forEach(name -> logger.info(name));
      
      Tag springTag = new Tag();
      springTag.setName("Spring Framework");
      springTag.setSlug("spring-framework");
      springTag.setDescription("Spring is the most popular Java Framework in the world.");
      
      Post newPost = new Post();
      newPost.setName("My new blog");
      newPost.setDate(new Date());
      newPost.setContent("A new blog post, Amazing");
      newPost.setTag(springTag);
      
      Post newPost1 = new Post();
      newPost1.setName("A new post");
      newPost1.setDate(new Date());
      newPost1.setContent("A new blog post, Amazing");
      
      Post newPost2 = new Post();
      newPost2.setName("new post again");
      newPost2.setDate(new Date());
      newPost2.setContent("A new blog post, Amazing");
      
      postRepository.insert(List.of(newPost, newPost1, newPost2));
      
      List<Post> updatedPost = postRepository.findByName(newPost.getName());
      Post existingPost = updatedPost.get(0);
      existingPost.setName("Name updated");
      postRepository.save(existingPost);
      
      postRepository.delete(existingPost);
      
    }

}
