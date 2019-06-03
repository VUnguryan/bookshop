package ua.step.bookshop.servises;

import ua.step.bookshop.models.Book;
import ua.step.bookshop.models.Rate;
import ua.step.bookshop.models.User;

public interface RateService {
	public void setRate(Rate rate, Book book);

	public boolean isRateNotExist(Rate rate);

	public double getRate(Book book);

	public double getRate(Integer id);

	public double getRate(Book book, User user);
}
