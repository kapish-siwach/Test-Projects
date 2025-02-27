package com.first.smartstudent;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;

public class SignupFragment extends BottomSheetDialogFragment {

    MaterialCardView imageLayout;
    ImageView userImg, imgBottomBtn, closeDilog;
    TextView signUpBtn;
    TextInputLayout fullNameLayout, emailLayout, mabileLayout, passwordLayout, confmPasswordLayout;
    TextInputEditText nameInput, emailInput, mobileInput, passwordInput, confmPasswordInput;
    byte[] userImage;
    private UserDB userDB;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initValues(view);


        imageLayout.setOnClickListener(v->{
            Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            openCamera.launch(i);
        });
        closeDilog.setOnClickListener(v->{
            dismiss();
        });

        signUpBtn.setOnClickListener(v->{
            checkValidation();
        });

    }
    ActivityResultLauncher<Intent> openCamera=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result->{
        if (result.getResultCode()==RESULT_OK && result.getData()!=null){
            try {
                Bitmap bitmap=(Bitmap) result.getData().getExtras().get("data");
                if (bitmap!=null) {
                    userImg.setImageBitmap(bitmap);
                    userImage=convertToByte(bitmap);
                    imgBottomBtn.setImageResource(R.drawable.baseline_change_circle_24);
                }
                else
                    Toast.makeText(getActivity(), "Error Image Not Captured!", Toast.LENGTH_SHORT).show();
            }catch (Exception e){
                Log.d("AddingImageError", "Cann't Add the image: "+e.getMessage());
            }
        }
    });

    private byte[] convertToByte(Bitmap bitmap) {
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        return stream.toByteArray();
    }

    private void checkValidation() {
        SharedPreferences sp=getActivity().getSharedPreferences("login",0);
        SharedPreferences.Editor editor=sp.edit();
        if (nameInput.getText().toString().equalsIgnoreCase("")||emailInput.getText().toString().equalsIgnoreCase("")||mobileInput.getText().toString().equalsIgnoreCase("")||passwordInput.getText().toString().equalsIgnoreCase("")||confmPasswordInput.getText().toString().equalsIgnoreCase("")||userImage==null){
            Toast.makeText(getActivity(), "All fields are required!", Toast.LENGTH_SHORT).show();

        } else if (!passwordInput.getText().toString().equalsIgnoreCase(confmPasswordInput.getText().toString())) {
            confmPasswordLayout.setError("Password does not match");
        } else {
            userDB.addUser(nameInput.getText().toString(),emailInput.getText().toString(),mobileInput.getText().toString(),passwordInput.getText().toString(),confmPasswordInput.getText().toString(),userImage);
            editor.putBoolean("isLoggedIn",true);
            editor.putString("userEmail", emailInput.getText().toString());
            editor.putString("password", passwordInput.getText().toString());
            editor.apply();
            startActivity(new Intent(getActivity(),HomeActivity.class));
            Toast.makeText(getActivity(), "Registration Successful..", Toast.LENGTH_SHORT).show();
            clearFields();
        }

    }

    private void clearFields() {
        nameInput.setText("");
        emailInput.setText("");
        mobileInput.setText("");
        passwordInput.setText("");
        confmPasswordInput.setText("");
        userImg.setImageResource(R.drawable.focus);
        imgBottomBtn.setImageResource(R.drawable.baseline_add_24);
    }

    private void initValues(View view) {

        userDB=new UserDB(SignupFragment.this.getContext());
        signUpBtn = view.findViewById(R.id.signUpBtn2);
        imageLayout = view.findViewById(R.id.imagePlaceholder);
        userImg = view.findViewById(R.id.userImg);
        imgBottomBtn = view.findViewById(R.id.imgBottomBtn);
        closeDilog = view.findViewById(R.id.closeDilog);

        fullNameLayout = view.findViewById(R.id.fullNameLayout);
        emailLayout = view.findViewById(R.id.emailLayout);
        mabileLayout = view.findViewById(R.id.mabileLayout);
        passwordLayout = view.findViewById(R.id.passwordLayout);
        confmPasswordLayout = view.findViewById(R.id.confmPasswordLayout);

        nameInput = view.findViewById(R.id.nameInput);
        emailInput = view.findViewById(R.id.emailInput);
        mobileInput = view.findViewById(R.id.mobileInput);
        passwordInput = view.findViewById(R.id.passwordInput);
        confmPasswordInput = view.findViewById(R.id.confmPasswordInput);
    }

}