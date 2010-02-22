package cpre492.sdmay1020.plugin.zeus;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.jface.dialogs.MessageDialog;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.ImporterTopLevel;
import org.mozilla.javascript.Scriptable;

public class ZeusRunButtonAction implements IWorkbenchWindowActionDelegate {

	private IWorkbenchWindow window;
	
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
		//scriptString: The  script, as a single string
		String scriptString = "java.lang.System.out.println(\"Hello world! This came from javascript!\")";
			
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

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

}
