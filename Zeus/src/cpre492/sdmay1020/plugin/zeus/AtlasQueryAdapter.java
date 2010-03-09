package cpre492.sdmay1020.plugin.zeus;

import com.ensoftcorp.plugin.atlas.query.api.QueryFactory;
import com.ensoftcorp.plugin.atlas.query.lang.IArtifacts;
import com.ensoftcorp.plugin.atlas.query.lang.IQueryFunction;
import com.ensoftcorp.plugin.atlas.query.lang.IQueryFunctionSymbolTable;
import com.ensoftcorp.plugin.atlas.query.lang.IQueryState;
import com.ensoftcorp.plugin.atlas.query.lang.IValue;
import com.ensoftcorp.plugin.atlas.query.lang.IQueryFunctionSymbolTable.FUNCTION;


/**
 * 
 * @author Kristina Gervais
 * @author Alex Kharbush
 *
 */
public class AtlasQueryAdapter {
	
	private static QueryFactory qf = QueryFactory.instance;
	private static IQueryState queryState = qf.createQueryState();
	private static IQueryFunctionSymbolTable qfst = qf.createQueryFunctionSymbolTable();
	
	// private constructor prevents instantiating this object
	private AtlasQueryAdapter(){}
	
	/**
	 * Returns the artifact set resulting after the subtraction of the two input artifact sets
	 * 		input1 - input2
	 * 
	 * @param input1 - the set from which to subtract
	 * @param input2 - the set to subtract
	 * @return the artifact set resulting from input1 - input2
	 * 
	 * @author Alex Kharbush
	 * 
	 */
	public static IArtifacts runMinusQuery(IValue input1, IValue input2)
	{
		//Set up the query function call in the Atlas query language
		IQueryFunction MINUS = qfst.lookupSymbol(FUNCTION.MINUS);
			
		
		//Setup the IValue array
		IValue[] submit = new IValue[2];
		
		submit[0] = input1;
		submit[1] = input2;
		
		//This is where the call to atlas is actually made, we will pass in the submit IValue array and get back an IValue
		return (IArtifacts)MINUS.execute(qfst, queryState, submit);
	}
	
	/**
	 * This wrapper Function will take the "and" of two queries
	 * 
	 * @param input1 the starting query, this will be added to the second query
	 * @param input2 the added query, this will be added to the first query
	 * @return result the result of the and query of input1 and input2 in IArtifacts form
	 * 
	 * @author Alex Kharbush
	 * 
	 */
	public static IArtifacts runAndQuery(IValue input1, IValue input2)
	{
		//Set up the query function call in the Atlas query language
		IQueryFunction AND = qfst.lookupSymbol(FUNCTION.AND);
			
		//Setup the IValue array
		IValue[] submit = new IValue[2];
		
		//Add the elements to the array
		submit[0] = input1;
		submit[1] = input2;
		
		//This is where the call to atlas is actually made, we will pass in the submit IValue array and get back an IValue
		return  (IArtifacts)AND.execute(qfst, queryState, submit);
		
	}
	
	/**
	 * This wrapper Function will take the "or" of two queries
	 * 
	 * @param input1 The first section of a query to be or'd
	 * @param input2 The second section of a query to be or'd
	 * @return result the result of the or query in IArtifacts form
	 * 
	 * @param Alex Kharbush
	 * 
	 */
	public static IArtifacts runOrQuery (IValue input1, IValue input2)
	{
		// Set up the query function call in the Atlas query language
		IQueryFunction OR = qfst.lookupSymbol(FUNCTION.OR);
			
		//Setup the IValue array
		IValue[] submit = new IValue[2];
		
		//Add the input elements to the array
		submit[0] = input1;
		submit[1] = input2;
		
		//This is where the call to atlas is actually made, we will pass in the submit IValue array and get back an IValue
		return (IArtifacts)OR.execute(qfst, queryState, submit);
	}
	
	
	/**
	 * 
	 * This wrapper function will return the artifacts which are defined in path(e.g. artifacts defined in a header file),
	 * path is an IValue representing part of a file path, e.g. "includes/disk.h"
	 * 
	 * @param inputPath - an IValue representing the file path
	 * @return result - IArtifacts that result from the query
	 * 
	 * @author Alex Kharbush
	 * 
	 */
	
	
	public static IArtifacts runDefQuery (IValue input)
	{
		// Set up the query function call in the Atlas query language
		IQueryFunction def = qfst.lookupSymbol(FUNCTION.DEF);
			
		//Create an IValue
		IValue[] helper =  new IValue[1];
		helper[0]= input;
			
		//Return the casted IValue into an IArtifact object
		return  (IArtifacts) def.execute(qfst, queryState, helper);
	
		
	}
	
