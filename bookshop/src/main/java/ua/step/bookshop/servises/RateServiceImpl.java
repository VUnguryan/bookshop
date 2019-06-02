package ua.step.bookshop.servises;

import org.springframework.beans.factory.annotation.Autowired;

import ua.step.bookshop.models.Book;
import ua.step.bookshop.models.Rate;
import ua.step.bookshop.models.User;
import ua.step.bookshop.repositories.BookRepository;
import ua.step.bookshop.repositories.RateRepository;
import ua.step.bookshop.repositories.UserRepository;

public class RateServiceImpl implements RateService{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private RateRepository rateRepository;
	
	
	@Override
	public void setRate(Rate rate, Book book) {// добавляет оценку в список оценок или редактирует ее
		if (rate.getBook().getName() == book.getName()) {
			if (isRateNotExist(rate)) { // если оценка от пользователя еще не существует - новая оценка добавляется в
										// список
				rateRepository.save(rate);
			} else {
				for (Rate rate1 : rateRepository.findAll()) { // если уже существует - оценка изменянт свое значение
					// rate
				}
			}
		}
	}

	@Override
	public boolean isRateNotExist(Rate rate) { // ставил ли пользователь рейтинг этой книге
		for (Rate rate1 : rateRepository.findAll()) {
			if (rate1.getUser().equals(rate)) {
				return false; // пользователь уже поставил рейтинг этой книге
			}
		}
		return true; // пользователь не ставил рейтинг этой книге
	}

	@Override
	public double getRate(Book book) {
		int rateCount = 0;
		int rateGeneral = 0;
		for (Rate rate : rateRepository.findAll()) {
			if (rate.getBook().equals(book)) {
				rateCount++;
				rateGeneral += rate.getRate();
			}
		}

		return (double) rateGeneral / rateCount;
	}
	
	@Override
	public double getRate(Integer id) {
		int rateCount = 0;
		int rateGeneral = 0;
		for (Rate rate : rateRepository.findAll()) {
			if (rate.getId().equals(id)) {
				rateCount++;
				rateGeneral += rate.getRate();
			}
		}

		return (double) rateGeneral / rateCount;
	}

	@Override
	public double getRate(Book book, User user) {
		for (Rate rate : rateRepository.findAll()) {
			if (rate.getBook().equals(book) && rate.getUser().equals(user)) {
				return rate.getRate();
			}
		}

		return -1.;
	}

}
