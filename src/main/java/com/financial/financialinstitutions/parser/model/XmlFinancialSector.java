package com.financial.financialinstitutions.parser.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "financialSector")
public class XmlFinancialSector {

    private String name;
    private List<XmlBank> banks;

    public XmlFinancialSector() {
    }

    public String getName() {
        return name;
    }

    @XmlElement(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    public List<XmlBank> getBanks() {
        return banks;
    }

    @XmlElementWrapper(name = "banks")
    @XmlElement(name = "bank")
    public void setBanks(List<XmlBank> banks) {
        this.banks = banks;
    }

    @Override
    public String toString() {
        return "XmlFinancialSector{name='" + name + "', banks=" + banks + "}";
    }
}