package aclaudel.codurance.atm.context;

import aclaudel.codurance.atm.Account;
import aclaudel.codurance.atm.AccountRepository;
import aclaudel.codurance.atm.Atm;
import com.mongodb.client.MongoClients;
import org.bson.Document;

import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AtmMongoContext implements AtmContext {

    // mocks
    private AccountRepository accountRepository;

    // sut
    private Atm atm;

    // variables
    private UUID accountId;
    private int amount;

    @Override
    public void setup() {
        var collection = MongoClients.create()
                .getDatabase("atm")
                .getCollection("accounts");

        accountRepository = new AccountRepository() {
            @Override
            public void save(Account account) {
                var document = new Document();
                document.append("id", account.getId());
                document.append("balance", account.getBalance());

                var previousDocument = collection.find()
                        .filter(eq("id", accountId))
                        .first();
                System.out.println(document);
                if(previousDocument == null) {
                    collection.insertOne(document);
                } else {
                    collection.replaceOne(eq("id", account.getId()), document);
                }
            }

            @Override
            public Account getById(UUID accountId) {
                var document = collection.find()
                        .filter(eq("id", accountId))
                        .first();
                if(document == null) {
                    return null;
                }
                return new Account(document.get("id", UUID.class), document.get("balance", Integer.class));
            }
        };
        atm = new Atm(accountRepository);
    }

    @Override
    public void setup_account(int initialBalance) {
        accountId = AN_ACCOUNT_ID;
        Account account = new Account(accountId, initialBalance);
        accountRepository.save(account);
    }

    @Override
    public void setup_a_not_existing_account() {
        accountId = UUID.randomUUID();
    }

    @Override
    public void setup_amount_of_money(int amountOfMoney) {
        amount = amountOfMoney;
    }

    @Override
    public void assert_account_was_saved_with(int finalBalance) {
        assertEquals(finalBalance, accountRepository.getById(accountId).getBalance());
    }

    @Override
    public void do_withdraw() {
        atm.withdraw(accountId, amount);
    }

    @Override
    public void do_deposit() {
        atm.deposit(accountId, amount);
    }
}
