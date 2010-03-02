package com.ensoftcorp.plugin.atlas.sample_client.actions;

import java.util.Collections;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import com.ensoftcorp.plugin.atlas.query.api.QueryFactory;
import com.ensoftcorp.plugin.atlas.query.lang.IArtifact;
import com.ensoftcorp.plugin.atlas.query.lang.IArtifacts;
import com.ensoftcorp.plugin.atlas.query.lang.IFunctionArtifact;
import com.ensoftcorp.plugin.atlas.query.lang.IQueryFunction;
import com.ensoftcorp.plugin.atlas.query.lang.IQueryFunctionSymbolTable;
import com.ensoftcorp.plugin.atlas.query.lang.IQueryState;
import com.ensoftcorp.plugin.atlas.query.lang.IStringValue;
import com.ensoftcorp.plugin.atlas.query.lang.IValue;
import com.ensoftcorp.plugin.atlas.query.lang.IVariable;
import com.ensoftcorp.plugin.atlas.query.lang.IQueryFunctionSymbolTable.FUNCTION;
import com.ensoftcorp.plugin.atlas.ui.api.GraphUI;

/**
 * Our sample action implements workbench action delegate.
 * The action proxy will be created by the workbench and
 * shown in the UI. When the user tries to use the action,
 * this delegate will be created and execution will be 
 * delegated to it.
 * @see IWorkbenchWindowActionDelegate
 */
public class SampleAction implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow window;
	/**
	 * The constructor.
	 */
	public SampleAction() {
	}

	/**
	 * The action has been activated. The argument of the
	 * method represents the 'real' action sitting
	 * in the workbench UI.
	 * @see IWorkbenchWindowActionDelegate#run
	 */
	public void run(IAction action) {

		try {
			
			QueryFactory qf = QueryFactory.instance;
			IQueryState queryState = qf.createQueryState();
			IQueryFunctionSymbolTable qfst = qf.createQueryFunctionSymbolTable();
			
//			callsToFreebuf(qf, queryState, qfst);

			
			String [] headerFiles = new String[] {
				"net.h",
				"ether.h",
				"disk.h",
				"arp.h",
				"bufpool.h",
				"mem.h",
				"sem.h"
			};
			
			for (String headerFile : headerFiles) {
				displayHeaderFileRelationships(qf, queryState, qfst, headerFile);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			MessageDialog.openError(window.getShell(), "Error", "Unexpected error while running query...");
		}

	}

	private void callsToFreebuf(QueryFactory qf, IQueryState queryState,
			IQueryFunctionSymbolTable qfst) {
		
		// call(f:freebuf)
		IFunctionArtifact function = qf.createFunctionArtifact("freebuf");
		IArtifacts artifacts = qf.createArtifacts();
		artifacts.add(function);
		IQueryFunction call = qfst.lookupSymbol(FUNCTION.CALL);
		IValue result = call.execute(qfst, queryState, new IValue[] {artifacts});
		
		showResult(result);
	}

	private void displayHeaderFileRelationships(QueryFactory qf,
			IQueryState queryState, IQueryFunctionSymbolTable qfst,
			String headerFile) {
		
		// roughly equivalent to:
		// #x = ref(def(<headerFile>))
		// graph(leaves=#x, highlights=#x)
		
		IStringValue sv = qf.createStringValue(headerFile);
		IQueryFunction def = qfst.lookupSymbol(FUNCTION.DEF);
		IQueryFunction ref = qfst.lookupSymbol(FUNCTION.REF);
		IValue result2 = def.execute(qfst, queryState, new IValue[] {sv});
		IValue result3 = ref.execute(qfst, queryState, new IValue[] {result2});
		
		
		IArtifacts roots = QueryFactory.instance.createArtifacts();
		IArtifacts leaves = QueryFactory.instance.createArtifacts();
		IArtifacts omissions = QueryFactory.instance.createArtifacts();
		IArtifacts highlights = QueryFactory.instance.createArtifacts();
		IArtifacts simplify = QueryFactory.instance.createArtifacts();
		IArtifacts center = QueryFactory.instance.createArtifacts();
		
		leaves = (IArtifacts) result3;
		highlights = (IArtifacts) result3;
		
		GraphUI.instance.showGraph(headerFile, roots, leaves, omissions, highlights, simplify, center);
	}

	private void showResult(IValue result) {
		if (result instanceof IArtifacts) {
			IArtifacts artifacts = (IArtifacts) result;
			
			StringBuilder s = new StringBuilder();
			
			for (IArtifact a : artifacts) {
				s.append(a.getName());
				s.append("\n");
			}
			
			MessageDialog.openInformation(window.getShell(), "Atlas Query Result", "Artifacts:\n" + s.toString());
			
		} else if (result instanceof IVariable) {
			// not really possible at this time - query language only returns artifacts
			IVariable v = (IVariable) result;
			MessageDialog.openInformation(window.getShell(), "Atlas Query Result", "Variable: " + v.getName());
			
		} else if (result instanceof IStringValue) {
			// not really possible at this time - query language only returns artifacts
			IStringValue s = (IStringValue) result;
			MessageDialog.openInformation(window.getShell(), "Atlas Query Result", "StringValue: " + s.getValue());
		}
	}
	

	/**
	 * Selection in the workbench has been changed. We 
	 * can change the state of the 'real' action here
	 * if we want, but this can only happen after 
	 * the delegate has been created.
	 * @see IWorkbenchWindowActionDelegate#selectionChanged
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

	/**
	 * We can use this method to dispose of any system
	 * resources we previously allocated.
	 * @see IWorkbenchWindowActionDelegate#dispose
	 */
	public void dispose() {
	}

	/**
	 * We will cache window object in order to
	 * be able to provide parent shell for the message dialog.
	 * @see IWorkbenchWindowActionDelegate#init
	 */
	public void init(IWorkbenchWindow window) {
		this.window = window;
	}
}