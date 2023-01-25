package main.service;

import main.products.*;
import main.repository.InvoiceRepository;

import java.util.*;
import java.util.stream.Collectors;

public class SalesAnalytics {
    private static SalesAnalytics salesAnalytics;

    public static SalesAnalytics getInstance() {
        if (salesAnalytics == null) {
            salesAnalytics = new SalesAnalytics();
        }
        return salesAnalytics;
    }

    public Map<TechnicsType, Long> salesFromTypes(List<Invoice> invoices) {
        return invoices.stream()
                .flatMap(x -> x.getTechnicsCatalog().stream())
                .collect(Collectors.groupingBy(Technics::getTechnicsType, Collectors.counting()));
    }

    public Optional<Invoice> minSumAndInfo(List<Invoice> invoices) {
        return invoices.stream()
                .min(Comparator.comparingDouble(invoice -> invoice.getTechnicsCatalog().stream().mapToDouble(Technics::getPrice).sum()));

    }

    public void printMinSumAndInfo(Map<Integer, Customer> minSumAndInfo) {
        System.out.println("Min check: " + minSumAndInfo.keySet().toArray()[0]);
        Customer info = minSumAndInfo.get(minSumAndInfo.keySet().toArray()[0]);
        System.out.println("Client name: " + info.getName() + "; ID: " + info.getId() + "; Email: " + info.getEmail());
    }

    public Double allSumSales(List<Invoice> invoices) {
        return invoices.stream()
                .flatMap(s -> s.getTechnicsCatalog().stream())
                .mapToDouble(Technics::getPrice)
                .sum();
    }

    public long invoiceFromRetail(List<Invoice> invoices) {
        return invoices.stream()
                .filter(x -> x.getType().equals("retail"))
                .count();
    }

    public List<Invoice> oneTypeInvoice(List<Invoice> invoices) {
        return invoices.stream()
                .filter(x -> 1 == x.getTechnicsCatalog().stream()
                        .map(Technics::getTechnicsType)
                        .distinct()
                        .toArray()
                        .length).collect(Collectors.toList());
    }

    public List<Invoice> firstThreeInvoice(List<Invoice> invoices) {
        return invoices.stream()
                .limit(3)
                .collect(Collectors.toList());
    }

    public List<Invoice> infoAboutCustomersLowAge(List<Invoice> invoices) {
        return invoices.stream()
                .filter(x -> x.getCustomer().getAge() < 18)
                .peek(s -> s.setType("low_age"))
                .collect(Collectors.toList());
    }

    public List<Invoice> sortAllInvoice(List<Invoice> invoices) {
        Comparator<Invoice> sortedByAge = Comparator.comparing(x -> x.getCustomer().getAge());
        Comparator<Invoice> sortedByInvoiceProducts = Comparator.comparing(x -> x.getTechnicsCatalog().size());
        Comparator<Invoice> sortedByAllSum = Comparator.comparing(x -> x.getTechnicsCatalog()
                .stream().mapToDouble(Technics::getPrice).sum());
        return invoices.stream()
                .sorted(sortedByAge
                        .thenComparing(sortedByInvoiceProducts)
                        .thenComparing(sortedByAllSum)
                ).collect(Collectors.toList());
    }

    public void printSalesAnalytics() {
        System.out.println("-------------------------------------");
        System.out.println("Number of technics sold by category: "
                + this.salesFromTypes(InvoiceRepository.getInstance().getAll()));
        System.out.println("Sum of min check and info about customers: "
                + this.minSumAndInfo(InvoiceRepository.getInstance().getAll()));
        System.out.println("Sum of all invoice: " + this.allSumSales(InvoiceRepository.getInstance().getAll()));
        System.out.println("Number of check with type retail: "
                + this.invoiceFromRetail(InvoiceRepository.getInstance().getAll()));
        System.out.println("Check with one type type technics: "
                + this.oneTypeInvoice(InvoiceRepository.getInstance().getAll()));
        System.out.println("The first three check: " + this.firstThreeInvoice(InvoiceRepository.getInstance().getAll()));
        System.out.println("Info about check from customer under 18 age: "
                + this.infoAboutCustomersLowAge(InvoiceRepository.getInstance().getAll()));
        System.out.println("Invoices sorted: " + this.sortAllInvoice(InvoiceRepository.getInstance().getAll()));
        System.out.println("-------------------------------------");
    }

}
