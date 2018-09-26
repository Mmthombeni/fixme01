import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.CharBuffer;

public class Market {
  static Random rand = new Random();
  private static int result;

    static BufferedReader userInputReader = null;
    static String message = null;

    public static boolean processReadySet(Set readySet) throws Exception {
      Iterator iterator = readySet.iterator();
      while (iterator.hasNext()) {
        SelectionKey key = (SelectionKey)
         iterator.next();
        iterator.remove();
        if (key.isConnectable()) {
          boolean connected = processConnect(key);
          if (!connected) {
            return true; // Exit
          }
        }
        if (key.isReadable()) {
          String msg = processRead(key);
          
          if (msg.length() > 0){
            System.out.println("[Server]: " + msg);
            msg += " mpho@";
            // r/a
            
            System.out.println("[Inner]: <" + msg + ">");
            // msg = userInputReader.readLine(); 
            //System.out.println("Market");
         
            message = msg;
            SocketChannel sChannel = (SocketChannel) key.channel();
            sChannel.register(key.selector(), SelectionKey.OP_WRITE);
            // ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
            // sChannel.write(buffer);

          }
          
        }
        if (key.isWritable()) {
          // System.out.print("Please enter a message(Bye to quit):");
          // String msg = userInputReader.readLine();
          
          // if (msg.equalsIgnoreCase("bye")) {
          //   return true; // Exit
          // }
          SocketChannel sChannel = (SocketChannel) key.channel();
          ByteBuffer buffer = ByteBuffer.wrap((message + " - market message").getBytes());
          message = "";
          sChannel.write(buffer);
          sChannel.register(key.selector(), SelectionKey.OP_READ);
        }
      }
      return false; // Not done yet
    }
    public static boolean processConnect(SelectionKey key) throws Exception{
      SocketChannel channel = (SocketChannel) key.channel();
      while (channel.isConnectionPending()) {
        channel.finishConnect();
      }
      return true;
    }
    public static String processRead(SelectionKey key) throws Exception {
      SocketChannel sChannel = (SocketChannel) key.channel();
      ByteBuffer buffer = ByteBuffer.allocate(1024);
      sChannel.read(buffer);
      buffer.flip();
      Charset charset = Charset.forName("UTF-8");
      CharsetDecoder decoder = charset.newDecoder();
      CharBuffer charBuffer = decoder.decode(buffer);
      String msg = charBuffer.toString();
      return msg;
    }
  
  public static void main(String[] args) throws Exception {
      InetAddress serverIPAddress = InetAddress.getByName("localhost");
      int port = 5001;
      InetSocketAddress serverAddress = new InetSocketAddress(
          serverIPAddress, port);
      Selector selector = Selector.open();
      SocketChannel channel = SocketChannel.open();
      channel.configureBlocking(false);
      channel.connect(serverAddress);
      int operations = SelectionKey.OP_CONNECT | SelectionKey.OP_READ | SelectionKey.OP_WRITE;
      channel.register(selector, operations);
  
      userInputReader = new BufferedReader(new InputStreamReader(System.in));
      //System.out.println("Please");
      while (true) {
        if (selector.select() > 0) {
          boolean doneStatus = processReadySet(selector.selectedKeys());
          //flip();
          //break;
          if (doneStatus) {
            break;
          }
          //flip();
          //break;
        }
      }
      
      channel.close();
    }

    public static void flip(){
      result = rand.nextInt(3);

      //while(true){
        if (result == 0){
          System.out.println("msg Accepted");
          //break;
          
        }else
        System.out.println("msg Reject");
       // break;
      //}
     

    }
    
    // public static void main(String args[]) throws Exception
    // {
    //     Socket sk=new Socket("127.0.0.1",5000);


    //     BufferedReader sin=new BufferedReader(new InputStreamReader(sk.getInputStream()));
    //     PrintStream sout=new PrintStream(sk.getOutputStream());
    //     BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
    //     String s;
    //     while (  true )
    //     {
    //         System.out.print("Market : ");
    //         s=stdin.readLine();
    //         sout.println(s);
    //         if ( s.equalsIgnoreCase("BYE") )
    //         {
    //             System.out.println("Connection ended by client");
    //             break;
    //         }
    //         s=sin.readLine();
    //         //System.out.print("Server : "+s+"\n");

    //     }
    //     sk.close();
    //     sin.close();
    //     sout.close();
    //     stdin.close();
    // }
}


