package com.pixel.meirlen.orc.view.fragment;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.pixel.meirlen.orc.App;
import com.pixel.meirlen.orc.R;
import com.pixel.meirlen.orc.basket.BasketManager;
import com.pixel.meirlen.orc.helper.SessionManager;
import com.pixel.meirlen.orc.model.signup.ConfirmResponse;
import com.pixel.meirlen.orc.presenter.SignUpPresenter;
import com.pixel.meirlen.orc.view.SignUpView;
import com.pixel.meirlen.orc.view.activity.AboutActivity;
import com.pixel.meirlen.orc.view.activity.DecoderActivity;
import com.pixel.meirlen.orc.view.activity.SignInActivity;
import com.github.chuross.library.ExpandableLayout;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.pixel.meirlen.orc.helper.Constans.WHATSAPP_URL;


public class ProfileFragment extends Fragment implements SignUpView {

    private static final String TAG = "ProfileFragment";

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Inject
    SignUpPresenter presenter;


    @Inject
    SessionManager sessionManager;


    @BindView(R.id.textViewUserName)
    TextView textViewUserName;

    @BindView(R.id.textViewPhone)
    TextView textViewPhone;


    @BindViews({R.id.i1, R.id.i2, R.id.i3, R.id.i6, R.id.i7, R.id.i8, R.id.i9, R.id.i10, R.id.i16, R.id.i11, R.id.i12, R.id.i13})
    ImageView[] imageViews;
    @BindView(R.id.layout_expandable)
    ExpandableLayout expandableLayout;
    @BindView(R.id.ic_arrow_1)
    ImageView icArrow1;
    @BindView(R.id.layout_expandable2)
    ExpandableLayout expandableLayout2;
    @BindView(R.id.ic_arrow_2)
    ImageView icArrow2;

    @BindView(R.id.layout_expandable3)
    ExpandableLayout expandableLayout3;
    @BindView(R.id.ic_arrow_3)
    ImageView icArrow3;
    @BindView(R.id.layout_expandable4)
    ExpandableLayout expandableLayout4;
    @BindView(R.id.ic_arrow_4)
    ImageView icArrow4;

    private int[] SETTINGS_ICONS_MENU = {R.drawable.s1, R.drawable.s2, R.drawable.s3, R.drawable.s4,
            R.drawable.s5, R.drawable.s3, R.drawable.s7, R.drawable.s8, R.drawable.s9, R.drawable.s10, R.drawable.s11, R.drawable.s12};


    @Inject
    BasketManager basketManager;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    public static ProfileFragment newInstance(String param1) {
        ProfileFragment fragment = new ProfileFragment();
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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.settings_layout, container, false);
        ButterKnife.bind(this, rootView);
        App.getInstance().createSignUpComponent().inject(this);
        assert getArguments() != null;
        presenter.setView(this);

        for (int i = 0; i < imageViews.length; i++) {


            Glide
                    .with(getContext())
                    .load(SETTINGS_ICONS_MENU[i])
                    .into(imageViews[i]);


        }
        Log.d(TAG, "onCreateView: " + sessionManager.getAccessToken());
        presenter.getProfile(sessionManager.getAccessToken());
        return rootView;
    }


    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
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
    public void response(boolean state) {
    }

    @Override
    public void response(boolean state, ConfirmResponse response) {
        textViewPhone.setText(response.getPhone());
        textViewUserName.setText(response.getName());

    }

    @OnClick(R.id.layout_1)
    public void open_1() {
        if (expandableLayout.isExpanded()) {
            expandableLayout.collapse();
            icArrow1.animate().rotation(90).start();
        } else {
            expandableLayout.expand();
            icArrow1.animate().rotation(270).start();
        }
    }

    @OnClick(R.id.layout_2)
    public void open_2() {
        if (expandableLayout2.isExpanded()) {
            expandableLayout2.collapse();
            icArrow2.animate().rotation(90).start();
        } else {
            expandableLayout2.expand();
            icArrow2.animate().rotation(270).start();
        }
    }

    @OnClick(R.id.layout_3)
    public void open_3() {
        if (expandableLayout3.isExpanded()) {
            expandableLayout3.collapse();
            icArrow3.animate().rotation(90).start();
        } else {
            expandableLayout3.expand();
            icArrow3.animate().rotation(270).start();
        }
    }

    @OnClick(R.id.layout_4)
    public void open_4() {
        if (expandableLayout4.isExpanded()) {
            expandableLayout4.collapse();
            icArrow4.animate().rotation(90).start();
        } else {
            expandableLayout4.expand();
            icArrow4.animate().rotation(270).start();
        }
    }

    @OnClick(R.id.estimate)
    public void estimate() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id=com.umma.food.delivery"));
        startActivity(intent);

    }

    @OnClick(R.id.share)
    public void shareApp() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Приложение UmmaFood.САМОЕ БЫСТРОЕ И УДОБНОЕ  ПРИЛОЖЕНИЕ ПО ДОСТАВКЕ ХАЛАЛЬ ЕДЫ!https://play.google.com/store/apps/details?id=com.umma.food.delivery");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);

    }

    @OnClick(R.id.about)
    public void aboutApp() {
        Intent i = new Intent(getContext(), AboutActivity.class);
        i.putExtra("name", "О нас");
        startActivity(i);
        getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_righ);

    }

    @OnClick(R.id.conf)
    public void confApp() {
        Intent i = new Intent(getContext(), AboutActivity.class);
        i.putExtra("name", "Конфиденциа́льность");
        startActivity(i);
        getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_righ);

    }

    @OnClick(R.id.agree)
    public void agreeApp() {
        Intent i = new Intent(getContext(), AboutActivity.class);
        i.putExtra("name", "Соглашение пользователя");
        startActivity(i);
        getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_righ);

    }
    @OnClick(R.id.qr_code)
    public void qrApp() {
        Intent i = new Intent(getContext(), DecoderActivity.class);
        startActivity(i);
        getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_righ);

    }
    @OnClick(R.id.whatsapp)
    public void whatsapp() {
        Uri uri = Uri.parse(WHATSAPP_URL);
        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(Intent.createChooser(i, ""));

    }

    @OnClick(R.id.exit)
    public void exitApp() {
        String message = "Выйти из аккаунта?";
        String button1String = "Ok";
        String button2String = "Отменить";

        AlertDialog.Builder ad = new AlertDialog.Builder(Objects.requireNonNull(getContext()));
        ad.setMessage(message); // сообщение
        ad.setPositiveButton(button1String, (dialog, arg1) -> {

            sessionManager.setAccessToken(null);
            basketManager.disconnect();


            Intent i = new Intent(getContext(), SignInActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
            Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_righ);

        });
        ad.setNegativeButton(button2String, (dialog, arg1) -> {

        });

        ad.show();
    }


    @OnClick(R.id.call_centre)
    public void onCallCentre() {


        String message = "Вы хотите позвонить по этому номеру?";
        String button1String = "Ok";
        String button2String = "Отменить";

        AlertDialog.Builder ad = new AlertDialog.Builder(getContext());
        ad.setMessage(message); // сообщение
        ad.setPositiveButton(button1String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {

                int permissionCheck = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE);

                if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, 123);

                } else {
                    startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:" + "+" + "77752632438")));
                }
            }
        });
        ad.setNegativeButton(button2String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {

            }
        });

        ad.show();
    }


}
