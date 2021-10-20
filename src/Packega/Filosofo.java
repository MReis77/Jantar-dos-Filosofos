package Packega;


public class Filosofo extends Thread {
	
	final static int MAXIMO = 100;
	Mesa elmesa;
	int filosofo;
	
	public Filosofo (String name, Mesa mesajantar, int fil)
	{
		super(name);
		elmesa = mesajantar;
		filosofo = fil;
	}
	
	public void run ()
	{
		int tempo = 0;
		while (true);
		{
			tempo =  (int) (Math.random() * MAXIMO);
			pensar (tempo);
			getHashi();
			tempo =  (int) (Math.random() * MAXIMO);
			papar (tempo);
			returnHashi();
		}
	}
	
	public void pensar (int tempo)
	{
		try
		{
			sleep(tempo);
		}
		catch (InterruptedException e)
		{
			System.out.println("El Filosofo tá fritando o cerebro de tanto pensar");
		}
	}
	public void papar (int tempo)
	{
		try
		{
			sleep(tempo);
		}
		catch (InterruptedException e)
		{
			System.out.println("El Filosofo tá de buxin cheio");
		}
		
	}
}
