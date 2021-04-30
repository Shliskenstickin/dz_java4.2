package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Offer;
import ru.netology.repository.OfferRepository;

import static org.junit.jupiter.api.Assertions.*;

class OfferManagerTest {
    private OfferRepository repository = new OfferRepository();
    private OfferManager manager = new OfferManager(repository);

    private Offer offer1 = new Offer(1, 10, "DME", "VKO", 300);
    private Offer offer2 = new Offer(2, 20, "DME", "SVO", 400);
    private Offer offer3 = new Offer(3, 30, "KXK", "VKO", 600);
    private Offer offer4 = new Offer(4, 40, "EIE", "KXK", 100);
    private Offer offer5 = new Offer(5, 30, "DME", "VKO", 500);
    private Offer offer6 = new Offer(6, 15, "DME", "SVO", 400);
    private Offer offer7 = new Offer(7, 20, "DME", "SVO", 700);

    @BeforeEach
    public void setUp() {
        manager.add(offer1);
        manager.add(offer2);
        manager.add(offer3);
        manager.add(offer4);
        manager.add(offer5);
        manager.add(offer6);
        manager.add(offer7);
    }

    @Test
    void shouldFind() {
        Offer[] expected = new Offer[]{offer1, offer5};
        Offer[] actual = manager.findByAirport("DME", "VKO");

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNoFind() {
        Offer[] expected = new Offer[0];
        Offer[] actual = manager.findByAirport("DME", "DME");

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindNoRegister() {
        Offer[] expected = new Offer[]{offer4};
        Offer[] actual = manager.findByAirport("eie", "kxk");

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindAndSortByPrice() {
        Offer[] expected = new Offer[]{offer6, offer2, offer7};
        Offer[] actual = manager.findByAirport("DME", "SVO");

        assertArrayEquals(expected, actual);
    }
}