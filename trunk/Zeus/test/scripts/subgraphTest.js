//Creation of an IFunctionArtifact
var IFAGetBuf = af.createFunction("getbuf");
var IFAFreeBuf = af.createFunction("freebuf");

//Create an IArtifacts object
var IArtifacts = af.createArtifacts();
var IArtifacts2 = af.createArtifacts();


//Create an empty IArtifacts object
//We will use this to pas into showGraph
var empty = af.createArtifacts();

//Add the IFunctionArtirfact to the collection IArtifacts
IArtifacts.add(IFAGetBuf);
IArtifacts2.add(IFAFreeBuf);

//or them together into a base
var base = aq.or(IArtifacts, IArtifacts2);

//find the rcg of the base
var rcg = aq.rcg(base);

//find the roots of the base
var roots = aq.roots(rcg);

//manual input, will work on later
var functions = [];
functions[0] = aq.functions(af.createString("ds.*"));
functions[1] = aq.functions(af.createString("ls.*"));
functions[2] = aq.functions(af.createString("ib.*"));

//for loop to go threw array
for (x in functions)
{

var top = aq.and(roots, functions[x]);

//use refby on getbuff
//needs to make an array of types
//var types = epacket , dreq, freeblk

//Name of the graph
var name = "Graph Test";

//Pass the name and Results to the graph
//NOTE : we pass the name first, then pass the results as the root node,
//and finally pass the empty IArtifacts set to the showgraph
or.showGraph(name, top, base,empty, empty,empty,empty);
}
}


