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
	
}
