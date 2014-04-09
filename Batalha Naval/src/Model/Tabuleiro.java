package Model;

import java.util.Random;

public class Tabuleiro {
	
	public final static int ROWS = 10;
	public final static int COLUMNS = 10;
	private int pontos = 15;
	private Coordenada[][] matriz = new Coordenada[ROWS][COLUMNS];
	private int navios = 13;

	public Tabuleiro() {
		for (int x = 0; x < matriz.length; x++)
			for (int y = 0; y < matriz[x].length; y++)
				matriz[x][y] = new Coordenada();
	}

	public Boolean fimDeJogo() {
		return ((navios < 1) || (pontos < 1) ? true : false) ;
	}

	public void iniciar() {
		apontarNavio(new PortaAvioes());
		apontarNavio(new Destroyer());
		apontarNavio(new Destroyer());
		apontarNavio(new Fragata());
		apontarNavio(new Fragata());
		apontarNavio(new Torpedeiro());
		apontarNavio(new Torpedeiro());
		apontarNavio(new Torpedeiro());
		apontarNavio(new Submarino());
		apontarNavio(new Submarino());
		apontarNavio(new Submarino());
		apontarNavio(new Submarino());
		apontarNavio(new Submarino());
	}
	
	//Este metodo indica a posicao da embarcacao.
	public void apontarNavio (Navio navio)
	{
		Boolean livre = false;
		Random r = new Random();
		int linha = 0;
		int coluna = 0;
		
		while(!livre) {
			livre = true;

			if(r.nextInt(2) > 0) 
				navio.setVertical(true);
			else
				navio.setVertical(false);

			if(navio.getVertical()) {
				linha = r.nextInt(10 - (navio.embarcacao.size() - 1));
				coluna = r.nextInt(10);

				for(int i = linha; i < 10; i++)
					if(matriz[i][coluna].getNavio() != null)
						livre = false;

			} else {
				coluna = r.nextInt(10 - (navio.embarcacao.size() - 1));
				linha = r.nextInt(10);

				for(int i = coluna; i < 10; i++)
					if(matriz[linha][i].getNavio() != null)
						livre = false;
			}
		}

		if(navio.getVertical()) {
			for(int i = linha; i < linha + (navio.embarcacao.size()); i++) {
				int parte = 1;
				matriz[i][coluna].setNavio(navio);
				matriz[i][coluna].setParte(parte);
				parte ++;
			}
		} else {
			for(int i = coluna; i < coluna + (navio.embarcacao.size()); i++) {
				int parte = 1;
				matriz[linha][i].setNavio(navio);
				matriz[linha][i].setParte(parte);
				parte ++;
			}
		}
	}

	public String tipoNavio(int x, int y) {
		return matriz[x][y].getNavio().getClass().getSimpleName().toLowerCase();
	}

	public int fire(int x, int y) {
		if(matriz[x][y].aJogada()) {
			if(matriz[x][y].getNavio() != null) {
				matriz[x][y].getNavio().destruir(matriz[x][y].getPosicao());

				if(matriz[x][y].getNavio().foiDestruido()) {
					matriz[x][y].fogo();
					navios--;
					pontos += 7;
					return 7;
				} else {
					matriz[x][y].fogo();
					pontos += 2;
					return 2;	
				}					
			} else {
				matriz[x][y].fogo();
				pontos -= 1;
				return -1;
			}
		} else 
			return 0;
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

	public Coordenada[][] getMatriz() {
		return matriz;
	}

	public void setMatriz(Coordenada[][] matriz) {
		this.matriz = matriz;
	}
}