	/**
	 * 
	 * 
	 * Returns an array containing the constants of this enum type, in the order they are declared.
	 * This method may be used to iterate over the constants as follows:
	 * 
	 * eg for (IQueryFunctionSymbolTable.FUNCTION c : IQueryFunctionSymbolTable.FUNCTION.values())
	 * System.out.println(c);
	 * 
	 * @param - input, an IValue that will have its arguments cast
	 * 
	 * @return "an array containing the constants of this enum type, in the order they are declared"
	 *  from the API
	 * 
	 * @author Alex Kharbush
	 */
	public static IArtifacts runArgumentCastQuery(IValue input){
		// Set up the query function call in the Atlas query language
		IQueryFunction argCast = qfst.lookupSymbol(FUNCTION.ARGCAST);
			
		IValue [] submit = new IValue[1];
		
		submit[0] = input;
		
		return (IArtifacts) argCast.execute(qfst, queryState, submit);

	}

	/**
	 * 
	 * Functions which pass x as an argument to another function, x is a set of types
	 * 
	 * @param Input - An IValue that represents a set of types 
	 * @return IArtifacts that are the functions that pass input as an argument
	 * 
	 * @author Alex Kharbush
	 * 
	 */
	public static IArtifacts runArgumentQuery(IValue input ){
		// Set up the query function call in the Atlas query language
		IQueryFunction arg = qfst.lookupSymbol(FUNCTION.ARG);
			
		//Set up the input array for arg.execute
		IValue[] helper =  new IValue[1];
		helper[0]= input;
		
		// will this be able to handle multiple functions that are returned
		return  (IArtifacts)arg.execute(qfst, queryState, helper);
		
	
	}


	
	/**
	 * 
	 * Filters artifacts by kind (e.g. return only variables from the set x)
	 * 
	 * @param input - an IValue that represents a set of artifact that will have its artifacts filtered out
	 * @return IArtifacts - result of the filtering
	 * 
	 * @author Alex Kharbush
	 */
	public static IArtifacts runArtifactsQuery(IValue input){
		// Set up the query function call in the Atlas query language
		IQueryFunction art = qfst.lookupSymbol(FUNCTION.ARTIFACTS);
		
		IValue[] helper =  new IValue[1];
		helper[0]= input;
		
		//This is where the art command is actually made, we will pass the submit IValue[] and get back a set of IArtifacts
		return  (IArtifacts) art.execute(qfst, queryState, helper );
	}


	/**
	 * 
	 * Functions which are called by x where  x is a set of functions
	 * 
	 * @param input - An IValue that represents a set of functions
	 * @return result - An IArtifacts that represents the functions that are called by the input
	 * 
	 * @Aurthor Alex Kharbush
	 * 
	 */
	public static IArtifacts runCalledByQuery(IValue input){
		// Set up the query function call in the Atlas query language
		IQueryFunction callby = qfst.lookupSymbol(FUNCTION.CALLEDBY);
			
		//Setup the IValue array
		IValue[] submit = new IValue[1];
		
		//Fill the first value of the IValue array with an IFunctionArtifact
		submit[0] =  (IValue) input;
	
		//Send submit to atlas
		return  (IArtifacts)callby.execute(qfst, queryState, submit);
		
	}

