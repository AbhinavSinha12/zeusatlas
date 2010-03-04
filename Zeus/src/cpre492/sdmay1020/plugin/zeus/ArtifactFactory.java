package cpre492.sdmay1020.plugin.zeus;

import com.ensoftcorp.plugin.atlas.query.api.QueryFactory;
import com.ensoftcorp.plugin.atlas.query.lang.*;

/**
 * TODO : Tina to explain why we need this class
 * 
 * 
 * @author Alex Kharbush
 * @author Tina Gervais
 *
 */
public class ArtifactFactory {
	
	/**
	 * This static function will take a string argument and return an IFunctionArtifact
	 * 
	 * @param input - The string object that will be made into an IArtifact
	 * @return The IFunctionArtifact that was created with qf.createFunctionArtifact(String input)
	 * 
	 * @author Alex Kharbush
	 */
	public static IFunctionArtifact createFunction(String input)
	{
		//Create a new instance of the QueryFactory
		QueryFactory qf = QueryFactory.instance;
		
		//Return the IFunctionArtifact
		return qf.createFunctionArtifact(input);
	}
	
	/**
	 * This static function will take a string argument and return an ITypeArtifact
	 * 
	 * @param input - The string object that will be made into an IArtifact
	 * @return The ITypeArtifact that was created with qf.createTypeArtifact(String input)
	 * 
	 * @author Alex Kharbush
	 */
	public static ITypeArtifact createType(String input)
	{
		//Create a new instance of the QueryFactory
		QueryFactory qf = QueryFactory.instance;
		
		//Return the ITypeArtifact
		return qf.createTypeArtifact(input);
	}
	
	/**
	 * This static function will take a string argument and return an IVariableArtifact
	 * 
	 * @param input - The string object that will be made into an IArtifact
	 * @return The IVariableArtifact that was created with qf.createVariableArtifact(String input)
	 * 
	 * @author Alex Kharbush
	 */
	public static IVariableArtifact createVarible(String input)
	{
		//Create a new instance of the QueryFactory
		QueryFactory qf = QueryFactory.instance;
		
		//Return the IVaribleArtifact
		return qf.createVariableArtifact(input);
	}
	
	/**
	 * This static function will take a string argument and return an IMacroArtifact
	 * 
	 * @param input - The string object that will be made into an IMacroArtifact
	 * @return The IFunctionArtifact that was created with qf.createMacroArtifact(String input)
	 * 
	 * @note We will not be supporting this
	 * 
	 * @author Alex Kharbush
	 */
	public static IMacroArtifact createMacro(String input)
	{
		//Create a new instance of the QueryFactory
		QueryFactory qf = QueryFactory.instance;
		
		//Return the IMacroArtifact
		return qf.createMacroArtifact(input);
	}

	
	/**
	 * This static function will take a string argument and return an IFunctionArtifact
	 * 
	 * @param input - The string object that will be made into an IArtifact
	 * @return The IFunctionArtifact that was created with qf.createFunctionArtifact(String input)
	 * 
	 * @author Alex Kharbush
	 */
	public static IArtifacts createArtifacts()
	{
		//Create a new instance of the QueryFactory
		QueryFactory qf = QueryFactory.instance;
		
		//Return the IArtifacts
		return qf.createArtifacts();
	}
	
	/**
	 * This static function will take a string argument and return an IStringValue
	 * 
	 * @param input - The string object that will be made into an IStringValue
	 * @return The IFunctionArtifact that was created with qf.createStringValue(String input)
	 * 
	 * @author Alex Kharbush
	 */
	public static IStringValue createString(String input)
	{
		//Create a new instance of the QueryFactory
		QueryFactory qf = QueryFactory.instance;
		
		//Return the IStringValue
		return qf.createStringValue(input);
	}
	
	
	// TODO switch method to print to a file instead of the console
	
	/**
	 * showResult prints the type and name of the input IValue (or IValue array) in an
	 * easy to read format
	 * 
	 * @param header - The header string that is printed before the result values
	 * @param result - The query results to print (can be a single IValue or an array of IValue objects)
	 * @author Tina Gervais
	 */
	public static void showResult(String header, IValue... result) {
		System.out.println(header+"\n");
		for(IValue r : result){
			if (r instanceof IArtifacts) {
				IArtifacts artifacts = (IArtifacts) r;
				
				StringBuilder s = new StringBuilder();
				
				for (IArtifact a : artifacts) {
					s.append(a.getName());
					s.append("\n  ");
				}		
				System.out.println("Artifacts:\n  " + s.toString());
				
			} else if (r instanceof IVariable) {
				// not really possible at this time - query language only returns artifacts
				IVariable v = (IVariable) r;
				System.out.println("Variable: " + v.getName());
				
			} else if (r instanceof IStringValue) {
				// not really possible at this time - query language only returns artifacts
				IStringValue s = (IStringValue) r;
				System.out.println("StringValue: " + s.getValue());
			}
		}
		System.out.println("\n");
	}
}
