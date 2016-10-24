package model;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

import people.generated.*;

public class JSONMarshaller {  	
	public static PeopleType people = new PeopleType();

	// Creating the 1st person with healthprofile

	public static void initializeDB() {
		PersonType p1 = new PersonType();
		p1.setId(0002);
		p1.setFirstname("Hisyam");
		p1.setLastname("Saimin");
		p1.setBirthdate("1974-05-16T18:00:00.000+02:00");

		HealthProfileType hp1 = new HealthProfileType();
		hp1.setLastupdate("2014-09-20T18:10:00.000+02:00");
		hp1.setWeight(56);
		hp1.setHeight(1.69);
		hp1.setBmi(19.60);

		p1.setHealthprofile(hp1);

		// Creating the 2nd person with healthprofile

		PersonType p2 = new PersonType();
		p2.setId(0003);
		p2.setFirstname("Hanis");
		p2.setLastname("Saimin");
		p2.setBirthdate("1958-08-16T18:00:00.000+02:00");

		HealthProfileType hp2 = new HealthProfileType();
		hp2.setLastupdate("2014-09-20T18:15:00.000+02:00");
		hp2.setWeight(50);
		hp2.setHeight(1.64);
		hp2.setBmi(18.60);

		p1.setHealthprofile(hp2);

		// Creating the 3rd person with healthprofile

		PersonType p3 = new PersonType();
		p3.setId(0004);
		p3.setFirstname("Haizat");
		p3.setLastname("Saimin");
		p3.setBirthdate("1951-10-02T18:00:00.000+02:00");

		HealthProfileType hp3 = new HealthProfileType();
		hp3.setLastupdate("2014-09-20T18:20:00.000+02:00");
		hp3.setWeight(89);
		hp3.setHeight(1.76);
		hp3.setBmi(28.70);

		p3.setHealthprofile(hp3);

		people.getPerson().add(p1);
		people.getPerson().add(p2);
		people.getPerson().add(p3);
	}	

	// creating the json file
	public static void main(String[] args) throws Exception {
		
		initializeDB();
		
		// Jackson Object Mapper 
		ObjectMapper mapper = new ObjectMapper();
		
		// Adding the Jackson Module to process JAXB annotations
        JaxbAnnotationModule module = new JaxbAnnotationModule();
        
		// configure as necessary
		mapper.registerModule(module);
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);

        String result = mapper.writeValueAsString(people);
        System.out.println(result);
        mapper.writeValue(new File("people.json"), people);
    }
}