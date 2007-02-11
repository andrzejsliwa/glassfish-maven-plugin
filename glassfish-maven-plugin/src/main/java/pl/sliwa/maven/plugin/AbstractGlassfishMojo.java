package pl.sliwa.maven.plugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.lang.SystemUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

public abstract class AbstractGlassfishMojo extends AbstractMojo {

	private static final String SERVER_DIR_NAME = "domains";

	/**
	 * The location to Glassfish Home. This is a required configuration
	 * parameter (unless GLASSFISH_HOME is set).
	 * 
	 * @parameter expression="ENV"
	 * @required
	 */
	protected String glassfishHome;

	/**
	 * The user name of admin in Glassfish server
	 * 
	 * @parameter expression="admin"
	 */
	protected String user;

	/**
	 * The password of admin in Glassfish server
	 * 
	 * @parameter expression="adminadmin"
	 */
	protected String passwordfile;
	
	/**
	 * The password of admin in Glassfish server
	 * 
	 * @parameter expression="domain1"
	 */
	protected String domain;

	/**
	 * @parameter expression="default"
	 */
	private File glassfishHomeDir;
	
	/**
	 * The name of the file or directory to deploy or undeploy.
	 * 
	 * @parameter expression="${project.build.finalName}.${project.packaging}"
	 * @required
	 */
	protected String fileName;
	
	/**
	 * The name of the file or directory to deploy or undeploy.
	 * 
	 * @parameter expression="${project.build.finalName}"
	 * @required
	 */
	protected String undeployName;
		
	/**
	 * The name of the file or directory to deploy or undeploy.
	 * 
	 * @parameter expression="${project.build.directory}"
	 * @required
	 */
	protected String buildDir;
	
	private void checkConfig() throws MojoExecutionException,
			MojoFailureException {
		if (glassfishHome == null || glassfishHome.equals("ENV")) {
			if (SystemUtils.JAVA_VERSION_FLOAT < 1.5) {
				throw new MojoExecutionException(
						"Neither GLASSFISH_HOME nor the glassfishHome configuration parameter is set! Also, to save you the trouble, JBOSS_HOME cannot be read running a VM < 1.5, so set the jbossHome configuration parameter or use -D.");
			}
			glassfishHome = System.getenv("GLASSFISH_HOME");
		}

		if (glassfishHome == null) {
			throw new MojoExecutionException(
					"Neither GLASSFISH_HOME nor the glassfishHome configuration parameter is set!");
		}

		glassfishHomeDir = new File(glassfishHome);

		if (!glassfishHomeDir.exists()) {
			throw new MojoFailureException(
					"The glassfishHome specifed does not exist.");
		}
	}

	protected void launch(String fName, String params)
			throws MojoExecutionException {

		try {
			
			checkConfig();
			
			String osName = System.getProperty("os.name");
			Runtime runtime = Runtime.getRuntime();
			
			Process p = null;
			if (osName.startsWith("Windows")) {
				String command[] = {
						"cmd.exe",
						"/C",
						"cd " + glassfishHomeDir.getAbsolutePath() + "\\bin & "
								+ fName + ".bat " + " " + params };
				    
				p = runtime.exec(command);
				
				    InputStream is = p.getErrorStream();
				    InputStreamReader isr = new InputStreamReader(is);
				    BufferedReader br = new BufferedReader(isr);
				    String line = "";
				    String ln;
				    while ((ln = br.readLine()) != null) {
				    	 line += ln;
				    	 getLog().info(ln);

				    }
				    if (line.contains("failed")) throw new MojoFailureException(line); 
	
			} else {
				String command[] = {
						"sh",
						"-c",
						"cd " + glassfishHomeDir.getAbsolutePath() + "/bin; ./"
								+ fName + ".sh " + " " + params };
				p = runtime.exec(command);
				
			    InputStream is = p.getErrorStream();
			    InputStreamReader isr = new InputStreamReader(is);
			    BufferedReader br = new BufferedReader(isr);
			    String line = "";
			    String ln;
			    while ((ln = br.readLine()) != null) {
			    	 line += ln;
			    	 getLog().info(ln);
			    }
			    if (line.contains("failed")) throw new MojoFailureException(line);
			}

		} catch (Exception e) {
			throw new MojoExecutionException("Mojo error occurred: "
					+ e.getMessage(), e);
		}
	}

}
