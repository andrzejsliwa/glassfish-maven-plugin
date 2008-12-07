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
 * Stop the Glassfish via asadmin
 *
 * @goal stop
 * @description Maven 2 Glassfish plugin
 */
public class StopMojo extends AbstractGlassfishMojo{

	public void execute() throws MojoExecutionException, MojoFailureException {
		
		String params = "stop-domain " + domain;
		
		getLog().info("Glassfish home = " +glassfishHome);
		getLog().info("Params =" + params);
		
		launchWithoutMessage("asadmin", params);
	}

	
}
