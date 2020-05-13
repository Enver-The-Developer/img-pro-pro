import java.awt.image.BufferedImage;
import java.util.Random;

// Bu class sadece CIE-Lab renk uzayıyla çalışması için tasarlandı
class KMeans
{
	// k değeri kaç tane cluster olacağını belirliyor. Yeni obje oluşturulduğunda k değeri başlangıçta 1 oluyor
	int k;
	ClusterNode head;
	BufferedImage image;


	public KMeans()
	{
		k = 1;
		createCluster();
		head.write();
	}

	// resim alıp cluster işlemlerini yapan constructer
	public KMeans(BufferedImage img)
	{
		k = 1;
		image = img;
		createCluster();
	}

	// cluster işlemlerini yapan fonksiyon
	void createCluster()
	{
		head = new ClusterNode();
		ClusterNode temp = head;

		// k-1'e kadar çünkü zaten head'i oluşturarak bir cluster elde ettik
		for (int i=0; i<k-1; i++)
		{
			temp.next = new ClusterNode();
			temp = temp.next;
		}
		// Bu noktada k tane rastgele değere sahip cluster oluşturmuş olduk
	}
}