package com.example.TBSBank.jdbcpackagething;

import com.example.TBSBank.models.Account;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class AccountRowMapper implements RowMapper<Account> {
    @Override
    public Account mapRow(ResultSet row, int rowNum) throws SQLException{
        Account account = new Account();
        account.setAccountId(row.getLong("accountId"));
        account.setNickname(row.getString("nickname"));
        account.setRewards(row.getInt("rewards"));
        account.setBalance(row.getDouble("balance"));
        account.setCustomer(row.getLong("customerId"));

        return account;

    }
}
