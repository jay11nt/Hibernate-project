package in.sb.main.controller; //server side controller class

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.sb.main.entity.Book;
import in.sb.main.services.BookService;

@RestController
public class BookController {
	// @RequestMapping(value = "/books", method= RequestMethod.GET)

	@Autowired
	private BookService bookService;

	// get all books
	@GetMapping("/books") // for http GET method
	public ResponseEntity<List<Book>> getBooks() 
	{
		List<Book>list =bookService.getallBooks();
		if(list.size()<=0)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(list);
	}

	
			// get single book
			@GetMapping("/books/{id}")
			public ResponseEntity<Book> getBook(@PathVariable("id") int id) // for calling 1 book
			{
				Book book=bookService.getBookById(id);
					if(book==null)	
					{
						return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
						}
				return ResponseEntity.of(Optional.of(book));
			}

			// new book handler
			@PostMapping("/books") // for create object
			public ResponseEntity<Book> addBook(@RequestBody Book book) 
			{
				Book b=null;
				try
				{
					 b = this.bookService.addBook(book);
					System.out.println(book);
					return ResponseEntity.of(Optional.of(b));
				}
				catch(Exception e)
				{
					e.printStackTrace();
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
				}
				
			}

			// delete single book handler
			@DeleteMapping("/books/{bookId}")
			public ResponseEntity<Void> deleteBook(@PathVariable("bookId") int bookId) {
				try 
				{
					this.bookService.deleteBook(bookId);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				}
				catch(Exception e)
				{
					e.printStackTrace();
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
				}
			}

			// Update the single book(PUT)
			@PutMapping("/books/{bookId}")
			public Book updateBook(@RequestBody Book book, @PathVariable("bookId") int bookId) {
				this.bookService.updateBook(book, bookId);
				return book;
			}

}