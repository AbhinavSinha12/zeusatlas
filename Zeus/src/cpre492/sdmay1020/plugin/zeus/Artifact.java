package cpre492.sdmay1020.plugin.zeus;

import com.ensoftcorp.plugin.atlas.query.lang.IValue;

public abstract class Artifact {

	private String name;

	/**
	 * 
	 * @return Returns the IVAlue of the object
	 * Used for passing into the Execute commands of Atlas
	 * @author Alex Kharbush
	 */
	public abstract IValue returnIValue();
	
	public Artifact(){
		
	}
	
	
}
