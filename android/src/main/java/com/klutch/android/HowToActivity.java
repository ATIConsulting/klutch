package com.klutch.android;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HowToActivity extends Activity {

    TourPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_howto);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new TourPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == mSectionsPagerAdapter.getCount() - 1) {
                    new Handler().postDelayed(new Runnable(){
                        public void run() {
                            finish();
                            overridePendingTransition(0, 0);
                        }
                    }, 250);

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class TourPagerAdapter extends FragmentPagerAdapter {

        public TourPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == getCount() - 1 ) {
                return new Fragment() {
                    @Override
                    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                        return super.onCreateView(inflater, container, savedInstanceState);
                    }
                };
            }
            // getItem is called to instantiate the fragment for the given page.
            // Return a TourFragment (defined as a static inner class below).
            return TourFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 5;
        }

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class TourFragment extends Fragment {

        private static final String LAYOUT_POSITION = "section_number";
        private int mPosition;

        public static TourFragment newInstance(int sectionNumber) {
            TourFragment fragment = new TourFragment();
            Bundle args = new Bundle();
            args.putInt(LAYOUT_POSITION, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {

            mPosition = 0;
            if (getArguments() != null) {
                mPosition = getArguments().getInt(LAYOUT_POSITION);
            }

            View rootView = inflater.inflate(R.layout.howto, container, false);

            return rootView;

        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            TextView textDescription = (TextView)view.findViewById(R.id.text_description);
            TextView textTitle = (TextView)view.findViewById(R.id.text_title);

            textDescription.setText(getString(K.contants.howto_resources_description[mPosition]));
            textTitle.setText(getString(K.contants.howto_resources_title[mPosition]));

        }
    }

}
