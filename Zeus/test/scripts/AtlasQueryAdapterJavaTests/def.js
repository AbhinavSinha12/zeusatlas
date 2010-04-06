//Imports
	
//var Zeus = JavaImporter(Packages.cpre492.sdmay1020.plugin.zeus.ArtifactFactory, 
//						Packages.cpre492.sdmay1020.plugin.zeus.AtlasQueryAdapter,
//                      Packages.cpre492.sdmay1020.plugin.zeus.OutputResults );

with(Zeus){

//Creation of an IFunctionArtifact
var IFunctionArtifact = ArtifactFactory.createFunction("getbuf");

//Create an IArtifacts object
var IArtifacts = ArtifactFactory.createArtifacts();

//Create an empty IArtifacts object
//We will use this to pas into showGraph
var empty = ArtifactFactory.createArtifacts();

//Add the IFunctionArtirfact to the collection IArtifacts
IArtifacts.add(IFunctionArtifact);

//create an IArtifactFunction that holds the result of "calledby(getbuf)"
var Results = AtlasQueryAdapter.def(IArtifacts);

//Name of the graph
var name = "Graph Test";

//Pass the name and Results to the graph
//NOTE : we pass the name first, then pass the results as the root node,
//and finally pass the empty IArtifacts set to the showgraph
OutputResults.showGraph(name, Results, empty,empty, empty,empty,empty);

}


