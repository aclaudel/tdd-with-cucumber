package aclaudel.codurance.atm.infrastructure;

import aclaudel.codurance.atm.Account;
import aclaudel.codurance.atm.AccountRepository;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;

public class MongoDBAccountRepository implements AccountRepository {

    MongoCollection<Document> collection;

    public MongoDBAccountRepository() {
        this.collection = MongoClients.create()
                .getDatabase("atm")
                .getCollection("accounts");
    }

    @Override
    public void save(Account account) {
        var document = new Document();
        document.append("id", account.getId());
        document.append("balance", account.getBalance());

        var previousDocument = collection.find()
                .filter(eq("id", account.getId()))
                .first();

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

}
