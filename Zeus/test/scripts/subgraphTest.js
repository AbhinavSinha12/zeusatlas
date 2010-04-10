//Imports
	
//var Zeus = JavaImporter(Packages.cpre492.sdmay1020.plugin.zeus.ArtifactFactory, 
//						Packages.cpre492.sdmay1020.plugin.zeus.AtlasQueryAdapter,
//                      Packages.cpre492.sdmay1020.plugin.zeus.OutputResults );

with(Zeus){

//Creation of an IFunctionArtifact
var IFAGetBuf = ArtifactFactory.createFunction("getbuf");
var IFAFreeBuf = ArtifactFactory.createFunction("freebuf");

//Create an IArtifacts object
var IArtifacts = ArtifactFactory.createArtifacts();
var IArtifacts2 = ArtifactFactory.createArtifacts();


//Create an empty IArtifacts object
//We will use this to pas into showGraph
var empty = ArtifactFactory.createArtifacts();

//Add the IFunctionArtirfact to the collection IArtifacts
IArtifacts.add(IFAGetBuf);
IArtifacts2.add(IFAFreeBuf);



//create an IArtifactFunction that holds the result of "calledby(getbuf)"
var Results = AtlasQueryAdapter.leaves(IArtifacts);

//create an IArtifactFunction that holds the result of "calledby(getbuf)"
var Results2 = AtlasQueryAdapter.leaves(IArtifacts2);


//or them together
var Results3 = AtlasQueryAdapter.or(Results, Results2);


//use refby on getbuff
//needs to make an array of types
//var types = epacket , dreq, freeblk

//Name of the graph
var name = "Graph Test";

//Pass the name and Results to the graph
//NOTE : we pass the name first, then pass the results as the root node,
//and finally pass the empty IArtifacts set to the showgraph
OutputResults.showGraph(name, Results, empty,empty, empty,empty,empty);

}


