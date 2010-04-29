//Creation of an IFunctionArtifact
var IFunctionArtifact = af.createFunction("getbuf");

//Create an IArtifacts object
var IArtifacts = af.createArtifacts();

//Create an empty IArtifacts object
//We will use this to pas into showGraph
var empty = af.createArtifacts();

//Add the IFunctionArtirfact to the collection IArtifacts
IArtifacts.add(IFunctionArtifact);

//create an IArtifactFunction that holds the result of "calledby(getbuf)"
var Results = aq.calledby(IArtifacts);

//Name of the graph
var name = "Graph Test";

//Pass the name and Results to the graph
//NOTE : we pass the name first, then pass the results as the root node,
//and finally pass the empty IArtifacts set to the showgraph
out.showGraph(name, Results, empty,empty, empty,empty,empty);