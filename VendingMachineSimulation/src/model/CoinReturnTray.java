package model;

import java.util.ArrayList;

public class CoinReturnTray implements CoinReturnTrayInterface {

	private final ArrayList<InsertedCoinPiece> allReturnTrayCoins;
	private final ArrayList<InsertedCoinPiece> penniesInTray;
	private final ArrayList<InsertedCoinPiece> nickelsInTray;
	private final ArrayList<InsertedCoinPiece> dimesInTray;
	private final ArrayList<InsertedCoinPiece> quartersInTray;
	private final ArrayList<InsertedCoinPiece> slugsInTray;

	public CoinReturnTray() {
		this.allReturnTrayCoins = new ArrayList<InsertedCoinPiece>();
		this.penniesInTray = new ArrayList<InsertedCoinPiece>();
		this.nickelsInTray = new ArrayList<InsertedCoinPiece>();
		this.dimesInTray = new ArrayList<InsertedCoinPiece>();
		this.quartersInTray = new ArrayList<InsertedCoinPiece>();
		this.slugsInTray = new ArrayList<InsertedCoinPiece>();
	}

	@Override
	public void addInsertedCoinToReturnTray(InsertedCoinPiece returnedCoin) {
		if (returnedCoin.weightInGrams() == InsertedCoinPiece.PENNY.weightInGrams()) {
			this.penniesInTray.add(returnedCoin);
		} else if (returnedCoin.weightInGrams() == InsertedCoinPiece.NICKEL.weightInGrams()) {
			this.nickelsInTray.add(returnedCoin);
		} else if (returnedCoin.weightInGrams() == InsertedCoinPiece.DIME.weightInGrams()) {
			this.dimesInTray.add(returnedCoin);
		} else if (returnedCoin.weightInGrams() == InsertedCoinPiece.QUARTER.weightInGrams()) {
			this.quartersInTray.add(returnedCoin);
		} else {
			this.slugsInTray.add(returnedCoin);
		}
		this.allReturnTrayCoins.add(returnedCoin);
	}

	@Override
	public void emptyReturnTray() {
		this.allReturnTrayCoins.clear();
		this.slugsInTray.clear();
		this.quartersInTray.clear();
		this.dimesInTray.clear();
		this.nickelsInTray.clear();
		this.penniesInTray.clear();
	}

	@Override
	public ArrayList<InsertedCoinPiece> getPenniesInTray() {
		return penniesInTray;
	}

	@Override
	public ArrayList<InsertedCoinPiece> getNickelsInTray() {
		return nickelsInTray;
	}

	@Override
	public ArrayList<InsertedCoinPiece> getDimesInTray() {
		return dimesInTray;
	}

	@Override
	public ArrayList<InsertedCoinPiece> getQuartersInTray() {
		return quartersInTray;
	}

	@Override
	public ArrayList<InsertedCoinPiece> getSlugsInTray() {
		return slugsInTray;
	}

	@Override
	public ArrayList<InsertedCoinPiece> getAllReturnTrayCoins() {
		return this.allReturnTrayCoins;
	}
}
