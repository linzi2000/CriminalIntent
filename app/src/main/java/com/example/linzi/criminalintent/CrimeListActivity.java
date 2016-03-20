package com.example.linzi.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by linzi on 2016/2/29.
 */
public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment(){
        return new CrimeListFragment();
    }

}
