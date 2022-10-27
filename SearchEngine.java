import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    
    ArrayList<String> strList = new ArrayList<String>();

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("please type");
        }else if(url.getPath().contains("/add")){
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
            strList.add(parameters[1]);
            }
        } 
        else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/search")) {
                String[] parameters = url.getQuery().split("=");
                String result = "";
                for(int i=0;i<strList.size();i++){
                    if((strList.get(i).contains(parameters[1])&& (i==strList.size()-1))){
                        result += strList.get(i);
                    }else if(strList.get(i).contains(parameters[1])){
                        result += strList.get(i) + " and ";
                    }else return "not found!";
                }
                return result;     
            }
            }return "continue adding";
        }
}


class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
