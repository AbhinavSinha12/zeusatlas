package cpre492.sdmay1020.plugin.zeus;

import com.ensoftcorp.plugin.atlas.query.api.QueryFactory;
import com.ensoftcorp.plugin.atlas.query.lang.*;

public class ArtifactFactory {
	
	/**
	 * Needs to be able to return 
	 * @param input
	 * @return
	 */
	public static IFunctionArtifact createFunction(String input)
	{
		
		QueryFactory qf = QueryFactory.instance;
		
		return qf.createFunctionArtifact(input);
		
		
	}
	
	public static ITypeArtifact createType(String input)
	{
		QueryFactory qf = QueryFactory.instance;
		
		return qf.createTypeArtifact(input);
	}
	
	public static IVariableArtifact createVarible(String input)
	{
		
		
		QueryFactory qf = QueryFactory.instance;
		
		return qf.createVariableArtifact(input);
	}
	
	/**
	 * Creates an Atlas Macro Artifact to use as input in script execution
	 * 
	 * @note We will not be supporting this
	 * @param input - String representing MACRO name
	 * @return an Atlas Macro Artifact
	 */
	public static IMacroArtifact createMacro(String input)
	{
		
		QueryFactory qf = QueryFactory.instance;
		
		return qf.createMacroArtifact(input);
	}

	
	/**
	 * 
	 * @return
	 */
	public static IArtifacts createArtifacts()
	{
		QueryFactory qf = QueryFactory.instance;
		
		IArtifacts retval = qf.createArtifacts();
		
		return retval;
	}
	
	/**
	 * 
	 * @param input
	 * @return
	 */
	public static IStringValue createString(String input)
	{
		QueryFactory qf = QueryFactory.instance;
		
		IStringValue retval = qf.createStringValue(input);
		
		return retval;
	}
}
