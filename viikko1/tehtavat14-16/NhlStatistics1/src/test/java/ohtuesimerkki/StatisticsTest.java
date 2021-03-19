/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author anttihalmetoja
 */
public class StatisticsTest {
 
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }  
    @Test
    public void searchPalauttaaOikeanPelaajanNimen() {
        
        assertEquals("Kurri", stats.search("Kurri").getName());
    }
    
    @Test
    public void searchPalauttaaNullinOlemattomallaNimella() {
        
        assertEquals(null, stats.search("Kurli"));
    }
    
    
    
    @Test
    public void teamPalauttaaOikeanTiimin() {
        
        assertEquals("Semenko", stats.team("EDM").get(0).getName());
    }
    @Test
    public void topScorerersPalauttaaOikeanListan() {
        
        assertEquals("Gretzky", stats.topScorers(1).get(0).getName());
    }
}