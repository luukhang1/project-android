package com.example.projectfinal.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.projectfinal.fragment.FragmentAdd;
import com.example.projectfinal.fragment.FragmentProfile;
import com.example.projectfinal.fragment.FragmentSearch;
import com.example.projectfinal.fragment.HomeFragment;
import com.example.projectfinal.model.ImageUser;
import com.example.projectfinal.model.User;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private User user;
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior, User user) {
        super(fm, behavior);
        this.user = user;
        //System.out.println(this.user);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 : return  new HomeFragment(this.user);
            case 1 : return  new FragmentSearch(this.user);
            case 2 : return new FragmentAdd(this.user);
            case 3 : return new FragmentProfile(this.user);
            default: return new HomeFragment(this.user);
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}
