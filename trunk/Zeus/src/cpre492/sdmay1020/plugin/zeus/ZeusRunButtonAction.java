package cpre492.sdmay1020.plugin.zeus;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.ImporterTopLevel;
import org.mozilla.javascript.Scriptable;


public class ZeusRunButtonAction implements IWorkbenchWindowActionDelegate {

	private IWorkbenchWindow window;
	
	/*Import String for all the classes within Zeus that will be called by the input script
	 * enabling the script author to use 'with(Zeus){ ... } instead of importing all the class individually */
	private String zeusImport = "var Zeus = JavaImporter(Packages.cpre492.sdmay1020.plugin.zeus.ArtifactFactory, Packages.cpre492.sdmay1020.plugin.zeus.AtlasQueryAdapter);";
	
	/** 
	 * The constructor
	 */
	public ZeusRunButtonAction(){
		
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IWorkbenchWindow window) {
		this.window = window;
	}

	@Override
	public void run(IAction action) {
		//TODO: get scriptString from file system or eclipse editor
		System.out.println(getScript());
		
		//scriptString: The script, as a single string
		String scriptString = zeusImport + "with(Zeus){var headerArray = [];" +
		 "headerArray[0] = ArtifactFactory.createString(\"dir.h\"); " +
		 "headerArray[1] = ArtifactFactory.createString(\"lfile.h\"); " +
		 "headerArray[2] = ArtifactFactory.createString(\"disk.h\"); " +
		 "headerArray[3] = ArtifactFactory.createString(\"ether.h\"); " +
		 "headerArray[4] = ArtifactFactory.createString(\"net.h\"); " +
		 "headerArray[5] = ArtifactFactory.createString(\"proc.h\"); " +
		 "headerArray[6] = ArtifactFactory.createString(\"mem.h\"); " +
		 "var refArray = [];	" +
		 "for(i=0;i<7;i++){refArray[i] = AtlasQueryAdapter.runReferenceQuery(headerArray[i]); }" +
		 "var rcgArray = [];" +
		 "for(i=0;i<7;i++){rcgArray[i] = AtlasQueryAdapter.runReverseCallGraphQuery(refArray[i]); }" +
		 "var indRefArray = [];" +
		 "for(i=0;i<7;i++){indRefArray[i] = AtlasQueryAdapter.runMinusQuery(rcgArray[i], refArray[i]); }" +
		 "var oaArray = [];" +
		 "for(i=0;i<7;i++){oaArray[i] = AtlasQueryAdapter.runMinusQuery(" +
		 "AtlasQueryAdapter.runCalledByQuery(rcgArray[i]), rcgArray[i]);}" +
		 "var andArray = [];for(i=0;i<7;i++){for(j=i+1;j<7;j++){" +
		 "andArray = AtlasQueryAdapter.runAndQuery(refArray[i], refArray[j]);}}" +
		 "for(i=0;i<7;i++){ArtifactFactory.showResult(headerArray[i]);" +
		 "ArtifactFactory.showResult(refArray[i]);" +
		 "ArtifactFactory.showResult(rcgArray[i]);" +
		 "ArtifactFactory.showResult(indRefArray[i]);" +
		 "ArtifactFactory.showResult(oaArray[i]);" +
		 "ArtifactFactory.showResult(andArray[i]);}}";

		/*	"with(Zeus){var set = ArtifactFactory.createArtifacts();" +
			" set.add(ArtifactFactory.createFunction(\"dswrite\")); " +
			"var r1 = AtlasQueryAdapter.runCalledByQuery(set);ArtifactFactory.showResult(r1);}"; */
		//"java.lang.System.out.println(\"Hello world! This came from javascript!\")";
			
		try {
      // create a private ContextFactory that uses this plug-in's class loader
			ContextFactory cf = new ContextFactory();
			cf.initApplicationClassLoader(Zeus.class.getClassLoader());
			
			// create a new context which can use "importClass, importPackage"
			Context cx = cf.enterContext();
			cx.setLanguageVersion(Context.VERSION_1_7);
			Scriptable scope = new ImporterTopLevel(cx);

			// run script 
			Object result = cx.evaluateString(scope, scriptString, "<cmd>", 1, null);
			//Don't need to print result at the moment, but could uncomment for debugging purposes
			//System.out.println(Context.toString(result));
	  } catch(Exception e) {
	  		System.out.println(e.toString());
	  } finally {
	      Context.exit();
	  }
		
		MessageDialog.openInformation(window.getShell(), "Hello World!", "Sample Script Completed.");
		
		
	}

	public String getScript() {
		//Get the active editor
		IEditorPart editor =  PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		if (editor instanceof ITextEditor) {
			//Get the DocumentProvider and EditorInput for the active editor
		  IDocumentProvider docProvider = ((ITextEditor)editor).getDocumentProvider();
		  IEditorInput editorInput = ((ITextEditor)editor).getEditorInput();
		  //Get the Document for the active editor
		  IDocument scriptDocument = docProvider.getDocument(editorInput);
		  return scriptDocument.get();
		}
		else {
			// TODO Not a valid editor
			return "";
		}
	}
	
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

}
