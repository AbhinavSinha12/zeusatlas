package cpre492.sdmay1020.plugin.zeus;

import java.util.*;

import com.ensoftcorp.plugin.atlas.query.api.QueryFactory;
import com.ensoftcorp.plugin.atlas.query.lang.IArtifact;
import com.ensoftcorp.plugin.atlas.query.lang.IArtifacts;
import com.ensoftcorp.plugin.atlas.query.lang.IFunctionArtifact;
import com.ensoftcorp.plugin.atlas.query.lang.IQueryFunction;
import com.ensoftcorp.plugin.atlas.query.lang.IQueryFunctionSymbolTable;
import com.ensoftcorp.plugin.atlas.query.lang.IQueryState;
import com.ensoftcorp.plugin.atlas.query.lang.IStringValue;
import com.ensoftcorp.plugin.atlas.query.lang.IValue;
import com.ensoftcorp.plugin.atlas.query.lang.IVariable;
import com.ensoftcorp.plugin.atlas.query.lang.IQueryFunctionSymbolTable.FUNCTION;
import com.ensoftcorp.plugin.atlas.ui.api.GraphUI;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;


/**
 * 
 * @author Kristina Gervais
 *
 */
public abstract class AtlasQueryAdapter {
	
	static Collection<Artifact> runArgumentCastQuery(){
		return null;
	}

	static Collection<Function> runArgumentQuery(){
		return null;
	}

	static Collection<Function> runArgumentsQuery(){
		return null;
	}

	static Collection<Artifact> runArtifactsQuery(){
		return null;
	}

	static Collection<Artifact> runArtifactsRegQuery(){
		return null;
	}

	static Collection<Function> runCalledByQuery(){
		return null;
	}

	static Collection<Function> runCallGraphQuery(){
		return null;
	}

	static Collection<Function> runCallQuery(){
		return null;
	}

	static Collection<Artifact> runCastQuery(){
		return null;
	}

	static Collection<Artifact> runDefinitionQuery(){
		return null;
	}

	static Collection<Function> runFunctionDeclareQuery(){
		return null;
	}

	
	static Collection<Function> runFunctionDeclareRegExQuery(){
		return null;
	}
	static Collection<Function> runFunctionsCastQuery(){
		return null;
	}

	
	static Collection<Function> runFunctionsRegExQuery(){
		return null;
	}

	static Collection<Function> runLeavesQuery(){
		return null;
	}

	static Collection<Artifact> runReadByQuery(){
		return null;
	}

	static Collection<Function> runReadQuery(){
		return null;
	}

	static Collection<Artifact> runReferencedByQuery(){
		return null;
	}

	static Collection<Function> runReferenceQuery(){
		return null;
	}

	static Collection<Function> runReverseCallGraphQuery(){
		return null;
	}

	static Collection<Function> runRootsQuery(){
		return null;
	}

	static Collection<Type> runTypesQuery(){
		return null;
	}

	static Collection<Type> runTypesRegExQuery(){
		return null;
	}

	static Collection<Variable> runVariablesQuery(){
		return null;
	}

	
	static Collection<Variable> runVariablesRegExQuery(){
		return null;
	}

	/*
	 * functions which write x where x is a set of variables or types
	 * So I took jons code and pasted it in. 
	 * 
	 * I would think that each call to a wrapper class will return an IValue
	 * 
	 * 
	 */
	public IValue runWriteQuery(QueryFactory qf, IQueryState queryState,
			IQueryFunctionSymbolTable qfst , String input){
		
		/*
		 * I have no idea what this does but I think it creates a function to look up
		 * so I'll say that we want to look up the function that we pass to the runWriteQuery
		 * in the String parameter labeled input.
		 * 
		*/
		IFunctionArtifact function = qf.createFunctionArtifact(input);
		
		/*
		 * I have no idea what this is for but jon uses it
		 */
		IArtifacts artifacts = qf.createArtifacts();
		
		/*
		 * So we add the function to the artifacts.....
		 */
		artifacts.add(function);
		
		/*
		 * I think this is where we actually set up the function call
		 */
		IQueryFunction write = qfst.lookupSymbol(FUNCTION.WRITE);
		
		/*
		 * When we "execute" the IQueryFunction we pass it the artifacts that are passed
		 * in. We will then get the result back as an IValue.
		 */
		IValue result = write.execute(qfst, queryState, new IValue[] {artifacts});
		
		
		return result;
	}

