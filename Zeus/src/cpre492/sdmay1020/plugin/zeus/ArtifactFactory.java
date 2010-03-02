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
	
	
	public static void showResult(IValue result) {
		if (result instanceof IArtifacts) {
			IArtifacts artifacts = (IArtifacts) result;
			
			StringBuilder s = new StringBuilder();
			
			for (IArtifact a : artifacts) {
				s.append(a.getName());
				s.append("\n");
			}
			
			System.out.print("Atlas Query Result\n\nArtifacts:\n" + s.toString());
			
		} else if (result instanceof IVariable) {
			// not really possible at this time - query language only returns artifacts
			IVariable v = (IVariable) result;
			System.out.print("Atlas Query Result\n\nVariable: " + v.getName());
			
		} else if (result instanceof IStringValue) {
			// not really possible at this time - query language only returns artifacts
			IStringValue s = (IStringValue) result;
			System.out.print("Atlas Query Result\n\nStringValue: " + s.getValue());
		}
	}
}
