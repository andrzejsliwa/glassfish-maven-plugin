package eu.itool.glassfishmavenplugin;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * 
 * $License$
 * 
 * $Date$
 * $Revision$
 * $Author$
 * $HeadURL$
 * $Id$
 * 
 * stop the Glassfish via asadmin
 *
 * @goal start-database
 * @description Maven 2 Glassfish plugin
 */
public class StartDatabaseMojo extends AbstractGlassfishMojo{

	public void execute() throws MojoExecutionException, MojoFailureException {
		
		String params = "start-database --user " + user + " --passwordfile " + passwordfile;
		
		getLog().info("Glassfish home = " +glassfishHome);
		getLog().info("Params =" + params);
		
		launch("asadmin", params);
	}

}
