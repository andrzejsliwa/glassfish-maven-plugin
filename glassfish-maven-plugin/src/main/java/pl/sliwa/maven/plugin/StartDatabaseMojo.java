package pl.sliwa.maven.plugin;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 *  stop the Glassfish via asadmin
 *
 * @goal start-database
 * @description Maven 2 Glassfish plugin
 */
public class StartDatabaseMojo extends AbstractGlassfishMojo{

	public void execute() throws MojoExecutionException, MojoFailureException {
		
		String params = "start-database --user " + user + " --passwordfile " + passwordfile;
		
		System.out.println("Glassfish home = " +glassfishHome);
		System.out.println("Params =" + params);
		
		launch("asadmin", params);
	}

}