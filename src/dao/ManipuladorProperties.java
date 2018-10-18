package dao;



import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author https://www.devmedia.com.br/utilizando-arquivos-de-propriedades-no-java/25546
 *
 */
public class ManipuladorProperties {
	
	public static Properties getProp() throws IOException {
        Properties props = new Properties();
        FileInputStream file = new FileInputStream(
                "dados.properties");
        props.load(file);
        return props;
    }

}
