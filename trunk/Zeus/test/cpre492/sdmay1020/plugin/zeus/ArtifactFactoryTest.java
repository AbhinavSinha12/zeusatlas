package cpre492.sdmay1020.plugin.zeus;

import static org.junit.Assert.*;

import java.util.*;
import org.junit.Test;
import junit.framework.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith (Parameterized.class)
public class ArtifactFactoryTest {

	
	//TODO: create new variables that are needed to run tests 
	// Variables used during test execution - defined in the constructor
	private double[] target=null;
	private int mode=-1;
	
	
	//TODO: set this up as needed
	//required constructor
	public ArtifactFactoryTest(double[] target, double[] mode){
		this.target=target;
		this.mode=(int)mode[0];
	}
	
	
	@Parameters
	public static Collection arrays(){
		double[][][]data = new double[][][] { 
			   {{5,2,3},{0}},
			   {{1,3,2},{1}}
		};
		return Arrays.asList(data);
	}
	
	@Test
	public void testCreateFunction() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateType() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateVarible() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateMacro() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateArtifacts() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateString() {
		fail("Not yet implemented");
	}

	@Test
	public void testShowResult() {
		fail("Not yet implemented");
	}

}
