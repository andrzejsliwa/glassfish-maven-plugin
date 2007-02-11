package pl.sliwa.maven.plugin;

import java.io.File;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 *  Deploys a directory or file to Glassfish via asadmin
 *
 * @goal deploy
 * @description Maven 2 Glassfish plugin
 */
public class DeployMojo extends AbstractGlassfishMojo {

	public void execute() throws MojoExecutionException, MojoFailureException {
		// TODO Auto-generated method stub
		
		String fileNamePath = buildDir + File.separator + fileName;
		String params = "deploy --user " + user + " --passwordfile " + passwordfile + " " + fileNamePath;
		
		System.out.println("Glassfish home = " +glassfishHome);
		System.out.println("Params =" + params);
		
		launch("asadmin", params);
	}
}
