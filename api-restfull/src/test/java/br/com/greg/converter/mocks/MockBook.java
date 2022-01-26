package br.com.greg.converter.mocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.greg.data.vo.BookVO;
import br.com.greg.model.Book;

public class MockBook {
	
	public Book mockEntity() {
		return mockEntity(0);
	}
	
	public BookVO mockVO() {
		return mockVO(0);
	}
	
	public List<Book> mockEntityList() {
		List<Book> books = new ArrayList<Book>();
		for (int i = 0; i < 14; i++) {
			books.add(mockEntity(i));			
		}
		return books;
	}
	
	public List<BookVO> mockVOList() {
        List<BookVO> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
        	books.add(mockVO(i));
        }
        return books;
    }
	
	private Book mockEntity(Integer number) {
		Book book = new Book();
		book.setAuthor("Author Test" + number);
		book.setLaunchDate(new Date());
		book.setPrice(number.doubleValue());
		book.setId(number.longValue());
		book.setTitle("Title Test" + number);
        return book;
    }
	
	private BookVO mockVO(Integer number) {
		BookVO book = new BookVO();
		book.setAuthor("Author Test" + number);
		book.setLaunchDate(new Date());
		book.setPrice(number.doubleValue());
		book.setId(number.longValue());
		book.setTitle("Title Test" + number);
        return book;
    }
}
