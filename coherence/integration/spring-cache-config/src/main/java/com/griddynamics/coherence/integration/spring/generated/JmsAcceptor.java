//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.04.16 at 09:47:17 PM MSD 
//


package com.griddynamics.coherence.integration.spring.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "queueConnectionFactoryName",
    "queueName"
})
@XmlRootElement(name = "jms-acceptor")
public class JmsAcceptor {

    @XmlElement(name = "queue-connection-factory-name", required = true)
    protected String queueConnectionFactoryName;
    @XmlElement(name = "queue-name", required = true)
    protected String queueName;

    /**
     * Gets the value of the queueConnectionFactoryName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQueueConnectionFactoryName() {
        return queueConnectionFactoryName;
    }

    /**
     * Sets the value of the queueConnectionFactoryName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQueueConnectionFactoryName(String value) {
        this.queueConnectionFactoryName = value;
    }

    /**
     * Gets the value of the queueName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQueueName() {
        return queueName;
    }

    /**
     * Sets the value of the queueName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQueueName(String value) {
        this.queueName = value;
    }

}
