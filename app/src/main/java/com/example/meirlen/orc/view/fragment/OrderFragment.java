package com.example.meirlen.orc.view.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meirlen.orc.App;
import com.example.meirlen.orc.R;
import com.example.meirlen.orc.helper.SessionManager;
import com.example.meirlen.orc.presenter.OrderPresenter;
import com.example.meirlen.orc.view.OrderView;
import com.example.meirlen.orc.view.activity.ModalActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class OrderFragment extends Fragment implements OrderView {


    public static final String EXTRA_TOTAL = "extra:total";
    public static final String EXTRA_TOTAL_SIZE = "extra:total_size";


    //Location
    private static final int REQUEST_PERMISSIONS_LOCATION_SETTINGS_REQUEST_CODE = 33;
    private static final int REQUEST_PERMISSIONS_LAST_LOCATION_REQUEST_CODE = 34;
    private static final int REQUEST_PERMISSIONS_CURRENT_LOCATION_REQUEST_CODE = 35;
    @BindView(R.id.main_layout)
    RelativeLayout mainLayout;
    @BindView(R.id.checkBox)
    CheckBox checkBox;

    private FusedLocationProviderClient mFusedLocationClient;

    protected static long MIN_UPDATE_INTERVAL = 30 * 1000; // 1  minute is the minimum Android recommends, but we use 30 seconds

    protected Location mLastLocation;

    LocationRequest locationRequest;

    Location currentLocation = null;


    @BindView(R.id.progressBar)
    ProgressBar progressBar;


    @Inject
    OrderPresenter presenter;


    @Inject
    SessionManager sessionManager;


    @BindView(R.id.loading_bar)
    RelativeLayout progressLayout;

    @BindView(R.id.create)
    RelativeLayout createLayout;


    @BindView(R.id.line1)
    LinearLayout contentLayout;

    private static final String TAG = "BasketFragment";
    @BindView(R.id.e1)
    EditText e1;
    @BindView(R.id.e2)
    EditText e2;
    @BindView(R.id.e5)
    EditText e5;
    @BindView(R.id.e6)
    EditText e6;
    @BindView(R.id.e7)
    EditText e7;
    @BindView(R.id.e8)
    EditText e8;
    @BindView(R.id.edName)
    EditText edName;
    @BindView(R.id.edNumber)
    EditText edNumber;
    @BindView(R.id.txt_total_price)
    TextView txtTotalPrice;
    @BindView(R.id.txt_total_count)
    TextView txt_total_count;
    @BindView(R.id.txt_total_delivery)
    TextView txtTotalDelivery;
    View rootView;

    public static OrderFragment newInstance() {
        return new OrderFragment();
    }

    public static OrderFragment newInstance(String param1, String param2) {
        OrderFragment fragment = new OrderFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_order, container, false);
        ButterKnife.bind(this, rootView);
        txtTotalPrice.setText(Objects.requireNonNull(getActivity()).getIntent().getStringExtra(EXTRA_TOTAL));
        txt_total_count.setText(Objects.requireNonNull(getActivity()).getIntent().getStringExtra(EXTRA_TOTAL_SIZE) + " товаров на сумму :");
        App.getInstance().createOrderComponent().inject(this);
        Log.d(TAG, "onCreateView: " + sessionManager.getAccessToken());
        presenter.setView(this);
        initLocalDate();


        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());

        checkForLocationRequest();
        checkForLocationSettings();
        callLastKnownLocation();

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int result = googleApiAvailability.isGooglePlayServicesAvailable(getActivity());

        if (result != ConnectionResult.SUCCESS && result != ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED) {
            Toast.makeText(getActivity(), "Are you running in Emulator ? try a real device.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showLoading() {
        mainLayout.setAlpha((float) 0.7);
        progressLayout.setVisibility(View.VISIBLE);
        createLayout.setVisibility(View.GONE);
        contentLayout.setAlpha((float) 0.5);
        Objects.requireNonNull(getActivity()).getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    @Override
    public void hideLoading() {
        progressLayout.setVisibility(View.GONE);
        createLayout.setVisibility(View.VISIBLE);
        contentLayout.setAlpha((float) 1.0);
        mainLayout.setAlpha((float) 1.0);
        Objects.requireNonNull(getActivity()).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    @Override
    public void loadingFailed(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(Throwable throwable) {
        Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void isSussess(boolean success) {
        if (success) {
            Intent intent = new Intent(getContext(), ModalActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    @OnClick(R.id.create)
    public void addOrder() {
        if(checkBox.isChecked()){
            if (e1.getText().length() == 0 || e2.getText().length() == 0 || edNumber.getText().length() == 0) {
                Toast.makeText(getContext(), "Заполните обязательные поля", Toast.LENGTH_SHORT).show();
            } else {
                sessionManager.setStreet(e1.getText().toString());
                sessionManager.setHouse(e2.getText().toString());
                sessionManager.setFlat(e5.getText().toString());
                sessionManager.setFloor(e6.getText().toString());
                sessionManager.setEntrance(e7.getText().toString());
                sessionManager.setUserName(edName.getText().toString().trim());
                sessionManager.setPhone(edNumber.getText().toString().trim());
                Log.d(TAG, "addOrder: " + sessionManager.getLat() + sessionManager.getLot());
                presenter.sendOrder(sessionManager.getAccessToken(), sessionManager.getLat(), sessionManager.getLot(),e1.getText().toString()+" "+e2.getText().toString());
            }
        }
        else {
            Toast.makeText(getActivity(), "Вы должны принять наше пользовательское сообщени", Toast.LENGTH_SHORT).show();
        }

    }


    private void initLocalDate() {

        edNumber.setText(sessionManager.getPhone());
        edName.setText(sessionManager.getUserName());
        e1.setText(sessionManager.getStreet());
        e2.setText(sessionManager.getHouse());
        e5.setText(sessionManager.getFlat());
        e6.setText(sessionManager.getFloor());
        e7.setText(sessionManager.getEntrance());

    }

    @OnClick(R.id.myLocationButton)
    public void callLastKnownLocation() {
        try {
            if (
                    ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                            ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                requestPermissions(REQUEST_PERMISSIONS_LAST_LOCATION_REQUEST_CODE);
                return;
            }

            getLastLocation();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void callCurrentLocation(View view) {
        try {
            if (
                    ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                            ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    ) {
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                requestPermissions(REQUEST_PERMISSIONS_CURRENT_LOCATION_REQUEST_CODE);
                return;
            }

            mFusedLocationClient.requestLocationUpdates(locationRequest, new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {

                    currentLocation = (Location) locationResult.getLastLocation();

                    String result = "Current Location Latitude is " +
                            currentLocation.getLatitude() + "\n" +
                            "Current location Longitude is " + currentLocation.getLongitude();

                    Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
                }
            }, Looper.myLooper());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @SuppressWarnings("MissingPermission")
    private void getLastLocation() {

        mFusedLocationClient.getLastLocation()
                .addOnCompleteListener(getActivity(), new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            mLastLocation = task.getResult();

                            sessionManager.setLat(String.valueOf(mLastLocation.getLatitude()));
                            sessionManager.setLot(String.valueOf(mLastLocation.getLongitude()));


                            getCompleteAddressString(mLastLocation.getLatitude(), mLastLocation.getLongitude());


                        } else {
                            showSnackbar("No Last known location found. Try current location..!");
                        }
                    }
                });
    }

    private void showSnackbar(final String text) {
        View container = rootView.findViewById(R.id.container);
        if (container != null) {
            Snackbar.make(container, text, Snackbar.LENGTH_LONG).show();
        }
    }


    private void showSnackbar(final String mainTextString, final String actionString,
                              View.OnClickListener listener) {
        Snackbar.make(rootView.findViewById(android.R.id.content),
                mainTextString,
                Snackbar.LENGTH_INDEFINITE)
                .setAction(actionString, listener).show();
    }

    private void startLocationPermissionRequest(int requestCode) {
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, requestCode);
    }

    private void requestPermissions(final int requestCode) {
        boolean shouldProvideRationale = ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION);

        // Provide an additional rationale to the user. getActivity() would happen if the user denied the
        // request previously, but didn't check the "Don't ask again" checkbox.
        if (shouldProvideRationale) {
            showSnackbar("Permission is must to find the location", "Ok",
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Request permission
                            startLocationPermissionRequest(requestCode);
                        }
                    });

        } else {
            startLocationPermissionRequest(requestCode);
        }
    }

    public void checkForLocationRequest() {
        locationRequest = LocationRequest.create();
        locationRequest.setInterval(MIN_UPDATE_INTERVAL);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
    }

    //Check for location settings.
    public void checkForLocationSettings() {
        try {
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
            builder.addLocationRequest(locationRequest);
            SettingsClient settingsClient = LocationServices.getSettingsClient(getActivity());

            settingsClient.checkLocationSettings(builder.build())
                    .addOnSuccessListener(getActivity(), new OnSuccessListener<LocationSettingsResponse>() {
                        @Override
                        public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                            //Setting is success...
                            //Toast.makeText(getActivity(), "Enabled the Location successfully. Now you can press the buttons..", Toast.LENGTH_SHORT).show();
                            getLastLocation();

                        }
                    })
                    .addOnFailureListener(getActivity(), new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {


                            int statusCode = ((ApiException) e).getStatusCode();
                            switch (statusCode) {
                                case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                                    try {
                                        // Show the dialog by calling startResolutionForResult(), and check the
                                        // result in onActivityResult().
                                        ResolvableApiException rae = (ResolvableApiException) e;
                                        rae.startResolutionForResult(getActivity(), REQUEST_PERMISSIONS_LOCATION_SETTINGS_REQUEST_CODE);
                                    } catch (IntentSender.SendIntentException sie) {
                                        sie.printStackTrace();
                                    }
                                    break;
                                case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                    Toast.makeText(getActivity(), "Setting change is not available.Try in another device.", Toast.LENGTH_LONG).show();
                            }

                        }
                    });

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSIONS_LAST_LOCATION_REQUEST_CODE) {
            if (grantResults.length <= 0) {
                // If user interaction was interrupted, the permission request is cancelled and you
                // receive empty arrays.
                Log.i(TAG, "User interaction was cancelled.");
            } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted.
                getLastLocation();
            }
        }

        if (requestCode == REQUEST_PERMISSIONS_CURRENT_LOCATION_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callCurrentLocation(null);
            }
        }
    }

    private void getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                Log.d(TAG, "getCompleteAddressString: " + returnedAddress);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i));
                }

                Log.w("My Current address", strReturnedAddress.toString());
                e1.setText(strReturnedAddress.toString());
            } else {
                Log.w("My Current address", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("My Current address", "Canont get Address!");
        }
//        return strAdd;
    }



}
