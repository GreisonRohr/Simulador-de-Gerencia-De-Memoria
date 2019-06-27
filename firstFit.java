import java.util.ArrayList;
import java.util.Random;

public class firstFit {
	
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

	
	
	public  firstFit(int nProcesso,int tempo) {
		for (int i = 0; i < 5000; i++) {
			memoria[i] = ".";

		}
		while (encerrados.size() != nProcesso) {// processador executa ate todos os processos encerar
			contCicloExe++;//conta os ciclos de execução
			
			for (int f = 0; f < filaProcessos.size(); f++) {//conta tempo de espera de um processo na fila
				filaProcessos.get(f).settempoEsperaFila(filaProcessos.get(f).gettempoEsperaFila()+1);
			}


			if (processosNaMemoria.size() != 0) {//verifica se tem processos na memoria
				for (int contProNaMemoria = 0; contProNaMemoria < processosNaMemoria.size(); contProNaMemoria++) {

					if (processosNaMemoria.get(contProNaMemoria).getdecrementaTempoExe() == 0) {//testa tempo de execução dos processos 

						for (int contPosiMemoria = 0; contPosiMemoria < memoria.length; contPosiMemoria++) {// testa e apaga processo na memoria
							if (Integer.toString(processosNaMemoria.get(contProNaMemoria).getpid()).equals(memoria[contPosiMemoria])) {//compara o pid do processo com a posiçao da memoria se for igual seta a memoria com "."
								for (int e = contPosiMemoria; e < processosNaMemoria.get(contProNaMemoria).gettamanho() + contPosiMemoria ; e++) {
									memoria[e] = ".";
								}
								

								encerrados.add(processosNaMemoria.get(contProNaMemoria));//adiciona o processo a uma fila de encerrados
								processosNaMemoria.remove(contProNaMemoria);//remove o processo do array de memoria


								


								if(filaProcessos.size()!=0) {// testa se possui processo na fila para poder alocar na memoria
									for (int g = 0; g < memoria.length; g++) {// testa espaço livre na memoria

										if (memoria[g] == ".") {
											contaTamanho++;
											if (inicio == 0) {
												espaçoLivre = new espacoEmMemoria();
												espaçoLivre.setposiInicial(g);
												inicio++;
											}

											espaçoLivre.settamanho(contaTamanho);
											if (g == memoria.length - 1) {//testa area de memoria vazia
												espaçoLivre.setposiFinal(g);
												if(filaProcessos.size()!=0) {
													for (int f = 0; f < filaProcessos.size(); f++) {
														
															if (espaçoLivre.gettamanho() >= filaProcessos.get(f).gettamanho()) {// compara o tamanho do espaço livre com o do processo para alocar na memoria o processo que estava agurdando
																for (int x = espaçoLivre.getposiInicial(); x <espaçoLivre.getposiInicial() + filaProcessos.get(f).gettamanho(); x++) {
																	memoria[x] = "¨";
																}
																memoria[espaçoLivre.getposiInicial()] = Integer.toString(filaProcessos.get(f).getpid());
																memoria[espaçoLivre.getposiInicial() + filaProcessos.get(f).gettamanho()-1] = Integer
																		.toString(filaProcessos.get(f).getpid());
																processosNaMemoria.add(filaProcessos.get(f));
																filaProcessos.remove(f);
																break;
																
															}
															
															
														

														
													}
													
													
												}
												inicio = 0;
												contaTamanho = 0;

											}

										} else {
											if (inicio != 0) {//testa area de memoria vazia
												espaçoLivre.setposiFinal(g - 1);
												if(filaProcessos.size()!=0) {
													for (int f = 0; f < filaProcessos.size(); f++) {// testa e coloca processo que estava aguardando na memoria
														
															if (espaçoLivre.gettamanho() >= filaProcessos.get(f).gettamanho()) {//compara o tamanho do espaço livre com o do processo para alocar na memoria o processo que estava agurdando
																for (int x = espaçoLivre.getposiInicial(); x <espaçoLivre.getposiInicial() + filaProcessos.get(f).gettamanho(); x++) {
																	memoria[x] = "¨";
																}
																memoria[espaçoLivre.getposiInicial()] = Integer.toString(filaProcessos.get(f).getpid());
																memoria[espaçoLivre.getposiInicial() + filaProcessos.get(f).gettamanho()-1] = Integer
																		.toString(filaProcessos.get(f).getpid());
																processosNaMemoria.add(filaProcessos.get(f));
																filaProcessos.remove(f);
																break;
															}
														
															
														

														
													}
													
													
												}
												inicio = 0;
												contaTamanho = 0;

											}

										}
									}
									
								}
								
								
								break;

							}
						}
					
									
					} else {//decrementa o tempo de execução dos processos que estao na memoria
						processosNaMemoria.get(contProNaMemoria).setdecrementaTempoExe(processosNaMemoria.get(contProNaMemoria).getdecrementaTempoExe() - 1);

					}
					
					
					
					

				}

			}

			if (contProcesso != nProcesso) {// testa se o total de processsos criados foi o mesmo solicitado
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
					

					for (int i = 0; i < memoria.length; i++) {// testa espaço livre na memoria para alocar processo criado 

						if (memoria[i] == ".") {
							contaTamanho++;
							if (inicio == 0) {
								local = new espacoEmMemoria();
								local.setposiInicial(i);
								inicio++;
							}

							local.settamanho(contaTamanho);
							if (i == memoria.length - 1) {//testa area de memoria vazia
								local.setposiFinal(i);
								if (local.gettamanho() >= pro.gettamanho()) {
									for (int x = local.getposiInicial(); x < local
											.getposiInicial() + pro.gettamanho(); x++) {
										memoria[x] = "¨";
									}
									memoria[local.getposiInicial()] = Integer.toString(valorpid);
									memoria[local.getposiInicial() + pro.gettamanho()-1] = Integer
											.toString(valorpid);
									processosNaMemoria.add(pro);
									inicio = 0;
									contaTamanho = 0;
									break;
									
								} else {
									filaProcessos.add(pro);//adiciona o processo na fila se nao houver espaço disponivel suficiente
									inicio = 0;
									contaTamanho = 0;

								}
								
								

							}

						} else {
							if (inicio != 0) {
								local.setposiFinal(i - 1);
								if (local.gettamanho() >= pro.gettamanho()) {
									for (int x = local.getposiInicial(); x < local
											.getposiInicial() + pro.gettamanho(); x++) {
										memoria[x] = "¨";
									}
									memoria[local.getposiInicial()] = Integer.toString(valorpid);
									memoria[local.getposiInicial() + pro.gettamanho()-1] = Integer
											.toString(valorpid);
									processosNaMemoria.add(pro);
									inicio = 0;
									contaTamanho = 0;
									break;
								} else {// se nao possui nem um espaço em memoria adiciona o processo a uma fila de espera 
									if (i == memoria.length - 1) {
										filaProcessos.add(pro);
										
									}
								}
								inicio = 0;
								contaTamanho = 0;
								

							}

						}
						
					}
					

					

				}

			}

			System.out.println('\n');

			System.out.println("FIRST-FIT");
			// mostra a area de memoria
			for (int i = 0; i < 100; i++) {
				int a = i * 50;
				int b = (i + 1) * 50;
				for (; a < b; a++) {

					System.out.print(memoria[a] + "|");

				}
				System.out.print('\n');

			}

			new listaRelatorio(nProcesso,tempo,contCicloExe,processosNaMemoria,filaProcessos,encerrados);// Lista o relatorio/status a cada ciclo
			
			try{//pausa o ciclo para poder ver o status a cada ciclo
			      Thread.sleep(tempo*1000);
			}catch(Exception e){
			      System.out.println("Deu erro!");
			}	

			

		}
		
	
		
	}




}
