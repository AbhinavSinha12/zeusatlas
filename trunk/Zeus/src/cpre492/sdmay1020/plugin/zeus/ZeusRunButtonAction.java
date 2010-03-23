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
	 * enabling the script author to use 'with(Zeus){ ... } instead of importing all the class individually 
	 * final keyword -> means value is constant(can't change)*/
	private final String zeusImport = "var Zeus = JavaImporter(Packages.cpre492.sdmay1020.plugin.zeus.ArtifactFactory, " +
																"Packages.cpre492.sdmay1020.plugin.zeus.AtlasQueryAdapter," +
																"Packages.cpre492.sdmay1020.plugin.zeus.OutputResults);";
	
	/** 
	 * The constructor
	 */
	public ZeusRunButtonAction(){ }
	
	/**
	 * Disposes this action delegate so garbage collection can occur.
	 */
	@Override
	public void dispose() {
		// do nothing - no references to itself to unhook so garbage collection can occur.
	}

	/**
	 * Initializes this action delegate with the workbench window it will work in.
	 */
	@Override
	public void init(IWorkbenchWindow window) {
		this.window = window;
	}

	/**
	 * Reads the text in the active editor and parses and executes the JavaScript
	 * in the editor. This is launched by clicking the Zeus Run Button. (Lightning Bolt Icon)
	 */
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
			if(!cx.stringIsCompilableUnit(scriptString)){
				MessageDialog.openInformation(window.getShell(), "Script Error", "Unable to compile script.");
			}
			
			// run the script - if invalid input, this will throw an exception
			cx.evaluateString(scope, scriptString, "script", 1, null);

	  } catch(RhinoException re) {// Invalid JavaScript
		  	// tell user what (first) error occurred -> helps with debugging input 
		    System.out.println("\n" + re.toString());
		    // tell user where (first) error occurred -> helps with debugging input 
		    System.out.println("(" + re.lineNumber() + ", " + re.columnNumber() + ") " + re.lineSource());
	  } catch(Exception e){
	  		System.out.println(e.toString());
	  }finally {
	      Context.exit();
	  }
	}

	/**
	 * Notifies this action delegate that the selection in the workbench has changed.
	 */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// do nothing, action is not dependent on the selection
	}

	
	/*
	 * Obtains the text in the active Eclipse editor and returns it as a String object. An empty
	 * String object is returned if a valid editor does not exist.
	 * protected - data encapsulation & testing ability
	 */
	protected String getScript() {
		String retValue = "";
		//Get the active editor
		IEditorPart editor =  PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		if (editor instanceof ITextEditor) {
			//Get the DocumentProvider and EditorInput for the active editor
		  IDocumentProvider docProvider = ((ITextEditor)editor).getDocumentProvider();
		  IEditorInput editorInput = ((ITextEditor)editor).getEditorInput();
		  //Get the Document for the active editor
		  IDocument scriptDocument = docProvider.getDocument(editorInput);
		  retValue = scriptDocument.get();
		}
		return retValue;
	}
}