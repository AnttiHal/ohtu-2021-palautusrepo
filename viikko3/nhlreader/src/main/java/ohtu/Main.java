package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.apache.http.client.fluent.Request;

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";
        
        String bodyText = Request.Get(url).execute().returnContent().asString();
                
        
        

        Gson mapper = new Gson();
        Player[] players = mapper.fromJson(bodyText, Player[].class);
        
        System.out.println("Oliot:");
        ArrayList<Player> playerslist = new ArrayList<>();
        for (Player player : players) {
            if (player.getNationality().contains("FIN")) {
                playerslist.add(player);
            }
            
        }   
        Collections.sort(playerslist);

        for (Player player : playerslist) {
            System.out.println(player.toString());
            
        }   
    }
    
  
}