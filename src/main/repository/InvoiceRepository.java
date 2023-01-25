package main.repository;

import main.products.Invoice;

import java.util.ArrayList;
import java.util.List;

public class InvoiceRepository {
    private static InvoiceRepository instance;
    private List<Invoice> INVOICE_REPOSITORY = new ArrayList<>();

    public InvoiceRepository() {
    }

    public static InvoiceRepository getInstance() {
        if (instance == null) {
            instance = new InvoiceRepository();
        }
        return instance;
    }

    public void save(Invoice invoice) {
        INVOICE_REPOSITORY.add(invoice);
    }

    public List<Invoice> getAll() {
        return INVOICE_REPOSITORY;
    }

}
