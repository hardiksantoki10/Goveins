package com.goviens.android.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.goviens.android.R;
import com.goviens.android.databinding.ActivityReceiptBinding;
import com.goviens.android.databinding.ActivitySignUpBinding;
import com.goviens.android.models.ModelReceiptDriver;
import com.goviens.android.models.ModelReceiptUser;
import com.goviens.android.utils.SingletonGson;
import com.mindorks.placeholderview.PlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;



public class ReceiptActivity extends AppCompatActivity {

    PlaceHolderView placeHolderView;
    String user_receipt;
    ModelReceiptDriver modelReceiptDriver;
    ModelReceiptUser modelReceiptUser;

    ActivityReceiptBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReceiptBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        user_receipt = getIntent().getStringExtra("user_receipt");
        if(user_receipt != null) {
            if (user_receipt.equals("0")) {
                modelReceiptDriver = SingletonGson.getInstance().fromJson(getIntent().getStringExtra("script"), ModelReceiptDriver.class);
                binding.tvReceipt.setText(modelReceiptDriver.getData().getDriver_Receipt().getHeader().getCenter_text());
                for (int i = 0; i < modelReceiptDriver.getData().getDriver_Receipt().getBody().getRide_details().size(); i++) {
                    placeHolderView.addView(new HolderReceipt(modelReceiptDriver.getData().getDriver_Receipt().getBody().getRide_details().get(i)));
                }
                for (int i = 0; i < modelReceiptDriver.getData().getDriver_Receipt().getBody().getBill_details().size(); i++) {
                    placeHolderView.addView(new HolderReceiptBill(modelReceiptDriver.getData().getDriver_Receipt().getBody().getBill_details().get(i)));
                }
                placeHolderView.addView(new HolderFooter(modelReceiptDriver.getData().getDriver_Receipt().getFooter()));
            } else {
                modelReceiptUser = SingletonGson.getInstance().fromJson(getIntent().getStringExtra("script"), ModelReceiptUser.class);
                binding.tvReceipt.setText(modelReceiptUser.getData().getUser_Receipt().getHeader().getCenter_text());
                for (int i = 0; i < modelReceiptUser.getData().getUser_Receipt().getBody().getRide_details().size(); i++) {
                    placeHolderView.addView(new HolderUserReceipt(modelReceiptUser.getData().getUser_Receipt().getBody().getRide_details().get(i)));
                }
                for (int i = 0; i < modelReceiptUser.getData().getUser_Receipt().getBody().getBill_details().size(); i++) {
                    placeHolderView.addView(new HolderUserReceiptBill(modelReceiptUser.getData().getUser_Receipt().getBody().getBill_details().get(i)));
                }
                placeHolderView.addView(new HolderUserFooter(modelReceiptUser.getData().getUser_Receipt().getFooter()));
            }
        }
        binding.lldoneReceipt.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                finish();
            }
        });
    }

    @Layout(R.layout.raw_receipt)
    class HolderReceipt{
        @View(R.id.tv_left_text)
        TextView tvLeftText;
        @View(R.id.tv_right_text)
        TextView tvRightText;

        ModelReceiptDriver.DataBean.DriverReceiptBean.BodyBean.RideDetailsBean rideDetailsBean ;

        public HolderReceipt(ModelReceiptDriver.DataBean.DriverReceiptBean.BodyBean.RideDetailsBean rideDetailsBean) {
            this.rideDetailsBean = rideDetailsBean;
        }


        @Resolve
        void setData(){
            try{
                tvLeftText.setText(""+rideDetailsBean.getLeft_text());
                tvRightText.setText(""+rideDetailsBean.getRight_text());
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(ReceiptActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Layout(R.layout.raw_receipt)
    class HolderReceiptBill{
        @View(R.id.tv_left_text)
        TextView tvLeftText;
        @View(R.id.tv_right_text)
        TextView tvRightText;

        ModelReceiptDriver.DataBean.DriverReceiptBean.BodyBean.BillDetailsBean billDetailsBean;

        public HolderReceiptBill(ModelReceiptDriver.DataBean.DriverReceiptBean.BodyBean.BillDetailsBean billDetailsBean) {
            this.billDetailsBean = billDetailsBean;
        }


        @Resolve
        void setData(){
            try{
                tvLeftText.setText(""+billDetailsBean.getLeft_text());
                tvRightText.setText(""+billDetailsBean.getRight_text());
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(ReceiptActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Layout(R.layout.raw_receipt)
    class HolderFooter{
        @com.mindorks.placeholderview.annotations.View(R.id.tv_left_text)
        TextView tvLeftText;
        @com.mindorks.placeholderview.annotations.View(R.id.tv_right_text)
        TextView tvRightText;

        ModelReceiptDriver.DataBean.DriverReceiptBean.FooterBean footer;

        public HolderFooter(ModelReceiptDriver.DataBean.DriverReceiptBean.FooterBean footer) {
            this.footer = footer;
        }


        @Resolve
        void setData(){
            try{
                tvLeftText.setText(""+footer.getLeft_text());
                tvRightText.setText(""+footer.getRight_text());
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(ReceiptActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Layout(R.layout.raw_receipt)
    class HolderUserReceipt{
        @View(R.id.tv_left_text)
        TextView tvLeftText;
        @View(R.id.tv_right_text)
        TextView tvRightText;

        ModelReceiptUser.DataBean.UserReceiptBean.BodyBean.RideDetailsBean rideDetailsBean;

        public HolderUserReceipt(ModelReceiptUser.DataBean.UserReceiptBean.BodyBean.RideDetailsBean rideDetailsBean) {
            this.rideDetailsBean = rideDetailsBean;
        }

        @Resolve
        void setData(){
            try{
                tvLeftText.setText(""+rideDetailsBean.getLeft_text());
                tvRightText.setText(""+rideDetailsBean.getRight_text());
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(ReceiptActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Layout(R.layout.raw_receipt)
    class HolderUserReceiptBill{
        @View(R.id.tv_left_text)
        TextView tvLeftText;
        @View(R.id.tv_right_text)
        TextView tvRightText;

        ModelReceiptUser.DataBean.UserReceiptBean.BodyBean.BillDetailsBean billDetailsBean;

        public HolderUserReceiptBill(ModelReceiptUser.DataBean.UserReceiptBean.BodyBean.BillDetailsBean billDetailsBean) {
            this.billDetailsBean = billDetailsBean;
        }


        @Resolve
        void setData(){
            try{
                tvLeftText.setText(""+billDetailsBean.getLeft_text());
                tvRightText.setText(""+billDetailsBean.getRight_text());
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(ReceiptActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Layout(R.layout.raw_receipt)
    class HolderUserFooter{
        @com.mindorks.placeholderview.annotations.View(R.id.tv_left_text)
        TextView tvLeftText;
        @com.mindorks.placeholderview.annotations.View(R.id.tv_right_text)
        TextView tvRightText;

        ModelReceiptUser.DataBean.UserReceiptBean.FooterBean footer;
        public HolderUserFooter(ModelReceiptUser.DataBean.UserReceiptBean.FooterBean footer) {
            this.footer = footer;
        }


        @Resolve
        void setData(){
            try{
                tvLeftText.setText(""+footer.getLeft_text());
                tvRightText.setText(""+footer.getRight_text());
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(ReceiptActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        }
    }
}