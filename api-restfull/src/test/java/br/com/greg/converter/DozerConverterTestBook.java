package br.com.greg.converter;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.greg.converter.mocks.MockBook;
import br.com.greg.data.vo.BookVO;
import br.com.greg.data.vo.PersonVO;
import br.com.greg.model.Book;

public class DozerConverterTestBook {
	
	MockBook inputObject;
	
	@Before
	public void setUp() {
		inputObject = new MockBook();
	}
	
	@Test
    public void parseEntityToVOTest() {
        BookVO output = DozerConverter.parserObject(inputObject.mockEntity(), BookVO.class);
        Assert.assertEquals(Long.valueOf(0L), output.getId());
        Assert.assertEquals("Author Test0", output.getAuthor());
        Assert.assertEquals(new Date(), output.getLaunchDate());
        Assert.assertEquals("Title Test0", output.getTitle());
        Assert.assertEquals("20", output.getPrice());
    }
	
	@Test
    public void parseVOToEntityTest() {
        Book output = DozerConverter.parserObject(inputObject.mockVO(), Book.class);
        Assert.assertEquals(Long.valueOf(0L), output.getId());
        Assert.assertEquals("Author Test0", output.getAuthor());
        Assert.assertEquals(new Date(), output.getLaunchDate());
        Assert.assertEquals("Title Test0", output.getTitle());
        Assert.assertEquals("20", output.getPrice());
    }
	
	@Test
    public void parseEntityListToVOListTest() {
        List<BookVO> outputList = DozerConverter.parserObject(inputObject.mockEntityList(), BookVO.class);
        BookVO outputZero = outputList.get(0);
        
        Assert.assertEquals(Long.valueOf(0L), outputZero.getId());
        Assert.assertEquals("Author Test0", outputZero.getAuthor());
        Assert.assertEquals(new Date(), outputZero.getLaunchDate());
        Assert.assertEquals("Title Test0", outputZero.getTitle());
        Assert.assertEquals("20", outputZero.getPrice());
        
        BookVO outputSeven = outputList.get(7);
        
        Assert.assertEquals(Long.valueOf(7L), outputSeven.getId());
        Assert.assertEquals("Author Test7", outputSeven.getAuthor());
        Assert.assertEquals(new Date(), outputSeven.getLaunchDate());
        Assert.assertEquals("Title Test7", outputSeven.getTitle());
        Assert.assertEquals("20", outputSeven.getPrice());
        
        BookVO outputTwelve = outputList.get(12);
        
        Assert.assertEquals(Long.valueOf(12L), outputTwelve.getId());
        Assert.assertEquals("Author Test12", outputTwelve.getAuthor());
        Assert.assertEquals(new Date(), outputTwelve.getLaunchDate());
        Assert.assertEquals("Title Test12", outputTwelve.getTitle());
        Assert.assertEquals("20", outputTwelve.getPrice());
    }
	
	
	@Test
    public void parseVOListToEntityListTest() {
        List<Book> outputList = DozerConverter.parserObject(inputObject.mockVOList(), Book.class);
        Book outputZero = outputList.get(0);
        
        Assert.assertEquals(Long.valueOf(0L), outputZero.getId());
        Assert.assertEquals("Author Test0", outputZero.getAuthor());
        Assert.assertEquals(new Date(), outputZero.getLaunchDate());
        Assert.assertEquals("Title Test0", outputZero.getTitle());
        Assert.assertEquals("20", outputZero.getPrice());
        
        Book outputSeven = outputList.get(7);
        
        Assert.assertEquals(Long.valueOf(7L), outputSeven.getId());
        Assert.assertEquals("Author Test7", outputSeven.getAuthor());
        Assert.assertEquals(new Date(), outputSeven.getLaunchDate());
        Assert.assertEquals("Title Test7", outputSeven.getTitle());
        Assert.assertEquals("20", outputSeven.getPrice());
        
        Book outputTwelve = outputList.get(12);
        
        Assert.assertEquals(Long.valueOf(12L), outputTwelve.getId());
        Assert.assertEquals("Author Test12", outputTwelve.getAuthor());
        Assert.assertEquals(new Date(), outputTwelve.getLaunchDate());
        Assert.assertEquals("Title Test12", outputTwelve.getTitle());
        Assert.assertEquals("20", outputTwelve.getPrice());
    }

}
