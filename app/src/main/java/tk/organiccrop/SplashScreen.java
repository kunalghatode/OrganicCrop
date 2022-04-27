package tk.organiccrop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import tk.organiccrop.models.Distributor;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        if (FirebaseAuth.getInstance().getCurrentUser() != null){
            checkAccountType();
        }
    }

    private void checkAccountType() {

        String currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DatabaseReferences.getCustomersDatabaseRef().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(currentUserId)){
                    sendToCustomerHome();
                }else{
                    sendToDistributorHome();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void loginCustomer(View view){
        sendToCustomerLogin();
    }

    public void loginDistributor(View view){
        sendToDistributorLogin();
    }

    public void createAccount(View view){
        sendToSignup();
    }

    private void sendToSignup() {
        Intent intent = new Intent(SplashScreen.this, SignupActivity.class);
        startActivity(intent);
    }


    private void sendToDistributorLogin() {
        Intent intent = new Intent(SplashScreen.this, DistributorLoginActivity.class);
        startActivity(intent);
    }

    private void sendToCustomerLogin() {
        Intent intent = new Intent(SplashScreen.this, CustomerLoginActivity.class);
        startActivity(intent);
    }


    private void sendToDistributorHome() {
        Intent intent = new Intent(this, DistributorHomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void sendToCustomerHome() {
        Intent intent = new Intent(this, CustomerHomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


}