import java.io.File;
import java.io.IOException;
import java.util.Arrays;
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
	int[] totalDeTonsDeCinza;
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
	    
	    //recolhendo as dimensões da imagem
	    
	    	    	    
	}

	//Lê os pixels, tira os RGBs e faz os calculos para greyscale
	public static void LerPixels() {
		int contadorElementos = 0;
	    largura = imagem.getWidth();
	    altura = imagem.getHeight();      
	    
	    int[] valoresTonsDeCinza = new int[(largura*altura)];
	    
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
	    			valoresTonsDeCinza[contadorElementos]= grey; 
	    			// falta imprimir o array com os valores dos tons de cinza
	    			//System.out.println("");
	    			System.out.println(contadorElementos);
	    			contadorElementos++;
	    			
	    		}
	    }  
	}
	
	public static void CalcularEntropia() {
		
		//separar os elementos únicos 
		//trabalho de indio hightec:

		
	}

	public static void main(String[] args) {
		 
		LerImagem();
		LerPixels();
		CalcularEntropia();
		

	}

}
