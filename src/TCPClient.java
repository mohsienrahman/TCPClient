import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.Socket;

public class TCPClient {

    public TCPClient() {}

    public static void main(String[] args) throws IOException {

        // IP address of localhost
        String IPAddressString = "127.0.0.1";
        InetAddress host = InetAddress.getByName(IPAddressString);

        // HTTP port number
        int port = 80;

        // Connect to the server
        System.out.println("TCPClient connecting to " + host.toString() + ":" + port);
        Socket clientSocket = new Socket(host, port);

        // Setup reader and writer for the socket
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        Writer writer = new OutputStreamWriter(clientSocket.getOutputStream());

        // Sending the HTTP GET request
        String httpRequest = "GET / HTTP/1.1\r\nHost: 127.0.0.1\r\n\r\n";
        System.out.println("Sending request: " + httpRequest);
        writer.write(httpRequest);
        writer.flush();

        // Read and print the entire response
        String responseLine;
        System.out.println("Response from server:");
        while ((responseLine = reader.readLine()) != null) {
            System.out.println(responseLine);
        }

        // Close the connection
        clientSocket.close();
    }
}
