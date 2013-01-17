package gov.usgs.cida.n52.wps.config;

import gov.usgs.cida.utilities.properties.JNDISingleton;
import java.io.IOException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.xmlbeans.XmlException;
import org.n52.wps.commons.WPSConfig;

/**
 * Web application lifecycle listener.
 *
 * @author Jordan Walker <jiwalker@usgs.gov>
 */
public class ForceConfigInitializeListener implements ServletContextListener {
    
    private static final String WPS_CONFIG_LOCATION = "watersmart.wps.config.location";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String defaultLocation = WPSConfig.getConfigPath();
        String configLocation = JNDISingleton.getInstance().getProperty(WPS_CONFIG_LOCATION, defaultLocation);
        System.out.println(configLocation);
        try {
            WPSConfig.forceInitialization(configLocation);
        }
        catch (XmlException ex) {
            System.out.println(ex);
            // I think this will get caught later
        }
        catch (IOException ex) {
            System.out.println(ex);
            // yell at me if I'm wrong
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // do nothing
    }
}
