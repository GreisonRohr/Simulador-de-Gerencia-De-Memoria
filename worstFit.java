import java.util.ArrayList;
import java.util.Random;

public class worstFit {
	
	int nProcesso = 0, tempo = 0, valorpid = 0, valor = 0, probCriar = 0, inicio = 0;
	int  contProcesso = 0, contCicloExe = 0;
	int  contaTamanho = 0;

	processo pro = null;
	espacoEmMemoria local = null;
	espacoEmMemoria espaçoLivre = null,espacoMaior=null;


	ArrayList<processo> processosNaMemoria = new ArrayList<processo>();
	String[] memoria = new String[5000];
	ArrayList<processo> filaProcessos = new ArrayList<processo>();
	ArrayList<processo> encerrados = new ArrayList<processo>();
	ArrayList<espacoEmMemoria> espacoLivreNaMemoria = new ArrayList<espacoEmMemoria>();

	
	
	public  worstFit(int nProcesso, int tempo) {
		for (int i = 0; i < 5000; i++) {
			memoria[i] = ".";

		}
		while (encerrados.size() != nProcesso) {// processador executa ate todos os processos encerar
			contCicloExe++;

			for (int f = 0; f < filaProcessos.size(); f++) {//conta o tempo que um processo fica na fila
				filaProcessos.get(f).settempoEsperaFila(filaProcessos.get(f).gettempoEsperaFila()+1);
			}
			
			if (processosNaMemoria.size() != 0) {//testa se tem processo na memoria 
				for (int contProNaMemoria = 0; contProNaMemoria < processosNaMemoria.size(); contProNaMemoria++) {

					if (processosNaMemoria.get(contProNaMemoria).getdecrementaTempoExe() == 0) {//se o processo executou seu tempo necessario remove o processo e libera espaço na memoria

						for (int contPosiMemoria = 0; contPosiMemoria < memoria.length; contPosiMemoria++) {// testa e apaga processo na memoria
							if (Integer.toString(processosNaMemoria.get(contProNaMemoria).getpid()).equals(memoria[contPosiMemoria])) {
								for (int e = contPosiMemoria; e < processosNaMemoria.get(contProNaMemoria).gettamanho() + contPosiMemoria  ; e++) {
									memoria[e] = ".";
								}
								
									encerrados.add(processosNaMemoria.get(contProNaMemoria));//adiciona o processo que estava na memoria a uma fila de encerrados
									processosNaMemoria.remove(contProNaMemoria);//remove o processo da memoria

								if(filaProcessos.size()!=0) {//testa para ver se possui algum processo na fila de espera
									
									for (int g = 0; g < memoria.length; g++) {// testa espaço livre na memoria

										if (memoria[g] == ".") {
											contaTamanho++;
											if (inicio == 0) {
												espaçoLivre = new espacoEmMemoria();
												espaçoLivre.setposiInicial(g);
												inicio++;
											}

											espaçoLivre.settamanho(contaTamanho);
											if (g == memoria.length - 1) {
												espaçoLivre.setposiFinal(g);
												espacoLivreNaMemoria.add(espaçoLivre);
												inicio = 0;
												contaTamanho = 0;

											}

										} else {
											if (inicio != 0) {
												espaçoLivre.setposiFinal(g - 1);// 13
												espacoLivreNaMemoria.add(espaçoLivre);
												inicio = 0;
												contaTamanho = 0;

											}

										}
									
									}
									espacoMaior=espacoLivreNaMemoria.get(0);//adiciona o primeiro espaço para comparar com os demais
									for (int i = 1; i < espacoLivreNaMemoria.size(); i++) {// testa e coloca processo na memoria
										if(espacoLivreNaMemoria.get(i).gettamanho()>espacoMaior.gettamanho()) {//compara os espaços livres para pegar o maior espaço
											espacoMaior=espacoLivreNaMemoria.get(i);
											
										}
										
										
									}
										for (int f = 0; f < filaProcessos.size(); f++) {// testa e coloca processo que estava aguardando na memoria
												if (espacoMaior.gettamanho() >= filaProcessos.get(f).gettamanho()) {
													for (int x = espacoMaior.getposiInicial(); x <espacoMaior.getposiInicial() + filaProcessos.get(f).gettamanho(); x++) {
														memoria[x] = "¨";
													}
													memoria[espacoMaior.getposiInicial()] = Integer.toString(filaProcessos.get(f).getpid());
													memoria[espacoMaior.getposiInicial() + filaProcessos.get(f).gettamanho()-1] = Integer
															.toString(filaProcessos.get(f).getpid());
													espacoLivreNaMemoria.removeAll(espacoLivreNaMemoria);//remove o espaço livre da lista de espaços 
													processosNaMemoria.add(filaProcessos.get(f));//adiciona o processo na memoria
													filaProcessos.remove(f);//remove processo da fila de espera
													
													break;
												}
											
										}
										
										
									

									
								}
								
								
								break;

							}
						}
					
									
					} else {//decrementa o tempo de execução dos processos
						processosNaMemoria.get(contProNaMemoria).setdecrementaTempoExe(processosNaMemoria.get(contProNaMemoria).getdecrementaTempoExe() - 1);
						
						

					}
					
				}

			}

			if (contProcesso != nProcesso) {//testa se foram criados todos os processos solicitados
				Random probabilidade = new Random();
				probCriar = probabilidade.nextInt(100);
				if (probCriar <= 20) {// probabilidade de 20% de criar processo
					contProcesso++;
					pro = new processo();// cria processo
					valorpid++;
					pro.setpid(valorpid);// adiciona o pid no processo
					Random aleatorio = new Random();
					valor = aleatorio.nextInt(900) + 100;// sorteia tamanho do processo
					pro.settamanho(valor);// adiciona o tamanho do processo
					Random geraTempo = new Random();
					int gTempo = geraTempo.nextInt(259) + 1;// sorteia tempo em que o processo fica na memoria
					pro.settempoExe(gTempo);// adiciona o tempo de execução do processo
					pro.setdecrementaTempoExe(gTempo);
					

					for (int i = 0; i < memoria.length; i++) {// testa espaço livre na memoria

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
								espacoLivreNaMemoria.add(local);
								inicio = 0;
								contaTamanho = 0;

							}

						} else {
							if (inicio != 0) {
								local.setposiFinal(i - 1);
								espacoLivreNaMemoria.add(local);
								inicio = 0;
								contaTamanho = 0;

							}
								
								

							}

						}
					
					espacoMaior=espacoLivreNaMemoria.get(0);
					
					
					for (int i = 1; i < espacoLivreNaMemoria.size(); i++) {// testa e coloca processo na memoria
						if(espacoLivreNaMemoria.get(i).gettamanho()>espacoMaior.gettamanho()) {
							espacoMaior=espacoLivreNaMemoria.get(i);
							
						}
						
						
					}
					
						if (espacoMaior.gettamanho() >= pro.gettamanho()) {//se o tamanho do processo for maior ou igual ao espaço maior encontrado adiociona o processo na memoria
							for (int x = espacoMaior.getposiInicial(); x < espacoMaior
									.getposiInicial() + pro.gettamanho(); x++) {
								memoria[x] = "¨";
							}
							memoria[espacoMaior.getposiInicial()] = Integer.toString(valorpid);
							memoria[espacoMaior.getposiInicial() + pro.gettamanho()-1] = Integer
									.toString(valorpid);
							processosNaMemoria.add(pro);
							espacoLivreNaMemoria.removeAll(espacoLivreNaMemoria);
						} else {
							filaProcessos.add(pro);
							pro.settempoEsperaFila(1);
							espacoLivreNaMemoria.removeAll(espacoLivreNaMemoria);

						}
					
						
					}
			
					

					

				}

			

			System.out.println('\n');

			System.out.println("WORST-FIT");
			// mostra a area de memoria
			for (int i = 0; i < 100; i++) {
				int a = i * 50;
				int b = (i + 1) * 50;
				for (; a < b; a++) {

					System.out.print(memoria[a] + "|");

				}
				System.out.print('\n');

			}

			new listaRelatorio(nProcesso,tempo,contCicloExe,processosNaMemoria,filaProcessos,encerrados);

			try{//pausa o ciclo para poder ver o status a cada ciclo
			      Thread.sleep(tempo*1000);
			}catch(Exception e){
			      System.out.println("Deu erro!");
			}	
			
		}
		
		
	
		
	}

}
