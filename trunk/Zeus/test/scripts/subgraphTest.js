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

//or them together into a base
var base = AtlasQueryAdapter.or(IArtifacts, IArtifacts2);

//find the rcg of the base
var rcg = AtlasQueryAdapter.rcg(base);

//find the roots of the base
var roots = AtlasQueryAdapter.roots(rcg);

//manual input, will work on later
var functions = new Array();
functions[0] = AtlasQueryAdapter.functions("ds.*");
functions[1] = AtlasQueryAdapter.functions("ls.*");
functions[2] = AtlasQueryAdapter.functions("ib.*");

//for loop to go threw array
for (x in functions)
{

var dstop = AtlasQueryAdapter.and(roots, functions[x]);

//use refby on getbuff
//needs to make an array of types
//var types = epacket , dreq, freeblk

//Name of the graph
var name = "Graph Test";

//Pass the name and Results to the graph
//NOTE : we pass the name first, then pass the results as the root node,
//and finally pass the empty IArtifacts set to the showgraph
OutputResults.showGraph(name, dstop, base,empty, empty,empty,empty);
}
}


