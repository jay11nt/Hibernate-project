package in.sb.main.DaoRepo;

import org.springframework.data.repository.CrudRepository;

import in.sb.main.entity.Book;

public interface BookRepository extends CrudRepository<Book, Integer>
{
	public Book findById(int id);
}
