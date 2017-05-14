package id.sch.smktelkom_mlg.privateassignment.xirpl227.dil;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl227.dil.adapter.SourceAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl227.dil.model.Result;
import id.sch.smktelkom_mlg.privateassignment.xirpl227.dil.model.SourcesResponse;
import id.sch.smktelkom_mlg.privateassignment.xirpl227.dil.service.GsonGetRequest;
import id.sch.smktelkom_mlg.privateassignment.xirpl227.dil.service.VolleySingleton;


/**
 * A simple {@link Fragment} subclass.
 */
public class NowFragment extends Fragment implements SourceAdapter.ISourceAdapter {
    ArrayList<Result> mList = new ArrayList<>();
    SourceAdapter mAdapter;


    public NowFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerView);
        StaggeredGridLayoutManager layoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new SourceAdapter(this.getActivity(), mList);
        recyclerView.setAdapter(mAdapter);

        downloadDataSources();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_now, container, false);
    }

    private void downloadDataSources() {
        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=019cee4207ef7c0cb9338ea35b2ccb62&language=en";

        GsonGetRequest<SourcesResponse> myRequest = new GsonGetRequest<SourcesResponse>
                (url, SourcesResponse.class, null, new Response.Listener<SourcesResponse>() {

                    @Override
                    public void onResponse(SourcesResponse response) {
                        Log.d("FLOW", "onResponse: " + (new Gson().toJson(response)));
                        mList.addAll(response.results);
                        mAdapter.notifyDataSetChanged();
                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("FLOW", "onErrorResponse: ", error);
                    }
                });
        VolleySingleton.getInstance(this.getActivity()).addToRequestQueue(myRequest);
    }

    @Override
    public void showArticles(String title, String overview, String poster_path) {

    }
}
