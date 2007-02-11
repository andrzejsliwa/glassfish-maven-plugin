package pl.sliwa.maven.plugin;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 *  stop the Glassfish via asadmin
 *
 * @goal stop
 * @description Maven 2 Glassfish plugin
 */
public class StopMojo extends AbstractGlassfishMojo{

	public void execute() throws MojoExecutionException, MojoFailureException {
		
		String params = "stop-domain " + domain;
		
		System.out.println("Glassfish home = " +glassfishHome);
		System.out.println("Params =" + params);
		
		launch("asadmin", params);
	}

	
}
