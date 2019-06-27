import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class principal {

	public static void main(String[] args) {

		int nProcesso = 0, algoritmo = 0, tempo=0;
		
		Scanner ler = new Scanner(System.in);

		System.out.println("Qual algoritmo de Escalonamento deseja Utilizar?");
		System.out.println("1 = FIRST-FIT");
		System.out.println("2 = WORST-FIT");
		System.out.println("3 = BEST-FIT");
		System.out.println("4 = CIRCULAR-FIT");

		algoritmo = ler.nextInt();
		while (algoritmo > 4 || algoritmo <= 0) {// nao deixa o usuario dixitar uma opção diferente do menu
			System.err.println("OPÇÃO INVALIDA!!!");
			System.out.println('\n');

			System.out.println("Qual algoritmo de Escalonamento deseja Utilizar?");
			System.out.println("1 = FIRST-FIT");
			System.out.println("2 = WORST-FIT");
			System.out.println("3 = BEST-FIT");
			System.out.println("4 = CIRCULAR-FIT");

			algoritmo = ler.nextInt();

		}

		System.out.println("Quantos processos deseja Criar:");

		nProcesso = ler.nextInt();

		System.out.println("Tempo de espera par visualizar o Processamento:");

		tempo = ler.nextInt();

		switch (algoritmo) {

		case 1:
			new firstFit(nProcesso,tempo);
			break;
		case 2:
			new worstFit(nProcesso,tempo);
			break;
		case 3:
			new bestFit(nProcesso,tempo);
			
			break;
		case 4:
			new circularFit(nProcesso,tempo);
			

			break;

		}

	}



}
