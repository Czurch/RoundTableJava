package mechanics.Items;

public abstract class Item {
	protected int value,
	weight;

	public abstract int active();

	public abstract void passive();
}
