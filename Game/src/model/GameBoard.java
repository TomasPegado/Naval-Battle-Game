package model;

class GameBoard 
{
    private int[][] board;

    GameBoard() 
    {
        // Inicializar o tabuleiro com água        
        board = new int[15][15];
        for (int i = 0; i < 15; i++) 
        {
            for (int j = 0; j < 15; j++) 
            {
                board[i][j] = 0; // Água
            }
        }
    }

    boolean PositionShip(int x, int y, Ship ship, boolean orientacao) 
    {
        // Verificar se as coordenadas são válidas e se o navio cabe no tabuleiro
        if (orientacao == true && x + tamanho <= board.length) 
        {
            // Posicionar o navio horizontalmente
            for (int i = x; i < x + tamanho; i++) 
            {
                board[i][y] = 1; // Parte do navio
            }
            return true;

        } 
        else if (orientacao == false && y + tamanho <= board[0].length) 
        {
            // Posicionar o navio verticalmente
            for (int j = y; j < y + tamanho; j++) 
            {
                board[x][j] = 1; // Parte do navio
            }
            return true;
        }
        return false; // Navio não pode ser posicionado
    }

    ShotResult shoot(String coordinate) 
    {
        // Implementação: Verifica o que foi atingido e atualiza o tabuleiro.
        return new ShotResult(); // Objeto de resultado customizado.
    }
}
