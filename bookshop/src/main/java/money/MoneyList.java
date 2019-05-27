package money;

import java.util.ArrayList;
import java.util.List;

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

		if (ccy.equals("BTC")) {
			double buy = 7636.0746;
			double buy1 = price / 100 / buy;
			buy1 = buy / usd;
			stringPrice = String.format("%.2f ", buy1) + " " + ccy;
			return stringPrice;
		}
		if (ccy.equals("UAN")) {
			double buy = 1.0;
			double buy1 = price / 100 / buy;
			stringPrice = String.format("%.2f ", buy1) + " " + ccy;
			return stringPrice;
		}
		if (ccy.equals("USD")) {
			double buy = 26.10000;
			double buy1 = price / 100 / buy;
			stringPrice = String.format("%.2f ", buy1) + " " + ccy;
			return stringPrice;
		}

		if (ccy.equals("EUR")) {
			double buy = 29.00000;
			double buy1 = price / 100 / buy;
			stringPrice = String.format("%.2f ", buy1) + " " + ccy;
			return stringPrice;
		}

		if (ccy.equals("RUR")) {
			double buy = 0.37000;
			double buy1 = price / 100 / buy;
			stringPrice = String.format("%.2f ", buy1) + " " + ccy;
			return stringPrice;
		}

		/*
		 * for (Money mon : money) {
		 * 
		 * if (ccy == "BTC") { double buy = Double.parseDouble(mon.getBuy()); double
		 * buy1 = price / 100 / buy; buy1 = buy / usd; stringPrice =
		 * String.format("%.2f ", buy1) + " " + mon.getCcy(); return stringPrice; } else
		 * if (mon.getCcy().equals(ccy)) { double buy =
		 * Double.parseDouble(mon.getBuy()); double buy1 = price / 100 / buy;
		 * stringPrice = String.format("%.2f ", buy1) + " " + mon.getCcy(); return
		 * stringPrice; }
		 * 
		 * }
		 */

		return stringPrice + "нет цены";
	}
}
