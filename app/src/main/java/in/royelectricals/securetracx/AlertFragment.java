package in.royelectricals.securetracx;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ReportFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ReportFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AlertFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener{
    String[] vechileist = {"Bus", "Truck", "Scorpio", "Fortuner", "Other"};
    EditText editTextStartDate, editTextStartTime, editTextEndDate, editTextEndTime;
    private int mYear, mMonth, mDay, mHour, mMinute;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AlertFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReportFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReportFragment newInstance(String param1, String param2) {
        ReportFragment fragment = new ReportFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Alert");
        View view =inflater.inflate(R.layout.fragment_alert, container, false);
        //Spinner==============================================
        Spinner spin = (Spinner) view.findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, vechileist);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

        //Date Time====================================================
        editTextStartDate = view.findViewById(R.id.startDate);
        editTextStartTime = view.findViewById(R.id.startTime);
        editTextEndDate = view.findViewById(R.id.endDate);
        editTextEndTime = view.findViewById(R.id.endTime);

        editTextStartDate.setOnClickListener(this);
        editTextStartTime.setOnClickListener(this);
        editTextEndDate.setOnClickListener(this);
        editTextEndTime.setOnClickListener(this);



        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

/*    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startDate:
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(this.getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                editTextStartDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
                break;
            case R.id.startTime:
                // Get Current Time
                final Calendar c2 = Calendar.getInstance();
                mHour = c2.get(Calendar.HOUR_OF_DAY);
                mMinute = c2.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(this.getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                editTextStartTime.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
                break;
            case R.id.endDate:
                // Get Current Date
                final Calendar c3 = Calendar.getInstance();
                mYear = c3.get(Calendar.YEAR);
                mMonth = c3.get(Calendar.MONTH);
                mDay = c3.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog2 = new DatePickerDialog(this.getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                editTextEndDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog2.show();
                break;
            case R.id.endTime:
                // Get Current Time
                final Calendar c4 = Calendar.getInstance();
                mHour = c4.get(Calendar.HOUR_OF_DAY);
                mMinute = c4.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog2 = new TimePickerDialog(this.getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                editTextEndTime.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog2.show();
                break;
            default:
                Toast.makeText(this.getActivity(), "Plse select date and time", Toast.LENGTH_SHORT);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