	/**
	 * 
	 * Functions which are in the call graph starting at function x
	 * 
	 * @param input - An IValue that represents the starting function of a call graph
	 * @return result - An IArtifacts object that represents the functions in the call graph starting at the input function/IValue
	 *  
	 * @author Alex Kharbush
	 * 
	 */
	public static IArtifacts runCallGraphQuery(IValue input){
		// Set up the query function call in the Atlas query language
		IQueryFunction cg = qfst.lookupSymbol(FUNCTION.CG);
			
		//Setup the IValue array
		IValue[] submit = new IValue[1];
		
		//Fill the first value of the IValue array with an IFunctionArtifact
		submit[0] =  (IValue) input;
		
		return  (IArtifacts)cg.execute(qfst, queryState, submit);
	
	}

	/**
	 * 
	 * Functions which call x where x is a set of functions
	 * 
	 * @param input - An IValue that represents a set of functions
	 * @return result - IArtifacts that represents functions that call the input set of functions
	 * 
	 * @author Alex Kharbush
	 * 
	 */
	public static IArtifacts runCallQuery(IValue input){
		// Set up the query function call in the Atlas query language
		IQueryFunction call = qfst.lookupSymbol(FUNCTION.CALL);
			
		
		//Setup the IValue array
		IValue[] submit = new IValue[1];
		
		//Fill the first value of the IValue array with an IFunctionArtifact
		submit[0] =  (IValue) input;
		
		return (IArtifacts)call.execute(qfst, queryState, submit);
		
	}
	
	
	/**
	 * 
	 *   x is a set of types.  cast(myType) will return the functions which contain a cast to "myType". 
	 *    More specifically, input is IArtifacts containing ITypeArtifact, 
	 *    returning IArtifacts containing IFunctionAritfact.
	 * 
	 * @param input - An IValue that represents a set of types
	 * @return result - an IArtifacts object that is a cast of the input
	 * 
	 * @author Alex Kharbush
	 * 
	 */
	public static IArtifacts runCastQuery(IValue input){
		// Set up the query function call in the Atlas query language
		IQueryFunction cast = qfst.lookupSymbol(FUNCTION.CAST);
			
		IValue[] helper =  new IValue[1];
		helper[0]= input;
		
		
		// make the Atlas query call #x = write(n);
		// where #x is 'result' and n is 'input'
		return  (IArtifacts) cast.execute(qfst, queryState, helper);
		
	}


	/**
	 * 
	 * This function is not listed in the Atlas documentation
	 * 
	 * @param input - An IValue
	 * @return an IArtifacts object
	 * 
	 * @author Alex Kharbush
	 * 
	 */
	public static IArtifacts runFunctionDeclareQuery(IValue input){
		IQueryFunction FunctionDeclare = qfst.lookupSymbol(FUNCTION.FDECL);
		
		IValue[] helper =  new IValue[1];
		helper[0]= input;
		
		return (IArtifacts) FunctionDeclare.execute(qfst, queryState, helper);


	}


	/**
	 * 
	 * Functions whose names match the given regular expression  (from the set of all defined functions)
	 * 
	 * @return result - An IArtifacts object that is a set of functions whos names mactch the given input
	 * @param input - An IValue that represents a reguler expression. 
	 * 
	 * @author Alex Kharbush
	 * 
	 */
	public static IArtifacts runFunctionsRegExQuery(IValue input){
		// Set up the query function call in the Atlas query language
		IQueryFunction FunctionsRegEX = qfst.lookupSymbol(FUNCTION.FUNCTIONS);
		IValue[] helper =  new IValue[1];
		helper[0]= input;
		
		return  (IArtifacts)FunctionsRegEX.execute(qfst, queryState, helper);

	}

