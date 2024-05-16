package test;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import model.GameFacade;
import model.Player;

public class GameFacadeTest {

    @Test
    public void testStartGame() {
        GameFacade facade = GameFacade.getInstance();

   
        assertEquals(2, facade.getJogadores().size());
    }

    @Test
    public void testEndGame() {
        GameFacade facade = GameFacade.getInstance();

        
        List<Player> players = new ArrayList<>();
        Player jogador1 = new Player("Teste1", 0);
        Player jogador2 = new Player("Teste2", 1);
        players.add(jogador1);
        players.add(jogador2);

        
        assertFalse(facade.endGame(players));

      
        jogador1.SetShipsLeft(0);
        assertTrue(facade.endGame(players));
    }

  

    @Test
    public void testSaveGame() {
        GameFacade facade = GameFacade.getInstance();
        facade.saveGame("testSaveGame.dat");
       
    }

    @Test
    public void testLoadGame() {
        GameFacade facade = GameFacade.getInstance();
        facade.loadGame("testSaveGame.dat");
       
    }
}

