package cpre492.sdmay1020.plugin.zeus;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
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
			//This is where the file gets opened
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
