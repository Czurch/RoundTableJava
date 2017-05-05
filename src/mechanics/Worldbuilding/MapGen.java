package mechanics.Worldbuilding;

import java.util.Random;

import mechanics.coreMath;

public class MapGen {
	private static final Random random = new Random();
	public double[] values;
	private int width, height;
	
	public MapGen(int w, int h, int featureSize) {
		this.width = w;
		this.height = h;
		int samplesize = featureSize;
		double scale = 1.0;
		
		values = new double[w * h];

		for (int y = 0; y < w; y += featureSize)
		{
			for (int x = 0; x < w; x += featureSize)
			{
				setSample(x, y, coreMath.randomFreq());
			}
		}
		 
		while (samplesize > 1)
		{
		    DiamondSquare(samplesize, scale);
		 
		    samplesize /= 2;
		    scale /= 2.0;
		}
	}
	
	public double sample(int x, int y)
	{
	    return values[(x & (width - 1)) + (y & (height - 1)) * width];
	}
	 
	public void setSample(int x, int y, double value)
	{
	    values[(x & (width - 1)) + (y & (height - 1)) * width] = value;
	}
	
	public void sampleSquare(int x, int y, int size, double value)
	{
	    int hs = size / 2;
	 
	    // a     b 
	    //
	    //    x
	    //
	    // c     d
	 
	    double a = sample(x - hs, y - hs);
	    double b = sample(x + hs, y - hs);
	    double c = sample(x - hs, y + hs);
	    double d = sample(x + hs, y + hs);
	 
	    setSample(x, y, ((a + b + c + d) / 4.0) + value);
	 
	}
	 
	public void sampleDiamond(int x, int y, int size, double value)
	{
	    int hs = size / 2;
	 
	    //   c
	    //
	    //a  x  b
	    //
	    //   d
	 
	    double a = sample(x - hs, y);
	    double b = sample(x + hs, y);
	    double c = sample(x, y - hs);
	    double d = sample(x, y + hs);
	 
	    setSample(x, y, ((a + b + c + d) / 4.0) + value);
	}
	
	void DiamondSquare(int stepsize, double scale)
	{
	 
	    int halfstep = stepsize / 2;
	 
	    for (int y = halfstep; y < this.height + halfstep; y += stepsize)
	    {
	        for (int x = halfstep; x < this.width + halfstep; x += stepsize)
	        {
	            sampleSquare(x, y, stepsize, coreMath.randomFreq() * scale);
	        }
	    }
	 
	    for (int y = 0; y < this.height; y += stepsize)
	    {
	        for (int x = 0; x < this.width; x += stepsize)
	        {
	            sampleDiamond(x + halfstep, y, stepsize, coreMath.randomFreq() * scale);
	            sampleDiamond(x, y + halfstep, stepsize, coreMath.randomFreq() * scale);
	        }
	    }
	 
	}
}
