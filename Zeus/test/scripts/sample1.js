	
//var Zeus = JavaImporter(Packages.cpre492.sdmay1020.plugin.zeus.ArtifactFactory, 
//						Packages.cpre492.sdmay1020.plugin.zeus.AtlasQueryAdapter);

with(Zeus){

	var set = ArtifactFactory.createArtifacts(); 
	set.add(ArtifactFactory.createFunction("dswrite")); 
	var r1 = AtlasQueryAdapter.runCalledByQuery(set);
	ArtifactFactory.showResult("Called By dswrite", r1);

}
