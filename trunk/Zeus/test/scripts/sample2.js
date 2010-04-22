var headerArray = [];
headerArray[0] = af.createString("dir.h"); 
headerArray[1] = af.createString("lfile.h"); 
headerArray[2] = af.createString("disk.h"); 
headerArray[3] = af.createString("ether.h"); 
headerArray[4] = af.createString("net.h"); 
headerArray[5] = af.createString("proc.h"); 
headerArray[6] = af.createString("mem.h"); 

var defArray = [];	
var refArray = [];
var rcgArray = [];
var indRefArray = [];
var oaArray = [];
var andArray = [];
var indAndArray = [];

for(i=0;i<7;i++){
	defArray[i] = aq.def(headerArray[i]); 
	refArray[i] = aq.ref(defArray[i]); 
	rcgArray[i] = aq.rcg(refArray[i]); 
	indRefArray[i] = aq.minus(rcgArray[i], refArray[i]); 
	oaArray[i] = aq.minus(aq.calledby(rcgArray[i]), rcgArray[i]); 
}
var count = 0;
for(i=0;i<7;i++){
	for(j=i+1;j<7;j++){
		andArray[count] = aq.and(refArray[i], refArray[j]);
		indAndArray[count] = aq.and(indRefArray[i], indRefArray[j]);
		count++;
	}
}

indAndArray[count] = aq.and(indAndArray[16], indAndArray[18]);

var intersectInd = aq.and(indRefArray);
var intersectIndDirLfileANDDisk = aq.and(indRefArray[0], indRefArray[1], indRefArray[2]);
var intersectOA = aq.and(oaArray);
var intersect5OA = aq.and(oaArray[0], oaArray[1], oaArray[2], oaArray[3], oaArray[4]);
var intersect5Ind = aq.and(indRefArray[0], indRefArray[1], indRefArray[2], indRefArray[3], indRefArray[4]);

var read = af.createArtifacts();
var write = af.createArtifacts();
read.add(af.createFunction("read"));
write.add(af.createFunction("write"));
var callRead = aq.call(read);
var callWrite = aq.call(write);
var callReadANDWrite = aq.and(callRead, callWrite);
var calledByRead = aq.calledby(read);
var calledByWrite = aq.calledby(write);

var readString = af.createString(".*read");
var writeSting = af.createString(".*write");
var readFns = aq.functions(readString);
var writeFns = aq.functions(writeSting);


or.toTextFile("Header File Array", headerArray);
or.toTextFile("Def Array", defArray);
or.toTextFile("Ref Array", refArray);
or.toTextFile("RCG Array", rcgArray);
or.toTextFile("IndRef Array", indRefArray);
or.toTextFile("OA Array", oaArray);
or.toTextFile("AND Array", andArray);
or.toTextFile("IndAND Array", indAndArray);
or.toTextFile("Ind Intersection", intersectInd);
or.toTextFile("Ind dir, lfile, and disk Intersection", intersectIndDirLfileANDDisk);
or.toTextFile("OA Intersection", intersectOA);
or.toTextFile("OA 5 Intersection", intersect5OA);
or.toTextFile("Ind 5 Intersection", intersect5Ind);
or.toTextFile("Call Read", callRead);
or.toTextFile("Call Write", callWrite);
or.toTextFile("Call Read and Write", callReadANDWrite);
or.toTextFile("CalledBy Read", calledByRead);
or.toTextFile("CalledBy Write", calledByWrite);
or.toTextFile("Read Functions", readFns);
or.toTextFile("Write Functions", writeFns);



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
