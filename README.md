# introsde-2016-assignment-1
for submission of assignment 01

Student name: Muhamad Hisyam bin Saimin

Email: muhamad.binsaimin@studenti.unitn.it

This document contains the description of the list and functions of files in the project, and how to run the project.

Used technologies: Java, XML/XSD, JSON, XPATH, JAXB/Jackson

## The project contains:

In the project you will find a java package called model which contains the below java class:
>> 1 HealthProfileReader.java uses xPath to retrive the health information from people.xml and print output according to the assigment requirement:
>>> - method  getWeight and getHeight using specific person firstname and last name
>>> - print the list of all people
>>> - print the person health profile with id 0005
>>> - print the list of people with weight > 80kg

>> 2 JAXBMarshaller.java will use the JAXB marshalling function of which will  create 3 new persons healhprofile into the newly generated people1.xml

>> 3 JAXBUnMarshaller.java will use the JAXB unmarshallig function to unmarshal the content of people.xml and convert them into java objects

>> 4 JSONMarshaller.java  will create 3 new people in java and marshal them into generated JSON

The root project contains:
>>1 ivy.xml file of dependency manager with specified dependencies, helps to manage the dependencies.

>>2 build.xml - the file contains JAXB XJC which will generate necessary java classes for marshalling, compiles and runs the project according to the assigment requirements.

>>3 people.xml is the file which contains the list of 20 people with their respective healhprofiles

>>4 people.xsd - XML schema for people.xml, is used in build.xml for generation of the java objects for marshalling function.

## Instruction on how to run the project:

*  To run the whole project in a single command line :  

>> ant execute.evaluation

*  If you want to run each function, run below command first: 

>> ant compile 

*  To  run Un-Marshalling/ function : 

>> ant execute.JAXBMarshaller 

*  To run the  Unmarshalling function : 

>> ant execute.JAXBUnMarshaller 

* To run the  JSON marshalling  function : 

>>  ant execute.JSONMarshaller 
