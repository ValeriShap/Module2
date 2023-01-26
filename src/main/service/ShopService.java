package main.service;

import main.exception.InvalidLineException;
import main.products.*;
import main.repository.InvoiceRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class ShopService {
    private int seriesIndex;
    private int typeIndex;
    private int modelIndex;
    private int screenTypeIndex;
    private int priceIndex;
    private int diagonalIndex;
    private int countryIndex;
    private final Random random = new Random();
    private static InvoiceRepository INVOICE_REPOSITORY = InvoiceRepository.getInstance();
    private List<Technics> products = new ArrayList<>();

    public ShopService() {
        try {
            initProductListFromCsv("resources/model.csv");
        } catch (InvalidLineException | IOException e) {
            e.printStackTrace();
        }
    }

    public void initProductListFromCsv(String filePath) throws InvalidLineException, IOException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        final InputStream recourcesStream = loader.getResourceAsStream(filePath);
        assert recourcesStream != null;
        BufferedReader br = new BufferedReader(new InputStreamReader(recourcesStream));
        String line;
        initAllIndex(br.readLine());

        while ((line = br.readLine()) != null) {
            String[] separate = line.split(";");
            String type = separate[typeIndex];
            if (type.equalsIgnoreCase("TELEPHONE")) {
                checkForErrors(separate[seriesIndex], separate[modelIndex], separate[priceIndex]);
                products.add(new Telephone(separate[seriesIndex], separate[screenTypeIndex],
                        separate[priceIndex], separate[modelIndex]));
            } else {
                checkForErrors(separate[seriesIndex], separate[diagonalIndex], separate[seriesIndex],
                        separate[countryIndex], separate[priceIndex]);
                products.add(new Television(separate[seriesIndex],
                        separate[diagonalIndex],
                        separate[screenTypeIndex], separate[countryIndex],
                        separate[priceIndex]));
            }

        }
    }

    private void initAllIndex(String s) {

        String[] columns = s.split(";");
        for (int i = 0; i < columns.length; i++) {
            if (columns[i].equals("type")) {
                typeIndex = i;
                continue;
            }
            if (columns[i].equals("series")) {
                seriesIndex = i;
                continue;
            }
            if (columns[i].equals("model")) {
                modelIndex = i;
                continue;
            }
            if (columns[i].equals("screenType")) {
                screenTypeIndex = i;
                continue;
            }
            if (columns[i].equals("price")) {
                priceIndex = i;
                continue;
            }
            if (columns[i].equals("diagonal")) {
                diagonalIndex = i;
                continue;
            }
            if (columns[i].equals("country")) {
                countryIndex = i;
            }
        }
    }

    private void checkForErrors(String... fields) throws InvalidLineException {
        for (String field : fields) {
            if (field.isBlank()) {
                throw new InvalidLineException("Error", "required field is empty");
            }
        }
    }


    public Invoice createRandomInvoice() {
        List<Technics> randomProductList = new ArrayList<>();
        int randomInvoiceProductsSize = random.nextInt(5) + 1;
        for (int i = 0; i < randomInvoiceProductsSize; i++) {
            int randomIndex = random.nextInt(products.size());
            randomProductList.add(products.stream().skip(randomIndex).findFirst().orElseThrow());
        }
        Customer randomClient = new PersonService().createClient();
        Invoice resultInvoice = new Invoice();
        resultInvoice.setCustomer(randomClient);
        resultInvoice.setTechnicsCatalog(randomProductList);
        resultInvoice.saleType();
        INVOICE_REPOSITORY.save(resultInvoice);
        return resultInvoice;
    }

}
