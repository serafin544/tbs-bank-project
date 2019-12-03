public List<Deposit> getAllDepositsForAccount(Long accountId) {
        List<Deposit> listOfDeposits = new ArrayList<>();
        Optional<Account> acc = accountRepository.findById(accountId);
        if (acc.isPresent()){
            //    listOfDeposits = depository.findAllByPayeeId(accountId);

              String query = "SELECT * FROM deposit WHERE accountId=?";
              List<Deposit> d = template.query(query, new Object[]{accountId}, new BeanPropertyRowMapper<>(Deposit.class));


            return d;
        }
        else
            return null;

    }

    public Optional<Deposit> getDepositById(Long depositId) {
        String query = "SELECT * FROM deposit WHERE DEPOSIT_ID=?" ;
        Deposit deposit = template.queryForObject(query,new Object[]{depositId},
                new BeanPropertyRowMapper<>(Deposit.class));
        // depository.findById(depositId);
               Optional<Deposit> depositOptional = Optional.of(deposit);

        return depositOptional;
    }

    public Deposit addDeposit(Deposit deposit, Long accountId) {

        //depository.save(deposit);
              //  String query = "INSERT INTO DEPOSIT ";
            // int update - template.update(query,deposit);
             if(accountRepository.findById(accountId).isPresent()){
                 String query = "INSERT INTO deposit VALUES(?,?,?,?,?,?,?,?,?)";
                 template.update(query,deposit.getId(),deposit.getStatus(),deposit.getMedium(),deposit.getType(),
                         deposit.getTransaction_date(),deposit.getPayeeId(),deposit.getAmount(),
                         deposit.getDescription(),deposit.getAccountId());

                 return deposit;

             }
             return null;

       }

    public Deposit updateDeposit(Deposit deposit, Long depositId) {
        if(accountRepository.findById(depositId).isPresent()){
            String query = "UPDATE deposit " +
                    "SET account_id ='" + deposit.getAccountId() + "'," +
                    "amount ='" + deposit.getAmount() + "'," +
                    "description ='" + deposit.getDescription() + "'," +
                    "medium ='" + deposit.getMedium() + "'," +
                    "payee_id ='" + deposit.getPayeeId() + "'," +
                    "status ='" + deposit.getStatus() + "'," +
                    "transaction_date ='" + deposit.getTransaction_date() + "'," +
                    "type ='" + deposit.getType() + "'" +
                    " WHERE DEPOSIT_ID= ?";
            template.update(query, depositId);
            return deposit;
        }
        return null;
    }

    public void deleteDeposit(Long depositId) {


        String sql = "DELETE FROM deposit WHERE deposit_id=?";
        template.update(sql,depositId);

        /*depository.deleteById(depositId);*/
    }
}

