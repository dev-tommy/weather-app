package pl.devtommy;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;

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

    public static void main( String[] args ) throws APIException {
        System.out.println( "Weather App!" );

        getApiKeyFromConfigFile("config.properties");

        OWM owm = new OWM(API_KEY);

        CurrentWeather cwd = owm.currentWeatherByCityName("Poznan", OWM.Country.POLAND);

        System.out.println("Miasto: " + cwd.getCityName());

        System.out.println("Temp: " + Math.round(convertKelvinCelsius( cwd.getMainData().getTemp() )) + " \'C");
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

    private static double convertKelvinCelsius(double kelvin) {
        return (kelvin - 273.15F);
    }
}
