package controller;
import java.util.concurrent.Semaphore;
public class PistaController extends Thread {
	private int numaviao;
	private Semaphore norte;
	private Semaphore sul;
	private Semaphore semaforo;
	private String pista;
	
	public PistaController(int numaviao, Semaphore norte, Semaphore sul, Semaphore semaforo, String pista) {
		this.numaviao = numaviao;
		this.norte = norte;
		this.sul = sul;
		this.semaforo = semaforo;
		this.pista = pista;
	}
	
	@Override
	public void run() {
		try{
			semaforo.acquire();
			Decolagem();
			
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		} finally {
			semaforo.release();
		}
		
	}
	
	public void Decolagem() {
		if(pista == "Norte") {
			System.out.println("O avião " + numaviao + " chegou a pista norte! ");
			try {
				norte.acquire();
				sleep((int)(Math.random() * 401) + 300);
				System.out.println("O avião " + numaviao + " manobrou!");
				sleep((int)(Math.random() * 501) + 500);
				System.out.println("O avião " + numaviao + " taxiou!");
				sleep((int)(Math.random() * 601) + 200);
				System.out.println("O avião " + numaviao + " decolou! ");
				sleep((int)(Math.random() * 501) + 300);
				System.out.println("O avião " + numaviao +  " se afastou! ");
				sleep((int)(Math.random() * 1001) + 1000);
				System.out.println("Nova decolagem permitida!"); // simulação do tempo de delay da cabine de controle
			} catch(Exception e) {
				System.err.println(e.getMessage());
			} finally {
				norte.release();
			}
		
		} else {
			System.out.println("O avião " + numaviao + " chegou a pista sul! ");
			try {
				sul.acquire();
				sleep((int)(Math.random() * 401) + 300);
				System.out.println("O avião " + numaviao + " manobrou!");
				sleep((int)(Math.random() * 501) + 500);
				System.out.println("O avião " + numaviao + " taxiou!");
				sleep((int)(Math.random() * 201) + 600);
				System.out.println("O avião " + numaviao + " decolou! ");
				sleep((int)(Math.random() * 501) + 300);
				System.out.println("O avião " + numaviao +  " se afastou! ");
				sleep((int)(Math.random() * 1001) + 1000); // simulação do tempo de delay da cabine de controle
				System.out.println("Nova decolagem permitida!");
			} catch(Exception e) {
				System.err.println(e.getMessage());
			} finally {
				sul.release();
			}
		}
		
	}
}
