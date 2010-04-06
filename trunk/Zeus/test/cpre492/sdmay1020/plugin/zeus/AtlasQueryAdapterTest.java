package cpre492.sdmay1020.plugin.zeus;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import com.ensoftcorp.plugin.atlas.query.lang.IArtifact;
import com.ensoftcorp.plugin.atlas.query.lang.IArtifacts;

public class AtlasQueryAdapterTest {

	/**
	 * For this test we will create two IArtifacts.
	 * The first IArtifacts will have 2 items
	 * The second IArtifacts will have one item
	 * We will subtract the second set from the first
	 */
	@Test
	public void testMinus() {
	
		/*
		
		IArtifacts setWithTwoArtifacts = ArtifactFactory.createArtifacts();
		//need to add two items to this
		
		setWithTwoArtifacts.add(ArtifactFactory.createFunction("getbuf"));
		setWithTwoArtifacts.add(ArtifactFactory.createFunction("freebuf"));
		
		//System.out.println(setWithTwoArtifacts.size());
		
		Iterator<IArtifact> pointer =  setWithTwoArtifacts.iterator();
		
		
		while(pointer.hasNext())
		{
			IArtifact current = pointer.next();
			
			System.out.println(current.getName());
		}
		
		
		
		IArtifacts setWithOneArtifact =ArtifactFactory.createArtifacts();
		setWithOneArtifact.add(ArtifactFactory.createFunction("getbuf"));
		
		IArtifacts result = AtlasQueryAdapter.minus(setWithTwoArtifacts, setWithOneArtifact);
		
		System.out.println("hello " + result.size());
		
	pointer =  result.iterator();
		
		
		while(pointer.hasNext())
		{
			IArtifact current = pointer.next();
			
			System.out.println(current.getName());
		}
		
		*/
		
		//System.out.println(setWithTwoArtifacts.size());
		//System.out.println(setWithOneArtifact.size());
		
		
		//assertTrue(result.size() == 1);
		
		fail("Not yet implemented");
	}

	@Test
	public void testRunAndQuery() {
		
		
		
		IArtifacts input1 = ArtifactFactory.createArtifacts();
		IArtifacts input2= ArtifactFactory.createArtifacts();
	
		input1.add(ArtifactFactory.createFunction("getbuf"));
		input2.add(ArtifactFactory.createFunction("freebuf"));
		
		IArtifacts result = AtlasQueryAdapter.and(input1, input2);
		
		
		System.out.println(result.size());
		
		
		fail("Not yet implemented");
	}

	@Test
	public void testRunOrQuery() {
		fail("Not yet implemented");
	}

	@Test
	public void testRunDefQuery() {
		fail("Not yet implemented");
	}

	@Test
	public void testRunArgumentCastQuery() {
		fail("Not yet implemented");
	}

	@Test
	public void testRunArgumentQuery() {
		fail("Not yet implemented");
	}

	@Test
	public void testRunArtifactsQuery() {
		fail("Not yet implemented");
	}

	@Test
	public void testRunCalledByQuery() {
		fail("Not yet implemented");
	}

	@Test
	public void testRunCallGraphQuery() {
		fail("Not yet implemented");
	}

	@Test
	public void testRunCallQuery() {
		fail("Not yet implemented");
	}

	@Test
	public void testRunCastQuery() {
		fail("Not yet implemented");
	}

	@Test
	public void testRunFunctionDeclareQuery() {
		fail("Not yet implemented");
	}

	@Test
	public void testRunFunctionsRegExQuery() {
		fail("Not yet implemented");
	}

	@Test
	public void testRunLeavesQuery() {
		fail("Not yet implemented");
	}

	@Test
	public void testRunReadByQuery() {
		fail("Not yet implemented");
	}

	@Test
	public void testRunReadQuery() {
		fail("Not yet implemented");
	}

	@Test
	public void testRunReferencedByQuery() {
		fail("Not yet implemented");
	}

	@Test
	public void testRunReferenceQuery() {
		fail("Not yet implemented");
	}

	@Test
	public void testRunReverseCallGraphQuery() {
		fail("Not yet implemented");
	}

	@Test
	public void testRunRootsQuery() {
		fail("Not yet implemented");
	}

	@Test
	public void testRunTypesQuery() {
		fail("Not yet implemented");
	}

	@Test
	public void testRunVariablesQuery() {
		fail("Not yet implemented");
	}

	@Test
	public void testRunWriteQuery() {
		fail("Not yet implemented");
	}

	@Test
	public void testRunWrittenByQuery() {
		fail("Not yet implemented");
	}

}