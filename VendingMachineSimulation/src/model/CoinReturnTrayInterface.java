package model;

import java.util.ArrayList;

public interface CoinReturnTrayInterface {

	public abstract void emptyReturnTray();

	public abstract void addInsertedCoinToReturnTray(InsertedCoinPiece returnedCoin);

	public abstract ArrayList<InsertedCoinPiece> getSlugsInTray();

	public abstract ArrayList<InsertedCoinPiece> getQuartersInTray();

	public abstract ArrayList<InsertedCoinPiece> getDimesInTray();

	public abstract ArrayList<InsertedCoinPiece> getNickelsInTray();

	public abstract ArrayList<InsertedCoinPiece> getPenniesInTray();

	public abstract ArrayList<InsertedCoinPiece> getAllReturnTrayCoins();
}
