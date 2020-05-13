import java.util.Random;
import java.lang.Math;

// bu class sadece CIE-Lab renk uzayıyla çalışması için tasarlandı
class ClusterNode
{
	double[] position = new double[3];
	ClusterNode next;


	// cluster ilk oluşturulduğunda rastgele değerler atanıyor
	public ClusterNode()
	{
		Random random = new Random();
		position[0] = Math.abs(random.nextInt() % 100); // L değeri 0-100 arasında
		position[1] = random.nextInt() % 128;           // a ve b değerleri -128 - +128 aralığında
		position[2] = random.nextInt() % 128;

		next = null;
	}

	// renk değerlerini (position) tek tek alıp oluşturan constructor
	public ClusterNode(double L, double a, double b)
	{
		position[0] = L;
		position[1] = a;
		position[2] = b;
		next = null;
	}

	// renk değerlerini (position) dizi şeklinde alıp oluşturan constructor
	public ClusterNode(double[] position)
	{
		this.position = position;
		next = null;
	}

	// Cluster renk değişkenlerine rastgele değerler ata.(Belki lazım olur)
	public void setRandomValues()
	{
		Random random = new Random();
		position[0] = Math.abs(random.nextInt() % 100);
		position[1] = random.nextInt() % 128;
		position[2] = random.nextInt() % 128;
	}

	// renk değerlerini (position) yaz
	public void write()
	{
		System.out.println(position[0]);
		System.out.println(position[1]);
		System.out.println(position[2]);
	}
}