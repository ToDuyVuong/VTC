package hcmute.tlcn.vtc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
public class VtcApplication {

    public static void main(String[] args) {
        SpringApplication.run(VtcApplication.class, args);
        System.out.println("\n\n" );


        try {
            InetAddress ip = InetAddress.getLocalHost();
            System.out.println("IP Address: " + ip.getHostAddress());
            System.out.println("Localhost:  http://localhost:8181/swagger-ui/index.html");
            System.out.println("\n\n" );

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}
