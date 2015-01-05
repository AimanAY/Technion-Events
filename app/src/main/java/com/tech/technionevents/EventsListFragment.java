package com.tech.technionevents;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.CalendarContract;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EventsListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EventsListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventsListFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    public static final String TAG="EVENTS";
    RecyclerView mRecyclerView;

    private ArrayDeque<Event> eventsQ;

    public static EventsListFragment newInstance() {
        EventsListFragment eLF = new EventsListFragment();

        return eLF;
    }

    public EventsListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       eventsQ= (ArrayDeque<Event>)getArguments().getSerializable("ARG_EVENTS");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

            View layout = inflater.inflate(R.layout.fragment_events_list, container, false);
            EventsAdapter adapter = new EventsAdapter(getActivity(), eventsQ.toArray(new Event[eventsQ.size()]));
            mRecyclerView = (RecyclerView) layout.findViewById(R.id.eventsList);
            mRecyclerView.setAdapter(adapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            return layout;


    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /*@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    public void changeEvents(List<Event> events){
        EventsAdapter adapter = new EventsAdapter(getActivity(), events.toArray(new Event[events.size()]));
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
