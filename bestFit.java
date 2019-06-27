import java.util.ArrayList;
import java.util.Random;

public class bestFit {
	
	int nProcesso = 0, algoritmo = 0, tempo = 0, valorpid = 0, valor = 0, probCriar = 0, inicio = 0;
	int  contProcesso = 0, contCicloExe = 0;
	int  contaTamanho = 0,testaEspaco=0,menorEspaco=0;

	processo pro = null;
	espacoEmMemoria local = null;
	espacoEmMemoria espaçoLivre = null,espacoMaior=null,espacoMenor=null;


	ArrayList<processo> processosNaMemoria = new ArrayList<processo>();
	String[] memoria = new String[5000];
	ArrayList<processo> filaProcessos = new ArrayList<processo>();
	ArrayList<processo> encerrados = new ArrayList<processo>();
	ArrayList<espacoEmMemoria> espacoLivreNaMemoria = new ArrayList<espacoEmMemoria>();
	
	//inicializa a memoria com "."
	public  bestFit(int nProcesso,int tempo) {
		for (int i = 0; i < 5000; i++) {
			memoria[i] = ".";

		}
		
		while (encerrados.size() != nProcesso) {// processador executa ate todos os processos encerrar
			contCicloExe++;
			
			for (int f = 0; f < filaProcessos.size(); f++) {//
				filaProcessos.get(f).settempoEsperaFila(filaProcessos.get(f).gettempoEsperaFila()+1);
			}
			


			if (processosNaMemoria.size() != 0) {//testa se possui processos na fila
				for (int contProNaMemoria = 0; contProNaMemoria < processosNaMemoria.size(); contProNaMemoria++) {

					if (processosNaMemoria.get(contProNaMemoria).getdecrementaTempoExe() == 0) {//se o processo executou seu tempo necessario remove o processo e libera espaço na memoria

						for (int contPosiMemoria = 0; contPosiMemoria < memoria.length; contPosiMemoria++) {// testa e apaga processo na memoria
							if (Integer.toString(processosNaMemoria.get(contProNaMemoria).getpid()).equals(memoria[contPosiMemoria])) {
								for (int e = contPosiMemoria; e < processosNaMemoria.get(contProNaMemoria).gettamanho() + contPosiMemoria  ; e++) {
									memoria[e] = ".";
								}
								
									encerrados.add(processosNaMemoria.get(contProNaMemoria));//adiciona o processo a uma fila de encerrados
									processosNaMemoria.remove(contProNaMemoria);// remove o processo do array da memoria


								
								
							


								


								if(filaProcessos.size()!=0) {//testa se possui algum processo na fila de espera para adicionar no espaço criado
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
												espacoLivreNaMemoria.add(espaçoLivre);//adiciona o espaço criado a uma fila de espaços livres
												inicio = 0;
												contaTamanho = 0;

											}

										} else {
											if (inicio != 0) {
												espaçoLivre.setposiFinal(g - 1);
												espacoLivreNaMemoria.add(espaçoLivre);//adiciona o espaço criado a uma fila de espaços livres
												inicio = 0;
												contaTamanho = 0;

											}

										}
									
									}
									for(int y=0;y<filaProcessos.size();y++) {//percorre a fila de processos que estao aguradando area de memoria
										for (int i = 0; i < espacoLivreNaMemoria.size(); i++) {
											if(espacoLivreNaMemoria.get(i).gettamanho()>=filaProcessos.get(y).gettamanho()) {// testa o tamanho do espaço livre com o tamanho do processo 
												
												if(testaEspaco==0) {//pega menor posiçao possivel, primeiro adiciona o primeiro espaço e depois compara com os demais possiveis
													testaEspaco++;
													menorEspaco=espacoLivreNaMemoria.get(i).gettamanho()-filaProcessos.get(y).gettamanho();
													espacoMenor=espacoLivreNaMemoria.get(i);

												}else {
													if(espacoLivreNaMemoria.get(i).gettamanho()-filaProcessos.get(y).gettamanho()<menorEspaco) {
														menorEspaco=espacoLivreNaMemoria.get(i).gettamanho()-filaProcessos.get(y).gettamanho();
														espacoMenor=espacoLivreNaMemoria.get(i);//adiciona o menor espaço a variavel 
														
													}
												}
											}
											
											if(espacoMenor!=null) {// adiciona o processo no menor espaço de alocamento possivel 
												for (int x = espacoMenor.getposiInicial(); x <espacoMenor.getposiInicial() + filaProcessos.get(y).gettamanho(); x++) {
													memoria[x] = "¨";
												}
												memoria[espacoMenor.getposiInicial()] = Integer.toString(filaProcessos.get(y).getpid());
												memoria[espacoMenor.getposiInicial() + filaProcessos.get(y).gettamanho()-1] = Integer
														.toString(filaProcessos.get(y).getpid());
												espacoLivreNaMemoria.removeAll(espacoLivreNaMemoria);//limpa o array de espaço livre
												processosNaMemoria.add(filaProcessos.get(y));//adiciona o processo na memoria
												filaProcessos.remove(y);//remove processo da fila de espera
												espacoMenor=null;

												
												
											
											testaEspaco=0;
												
											}
											
											
										}
										break;
										
									}

									
								}
								
								
								break;

							}
						}
					
									
					} else {//decrementa o tempo de execução dos processos na memoria
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
					pro.setdecrementaTempoExe(20);					

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
								espacoLivreNaMemoria.add(espaçoLivre);//adiciona o espaço criado a uma fila de espaços livres
								
								inicio = 0;
								contaTamanho = 0;

							}

						} else {
							if (inicio != 0) {
								espaçoLivre.setposiFinal(g - 1);
								espacoLivreNaMemoria.add(espaçoLivre);//adiciona o espaço criado a uma fila de espaços livres
								inicio = 0;
								contaTamanho = 0;

							}

						}
					
					}
					
					menorEspaco=0;
					testaEspaco=0;
					for (int i = 0; i < espacoLivreNaMemoria.size(); i++) {// testa e coloca processo na memoria
						if(espacoLivreNaMemoria.get(i).gettamanho()>=pro.gettamanho()) {//testa tamanho do espaço livre e compara com tamanho do processo criado
							
							if(testaEspaco==0) {//pega menor posiçao possivel, primeiro adiciona o primeiro espaço e depois compara com os demais possiveis
								testaEspaco++;
								menorEspaco=espacoLivreNaMemoria.get(i).gettamanho()-pro.gettamanho();
								espacoMenor=espacoLivreNaMemoria.get(i);

							}else {
								if(espacoLivreNaMemoria.get(i).gettamanho()-pro.gettamanho()<menorEspaco) {
									menorEspaco=espacoLivreNaMemoria.get(i).gettamanho()-pro.gettamanho();
									espacoMenor=espacoLivreNaMemoria.get(i);//adiciona o menor espaço a variavel 
									
								}
							}
						}
					}
					
					
					
						if (espacoMenor!=null) {// adiciona o processo criado no menor espaço de alocamento possivel 
							for (int x = espacoMenor.getposiInicial(); x < espacoMenor
									.getposiInicial() + pro.gettamanho(); x++) {
								memoria[x] = "¨";
							}
							memoria[espacoMenor.getposiInicial()] = Integer.toString(valorpid);
							memoria[espacoMenor.getposiInicial() + pro.gettamanho()-1] = Integer
									.toString(valorpid);
							processosNaMemoria.add(pro);
							espacoLivreNaMemoria.removeAll(espacoLivreNaMemoria);
							espacoMenor=null;

						} else {
							filaProcessos.add(pro);//se o espaço nao for suficiente adiciona a lista de processos para aguardar liberar a memoria
							espacoLivreNaMemoria.removeAll(espacoLivreNaMemoria);
							espacoMenor=null;


						}
					
						
					
			
					

					

				}
			}

			
			System.out.println('\n');

			System.out.println("BEST-FIT");
			// mostra a area de memoria
			for (int i = 0; i < 100; i++) {
				int a = i * 50;
				int b = (i + 1) * 50;
				for (; a < b; a++) {

					System.out.print(memoria[a] + "|");

				}
				System.out.print('\n');

			}

			new listaRelatorio(nProcesso,tempo,contCicloExe,processosNaMemoria,filaProcessos,encerrados);//// Lista o relatorio/status a cada ciclo

			try{//pausa o ciclo para poder ver o status a cada ciclo
			      Thread.sleep(tempo*1000);
			}catch(Exception e){
			      System.out.println("Deu erro!");
			}	
			
}

		
		
	
		
	}

}
