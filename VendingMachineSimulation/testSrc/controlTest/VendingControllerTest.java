package controlTest;

import java.util.ArrayList;

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

import static java.lang.Thread.sleep;

public class VendingControllerTest extends TestCase {
	MockDispensedItemChute dispensedItemChute;
	ArrayList<VendingSlotInterface> vendingSlotCollection ;
	UserAccountTeller userAccountTeller ;
	MockVendingWindow vendingWindow;
	MockVendingSlot slot;
	VendingController vendingController;
	MockUserBalance mockUserBalance;
	MockChangeManager mockChangeManager;
	ChangeManager changeManager;
	CoinReturnTray coinReturnTray;
	ChangeValueInterface changeValueInterface;


	@Override
	protected void setUp() throws Exception {
		dispensedItemChute = new MockDispensedItemChute();
		vendingSlotCollection = new ArrayList<VendingSlotInterface>();
		mockUserBalance = new MockUserBalance();
		mockChangeManager =new MockChangeManager();

		userAccountTeller = new UserAccountTeller(mockUserBalance,mockChangeManager);
		vendingWindow = new MockVendingWindow();
		slot = new MockVendingSlot();
		changeManager = new ChangeManager(mockUserBalance,coinReturnTray,vendingWindow,changeValueInterface);
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
		mockUserBalance.setBalanceValue("0.25");
		slot.setDispensibleItemToReturn(DispensibleItem.COLA);
		slot.setProductQuantity(3);
		vendingSlotCollection.add(slot);
		vendingController = new VendingController(vendingSlotCollection,
				dispensedItemChute, userAccountTeller, vendingWindow);
		vendingController.vendProduct(0);
		assertTrue(vendingWindow.getShowPriceMessageWasCalled());
		Thread.sleep(5000);
		assertTrue(vendingWindow.getShowInsertCoinsMessageWasCalled());
		assertTrue(mockUserBalance.getGetBalanceValueWasCalled());
		assertTrue(slot.getGetProductQuantityWasCalled());

	}

	public void testVendProduct_VendsFromSlotAWithExactMoneyforProduct()
	{
		mockUserBalance.setBalanceValue("1.00");
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

		assertTrue(vendingWindow.getShowThankYouMessageWasCalled());
		assertEquals(0.0,mockUserBalance.getBalanceValue());
		//Todo Below asserts fails
		assertEquals("0.0", changeManager.getChangeValue().getTotalAsFormattedString());
	}






}
