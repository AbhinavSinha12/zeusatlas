package cpre492.sdmay1020.plugin.zeus;

import java.util.ArrayList;

import com.ensoftcorp.plugin.atlas.query.api.QueryFactory;
import com.ensoftcorp.plugin.atlas.query.lang.IQueryFunctionSymbolTable;
import com.ensoftcorp.plugin.atlas.query.lang.IQueryState;
import com.ensoftcorp.plugin.atlas.query.lang.IValue;

/**
 * Our version of an IValue Class
 * This will be used for creating IValues
 * Mainly used to create IValues out of strings
 * @author Alex Kharbush
 *
 */
public class ZIValue {

	
	private QueryFactory qf = null;
	private IQueryState queryState = null;
	private IQueryFunctionSymbolTable qfst = null;
	
	
	
	
	
	IValue CurrentIValue;
	
	ArrayList <IValue> CurrentIValueArray;
	
	
	public ZIValue()
	{
		
		qf = QueryFactory.instance;
		queryState = qf.createQueryState();
		qfst = qf.createQueryFunctionSymbolTable();
		
		CurrentIValue = null;
		
		CurrentIValueArray = new ArrayList<IValue>();
		
	}
	
	/**
	 * A specific constructor that will create an IValue out of a sting input
	 * @param input - A string Value
	 * @author Alex Kharbush
	 */
	public ZIValue(String input)
	{
		
		
		CurrentIValue = qf.createStringValue(input);
		
		
		CurrentIValueArray.add(CurrentIValue);
	}
	
	/**
	 * Return the current IValue
	 * @return - CurrentIValue
	 * @author Alex Kharbush
	 */
	public IValue returnIValue()
	{
	return CurrentIValue;	
	}
	
	/**
	 * Return an IValue Array
	 * @return CurrentIValueArray
	 * @author Alex Kharbush
	 */
	public IValue[]  returnIValueArray()
	{
		//TODO: have tina look over this method
		return   (IValue[]) CurrentIValueArray.toArray();
	}
	
}
