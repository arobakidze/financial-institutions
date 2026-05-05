package com.financial.financialinstitutions.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.financial.financialinstitutions.parser.model.XmlBank;
import com.financial.financialinstitutions.parser.model.XmlCustomer;
import com.financial.financialinstitutions.parser.model.XmlFinancialSector;
import com.financial.financialinstitutions.parser.model.XmlTransaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class JacksonParser implements Parser {

    private static final Logger LOGGER = LogManager.getLogger(JacksonParser.class);

    @Override
    public void parse(String filePath) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            XmlFinancialSector sector = mapper.readValue(new File(filePath), XmlFinancialSector.class);

            LOGGER.info("Jackson Parser result: {}", sector);
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
            LOGGER.error("Jackson parsing error: {}", e.getMessage());
        }
    }

}