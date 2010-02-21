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
	
/**
 * This Wrapper Function will take the subtraction of two queries
 * @param input1 the first input of IValue
 * @param input2 the second input of IValue
 * @return result the result of the query in IValue form
 * @author Alex Kharbush
 * 
 */
	public IValue runMinusQuery(IValue input1, IValue input2)
	{
		//Set up the query function call in the Atlas query language
		IQueryFunction MINUS = qfst.lookupSymbol(FUNCTION.MINUS);
			
		
		//Setup the IValue array
		IValue[] submit = new IValue[2];
		
		submit[0] = input1;
		submit[1] = input2;
		
		//This is where the call to atlas is actually made, we will pass in the submit IValue array and get back an IValue
		IValue result = MINUS.execute(qfst, queryState, submit);
		
		//Return statement
		return result;
	}
	
	/**
	 * This wrapper Function will take the "and" of two queries
	 * @param input1 the first input of a query
	 * @param input2 the second input of a query
	 * @return result the result of the query in IValue form
	 * @param Alex Kharbush
	 */
	public IValue runAndQuery(IValue input1, IValue input2)
	{
		//Set up the query function call in the Atlas query language
		IQueryFunction AND = qfst.lookupSymbol(FUNCTION.AND);
			
		//Setup the IValue array
		IValue[] submit = new IValue[2];
		
		submit[0] = input1;
		submit[1] = input2;
		
		//This is where the call to atlas is actually made, we will pass in the submit IValue array and get back an IValue
		IValue result = AND.execute(qfst, queryState, submit);
		
		//Return statement
		return result;
	}
	
	/**
	 * This wrapper Function will take the "or" of two queries
	 * @param input1 the first input of a query
	 * @param input2 the second input of a query
	 * @return result the result of the query in IValue form
	 * @param Alex Kharbush
	 */
	public IValue runOrQuery (IValue input1, IValue input2)
	{
		// Set up the query function call in the Atlas query language
		IQueryFunction OR = qfst.lookupSymbol(FUNCTION.OR);
			
		//Setup the IValue array
		IValue[] submit = new IValue[2];
		
		submit[0] = input1;
		submit[1] = input2;
		
		//This is where the call to atlas is actually made, we will pass in the submit IValue array and get back an IValue
		IValue result = OR.execute(qfst, queryState, submit);
		
		//Return statement
		return result;
	}
	
	
	/**
	 * This wrapper function will return the artifacts which are defined in path(e.g. artifacts defined in a header file),
	 * path is a string representing part of a file path, e.g. "includes/disk.h"
	 * @param inputPath - a String representing part of a file path
	 * @return result - IArtifacts that result from the query
	 * @author Alex Kharbush
	 */
	
	public IArtifacts runDefQuery (String inputPath)
	{
		// Set up the query function call in the Atlas query language
		IQueryFunction def = qfst.lookupSymbol(FUNCTION.DEF);
			
		//Setup the IValue array
		IValue[] submit = new IValue[1];
		
		//TODO need to setup a new IStringValue
		//submit[0] =  new IStringValue(inputPath);
		
		//TODO is this a correct cast from IValue to IArtifacts
		//IArtifacts result = (IArtifacts) def.execute(qfst, queryState, submit);
		
		//Return statement
		//return result;
		
		return null;
	}
	
	
	
	/**
	 * //TODO : this needs to be reviewed
	 */
	public IValue runArgumentCastQuery(IValue[] input){
		// Set up the query function call in the Atlas query language
		IQueryFunction argCast = qfst.lookupSymbol(FUNCTION.ARGCAST);
			
		// make the Atlas query call #x = write(n);
		// where #x is 'result' and n is 'input'
		IValue result = argCast.execute(qfst, queryState, input);
		
		//TODO: cast result into funcitons and return functions
		return result;
	}

	/**
	 * functions which pass x as an argument to another function x is a set of types
	 * @return
	 */
	public IValue runArgumentQuery(IValue[] input ){
		// Set up the query function call in the Atlas query language
		IQueryFunction arg = qfst.lookupSymbol(FUNCTION.ARG);
			
		// make the Atlas query call #x = write(n);
		// where #x is 'result' and n is 'input'
		IValue result = arg.execute(qfst, queryState, input);
		
		//TODO: cast result into funcitons and return functions
		return result;
	}


	//TODO : this needs to be reviewed
	public IValue runArtifactsQuery(IValue[] input){
		// Set up the query function call in the Atlas query language
		IQueryFunction art = qfst.lookupSymbol(FUNCTION.ARTIFACTS);
			
		// make the Atlas query call #x = write(n);
		// where #x is 'result' and n is 'input'
		IValue result = art.execute(qfst, queryState, input);
		
		//TODO: cast result into funcitons and return functions
		return result;
	}

	/**
	 *  artifacts (functions,variables,types)  whose names match the given regular expression 
	 *  (equivalent to the union of the last three)
	 * @return
	 */
	//TODO : check is this is correct qfst.lookupSymbol
	
	public IValue runArtifactsRegQuery(IValue[] input){
		// Set up the query function call in the Atlas query language
		IQueryFunction Artifacts = qfst.lookupSymbol(FUNCTION.ARTIFACTS);
			
		// make the Atlas query call #x = write(n);
		// where #x is 'result' and n is 'input'
		IValue result = Artifacts.execute(qfst, queryState, input);
		
		//TODO: cast result into funcitons and return functions
		return result;
	}

	/**
	 * functions which are called by x where  x is a set of functions
	 * @return
	 */
	public IValue runCalledByQuery(IValue[] input){
		// Set up the query function call in the Atlas query language
		IQueryFunction callby = qfst.lookupSymbol(FUNCTION.CALLEDBY);
			
		// make the Atlas query call #x = write(n);
		// where #x is 'result' and n is 'input'
		IValue result = callby.execute(qfst, queryState, input);
		
		//TODO: cast result into funcitons and return functions
		return result;
	}

	/**
	 * functions which are in the call graph starting at function x
	 * @return
	 */
	public IValue runCallGraphQuery(IValue[] input){
		// Set up the query function call in the Atlas query language
		IQueryFunction cg = qfst.lookupSymbol(FUNCTION.CG);
			
		// make the Atlas query call #x = write(n);
		// where #x is 'result' and n is 'input'
		IValue result = cg.execute(qfst, queryState, input);
		
		//TODO: cast result into funcitons and return functions
		return result;
	}

	/**
	 *  * functions which call x where x is a set of functions
	 * @return
	 */
	public IValue runCallQuery(IValue[] input){
		// Set up the query function call in the Atlas query language
		IQueryFunction call = qfst.lookupSymbol(FUNCTION.CALL);
			
		// make the Atlas query call #x = write(n);
		// where #x is 'result' and n is 'input'
		IValue result = call.execute(qfst, queryState, input);
		
		//TODO: cast result into funcitons and return functions
		return result;
	}
	
	
	//TODO : this needs to be commented
	public IValue runCastQuery(IValue[] input){
		// Set up the query function call in the Atlas query language
		IQueryFunction cast = qfst.lookupSymbol(FUNCTION.CAST);
			
		// make the Atlas query call #x = write(n);
		// where #x is 'result' and n is 'input'
		IValue result = cast.execute(qfst, queryState, input);
		
		//TODO: cast result into funcitons and return functions
		return result;
	}


	//TODO : this needs to be reviewed
	static Collection<Function> runFunctionDeclareQuery(){
		return null;
	}

	/**
	 * declared function whose names match the given regular expression 
	 * (useful because some functions are declared but not defined)
	 * @return
	 */
	
	//TODO : I dont think this is the correct lookup for FDECL regular expression
	public IValue runFunctionDeclareRegExQuery(IValue[] input){
		// Set up the query function call in the Atlas query language
		IQueryFunction fdecl = qfst.lookupSymbol(FUNCTION.FDECL);
			
		// make the Atlas query call #x = write(n);
		// where #x is 'result' and n is 'input'
		IValue result = fdecl.execute(qfst, queryState, input);
		
		//TODO: cast result into funcitons and return functions
		return result;
	}
	
	//TODO Needs to be reviewed
	static Collection<Function> runFunctionsCastQuery(){
		return null;
	}

	/**
	 * functions whose names match the given regular expression  (from the set of all defined functions)
	 * @return
	 */
	public IValue runFunctionsRegExQuery(IValue[] input){
		// Set up the query function call in the Atlas query language
		IQueryFunction readby = qfst.lookupSymbol(FUNCTION.READBY);
			
		// make the Atlas query call #x = write(n);
		// where #x is 'result' and n is 'input'
		IValue result = readby.execute(qfst, queryState, input);
		
		//TODO: cast result into funcitons and return functions
		return result;
	}

	//TODO Needs to be reviewed
	static Collection<Function> runLeavesQuery(){
		return null;
	}

	/**
	 * artifacts which are read by x where x is a set of functions
	 * @return
	 */
	public IValue runReadByQuery(IValue[] input){
		// Set up the query function call in the Atlas query language
		IQueryFunction readby = qfst.lookupSymbol(FUNCTION.READBY);
			
		// make the Atlas query call #x = write(n);
		// where #x is 'result' and n is 'input'
		IValue result = readby.execute(qfst, queryState, input);
		
		//TODO: cast result into funcitons and return functions
		return result;
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

	/**
	 * artifacts which are read or written by x, equivalent to "readby(x) or write(x)"
	 * @return
	 */
	public IValue runReferencedByQuery(IValue[] input){
		// Set up the query function call in the Atlas query language
		IQueryFunction refby = qfst.lookupSymbol(FUNCTION.REFBY);
			
		// make the Atlas query call #x = write(n);
		// where #x is 'result' and n is 'input'
		IValue result = refby.execute(qfst, queryState, input);
		
		//TODO: cast result into funcitons and return functions
		return result;
	}

	/**
	 * functions which read or write x, equivalent to "read(x) or write(x)"
	 * @return
	 */
	public IValue runReferenceQuery(IValue[] input){
		// Set up the query function call in the Atlas query language
		IQueryFunction ref = qfst.lookupSymbol(FUNCTION.REF);
			
		// make the Atlas query call #x = write(n);
		// where #x is 'result' and n is 'input'
		IValue result = ref.execute(qfst, queryState, input);
		
		//TODO: cast result into funcitons and return functions
		return result;	
		}

	/**
	 * functions which are in the reverse call graph starting at function x
	 * @return
	 */
	//TODO: make sure this is rcg
	public IValue runReverseCallGraphQuery(IValue[] input){
		// Set up the query function call in the Atlas query language
		IQueryFunction rgc = qfst.lookupSymbol(FUNCTION.RCG);
			
		// make the Atlas query call #x = write(n);
		// where #x is 'result' and n is 'input'
		IValue result = rgc.execute(qfst, queryState, input);
		
		//TODO: cast result into funcitons and return functions
		return result;
	}

	/**
	 * functions which are roots of the reverse call graph starting at leaf function x leaves(x)   
	 *  functions which are leaves of the call graph starting at root function x
	 * @return
	 */
	public IValue  runRootsQuery(IValue[] input){
		// Set up the query function call in the Atlas query language
		IQueryFunction roots = qfst.lookupSymbol(FUNCTION.ROOTS);
			
		// make the Atlas query call #x = write(n);
		// where #x is 'result' and n is 'input'
		IValue result = roots.execute(qfst, queryState, input);
		
		//TODO: cast result into funcitons and return functions
		return result;
	}

	/**
	 * filters artifacts by kind 
	 * @return
	 */
	public  IValue runTypesQuery(IValue[] input){
		
		// Set up the query function call in the Atlas query language
		IQueryFunction types = qfst.lookupSymbol(FUNCTION.TYPES);
			
		// make the Atlas query call #x = write(n);
		// where #x is 'result' and n is 'input'
		IValue result = types.execute(qfst, queryState, input);
		
		//TODO: cast result into funcitons and return functions
		return result;
	}

	/**
	 * types whose names match the given regular expression (from the set of all types)     
	 * @return
	 */
	public IValue runTypesRegExQuery(IValue[] input){
		// Set up the query function call in the Atlas query language
		IQueryFunction Types = qfst.lookupSymbol(FUNCTION.TYPES);
			
		// make the Atlas query call #x = write(n);
		// where #x is 'result' and n is 'input'
		IValue result = Types.execute(qfst, queryState, input);
		
		//TODO: cast result into funcitons and return functions
		return result;
	}

	/**
	 * filters artifacts by kind 
	 * @return
	 */
	public IValue runVariablesQuery(IValue[] input){
		// Set up the query function call in the Atlas query language
		IQueryFunction varibles = qfst.lookupSymbol(FUNCTION.VARIABLES);
			
		// make the Atlas query call #x = write(n);
		// where #x is 'result' and n is 'input'
		IValue result = varibles.execute(qfst, queryState, input);
		
		//TODO: cast result into funcitons and return functions
		return result;
	}

	/**
	 * variables whose names match the given regular expression (from the set of all variables)
	 * @return
	 */
	public IValue runVariablesRegExQuery(IValue[] input){
		// Set up the query function call in the Atlas query language
		IQueryFunction vars = qfst.lookupSymbol(FUNCTION.VARIABLES);
			
		// make the Atlas query call #x = write(n);
		// where #x is 'result' and n is 'input'
		IValue result = vars.execute(qfst, queryState, input);
		
		//TODO: cast result into funcitons and return functions
		return result;
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
