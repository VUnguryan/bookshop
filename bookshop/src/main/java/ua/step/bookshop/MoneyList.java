package ua.step.bookshop;

import java.util.ArrayList;
import java.util.List;
import ua.step.bookshop.models.Money;

public class MoneyList {
	private static List<Money> money = new ArrayList<Money>();
	private static double usd = 26.10000;

	static {
		money.add(new Money("UAN", "UAN", "1", "1"));
		money.add(new Money("USD", "UAN", "26.10000", "26.50000"));
		money.add(new Money("EUR", "UAN", "29.00000", "29.65000"));
		money.add(new Money("RUR", "UAN", "0.37000", "0.41000"));
		money.add(new Money("BTC", "USD", "7636.0746", "8439.8720"));
	}

	public static String calcPrice(String ccy, Integer price) {
		String stringPrice = "";

		switch (ccy) {
		case "BTC":
			double buy = 7636.0746;
			double buy1 = price / 100 / buy;
			buy1 = buy1 / usd;
			stringPrice = String.format("%.5f ", buy1) + " " + ccy;
			return stringPrice;
		case "UAH":
			buy = 1.0;
			buy1 = price / 100 / buy;
			stringPrice = String.format("%.2f ", buy1) + " " + ccy;
			return stringPrice;
		case "USD":
			buy = 26.10000;
			buy1 = price / 100 / buy;
			stringPrice = String.format("%.2f ", buy1) + " " + ccy;
			return stringPrice;
		case "EUR":
			buy = 29.00000;
			buy1 = price / 100 / buy;
			stringPrice = String.format("%.2f ", buy1) + " " + ccy;
			return stringPrice;
		case "RUR":
			buy = 0.37000;
			buy1 = price / 100 / buy;
			stringPrice = String.format("%.2f ", buy1) + " " + ccy;
			return stringPrice;
		default:
			return stringPrice + "нет цены";
		}
	}
}