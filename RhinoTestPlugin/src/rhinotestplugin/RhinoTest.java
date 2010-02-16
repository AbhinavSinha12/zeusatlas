package rhinotestplugin;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.ImporterTopLevel;
import org.mozilla.javascript.Scriptable;

public class RhinoTest
{
	public static void rhinoFunc() {
	  try {
      // create a private ContextFactory that uses this plug-in's class loader
			ContextFactory cf = new ContextFactory();
			cf.initApplicationClassLoader(Activator.class.getClassLoader());
			
			// create a new context which can use "importClass, importPackage"
			Context cx = cf.enterContext();
			cx.setLanguageVersion(Context.VERSION_1_7);
			Scriptable scope = new ImporterTopLevel(cx);

			// run script 
			Object result = cx.evaluateString(scope, "importClass(Packages.rhinotestplugin.TestCounter); TestCounter.increment()", "<cmd>", 1, null);
			System.out.println(Context.toString(result));
	  } catch(Exception e) {
	  		System.out.println(e.toString());
	  } finally {
	      Context.exit();
	  }
	}
}
