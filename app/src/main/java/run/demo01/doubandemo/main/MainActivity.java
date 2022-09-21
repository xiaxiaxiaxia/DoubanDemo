package run.demo01.doubandemo.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import run.demo01.doubandemo.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigationView = findViewById(R.id.bnv_view);

        //设置NavController和BottomNavigationView
        NavController navController = Navigation.findNavController(this,R.id.nav_main_frg);
        NavigationUI.setupWithNavController(navigationView,navController);
//
//        //设置NavController和AppBarConfiguration的关系
//        AppBarConfiguration barConfiguration = new AppBarConfiguration.Builder(R.id.movieFragment, R.id.favouriteFragment, R.id.meFragment).build();
//        NavigationUI.setupActionBarWithNavController(this,navController,barConfiguration);

    }
}