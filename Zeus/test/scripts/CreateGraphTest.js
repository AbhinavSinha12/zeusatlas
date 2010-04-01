	
//var Zeus = JavaImporter(Packages.cpre492.sdmay1020.plugin.zeus.ArtifactFactory, 
//						Packages.cpre492.sdmay1020.plugin.zeus.AtlasQueryAdapter,
//                      Packages.cpre492.sdmay1020.plugin.zeus.OutputResults );

with(Zeus){

var graphme = ArtifactFactory.createFunction("getbuf");


var stuff = ArtifactFactory.createArtifacts();
var empty = ArtifactFactory.createArtifacts();
stuff.add(graphme);


var graphmept2 = AtlasQueryAdapter.calledby(stuff);

var name = "testme";

OutputResults.showGraph(name, graphmept2, empty,empty, empty,empty,empty);

}


