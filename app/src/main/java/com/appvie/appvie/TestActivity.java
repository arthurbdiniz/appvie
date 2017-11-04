package com.appvie.appvie;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.appvie.appvie.model.Cliente;
import com.appvie.appvie.model.OrderStatus;
import com.appvie.appvie.model.Orientation;
import com.appvie.appvie.model.TimeLineModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private TimeLineAdapter mTimeLineAdapter;
    private List<TimeLineModel> mDataList = new ArrayList<>();
    private Orientation mOrientation;
    private boolean mWithLinePadding;

    public final static String EXTRA_ORIENTATION = "EXTRA_ORIENTATION";
    public final static String EXTRA_WITH_LINE_PADDING = "EXTRA_WITH_LINE_PADDING";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        mOrientation = (Orientation) getIntent().getSerializableExtra(EXTRA_ORIENTATION);
        mWithLinePadding = getIntent().getBooleanExtra(EXTRA_WITH_LINE_PADDING, false);

        setTitle("Linha do Tempo");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(getLinearLayoutManager());
        mRecyclerView.setHasFixedSize(true);

        initView();
    }

    private LinearLayoutManager getLinearLayoutManager() {
        if (mOrientation == Orientation.HORIZONTAL) {
            return new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        } else {
            return new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        }
    }

    private void initView() {
        setDataListItems();
        mTimeLineAdapter = new TimeLineAdapter(mDataList, mOrientation, mWithLinePadding);
        mRecyclerView.setAdapter(mTimeLineAdapter);
    }

    private void setDataListItems(){
        mDataList.add(new TimeLineModel("Alta","Melhora do quadro de infecção. Paciente recebe alta.", "","Pedro Carvalho", "12323", OrderStatus.INACTIVE));
        mDataList.add(new TimeLineModel("Proxima Visita","Próxima visita agendada para 30 dias", "", "Pedro Carvalho", "12323", OrderStatus.INACTIVE));
        mDataList.add(new TimeLineModel("Receita","antibiótico", "2017-02-12   08:00","Pedro Carvalho", "12323", OrderStatus.ACTIVE));
        mDataList.add(new TimeLineModel("Pedido de Exame", "Exame de sangue e urina ", "2017-02-11   21:00","Pedro Carvalho", "12323", OrderStatus.COMPLETED));
        mDataList.add(new TimeLineModel("Consulta","Consulta com um urologista, para nova avaliação do quadro de infecção.", "2017-02-11   18:00","Pedro Carvalho", "12323", OrderStatus.COMPLETED));
        mDataList.add(new TimeLineModel("Encaminhamento","Tratamento com um urologista  ", "2017-02-11   09:30","Arthur Diniz", "39421", OrderStatus.COMPLETED));
        mDataList.add(new TimeLineModel("Internação","Não melhora do quadro de infecção. Tratamento intensivo durante 48hrs ", "2017-02-11   08:00","Arthur Diniz", "39421", OrderStatus.COMPLETED));
        mDataList.add(new TimeLineModel("Receita","Antibiótico e analgésico ", "2017-02-10   15:00","Arthur Diniz", "39421", OrderStatus.COMPLETED));
        mDataList.add(new TimeLineModel("Pedido de Exame","Exame de sangue e urina ", "2017-02-10   14:30","Arthur Diniz", "39421", OrderStatus.COMPLETED));
        mDataList.add(new TimeLineModel("Consulta","Consulta com um clínico geral. Paciente queixando de dor abdominal com dificuldade de urinar ", "2017-02-10   14:00","Arthur Diniz", "39421", OrderStatus.COMPLETED));


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Menu
        switch (item.getItemId()) {
            //When home is clicked
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.done:
                // custom dialog
                final Dialog dialog = new Dialog(TestActivity.this);
                dialog.setContentView(R.layout.dialog_add_timeline);
                dialog.setTitle("Title...");

                // set the custom dialog components - text, image and button
//                TextView text = (TextView) dialog.findViewById(R.id.text);
//                text.setText("Android custom dialog example!");
//                ImageView image = (ImageView) dialog.findViewById(R.id.image);
//                image.setImageResource(R.drawable.ic_add_black_24dp);

                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
                return true;


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        if(mOrientation!=null)
            savedInstanceState.putSerializable(EXTRA_ORIENTATION, mOrientation);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(EXTRA_ORIENTATION)) {
                mOrientation = (Orientation) savedInstanceState.getSerializable(EXTRA_ORIENTATION);
            }
        }
        super.onRestoreInstanceState(savedInstanceState);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_new_time_line, menu);
        return true;
    }


}
