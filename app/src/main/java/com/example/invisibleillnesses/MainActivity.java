package com.example.invisibleillnesses;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.invisibleillnesses.Dialog.LogoutDialog;
import com.example.invisibleillnesses.Pages.CheckOutActivity;
import com.example.invisibleillnesses.Pages.ProductCartActivity;
import com.example.invisibleillnesses.Pages.StripePaymentActivity;
import com.example.invisibleillnesses.Stripe.AcceptedPaymentMainActivity;
import com.example.invisibleillnesses.Stripe.StripeCheckoutActivity;
import com.example.invisibleillnesses.databinding.ActivityMainBinding;
import com.example.invisibleillnesses.ui.Applicationform.FormFragment;
import com.example.invisibleillnesses.ui.home.HomeFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    ImageView icon_fb, icon_meta, icon_link;
    FragmentTransaction ft;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        icon_fb = findViewById(R.id.icon_fb);
        icon_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.facebook.com/invisibleillnessesinc/"));
                startActivity(intent);
            }
        });
        icon_meta = findViewById(R.id.icon_meta);
        icon_meta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.instagram.com/invisibleillneseswa/"));
                startActivity(intent);
            }
        });
        icon_link = findViewById(R.id.icon_link);
        icon_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.linkedin.com/in/invisibleillnesses/"));
                startActivity(intent);
            }
        });


        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_form, R.id.nav_events, R.id.nav_shop, R.id.nav_donation, R.id.nav_aboutus)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                int id = item.getItemId();
//                switch (id) {
//                    case R.id.nav_home:
//
//                        HomeFragment fragment = new HomeFragment();
//                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                        fragmentTransaction.replace(R.id.nav_host_fragment_content_main, fragment, "1");
//                        fragmentTransaction.commit();
//                        return  true;
//
//
//                    case R.id.nav_form:
//                        FormFragment fragment1 = new FormFragment();
//                        FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
//                        fragmentTransaction1.replace(R.id.nav_host_fragment_content_main, fragment1, "2");
//                        fragmentTransaction1.commit();
//                        return true;
//
//                    case R.id.nav_events:
//                        break;
//                    case R.id.nav_shop:
//                        break;
//                    case R.id.nav_donation:
//                        break;
//                    case R.id.nav_aboutus:
//                        break;
//                    case R.id.nav_logout:
//                        break;
//                    default:
//                        break;
//                }
//
//
//                return false;
//            }
//        });


        //updateNavHeader();
    }


    private void loadFragment(HomeFragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment_content_main, fragment).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.product_cart:
                Intent i = new Intent(MainActivity.this, ProductCartActivity.class);
                startActivity(i);
                return true;
            case R.id.log_out:
                LogoutDialog logoutDialogBox = new LogoutDialog();
                logoutDialogBox.show(getSupportFragmentManager(), "Logout Dialog Box");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


}