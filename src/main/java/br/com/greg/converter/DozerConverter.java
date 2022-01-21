package br.com.greg.converter;

import java.util.ArrayList;
import java.util.List;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class DozerConverter {
	
	private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();
	
	public static <O, D> D parserObject(O origin, Class<D> destination) {
		return mapper.map(origin, destination);
	}
	
	public static <O, D> List<D> parserObject(List<O> origin, Class<D> destination) {
		List<D> destinationObjects = new ArrayList<D>();
		for (Object o : origin) {
			destinationObjects.add(mapper.map(o, destination));
		}
		return destinationObjects;
	}

}
