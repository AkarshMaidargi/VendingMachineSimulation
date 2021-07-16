package control;

import java.text.DecimalFormat;
import java.util.ArrayList;

import model.DispensedItemChuteInterface;
import model.DispensibleItem;
import model.NullVendingSlot;
import model.UserAccountTeller;
import model.UserBalanceInterface;
import model.VendingSlotInterface;
import view.VendingWindowInterface;

public class VendingController implements VendingControllerInterface {

	private final DispensedItemChuteInterface dispensedItemChute;
	private final ArrayList<VendingSlotInterface> vendingSlotList;
	private final UserAccountTeller userAccountTeller;
	private final VendingWindowInterface vendingWindow;
	private final ShowInsertCoinTimer timerForShowingInsertCoins;

	public VendingController(ArrayList<VendingSlotInterface> vendingSlotList,
			DispensedItemChuteInterface dispensedItemChute, UserAccountTeller userAccountTeller,
			VendingWindowInterface vendingWindow) {
		this.dispensedItemChute = dispensedItemChute;
		this.vendingSlotList = vendingSlotList;
		this.userAccountTeller = userAccountTeller;
		this.vendingWindow = vendingWindow;
		int delayInMilliseconds = 1500;
		this.timerForShowingInsertCoins = new ShowInsertCoinTimer(delayInMilliseconds,
				new InsertCoinDisplayUpdateActionListener(this.vendingWindow));
	}

	@Override
	public void vendProduct(int vendingButtonIndex) {
		VendingSlotInterface slotToVendFrom = getSlotToVendFrom(vendingButtonIndex);
		if (slotToVendFrom.getProductQuantity() > 0) {
			DispensibleItem itemFromSlot = slotToVendFrom.getDispensibleItem();
			UserBalanceInterface userBalance = this.userAccountTeller.getUserBalance();
			double userBalanceValue = userBalance.getBalanceValue();

			if (userBalanceValue >= itemFromSlot.priceOfItem()) {
				this.dispensedItemChute.addItemToChute(itemFromSlot);
				slotToVendFrom.subtractProductQuantityByOne();
				userBalance.subtractFunds(Double.toString(itemFromSlot.priceOfItem()));
				this.userAccountTeller.getChangeManager().makeChange();
				this.vendingWindow.showThankYouMessage();
				this.timerForShowingInsertCoins.startTimer();
				this.vendingWindow.showInsertCoinsMessage();
				this.dispensedItemChute.getDispensedItems();

			} else {
				String priceToDisplay = formatItemPriceAsString(itemFromSlot);
				this.vendingWindow.showPriceMessage(priceToDisplay);
				this.timerForShowingInsertCoins.startTimer();
			}
		} else {
			this.vendingWindow.showSoldOutMessage();
			this.timerForShowingInsertCoins.startTimer();
		}
	}

	private String formatItemPriceAsString(DispensibleItem itemFromSlot) {
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		return decimalFormat.format(itemFromSlot.priceOfItem()).toString();
	}

	private VendingSlotInterface getSlotToVendFrom(int vendingButtonIndex) {
		for (VendingSlotInterface vendingSlot : vendingSlotList) {
			if (vendingButtonIndex == vendingSlotList.indexOf(vendingSlot)) {
				return vendingSlot;
			}
		}
		return new NullVendingSlot(DispensibleItem.NULL, 0);
	}

	public ArrayList<VendingSlotInterface> getVendingSlotCollection() {
		return this.vendingSlotList;
	}

	public DispensedItemChuteInterface getDispensedItemChute() {
		return this.dispensedItemChute;
	}

	public UserAccountTeller getUserAccountTeller() {
		return this.userAccountTeller;
	}

	public VendingWindowInterface getVendingWindow() {
		return this.vendingWindow;
	}

	public ShowInsertCoinTimer getTimer() {
		return this.timerForShowingInsertCoins;
	}
}
