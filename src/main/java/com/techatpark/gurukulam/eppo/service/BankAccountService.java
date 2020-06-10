package com.techatpark.gurukulam.eppo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.techatpark.gurukulam.eppo.model.BankAccount;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class BankAccountService {
    /**
     * this is used to execute a connection with a database.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     */
    private final DataSource dataSource;

    /**
     * Creates a service for BankAccount related operations.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public BankAccountService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into BankAccount table.
     * 
     * @param newBankAccount
     * @return reads the input data
     */
    public BankAccount create(final BankAccount newBankAccount) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("bank_accounts")
                .usingGeneratedKeyColumns("id").usingColumns("payment_type", "bank_account_nick_name",
                        "bank_account_holder_name", "bank_account_type", "bank_routing_number", "bank_name",
                        "bank_account_number", "customer_or_account", "card_holder_first_name", "card_holder_last_name",
                        "card_number", "card_type", "card_expiry", "credit_debit_type", "created_by", "updated_by",
                        "is_deleted", "status", "customer_or_account_id", "address_id", "created_at");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("payment_type", newBankAccount.getPaymentType());
        valuesMap.put("bank_account_nick_name", newBankAccount.getBankAccountNickName());
        valuesMap.put("bank_account_holder_name", newBankAccount.getBankAccountHolderName());
        valuesMap.put("bank_account_type", newBankAccount.getBankAccountType());
        valuesMap.put("bank_routing_number", newBankAccount.getBankRoutingNumber());
        valuesMap.put("bank_name", newBankAccount.getBankName());
        valuesMap.put("bank_account_number", newBankAccount.getBankAccountNumber());
        valuesMap.put("customer_or_account", newBankAccount.getCustomerOrAccount());
        valuesMap.put("card_holder_first_name", newBankAccount.getCardHolderFirstName());
        valuesMap.put("card_holder_last_name", newBankAccount.getCardHolderLastName());
        valuesMap.put("card_number", newBankAccount.getCardNumber());
        valuesMap.put("card_type", newBankAccount.getCardType());
        valuesMap.put("card_expiry", newBankAccount.getCardExpiry());
        valuesMap.put("credit_debit_type", newBankAccount.getCreditDebitType());
        valuesMap.put("created_by", newBankAccount.getCreatedBy());
        valuesMap.put("updated_by", newBankAccount.getUpdatedBy());
        valuesMap.put("is_deleted", newBankAccount.getIsDeleted());
        valuesMap.put("status", newBankAccount.getStatus());
        valuesMap.put("customer_or_account_id", newBankAccount.getCustomerOrAccountId());
        valuesMap.put("address_id", newBankAccount.getAddressId());
        // Actual Query Execution happens
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue()).get();
    }

    /**
     * reads from table BankAccount.
     *
     * @param id
     * @return BankAccount
     */
    public Optional<BankAccount> read(final Integer id) {
        final String query = "SELECT payment_type, bank_account_nick_name, bank_account_holder_name, bank_account_type, bank_routing_number, bank_name, bank_account_number, customer_or_account, card_holder_first_name, card_holder_last_name, card_number, card_type, card_expiry, credit_debit_type, created_by, updated_by, is_deleted, status, customer_or_account_id, address_id, created_at, updated_at FROM bank_accounts WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update table BankAccount.
     *
     * @param id
     * @param newBankAccount
     * @return BankAccount
     */
    public BankAccount update(final Integer id, final BankAccount newBankAccount) {
        final String query = "UPDATE bank_accounts SET payment_type = ?, bank_account_nick_name = ?, bank_account_holder_name = ?, bank_account_type = ?, bank_routing_number = ?, bank_name = ?, bank_account_number = ?, customer_or_account = ?, card_holder_first_name = ?, card_holder_last_name = ?, card_number = ?, card_type = ?, card_expiry = ?, credit_debit_type = ?, created_by = ?, updated_by = ?, is_deleted = ?, status = ?, customer_or_account_id = ?, address_id = ?, created_at = CURRENT_TIMESTAMP, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(query, newBankAccount.getPaymentType(), newBankAccount.getBankAccountNickName(),
                newBankAccount.getBankAccountHolderName(), newBankAccount.getBankAccountType(),
                newBankAccount.getBankRoutingNumber(), newBankAccount.getBankName(),
                newBankAccount.getBankAccountNumber(), newBankAccount.getCustomerOrAccount(),
                newBankAccount.getCardHolderFirstName(), newBankAccount.getCardHolderLastName(),
                newBankAccount.getCardNumber(), newBankAccount.getCardType(), newBankAccount.getCardExpiry(),
                newBankAccount.getCreditDebitType(), newBankAccount.getCreatedBy(), newBankAccount.getUpdatedBy(),
                newBankAccount.getIsDeleted(), newBankAccount.getStatus(), newBankAccount.getCustomerOrAccountId(),
                newBankAccount.getAddressId(), id);
        return read(id).get();
    }

    /**
     * Delete all from BankAccount.
     * 
     * @param id
     * @return BankAccount
     */
    public Integer delete(final Integer id) {
        final String query = "DELETE FROM bank_accounts WHERE id = ?";
        return jdbcTemplate.update(query);
    }

    /**
     * Delete all from BankAccount.
     *
     * @return BankAccount
     */
    public Integer delete() {
        final String query = "DELETE FROM bank_accounts";
        return jdbcTemplate.update(query);
    }

    /**
     * gets a list of all in BankAccount.
     *
     * @return BankAccount
     */
    public List<BankAccount> list() {
        final String query = "SELECT id, payment_type, bank_account_nick_name, bank_account_holder_name, bank_account_type, bank_routing_number, bank_name, bank_account_number, customer_or_account, card_holder_first_name, card_holder_last_name, card_number, card_type, card_expiry, credit_debit_type, created_by, updated_by, is_deleted, status, customer_or_account_id, address_id, created_at, updated_at FROM bank_accounts";
        return jdbcTemplate.query(query, this::mapRow);
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @return BankAccount
     * @throws SQLException
     */
    private BankAccount mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final BankAccount bankAccount = new BankAccount();
        bankAccount.setId(rs.getInt("id"));
        bankAccount.setPaymentType(rs.getShort("payment_type"));
        bankAccount.setBankAccountNickName(rs.getString("bank_account_nick_name"));
        bankAccount.setBankAccountHolderName(rs.getString("bank_account_holder_name"));
        bankAccount.setBankAccountType(rs.getString("bank_account_type"));
        bankAccount.setBankRoutingNumber(rs.getString("bank_routing_number"));
        bankAccount.setBankName(rs.getString("bank_name"));
        bankAccount.setBankAccountNumber(rs.getString("bank_account_number"));
        bankAccount.setCustomerOrAccount(rs.getString("customer_or_account"));
        bankAccount.setCardHolderFirstName(rs.getString("card_holder_first_name"));
        bankAccount.setCardHolderLastName(rs.getString("card_holder_last_name"));
        bankAccount.setCardNumber(rs.getString("card_number"));
        bankAccount.setCardType(rs.getString("card_type"));
        bankAccount.setCardExpiry(rs.getString("card_expiry"));
        bankAccount.setCreditDebitType(rs.getString("credit_debit_type"));
        bankAccount.setCreatedBy(rs.getInt("created_by"));
        bankAccount.setUpdatedBy(rs.getInt("updated_by"));
        bankAccount.setIsDeleted(rs.getShort("is_deleted"));
        bankAccount.setStatus(rs.getShort("status"));
        bankAccount.setCustomerOrAccountId(rs.getInt("customer_or_account_id"));
        bankAccount.setAddressId(rs.getInt("address_id"));
        bankAccount.setCreatedAt(rs.getDate("created_at"));
        bankAccount.setUpdatedAt(rs.getDate("updated_at"));
        return bankAccount;
    }
}
