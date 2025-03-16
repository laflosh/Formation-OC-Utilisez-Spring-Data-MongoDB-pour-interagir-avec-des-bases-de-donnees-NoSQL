package com.openclassrooms.blogdatalayer;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.openclassrooms.blogdatalayer.model.Post;
import com.openclassrooms.blogdatalayer.repository.PostRepository;

@SpringBootApplication
public class BlogdatalayerApplication implements CommandLineRunner{

    private final Logger logger = LoggerFactory.getLogger(BlogdatalayerApplication.class);

    @Autowired
    private PostRepository postRepository;

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

    }

}
