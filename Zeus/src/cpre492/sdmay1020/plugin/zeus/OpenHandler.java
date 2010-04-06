package cpre492.sdmay1020.plugin.zeus;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;


public class OpenHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		Shell shell = window.getShell();
		FileDialog fd = new FileDialog(shell, SWT.OPEN);
		fd.setText("Open");
        fd.setFilterPath("C:/");
        String[] filterExt = { "*.js", "*.*" };
        fd.setFilterExtensions(filterExt);
		String name = fd.open();
		if (name == null)
		    return null;
		IFileStore fileStore = EFS.getLocalFileSystem().getStore((IPath) new Path(name));
		if (!fileStore.fetchInfo().isDirectory() && fileStore.fetchInfo().exists()) {			
			IWorkbenchPage page = window.getActivePage();
		    try {
		        IDE.openEditorOnFileStore(page, fileStore);
		    } catch (PartInitException e) {
		        /* some code */
		    }
		    return null;
		}
		else
		{
			//File does not exist
			return null;
		}
	}	

	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}



	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isHandled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

}
