package control;

import model.ChangeValueInterface;
import view.VendingWindowInterface;
import model.CoinReturnTrayInterface;

public interface ChangeManagerInterface {

	public abstract void makeChange();

	public abstract void clearChangeValueTotal();

	public abstract CoinReturnTrayInterface getCoinReturnTray();

	public abstract VendingWindowInterface getVendingWindow();

	public abstract ChangeValueInterface getChangeValue();

}
