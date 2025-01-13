package in.sb.main.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.sb.main.DaoRepo.BookRepository;
import in.sb.main.entity.Book;
@Component
public class BookService 
{
	@Autowired
	private BookRepository bookRepository;
//	private static List<Book> list=new ArrayList<>();
//	
//	static 
//	{
//		list.add(new Book(12,"book 1","publisher 1"));
//		list.add(new Book(13,"book 2","publisher 2"));
//		list.add(new Book(14,"book 3","publisher 3"));
//	}
	
	//get all books
	public List<Book> getallBooks()
	{
		 List<Book>list=(List<Book>) this.bookRepository.findAll();
		 return list;
	}
	
	//get single book
	public Book getBookById(int id)
	{
		Book book=null;
		try
		{
			//book=list.stream().filter(e-> e.getId()==id).findFirst().get();			//matching the list through id for print
			book=this.bookRepository.findById(id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return book;
	}
	
	//create new book object (Adding the Book) 
	public Book addBook (Book b)
	{
		Book result=bookRepository.save(b);
		return result;
	}
	
	//delete book
	public void deleteBook(int bid)
	{
		//.stream().filter(book->book.getId()!=bid).collect(Collectors.toList());	//using lamba function
		bookRepository.deleteById(bid);
		
	}
	
	//Update book
	public void updateBook(Book book,int bookId)
	{
//		stream().map(b->
//		{
//			if(b.getId()==bookId) 
//			{
//				b.setTitle(book.getTitle());
//				b.setAuthor(book.getAuthor());
//			}
//			return b;
//		}).collect(Collectors.toList());
		
		book.setId(bookId);
		bookRepository.save(book);
	}
	
	
}