	/**
	 * 
	 *  x is a set of root functions.  For each root function, it calculates the forward call graph. 
	 *   For each call graph, it collects the leaves (functions which do not call another function). 
	 *   The answer is the set of all such leaves.  Input and output are both IArtifacts containing
	 *   IFunctionArtifact.
	 *     
	 * @param input - An Ivalue that represents a set of root functions
	 * @return An IArtifacts object that contains a set of leave functions
	 * 
	 * @author Alex Kharbush
	 * 
	 */
	public static IArtifacts runLeavesQuery(IValue input){
		
		// Set up the query function call in the Atlas query language
		IQueryFunction Leaves = qfst.lookupSymbol(FUNCTION.LEAVES);
		
		IValue[] helper =  new IValue[1];
		helper[0]= input;
		
		return (IArtifacts) Leaves.execute(qfst, queryState, helper);
		
		
	}

	/**
	 * 
	 * Artifacts which are read by x where x is a set of functions
	 * 
	 * @return result - IArtifacts wish is filled with artifact that are read by the input
	 * @param input - An IValue that represents a set of functions
	 * 
	 * @author Alex Kharbush
	 * 
	 */
	public static IArtifacts runReadByQuery(IValue input){
		// Set up the query function call in the Atlas query language
		IQueryFunction readby = qfst.lookupSymbol(FUNCTION.READBY);
		
		IValue[] helper =  new IValue[1];
		helper[0]= input;
		
		return  (IArtifacts)(readby.execute(qfst, queryState, helper));
		
	}

	/**
	 * 
	 * read(x): functions which read x where x is a set of variables, 
	 * types or functions (reads of function addresses are useful for finding 
	 * function pointer initialization)
	 * 
	 * @param input - An IValue that represents a set of varibles
	 * @return An IArtifacts object that represents a set of functions that read the input
	 * 
	 * @author Alex Kharbush
	 * 
	 */
	public static IArtifacts runReadQuery(IValue input ){
		
		// Set up the query function call in the Atlas query language
		IQueryFunction read = qfst.lookupSymbol(FUNCTION.READ);
			
		//Setup the IValue array
		IValue[] submit = new IValue[1];
		
	
		submit[0] =  input;
		
		return (IArtifacts) read.execute(qfst, queryState, submit);
	
	}

	/**
	 * 
	 * Artifacts which are read or written by x, equivalent to "readby(x) or write(x)"
	 * 
	 * @param input - An IValue that is a set of artifacts
	 * @return An IArtifacts object that represents artifacts that are read or written by the input
	 * 
	 * @author Alex Kharbush
	 * 
	 */
	public static IArtifacts runReferencedByQuery(IValue input){
		// Set up the query function call in the Atlas query language
		IQueryFunction refby = qfst.lookupSymbol(FUNCTION.REFBY);
			
		IValue[] submit = new IValue[1];
		
		
		submit[0] =  (IValue)input;
		return (IArtifacts)refby.execute(qfst, queryState, submit);
		

	}

	/**
	 * 
	 * Functions which read or write x, equivalent to "read(x) or write(x)"
	 * 
	 * @param input - An IValue that represents a set of artifacts
	 * @return result - An IArtifacts object that represents functions that read or write the to/from the input.
	 *  
	 * @author Alex Kharbush
	 * 
	 */
	public static IArtifacts runReferenceQuery(IValue input){
		// Set up the query function call in the Atlas query language
		IQueryFunction ref = qfst.lookupSymbol(FUNCTION.REF);

		//Setup the IValue array
		IValue[] submit = new IValue[1];
		
		
		submit[0] =  (IValue)input;
		
		
		return (IArtifacts)ref.execute(qfst, queryState, submit);
		

		}

	/**
	 * 
	 * Functions which are in the reverse call graph starting at function x
	 * 
	 * @param input - An IValue that is the starting function for the reverse call graph
	 * @return An IArtifacts object that is a set of functions in the reverse call graph of the input 
	 * 
	 * @author Alex Kharbush
	 * 
	 */
	public static IArtifacts runReverseCallGraphQuery(IValue input){
		// Set up the query function call in the Atlas query language
		IQueryFunction rgc = qfst.lookupSymbol(FUNCTION.RCG);
			
		//Setup the IValue array
		IValue[] submit = new IValue[1];
		
		
		submit[0] =  (IValue)input;
		
		
		return  (IArtifacts)rgc.execute(qfst, queryState, submit);
		

	}

