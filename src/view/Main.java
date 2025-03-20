package view;

import controller.PistaController;
import java.util.concurrent.Semaphore;
public class Main {

	public static void main(String[] args) {
		String[] pista = {"Norte" , "Sul"};
		Semaphore sul = new Semaphore(1);
		Semaphore norte = new Semaphore(1);
		Semaphore semaforo = new Semaphore(2);
		for(int i = 0; i < 12; i++) {
			int index = (int)(Math.random() * 2);
			Thread t = new PistaController(i + 1, sul, norte, semaforo, pista[index]);
			t.start();
		}	
	}

}
