import java.util.ArrayList;

public class compactarMemoria {
	
	int nProcesso = 0, algoritmo = 0, tempo = 0, valorpid = 0, valor = 0, probCriar = 0, inicio = 0;
	int  contProcesso = 0, contCicloExe = 0;
	int contaTempo = 0, contaTamanho = 0,testaEspaco=0,menorEspaco=0;
	processo pro = null;
	espacoEmMemoria local = null;
	espacoEmMemoria espaçoLivre = null,espacoMaior=null,espacoMenor=null;
	


	ArrayList<processo> processosNaMemoria = new ArrayList<processo>();
	String[] memoria = new String[5000];
	ArrayList<processo> filaProcessos = new ArrayList<processo>();
	ArrayList<processo> encerrados = new ArrayList<processo>();
	ArrayList<espacoEmMemoria> espacoLivreNaMemoria = new ArrayList<espacoEmMemoria>();
	
	public compactarMemoria(){
		
		for (int i = 0; i < 5000; i++) {
			memoria[i] = ".";

		}
		
		for(int p=0;p<processosNaMemoria.size();p++) {// coloca todos os processos que estavam na memoria novamente na memoria sem espaço sobrando entre eles!
			
			for (int i = 0; i < memoria.length; i++) {
				if (memoria[i] == ".") {
					contaTamanho++;
					if (inicio == 0) {
						local = new espacoEmMemoria();
						local.setposiInicial(i);
						inicio++;
					}
					local.settamanho(contaTamanho);
					if (i == memoria.length - 1) {
						local.setposiFinal(i);						
						if (local.gettamanho() >= processosNaMemoria.get(p).gettamanho()) {
							for (int x = local.getposiInicial(); x < local
									.getposiInicial() +  processosNaMemoria.get(p).gettamanho(); x++) {
								memoria[x] = "¨";
							}
							memoria[local.getposiInicial()] = Integer.toString( processosNaMemoria.get(p).getpid());
							memoria[local.getposiInicial() +  processosNaMemoria.get(p).gettamanho()-1] = Integer
									.toString( processosNaMemoria.get(p).getpid());
							inicio = 0;
							contaTamanho = 0;
							break;
							
						}
					}

				} else {
					if (inicio != 0) {
						local.setposiFinal(i - 1);
						if (local.gettamanho() >=  processosNaMemoria.get(p).gettamanho()) {
							for (int x = local.getposiInicial(); x < local
									.getposiInicial() +  processosNaMemoria.get(p).gettamanho(); x++) {
								memoria[x] = "¨";
							}
							memoria[local.getposiInicial()] = Integer.toString( processosNaMemoria.get(p).getpid());
							memoria[local.getposiInicial() +  processosNaMemoria.get(p).gettamanho()-1] = Integer
									.toString( processosNaMemoria.get(p).getpid());
							inicio = 0;
							contaTamanho = 0;
							break;
						}
						inicio = 0;
						contaTamanho = 0;
						

					}

				}
				
			}
			
		}
		
	
		
		
		
	}

}