import java.lang.Math;
import java.util.Random;

class imgpro
{
	// rgb renk değerlerini alıp xyz renk uzayındaki karşılığını dizi olarak verir [x,y,z]
	static double[] rgb2xyz(int color)
	{
		double sR,sG,sB;
		double var_R,var_G,var_B,x,y,z,tempR,tempG,tempB;

		// rgb renklerini ayrı ayrı al
		sR = (color>>16)&0xff;
		sG = (color>>8)&0xff;
		sB = color&0xff;


		var_R = sR/255;
		var_G = sG/255;
		var_B = sB/255;

		// Math.pow() fonksiyonun içinde bölme işlemi sıkıntı çıkarabiliyordu (sanırım) o yüzden böyle yaptım
		tempR = (var_R + 0.055)/1.055;
		tempG = (var_G + 0.055)/1.055;
		tempB = (var_B + 0.055)/1.055;


		if (var_R > 0.04045)
			var_R = Math.pow(tempR,2.4);
		else
			var_R = var_R / 12.92;
		if(var_G > 0.04045)
			var_G = Math.pow(tempG,2.4);
		else
			var_G = var_G / 12.92;
		if(var_B > 0.04045)
			var_B = Math.pow(tempB,2.4);
		else
			var_B = var_B / 12.92;


		var_R = var_R*100;
		var_G = var_G*100;
		var_B = var_B*100;


		x = var_R * 0.4124 + var_G * 0.3576 + var_B * 0.1805;
		y = var_R * 0.2126 + var_G * 0.7152 + var_B * 0.0722;
		z = var_R * 0.0193 + var_G * 0.1192 + var_B * 0.9505;

		// sonucu dizi şeklinde döndür
		double[] result = {x,y,z};
		return result;
	}

	// xyz renk değerlerini alıp CIE-Lab renk uzayındaki karşılığını dizi olarak verir [L,a,b]
	static double[] xyz2lab(double x, double y, double z)
	{
		double refX,refY,refZ,varx,vary,varz,L,a,b;

		// Standart illuminant D65  2®  referans değerleri (ortalama gün ortası ışığı)
		refX = 95.047;
		refY = 100.000;
		refZ = 108.883;


		varx = x/refX;
		vary = y/refY;
		varz = z/refZ;


		if(varx > 0.00885645167)
			varx = Math.pow(varx,0.33333333);
		else
			varx = (7.787037037)*varx + (0.13793103);
		if(vary > 0.00885645167)
			vary = Math.pow(vary,0.33333333);
		else
			vary = (7.787037037)*vary + (0.13793103);
		if(varz > 0.00885645167)
			varz = Math.pow(varz,0.33333333);
		else
			varz = (7.787037037)*varz + (0.13793103);


		L = (116 * vary) - 16;
		a = 500 * (varx - vary);
		b = 200 * (vary -varz);


		double[] result = {L,a,b};
		return result;
	}

	// rgb renk değerlerini alıp CIE-Lab renk uzayındaki karşılığını dizi olarak verir [L,a,b]
	static double[] rgb2lab(int color)
	{
		double[] xyz = rgb2xyz(color);
		return xyz2lab(xyz[0],xyz[1],xyz[2]);
	}

	// Renk değerlerini tek tek alıp arasındaki mesafeyi(farkı) verir
	static double clrDif(double L1, double a1, double b1, double L2, double a2, double  b2)
	{

		return Math.sqrt(Math.pow((L1-L2),2) + Math.pow((a1-a2),2) + Math.pow((b1-b2),2));
	}

	//iki renk arasındaki farkı verir
	// özellikle rgb2lab fonksiyonundan dizi şeklinde gelen renklerle çalışması için yazdım
	static double clrDif(double[] clr1, double[] clr2)
	{

		return Math.sqrt(Math.pow((clr1[0]-clr2[0]),2) + Math.pow((clr1[1]-clr2[1]),2) + Math.pow((clr1[2]-clr2[2]),2));
	}

	public static int rndm()
	{
		Random rand = new Random();
		int x = rand.nextInt();

		return x%128;
	}


	public static void main(String... args)
	{
		int color1 = 0;
		color1 = (60 << 16) | (60 << 8) | 20;

		int color2 = 0;
		color2 = (255 << 16) | (255 << 8) | 253;

		
		

		double dif = clrDif(rgb2lab(color1),rgb2lab(color2));
		System.out.println();
		System.out.println();
		
		KMeans clusterr = new KMeans();


		


	}
}

//173, 0, 53
//173, 0, 74
//117, 49, 49
//102, 30, 30
//30, 94, 102
//102, 30, 41