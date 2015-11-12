package ie.headway.app.xml;

import org.simpleframework.xml.Serializer;

import java.io.File;

/**
 * A {@link Serializer} with two additional overloads of the {@link Serializer#read(Class, File)} and
 * {@link Serializer#write(Object, File)} methods intended for cases where the {@link File} or is already known.
 * */
public interface SpecialSerializer<T> extends Serializer {

  T read() throws Exception;

  void write(T t) throws Exception;

}
