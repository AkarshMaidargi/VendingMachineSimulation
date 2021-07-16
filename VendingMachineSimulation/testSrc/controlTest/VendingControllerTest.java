package controlTest;

import java.util.ArrayList;
import java.util.List;

import control.ChangeManager;
import junit.framework.TestCase;
import model.*;
import modelTest.MockDispensedItemChute;
import modelTest.MockUserBalance;
import modelTest.MockVendingSlot;
import view.VendingWindow;
import view.VendingWindowInterface;
import viewTest.MockVendingWindow;
import control.ShowInsertCoinTimer;
import control.VendingController;
import control.VendingControllerInterface;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class VendingControllerTest extends TestCase {
	MockDispensedItemChute dispensedItemChute;
	ArrayList<VendingSlotInterface> vendingSlotCollection ;
	UserAccountTeller userAccountTeller ;
	MockVendingWindow vendingWindow;
	MockVendingSlot slot;
	VendingController vendingController;
	ChangeManager changeManager;
	CoinReturnTray coinReturnTray;
	ChangeValueInterface changeValueInterface;
	UserBalance userBalance;


	@Override
	protected void setUp() throws Exception {
		changeValueInterface = new ChangeValue();
		dispensedItemChute = new MockDispensedItemChute();
		vendingSlotCollection = new ArrayList<VendingSlotInterface>();
		userBalance = new UserBalance();
		vendingWindow = new MockVendingWindow();
		slot = new MockVendingSlot();
		changeManager = new ChangeManager(userBalance,coinReturnTray,vendingWindow,changeValueInterface);
		userAccountTeller = new UserAccountTeller(userBalance,changeManager);
	}
	//Vend Button to dispense Cola
	public void testVendProductwithNoBalanceAndItemPriceToBeDisplayed() {
		slot.setDispensibleItemToReturn(DispensibleItem.COLA);
		slot.setProductQuantity(3);
		vendingSlotCollection.add(slot);
		vendingController = new VendingController(vendingSlotCollection,
				dispensedItemChute, userAccountTeller, vendingWindow);
		vendingController.vendProduct(0);
		assertTrue(vendingWindow.getShowPriceMessageWasCalled());
		assertEquals(0.0, userAccountTeller.getUserBalance().getBalanceValue()
		);
	}
	public void testVendProductwithInsufficentMoneyforProductItemPriceToBeDisplayed() throws InterruptedException {
		userBalance.addFunds(InsertedCoinPiece.QUARTER);
		slot.setDispensibleItemToReturn(DispensibleItem.COLA);
		slot.setProductQuantity(3);
		vendingSlotCollection.add(slot);
		vendingController = new VendingController(vendingSlotCollection,
				dispensedItemChute, userAccountTeller, vendingWindow);
		vendingController.vendProduct(0);
		assertTrue(vendingWindow.getShowPriceMessageWasCalled());
		Thread.sleep(5000);
		assertTrue(vendingWindow.getShowInsertCoinsMessageWasCalled());
		assertEquals(0.25,userBalance.getBalanceValue());
		assertTrue(slot.getGetProductQuantityWasCalled());

	}

	public void testVendProduct_VendsFromSlotAWithExactMoneyforProduct() throws InterruptedException {
		userBalance.addFunds(InsertedCoinPiece.QUARTER);
		userBalance.addFunds(InsertedCoinPiece.QUARTER);
		userBalance.addFunds(InsertedCoinPiece.QUARTER);
		userBalance.addFunds(InsertedCoinPiece.QUARTER);
		slot.setDispensibleItemToReturn(DispensibleItem.COLA);
		slot.setProductQuantity(1);
		vendingSlotCollection.add(slot);
		vendingController = new VendingController(vendingSlotCollection,
				dispensedItemChute, userAccountTeller, vendingWindow);
		vendingController.vendProduct(0);

		// 1) Display message should be thank you
		// 2) User Balance should be 0
		// 3) change balance should be 0
		// 4) After timeout Display message should change to Insert Coin
		// 5) Check if Cola is dispensed.

		// 1) User Balance should be 0
		// 2) change balance should be 0
		// 3) Display message should be thank you
		// 4) Check if Cola is dispensed.
		// 5) After timeout Display message should change to Insert Coin

		List<String> expectedMessages = new ArrayList<String>();
		expectedMessages.add("0.0");
		expectedMessages.add("Thank You");
		expectedMessages.add("Insert Coin");

		assertEquals(expectedMessages,vendingWindow.getDisplayMessages());

		assertEquals(0.0,userBalance.getBalanceValue());
		assertEquals("0.00",changeManager.getChangeValue().getTotalAsFormattedString());
		assertTrue(vendingWindow.getShowThankYouMessageWasCalled());
		assertTrue(dispensedItemChute.getGetDispensedItemsWasCalled());
		assertTrue(vendingWindow.getShowInsertCoinsMessageWasCalled());
	}
}
