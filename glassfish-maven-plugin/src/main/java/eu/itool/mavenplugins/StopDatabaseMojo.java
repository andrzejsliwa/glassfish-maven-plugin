package eu.itool.mavenplugins;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 *  stop the Glassfish via asadmin
 *
 * @goal stop-database
 * @description Maven 2 Glassfish plugin
 */
public class StopDatabaseMojo extends AbstractGlassfishMojo{

	public void execute() throws MojoExecutionException, MojoFailureException {
		
		String params = "stop-database --user " + user + " --passwordfile " + passwordfile;
		
		getLog().info("Glassfish home = " +glassfishHome);
		getLog().info("Params =" + params);
		
		launch("asadmin", params);
	}

	
}
