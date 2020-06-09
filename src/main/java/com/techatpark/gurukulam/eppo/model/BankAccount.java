package com.techatpark.gurukulam.eppo.model;

import java.util.Date;

public class BankAccount {
    /**
     * tells the id.
     */
    private Integer id;
    /**
     * tells the payment type.
     */
    private Short paymentType;
    /**
     * tells the bank account nick name.
     */
    private String bankAccountNickName;
    /**
     * tells the bank account holder name.
     */
    private String bankAccountHolderName;
    /**
     * tells the bank account type.
     */
    private String bankAccountType;
    /**
     * tells the bank routing number.
     */
    private String bankRoutingNumber;
    /**
     * tells the bank name.
     */
    private String bankName;
    /**
     * tells the bank account number.
     */
    private String bankAccountNumber;
    /**
     * tells the customer or account.
     */
    private String customerOrAccount;
    /**
     * tells the card holder first name.
     */
    private String cardHolderFirstName;
    /**
     * tells the card holder last name.
     */
    private String cardHolderLastName;
    /**
     * tells the card number.
     */
    private String cardNumber;
    /**
     * tells the card type.
     */
    private String cardType;
    /**
     * tells the card expiry.
     */
    private String cardExpiry;
    /**
     * tells the credit debit type.
     */
    private String creditDebitType;
    /**
     * tells the created by.
     */
    private Integer createdBy;
    /**
     * tells the updated by.
     */
    private Integer updatedBy;
    /**
     * tells the is deleted value.
     */
    private Short isDeleted;
    /**
     * tells the status.
     */
    private Short status;
    /**
     * tells the customer or account id.
     */
    private Integer customerOrAccountId;
    /**
     * tells the address id.
     */
    private Integer addressId;
    /***
     * tells the created at.
     */
    private Date createdAt;
    /**
     * tells the updated at.
     */
    private Date updatedAt;

    /**
     * gets the id.
     * 
     * @return Integer
     */
    public Integer getId() {
        return id;
    }

    /**
     * sets the id.
     * 
     * @param id
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     * gets the payment type.
     * 
     * @return Short
     */
    public Short getPaymentType() {
        return paymentType;
    }

