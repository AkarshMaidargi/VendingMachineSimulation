package startup;

import java.util.ArrayList;

import model.ChangeValue;
import model.CoinReturnTray;
import model.CoinReturnTrayInterface;
import model.DispensedItemChute;
import model.DispensibleItem;
import model.UserAccountTeller;
import model.UserBalance;
import model.VendingSlot;
import model.VendingSlotInterface;
import view.VendingWindow;
import control.ChangeManager;
import control.ChangeManagerInterface;
import control.CoinInsertionButtonListenerCollection;
import control.CoinInsertionManager;
import control.CoinValidator;
import control.DimeButtonListener;
import control.EmptyChangeSlotButtonListener;
import control.NickelButtonListener;
import control.PennyButtonListener;
import control.QuarterButtonListener;
import control.ReturnButtonListener;
import control.SlugButtonListener;
import control.VendingController;

public class ApplicationLauncher {
// Adarsh added Comment
	public static void main(String[] args) {
		VendingWindow vendingWindow = new VendingWindow();
		vendingWindow.setVisible(true);

		VendingSlotInterface vendingSlot1 = new VendingSlot(DispensibleItem.COLA, 3);
		VendingSlotInterface vendingSlot2 = new VendingSlot(DispensibleItem.CHIPS, 2);
		VendingSlotInterface vendingSlot3 = new VendingSlot(DispensibleItem.CANDY, 4);

		ArrayList<VendingSlotInterface> vendingSlots = new ArrayList<VendingSlotInterface>();
		vendingSlots.add(vendingSlot1);
		vendingSlots.add(vendingSlot2);
		vendingSlots.add(vendingSlot3);

		CoinReturnTrayInterface coinReturnTray = new CoinReturnTray();
		ChangeValue changeValue = new ChangeValue();
		UserBalance userBalance = new UserBalance();
		ChangeManagerInterface changeManager = new ChangeManager(userBalance, coinReturnTray,
				vendingWindow, changeValue);
		DispensedItemChute dispensedItemChute = new DispensedItemChute();
		UserAccountTeller userAccountTeller = new UserAccountTeller(userBalance, changeManager);
		VendingController vendingController = new VendingController(vendingSlots,
				dispensedItemChute, userAccountTeller, vendingWindow);

		CoinValidator coinValidator = new CoinValidator();
		CoinInsertionManager coinInsertionManager = new CoinInsertionManager(coinValidator,
				userBalance, coinReturnTray, vendingWindow);

		PennyButtonListener pennyButtonListener = new PennyButtonListener(coinInsertionManager);
		NickelButtonListener nickelButtonListener = new NickelButtonListener(coinInsertionManager);
		DimeButtonListener dimeButtonListener = new DimeButtonListener(coinInsertionManager);
		QuarterButtonListener quarterButtonListener = new QuarterButtonListener(
				coinInsertionManager);
		SlugButtonListener slugButtonListener = new SlugButtonListener(coinInsertionManager);

		CoinInsertionButtonListenerCollection coinInsertionButtonListenerCollection = new CoinInsertionButtonListenerCollection(
				pennyButtonListener, nickelButtonListener, dimeButtonListener,
				quarterButtonListener, slugButtonListener);

		ViewControlConnector viewControlConnector = new ViewControlConnector(
				coinInsertionButtonListenerCollection, new EmptyChangeSlotButtonListener(
						changeManager), new ReturnButtonListener(changeManager), vendingWindow,
				vendingController);
		viewControlConnector.connectProductSelectionButtonsToVendingButtonListener();
		viewControlConnector.connectCoinInsertionButtonsToButtonListeners();
		viewControlConnector.connectEmptyChangeSlotButtonToListener();
		viewControlConnector.connectReturnButtonToListener();
	}
}
