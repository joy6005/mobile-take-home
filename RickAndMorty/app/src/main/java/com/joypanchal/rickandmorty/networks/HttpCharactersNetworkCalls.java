package com.joypanchal.rickandmorty.networks;

import android.os.AsyncTask;
import android.util.Log;

import com.joypanchal.rickandmorty.callbacks.CharactersCallBackListener;
import com.joypanchal.rickandmorty.models.EpisodeCharacter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HttpCharactersNetworkCalls extends AsyncTask<String, Integer, List<EpisodeCharacter>> {

    private String url;

    private CharactersCallBackListener callBackListener;

    public HttpCharactersNetworkCalls(CharactersCallBackListener callBackListener, String url) {
        this.url = url;
        this.callBackListener = callBackListener;
    }

    @Override
    protected List<EpisodeCharacter> doInBackground(String... strings) {
        BufferedReader httpResponseReader = null;
        try {
            URL serverUrl = new URL(this.url);
            HttpURLConnection urlConnection = (HttpURLConnection) serverUrl.openConnection();
            urlConnection.setRequestMethod("GET");
            httpResponseReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String lineRead = httpResponseReader.readLine();

            if (lineRead != null || lineRead != "") {
                return parseJSONResponse(lineRead);

            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {

            if (httpResponseReader != null) {
                try {
                    httpResponseReader.close();
                } catch (IOException ioe) {
                    // Close quietly
                }
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<EpisodeCharacter> episodeCharacters) {
        super.onPostExecute(episodeCharacters);

        Log.d("oho", "onPostExecute");
        //recyclerView.setAdapter(new AllEpisodeAdapter(episodes, EpisodesListActivity.this));

        if (episodeCharacters != null) {
            callBackListener.onSuccess(episodeCharacters);
        } else {
            callBackListener.onError("API call failes");
        }
    }

    private List<EpisodeCharacter> parseJSONResponse(String data) {

        try {
            JSONArray jsonArray = new JSONArray(data);

            List<EpisodeCharacter> episodeCharacters = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                EpisodeCharacter mEpisodeCharacter = new EpisodeCharacter();
                mEpisodeCharacter.setId(jsonObject.getInt("id"));
                mEpisodeCharacter.setName(jsonObject.getString("name"));
                mEpisodeCharacter.setStatus(jsonObject.getString("status"));
                mEpisodeCharacter.setSpecies(jsonObject.getString("species"));
                mEpisodeCharacter.setType(jsonObject.getString("type"));
                mEpisodeCharacter.setGender(jsonObject.getString("gender"));
                mEpisodeCharacter.setImage(jsonObject.getString("image"));
                mEpisodeCharacter.setUrl(jsonObject.getString("url"));
                mEpisodeCharacter.setCreated(jsonObject.getString("created"));


                episodeCharacters.add(mEpisodeCharacter);
            }

            return episodeCharacters;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
