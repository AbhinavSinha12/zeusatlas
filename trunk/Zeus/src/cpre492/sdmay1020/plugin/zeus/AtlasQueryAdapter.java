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
import com.ensoftcorp.plugin.atlas.query.lang.ITypeArtifact;
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
	public IArtifacts runMinusQuery(IValue input1, IValue input2)
	{
		//Set up the query function call in the Atlas query language
		IQueryFunction MINUS = qfst.lookupSymbol(FUNCTION.MINUS);
			
		
		//Setup the IValue array
		IValue[] submit = new IValue[2];
		
		submit[0] = input1;
		submit[1] = input2;
		
		//This is where the call to atlas is actually made, we will pass in the submit IValue array and get back an IValue
		IArtifacts result = (IArtifacts)MINUS.execute(qfst, queryState, submit);
		
		//Return statement
		return result;
	}
	
	/**
	 * This wrapper Function will take the "and" of two queries
	 * @param input1 the first input of a query
	 * @param input2 the second input of a query
	 * @return result the result of the query in IValue form
	 * @author Alex Kharbush
	 */
	public IArtifacts runAndQuery(IValue input1, IValue input2)
	{
		//Set up the query function call in the Atlas query language
		IQueryFunction AND = qfst.lookupSymbol(FUNCTION.AND);
			
		//Setup the IValue array
		IValue[] submit = new IValue[2];
		
		submit[0] = input1;
		submit[1] = input2;
		
		//This is where the call to atlas is actually made, we will pass in the submit IValue array and get back an IValue
		IArtifacts result = (IArtifacts)AND.execute(qfst, queryState, submit);
		
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
	public IArtifacts runOrQuery (IValue input1, IValue input2)
	{
		// Set up the query function call in the Atlas query language
		IQueryFunction OR = qfst.lookupSymbol(FUNCTION.OR);
			
		//Setup the IValue array
		IValue[] submit = new IValue[2];
		
		submit[0] = input1;
		submit[1] = input2;
		
		//This is where the call to atlas is actually made, we will pass in the submit IValue array and get back an IValue
		IArtifacts result = (IArtifacts)OR.execute(qfst, queryState, submit);
		
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
	
	public IArtifacts runDefQuery (IValue input)
	{
		// Set up the query function call in the Atlas query language
		IQueryFunction def = qfst.lookupSymbol(FUNCTION.DEF);
			
		//Create an IValue
		IValue[] helper =  new IValue[1];
		helper[0]= input;
			
		IArtifacts result = (IArtifacts) def.execute(qfst, queryState, helper);
		
		//Return statement
		return result;
		
	}
	
	/**
	 * //TODO : this needs to be reviewed
	 * 
	 * Returns an array containing the constants of this enum type, in the order they are declared.
	 * This method may be used to iterate over the constants as follows:
	 * 
	 * eg for (IQueryFunctionSymbolTable.FUNCTION c : IQueryFunctionSymbolTable.FUNCTION.values())
    System.out.println(c);
    
	 * @return "an array containing the constants of this enum type, in the order they are declared"
	 *  from the API
	 * 
	 * @author Alex Kharbush
	 */
	public IArtifacts runArgumentCastQuery(IValue input){
		// Set up the query function call in the Atlas query language
		IQueryFunction argCast = qfst.lookupSymbol(FUNCTION.ARGCAST);
			
		IValue [] submit = new IValue[1];
		
		submit[0] = input;
		
		IArtifacts result =(IArtifacts) argCast.execute(qfst, queryState, submit);
		
	
		return result;
	}

	/**
	 * functions which pass x as an argument to another function, x is a set of types
	 * @param Input - Iartifacts
	 * @return Functions called result
	 * @author Alex Kharbush
	 */
	public IArtifacts runArgumentQuery(IValue input ){
		// Set up the query function call in the Atlas query language
		IQueryFunction arg = qfst.lookupSymbol(FUNCTION.ARG);
			

		IValue[] helper =  new IValue[1];
		helper[0]= input;
		
		// will this be able to handle multiple functions that are returned
		IArtifacts result=  (IArtifacts)arg.execute(qfst, queryState, helper);
		
		//Return Result
		return result;
	}


	
	/**
	 * filters artifacts by kind (e.g. return only variables from the set x)
	 * @param IArtifact - input
	 * @return IArtifact - result
	 * @author Alex Kharbush
	 */
	public IArtifacts runArtifactsQuery(IValue input){
		// Set up the query function call in the Atlas query language
		IQueryFunction art = qfst.lookupSymbol(FUNCTION.ARTIFACTS);
		
		IValue[] helper =  new IValue[1];
		helper[0]= input;
		//This is where the art command is actually made, we will pass the submit IValue[] and get back a set of IArtifacts
		IArtifacts result = (IArtifacts) art.execute(qfst, queryState, helper );
		
		//Return Result
		return result;
	}

	/**
	 *  artifacts (functions,variables,types)  whose names match the given regular expression 
	 *  (equivalent to the union of the last three)
	 *  @param String  - input
	 * @return IArtifacts - result
	 * 
	 * @author Alex Kharbush
	 */
	
	public IArtifacts runArtifactsRegQuery(IValue input){
		// Set up the query function call in the Atlas query language
		IQueryFunction Artifacts = qfst.lookupSymbol(FUNCTION.ARTIFACTS);
		
		IValue[] helper =  new IValue[1];
		helper[0]= input;
		
	
		
		//This is where the artifacts command is sent to atlas, we pass submit[] to it
		IArtifacts result = (IArtifacts)Artifacts.execute(qfst, queryState,helper );
		
		//Return result
		return result;
	}

	/**
	 * functions which are called by x where  x is a set of functions
	 * @param input - IFunctionArtifact
	 * @return result - IFunctionArtifact
	 * @Aurthor Alex Kharbush
	 */
	public IArtifacts runCalledByQuery(IValue input){
		// Set up the query function call in the Atlas query language
		IQueryFunction callby = qfst.lookupSymbol(FUNCTION.CALLEDBY);
			
		//Setup the IValue array
		IValue[] submit = new IValue[1];
		
		//Fill the first value of the IValue array with an IFunctionArtifact
		submit[0] =  (IValue) input;
		
	
		//Send submit to atlas
		IArtifacts result = (IArtifacts)callby.execute(qfst, queryState, submit);
		
		//Return Result
		return result;
	}

	/**
	 * functions which are in the call graph starting at function x
	 * @return IFunctionArtifact - result
	 * @param IFunctionArtiface - input
	 * @author Alex Kharbush
	 */
	public IArtifacts runCallGraphQuery(IValue input){
		// Set up the query function call in the Atlas query language
		IQueryFunction cg = qfst.lookupSymbol(FUNCTION.CG);
			
		//Setup the IValue array
		IValue[] submit = new IValue[1];
		
		//Fill the first value of the IValue array with an IFunctionArtifact
		submit[0] =  (IValue) input;
		
		IArtifacts result = (IArtifacts)cg.execute(qfst, queryState, submit);
		
		//Return Result
		return result;
	}

	/**
	 *  * functions which call x where x is a set of functions
	 * @return result - IFunctionArtifact
	 * @param input - IFunctionArtifact
	 * @author Alex Kharbush
	 * 
	 */
	public IArtifacts runCallQuery(IValue input){
		// Set up the query function call in the Atlas query language
		IQueryFunction call = qfst.lookupSymbol(FUNCTION.CALL);
			
		
		//Setup the IValue array
		IValue[] submit = new IValue[1];
		
		//Fill the first value of the IValue array with an IFunctionArtifact
		submit[0] =  (IValue) input;
		
		IArtifacts result = (IArtifacts)call.execute(qfst, queryState, submit);
		
		//Return Result
		return result;
	}
	
	
	/**
	 *   x is a set of types.  cast(myType) will return the functions which contain a cast to "myType". 
	 *    More specifically, input is IArtifacts containing ITypeArtifact, 
	 *    returning IArtifacts containing IFunctionAritfact.
	 * @param input ZIArtifacts
	 * @return result - ZIArtifacts
	 * @author Alex Kharbush
	 */
	public IArtifacts runCastQuery(IValue input){
		// Set up the query function call in the Atlas query language
		IQueryFunction cast = qfst.lookupSymbol(FUNCTION.CAST);
			
		IValue[] helper =  new IValue[1];
		helper[0]= input;
		
		
		// make the Atlas query call #x = write(n);
		// where #x is 'result' and n is 'input'
		IArtifacts result = (IArtifacts) cast.execute(qfst, queryState, helper);
		
		return result;
	}


	//TODO : this needs to be reviewed
	static IArtifacts runFunctionDeclareQuery(IValue input){
		return null;
	}

	/**
	 * declared function whose names match the given regular expression 
	 * (useful because some functions are declared but not defined)
	 * @return result - IFunctionArtifact
	 * @param input - String that is a regular expression
	 * @author Alex Kharbush
	 */
	
	public IArtifacts runFunctionDeclareRegExQuery(IValue input){
		// Set up the query function call in the Atlas query language
		IQueryFunction fdecl = qfst.lookupSymbol(FUNCTION.FDECL);
			
		IValue[] helper =  new IValue[1];
		helper[0]= input;
		
		
		IArtifacts result =(IArtifacts) fdecl.execute(qfst, queryState, helper);
		
		//Return Result
		return result;
	}
	
	//TODO Needs to be reviewed
	static IArtifacts runFunctionsCastQuery(IValue input){
		return null;
	}

	/**
	 * functions whose names match the given regular expression  (from the set of all defined functions)
	 * @return result - IFunctionArtifact
	 * @param input - String
	 * @author Alex Kharbush
	 */
	public IArtifacts runFunctionsRegExQuery(IValue input){
		// Set up the query function call in the Atlas query language
		IQueryFunction FunctionsRegEX = qfst.lookupSymbol(FUNCTION.FUNCTIONS);
		IValue[] helper =  new IValue[1];
		helper[0]= input;
		
		IArtifacts result = (IArtifacts)FunctionsRegEX.execute(qfst, queryState, helper);
		
		return result;
	}

	/**
	 *  x is a set of root functions.  For each root function, it calculates the forward call graph. 
	 *   For each call graph, it collects the leaves (functions which do not call another function). 
	 *    The answer is the set of all such leaves.  Input and output are both IArtifacts containing
	 *     IFunctionArtifact.
	 * @return
	 */
	public IArtifacts runLeavesQuery(IValue input){
		
		// Set up the query function call in the Atlas query language
		IQueryFunction Leaves = qfst.lookupSymbol(FUNCTION.LEAVES);
		
		IValue[] helper =  new IValue[1];
		helper[0]= input;
		
		IArtifacts result = (IArtifacts) Leaves.execute(qfst, queryState, helper);
	
		return result;
		
		
	}

	/**
	 * artifacts which are read by x where x is a set of functions
	 * @return result - IArtifact
	 * @param input - IFunctionArtifact
	 * @author Alex Kharbush
	 */
	public IArtifacts runReadByQuery(IValue input){
		// Set up the query function call in the Atlas query language
		IQueryFunction readby = qfst.lookupSymbol(FUNCTION.READBY);
		
		IValue[] helper =  new IValue[1];
		helper[0]= input;
		
		IArtifacts result = (IArtifacts)(readby.execute(qfst, queryState, helper));
		
		
		return result;
	}

	/**
	 * read(x): functions which read x where x is a set of variables, 
	 * types or functions (reads of function addresses are useful for finding 
	 * function pointer initialization)
	 * @return
	 */
	public IArtifacts runReadQuery(IValue input ){
		
		// Set up the query function call in the Atlas query language
		IQueryFunction read = qfst.lookupSymbol(FUNCTION.READ);
			
		//Setup the IValue array
		IValue[] submit = new IValue[1];
		
	
		submit[0] =  input;
		
		IArtifacts result =(IArtifacts) read.execute(qfst, queryState, submit);
		
		//Return Result
		return result;
		
	}

	/**
	 * artifacts which are read or written by x, equivalent to "readby(x) or write(x)"
	 * @return result - IArtifacts
	 * @param input - IArtifact
	 * @author Alex Kharbush
	 */
	public IArtifacts runReferencedByQuery(IValue input){
		// Set up the query function call in the Atlas query language
		IQueryFunction refby = qfst.lookupSymbol(FUNCTION.REFBY);
			
		IValue[] submit = new IValue[1];
		
		
		submit[0] =  (IValue)input;
		IArtifacts result = (IArtifacts)refby.execute(qfst, queryState, submit);
		
		//Return Result
		return result;
	}

	/**
	 * functions which read or write x, equivalent to "read(x) or write(x)"
	 * @return result - IFunctionArtifact
	 * @param input - IArtifact 
	 * @author Alex Kharbush
	 */
	public IArtifacts runReferenceQuery(IValue input){
		// Set up the query function call in the Atlas query language
		IQueryFunction ref = qfst.lookupSymbol(FUNCTION.REF);

		//Setup the IValue array
		IValue[] submit = new IValue[1];
		
		
		submit[0] =  (IValue)input;
		
		
		IArtifacts result = (IArtifacts)ref.execute(qfst, queryState, submit);
		
		//Return result
		return result;	
		}

	/**
	 * functions which are in the reverse call graph starting at function x
	 * @return
	 */
	public IArtifacts runReverseCallGraphQuery(IValue input){
		// Set up the query function call in the Atlas query language
		IQueryFunction rgc = qfst.lookupSymbol(FUNCTION.RCG);
			
		//Setup the IValue array
		IValue[] submit = new IValue[1];
		
		
		submit[0] =  (IValue)input;
		
		
		IArtifacts result = (IArtifacts)rgc.execute(qfst, queryState, submit);
		
		
		//Return Result
		return result;
	}

	/**
	 * functions which are roots of the reverse call graph starting at leaf function x leaves(x)   
	 *  functions which are leaves of the call graph starting at root function x
	 * @return Result - IFunctionArtifact
	 * @param input - IFunctionArtifact
	 * @author Alex Kharbush
	 */
	public IArtifacts  runRootsQuery(IValue input){
		// Set up the query function call in the Atlas query language
		IQueryFunction roots = qfst.lookupSymbol(FUNCTION.ROOTS);
			
		
		//Setup the IValue array
		IValue[] submit = new IValue[1];
		
		
		submit[0] =  (IValue)input;
		
		
		
		IArtifacts result =(IArtifacts) roots.execute(qfst, queryState, submit);
		
		//Return Results
		return result;
	}

	/**
	 * filters artifacts by kind 
	 * @return result - Types
	 * @param input - IArtifacts
	 * @author Alex Kharbush
	 */
	public  IArtifacts runTypesQuery(IValue input){
		
		// Set up the query function call in the Atlas query language
		IQueryFunction types = qfst.lookupSymbol(FUNCTION.TYPES);
			
		
		//Setup the IValue array
		IValue[] submit = new IValue[1];
		
		
		submit[0] =  (IValue)input;
		
		
		IArtifacts result = (IArtifacts) types.execute(qfst, queryState, submit);
		
		//Return Result
		return result;
	}



	/**
	 * filters artifacts by kind 
	 * @return result - IVarible
	 * @param input - IArtifacts
	 * @author Alex Kharbush
	 */
	public IArtifacts runVariablesQuery(IArtifacts input){
		// Set up the query function call in the Atlas query language
		IQueryFunction varibles = qfst.lookupSymbol(FUNCTION.VARIABLES);
			
		//Setup the IValue array
		IValue[] submit = new IValue[1];
		
		
		submit[0] =  (IValue)input;
		
		IArtifacts result = (IArtifacts) varibles.execute(qfst, queryState, submit);
		
		//Return Result
		return result;
	}

	/**
	 * variables whose names match the given regular expression (from the set of all variables)
	 * @return result - IVarible
	 * @param input - String input representing a regular expression
	 * @author Alex Kharbush
	 */
	public IArtifacts runVariablesRegExQuery(IValue input){
		// Set up the query function call in the Atlas query language
		IQueryFunction vars = qfst.lookupSymbol(FUNCTION.VARIABLES);
			
		IValue[] helper =  new IValue[1];
		helper[0]= input;
		
		IArtifacts result = (IArtifacts)vars.execute(qfst, queryState, helper);
		
		
		return result;
	}

	/**
	 * @param input - IArtifact
	 * @return result - IFunctionArtiface
	 * 
	 * functions which write x where x is a set of variables or types
	 * I would think that each call to a wrapper class will return an IValue
	 * 
	 * @author Alex Kharbush
	 */
	public IArtifacts runWriteQuery (IValue input){
		
		// Set up the query function call in the Atlas query language
		IQueryFunction write = qfst.lookupSymbol(FUNCTION.WRITE);
		
		//Set up the IVAlue array to send to write.execute
		IValue[] submit = new IValue[1];
		
		//Set the value in the array to the input
		submit[0]= (IValue)input;
		
		//
		IArtifacts result = (IArtifacts)write.execute(qfst, queryState, submit);
		
		//Return the Result
		return result;
	}

	/**
	 * artifacts which are written by x where x is a set of functions
	 * 
	 * @param input - IfunctionArtifact
	 * @return result - IArtifacts
	 * 
	 * @author Alex Kharbush
	 * 
	 * pretty much the same as WriteQuery except the result should be artifacts
	 * still need to work on return values do they need to be anything other then ivalues 
	 */
	public IArtifacts runWrittenByQuery( IValue input ){
			
		//create the writtenby query function
		IQueryFunction writtenby = qfst.lookupSymbol(FUNCTION.WRITTENBY);
		
		IValue[] submit = new IValue[1];
		
		submit[0]= (IValue)input;
		
		//execute the function and save the results into the ivalue named results
		IArtifacts results = (IArtifacts)writtenby.execute(qfst, queryState, submit);
		
		
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
