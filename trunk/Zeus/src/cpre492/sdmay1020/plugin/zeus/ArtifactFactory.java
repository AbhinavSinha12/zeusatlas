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

	//TODO : need to be able to create IArtifact set, called IArtifacts 
	/**
	 * 
	 */
	public static IArtifacts createArtifacts()
	{
		IArtifacts retval = new IArtifacts();
		
		return retval;
	}
}
