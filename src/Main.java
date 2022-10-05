import java.util.ArrayList;
import java.util.Random;

public class Main {
	public int sobAtaque(int matriz[][], int linha, int coluna) {
		int quantAtaquesNaPosicao = 0;

		// verificar na linha
		for (int j = 0; j < matriz[linha].length; j++) {
			if (j != coluna && matriz[linha][j] == 1) {
				quantAtaquesNaPosicao++;
			}
		}

		// verificar na coluna
		for (int i = 0; i < matriz.length; i++) {
			if (i != linha && matriz[i][coluna] == 1) {
				quantAtaquesNaPosicao++;
			}
		}
//		for (int i = 0; i < matriz.length; i++) {
//			for (int j = 0; j < matriz[i].length; j++) {
//
//// verificando na linha ou na coluna se tem sob ataque
//				if (i != linha || j != coluna) {
//
//					if (i == linha && matriz[i][j] == 1) {
//						return true;
//					}
//
//					if (j == coluna && matriz[i][j] == 1) {
//						return true;
//					}
//				}
//			}
//		}

		int linhaAux = linha + 1;
		int colunaAux = coluna + 1;

		while (linhaAux < 8 && colunaAux < 8) {
			if (matriz[linhaAux][colunaAux] == 1) {
				quantAtaquesNaPosicao++;
			}
			linhaAux++;
			colunaAux++;
		}

		linhaAux = linha - 1;
		colunaAux = coluna - 1;

		while (linhaAux >= 0 && colunaAux >= 0) {
			if (matriz[linhaAux][colunaAux] == 1) {
				quantAtaquesNaPosicao++;
			}
			linhaAux--;
			colunaAux--;
		}

		linhaAux = linha + 1;
		colunaAux = coluna - 1;

		while (linhaAux < 8 && colunaAux >= 0) {
			if (matriz[linhaAux][colunaAux] == 1) {
				quantAtaquesNaPosicao++;
			}
			linhaAux++;
			colunaAux--;
		}

		linhaAux = linha - 1;
		colunaAux = coluna + 1;

		while (linhaAux >= 0 && colunaAux < 8) {
			if (matriz[linhaAux][colunaAux] == 1) {
				quantAtaquesNaPosicao++;
			}
			linhaAux--;
			colunaAux++;
		}

		return quantAtaquesNaPosicao;
	}

	public static void main(String[] args) {
		Main rainhas = new Main();
		int[][] matriz = new int[8][8];

		final int QUANTIDADE_RAINHAS = 8;

		int quantRainhasColocados = 0;

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				System.out.print(matriz[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println();

		int menorQuantAtaquesAnterior;

		int quantAtaquesAtual = 0;
		
		ArrayList<int[]> sobAtaqueEmpatados = new ArrayList<>(); 
		
		int[] vetorPosicaoEscolhida = new int[2];
		
		for (int i = 0; i < QUANTIDADE_RAINHAS; i++) {
			menorQuantAtaquesAnterior = Integer.MAX_VALUE;
			sobAtaqueEmpatados.clear();

			// descobrir qual � a menor quantAtaques em uma posi��o 
			for (int k = 0; k < matriz.length; k++) {
				for (int j = 0; j < matriz[k].length; j++) {
					quantAtaquesAtual = rainhas.sobAtaque(matriz, k, j);
					if (quantAtaquesAtual < menorQuantAtaquesAnterior && matriz[k][j] != 1) {
						menorQuantAtaquesAnterior = quantAtaquesAtual;
					}
				}
			}	
			
			// verificar se tem mais de uma menor em quantAtaques em v�rias posi��es
			for (int k = 0; k < matriz.length; k++) {
				for (int j = 0; j < matriz[k].length; j++) {
					quantAtaquesAtual = rainhas.sobAtaque(matriz, k, j);
					if (quantAtaquesAtual == menorQuantAtaquesAnterior && matriz[k][j] != 1) {
						int[] vetor = new int[2];
						vetor[0] = k;
						vetor[1] = j;
						sobAtaqueEmpatados.add(vetor);						
					}
				}
			}
			
			if (menorQuantAtaquesAnterior == Integer.MAX_VALUE || menorQuantAtaquesAnterior > 0) {
				break;
			}
						
			vetorPosicaoEscolhida = sobAtaqueEmpatados.get(new Random().nextInt(sobAtaqueEmpatados.size()));
//			System.out.println(vetorPosicaoEscolhida[0] + " " + vetorPosicaoEscolhida[1]);
			
			matriz[vetorPosicaoEscolhida[0]][vetorPosicaoEscolhida[1]] = 1;
			quantRainhasColocados++;				
			
		}

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				System.out.print(matriz[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println("Quantidade de rainhas no mapa: " + quantRainhasColocados);

	}

}