	static Collection<Artifact> runWrittenByQuery(){
		return null;
	}

	/********************* Sample Code from ATLAS-SAMPLE-CLIENT SampleAction.java *******************/
	/*
		public void run(IAction action) {

			try {
				
				QueryFactory qf = QueryFactory.instance;
				IQueryState queryState = qf.createQueryState();
				IQueryFunctionSymbolTable qfst = qf.createQueryFunctionSymbolTable();
				
//				callsToFreebuf(qf, queryState, qfst);

				
				String [] headerFiles = new String[] {
					"net.h",
					"ether.h",
					"disk.h",
					"arp.h",
					"bufpool.h",
					"mem.h",
					"sem.h"
				};
				
				for (String headerFile : headerFiles) {
					displayHeaderFileRelationships(qf, queryState, qfst, headerFile);
				}
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
//				MessageDialog.openError(window.getShell(), "Error", "Unexpected error while running query...");
			}

		}

		private void callsToFreebuf(QueryFactory qf, IQueryState queryState,
				IQueryFunctionSymbolTable qfst) {
			
			// call(f:freebuf)
			IFunctionArtifact function = qf.createFunctionArtifact("freebuf");
			IArtifacts artifacts = qf.createArtifacts();
			artifacts.add(function);
			IQueryFunction call = qfst.lookupSymbol(FUNCTION.CALL);
			IValue result = call.execute(qfst, queryState, new IValue[] {artifacts});
			
			showResult(result);
		}

		private void displayHeaderFileRelationships(QueryFactory qf,
				IQueryState queryState, IQueryFunctionSymbolTable qfst,
				String headerFile) {
			
			// roughly equivalent to:
			// #x = ref(def(<headerFile>))
			// graph(leaves=#x, highlights=#x)
			
			IStringValue sv = qf.createStringValue(headerFile);
			IQueryFunction def = qfst.lookupSymbol(FUNCTION.DEF);
			IQueryFunction ref = qfst.lookupSymbol(FUNCTION.REF);
			IValue result2 = def.execute(qfst, queryState, new IValue[] {sv});
			IValue result3 = ref.execute(qfst, queryState, new IValue[] {result2});
			
			
			IArtifacts roots = QueryFactory.instance.createArtifacts();
			IArtifacts leaves = QueryFactory.instance.createArtifacts();
			IArtifacts omissions = QueryFactory.instance.createArtifacts();
			IArtifacts highlights = QueryFactory.instance.createArtifacts();
			IArtifacts simplify = QueryFactory.instance.createArtifacts();
			IArtifacts center = QueryFactory.instance.createArtifacts();
			
			leaves = (IArtifacts) result3;
			highlights = (IArtifacts) result3;
			
			GraphUI.instance.showGraph(headerFile, roots, leaves, omissions, highlights, simplify, center);
		}

		private void showResult(IValue result) {
			if (result instanceof IArtifacts) {
				IArtifacts artifacts = (IArtifacts) result;
				
				StringBuilder s = new StringBuilder();
				
				for (IArtifact a : artifacts) {
					s.append(a.getName());
					s.append("\n");
				}
				
//				MessageDialog.openInformation(window.getShell(), "Atlas Query Result", "Artifacts:\n" + s.toString());
				
			} else if (result instanceof IVariable) {
				// not really possible at this time - query language only returns artifacts
				IVariable v = (IVariable) result;
//				MessageDialog.openInformation(window.getShell(), "Atlas Query Result", "Variable: " + v.getName());
				
			} else if (result instanceof IStringValue) {
				// not really possible at this time - query language only returns artifacts
				IStringValue s = (IStringValue) result;
//				MessageDialog.openInformation(window.getShell(), "Atlas Query Result", "StringValue: " + s.getValue());
			}
		}
*/		
/********************* Sample Code from ATLAS-SAMPLE-CLIENT SampleAction.java *******************/

	
}
