package _12.codec;

import org.jboss.marshalling.*;

import java.io.IOException;

/**
 * @author huang
 * @version v1.0
 * @date 2019-10-22 4:03 PM
 */
public class MarshallingCodeCFactory {


    public static Marshaller buildMarshalling() throws IOException {
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();

        configuration.setVersion(5);

        return marshallerFactory.createMarshaller(configuration);
    }

    public static Unmarshaller buildUnMarshalling() throws IOException {
        MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        MarshallingConfiguration configuration = new MarshallingConfiguration();

        configuration.setVersion(5);

        return marshallerFactory.createUnmarshaller(configuration);
    }
}