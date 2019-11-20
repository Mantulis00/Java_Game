package world;

public class groundObject
{
	private boolean topLayer;
	private int layer;
	
	groundObject(int layer, boolean topLayer)
	{
		this.layer = layer;
		this.topLayer = topLayer;
	}
	
	
	public boolean isTopLayer() {
		return topLayer;
	}
	public void setTopLayer(boolean topLayer) {
		this.topLayer = topLayer;
	}
	public int getLayer() {
		return layer;
	}
	public void setLayer(int layer) {
		this.layer = layer;
	}
	
	
	
}