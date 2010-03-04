	
//var Zeus = JavaImporter(Packages.cpre492.sdmay1020.plugin.zeus.ArtifactFactory, 
//						Packages.cpre492.sdmay1020.plugin.zeus.AtlasQueryAdapter);

with(Zeus){

	var headerArray = [];
	headerArray[0] = ArtifactFactory.createString("dir.h"); 
	headerArray[1] = ArtifactFactory.createString("lfile.h"); 
	headerArray[2] = ArtifactFactory.createString("disk.h"); 
	headerArray[3] = ArtifactFactory.createString("ether.h"); 
	headerArray[4] = ArtifactFactory.createString("net.h"); 
	headerArray[5] = ArtifactFactory.createString("proc.h"); 
	headerArray[6] = ArtifactFactory.createString("mem.h"); 

	var refArray = [];	
	for(i=0;i<7;i++){
		refArray[i] = AtlasQueryAdapter.runReferenceQuery(headerArray[i]); 
	}
	
	var rcgArray = [];
	for(i=0;i<7;i++){
		rcgArray[i] = AtlasQueryAdapter.runRCGQuery(refArray[i]); 
	}
	
	var indRefArray = [];
	for(i=0;i<7;i++){
		indRefArray[i] = AtlasQueryAdapter.runMinusQuery(rcgArray[i], refArray[i]); 
	}
	
	var oaArray = [];
	for(i=0;i<7;i++){
		oaArray[i] = AtlasQueryAdapter.runMinusQuery(AtlasQueryAdapter.runCalledByQuery(rcgArray[i]), rcgArray[i]); 
	}
	
	var andArray = [];
	for(i=0;i<7;i++){
		for(j=i+1;j<7;j++){
			andArray = AtlasQueryAdapter.runAndQuery(refArray[i], refArray[j]);
	}
	for(i=0;i<7;i++){
		ArtifactFactory.showResult(headerArray[i]);
		ArtifactFactory.showResult(refArray[i]);
		ArtifactFactory.showResult(rcgArray[i]);
		ArtifactFactory.showResult(indRefArray[i]);
		ArtifactFactory.showResult(oaArray[i]);
		ArtifactFactory.showResult(andArray[i]);
	}
}


//#dirData = def(“dir.h”)
//#DrefDir = ref(#dirData)
//#dirRCG = rcg(#DrefDir)
//#IndRefDir = #dirRCG minus #DrefDir

//#lfileData = def(“lfile.h”)
//#DrefLfile = ref(#lfileData)
//#lfileRCG = rcg(#DrefLfile)
//#IndRefLfile = #lfileRCG minus #DrefLfile

//#diskData = def(“disk.h”)
//#DrefDisk = ref(#diskData)
//#diskRCG = rcg(#DrefDisk)
//#IndRefDisk = #diskRCG minus #DrefDisk

//#etherData = def(“ether.h”)
//#DrefEther = ref(#etherData)
//#etherRCG = rcg(#DrefEther)
//#IndRefEther = #etherRCG minus #DrefEther

//#netData = def(“net.h”)
//#DrefNet = ref(#netData)
//#netRCG = rcg(#DrefNet)
//#IndRefNet = #netRCG minus #DrefNet

//#procData = def(“proc.h”)
//#DrefProc = ref(#procData)
//#procRCG = rcg(#DrefProc)
//#IndRefProc = #procRCG minus #DrefProc

//#memData = def(“mem.h”)
//#DrefMem = ref(#memData)
//#memRCG = rcg(#DrefMem)
//#IndRefMem = #memRCG minus #DrefMem

//#OAdir = calledby(#dirRCG) minus #dirRCG 
//#OAlfile = calledby(#lfileRCG) minus #lfileRCG
//#OAdisk = calledby(#diskRCG) minus #diskRCG
//#OAether = calledby(#etherRCG) minus #etherRCG
//#OAnet = calledby(#netRCG) minus #netRCG
//#OAproc = calledby(#procRCG) minus #procRCG
//#memOfn = calledby(#memRCG) minus #memRCG

//#dirANDlfile = #DrefDir and #DrefLfile
//#dirANDdisk = #DrefDir and #DrefDisk
//#dirANDether = #DrefDir and #DrefEther
//#dirANDnet = #DrefDir and #DrefNet
//#dirANDproc = #DrefDir and #DrefProc
//#dirANDmem = #DrefDir and #DrefMem

//#lfileANDdisk = #DrefLfile and #DrefDisk
//#lfileANDether = #DrefLfile and #DrefEther
//#lfileANDnet = #DrefLfile and #DrefNet
//#lfileANDproc = #DrefLfile and #DrefProc
//#lfileANDmem = #DrefLfile and #DrefMem

//#diskANDether = #DrefDisk and #DrefEther
//#diskANDnet = #DrefDisk and #DrefNet
//#diskANDproc = #DrefDisk and #DrefProc
//#diskANDmem = #DrefDisk and #DrefMem

//#etherANDnet = #DrefEther and #DrefNet
//#etherANDproc = #DrefEther and #DrefProc
//#etherANDmem = #DrefEther and #DrefMem

//#netANDproc = #DrefNet and #DrefProc
//#netANDmem = #DrefNet and #DrefMem

//#procANDmem = #DrefProc and #DrefMem
