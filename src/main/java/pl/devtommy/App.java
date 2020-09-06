package pl.devtommy;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App 
{
    private static String API_KEY;

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        getApiKeyFromConfigFile("/config.properties");
    }

    private static void getApiKeyFromConfigFile(String configFileName) {
        try (InputStream input = new FileInputStream(configFileName)) {
            /* config.properties:
               api.key=your_owm_api_key
             */

            Properties prop = new Properties();
            prop.load(input);

            API_KEY = prop.getProperty("api.key");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
