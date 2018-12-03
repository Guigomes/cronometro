package br.com.ggsoftware.controletempo;

import android.os.SystemClock;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

         long lastPauseSentado = 0;
        long lastPauseEmPe = 0;
        long lastPauseFora = 0;

        long totalPauseSentado = 0;
        long totalPauseEmPe = 0;
        long totalPauseFora = 0;


        Chronometer chronometerSentado;
        Chronometer chronometerEmPe;
        Chronometer chronometerFora;


        boolean chronometerSentadoLigado = false;
        boolean chronometerEmPeLigado = false;
        boolean chronometerForaLigado = false;
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            chronometerSentado = ((Chronometer) rootView.findViewById(R.id.chronometer_sentado));

            chronometerEmPe = ((Chronometer) rootView.findViewById(R.id.chronometer_em_pe));

            chronometerFora = ((Chronometer) rootView.findViewById(R.id.chronometer_fora));

            Button startButtonSentado = rootView.findViewById(R.id.start_button_sentado);
            Button startButtonEmPe = rootView.findViewById(R.id.start_button_em_pe);
            Button startButtonFora = rootView.findViewById(R.id.start_button_fora);


            Button FimButton = rootView.findViewById(R.id.fim_button);


            FimButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(chronometerSentadoLigado) {
                        lastPauseSentado = stop(chronometerSentado);
                        totalPauseSentado += (lastPauseSentado - chronometerSentado.getBase());

                    }

                    if(chronometerEmPeLigado) {
                        lastPauseEmPe = stop(chronometerEmPe);
                        totalPauseEmPe += (lastPauseEmPe - chronometerEmPe.getBase());

                    }

                    if(chronometerForaLigado) {

                        lastPauseFora = stop(chronometerFora);


                        totalPauseFora += (lastPauseFora - chronometerFora.getBase());
                    }




                    Log.i("TESTE",chronometerSentado.getText().toString());
                    Log.i("TESTE",chronometerEmPe.getText().toString());
                    Log.i("TESTE",chronometerFora.getText().toString());


                }
            });



            startButtonSentado.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                start(chronometerSentado, lastPauseSentado);

                chronometerSentadoLigado = true;

                if(chronometerEmPeLigado) {
                    lastPauseEmPe = stop(chronometerEmPe);
                    totalPauseEmPe += (lastPauseEmPe - chronometerEmPe.getBase());


                    chronometerEmPeLigado = false;

                }
                if(chronometerForaLigado) {
                    lastPauseFora = stop(chronometerFora);
                    chronometerForaLigado = false;
                    totalPauseFora += (lastPauseFora - chronometerFora.getBase());

                }

                }
            });


            startButtonEmPe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    start(chronometerEmPe, lastPauseEmPe);
                    chronometerEmPeLigado = true;
                    if(chronometerSentadoLigado) {
                        totalPauseSentado += (lastPauseSentado - chronometerSentado.getBase());

                        lastPauseSentado = stop(chronometerSentado);
                        chronometerSentadoLigado = false;
                    }
                    if(chronometerForaLigado) {

                        totalPauseFora += (lastPauseFora - chronometerFora.getBase());
                        lastPauseFora = stop(chronometerFora);
                        chronometerForaLigado = false;
                    }
                }
            });



            startButtonFora.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    start(chronometerFora, lastPauseFora);
                    chronometerForaLigado = true;
                    if(chronometerSentadoLigado) {
                        totalPauseSentado += (lastPauseSentado - chronometerSentado.getBase());

                        lastPauseSentado = stop(chronometerSentado);
                        chronometerSentadoLigado = false;
                    }
                    if(chronometerEmPeLigado) {
                        totalPauseEmPe += (lastPauseEmPe - chronometerEmPe.getBase());
                        lastPauseEmPe = stop(chronometerEmPe);
                        chronometerEmPeLigado = false;
                    }

                }
            });


            return rootView;
        }

        public void start(Chronometer chronometer, long lastPause){

            if(lastPause > 0) {
                chronometer.setBase(chronometer.getBase() + SystemClock.elapsedRealtime() - lastPause);
            }else{
                chronometer.setBase(SystemClock.elapsedRealtime());

            }

            chronometer.start();
        }

        public long stop(Chronometer chronometer){
            long lastPause = SystemClock.elapsedRealtime();
            chronometer.stop();
            return lastPause;
        }

        public void reset(Chronometer chronometer){
            chronometer.setBase(SystemClock.elapsedRealtime());

        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }
    }


}
