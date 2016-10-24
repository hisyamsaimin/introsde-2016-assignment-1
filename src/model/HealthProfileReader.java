package model;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class HealthProfileReader {

    private Document doc;
    private XPath xpath;

    public static final String FILE_NAME = "people.xml";

    public void loadXML() throws ParserConfigurationException, SAXException, IOException {
    	DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setNamespaceAware(true);
        DocumentBuilder builder = domFactory.newDocumentBuilder();
        doc = builder.parse(FILE_NAME);
       // Document doc = builder.parse("people.xml");

        //creating xpath object

        getXPathObj(); 
    }

    public XPath getXPathObj() {

        XPathFactory factory = XPathFactory.newInstance();
        xpath = factory.newXPath();
        return xpath;
    }
     
     // Get the weight of person by calling the first name and last name

    public Node getWeight(String firstname, String lastname) throws XPathExpressionException {

            XPathExpression expr = xpath.compile("/people/person[firstname='" + firstname + "' and lastname='" + lastname + "']/healthprofile/weight");
            Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
            return node;
    }
        
    // Get the height of person by calling the first and last name

    public Node getHeight(String firstname, String lastname) throws XPathExpressionException {

            XPathExpression expr = xpath.compile("/people/person[firstname='" + firstname + "' and lastname='" + lastname + "']/healthprofile/height");
            Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
            return node;
    }

    //Print the list of all people

    public NodeList getAllPeople() throws XPathExpressionException {  

        XPathExpression expr = xpath.compile("/people/*");
        NodeList node = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
        return node;
    }

    // This will use the id to get the person's health profile

    public Node printHealthProfileById(String id) throws XPathExpressionException {

            XPathExpression expr = xpath.compile("/people/person[@id='" + id + "']/healthprofile");
            Node nodes = (Node) expr.evaluate(doc, XPathConstants.NODE);
            return nodes;
    }

    // This is a method which accepts a weight and an operator (=, > , <) as parameters and prints people that fulfill that condition

    public NodeList getPersonByWeight(String param, int weight) throws XPathExpressionException {
       
        XPathExpression expr = xpath.compile("/people/person/healthprofile[weight" + param + weight + "]/../*[self::firstname or self::lastname]/text()");        
        NodeList nodes = (NodeList)expr.evaluate(doc, XPathConstants.NODESET);
        return nodes;
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException,
		IOException, XPathExpressionException {

        HealthProfileReader healthProfileReader = new HealthProfileReader();
        healthProfileReader.loadXML();
         
        /*We are going to call methods to retrieve healthprofile information ( weight, height, healthprofile) of a 
        specific person according to his characteristics (lastname, firstname, id, weight). The characteristics are the following: 
        */                 

        String firstname = "Novak";
        String lastname = "Djokovic";
        String id = "0005";
        String param = ">";
        int weight = 80;
        
        // to demonstrate getWeight and getHeight methods let's check the weight and height of a person with an id=0005
          
        Node node = healthProfileReader.getWeight(firstname , lastname);
        System.out.println("Weight and Height of the person with id 0005");
        System.out.println(firstname + " " +lastname +" weights " + node.getTextContent() + " kilos.");
        
        node = healthProfileReader.getHeight(firstname , lastname);
        System.out.println(firstname + " " +lastname +" is " + node.getTextContent() + " meters tall.");
        System.out.println();

        //Printing the healthprofile of the person with an id=0005

        node = healthProfileReader.printHealthProfileById (id);
		System.out.println("Here are the healthprofile details of the person with id= " + id);
		printNode(node);

        //Printing the people with weight over 80 kg

        NodeList nodes = healthProfileReader.getPersonByWeight(param, weight);
        System.out.println("Here are all the people from the list who weight " + param + " " + weight + "kg:");
        
        for (int i = 0; i < nodes.getLength(); i += 2) {
            System.out.println(nodes.item(i).getTextContent() + " " + nodes.item(i+1).getTextContent());
		}
	   
       System.out.println();

        //Getting all the people with healprofile details from the list

        NodeList peopleNodes = healthProfileReader.getAllPeople();
        System.out.println("Here is the list of all the people from people.xml and their healthprofile details:");   
               
		for (int i = 0; i < peopleNodes.getLength(); i ++) {
            printNode(peopleNodes.item(i));
		}
    }    
	   //Printing the names of the nodes 
	private static void printNode(Node targetNode){				
		for(int l = 0; l < targetNode.getChildNodes().getLength(); l++){
				Node innerNode = targetNode.getChildNodes().item(l);	
				if(innerNode.getNodeType() == 1){
					if(innerNode.getNodeName().equals("healthprofile")){
						printNode(innerNode);
					} else {
						System.out.println(innerNode.getNodeName() + ": " + innerNode.getTextContent());
					}
				}				
		}		
		System.out.println();
	}
}