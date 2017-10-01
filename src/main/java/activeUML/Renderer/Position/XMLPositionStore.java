package activeUML.Renderer.Position;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import activeUML.Renderer.DrawBox;

// Extract position information from a xml file
/*
<?xml version = "1.0" encoding = "UTF-8" standalone = "no"?>
   <xmlkey x="20" y="20">
 */

public class XMLPositionStore implements IPositionStore {
	private File xmlFile;

	public XMLPositionStore() {

	}

	public void changeToStoredPosition(DrawBox sourceBox, String key) {
		if (this.xmlFile == null || this.xmlFile.exists() == false) {
			System.out.println("read file is empty");
			return;
		}
		Integer x = this.getXForKey(key);
		Integer y = this.getYForKey(key);
		if (x != null) {
			sourceBox.setX(x);
		}
		if (y != null) {
			sourceBox.setY(y);
		}
	}

	public void setup() {
		this.xmlFile = new File("user_box_position.xml");
		if (this.xmlFile.exists() == false) {
			this.createFile();
		}
	}

	@Override
	public void setPosition(DrawBox sourceBox, String key) {
		// get node
		Element node = this.getNodeForKey(key);
		// node does not exists -> create it
		if (node == null) {
			this.createNode(key);
			node = this.getNodeForKey(key);
		}
		if (node == null) {
			return;
		}
		// save the changes
		this.setNodeAttributes(new Integer(sourceBox.getX()).toString(), new Integer(sourceBox.getY()).toString(), key);

	}

	private void createFile() {
		try {
			this.xmlFile.createNewFile();
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();

			Element root = doc.createElement("root");
			doc.appendChild(root);
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(this.xmlFile);
			transformer.transform(source, result);

			// Output to console for testing
			StreamResult consoleResult = new StreamResult(System.out);
			transformer.transform(source, consoleResult);

		} catch (IOException e) {
			System.out.println("a new empty file could not be created");
		} catch (ParserConfigurationException e) {
			System.out.println("could not create empty xml document into file");
		} catch (TransformerConfigurationException e) {
			System.out.println("could not create a new transformer instance");
		} catch (TransformerException e) {
			System.out.println(" an unrecoverable error occured during the course of the transformation");
		}
	}

	private void setNodeAttributes(String x, String y, String tagName) {
		try {
			// set the attributes of that node

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(this.xmlFile);
			doc.getDocumentElement().normalize();
			NodeList nodes = doc.getElementsByTagName(tagName);
			Element node = (Element) nodes.item(0);
			node.setAttribute("x", x);
			node.setAttribute("y", y);
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();

			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(this.xmlFile);
			transformer.transform(source, result);
			// Output to console for testing
			StreamResult consoleResult = new StreamResult(System.out);
			transformer.transform(source, consoleResult);

		} catch (ParserConfigurationException e) {
			System.out.println("could not create empty xml document into file");
		} catch (TransformerConfigurationException e) {
			System.out.println("could not create a new transformer instance");
		} catch (TransformerException e) {
			System.out.println(" an unrecoverable error occured during the course of the transformation");
		} catch (NullPointerException e) {
			System.out.println("a NullPointerException accured");
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Integer getXForKey(String key) {
		try {
			Node node = this.getNodeForKey(key);
			String value = node.getAttributes().getNamedItem("x").getNodeValue();
			return Integer.valueOf(value);
		} catch (NullPointerException e) {
			System.out.println("error while parsing the document");
		} catch (NumberFormatException e) {
			System.out.println("error the x attribute is not a number");
		}
		return null;
	}

	private Integer getYForKey(String key) {
		try {
			Node node = this.getNodeForKey(key);
			String value = node.getAttributes().getNamedItem("y").getNodeValue();
			return Integer.valueOf(value);
		} catch (NullPointerException e) {
			System.out.println("error while parsing the document");
		} catch (NumberFormatException e) {
			System.out.println("error the y attribute is not a number");
		}
		return null;

	}

	private Element getNodeForKey(String key) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(this.xmlFile);
			doc.getDocumentElement().normalize();
			NodeList nodes = doc.getElementsByTagName(key);
			Node node = nodes.item(0);
			return (Element) node;
		} catch (ParserConfigurationException e) {
			System.out.println("a parse error has accured");
		} catch (SAXException e) {
			System.out.println("a DocumentBuilder cannot be created which satisfies the configuration requested.");
		} catch (IOException e) {
			System.out.println("file not readable");
		} catch (NullPointerException e) {
			System.out.println("error while parsing the document");
		}
		return null;
	}

	private void createNode(String key) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(this.xmlFile);
			doc.getDocumentElement().normalize();
			Element createdNode = doc.createElement(key);
			createdNode.setAttribute("x", "");
			createdNode.setAttribute("y", "");
			doc.getDocumentElement().appendChild(createdNode);
			DOMSource source = new DOMSource(doc);

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			StreamResult result = new StreamResult(this.xmlFile);
			transformer.transform(source, result);

		} catch (ParserConfigurationException e) {
			System.out.println("a parse error has accured");
		} catch (SAXException e) {
			System.out.println("a DocumentBuilder cannot be created which satisfies the configuration requested.");
		} catch (IOException e) {
			System.out.println("file not readable");
		} catch (NullPointerException e) {
			System.out.println("error while parsing the document");
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
