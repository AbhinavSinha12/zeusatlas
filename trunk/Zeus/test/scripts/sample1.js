var set = af.createArtifacts(); 
set.add(af.createFunction("dswrite")); 
var r1 = aq.runCalledByQuery(set);
af.showResult("Called By dswrite", r1);