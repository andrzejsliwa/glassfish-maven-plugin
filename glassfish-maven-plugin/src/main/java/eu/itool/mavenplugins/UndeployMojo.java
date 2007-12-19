package eu.itool.mavenplugins;

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
 * Undeploy aplication from Glassfish
 *
 * @goal undeploy
 * @description Maven 2 Glassfish plugin
 */
public class UndeployMojo extends AbstractGlassfishMojo {
	
	
	public void execute() throws MojoExecutionException, MojoFailureException {
		// TODO Auto-generated method stub
		
		String params = "undeploy --user " + user + " --passwordfile " + passwordfile;
		
		if (host != null && port != null)
			params += " --host " + host + " --port " + port;
		
		params += " " + undeployName;
		
		getLog().info("Glassfish home = " +glassfishHome);
		getLog().info("Params =" + params);
		
		launch("asadmin", params);
	}
}
