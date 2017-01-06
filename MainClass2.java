import java.io.IOException;

/**
 * Created by ben on 1/6/17.
 */
class MainClass {
    public static void main(String[] args) throws IOException {
        SocketConnection socketConnection = new SocketConnection(Integer.parseInt(args[0]));
    }
}
