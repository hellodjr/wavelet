import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    
    ArrayList<String> strList = new ArrayList<String>();
    ArrayList<String> container = new ArrayList<String>();

   
    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("please type");
        }else if(url.getPath().equals("/add")){
            String[] parameters = url.getQuery().split("=");
            strList.add(parameters[1]);
        } 
        else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/search")) {
                String[] parameters = url.getQuery().split("=");
                for(String s:strList){
                    if(s.contains(parameters[1])){
                        container.add(s);
                    }
                }     
            }
            }
            return "404 Not Found!";
        }
}


class NumberServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
