package eu.itool.glassfishmavenplugin;

import java.io.File;

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
 * Deploys a directory or file to Glassfish via asadmin
 *
 * @goal deploy
 * @description Maven 2 Glassfish plugin
 */
public class DeployMojo extends AbstractGlassfishMojo {

	public void execute() throws MojoExecutionException, MojoFailureException {
		// TODO Auto-generated method stub
		
		String fileNamePath = buildDir + File.separator + fileName;
		String params = "deploy --user " + user + " --passwordfile " + passwordfile;
		
		if (host != null && port != null)
			params += " --host " + host + " --port " + port;
		
		params += " " + fileNamePath;
		
		getLog().info("Glassfish home = " +glassfishHome);
		getLog().info("Params =" + params);
		
		launch("asadmin", params);
	}
}
