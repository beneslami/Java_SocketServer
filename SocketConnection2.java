import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Created by ben on 1/6/17.
 */
class SocketConnection {
    private Socket          socket   = null;
    private ServerSocket    server   = null;
    private DataInputStream streamIn =  null;
    private DataInputStream console  =  null;
    private DataOutputStream streamOut = null;

    public SocketConnection(int port){
        try {
            System.out.println("Binding to port " + port + ", please wait  ...");
            server = new ServerSocket(port);
            System.out.println("Server started: " + server);
            System.out.println("Waiting for a client ...");
            socket = server.accept();
            System.out.println("Client accepted: " + socket);
            open();
            boolean done = false;
            while (!done) {
                try {
                    String line = streamIn.readUTF();
                    System.out.println(line);
                    done = line.equals(".bye");
                }
                catch(IOException ioe) {
                    done = true;
                }
                try{
                    String s = console.readLine();
                    streamOut.writeUTF(s);
                    streamOut.flush();
                }
                catch (IOException ioe){
                    System.out.println("error");
                }
            }
            close();
        }
        catch(IOException ioe) {
            System.out.println(ioe);
        }
    }
    public void open() throws IOException {
        streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        console = new DataInputStream(System.in);
        streamOut = new DataOutputStream(socket.getOutputStream());
    }
    public void close() throws IOException {
        if (socket != null)
            socket.close();
        if (streamIn != null)
            streamIn.close();
    }
}
