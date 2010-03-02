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
	 * We will not be implementing this
	 * @param input
	 * @return
	 */
	public static IMacroArtifact createMacro(String input)
	{
		
		QueryFactory qf = QueryFactory.instance;
		
		return qf.createMacroArtifact(input);
	}

	
	/**
	 * This method will use the Quer
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
