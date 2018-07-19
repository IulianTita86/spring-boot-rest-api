package com.example.simplebooks.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

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

	
	@MockBean(name = "bookRepository")
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
	
	@Test
	public void findBookById() {
		
		//Optional<Book> optBook = bookRepository.findById((long) 2);
		// given
		given(bookRepository.findById((long) 2)).willReturn(Optional.of(new Book("Shogun", "feudal japan")));
		
		//when 
		ResponseEntity<Book> bookResponse = restTemplate.getForEntity("/api/books/1", Book.class);
		
		//then
		assertThat(bookResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(bookResponse.getBody()).isEqualTo(new Book("Shogun", "feudal japan"));
	}
	
	 @Test
	  public void getAllBooks(){
	    when(bookRepository.findAll()).thenReturn(Collections.emptyList());
	    
		//when 
		ResponseEntity<Book[]> bookResponse = restTemplate.getForEntity("/api/books/", Book[].class);
		
		//then
		assertThat(bookResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(bookResponse.getBody()).isNotEmpty();
	  }

}
