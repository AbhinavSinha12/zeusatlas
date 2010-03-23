package cpre492.sdmay1020.plugin.zeus;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ensoftcorp.plugin.atlas.query.api.QueryFactory;
import com.ensoftcorp.plugin.atlas.query.lang.IArtifacts;
import com.ensoftcorp.plugin.atlas.query.lang.IQueryFunction;
import com.ensoftcorp.plugin.atlas.query.lang.IQueryFunctionSymbolTable;
import com.ensoftcorp.plugin.atlas.query.lang.IQueryState;
import com.ensoftcorp.plugin.atlas.query.lang.IStringValue;
import com.ensoftcorp.plugin.atlas.query.lang.IValue;
import com.ensoftcorp.plugin.atlas.query.lang.IQueryFunctionSymbolTable.FUNCTION;
import com.ensoftcorp.plugin.atlas.ui.api.GraphUI;

public class ZeusTest {

	@Test
	public void testZeus() {
		fail("Not yet implemented");
	}

	@Test
	public void testStartBundleContext() {
		fail("Not yet implemented");
	}

	@Test
	public void testStopBundleContext() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDefault() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetImageDescriptor() {
		fail("Not yet implemented");
	}
	
	/**
	 * This test will check to make sure we can send the results to graph gui.
	 */
	@Test
	public void testDrawGraph()
	{
		
		QueryFactory qf = QueryFactory.instance;
		IQueryState queryState = qf.createQueryState();
		IQueryFunctionSymbolTable qfst = qf.createQueryFunctionSymbolTable();
		
		
		IStringValue sv = qf.createStringValue("net.h");
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
		
		GraphUI.instance.showGraph("net.h", roots, leaves, omissions, highlights, simplify, center);
		
		
		fail("Not yet implemented");
	
	}

}
