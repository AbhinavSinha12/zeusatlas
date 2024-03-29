package cpre492.sdmay1020.plugin;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.ensoftcorp.plugin.atlas.query.lang.IArtifacts;
import cpre492.sdmay1020.plugin.zeus.ArtifactFactory;
import cpre492.sdmay1020.plugin.zeus.OutputResults;

public class OutputResultsTest {

	OutputResults o  = null;
	
	@Before 
	public void setUp() 
	{ 
	}
	
	@After 
	public void tearDown() 
	{ 
		OutputResults.outFileTxt = null;
		OutputResults.outFileXML = null;
	}
	
	@Test
	public void testTextFile()
	{
		//Setup variables
		String testOutput = "Test Artifacts 1: dswrite ";
		String testOutput2 = "Test2 Artifacts 1: dswrite";
		
		IArtifacts set = ArtifactFactory.createArtifacts();
		set.add(ArtifactFactory.createFunction("dswrite"));
		
		//Write to text file, should append second write
		OutputResults.toTextFile("Test", set);
		OutputResults.toTextFile("Test2", set);
		
		Scanner outfile = null;
		Scanner testfile = new Scanner(testOutput + testOutput2);
		try {
			outfile = new Scanner(OutputResults.outFileTxt);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Text file not created");
		}
		
		//Check text file
		while(outfile.hasNext())
		{
			assertTrue(testfile.next().equalsIgnoreCase(outfile.next()));
		}
		
		//Reset OutputResults
		OutputResults.outFileTxt = null;
		
		//Write to text file, should overwrite
		OutputResults.toTextFile("Test2", set);
		
		testfile = new Scanner(testOutput2);
		try {
			outfile = new Scanner(OutputResults.outFileTxt);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Text file not created");
		}
		
		//Check text file
		while(outfile.hasNext())
		{
			assertTrue(testfile.next().equalsIgnoreCase(outfile.next()));
		}
	}
	
	@Test
	public void testXMLFile()
	{
		//Setup variables
		IArtifacts set = ArtifactFactory.createArtifacts();
		set.add(ArtifactFactory.createFunction("dswrite"));
		
		//Write to XML file, should append second write
		OutputResults.toXMLFile("Test", set);
		OutputResults.toXMLFile("Test2", set);
		
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = null;
		Document doc = null;
		try {
			docBuilder = docBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			 doc = docBuilder.parse(OutputResults.outFileXML);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Node root = doc.getDocumentElement();
		NamedNodeMap attributes = root.getAttributes();
		
		//Check attributes of the document element
		assertTrue(attributes.getNamedItem("xmlns").getNodeValue().equalsIgnoreCase("urn:schemas-microsoft-com:office:spreadsheet"));
		assertTrue(attributes.getNamedItem("xmlns:o").getNodeValue().equalsIgnoreCase("urn:schemas-microsoft-com:office:office"));
		assertTrue(attributes.getNamedItem("xmlns:x").getNodeValue().equalsIgnoreCase("urn:schemas-microsoft-com:office:excel"));
		assertTrue(attributes.getNamedItem("xmlns:ss").getNodeValue().equalsIgnoreCase("urn:schemas-microsoft-com:office:spreadsheet"));
		assertTrue(attributes.getNamedItem("xmlns:html").getNodeValue().equalsIgnoreCase("http://www.w3.org/TR/REC-html40"));
		
		//Check document elements
		NodeList nodes = doc.getElementsByTagName("Styles");
		Node node = null;
		assertTrue(nodes.getLength()==1);
		
		nodes = doc.getElementsByTagName("Style");
		node = nodes.item(0);
		assertTrue(nodes.getLength()==1);
		
		nodes = doc.getElementsByTagName("Alignment");
		node = nodes.item(0);
		assertTrue(nodes.getLength()==1);
		
		nodes = doc.getElementsByTagName("Worksheet");
		assertTrue(nodes.getLength()==1);
		
		nodes = doc.getElementsByTagName("Table");
		assertTrue(nodes.getLength()==1);
		
		nodes = doc.getElementsByTagName("Row");
		assertTrue(nodes.item(0).getAttributes().getNamedItem("ID").getNodeValue().equalsIgnoreCase("Test"));
		assertTrue(nodes.item(1).getAttributes().getNamedItem("ID").getNodeValue().equalsIgnoreCase("Test2"));
		
		//Reset OutputResults
		OutputResults.outFileXML = null;
		
		//Write to XML file, should overwrite the file
		OutputResults.toXMLFile("Test3", set);
		
		try {
			 doc = docBuilder.parse(OutputResults.outFileXML);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Check that the file was overwritten
		nodes = doc.getElementsByTagName("Row");
		assertTrue(nodes.item(0).getAttributes().getNamedItem("ID").getNodeValue().equalsIgnoreCase("Test3"));
	}
}
