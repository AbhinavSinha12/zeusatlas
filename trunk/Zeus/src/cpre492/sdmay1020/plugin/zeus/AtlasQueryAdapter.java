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
public class AtlasQueryAdapter {
	
	private QueryFactory qf = null;
	private IQueryState queryState = null;
	private IQueryFunctionSymbolTable qfst = null;
	
	public AtlasQueryAdapter(){
		qf = QueryFactory.instance;
		queryState = qf.createQueryState();
		qfst = qf.createQueryFunctionSymbolTable();
	}
	
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

	/**
	 * read(x): functions which read x where x is a set of variables, 
	 * types or functions (reads of function addresses are useful for finding 
	 * function pointer initialization)
	 * @return
	 */
	public IValue runReadQuery(IValue[] input ){
		
		// Set up the query function call in the Atlas query language
		IQueryFunction read = qfst.lookupSymbol(FUNCTION.READ);
			
		// make the Atlas query call #x = write(n);
		// where #x is 'result' and n is 'input'
		IValue result = read.execute(qfst, queryState, input);
		
		//TODO: cast result into funcitons and return functions
		return result;
		
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
	 * I would think that each call to a wrapper class will return an IValue
	 * 
	 * 
	 */
	public IValue runWriteQuery (IValue[] input){
		
		// Set up the query function call in the Atlas query language
		IQueryFunction write = qfst.lookupSymbol(FUNCTION.WRITE);
			
		// make the Atlas query call #x = write(n);
		// where #x is 'result' and n is 'input'
		IValue result = write.execute(qfst, queryState, input);
		
		//TODO: cast result into funcitons and return functions
		return result;
	}

	/*
	 * pretty much the same as WriteQuery except the result should be artifacts
	 * still need to work on return values do they need to be anything other then ivalues 
	 */
	public IValue runWrittenByQuery( IValue[] input ){
			
		//create the writtenby query function
		IQueryFunction writtenby = qfst.lookupSymbol(FUNCTION.WRITTENBY);
		
		//execute the function and save the results into the ivalue named results
		IValue results = writtenby.execute(qfst, queryState, input);
		
		
		return results;
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