	/**
	 * 
	 * Functions which are roots of the reverse call graph starting at leaf function x leaves(x)
	 * functions which are leaves of the call graph starting at root function x
	 * 
	 * @param input - An IValue that represents a set of leaf functions
	 * @return Result - An IArtifacts object that are the roots of the reverse call graph starting at the root
	 * 
	 * @author Alex Kharbush
	 * 
	 */
	public static IArtifacts  runRootsQuery(IValue input){
		// Set up the query function call in the Atlas query language
		IQueryFunction roots = qfst.lookupSymbol(FUNCTION.ROOTS);
			
		
		//Setup the IValue array
		IValue[] submit = new IValue[1];
		submit[0] =  (IValue)input;
		
		
		
		return (IArtifacts) roots.execute(qfst, queryState, submit);
	
	}

	/**
	 * 
	 * Filters artifacts by kind 
	 * 
	 * @param input - An IValue that represents a set of artifacts
	 * @return An IArtifact object that are types of the input.
	 * 
	 * @author Alex Kharbush
	 * 
	 */
	public static IArtifacts runTypesQuery(IValue input){
		
		// Set up the query function call in the Atlas query language
		IQueryFunction types = qfst.lookupSymbol(FUNCTION.TYPES);
			
		
		//Setup the IValue array
		IValue[] submit = new IValue[1];
		
		
		submit[0] =  (IValue)input;
		
		//return statement
		return  (IArtifacts) types.execute(qfst, queryState, submit);
		

	}



	/**
	 * 
	 * Filters artifacts by kind 
	 * 
	 * @param input - An IValue that is a set
	 * @return An IArtifacts object that represents all the variables in the set
	 * 
	 * @author Alex Kharbush
	 */
	public static IArtifacts runVariablesQuery(IArtifacts input){
		// Set up the query function call in the Atlas query language
		IQueryFunction varibles = qfst.lookupSymbol(FUNCTION.VARIABLES);
			
		//Setup the IValue array
		IValue[] submit = new IValue[1];
		
		
		submit[0] =  (IValue)input;
		
		return  (IArtifacts) varibles.execute(qfst, queryState, submit);
	
	}

	
	/**
	 * 
	 * Functions which write x where x is a set of variables or type
	 *
	 * @param input - An IValue that represents a set of variables or types
	 * @return result - An IArtifacts object the represents a set of functions
	 * 
	 * @author Alex Kharbush
	 * 
	 */
	public static IArtifacts runWriteQuery (IValue input){
		
		// Set up the query function call in the Atlas query language
		IQueryFunction write = qfst.lookupSymbol(FUNCTION.WRITE);
		
		//Set up the IVAlue array to send to write.execute
		IValue[] submit = new IValue[1];
		
		//Set the value in the array to the input
		submit[0]= (IValue)input;
		
		return  (IArtifacts)write.execute(qfst, queryState, submit);
		

	}

	/**
	 * 
	 * Artifacts which are written by x where x is a set of functions
	 * 
	 * @param input - An IValue that represents a set of functions
	 * @return result - IArtifacts that is a set of artifacts.
	 * 
	 * @author Alex Kharbush
	 *  
	 */
	public static IArtifacts runWrittenByQuery( IValue input ){
			
		//create the writtenby query function
		IQueryFunction writtenby = qfst.lookupSymbol(FUNCTION.WRITTENBY);
		
		IValue[] submit = new IValue[1];
		
		submit[0]= (IValue)input;
		
		//execute the function and save the results into the ivalue named results
		return  (IArtifacts)writtenby.execute(qfst, queryState, submit);
		
		
		
	}
	
}