    /**
     * sets the payment type.
     * 
     * @param paymentType
     */
    public void setPaymentType(final Short paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * gets the bank account nick name.
     * 
     * @return String
     */
    public String getBankAccountNickName() {
        return bankAccountNickName;
    }

    /**
     * sets the bank account nick name.
     * 
     * @param bankAccountNickName
     */
    public void setBankAccountNickName(final String bankAccountNickName) {
        this.bankAccountNickName = bankAccountNickName;
    }

    /**
     * gets the bank account holder name.
     * 
     * @return String
     */
    public String getBankAccountHolderName() {
        return bankAccountHolderName;
    }

    /**
     * sets the bank account holder name.
     * 
     * @param bankAccountHolderName
     */
    public void setBankAccountHolderName(final String bankAccountHolderName) {
        this.bankAccountHolderName = bankAccountHolderName;
    }

    /**
     * gets the bank account type.
     * 
     * @return String
     */
    public String getBankAccountType() {
        return bankAccountType;
    }

    /**
     * sets the bank account type.
     * 
     * @param bankAccountType
     */
    public void setBankAccountType(final String bankAccountType) {
        this.bankAccountType = bankAccountType;
    }

    /**
     * gets the bank routing number.
     * 
     * @return String
     */
    public String getBankRoutingNumber() {
        return bankRoutingNumber;
    }

    /**
     * sets the bank routing number.
     * 
     * @param bankRoutingNumber
     */
    public void setBankRoutingNumber(final String bankRoutingNumber) {
        this.bankRoutingNumber = bankRoutingNumber;
    }

    /**
     * gets the bank name.
     * 
     * @return String
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * sets the bank name.
     * 
     * @param bankName
     */
    public void setBankName(final String bankName) {
        this.bankName = bankName;
    }

    /**
     * gets the bank account number.
     * 
     * @return String
     */
    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    /**
     * sets the bank account number.
     * 
     * @param bankAccountNumber
     */
    public void setBankAccountNumber(final String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    /**
     * gets customer or account.
     * 
     * @return String
     */
    public String getCustomerOrAccount() {
        return customerOrAccount;
    }

    /**
     * sets the customer or account.
     * 
     * @param customerOrAccount
     */
    public void setCustomerOrAccount(final String customerOrAccount) {
        this.customerOrAccount = customerOrAccount;
    }

    /**
     * gets card holder first name.
     * 
     * @return String
     */
    public String getCardHolderFirstName() {
        return cardHolderFirstName;
    }

    /**
     * sets card holder first name.
     * 
     * @param cardHolderFirstName
     */
    public void setCardHolderFirstName(final String cardHolderFirstName) {
        this.cardHolderFirstName = cardHolderFirstName;
    }

    /**
     * gets card holder last name.
     * 
     * @return String
     */
    public String getCardHolderLastName() {
        return cardHolderLastName;
    }

    /**
     * sets the card holder last name.
     * 
     * @param cardHolderLastName
     */
    public void setCardHolderLastName(final String cardHolderLastName) {
        this.cardHolderLastName = cardHolderLastName;
    }

    /**
     * gets the card number.
     * 
     * @return String
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * sets the card number.
     * 
     * @param cardNumber
     */
    public void setCardNumber(final String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * gets the card type.
     * 
     * @return String
     */
    public String getCardType() {
        return cardType;
    }

    /**
     * sets the card type.
     * 
     * @param cardType
     */
    public void setCardType(final String cardType) {
        this.cardType = cardType;
    }

    /**
     * gets the card expiry.
     * 
     * @return String
     */
    public String getCardExpiry() {
        return cardExpiry;
    }

    /**
     * sets the card expiry.
     * 
     * @param cardExpiry
     */
    public void setCardExpiry(final String cardExpiry) {
        this.cardExpiry = cardExpiry;
    }

    /**
     * gets the credit debit type.
     * 
     * @return String
     */
    public String getCreditDebitType() {
        return creditDebitType;
    }

    /**
     * sets the credit debit type.
     * 
     * @param creditDebitType
     */
    public void setCreditDebitType(final String creditDebitType) {
        this.creditDebitType = creditDebitType;
    }

    /**
     * gets the created by.
     * 
     * @return Integer
     */
    public Integer getCreatedBy() {
        return createdBy;
    }

    /**
     * sets created by.
     * 
     * @param createdBy
     */
    public void setCreatedBy(final Integer createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * gets updated by.
     * 
     * @return Integer
     */
    public Integer getUpdatedBy() {
        return updatedBy;
    }

    /**
     * sets updated by.
     * 
     * @param updatedBy
     */
    public void setUpdatedBy(final Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * get is deleted value.
     * 
     * @return Short
     */
    public Short getIsDeleted() {
        return isDeleted;
    }

    /**
     * sets is deleted value.
     * 
     * @param isDeleted
     */
    public void setIsDeleted(final Short isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * gets status.
     * 
     * @return Short
     */
    public Short getStatus() {
        return status;
    }

    /**
     * sets status.
     * 
     * @param status
     */
    public void setStatus(final Short status) {
        this.status = status;
    }

    /**
     * gets customer or account id.
     * 
     * @return Integer
     */
    public Integer getCustomerOrAccountId() {
        return customerOrAccountId;
    }

    /**
     * sets customer or account id.
     * 
     * @param customerOrAccountId
     */
    public void setCustomerOrAccountId(final Integer customerOrAccountId) {
        this.customerOrAccountId = customerOrAccountId;
    }

    /**
     * gets the address id.
     * 
     * @return Integer
     */
    public Integer getAddressId() {
        return addressId;
    }

    /**
     * sets the address id.
     * 
     * @param addressId
     */
    public void setAddressId(final Integer addressId) {
        this.addressId = addressId;
    }

    /**
     * gets created at value.
     * 
     * @return Date
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * sets the created at value.
     * 
     * @param createdAt
     */
    public void setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * gets the updated at value.
     * 
     * @return Date
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * sets the updated at value.
     * 
     * @param updatedAt
     */
    public void setUpdatedAt(final Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
