import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class VerificadorDeSolucoes {

	boolean contemSolucao(int matriz[][], ArrayList<int[][]> solucoesSemRepetidas) {

		int[][] solucaoExistenteNoArray;
		for (int i = 0; i < solucoesSemRepetidas.size(); i++) {
			solucaoExistenteNoArray = solucoesSemRepetidas.get(i);
			if (Arrays.deepEquals(solucaoExistenteNoArray, matriz)) {
				return true;
			}
		}

		return false;

	}

	public static void main(String[] args) throws FileNotFoundException {
		try {
			FileReader arq = new FileReader("./src/Solucoes.txt");
			BufferedReader lerArq = new BufferedReader(arq);

			VerificadorDeSolucoes verificador = new VerificadorDeSolucoes();

			String linha = lerArq.readLine(); // lê a primeira linha
			// a variável "linha" recebe o valor "null" quando o processo
			// de repetição atingir o final do arquivo texto

			ArrayList<int[][]> solucoesSemRepetidas = new ArrayList<int[][]>();

			int linhaTxt = 0;
			String caracteresLinha;

			int[][] matriz = new int[8][8];

			while (linha != null) {

				if (linhaTxt >= 8) {
//					System.out.println(verificador.contemSolucao(matriz, solucoesSemRepetidas));
					if (!verificador.contemSolucao(matriz, solucoesSemRepetidas)) {
						solucoesSemRepetidas.add(matriz);
					}

					matriz = new int[8][8];
					linhaTxt = 0;
				}

				caracteresLinha = linha.replace(" ", "");

				if (caracteresLinha.length() >= 8) {

					for (int i = 0; i < caracteresLinha.length(); i++) {
						matriz[linhaTxt][i] = Character.getNumericValue(caracteresLinha.charAt(i));
					}

					linhaTxt++;
				}
				
				linha = lerArq.readLine(); // lê da segunda até a última linha
			}

			arq.close();

			int[][] solucao;
//
//			for (int i = 0; i < solucoesSemRepetidas.size(); i++) {
//				solucao = solucoesSemRepetidas.get(i);
//				for (int j = 0; j < solucao.length; j++) {
//					for (int k = 0; k < solucao[j].length; k++) {
//						System.out.print(solucao[j][k] + " ");
//					}
//					System.out.println();
//				}
//				System.out.println();
//			}

			int quantSolucoesDiferentes = solucoesSemRepetidas.size();

			System.out.println("Quantidades de soluções é igual a " + quantSolucoesDiferentes);
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		}

	}
}
