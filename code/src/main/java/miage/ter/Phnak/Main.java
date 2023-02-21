package miage.ter.Phnak;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

public class Main {

    private static final String DEFAULT_HOST = "localhost";
    private static final int DEFAULT_PORT = 8080;
    private static final String DOC_BASE = ".";
    private static final String ADDITION_WEB_INF_CLASSES = "target/classes";
    private static final String WEB_APP_MOUNT = "/WEB-INF/classes";
    private static final String INTERNAL_PATH = "/";
    private static int port = DEFAULT_PORT;
    private static String host = DEFAULT_HOST;

    private static Properties loadConfig(String[] args) {
        try {
            InputStream config = Main.class.getResourceAsStream("/config.properties");

            Properties prop = new Properties();
            prop.load(config);
            config.close();

            if (args.length >= 2 && args[0].equals("-conf")) {
                config = new FileInputStream(args[1]);
                prop = new Properties(prop);
                prop.load(config);
                config.close();
            } else if (args.length != 0) {
                System.out.println("Use -conf <file> to specifie a configuration file.");
                System.exit(1);
            }

            String sport = prop.getProperty("server.port", DEFAULT_PORT + "");
            port = Integer.parseInt(sport);
            if (port <= 0) {
                throw new Exception("Invalid port number in server.port setting");
            }

            host = prop.getProperty("server.host", DEFAULT_HOST);


            return prop;

        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        return new Properties();
    }

    public static void main(String[] args) {

        Properties prop = loadConfig(args);

        Tomcat tomcat = new Tomcat();
        try {

            Path tmpDir = Files.createTempDirectory("tomcat." + port + ".");
            System.err.println("Setting work directory to: " + tmpDir);
            tomcat.setBaseDir(tmpDir.toString());
            tomcat.setHostname(host);
            tomcat.getHost().setAppBase(DOC_BASE);
            tomcat.setPort(port);
            tomcat.getConnector();
            tomcat.enableNaming();
            String[] components = Main.class.getName().split("[.]");
            if (components.length < 2) {
                // Not in a package ?
                System.exit(1);
            }
            String contextPath = components[components.length - 2];

            Context context = tomcat.addWebapp("/" + contextPath, DOC_BASE);
            ServletContext ctx = context.getServletContext();
            
            String[] keys = { "db.host", "db.port", "db.name", "db.user", "db.pass"};
            for (String k : keys) {
                ctx.setAttribute(k, prop.getProperty(k, ""));
            }

            File classes = new File(ADDITION_WEB_INF_CLASSES);
            String base = classes.getAbsolutePath();
            WebResourceRoot resources = new StandardRoot(context);
            resources.addPreResources(new DirResourceSet(resources, WEB_APP_MOUNT, base, INTERNAL_PATH));
            context.setResources(resources);

            tomcat.start();
        } catch (LifecycleException exception) {
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Cannot create Tomcat work directory.");
            System.exit(1);
        }
        tomcat.getServer().await();
    }

}
