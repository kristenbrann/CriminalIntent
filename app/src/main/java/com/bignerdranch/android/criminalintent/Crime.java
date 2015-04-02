package com.bignerdranch.android.criminalintent;

import android.util.Log;

import java.util.Date;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Crime {

    private static final String JSON_ID = "id";
    private static final String JSON_TITLE = "title";
    private static final String JSON_DATE = "date";
    private static final String JSON_SOLVED = "solved";
    private static final String JSON_PHOTOS = "photos";

    private static final int NUM_PHOTOS = 4;
    
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;
    private Photo[] mPhotos;
    
    public Crime() {
        mId = UUID.randomUUID();
        mDate = new Date();
        mPhotos = new Photo[NUM_PHOTOS];
    }

    public Crime(JSONObject json) throws JSONException {
        mId = UUID.fromString(json.getString(JSON_ID));
        mTitle = json.optString(JSON_TITLE, null);
        mSolved = json.getBoolean(JSON_SOLVED);
        mDate = new Date(json.getLong(JSON_DATE));

        mPhotos = new Photo[NUM_PHOTOS];
        JSONArray photosArray = json.getJSONArray(JSON_PHOTOS);
        for (int i=0; i<photosArray.length(); i++) {
            mPhotos[i] = new Photo(photosArray.getJSONObject(i));
        }
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_ID, mId.toString());
        json.put(JSON_TITLE, mTitle);
        json.put(JSON_SOLVED, mSolved);
        json.put(JSON_DATE, mDate.getTime());

        JSONArray photos  = new JSONArray();
        for (int i=0; i<NUM_PHOTOS; i++){
            if (mPhotos[i] != null){
                photos.put(mPhotos[i].toJSON());
            }
        }
        json.put(JSON_PHOTOS, photos);

        return json;
    }

    public void addPhoto(Photo photo){
        int i = 0;
        while(mPhotos[i] != null && (i+1)<NUM_PHOTOS)
            i++;
        mPhotos[i] = photo;
        Log.d("CRIME", "Photo saved, there are now "+i+" photos.");
    }

    @Override
    public String toString() {
        return mTitle;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public UUID getId() {
        return mId;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

	public Photo getPhoto() {
		return mPhotos[0];
	}

	public void setPhoto(Photo photo) {
		mPhotos[0] = photo;
	}

    public Photo[] getPhotos() {
        return mPhotos;
    }

    public void setPhotos(Photo[] photos) {
        mPhotos = mPhotos;
    }


}
