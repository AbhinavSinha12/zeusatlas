package cpre492.sdmay1020.plugin.zeus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.ProcessingInstruction;
import org.xml.sax.SAXException;

import com.ensoftcorp.plugin.atlas.query.api.QueryFactory;
import com.ensoftcorp.plugin.atlas.query.lang.IArtifact;
import com.ensoftcorp.plugin.atlas.query.lang.IArtifacts;
import com.ensoftcorp.plugin.atlas.query.lang.IStringValue;
import com.ensoftcorp.plugin.atlas.query.lang.IValue;
import com.ensoftcorp.plugin.atlas.query.lang.IVariable;
import com.ensoftcorp.plugin.atlas.ui.api.GraphUI;

public class OutputResults {

	static IPath outPathTxt = ResourcesPlugin.getWorkspace().getRoot().getLocation().append("output.txt");
	static File outFileTxt = null;
	static IPath outPathXML = ResourcesPlugin.getWorkspace().getRoot().getLocation().append("output.xml");
	static File outFileXML = null;
	
	/**
	 * toTextFile prints the type and name of the input IValue (or IValue array) in an
	 * easy to read format in a .txt file
	 * 
	 * @param header - The header string that is printed before the result values
	 * @param result - The query results to print (can be a single IValue or an array of IValue objects)
	 * @author Tina Gervais
	 */
	public static void toTextFile(String header, IValue... result) {
		//Create an output txt file in the current workspace
		if(outFileTxt == null)
		{
			outFileTxt = outPathTxt.toFile();
			if(outFileTxt.exists())
			{
				outFileTxt.delete();
			}
			try {
				outFileTxt.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		FileOutputStream fOutStream = null;
		try {
			fOutStream = new FileOutputStream(outFileTxt);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		PrintWriter out = new PrintWriter(fOutStream, true);
		
		out.println(header+"\n");
		for(IValue r : result){
			if (r instanceof IArtifacts) {
				IArtifacts artifacts = (IArtifacts) r;
				
				StringBuilder s = new StringBuilder();
				
				for (IArtifact a : artifacts) {
					s.append(a.getName());
					s.append("\n  ");
				}		
				out.println("Artifacts:\n  " + s.toString());
				
			} else if (r instanceof IVariable) {
				// not really possible at this time - query language only returns artifacts
				IVariable v = (IVariable) r;
				out.println("Variable: " + v.getName());
				
			} else if (r instanceof IStringValue) {
				// not really possible at this time - query language only returns artifacts
				IStringValue s = (IStringValue) r;
				out.println("StringValue: " + s.getValue());
			}
		}
		out.println("\n");
		
		out.close();
	}
	
	/**
	 * toXMLFile creates an XML file displaying the type and name of the input IValue (or IValue array) in an
	 * easy to read table format
	 * 
	 * @param header - The header string that is printed before the result values
	 * @param result - The query results to print (can be a single IValue or an array of IValue objects)
	 * @author Tina Gervais
	 */
	public static void toXMLFile(String header, IValue... result) {
		if(outFileXML == null)
		{
			//first time run, initialize global variables
			outFileXML = outPathXML.toFile();
			//delete the old output file if it exists
			if(outFileXML.exists())
			{
				outFileXML.delete();
			}
		}
		
		//initialize xml document
		DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		try {
			db = df.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		Document xmldoc = null;
		if(outFileXML.exists())
		{
			//try to parse the old xml file
			try {
				xmldoc = db.parse(outFileXML);	
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else
		{
			//create new xml document
			DOMImplementation di = db.getDOMImplementation();
			xmldoc = di.createDocument("urn:schemas-microsoft-com:office:spreadsheet", "Workbook", null);
			
			//initialize the document for excel
			Element root = xmldoc.getDocumentElement();
			
			ProcessingInstruction pi = xmldoc.createProcessingInstruction("mso-application", "progid=\"Excel.Sheet\"");
			root.appendChild(pi);
			
			root.setAttribute("xmlns", "urn:schemas-microsoft-com:office:spreadsheet");
			root.setAttribute("xmlns:o", "urn:schemas-microsoft-com:office:office");
			root.setAttribute("xmlns:x", "urn:schemas-microsoft-com:office:excel");
			root.setAttribute("xmlns:ss", "urn:schemas-microsoft-com:office:spreadsheet");
			root.setAttribute("xmlns:html", "http://www.w3.org/TR/REC-html40");
			
			Element c1 = xmldoc.createElement("Styles");
			Element c2 = xmldoc.createElement("Style");
			c2.setAttribute("ss:ID", "s62");
			Element c3 = xmldoc.createElement("Alignment");
			c3.setAttribute("ss:WrapText", "1");
			
			c2.appendChild(c3);
			c1.appendChild(c2);
			root.appendChild(c1);
			
			c1 = xmldoc.createElement("Worksheet");
			c1.setAttribute("ss:Name", "Output");
			c2 = xmldoc.createElement("Table");
			c2.setAttribute("ID", "TableRoot");
			c2.setIdAttribute("ID", true);
			c1.appendChild(c2);
			root.appendChild(c1);
		}
		
		Element tableRoot = (Element)xmldoc.getElementsByTagName("Table").item(0);
		Element elem = xmldoc.createElement("Row");
		elem.setAttribute("ID", header);
		elem.setIdAttribute("ID", true);
		
		Element child = xmldoc.createElement("Cell");
		child.setAttribute("ss:StyleID", "s62");
		Element child2 = xmldoc.createElement("Data");
		child2.setAttribute("ss:Type", "String");
		
		child2.appendChild(xmldoc.createTextNode(header));
		child.appendChild(child2);
		elem.appendChild(child);

		for(IValue r : result){
			if (r instanceof IArtifacts) {
				IArtifacts artifacts = (IArtifacts) r;
				
				StringBuilder s = new StringBuilder();
				
				for (IArtifact a : artifacts) {
					s.append(a.getName() + " ");
				}
				
				Element c = xmldoc.createElement("Cell");
				c.setAttribute("ss:StyleID", "s62");
				Element c2 = xmldoc.createElement("Data");
				c2.setAttribute("ss:Type", "String");
				
				c2.appendChild(xmldoc.createTextNode(s.toString()));
				c.appendChild(c2);
				elem.appendChild(c);
				//out.println("Artifacts:\n  " + s.toString());
				
			} else if (r instanceof IVariable) {
				// not really possible at this time - query language only returns artifacts
				IVariable v = (IVariable) r;
				Element c = xmldoc.createElement("Cell");
				c.setAttribute("ss:StyleID", "s62");
				Element c2 = xmldoc.createElement("Data");
				c2.setAttribute("ss:Type", "String");
				
				c2.appendChild(xmldoc.createTextNode(v.getName()));
				c.appendChild(c2);
				elem.appendChild(c);
				//out.println("Variable: " + v.getName());
				
			} else if (r instanceof IStringValue) {
				// not really possible at this time - query language only returns artifacts
				IStringValue s = (IStringValue) r;
				Element c = xmldoc.createElement("Cell");
				c.setAttribute("ss:StyleID", "s62");
				Element c2 = xmldoc.createElement("Data");
				c2.setAttribute("ss:Type", "String");
				
				c2.appendChild(xmldoc.createTextNode(s.getValue()));
				c.appendChild(c2);
				elem.appendChild(c);
				//out.println("StringValue: " + s.getValue());
			}
		}
		
		tableRoot.appendChild(elem);
		
		Transformer transformer = null;
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
		} catch (TransformerConfigurationException e2) {
			e2.printStackTrace();
		} catch (TransformerFactoryConfigurationError e2) {
			e2.printStackTrace();
		}
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");

		//initialize StreamResult with File object to save to file
		StreamResult output = new StreamResult(new StringWriter());
		DOMSource source = new DOMSource(xmldoc);
		try {
			transformer.transform(source, output);
		} catch (TransformerException e1) {
			e1.printStackTrace();
		}

		String xmlString = output.getWriter().toString();
		
		if(outFileXML.exists())
		{
			outFileXML.delete();
		}
		try {
			outFileXML.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		FileOutputStream fOutStream = null;
		try {
			fOutStream = new FileOutputStream(outFileXML);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		PrintWriter out = new PrintWriter(fOutStream, true);
		
		out.println(xmlString);
		out.close();
	}
	
	/**
	 * showGraph creates a graph in the Atlas Graph View of the input
	 * 
	 * @param title - the title to display in the graph view heading
	 * @param roots - the set of noeds that will be root nodes in the graph
	 * @param leaves - the set of nodes that will be leaf nodes in the graph
	 * @param omissions - the set of nodes that will be omissions nodes in the graph
	 * @param highlights - the set of nodes that will be highlights nodes in the graph
	 * @param simplify - the set of nodes that will be simplify nodes in the graph
	 * @param center - the set of nodes that will be center nodes in the graph
	 * 
	 * @author Tina Gervais
	 * @author Alex Kharbush
	 */
	public static void showGraph(String title, IArtifacts roots, IArtifacts leaves, IArtifacts omissions,IArtifacts highlights, IArtifacts simplify, IArtifacts center) {
		
		GraphUI.instance.showGraph(title, roots, leaves, omissions, highlights, simplify, center);
	}
}
