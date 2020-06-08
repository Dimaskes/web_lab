package dataSource;

import entities.Client;

import java.io.ByteArrayOutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class XmlConverter {

    private ByteArrayOutputStream baos;

    public ByteArrayOutputStream convertObject(Client client) throws JAXBException {
        baos = new ByteArrayOutputStream();
        JAXBContext context = JAXBContext.newInstance(Client.class);
        Marshaller marsh = context.createMarshaller();
        marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marsh.marshal(client, baos);
        return baos;
    }
}
