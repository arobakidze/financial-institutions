package com.financial.financialinstitutions.parser;

import com.financial.financialinstitutions.parser.model.XmlBank;
import com.financial.financialinstitutions.parser.model.XmlCustomer;
import com.financial.financialinstitutions.parser.model.XmlFinancialSector;
import com.financial.financialinstitutions.parser.model.XmlTransaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JaxbParser implements Parser {

    private static final Logger LOGGER = LogManager.getLogger(JaxbParser.class);

    @Override
    public void parse(String filePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(XmlFinancialSector.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            XmlFinancialSector sector = (XmlFinancialSector) unmarshaller.unmarshal(new File(filePath));

            LOGGER.info("JAXB Parser result: {}", sector);
            for (XmlBank bank : sector.getBanks()) {
                LOGGER.info("  Bank: {}", bank);
                for (XmlCustomer customer : bank.getCustomers()) {
                    LOGGER.info("    Customer: {}", customer);
                    LOGGER.info("      Account: {}", customer.getAccount());
                    for (XmlTransaction transaction : customer.getAccount().getTransactions()) {
                        LOGGER.info("        Transaction: {}", transaction);
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("JAXB parsing error: {}", e.getMessage());
        }
    }

}