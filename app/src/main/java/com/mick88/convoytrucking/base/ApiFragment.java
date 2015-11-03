package com.mick88.convoytrucking.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mick88.convoytrucking.R;
import com.mick88.convoytrucking.api.ApiConstants;
import com.mick88.convoytrucking.api.ModelRequest;

/**
 * Created by Michal on 03/11/2015.
 * <p/>
 * A helper fragment with boilerplate code to making requests
 */
public abstract class ApiFragment<T> extends BaseFragment implements Response.Listener<T> {

    private ModelRequest<T> currentRequest;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sendRequest();
    }

    protected abstract Class<T> getModelClass();

    protected void sendRequest() {
        currentRequest = new ModelRequest<>(ApiConstants.API_CHAT, getModelClass(), this, this);
        sendRequest(currentRequest);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onResponse(T response) {
        currentRequest = null;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        super.onErrorResponse(error);
        currentRequest = null;
    }

    /**
     * Tells whether a request has been sent
     */
    public boolean isRequestPending() {
        return currentRequest != null;
    }
}
