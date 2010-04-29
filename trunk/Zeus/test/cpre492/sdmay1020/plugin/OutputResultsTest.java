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
		String testOutput = "Test Artifacts 1: dswrite ";
		String testOutput2 = "Test2 Artifacts 1: dswrite";
		
		IArtifacts set = ArtifactFactory.createArtifacts();
		set.add(ArtifactFactory.createFunction("dswrite"));
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
		
		while(outfile.hasNext())
		{
			assertTrue(testfile.next().equalsIgnoreCase(outfile.next()));
		}
		
		OutputResults.outFileTxt = null;
		
		OutputResults.toTextFile("Test2", set);
		
		testfile = new Scanner(testOutput2);
		try {
			outfile = new Scanner(OutputResults.outFileTxt);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Text file not created");
		}
		
		while(outfile.hasNext())
		{
			assertTrue(testfile.next().equalsIgnoreCase(outfile.next()));
		}
	}
	
	@Test
	public void testXMLFile()
	{
		IArtifacts set = ArtifactFactory.createArtifacts();
		set.add(ArtifactFactory.createFunction("dswrite"));
		
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
		
		assertTrue(attributes.getNamedItem("xmlns").getNodeValue().equalsIgnoreCase("urn:schemas-microsoft-com:office:spreadsheet"));
		assertTrue(attributes.getNamedItem("xmlns:o").getNodeValue().equalsIgnoreCase("urn:schemas-microsoft-com:office:office"));
		assertTrue(attributes.getNamedItem("xmlns:x").getNodeValue().equalsIgnoreCase("urn:schemas-microsoft-com:office:excel"));
		assertTrue(attributes.getNamedItem("xmlns:ss").getNodeValue().equalsIgnoreCase("urn:schemas-microsoft-com:office:spreadsheet"));
		assertTrue(attributes.getNamedItem("xmlns:html").getNodeValue().equalsIgnoreCase("http://www.w3.org/TR/REC-html40"));
		
		NodeList nodes = doc.getElementsByTagName("Row");
		assertTrue(nodes.item(0).getAttributes().getNamedItem("ID").getNodeValue().equalsIgnoreCase("Test"));
		assertTrue(nodes.item(1).getAttributes().getNamedItem("ID").getNodeValue().equalsIgnoreCase("Test2"));
		
		OutputResults.outFileXML = null;
		
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
		
		nodes = doc.getElementsByTagName("Row");
		assertTrue(nodes.item(0).getAttributes().getNamedItem("ID").getNodeValue().equalsIgnoreCase("Test3"));
	}
}
