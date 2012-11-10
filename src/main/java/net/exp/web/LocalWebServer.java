package net.exp.web;

import java.io.File;

import javax.servlet.ServletContext;

import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.FragmentConfiguration;
import org.eclipse.jetty.webapp.MetaInfConfiguration;
import org.eclipse.jetty.webapp.TagLibConfiguration;
import org.eclipse.jetty.webapp.WebInfConfiguration;
import org.eclipse.jetty.webapp.WebXmlConfiguration;
import org.eclipse.jetty.plus.webapp.EnvConfiguration;
import org.eclipse.jetty.plus.webapp.PlusConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class LocalWebServer {
	private String[] __dftConfigurationClasses = {
			"org.eclipse.jetty.webapp.WebInfConfiguration",
			"org.eclipse.jetty.webapp.WebXmlConfiguration",
			"org.eclipse.jetty.webapp.MetaInfConfiguration",
			"org.eclipse.jetty.webapp.FragmentConfiguration",
			"org.eclipse.jetty.plus.webapp.EnvConfiguration",
			"org.eclipse.jetty.webapp.JettyWebXmlConfiguration",
			"org.eclipse.jetty.annotations.AnnotationConfiguration" };

	private static final String contextPath = "/";
	/** location where resources should be provided from for VAADIN resources */
	private static final String resourceBase = "src/main/webapp";

	public static void main(String[] args) {
		System.setProperty("yamlconfig",
				"file:///Users/sorran/gitrepos/albert/dashboard/dashboard-config.yml");
		System.setProperty("java.naming.factory.url", "org.eclipse.jetty.jndi");
		System.setProperty("java.naming.factory.initial",
				"org.eclipse.jetty.jndi.InitialContextFactory");

		// var yamlFile = new File("dashboard-config.yml")
		// System.setProperty("yamlconfig", "file://" +
		// yamlFile.getAbsolutePath)

		Server server = new Server(8000);

		File configFile = new File("src/main/webapp/WEB-INF/web.xml");
		System.out.println(configFile.getAbsolutePath());
		WebAppContext webapp = new WebAppContext();
		// webapp.setConfigurationClasses(__dftConfigurationClasses);

		webapp.setConfigurations(new Configuration[] {
				new EmbeddedAnnotationConfiguration(),
				new WebXmlConfiguration(), new WebInfConfiguration(),
				new TagLibConfiguration(), new PlusConfiguration(),
				new MetaInfConfiguration(), new FragmentConfiguration(),
				new EnvConfiguration() });

		webapp.setParentLoaderPriority(true);
		webapp.setDescriptor(configFile.getAbsolutePath());
		webapp.setContextPath(contextPath);
		webapp.setResourceBase(resourceBase);
		webapp.setClassLoader(Thread.currentThread().getContextClassLoader());

		initContext(webapp.getServletContext());

		server.setHandler(webapp);

		// var root = new Context(server, "/", Context.SESSIONS);
		// root.addServlet(new ServletHolder(new HelloServlet("Ciao")), "/*");
		try {
			server.start();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void initContext(ServletContext context) {
	}
}
