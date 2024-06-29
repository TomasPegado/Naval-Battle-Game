package test;

import static org.junit.Assert.*;
import org.junit.Test;
import model.PlayerActionsFacade;
import model.Player;
import model.ShipType;
import model.GameBoard;
import model.IPlayerActionsFacade;

public class PlayerActionsFacadeTest {

    @Test
    public void testPositionShip() {
    	String name = "TEST";
        int playerOrderId = 0;
        IPlayerActionsFacade facade = PlayerActionsFacade.getInstance();
        Player player = new Player(name, playerOrderId); // Supondo que a classe Player tenha um construtor padrão

        // Testando com uma posição válida
        assertTrue(facade.PositionShip(player, 0, 'A', 3, 1));

        // Testando com uma posição inválida
        assertFalse(facade.PositionShip(null, 0, 'A', 3, 1));
    }

    @Test
    public void testAttack() {
    	String name = "TEST";
        int playerOrderId = 0;
        IPlayerActionsFacade facade = PlayerActionsFacade.getInstance();
        Player playerAttacking = new Player(name, playerOrderId); // Supondo que a classe Player tenha um construtor padrão
        Player playerDefending = new Player(name, playerOrderId); // Supondo que a classe Player tenha um construtor padrão

        // Testando com jogadores válidos e coordenadas válidas
        assertTrue(facade.Attack(playerAttacking, playerDefending, 0, 'A'));

        // Testando com jogador atacante inválido
        assertFalse(facade.Attack(null, playerDefending, 0, 'A'));

        // Testando com jogador defensor inválido
        assertFalse(facade.Attack(playerAttacking, null, 0, 'A'));
    }

    @Test
    public void testValidateAttack() {
    	String name = "TEST";
        int playerOrderId = 0;
        IPlayerActionsFacade facade = PlayerActionsFacade.getInstance();
        Player playerAttacking = new Player(name, playerOrderId); // Supondo que a classe Player tenha um construtor padrão

        // Testando com uma posição válida
        assertTrue(facade.ValidateAttack(playerAttacking, 0, 'A'));

        // Testando com uma posição já atacada
        GameBoard attackMap = playerAttacking.GetTabuleiroAtaques();
        assertFalse(facade.ValidateAttack(playerAttacking, 0, 'A'));
    }
}
