import java.util.ArrayList;
import java.util.Random;

public class circularFit {
			
		int nProcesso = 0, tempo = 0, valorpid = 0, valor = 0, probCriar = 0, inicio = 0;
		int  contProcesso = 0, contCicloExe = 0,posicaoIgual=0,contEspacoPequeno=0;
		int  contaTamanho = 0,guardaPosicao=0,guardaPosicaoInicial=0;

		processo pro = null;
		espacoEmMemoria local = null;
		espacoEmMemoria espa�oLivre = null,espacoMaior=null;


		ArrayList<processo> processosNaMemoria = new ArrayList<processo>();
		String[] memoria = new String[5000];
		ArrayList<processo> filaProcessos = new ArrayList<processo>();
		ArrayList<processo> encerrados = new ArrayList<processo>();
		ArrayList<espacoEmMemoria> espacoLivreNaMemoria = new ArrayList<espacoEmMemoria>();

		
		
		public  circularFit(int nProcesso,int tempo) {
			for (int i = 0; i < 5000; i++) {
				memoria[i] = ".";

			}
			while (encerrados.size() != nProcesso) {// processador executa ate todos os processos encerar
				contCicloExe++;//conta os ciclos de execu��o
				
				for (int f = 0; f < filaProcessos.size(); f++) {//conta tempo de espera de um processo na fila
					filaProcessos.get(f).settempoEsperaFila(filaProcessos.get(f).gettempoEsperaFila()+1);
				}

				if (processosNaMemoria.size() != 0) {//verifica se tem processos na memoria
					for (int contProNaMemoria = 0; contProNaMemoria < processosNaMemoria.size(); contProNaMemoria++) {

						if (processosNaMemoria.get(contProNaMemoria).getdecrementaTempoExe() == 0) {//testa tempo de execu��o dos processos 

							for (int contPosiMemoria = 0; contPosiMemoria < memoria.length; contPosiMemoria++) {// testa e apaga processo na memoria
								if (Integer.toString(processosNaMemoria.get(contProNaMemoria).getpid()).equals(memoria[contPosiMemoria])) {//compara o pid do processo com a posi�ao da memoria
									for (int e = contPosiMemoria; e < processosNaMemoria.get(contProNaMemoria).gettamanho() + contPosiMemoria ; e++) {//se for igual seta a memoria com "."
										memoria[e] = ".";
									}
									
									encerrados.add(processosNaMemoria.get(contProNaMemoria));
									processosNaMemoria.remove(contProNaMemoria);


								


									if(filaProcessos.size()!=0) {
										for (int g = 0; g < memoria.length; g++) {// testa espa�o livre na memoria

											if (memoria[g] == ".") {
												contaTamanho++;
												if (inicio == 0) {
													espa�oLivre = new espacoEmMemoria();
													espa�oLivre.setposiInicial(g);
													inicio++;
												}

												espa�oLivre.settamanho(contaTamanho);
												if (g == memoria.length - 1) {
													espa�oLivre.setposiFinal(g);
													if(espacoLivreNaMemoria.size()!=0) {//se possui espa�o na memoria 
														for(int i=0;i<espacoLivreNaMemoria.size();i++) {//testa se espa�o encontrado ja possui na lista de espa�o livre
															if(espacoLivreNaMemoria.get(i).getposiInicial()==espa�oLivre.getposiInicial()) {
																posicaoIgual++;
																
															}
															
														}
														if(posicaoIgual==0) {
															espacoLivreNaMemoria.add(espa�oLivre);//se nao tem na fila de espa�o livre adiciona novo espa�o
															
														}
														
														guardaPosicaoInicial=guardaPosicao;//guarda posi�ao inicial pasra depois poder processeguir da ultima posi��o utilizada
														for(int f=0;f<filaProcessos.size();f++) {//coloca processo que estava na fila, na memoria
															for(int i=guardaPosicao;i<espacoLivreNaMemoria.size();i++) {
																if(i==espacoLivreNaMemoria.size()) {
																	for(int p=0;p<guardaPosicaoInicial;p++) {
																		if(filaProcessos.get(f).gettamanho()<=espacoLivreNaMemoria.get(p).gettamanho()) {
																			for (int x = espacoLivreNaMemoria.get(p).getposiInicial(); x <espacoLivreNaMemoria.get(p).getposiInicial() + filaProcessos.get(f).gettamanho(); x++) {
																				memoria[x] = "�";
																			}
																			memoria[espacoLivreNaMemoria.get(p).getposiInicial()] = Integer.toString(filaProcessos.get(f).getpid());
																			memoria[espacoLivreNaMemoria.get(p).getposiInicial() + filaProcessos.get(f).gettamanho()-1] = Integer
																					.toString(filaProcessos.get(f).getpid());
																			espacoLivreNaMemoria.remove(p);//remove o espa�o livre, pois foi ocupado
																			processosNaMemoria.add(filaProcessos.get(f));//adiciona processo na memoria
																			filaProcessos.remove(f);//remove o processo da fila de espera
																			break;
															
																	}
																	}
																	
																}
																if(filaProcessos.get(f).gettamanho()<=espacoLivreNaMemoria.get(i).gettamanho()) {
																		for (int x = espacoLivreNaMemoria.get(i).getposiInicial(); x <espacoLivreNaMemoria.get(i).getposiInicial() + filaProcessos.get(f).gettamanho(); x++) {
																			memoria[x] = "�";
																		}
																		memoria[espacoLivreNaMemoria.get(i).getposiInicial()] = Integer.toString(filaProcessos.get(f).getpid());
																		memoria[espacoLivreNaMemoria.get(i).getposiInicial() + filaProcessos.get(f).gettamanho()-1] = Integer
																				.toString(filaProcessos.get(f).getpid());
																		espacoLivreNaMemoria.remove(i);//remove o espa�o livre da fila de espa�os da memoria
																		processosNaMemoria.add(filaProcessos.get(f));//adiciona o processo que estava aguardando, na memoria
																		filaProcessos.remove(f);//remove o processo da memoria
																		break;
																		
																	
																	
																	
																}
																
																
																
															}
															
														}
														
													}else {

														for (int f = 0; f < filaProcessos.size(); f++) {// testa e coloca processo que estava aguardando na memoria
															
																if (espa�oLivre.gettamanho() >= filaProcessos.get(f).gettamanho()) {
																	for (int x = espa�oLivre.getposiInicial(); x <espa�oLivre.getposiInicial() + filaProcessos.get(f).gettamanho(); x++) {
																		memoria[x] = "�";
																	}
																	memoria[espa�oLivre.getposiInicial()] = Integer.toString(filaProcessos.get(f).getpid());
																	memoria[espa�oLivre.getposiInicial() + filaProcessos.get(f).gettamanho()-1] = Integer
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
												if (inicio != 0) {
													espa�oLivre.setposiFinal(g - 1);// 13
													if(espacoLivreNaMemoria.size()!=0) {//fazer aqui(pronto precisa testar!!!)
														for(int i=0;i<espacoLivreNaMemoria.size();i++) {
															if(espacoLivreNaMemoria.get(i).getposiInicial()==espa�oLivre.getposiInicial()) {
																posicaoIgual++;
																
															}
															
														}
														if(posicaoIgual==0) {
															espacoLivreNaMemoria.add(espa�oLivre);
															
														}
														
														guardaPosicaoInicial=guardaPosicao;
														for(int f=0;f<filaProcessos.size();f++) {//coloca processo que estava na fila, na memoria
															for(int i=guardaPosicao;i<espacoLivreNaMemoria.size();i++) {
																if(i==espacoLivreNaMemoria.size()) {
																	for(int p=0;p<guardaPosicaoInicial;p++) {
																		if(filaProcessos.get(f).gettamanho()<=espacoLivreNaMemoria.get(p).gettamanho()) {
																			for (int x = espacoLivreNaMemoria.get(p).getposiInicial(); x <espacoLivreNaMemoria.get(p).getposiInicial() + filaProcessos.get(f).gettamanho(); x++) {
																				memoria[x] = "�";
																			}
																			memoria[espacoLivreNaMemoria.get(p).getposiInicial()] = Integer.toString(filaProcessos.get(f).getpid());
																			memoria[espacoLivreNaMemoria.get(p).getposiInicial() + filaProcessos.get(f).gettamanho()-1] = Integer
																					.toString(filaProcessos.get(f).getpid());
																			espacoLivreNaMemoria.remove(p);
																			processosNaMemoria.add(filaProcessos.get(f));
																			filaProcessos.remove(f);
																			break;
															
																	}
																	}
																	
																}
																if(filaProcessos.get(f).gettamanho()<=espacoLivreNaMemoria.get(i).gettamanho()) {
																		for (int x = espacoLivreNaMemoria.get(i).getposiInicial(); x <espacoLivreNaMemoria.get(i).getposiInicial() + filaProcessos.get(f).gettamanho(); x++) {
																			memoria[x] = "�";
																		}
																		memoria[espacoLivreNaMemoria.get(i).getposiInicial()] = Integer.toString(filaProcessos.get(f).getpid());
																		memoria[espacoLivreNaMemoria.get(i).getposiInicial() + filaProcessos.get(f).gettamanho()-1] = Integer
																				.toString(filaProcessos.get(f).getpid());
																		espacoLivreNaMemoria.remove(i);//remove o espa�o livre da fila de espa�os da memoria
																		processosNaMemoria.add(filaProcessos.get(f));//adiciona o processo que estava na fila, na memoria
																		filaProcessos.remove(f);//remove o processo que estava na fila
																		break;
																		
																	
																	
																	
																}
																
																
																
															}
															
														}
														
													}else {

														for (int f = 0; f < filaProcessos.size(); f++) {// testa e coloca processo que estava aguardando na memoria
															
																if (espa�oLivre.gettamanho() >= filaProcessos.get(f).gettamanho()) {//testa o se o tamanho do processo que estava na fila � maior ou igual ao espa�o livre
																	for (int x = espa�oLivre.getposiInicial(); x <espa�oLivre.getposiInicial() + filaProcessos.get(f).gettamanho(); x++) {
																		memoria[x] = "�";
																	}
																	memoria[espa�oLivre.getposiInicial()] = Integer.toString(filaProcessos.get(f).getpid());
																	memoria[espa�oLivre.getposiInicial() + filaProcessos.get(f).gettamanho()-1] = Integer
																			.toString(filaProcessos.get(f).getpid());
																	processosNaMemoria.add(filaProcessos.get(f));//adiciona o processo que estava aguardando 
																	filaProcessos.remove(f);//remove o processo da fila de espera da memoria
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
						
										
						} else {//decrementa o tempo de execu��o de cada processo na memoria 
							processosNaMemoria.get(contProNaMemoria).setdecrementaTempoExe(processosNaMemoria.get(contProNaMemoria).getdecrementaTempoExe() - 1);

						}
						
						
						
						

					}

				}

				if (contProcesso != nProcesso) {
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
						pro.settempoExe(gTempo);// adiciona o tempo de execu��o do processo
						pro.setdecrementaTempoExe(gTempo);
						

						for (int i = 0; i < memoria.length; i++) {// testa espa�o livre na memoria

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
									//fazer aqui
									if(espacoLivreNaMemoria.size()!=0) {
										for(int e=0;e<espacoLivreNaMemoria.size();e++) {
											if(espacoLivreNaMemoria.get(e).getposiInicial()==local.getposiInicial()) {
												posicaoIgual++;
												
											}
											
										}
										if(posicaoIgual==0) {
											espacoLivreNaMemoria.add(local);
											
										}
										
										guardaPosicaoInicial=guardaPosicao;
										for(int g=guardaPosicao;g<espacoLivreNaMemoria.size();g++) {
											if(g==espacoLivreNaMemoria.size()) {
												for(int p=0;p<guardaPosicaoInicial;p++) {
													if(espacoLivreNaMemoria.get(p).gettamanho()>=pro.gettamanho()) {
														
														for (int x = espacoLivreNaMemoria.get(p).getposiInicial(); x < espacoLivreNaMemoria.get(p)
																.getposiInicial() + pro.gettamanho(); x++) {
															memoria[x] = "�";
														}
														memoria[espacoLivreNaMemoria.get(p).getposiInicial()] = Integer.toString(valorpid);
														memoria[espacoLivreNaMemoria.get(p).getposiInicial() + pro.gettamanho()-1] = Integer
																.toString(valorpid);
														processosNaMemoria.add(pro);
														inicio = 0;
														contaTamanho = 0;
														espacoLivreNaMemoria.remove(p);
														break;
														
														
														
													
													
												}
											}
												
											if(espacoLivreNaMemoria.get(g).gettamanho()>=pro.gettamanho()) {
												
												for (int x = espacoLivreNaMemoria.get(g).getposiInicial(); x < espacoLivreNaMemoria.get(g)
														.getposiInicial() + pro.gettamanho(); x++) {
													memoria[x] = "�";
												}
												memoria[espacoLivreNaMemoria.get(g).getposiInicial()] = Integer.toString(valorpid);
												memoria[espacoLivreNaMemoria.get(g).getposiInicial() + pro.gettamanho()-1] = Integer
														.toString(valorpid);
												processosNaMemoria.add(pro);
												inicio = 0;
												contaTamanho = 0;
												espacoLivreNaMemoria.remove(g);
												break;
												
												
												
											}
											else {
											contEspacoPequeno++;
												if(contEspacoPequeno==espacoLivreNaMemoria.size()) {
													filaProcessos.add(pro);
												}
													
												}
											}
											
										}
										
									
									}else {
										if (local.gettamanho() >= pro.gettamanho()) {
											for (int x = local.getposiInicial(); x < local
													.getposiInicial() + pro.gettamanho(); x++) {
												memoria[x] = "�";
											}
											memoria[local.getposiInicial()] = Integer.toString(valorpid);
											memoria[local.getposiInicial() + pro.gettamanho()-1] = Integer
													.toString(valorpid);
											processosNaMemoria.add(pro);
											inicio = 0;
											contaTamanho = 0;
											break;
											
										} else {
											filaProcessos.add(pro);
											inicio = 0;
											contaTamanho = 0;
											//espacoLivreNaMemoria.remove(i);

										}
										
									}
								}

							} else {
								if (inicio != 0) {
									local.setposiFinal(i - 1);// 13
									if(espacoLivreNaMemoria.size()!=0) {
										for(int e=0;e<espacoLivreNaMemoria.size();e++) {
											if(espacoLivreNaMemoria.get(e).getposiInicial()==local.getposiInicial()) {
												posicaoIgual++;
												
											}
											
										}
										if(posicaoIgual==0) {
											espacoLivreNaMemoria.add(local);
											
										}
										
										guardaPosicaoInicial=guardaPosicao;
										for(int g=guardaPosicao;g<espacoLivreNaMemoria.size();g++) {
											if(g==espacoLivreNaMemoria.size()) {
												for(int p=0;p<guardaPosicaoInicial;p++) {
													if(espacoLivreNaMemoria.get(p).gettamanho()>=pro.gettamanho()) {
														
														for (int x = espacoLivreNaMemoria.get(p).getposiInicial(); x < espacoLivreNaMemoria.get(p)
																.getposiInicial() + pro.gettamanho(); x++) {
															memoria[x] = "�";
														}
														memoria[espacoLivreNaMemoria.get(p).getposiInicial()] = Integer.toString(valorpid);
														memoria[espacoLivreNaMemoria.get(p).getposiInicial() + pro.gettamanho()-1] = Integer
																.toString(valorpid);
														processosNaMemoria.add(pro);
														inicio = 0;
														contaTamanho = 0;
														espacoLivreNaMemoria.remove(p);
														break;
														
														
														
													
													
												}
											}
												
											if(espacoLivreNaMemoria.get(g).gettamanho()>=pro.gettamanho()) {
												
												for (int x = espacoLivreNaMemoria.get(g).getposiInicial(); x < espacoLivreNaMemoria.get(g)
														.getposiInicial() + pro.gettamanho(); x++) {
													memoria[x] = "�";
												}
												memoria[espacoLivreNaMemoria.get(g).getposiInicial()] = Integer.toString(valorpid);
												memoria[espacoLivreNaMemoria.get(g).getposiInicial() + pro.gettamanho()-1] = Integer
														.toString(valorpid);
												processosNaMemoria.add(pro);
												inicio = 0;
												contaTamanho = 0;
												espacoLivreNaMemoria.remove(g);
												break;
												
												
												
											}
											else {
											contEspacoPequeno++;
												if(contEspacoPequeno==espacoLivreNaMemoria.size()) {
													filaProcessos.add(pro);//adiciona o processo a fila de espera se nao tiver espa�o diponivel suficiente na memoria
												}
													
												}
											}
											
										}
										
										
										
										}else {
											if (local.gettamanho() >= pro.gettamanho()) {
												for (int x = local.getposiInicial(); x < local
														.getposiInicial() + pro.gettamanho(); x++) {
													memoria[x] = "�";
												}
												memoria[local.getposiInicial()] = Integer.toString(valorpid);
												memoria[local.getposiInicial() + pro.gettamanho()-1] = Integer
														.toString(valorpid);
												processosNaMemoria.add(pro);
												inicio = 0;
												contaTamanho = 0;
												break;
											} else {
												if (i == memoria.length - 1) {
													filaProcessos.add(pro);//adiciona o processo a fila de espera se nao tiver espa�o diponivel suficiente na memoria
													}
											}
											inicio = 0;
											contaTamanho = 0;
											
											
										}
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

				new listaRelatorio(nProcesso,tempo,contCicloExe,processosNaMemoria,filaProcessos,encerrados);//chama o metodo para listar o relatorio/ status a cada ciclo

				try{//pausa o ciclo para poder ver o status a cada ciclo
				      Thread.sleep(tempo*1000);
				}catch(Exception e){
				      System.out.println("Deu erro!");
				}	

			}
			
		
			
		}

}
