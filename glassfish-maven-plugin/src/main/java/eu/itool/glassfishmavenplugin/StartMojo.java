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
 * Start the Glassfish via asadmin
 *
 * @goal start
 * @description Maven 2 Glassfish plugin
 */
public class StartMojo extends AbstractGlassfishMojo{

	public void execute() throws MojoExecutionException, MojoFailureException {
		
		String params = "start-domain " + domain;
		
		getLog().info("Glassfish home = " +glassfishHome);
		getLog().info("Params =" + params);
		
		launchWithoutMessage("asadmin", params);
	}

}
