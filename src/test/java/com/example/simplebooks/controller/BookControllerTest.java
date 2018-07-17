package com.example.simplebooks.controller;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.simplebooks.model.Book;
import com.example.simplebooks.repository.BookRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class BookControllerTest {

	
	@MockBean
	private BookRepository bookRepository;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void createANewBook(){
		//when
		ResponseEntity<Book> bookResponse = restTemplate.postForEntity("/api/books/", 
				new Book("Sapiens", "humankind history"), Book.class);
		
		 // then
		assertThat(bookResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

}
