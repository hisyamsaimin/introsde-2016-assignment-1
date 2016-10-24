package model;

import people.generated.*;
import javax.xml.bind.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class JAXBMarshaller {

	public void generateXMLDocument(File xmlDocument) {
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance("people.generated");
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty("jaxb.formatted.output", new Boolean(true));
			people.generated.ObjectFactory factory = new people.generated.ObjectFactory();

			// Create the first person health profile and insert it into the generated xml file

			PeopleType people = factory.createPeopleType();

			HealthProfileType healthProfile = factory.createHealthProfileType();

			healthProfile.setLastupdate("2016-10-23T18:10:00.000+02:00");
			healthProfile.setWeight(56);
			healthProfile.setHeight(1.69);
			healthProfile.setBmi(19.60);

			PersonType person = factory.createPersonType();
			person.setId(0002);
			person.setFirstname("Hisyam");
			person.setLastname("Saimin");
			person.setBirthdate("1974-05-16T18:00:00.000+02:00");
			person.setHealthprofile(healthProfile);

			List<PersonType> personList = people.getPerson();
			personList.add(person);

			// Create the second person health profile and insert it into the generated xml file

			person = factory.createPersonType();

			healthProfile = factory.createHealthProfileType();

			healthProfile.setLastupdate("2016-10-23T18:15:00.000+02:00");
			healthProfile.setWeight(50);
			healthProfile.setHeight(1.64);
			healthProfile.setBmi(18.60);

			person.setId(0003);
			person.setFirstname("Israel");
			person.setLastname("Marqez");
			person.setBirthdate("1958-08-16T18:00:00.000+02:00");
			person.setHealthprofile(healthProfile);


			personList = people.getPerson();
			personList.add(person);

			// Create the third person health profile and insert it into the generated xml file			

			person = factory.createPersonType();

			healthProfile = factory.createHealthProfileType();

			healthProfile.setLastupdate("2016-10-23T18:20:00.000+02:00");
			healthProfile.setWeight(89);
			healthProfile.setHeight(1.76);
			healthProfile.setBmi(28.70);

			person.setId(0004);
			person.setFirstname("Joel");
			person.setLastname("Romero");
			person.setBirthdate("1951-10-02T18:00:00.000+02:00");
			person.setHealthprofile(healthProfile);

			personList = people.getPerson();
			personList.add(person);

				

			JAXBElement<PeopleType> peopleElement = factory
					.createPeople(people);
			marshaller.marshal(peopleElement,
					new FileOutputStream(xmlDocument));

		} catch (IOException e) {
			System.out.println(e.toString());

		} catch (JAXBException e) {
			System.out.println(e.toString());

		}

	}

	public static void main(String[] argv) {
		String xmlDocument = "people1.xml";
		JAXBMarshaller jaxbMarshaller = new JAXBMarshaller();
		jaxbMarshaller.generateXMLDocument(new File(xmlDocument));
	}
}
