package ua.step.bookshop.servises;

import org.springframework.beans.factory.annotation.Autowired;

import ua.step.bookshop.models.Book;
import ua.step.bookshop.models.Rate;
import ua.step.bookshop.models.User;
import ua.step.bookshop.repositories.RateRepository;

public class RateServiceImpl implements RateService {

	@Autowired
	private RateRepository rateRepository;

	@SuppressWarnings("unused")
	@Override
	public void setRate(Rate rate, Book book) {
		if (rate.getBook().getName().equals(book.getName())) {
			if (isRateNotExist(rate)) {
				rateRepository.save(rate);
			} else {
				for (Rate rate1 : rateRepository.findAll()) {
				}
			}
		}
	}

	@Override
	public boolean isRateNotExist(Rate rate) {
		for (Rate rate1 : rateRepository.findAll()) {
			if (rate1.getUser().equals(rate)) {
				return false;
			}
		}
		return true;
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