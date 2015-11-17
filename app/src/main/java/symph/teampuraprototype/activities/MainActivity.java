package symph.teampuraprototype.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import symph.teampuraprototype.R;
import symph.teampuraprototype.fragments.PreLoginFragment;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.main_fragment_container);

        if (fragment == null) {
            fragment = new PreLoginFragment();
            fm.beginTransaction()
                    .add(R.id.main_fragment_container, fragment)
                    .commit();
        }
    }

}
