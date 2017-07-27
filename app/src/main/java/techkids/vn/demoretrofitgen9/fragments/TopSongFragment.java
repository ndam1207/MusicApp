package techkids.vn.demoretrofitgen9.fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import techkids.vn.demoretrofitgen9.R;
import techkids.vn.demoretrofitgen9.adapters.TopSongAdapter;
import techkids.vn.demoretrofitgen9.databases.MusicTypeModel;
import techkids.vn.demoretrofitgen9.databases.TopSongModel;
import techkids.vn.demoretrofitgen9.events.OnClickMusicType;
import techkids.vn.demoretrofitgen9.managers.MusicManager;
import techkids.vn.demoretrofitgen9.networks.GetTopSongsService;
import techkids.vn.demoretrofitgen9.networks.RetrofitFactory;
import techkids.vn.demoretrofitgen9.networks.json_models.top_songs.FeedJSON;
import techkids.vn.demoretrofitgen9.networks.json_models.top_songs.ObjectEntryJSON;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopSongFragment extends Fragment implements View.OnClickListener {
    private List<TopSongModel> topSongModelList = new ArrayList<>();
    private MusicTypeModel musicTypeModel;
    private TopSongAdapter topSongAdapter;
    private SeekBar sbMiniPlayer;
    private FloatingActionButton fbMini;


    @BindView(R.id.tv_music_type)
    TextView tvMusicType;
    @BindView(R.id.iv_music_type)
    ImageView ivMusicType;
    @BindView(R.id.rv_top_song)
    RecyclerView rvTopSong;

    public TopSongFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_song, container, false);

        setupUI(view);
        loadData();

        return view;
    }

    private void loadData() {
        GetTopSongsService getTopSongsService = RetrofitFactory.getInstance().create(GetTopSongsService.class);
        getTopSongsService.getTopSongs(musicTypeModel.getId()).enqueue(new Callback<FeedJSON>() {
            @Override
            public void onResponse(Call<FeedJSON> call, Response<FeedJSON> response) {
                FeedJSON feedJSON = response.body();
                for (ObjectEntryJSON objectEntryJSON : feedJSON.getListEntryJSON().getObjectEntryJSONs()) {
                    TopSongModel topSongModel = new TopSongModel();

                    topSongModel.setImage(objectEntryJSON.getJsonImageList().get(2).getJsonLabelImage());
                    topSongModel.setSingerName(objectEntryJSON.getJsonArtist().getJsonNameArtist());
                    topSongModel.setSongName(objectEntryJSON.getJsonName().getLabelName());

                    topSongModelList.add(topSongModel);
                }
                topSongAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<FeedJSON> call, Throwable t) {
                Toast.makeText(getContext(), "No connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupUI(View view) {
        EventBus.getDefault().register(this);
        ButterKnife.bind(this, view);

        tvMusicType.setText(musicTypeModel.getKey());
        ivMusicType.setImageResource(musicTypeModel.getImageID());

        topSongAdapter = new TopSongAdapter(getContext(), topSongModelList);
        rvTopSong.setAdapter(topSongAdapter);

        rvTopSong.setLayoutManager(new LinearLayoutManager(getContext()));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                getContext(), DividerItemDecoration.VERTICAL);
        rvTopSong.addItemDecoration(dividerItemDecoration);

        topSongAdapter.setOnItemClick(this);

    }

    @Subscribe(sticky = true)
    public void onReceivedMusicType(OnClickMusicType onClickMusicType) {
        musicTypeModel = onClickMusicType.getMusicTypeModel();
    }

    @Override
    public void onClick(View v) {
        TopSongModel topSongModel = (TopSongModel) v.getTag();
        miniPlayerSetUp(topSongModel);
        MusicManager.loadSearchSong(topSongModel, getContext(), sbMiniPlayer, fbMini);

    }

    @Subscribe
    private void miniPlayerSetUp(TopSongModel topSongModel) {
        RelativeLayout rlMiniPlayer = (RelativeLayout) getActivity().findViewById(R.id.rl_mini_player);
        rlMiniPlayer.setVisibility(View.VISIBLE);

        ImageView ivSongImage = (ImageView) getActivity().findViewById(R.id.iv_song_image);
        FloatingActionButton fbMini = (FloatingActionButton) getActivity().findViewById(R.id.fb_mini);
        sbMiniPlayer = (SeekBar) getActivity().findViewById(R.id.sb_mini_player);
        fbMini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MusicManager.playPause();
            }
        });

        TextView tvSongTitle = (TextView) getActivity().findViewById(R.id.tv_song_title);
        TextView tvSongAuthor = (TextView) getActivity().findViewById(R.id.tv_song_author);

        tvSongTitle.setText(topSongModel.getSongName());
        tvSongAuthor.setText(topSongModel.getSingerName());
        Picasso.with(getContext()).load(topSongModel.getImage()).into(ivSongImage);

    }
}
