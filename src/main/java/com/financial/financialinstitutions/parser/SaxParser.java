package com.financial.financialinstitutions.parser;

import com.financial.financialinstitutions.parser.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.SchemaFactory;
import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SaxParser extends DefaultHandler implements Parser {

    private static final Logger LOGGER = LogManager.getLogger(SaxParser.class);

    private XmlFinancialSector financialSector;
    private XmlBank currentBank;
    private XmlCustomer currentCustomer;
    private XmlAccount currentAccount;
    private XmlTransaction currentTransaction;

    private List<XmlBank> banks;
    private List<XmlCustomer> customers;
    private List<XmlTransaction> transactions;

    private StringBuilder currentValue;
    private boolean inTransaction = false;
    private boolean inAccount = false;
    private boolean inCustomer = false;
    private boolean inBank = false;

    @Override
    public void parse(String filePath) {
        try {
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File("src/main/resources/financial-institutions.xsd"));

            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setSchema(schema);
            factory.setNamespaceAware(true);

            javax.xml.parsers.SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(new File(filePath), this);

            LOGGER.info("SAX Parser result: {}", financialSector);
            for (XmlBank bank : financialSector.getBanks()) {
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
            LOGGER.error("SAX parsing error: {}", e.getMessage());
        }
    }

    @Override
    public void startDocument() {
        LOGGER.info("SAX: Started parsing document.");
        financialSector = new XmlFinancialSector();
        banks = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        currentValue = new StringBuilder();
        switch (qName) {
            case "bank":
                inBank = true;
                currentBank = new XmlBank();
                customers = new ArrayList<>();
                break;
            case "customer":
                inCustomer = true;
                currentCustomer = new XmlCustomer();
                transactions = new ArrayList<>();
                break;
            case "account":
                inAccount = true;
                currentAccount = new XmlAccount();
                break;
            case "transaction":
                inTransaction = true;
                currentTransaction = new XmlTransaction();
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        currentValue.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        String value = currentValue.toString().trim();
        switch (qName) {
            case "financialSector":
                break;
            case "name":
                if (inTransaction) break;
                if (inAccount) break;
                if (inCustomer) currentCustomer.setName(value);
                else if (inBank) currentBank.setName(value);
                else financialSector.setName(value);
                break;
            case "registrationNumber":
                if (inBank) currentBank.setRegistrationNumber(value);
                break;
            case "bankCode":
                currentBank.setBankCode(value);
                break;
            case "active":
                if (inAccount) currentAccount.setActive(Boolean.parseBoolean(value));
                else if (inBank) currentBank.setActive(Boolean.parseBoolean(value));
                break;
            case "totalAssets":
                currentBank.setTotalAssets(new BigDecimal(value));
                break;
            case "establishedDate":
                currentBank.setEstablishedDate(LocalDateTime.parse(value));
                break;
            case "email":
                currentCustomer.setEmail(value);
                break;
            case "verified":
                currentCustomer.setVerified(Boolean.parseBoolean(value));
                break;
            case "age":
                currentCustomer.setAge(Integer.parseInt(value));
                break;
            case "registeredAt":
                currentCustomer.setRegisteredAt(LocalDateTime.parse(value));
                break;
            case "owner":
                currentAccount.setOwner(value);
                break;
            case "balance":
                currentAccount.setBalance(new BigDecimal(value));
                break;
            case "accountType":
                currentAccount.setAccountType(value);
                break;
            case "amount":
                currentTransaction.setAmount(new BigDecimal(value));
                break;
            case "type":
                currentTransaction.setType(value);
                break;
            case "successful":
                currentTransaction.setSuccessful(Boolean.parseBoolean(value));
                break;
            case "transactionDate":
                currentTransaction.setTransactionDate(LocalDateTime.parse(value));
                break;
            case "transaction":
                transactions.add(currentTransaction);
                inTransaction = false;
                break;
            case "account":
                currentAccount.setTransactions(transactions);
                currentCustomer.setAccount(currentAccount);
                inAccount = false;
                break;
            case "customer":
                customers.add(currentCustomer);
                inCustomer = false;
                break;
            case "bank":
                currentBank.setCustomers(customers);
                banks.add(currentBank);
                inBank = false;
                break;
            case "banks":
                financialSector.setBanks(banks);
                break;
        }
    }

    @Override
    public void endDocument() {
        LOGGER.info("SAX: Finished parsing document.");
    }

    public XmlFinancialSector getFinancialSector() {
        return financialSector;
    }

}