package de.dc.jdt.ast.example;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.Launch;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.launching.IVMInstall;
import org.eclipse.jdt.launching.IVMRunner;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jdt.launching.VMRunnerConfiguration;

public class CompilationUnitExecuteExample {

	/**
	 * https://help.eclipse.org/oxygen/index.jsp?topic=%2Forg.eclipse.jdt.doc.isv%2Fguide%2Fjdt_api_compile.htm
	 * @param args
	 * @throws CoreException 
	 */
	public static void main(String[] args) throws CoreException {
		IJavaProject myJavaProject = null;
		IVMInstall vmInstall = JavaRuntime.getVMInstall(myJavaProject );
		   if (vmInstall == null)
		      vmInstall = JavaRuntime.getDefaultVMInstall();
		   if (vmInstall != null) {
		      IVMRunner vmRunner = vmInstall.getVMRunner(ILaunchManager.RUN_MODE);
		      if (vmRunner != null) {
		         String[] classPath = null;
		         try {
		            classPath = JavaRuntime.computeDefaultRuntimeClassPath(myJavaProject);
		         } catch (CoreException e) { }
		         if (classPath != null) {
		            VMRunnerConfiguration vmConfig = 
		               new VMRunnerConfiguration("MyClass", classPath);
		            ILaunch launch = new Launch(null, ILaunchManager.RUN_MODE, null);
		            vmRunner.run(vmConfig, launch, null);
		         }
		      }
		   }
	}
}
