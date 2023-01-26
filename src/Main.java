import main.products.Invoice;
import main.repository.InvoiceRepository;
import main.service.SalesAnalytics;
import main.service.ShopService;


public class Main {
    public static void main(String[] args) {
        ShopService service = new ShopService();
        Invoice invoice = new Invoice();
        InvoiceRepository invoiceRepository = InvoiceRepository.getInstance();
        SalesAnalytics salesAnalytics = new SalesAnalytics();
        while (invoiceRepository.getAll().size() < 15) {
            System.out.println(service.createRandomInvoice());
        }
        salesAnalytics.printSalesAnalytics();
    }
}