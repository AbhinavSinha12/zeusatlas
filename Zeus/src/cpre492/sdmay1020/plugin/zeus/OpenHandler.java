package cpre492.sdmay1020.plugin.zeus;


import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import javax.swing.*;
import java.io.File;

public class OpenHandler implements IHandler {

	JFileChooser fc;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(null);

		if(returnVal == JFileChooser.APPROVE_OPTION){
			File file = fc.getSelectedFile();

			if (file.exists() && file.isFile()) {
			    IFileStore fileStore = EFS.getLocalFileSystem().getStore(file.toURI());
			    IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			 
			    try {
			        IDE.openEditorOnFileStore( page, fileStore );
			    } catch ( PartInitException e ) {
			        //Exception handling
			    }
			} else {
			    //File does not exist
			}
			
		} else{
			//Open Canceled by user
		}
		return null;
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
