import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;

import javax.swing.*;
import java.io.IOException;

public class StartRestServer {

public static void main(String[] args) {
	HttpServer server = null;
	try {
		server = HttpServerFactory.create( "http://localhost:8081/api/" );
	} catch (IOException e) {
		e.printStackTrace();
	}

	server.start();
	JOptionPane.showMessageDialog( null, "Ende" );
	server.stop( 0 );
	}

}