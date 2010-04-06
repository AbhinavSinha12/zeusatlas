	
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

	var defArray = [];	
	var refArray = [];
	var rcgArray = [];
	var indRefArray = [];
	var oaArray = [];
	var andArray = [];
	var indAndArray = [];

	for(i=0;i<7;i++){
		defArray[i] = AtlasQueryAdapter.def(headerArray[i]); 
		refArray[i] = AtlasQueryAdapter.ref(defArray[i]); 
		rcgArray[i] = AtlasQueryAdapter.rcg(refArray[i]); 
		indRefArray[i] = AtlasQueryAdapter.minus(rcgArray[i], refArray[i]); 
		oaArray[i] = AtlasQueryAdapter.minus(AtlasQueryAdapter.calledby(rcgArray[i]), rcgArray[i]); 
	}
	var count = 0;
	for(i=0;i<7;i++){
		for(j=i+1;j<7;j++){
			andArray[count] = AtlasQueryAdapter.and(refArray[i], refArray[j]);
			indAndArray[count] = AtlasQueryAdapter.and(indRefArray[i], indRefArray[j]);
			count++;
		}
	}

	indAndArray[count] = AtlasQueryAdapter.and(indAndArray[16], indAndArray[18]);
	
	var intersectInd = AtlasQueryAdapter.and(indRefArray);
	var intersectIndDirLfileANDDisk = AtlasQueryAdapter.and(indRefArray[0], indRefArray[1], indRefArray[2]);
	var intersectOA = AtlasQueryAdapter.and(oaArray);
	var intersect5OA = AtlasQueryAdapter.and(oaArray[0], oaArray[1], oaArray[2], oaArray[3], oaArray[4]);
	var intersect5Ind = AtlasQueryAdapter.and(indRefArray[0], indRefArray[1], indRefArray[2], indRefArray[3], indRefArray[4]);
	
	var read = ArtifactFactory.createArtifacts();
	var write = ArtifactFactory.createArtifacts();
	read.add(ArtifactFactory.createFunction("read"));
	write.add(ArtifactFactory.createFunction("write"));
	var callRead = AtlasQueryAdapter.call(read);
	var callWrite = AtlasQueryAdapter.call(write);
	var callReadANDWrite = AtlasQueryAdapter.and(callRead, callWrite);
	var calledByRead = AtlasQueryAdapter.calledby(read);
	var calledByWrite = AtlasQueryAdapter.calledby(write);

	var readString = ArtifactFactory.createString(".*read");
	var writeSting = ArtifactFactory.createString(".*write");
	var readFns = AtlasQueryAdapter.functions(readString);
	var writeFns = AtlasQueryAdapter.functions(writeSting);
	
	
	OutputResults.toTextFile("Header File Array", headerArray);
	OutputResults.toTextFile("Def Array", defArray);
	OutputResults.toTextFile("Ref Array", refArray);
	OutputResults.toTextFile("RCG Array", rcgArray);
	OutputResults.toTextFile("IndRef Array", indRefArray);
	OutputResults.toTextFile("OA Array", oaArray);
	OutputResults.toTextFile("AND Array", andArray);
    OutputResults.toTextFile("IndAND Array", indAndArray);
	OutputResults.toTextFile("Ind Intersection", intersectInd);
	OutputResults.toTextFile("Ind dir, lfile, and disk Intersection", intersectIndDirLfileANDDisk);
	OutputResults.toTextFile("OA Intersection", intersectOA);
	OutputResults.toTextFile("OA 5 Intersection", intersect5OA);
	OutputResults.toTextFile("Ind 5 Intersection", intersect5Ind);
	OutputResults.toTextFile("Call Read", callRead);
	OutputResults.toTextFile("Call Write", callWrite);
	OutputResults.toTextFile("Call Read and Write", callReadANDWrite);
	OutputResults.toTextFile("CalledBy Read", calledByRead);
	OutputResults.toTextFile("CalledBy Write", calledByWrite);
	OutputResults.toTextFile("Read Functions", readFns);
	OutputResults.toTextFile("Write Functions", writeFns);

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


//////////////////////////////////////////////////
// #dirAND2lfile = #IndRefDir and #IndRefLfile
// #dirAND2disk = #IndRefDir and #IndRefDisk
// #dirAND2ether = #IndRefDir and #IndRefEther
// #dirAND2net = #IndRefDir and #IndRefNet
// #dirAND2proc = #IndRefDir and #IndRefProc
// #dirAND2mem = #IndRefDir and #IndRefMem

// #lfileAND2disk = #IndRefLfile and #IndRefDisk
// #lfileAND2ether = #IndRefLfile and #IndRefEther
// #lfileAND2net = #IndRefLfile and #IndRefNet
// #lfileAND2proc = #IndRefLfile and #IndRefProc
// #lfileAND2mem = #IndRefLfile and #IndRefMem

// #diskAND2ether = #IndRefDisk and #IndRefEther
// #diskAND2net = #IndRefDisk and #IndRefNet
// #diskAND2proc = #IndRefDisk and #IndRefProc
// #diskAND2mem = #IndRefDisk and #IndRefMem

// #etherAND2net = #IndRefEther and #IndRefNet
// #etherAND2proc = #IndRefEther and #IndRefProc
// #etherAND2mem = #IndRefEther and #IndRefMem

// #netAND2proc = #IndRefNet and #IndRefProc
// #netAND2mem = #IndRefNet and #IndRefMem

// #procAND2mem = #IndRefProc and #IndRefMem

// #etherAND2netAND2proc = #etherAND2proc and #netAND2proc

// #results = 5, results = {login, main, shell, x_uptime, x_who} 

// #intersectAll = #IndRefDir and #IndRefLfile and #IndRefDisk and #IndRefEther and #IndRefNet  and #IndRefProc and #IndRefMem

// #results = 0 

// #intersectProcANDmem = #IndRefProc and #IndRefMem

// #results = 17

// #intersectDirANDlfileANDdisk = #IndRefDir and #IndRefLfile and #IndRefDisk 

// #results = 2  lfread and lfwrite

// #IntersectAllOfn = #OAdir and #OAlfile and #OAdisk and #OAether and #OAnet and #OAproc and #memOfn 

// # results = 2 Results =  {read and write}

// #IntersectFiveOfn = #OAdir and #OAlfile and #OAdisk and #OAether and #OAnet 


// # results = 4 Results = {read and write wait signal}

// #IntersetFiveDfn = #IndRefDir and #IndRefLfile and #IndRefDisk and #IndRefEther and #IndRefNet 

// #results = 0 

// #callREAD = call(read) 

// #results = 19

// #callWRITE = call(write)

// #results = 37

// #callREADandWRITE = call(read) and call(write)

// #results = 10

// #calledbyREAD = calledby(read) 

// #results = 0

// #calledbyWRITE = calledby(write) 

// #results = 0

// #ReadFns = functions(".*read")

// #results = 7 Results = {dgread dsread ethread lfread rfread ttyread read}

// #WriteFns = functions(".*write")

// #results = 7 Results = {dgwrite dswrite ethwrite lfwrite rfwrite ttywrite write}
