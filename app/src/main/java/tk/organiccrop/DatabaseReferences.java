package tk.organiccrop;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DatabaseReferences {

    private static DatabaseReference root = FirebaseDatabase.getInstance().getReference();
    private static DatabaseReference customersDatabaseRef = root.child("customers");
    private static DatabaseReference distributorsDatabaseRef = root.child("distributors");


    public static DatabaseReference getCustomersDatabaseRef() {
        return customersDatabaseRef;
    }

    public static DatabaseReference getDistributorsDatabaseRef() {
        return distributorsDatabaseRef;
    }
}