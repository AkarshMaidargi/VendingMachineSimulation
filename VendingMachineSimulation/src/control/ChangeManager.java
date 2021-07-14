package control;

import java.math.BigDecimal;

import model.ChangeValueInterface;
import model.CoinReturnTrayInterface;
import model.InsertedCoinPiece;
import model.UserBalanceInterface;
import model.ValidCoin;
import view.VendingWindowInterface;

public class ChangeManager implements ChangeManagerInterface {

	private final UserBalanceInterface userBalance;
	private final CoinReturnTrayInterface coinReturnTray;
	private final VendingWindowInterface vendingWindow;
	private final ChangeValueInterface changeValue;

	public ChangeManager(UserBalanceInterface userBalance, CoinReturnTrayInterface coinReturnTray,
			VendingWindowInterface vendingWindow, ChangeValueInterface changeValue) {
		this.userBalance = userBalance;
		this.coinReturnTray = coinReturnTray;
		this.vendingWindow = vendingWindow;
		this.changeValue = changeValue;
	}

	@Override
	public void makeChange() {
		double twentyFiveCents = new BigDecimal(Double.toString(ValidCoin.QUARTER.valueInCents()))
				.doubleValue();
		double tenCents = new BigDecimal(Double.toString(ValidCoin.DIME.valueInCents()))
				.doubleValue();
		double fiveCents = new BigDecimal(Double.toString(ValidCoin.NICKEL.valueInCents()))
				.doubleValue();

		if (this.userBalance.getBalanceValue() > 0) {
			if (this.userBalance.getBalanceValue() >= twentyFiveCents) {
				this.userBalance.subtractFunds(Double.toString(twentyFiveCents));
				this.changeValue.addToTotal(twentyFiveCents);
				this.coinReturnTray.addInsertedCoinToReturnTray(InsertedCoinPiece.QUARTER);
				this.vendingWindow.showReturnedQuarterAmount(this.coinReturnTray
						.getQuartersInTray().size());
				this.vendingWindow.showUpdatedChangeDisplay(this.changeValue
						.getTotalAsFormattedString());
				this.vendingWindow.showUpdatedUserBalance(Double.toString(this.userBalance
						.getBalanceValue()));

			} else if (this.userBalance.getBalanceValue() >= tenCents) {
				this.userBalance.subtractFunds(Double.toString(tenCents));
				this.changeValue.addToTotal(tenCents);
				this.coinReturnTray.addInsertedCoinToReturnTray(InsertedCoinPiece.DIME);
				this.vendingWindow.showReturnedDimeAmount(this.coinReturnTray.getDimesInTray()
						.size());
				this.vendingWindow.showUpdatedChangeDisplay(this.changeValue
						.getTotalAsFormattedString());
				this.vendingWindow.showUpdatedUserBalance(Double.toString(this.userBalance
						.getBalanceValue()));

			} else if (this.userBalance.getBalanceValue() >= fiveCents) {
				this.userBalance.subtractFunds(Double.toString(fiveCents));
				this.changeValue.addToTotal(fiveCents);
				this.coinReturnTray.addInsertedCoinToReturnTray(InsertedCoinPiece.NICKEL);
				this.vendingWindow.showReturnedNickelAmount(this.coinReturnTray.getNickelsInTray()
						.size());
				this.vendingWindow.showUpdatedChangeDisplay(this.changeValue
						.getTotalAsFormattedString());
				this.vendingWindow.showUpdatedUserBalance(Double.toString(this.userBalance
						.getBalanceValue()));
			}
			makeChange();
		}
		this.vendingWindow.showUpdatedUserBalance(Double.toString(this.userBalance
				.getBalanceValue()));
	}

	@Override
	public void clearChangeValueTotal() {
		this.changeValue.clearTotal();
		this.vendingWindow.showUpdatedChangeDisplay(this.changeValue.getTotalAsFormattedString());
	}

	@Override
	public ChangeValueInterface getChangeValue(){
		return this.changeValue;
	}

	@Override
	public CoinReturnTrayInterface getCoinReturnTray() {
		return this.coinReturnTray;
	}

	@Override
	public VendingWindowInterface getVendingWindow() {
		return this.vendingWindow;
	}

}
