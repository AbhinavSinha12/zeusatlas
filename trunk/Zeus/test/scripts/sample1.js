var set = af.createArtifacts(); 
set.add(af.createFunction("dswrite")); 
var r1 = aq.calledby(set);
out.toTextFile("Called By dswrite", r1);