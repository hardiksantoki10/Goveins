package com.goviens.android.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.goviens.android.R;
import com.goviens.android.baseClass.BaseActivity;
import com.goviens.android.databinding.ActivityAddCarBinding;
import com.goviens.android.databinding.ActivityCardListBinding;
import com.goviens.android.models.ModelAddMoney;
import com.goviens.android.models.ModelViewCards;
import com.goviens.android.utils.API_S;
import com.goviens.android.utils.ApiManager;
import com.goviens.android.utils.ApporioLog;
import com.goviens.android.utils.SessionManager;
import com.goviens.android.utils.SingletonGson;
import com.mindorks.placeholderview.PlaceHolderView;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

import java.util.HashMap;



public class CardListActivity extends BaseActivity implements ApiManager.APIFETCHER {

    private ActivityCardListBinding mBinding;
    PlaceHolderView placeholder;

    SessionManager sessionManager;
    ApiManager apiManagerNew;


    private final String TAG = "CardListActivity";
    private ProgressDialog progressDialog;
    private String AMOUNT = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivityCardListBinding.inflate(getLayoutInflater());
        android.view.View view = mBinding.getRoot();          //Root xml or viewGroup will be a part of converted view over here
        setContentView(view);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);
        sessionManager = new SessionManager(this);
        apiManagerNew = new ApiManager(this, this);

        try {
            AMOUNT = "" + getIntent().getExtras().getString("amount" );
        } catch (Exception e) {

        }

        mBinding.imgBack.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                finish();
            }
        });

        mBinding.swiperefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    callAPI();
                } catch (Exception e) {
                }
            }
        });


        mBinding.addNew.setOnClickListener(views -> {
            startActivity(new Intent(CardListActivity.this, EnterCardDetailsActivity.class));


//                ApporioLog.logI(TAG, "Now GPS Status = " + false + ", Now Showing Dialog");
//                AlertDialog.Builder builder = new AlertDialog.Builder(CardListActivity.this);
//                builder.setCancelable(false);
//                builder.setTitle("Hello User");
//                builder.setMessage("Add card module can be integrated with different payment gateways, for the sake of demo we have added a demo card end with ** **** **** 1234, you can user this card to top up your wallet")
//                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                dialog.dismiss();
//                            }
//                        });
//                builder.create().show();
        });


    }


    private void callAPI() throws Exception {
        HashMap<String, String> data = new HashMap<>();
        data.put("payment_option", "STRIPE");
        apiManagerNew._post(API_S.Tags.VIEW_CARDS, API_S.Endpoints.VIEW_CARDS, data, sessionManager);
        placeholder.removeAllViews();
    }

    @Override
    public void onAPIRunningState(int a, String APINAME) {
        switch (APINAME) {
            case API_S.Tags.ADD_MONEY_IN_WALLET:
                if (a == ApiManager.APIFETCHER.KEY_API_IS_STARTED) {
                    progressDialog.show();
                } else if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                break;
            case API_S.Tags.VIEW_CARDS:
                if (a == ApiManager.APIFETCHER.KEY_API_IS_STARTED) {
                    mBinding.swiperefreshLayout.setRefreshing(true);
                } else {
                    mBinding.swiperefreshLayout.setRefreshing(false);
                }
                break;
            case API_S.Tags.MAKE_PAYMENT_WITH_CARD:
                if (a == ApiManager.APIFETCHER.KEY_API_IS_STARTED) {
                    mBinding.swiperefreshLayout.setRefreshing(true);
                } else {
                    mBinding.swiperefreshLayout.setRefreshing(false);
                }
                break;

            case API_S.Tags.DELETE_CARD:
                if (a == ApiManager.APIFETCHER.KEY_API_IS_STARTED) {
                    mBinding.swiperefreshLayout.setRefreshing(true);
                } else {
                    mBinding.swiperefreshLayout.setRefreshing(false);
                }
                break;
        }

    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {
        try {

            switch (APINAME) {
                case API_S.Tags.VIEW_CARDS:
                    placeholder.getViewAdapter().notifyDataSetChanged();
                    placeholder.removeAllViews();
                    ModelViewCards modelViewCards = SingletonGson.getInstance().fromJson("" + script, ModelViewCards.class);
                    for (int i = 0; i < modelViewCards.getData().size(); i++) {
                        placeholder.addView(new HolderCard(modelViewCards.getData().get(i)));
                    }
                    break;
                case API_S.Tags.MAKE_PAYMENT_WITH_CARD:
                    ModelAddMoney modelAddMoney2 = SingletonGson.getInstance().fromJson("" + script, ModelAddMoney.class);
                    if (modelAddMoney2.getResult().equals("1")) {
                        Toast.makeText(this, "" + modelAddMoney2.getMessage(), Toast.LENGTH_SHORT).show();
                        HashMap<String, String> data = new HashMap<>();
                        data.put("amount", "" + AMOUNT);
                        data.put("payment_request", "Card");
                        apiManagerNew._post("" + API_S.Tags.ADD_MONEY_IN_WALLET, "" + API_S.Endpoints.ADD_MONEY_IN_WALLET, data, sessionManager);
                    } else {
                        Toast.makeText(this, "" + modelAddMoney2.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    break;
                case API_S.Tags.ADD_MONEY_IN_WALLET:
                    ModelAddMoney modelAddMoney = SingletonGson.getInstance().fromJson("" + script, ModelAddMoney.class);
                    Toast.makeText(this, "" + modelAddMoney.getMessage(), Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                case API_S.Tags.DELETE_CARD:
                    ModelAddMoney modelAddMoney1 = SingletonGson.getInstance().fromJson("" + script, ModelAddMoney.class);
                    Toast.makeText(this, "" + modelAddMoney1.getMessage(), Toast.LENGTH_SHORT).show();
                    //finish();
                    break;
            }
        } catch (Exception e) {
            ApporioLog.logE("" + TAG, "Exception caught while calling API " + e.getMessage());
        }

    }

    @Override
    public void onFetchResultZero(String script, String APINAME) {
        Snackbar.make(mBinding.root, "" + script, Snackbar.LENGTH_SHORT).show();

    }


    @Layout(R.layout.holder_card_item)
    class HolderCard {

        @View(R.id.card_image)
        ImageView cardImage;
        @View(R.id.card_number)
        TextView cardNumber;
        @View(R.id.card_hlder_name)
        TextView cardHlderName;
        @View(R.id.img_dlt)
        ImageView imgDlt;
        @View(R.id.root)
        LinearLayout root;
        @View(R.id.select_text)
        TextView select_text;


        private ModelViewCards.DataBean mData;

        public HolderCard(ModelViewCards.DataBean responseBean) {
            this.mData = responseBean;
        }


        @Resolve
        private void setdata() {
            cardNumber.setText("" + mData.getCard_number());
            cardHlderName.setText("" + mData.getExp_month() + "/" + mData.getExp_year());
            imgDlt.setImageResource(R.drawable.ic_delete_black_24dp);

//            if (AMOUNT.equals("")) {
//                select_text.setVisibility(android.view.View.GONE);
//                deleteBtn.setVisibility(android.view.View.VISIBLE);
//            } else {
//                select_text.setVisibility(android.view.View.VISIBLE);
//                deleteBtn.setVisibility(android.view.View.VISIBLE);
//            }

            if (mData.getCard_type().equals("Master")) {
                cardImage.setImageResource(R.drawable.ic_master_card_vector);
            }else if (mData.getCard_type().equals("Visa")) {
                cardImage.setImageResource(R.drawable.ic_visa_card_vector);
            }else {
                cardImage.setImageResource(R.drawable.ic_master_card_vector);
            }
        }


        @Click(R.id.img_dlt)
        private void onDeleteClick() {
            final AlertDialog.Builder dialog = new AlertDialog.Builder(CardListActivity.this);
            dialog.setTitle(R.string.delete_card);
            dialog.setMessage(getString(R.string.are_you_sure_you_want_to_delete_card_) + " " + mData.getCard_number());
            dialog.setCancelable(false);
            dialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            dialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {

                    HashMap<String, String> data = new HashMap<>();
                    data.put("card_id", "" + mData.getCard_id());
                    try {
                        apiManagerNew._post("" + API_S.Tags.DELETE_CARD, "" + API_S.Endpoints.DELETE_CARD, data, sessionManager);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    // Snackbar.make(root,"Currently we are not deleting it , For Demo Purpose",Snackbar.LENGTH_SHORT).show();

                    paramDialogInterface.dismiss();
                }
            }).show();
        }


        @Click(R.id.root)
        private void onRootClick() {
            if (!AMOUNT.equals("")) {
                try {
                    ApporioLog.logI(TAG, "Now GPS Status = " + false + ", Now Showing Dialog");
                    AlertDialog.Builder builder = new AlertDialog.Builder(CardListActivity.this);
                    builder.setTitle(R.string.are_you_sure);
                    builder.setMessage(R.string.click_ok_to_redeem_card)
                            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    try {
                                        HashMap<String, String> data = new HashMap<>();
                                        data.put("card_id", "" + mData.getCard_id());
                                        data.put("amount", "" + AMOUNT);
                                        data.put("currency", sessionManager.getUserDetails().get(SessionManager.CURRENCY));

                                        apiManagerNew._post("" + API_S.Tags.MAKE_PAYMENT_WITH_CARD, "" + API_S.Endpoints.MAKE_PAYMENT_WITH_CARD, data, sessionManager);

                                        dialog.dismiss();
                                    } catch (Exception e) {
                                        ApporioLog.logE("" + TAG, "Exception caught while calling API " + e.getMessage());
                                    }
                                    dialog.dismiss();
                                }
                            })
                            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            });
                    builder.create().show();
                } catch (Exception e) {
                    Snackbar.make(root, "" + e.getMessage(), Snackbar.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            callAPI();
        } catch (Exception e) {
            ApporioLog.logE("" + TAG, "Exception caught while calling API " + e.getMessage());
        }
    }
}