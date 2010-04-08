package cpre492.sdmay1020.plugin.zeus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.ensoftcorp.plugin.atlas.query.api.QueryFactory;
import com.ensoftcorp.plugin.atlas.query.lang.IArtifact;
import com.ensoftcorp.plugin.atlas.query.lang.IArtifacts;
import com.ensoftcorp.plugin.atlas.query.lang.IStringValue;
import com.ensoftcorp.plugin.atlas.query.lang.IValue;
import com.ensoftcorp.plugin.atlas.query.lang.IVariable;
import com.ensoftcorp.plugin.atlas.ui.api.GraphUI;

public class OutputResults {

	// TODO switch method to print to a file instead of the console
	
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
		IPath p = ResourcesPlugin.getWorkspace().getRoot().getLocation().append("output.txt");
		File outFile = p.toFile();
		if(!outFile.exists())
		{
			try {
				outFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		FileOutputStream fOutStream = null;
		try {
			fOutStream = new FileOutputStream(outFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
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
	}
	
	// TODO switch method to print to a file instead of the console
	
	/**
	 * toXMLFile creates an XML file displaying the type and name of the input IValue (or IValue array) in an
	 * easy to read table format
	 * 
	 * @param header - The header string that is printed before the result values
	 * @param result - The query results to print (can be a single IValue or an array of IValue objects)
	 * @author Tina Gervais
	 */
	public static void toXMLFile(String header, IValue... result) {
		DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		try {
			db = df.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DOMImplementation di = db.getDOMImplementation();
		Document doc = di.createDocument(null, "OUTPUT", null);
		Element root = doc.getDocumentElement();
		
		for(IValue r : result){
			if (r instanceof IArtifacts) {
				IArtifacts artifacts = (IArtifacts) r;
				
				StringBuilder s = new StringBuilder();
				
				for (IArtifact a : artifacts) {
					s.append(a.getName());
					s.append("\n  ");
				}		
				//out.println("Artifacts:\n  " + s.toString());
				
			} else if (r instanceof IVariable) {
				// not really possible at this time - query language only returns artifacts
				IVariable v = (IVariable) r;
				//out.println("Variable: " + v.getName());
				
			} else if (r instanceof IStringValue) {
				// not really possible at this time - query language only returns artifacts
				IStringValue s = (IStringValue) r;
				//out.println("StringValue: " + s.getValue());
			}
		}
	}
	
	// TODO switch method to print to a file instead of the console
	
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
