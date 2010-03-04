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
import org.mozilla.javascript.RhinoException;
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
		
		//scriptString: The script, as a single string
		String scriptString = zeusImport + getScript();
			
		try {
			// create a private ContextFactory that uses this plug-in's class loader
			ContextFactory cf = new ContextFactory();
			cf.initApplicationClassLoader(Zeus.class.getClassLoader());
			
			// create a new context which can use "importClass, importPackage"
			Context cx = cf.enterContext();
			cx.setLanguageVersion(Context.VERSION_1_7);
			Scriptable scope = new ImporterTopLevel(cx);
			
			// if the script string is not compilable, display message box 
			if(!cx.stringIsCompilableUnit(scriptString))
			{
				MessageDialog.openInformation(window.getShell(), "Script Error", "Unable to compile script.");
			}
			
			// run the script
			Object result = cx.evaluateString(scope, scriptString, "script", 1, null);
			
			//Don't need to print result at the moment, but could uncomment for debugging purposes
			//System.out.println(Context.toString(result));
	  } catch(Exception e) {
		  	if(e instanceof RhinoException)
		  	{
		  		RhinoException re = (RhinoException) e;
		  		System.out.println("\n" + re.toString());
		  		System.out.println("(" + re.lineNumber() + ", " + re.columnNumber() + ") " + re.lineSource());
		  	}
		  	else
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