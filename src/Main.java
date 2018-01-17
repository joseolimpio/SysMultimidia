import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.IntStream;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.image.Raster;

public class Main {
	
	//inicializando as variaveis globais
	static BufferedImage imagem = null;
    static File arquivo = null;
    static int altura = 0;
	static int largura = 0;
	static int [] totalDeTonsDeCinza = null;
	static int[] tonsUnicos = null;
	static double[] probabilidadeTons = null;
	
	int x = 0; 
    int y = 0; 

	
	public static void LerImagem()
	{

	    //lendo a imagem
	    
	    try{
	      arquivo = new File("C:\\lenna.jpg");
	      imagem = ImageIO.read(arquivo);
	    }catch(IOException e){
	      System.out.println(e);
	    }
	    
	    	    	    
	}

	//Lê os pixels, tira os RGBs e faz os calculos para greyscale
	public static void LerPixels() {
		int contadorElementos = 0;

	    //recolhendo as dimensões da imagem
	    
		largura = imagem.getWidth();
	    altura = imagem.getHeight();
	    	    
	    totalDeTonsDeCinza = new int[(largura*altura)];
	    
	    //System.out.println("largura , altura: " + largura + ", " + altura);
	    
	    
	    for (int i = 0; i < altura; i++) 
	    {
	      
	    	for (int j = 0; j < largura; j++)
	    		{       
	    			//System.out.println("x,y: " + j + ", " + i);
	    			int pixel = imagem.getRGB(j, i);
	    			int r = (pixel >> 16) & 0xFF;
	    			int g = (pixel >> 8) & 0xFF;
	    			int b = (pixel & 0xFF);
	    			int grey = (r + g + b)/3;
	    			totalDeTonsDeCinza[contadorElementos]= grey; 
	    			// falta imprimir o array com os valores dos tons de cinza
	    			//System.out.println("");
	    			//System.out.println(contadorElementos);
	    			contadorElementos++;
	    			
	    		}
	    } 
	   //separar os elementos únicos 
	    tonsUnicos = IntStream.of(totalDeTonsDeCinza).distinct().toArray();
	  	    
	}
	
	public static void CalcularProbabilidade() {
		
		probabilidadeTons = new double[tonsUnicos.length];
		  double frequencia = 0;
		  double qtdPixels = altura*largura;
		  //primeiro calcular as probabilidades
		  //pra isso eu preciso saber a quantidade de vezes que cada tom aparece na imagem, e dividir os valores pelo total que é a quantidade de pixels (altura*largura)
	
		  //conta quantas vezes um elemento aparece, dividir pelo total e colocar a probabilidade em outro vetor	
	      for (int i = 0; i < tonsUnicos.length; i++) {
	    	  
	    	for(int k = 0; k < totalDeTonsDeCinza.length; k++) {
	    			
	    		if (tonsUnicos[i] == totalDeTonsDeCinza[k])
	    			{
			          frequencia++;
	    			}
	    		
	    		
	    	}
	    	probabilidadeTons[i] = (frequencia / qtdPixels) ;
  		//System.out.println("Ton["+tonsUnicos[i]+"]"+" frequencia["+frequencia+"]"+" probabilidade["+ probabilidadeTons[i]+"] Valor total["+ totalDeTonsDeCinza.length +"].");
	        frequencia = 0;
	      }
	}
	
	public static void CalcularEntropia() {
		
		//Calculamos a probabilidade de cada elemento unico
		CalcularProbabilidade();
		// a formula é o somatório de - Probabilidade[numa posição N] * log2 [probabilidade posição N]
	    //os resultados serão então calculados 1 a 1 dentro do for, e adicionados a uma variavel double (com a maior precisão) 
		double entropia = 0;
		for (int i = 0; i < probabilidadeTons.length ; i++) {
			
			entropia += (probabilidadeTons[i] * (Math.log(probabilidadeTons[i])/Math.log(2)))*(-1);
			
		}
		
		System.out.println("A entropia da imagem é de["+entropia+"].");

	}
	
	public static void CalcularRLE() {
		
		//RLE funciona contando quantos elementos consecutivos do mesmo termo por exemplo aaabbccc, vira a3,b2,c3
		//pra isso uso o vetor com todos os tons, pego o primeiro elemento e comparo com o proximo, se for igual, o contador sobe +1, caso contrario, ele escreve o 		
		// elemento que ele está testando, seguido da quantidade de elementos contados até ali
		for(int i = 0; i <totalDeTonsDeCinza.length; i++)
		{
			
			
		}
		
	}
	

	public static void main(String[] args) {
		 
		LerImagem();
		LerPixels();
		CalcularEntropia();
		CalcularRLE();

	}

}
