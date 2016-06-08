package read.bookmine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 6/1/2016.
 */
public class HomeTab extends Fragment {
    TabLayout tab;
    ViewPager vp;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View homepage_view=inflater.inflate(R.layout.home_tab,container,false);
        tab=(TabLayout)homepage_view.findViewById(R.id.home_tab);
        vp=(ViewPager)homepage_view.findViewById(R.id.home_vp);
        setupViewPager(vp);
        tab.setupWithViewPager(vp);
        getActivity().setTitle("BookMine");
        return homepage_view;
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());

        adapter.addFragment(new read.bookmine.ExploreTab(), "Explore");
        adapter.addFragment(new read.bookmine.MylibraryTab(), "My Library");
        adapter.addFragment(new read.bookmine.TrendingTab(), "Trending");
        viewPager.setAdapter(adapter);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {

            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            Bundle bund=getArguments();
            fragment.setArguments(bund);
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
