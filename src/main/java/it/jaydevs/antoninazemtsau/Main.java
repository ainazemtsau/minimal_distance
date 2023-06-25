package it.jaydevs.antoninazemtsau;

import it.jaydevs.antoninazemtsau.service.MinimalDistanceService;
import it.jaydevs.antoninazemtsau.service.MinimalDistanceServiceImpl;

public class Main {
    private static MinimalDistanceService minimalDistanceService = new MinimalDistanceServiceImpl();
    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Please provide two words as arguments");
        }
        minimalDistanceService.minimalDistance(args[0], args[1]);
    }
}