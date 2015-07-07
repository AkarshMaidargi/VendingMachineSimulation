package viewTest;

import view.VendingWindowInterface;

public class MockVendingWindow implements VendingWindowInterface {

	private boolean showPriceMessageWasCalled;
	private boolean showInsertCoinsMessageWasCalled;
	private boolean showThankYouMessageWasCalled;
	private boolean showSoldOutMessageWasCalled;
	private boolean showUpdatedUserBalanceWasCalled;
	private boolean showUpdatedChangeDisplayWasCalled;
	private int numberOfTimesShowUpdatedChangeDisplayWasCalled;
	private boolean showReturnedSlugAmountWasCalled;
	private boolean showReturnedQuarterAmountWasCalled;
	private boolean showReturnedDimeAmountWasCalled;
	private boolean showReturnedNickelAmountWasCalled;
	private boolean showReturnedPennyAmountWasCalled;
	private int numberOfTimesShowReturnedQuarterAmountWasCalled;
	private int numberOfTimesShowReturnedDimeAmountWasCalled;
	private int numberOfTimesShowReturnedNickelAmountWasCalled;

	public MockVendingWindow() {
		this.showPriceMessageWasCalled = false;
		this.showInsertCoinsMessageWasCalled = false;
		this.showThankYouMessageWasCalled = false;
		this.showSoldOutMessageWasCalled = false;
		this.showUpdatedUserBalanceWasCalled = false;
		this.showUpdatedChangeDisplayWasCalled = false;
		this.numberOfTimesShowUpdatedChangeDisplayWasCalled = 0;
		this.showReturnedDimeAmountWasCalled = false;
		this.showReturnedNickelAmountWasCalled = false;
		this.showReturnedPennyAmountWasCalled = false;
		this.showReturnedQuarterAmountWasCalled = false;
		this.showReturnedSlugAmountWasCalled = false;
		this.numberOfTimesShowReturnedQuarterAmountWasCalled = 0;
		this.numberOfTimesShowReturnedDimeAmountWasCalled = 0;
		this.numberOfTimesShowReturnedNickelAmountWasCalled = 0;
	}

	@Override
	public void showPriceMessage(String priceToDisplay) {
		this.showPriceMessageWasCalled = true;
	}

	@Override
	public void showInsertCoinsMessage() {
		this.showInsertCoinsMessageWasCalled = true;
	}

	@Override
	public void showThankYouMessage() {
		this.showThankYouMessageWasCalled = true;
	}

	@Override
	public void showSoldOutMessage() {
		this.showSoldOutMessageWasCalled = true;
	}

	@Override
	public void showUpdatedUserBalance(String balanceToDisplay) {
		this.showUpdatedUserBalanceWasCalled = true;
	}

	@Override
	public void showUpdatedChangeDisplay(String changeBalanceToDisplay) {
		this.showUpdatedChangeDisplayWasCalled = true;
		this.numberOfTimesShowUpdatedChangeDisplayWasCalled++;
	}

	public boolean getShowPriceMessageWasCalled() {
		return this.showPriceMessageWasCalled;
	}

	public boolean getShowInsertCoinsMessageWasCalled() {
		return this.showInsertCoinsMessageWasCalled;
	}

	public boolean getShowThankYouMessageWasCalled() {
		return this.showThankYouMessageWasCalled;
	}

	public boolean getShowSoldOutMessageWasCalled() {
		return this.showSoldOutMessageWasCalled;
	}

	public boolean getShowUpdatedUserBalanceWasCalled() {
		return this.showUpdatedUserBalanceWasCalled;
	}

	public boolean getShowUpdatedChangeDisplayWasCalled() {
		return this.showUpdatedChangeDisplayWasCalled;
	}

	public int getNumberOfTimesShowUpdatedChangeDisplayWasCalled() {
		return this.numberOfTimesShowUpdatedChangeDisplayWasCalled;
	}

	@Override
	public void showReturnedSlugAmount(int amountToShow) {
		this.showReturnedSlugAmountWasCalled = true;
	}

	@Override
	public void showReturnedQuarterAmount(int amountToShow) {
		this.showReturnedQuarterAmountWasCalled = true;
		this.numberOfTimesShowReturnedQuarterAmountWasCalled++;
	}

	@Override
	public void showReturnedDimeAmount(int amountToShow) {
		this.showReturnedDimeAmountWasCalled = true;
		this.numberOfTimesShowReturnedDimeAmountWasCalled++;
	}

	@Override
	public void showReturnedNickelAmount(int amountToShow) {
		this.showReturnedNickelAmountWasCalled = true;
		this.numberOfTimesShowReturnedNickelAmountWasCalled++;
	}

	@Override
	public void showReturnedPennyAmount(int amountToShow) {
		this.showReturnedPennyAmountWasCalled = true;
	}

	public boolean getShowReturnedPennyAmountWasCalled() {
		return this.showReturnedPennyAmountWasCalled;
	}

	public boolean getShowReturnedNickelAmountWasCalled() {
		return this.showReturnedNickelAmountWasCalled;
	}

	public boolean getShowReturnedDimeAmountWasCalled() {
		return this.showReturnedDimeAmountWasCalled;
	}

	public boolean getShowReturnedQuarterAmountWasCalled() {
		return this.showReturnedQuarterAmountWasCalled;
	}

	public boolean getShowReturnedSlugAmountWasCalled() {
		return this.showReturnedSlugAmountWasCalled;
	}

	public int getNumberOfTimesShowReturnedQuarterAmountWasCalled() {
		return numberOfTimesShowReturnedQuarterAmountWasCalled;
	}

	public int getNumberOfTimesShowReturnedDimeAmountWasCalled() {
		return numberOfTimesShowReturnedDimeAmountWasCalled;
	}

	public int getNumberOfTimesShowReturnedNickelAmountWasCalled() {
		return numberOfTimesShowReturnedNickelAmountWasCalled;
	}
}
