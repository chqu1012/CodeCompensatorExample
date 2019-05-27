package de.dc.jdt.ast.example;

import java.io.PrintWriter;

import org.eclipse.jdt.core.compiler.batch.BatchCompiler;

public class CompilationUnitCompileExample {

	/**
	 * https://help.eclipse.org/oxygen/index.jsp?topic=%2Forg.eclipse.jdt.doc.isv%2Fguide%2Fjdt_api_compile.htm
	 * @param args
	 */
	public static void main(String[] args) {
		boolean isCompiled = BatchCompiler.compile("-classpath rt.jar resources/HelloWorld.java",
				new PrintWriter(System.out), new PrintWriter(System.err), null);
		System.out.println("Compiled:"+isCompiled);
	}
}
