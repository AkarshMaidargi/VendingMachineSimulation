package modelTest;

import java.util.ArrayList;

import model.CoinReturnTrayInterface;
import model.InsertedCoinPiece;

public class MockCoinReturnTray implements CoinReturnTrayInterface {

	private boolean emptyReturnTrayWasCalled = false;
	private boolean addInsertedCoinToReturnTrayWasCalled = false;
	private int numberOfTimesAddInsertedCoinToReturnTrayWasCalled;
	private boolean getSlugsInTrayWasCalled;
	private boolean getQuartersInTrayWasCalled;
	private boolean getDimesInTrayWasCalled;
	private boolean getNickelsInTrayWasCalled;
	private boolean getPenniesInTrayWasCalled;
	private boolean getAllReturnTrayCoinsCalled;
	private final ArrayList<InsertedCoinPiece> quartersInTray;
	private final ArrayList<InsertedCoinPiece> dimesInTray;
	private final ArrayList<InsertedCoinPiece> nickelsInTray;
	private final ArrayList<InsertedCoinPiece> penniesInTray;
	private final ArrayList<InsertedCoinPiece> slugsInTray;
	private final ArrayList<InsertedCoinPiece> allReturnTrayCoins;

	public MockCoinReturnTray() {
		this.numberOfTimesAddInsertedCoinToReturnTrayWasCalled = 0;
		this.getSlugsInTrayWasCalled = false;
		this.getQuartersInTrayWasCalled = false;
		this.getDimesInTrayWasCalled = false;
		this.getNickelsInTrayWasCalled = false;
		this.getPenniesInTrayWasCalled = false;
		this.getAllReturnTrayCoinsCalled = false;
		this.quartersInTray = new ArrayList<InsertedCoinPiece>();
		this.dimesInTray = new ArrayList<InsertedCoinPiece>();
		this.nickelsInTray = new ArrayList<InsertedCoinPiece>();
		this.penniesInTray = new ArrayList<InsertedCoinPiece>();
		this.slugsInTray = new ArrayList<InsertedCoinPiece>();
		this.allReturnTrayCoins = new ArrayList<InsertedCoinPiece>();
	}

	@Override
	public void emptyReturnTray() {
		this.emptyReturnTrayWasCalled = true;
	}

	@Override
	public void addInsertedCoinToReturnTray(InsertedCoinPiece returnedCoin) {
		this.addInsertedCoinToReturnTrayWasCalled = true;
		this.numberOfTimesAddInsertedCoinToReturnTrayWasCalled++;
	}

	public boolean getEmptyReturnTrayWasCalled() {
		return this.emptyReturnTrayWasCalled;
	}

	public boolean getAddInsertedCoinToReturnTrayWasCalled() {
		return this.addInsertedCoinToReturnTrayWasCalled;
	}

	public int getNumberOfTimesAddInsertedCoinToReturnTrayWasCalled() {
		return this.numberOfTimesAddInsertedCoinToReturnTrayWasCalled;
	}

	@Override
	public ArrayList<InsertedCoinPiece> getSlugsInTray() {
		this.getSlugsInTrayWasCalled = true;
		return this.slugsInTray;
	}

	@Override
	public ArrayList<InsertedCoinPiece> getQuartersInTray() {
		this.getQuartersInTrayWasCalled = true;
		return this.quartersInTray;
	}

	@Override
	public ArrayList<InsertedCoinPiece> getDimesInTray() {
		this.getDimesInTrayWasCalled = true;
		return this.dimesInTray;
	}

	@Override
	public ArrayList<InsertedCoinPiece> getNickelsInTray() {
		this.getNickelsInTrayWasCalled = true;
		return this.nickelsInTray;
	}

	@Override
	public ArrayList<InsertedCoinPiece> getPenniesInTray() {
		this.getPenniesInTrayWasCalled = true;
		return this.penniesInTray;
	}

	@Override
	public ArrayList<InsertedCoinPiece> getAllReturnTrayCoins() {
		this.getAllReturnTrayCoinsCalled = true;
		return this.allReturnTrayCoins;
	}
	public void addToSlugsInTray(InsertedCoinPiece slugToAdd) {
		this.slugsInTray.add(slugToAdd);
	}

	public void addToQuartersInTray(InsertedCoinPiece quarterToAdd) {
		this.quartersInTray.add(quarterToAdd);
	}

	public void addToDimesInTray(InsertedCoinPiece dimeToAdd) {
		this.dimesInTray.add(dimeToAdd);
	}

	public void addToPenniesInTray(InsertedCoinPiece pennyToAdd) {
		this.penniesInTray.add(pennyToAdd);
	}

	public boolean getGetSlugsInTrayWasCalled() {
		return this.getSlugsInTrayWasCalled;
	}

	public boolean getGetQuartersInTrayWasCalled() {
		return this.getQuartersInTrayWasCalled;
	}

	public boolean getGetDimesInTrayWasCalled() {
		return this.getDimesInTrayWasCalled;
	}

	public boolean getGetNickelsInTrayWasCalled() {
		return this.getNickelsInTrayWasCalled;
	}

	public boolean getGetPenniesInTrayWasCalled() {
		return this.getPenniesInTrayWasCalled;
	}
}
