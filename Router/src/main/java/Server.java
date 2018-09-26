import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server{
    public static void main(String[] args) throws Exception{
      ExecutorService executorService = Executors.newCachedThreadPool();
      executorService.execute(new StartServer(5000));
      executorService.execute(new StartServer(5001));
      executorService.shutdown();
    }

    
}

// public class SocketServer {
//     private  / int port;
//     ServerSocket server = null;
//     Socket client = null;
//     ExecutorService pool = null;
//     int clientCounter = 100000;
//  public void runServer(){
//  try{} catch(){}
//  }
//     public void main(String[] args) throws IOException {
//         Server server_broker = new Server(5000);

//         server_broker.startServer();
//         //passPorts();


//     }

//     public Server(int port){

//         this.port = port;
//         pool = Executors.newScheduledThreadPool(5);

//     }

//     public void startServer() throws IOException {



//                 server = new ServerSocket(port);
//                 while(true){
//                     client = server.accept();
//                     clientCounter++;
//                     ServerThread runnable = new ServerThread(client, clientCounter,this);

//                     pool.execute(runnable);
//                 }
//              }

//     public void passPorts() throws IOException {
//                 Server server_broker = new Server(port);
//                 try {
//                     server_broker.startServer();

//                 } catch (IOException e) {
//                     e.printStackTrace();
//                 }
//             }



//         //System.out.println("yes");




//     //System.out.println("yes");
// }



//     class ServerThread implements Runnable{
//         Server server = null;
//         Socket client;
//         BufferedReader reader;
//         PrintStream printing;
//         Scanner scan = new Scanner(System.in);
//         int id;
//         String s;


//         ServerThread(Socket client, int count, Server server) throws IOException{
//             this.client = client;
//             this.server = server;
//             this.id = count;
//             System.out.println("Connection From " + id + " Established");
//             reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
//             printing = new PrintStream(client.getOutputStream());
//         }

//         @Override
//         public void run() {
//             int x= 1;
//             try{
//                 while(true){
//                     s=reader.readLine();

//                     System. out.print("Server("+ id +") :"+s+"\n");

//                     if (s.equalsIgnoreCase("bye"))
//                     {
//                         x=0;
//                         System.out.println("Connection ended by server");
//                         break;
//                     }
//                     printing.println(s);
//                 }



//                 reader.close();
//                 client.close();
//                 printing.close();
//                 if(x==0) {
//                     System.out.println( "Server cleaning up." );
//                     System.exit(0);
//                 }
//             }
//             catch(IOException ex){
//                 System.out.println("Error : "+ex);
//             }


//         }
    // }

