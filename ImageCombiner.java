import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;

import javax.imageio.ImageIO;

public class ImageCombiner {

	public static BufferedImage combineImages(BufferedImage a, BufferedImage b) {
		if (a.getWidth() != b.getWidth() || a.getHeight() != b.getHeight()) {
			return null;
		}

		BufferedImage result = new BufferedImage(a.getWidth(), a.getHeight(), BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < a.getHeight(); i++) {
			for (int j = 0; j < a.getWidth(); j++) {
				byte[] aPixel = new BigInteger("" + a.getRGB(j,i)).toByteArray();
				byte[] bPixel = new BigInteger("" + b.getRGB(j,i)).toByteArray();
				
				byte[] aFixed = new byte[4];
				byte[] bFixed = new byte[4];
				
				for (int k = 0; k < 4; k++) {
					if (aPixel.length > k) {
						aFixed[k] = aPixel[k];
					} else {
						aFixed[k] = 0;
					}
					
					if (bPixel.length > k) {
						bFixed[k] = bPixel[k];
					} else{
						bFixed[k] = 0;
					}
				}

				byte[] newPixel = new byte[4];
				newPixel[0] = -1;
				for (int k = 1; k < 4; k++) {
					newPixel[k] = (byte)(int)(((int)aFixed[k] + (int)bFixed[k]) / 2);
				}

				result.setRGB(j, i, new BigInteger(newPixel).intValue());
				//result.setRGB(j, i, (a.getRGB(j, i) + b.getRGB(j, i)) / 2);
			}
		}

		return result;
	}

	public static void main(String[] args) {
		try {
			BufferedImage frog = ImageIO.read(new File("frog.jpg"));
			BufferedImage mtn = ImageIO.read(new File("mtn.jpg"));
			
			ImageIO.write(combineImages(frog, mtn), "jpg", new File("combined.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
