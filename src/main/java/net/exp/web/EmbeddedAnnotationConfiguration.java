package net.exp.web;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.annotations.AnnotationParser;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.webapp.DiscoveredAnnotation;
import org.eclipse.jetty.annotations.ClassNameResolver;
import org.eclipse.jetty.util.resource.Resource;
import java.io.File;

public class EmbeddedAnnotationConfiguration extends AnnotationConfiguration {
	public void parseWebInfClasses(final WebAppContext context,
			AnnotationParser parser) {
		System.out.println("Scanning classes in WEB-INF/classes");
		try {
			if (context.getWebInf() != null) {
				File file = new File("target/classes");
				Resource classesDir = Resource.newResource(file.toURI());
				if (classesDir.exists()) {
					clearAnnotationList(parser.getAnnotationHandlers());
					parser.parse(classesDir, new ClassNameResolver() {
						public boolean isExcluded(String name) {
							if (context.isSystemClass(name))
								return true;
							if (context.isServerClass(name))
								return false;
							return false;
						}

						public boolean shouldOverride(String name) {
							// looking at webapp classpath, found already-parsed
							// class of same name - did it come from system or
							// duplicate in webapp?
							if (context.isParentLoaderPriority())
								return false;
							return true;
						}
					});

					// TODO - where to set the annotations discovered from
					// WEB-INF/classes?
					List<DiscoveredAnnotation> annotations = new ArrayList<DiscoveredAnnotation>();
					gatherAnnotations(annotations,
							parser.getAnnotationHandlers());
					context.getMetaData().addDiscoveredAnnotations(annotations);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}