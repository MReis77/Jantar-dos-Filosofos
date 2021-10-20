package Packega;

public class Mesa {
	
	final static int PENSANDO = 1;
	   final static int COMENDO = 2;
	   final static int FOME = 3;
	   final static int NR_FILOSOFOS = 5;
	   final static int PRIMEIRO_FILOSOFO = 0;
	   final static int ULTIMO_FILOSOFO = NR_FILOSOFOS - 1;
	   boolean[] hashi = new boolean[NR_FILOSOFOS];
	   int[] filosofos = new int[NR_FILOSOFOS];
	   int[] tentativas = new int[NR_FILOSOFOS];

	   public Mesa()
	   {
		   for (int i=0; i<5; i++)
		   {
			   hashi[i] = true;
			   filosofos [i] = PENSANDO;
			   tentativas [i] = 0;
		   }
	   }
	   
	   public synchronized void pegarHashi (int filosofo)
	   {
		filosofos[filosofo] = FOME;
		while (filosofos[aLeft(filosofo)] == COMENDO || filosofos[aRight(filosofo)] == COMENDO);
		{
			try
	         {
	            tentativas[filosofo]++;
	            wait();
	         }
	         catch (InterruptedException e)
	         {
	         }
	      }
	      System.out.println("O Filósofo morreu devido a starvation");
	      tentativas[filosofo] = 0;
	      hashi[hashiEsquerdo(filosofo)] = false;
	      hashi[hashiDireito(filosofo)] = false;
	      filosofos[filosofo] = COMENDO;
	      imprimeEstadosFilosofos();
	      imprimeHashis();
	      imprimeTentativas();
	   }

	   private void imprimeHashis() {
		
	}

	public synchronized void returningHashi (int filosofo)
	   {
	      hashi[hashiEsquerdo(filosofo)] = true;
	      hashi[hashiDireito(filosofo)] = true;
	      if (filosofos[aLeft(filosofo)] == FOME || filosofos[aRight(filosofo)] == FOME)
	      {
	         notifyAll();
	      }
	      filosofos[filosofo] = PENSANDO;
	      imprimeEstadosFilosofos();
	      imprimeHashis();
	      imprimeTentativas();
	   }

	   public int aRight (int filosofo)
	   {
	      int direito;
	      if (filosofo == ULTIMO_FILOSOFO)
	      {
	         direito = PRIMEIRO_FILOSOFO;
	      }
	      else
	      {
	         direito = filosofo + 1;
	      }
	      return direito;
	   }

	   public int aLeft (int filosofo)
	   {
	      int esquerdo;
	      if (filosofo == PRIMEIRO_FILOSOFO)
	      {
	         esquerdo = ULTIMO_FILOSOFO;
	      }
	      else
	      {
	         esquerdo = filosofo - 1;
	      }
	      return esquerdo;
	   }

	   public int hashiEsquerdo (int filosofo)
	   {
	      int hashiEsquerdo = filosofo;
	      return hashiEsquerdo;
	   }

	   public int hashiDireito (int filosofo)
	   {
	      int hashiDireito;
	      if (filosofo == ULTIMO_FILOSOFO)
	      {
	         hashiDireito = 0;
	      }
	      else
	      {
	         hashiDireito = filosofo + 1;
	      }
	      return hashiDireito;
	   }

	   public void imprimeEstadosFilosofos ()
	   {
	      String texto = "*";
	      System.out.print("Filósofos = [ ");
	      for (int i = 0; i < NR_FILOSOFOS; i++)
	      {
	         switch (filosofos[i])
	         {
	            case PENSANDO :
	               texto = "PENSANDO";
	               break;
	            case FOME :
	               texto = "FOME";
	               break;
	            case COMENDO :
	               texto = "COMENDO";
	               break;
	         }
	         System.out.print(texto + " ");
	      }
	      System.out.println("]");
	   }

	   public void imprimeGarfos (boolean[] hashis)
	   {
	      String hashi = "*";
	      System.out.print("Hashis = [ ");
	      for (int i = 0; i < NR_FILOSOFOS; i++)
	      {
	         if (hashis[i])
	         {
	        	 hashi = "LIVRE";
	         }
	         else
	         {
	        	 hashi = "OCUPADO";
	         }
	         System.out.print(hashi + " ");
	      }
	      System.out.println("]");
	   }

	   public void imprimeTentativas ()
	   {
	      System.out.print("Tentou comer = [ ");
	      for (int i = 0; i < NR_FILOSOFOS; i++)
	      {
	         System.out.print(filosofos[i] + " ");
	      }
	      System.out.println("]");
	   }
}
