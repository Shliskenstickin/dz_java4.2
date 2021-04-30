package ru.netology.manager;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.netology.domain.Offer;
import ru.netology.repository.OfferRepository;

import java.util.Arrays;
import java.util.Comparator;

@NoArgsConstructor
@AllArgsConstructor
public class OfferManager {
    private OfferRepository repository;

    public void add(Offer item) {
        repository.save(item);
    }

    public Offer[] findByAirportSortByPrice(String from, String to) {
        Offer[] offers = repository.findAll();
        Offer[] result = new Offer[0];
        int count = 0;
        for (Offer offer : offers) {
            if (offer.getDepartureAirport().equalsIgnoreCase(from) && offer.getArrivalAirport().equalsIgnoreCase(to)) {
                Offer[] tmp = new Offer[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[count] = offer;
                count++;
                result = tmp;
            }
        }
        Arrays.sort(result);
        return result;
    }

    public Offer[] findByAirportSortByTravelTime(String from, String to, Comparator<Offer> comparator) {
        Offer[] offers = repository.findAll();
        Offer[] result = new Offer[0];
        int count = 0;
        for (Offer offer : offers) {
            if (offer.getDepartureAirport().equalsIgnoreCase(from) && offer.getArrivalAirport().equalsIgnoreCase(to)) {
                Offer[] tmp = new Offer[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[count] = offer;
                count++;
                result = tmp;
            }
        }
        Arrays.sort(result, comparator);
        return result;
    }
}
