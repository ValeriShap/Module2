package test;

import main.products.*;
import main.repository.InvoiceRepository;
import main.service.Client;
import main.service.SalesAnalytics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.*;

class SalesAnalyticsTest {
    private SalesAnalytics target;
    private InvoiceRepository repository;
    Invoice invoice1;
    Invoice invoice2;
    Invoice invoice3;
    Telephone tel1;
    Telephone tel2;
    Telephone tel3;
    Television tv1;
    Television tv2;
    Customer customer1;
    Customer customer2;
    Customer customer3;

    @BeforeEach
    void setUp() {
        repository = InvoiceRepository.getInstance();
        target = SalesAnalytics.getInstance();
        tv1 = new Television("Xiaomi", "23", "OLED", "China", "21000");
        tv2 = new Television("Samsung", "45", "OLED", "", "32000");
        tel1 = new Telephone("Apple", "ALED", "50000", "A14");
        tel2 = new Telephone("Samsung", "ALED", "47000", "Re34");
        tel3 = new Telephone("Xiaomi", "ALED", "3200", "X12");
        customer1 = new Customer();
        customer1.setName(Client.OKSANA);
        customer1.setAge(17);
        customer1.setEmail("atuf@gmail.com");
        invoice1 = new Invoice();
        invoice1.setCustomer(customer1);
        invoice2 = new Invoice();
        customer2 = new Customer();
        customer2.setName(Client.ANTON);
        customer2.setAge(35);
        customer2.setEmail("fdnff@gmail.com");
        invoice2.setCustomer(customer2);
        invoice3 = new Invoice();
        customer3 = new Customer();
        customer3.setName(Client.OKSANA);
        customer3.setAge(54);
        customer3.setEmail("jkdfnf@gmail.com");
    }

    @Test
    void checkGetCatalog() {
        List<Invoice> invoices = new ArrayList<>();
        Assertions.assertEquals(invoices, invoice1.getTechnicsCatalog());
    }

    @Test
    void salesFromTypes() {
        List<Technics> tech = new ArrayList<>();
        tech.add(tv1);
        tech.add(tv2);
        tech.add(tel2);
        invoice1.setTechnicsCatalog(tech);
        Assertions.assertEquals(tech, invoice1.getTechnicsCatalog());
    }

    // В тесте все сравнивается верно,только в actual указывается Optional,а к не знаю как это применить или изменить
//   Хотя данные одинаковые
    @Test
    void minSumAndInfo() {
        List<Invoice> invoices1 = new ArrayList<>();
        invoice2.getTechnicsCatalog();
        invoice2.addProduct(tel2);
        invoice2.addProduct(tv1);
        invoices1.add(invoice1);
        Assertions.assertEquals(invoices1, target.minSumAndInfo(invoices1));
    }


    @Test
    void allSumSales() {
        List<Invoice> invoices = new ArrayList<>();
        invoice1.getTechnicsCatalog();
        invoice1.addProduct(tv1);
        invoice1.addProduct(tel1);
        invoices.add(invoice1);
        Assertions.assertEquals(71000.0, target.allSumSales(invoices));
    }

    @Test
    void invoiceFromRetail() {
        List<Invoice> invoices = new ArrayList<>();
        invoice3.setType("retail");
        invoice1.setType("wholesale");
        invoice2.setType("retail");
        invoices.add(invoice2);
        invoices.add(invoice3);
        Assertions.assertEquals(2, target.invoiceFromRetail(invoices));
    }

    @Test
    void oneTypeInvoice() {
        List<Invoice> invoices = new ArrayList<>();
        invoice3.addProduct(tv1);
        invoice2.addProduct(tel2);
        invoice1.addProduct(tel3);
        invoices.add(invoice1);
        invoices.add(invoice2);
        invoices.add(invoice3);
        Assertions.assertEquals(invoices, target.oneTypeInvoice(invoices));

    }

    @Test
    void firstThreeInvoice() {
        List<Invoice> invoices = new ArrayList<>();
        invoices.add(invoice1);
        invoices.add(invoice2);
        invoices.add(invoice3);
        Assertions.assertEquals(invoices, target.firstThreeInvoice(invoices));
    }

    @Test
    void infoAboutCustomersLowAge() {
        invoice1.setCustomer(customer1);
        List<Invoice> invoices = new ArrayList<>();
        Assertions.assertEquals(invoices, target.infoAboutCustomersLowAge(invoices));
    }

    @Test
    void sortAllInvoice() {
        List<Invoice> invoices = new ArrayList<>();
        invoice1.setCustomer(customer1);
        invoice2.setCustomer(customer2);
        invoice3.setCustomer(customer3);
        invoices.add(invoice1);
        invoices.add(invoice2);
        invoices.add(invoice3);
        Assertions.assertEquals(invoices, target.sortAllInvoice(invoices));
    }
}