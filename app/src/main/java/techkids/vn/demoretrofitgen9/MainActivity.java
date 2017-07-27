package techkids.vn.demoretrofitgen9;

import android.graphics.PorterDuff;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import org.greenrobot.eventbus.Subscribe;

import techkids.vn.demoretrofitgen9.adapters.PagerAdapter;

public class MainActivity extends AppCompatActivity {
    private Toolbar tbMain;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private SeekBar sbMiniPlayer;
    RelativeLayout rlMiniPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUI();
    }

    private void setupUI() {
        tbMain = (Toolbar) findViewById(R.id.tb_main);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        sbMiniPlayer = (SeekBar) findViewById(R.id.sb_mini_player);
        rlMiniPlayer = (RelativeLayout) findViewById(R.id.rl_mini_player);

        tbMain.setTitle("Free Music");
        tbMain.setTitleTextColor(getResources().getColor(R.color.primary_text));

        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_dashboard_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_favorite_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_get_app_black_24dp));

        tabLayout.getTabAt(0).getIcon().setColorFilter(
                (getResources().getColor(R.color.icon_selected)), PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(1).getIcon().setColorFilter(
                (getResources().getColor(R.color.icon_unselected)), PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(2).getIcon().setColorFilter(
                (getResources().getColor(R.color.icon_unselected)), PorterDuff.Mode.SRC_IN);

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                tab.getIcon().setColorFilter((getResources().getColor(R.color.icon_selected)),
                        PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter((getResources().getColor(R.color.icon_unselected)),
                        PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        sbMiniPlayer.setPadding(0,0,0,0);

    }
}
