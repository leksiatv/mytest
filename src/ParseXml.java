import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

class ParseXml {

    static XmlFile parseXml(String fileName, Boolean isSecondFile) throws Exception {
        HashMap<String, OfferHash> offerList = new HashMap<>();
        HashSet<String> imagesList = new HashSet<>();
        Offer off = null;
        OfferHash offHash = null;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        xmlInputFactory.setProperty("javax.xml.stream.isCoalescing", true);
        try {
            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName), "UTF8");
            while (xmlEventReader.hasNext()) {
                XMLEvent xmlEvent = xmlEventReader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equals("offers")) {
                        System.out.println("offers");
                        while (xmlEventReader.hasNext()) {
                            xmlEvent = xmlEventReader.nextEvent();
                            if (xmlEvent.isStartElement()) {
                                startElement = xmlEvent.asStartElement();
                                if (startElement.getName().getLocalPart().equals("offer")) {
                                    off = new Offer();
                                    offHash = new OfferHash();
                                    Attribute idAttr = startElement.getAttributeByName(new QName("id"));
                                    if (idAttr != null) {
                                        off.setId(idAttr.getValue());
                                    }
                                    Iterator<Attribute> attributes = xmlEvent.asStartElement().getAttributes();
                                    while (attributes.hasNext()) {
                                        Attribute myAttribute = attributes.next();
                                        off.addHash(MakeHash.main(myAttribute.getValue().toString()));
                                    }
                                } else if (isSecondFile && startElement.getName().getLocalPart().equals("picture")) {
                                    xmlEvent = xmlEventReader.nextEvent();
                                    if (!xmlEvent.isEndElement()) {
                                        assert off != null;
                                        off.addHash(MakeHash.main(xmlEvent.asCharacters().getData()));
                                        String img = xmlEvent.asCharacters().getData();
                                        imagesList.add(img);
                                        Integer hashString = MakeHash.main(img);
                                        off.addPictures(hashString);
                                    }
                                } else {
                                    xmlEvent = xmlEventReader.nextEvent();
                                    if (!xmlEvent.isEndElement()) {
                                        assert off != null;
                                        off.addHash(MakeHash.main(xmlEvent.asCharacters().getData()));
                                    }
                                }
                            }
                            if (xmlEvent.isEndElement()) {
                                EndElement endElement = xmlEvent.asEndElement();
                                if (endElement.getName().getLocalPart().equals("offer")) {
                                    assert offHash != null;
                                    offHash.setId(off.getId());
                                    offHash.setPictures(off.getPictures());
                                    String mainHash = off.toString();
                                    offHash.setOfferMainHash(mainHash);
                                    offerList.put(offHash.getId(), offHash);
                                }
                            }
                        }
                    }
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
        System.out.println("Parse XML DONE " + offerList.size() + " offers");
        return new XmlFile(offerList, imagesList);
    }
}