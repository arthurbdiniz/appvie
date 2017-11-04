package com.appvie.appvie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.appvie.appvie.model.Cliente;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;

public class ClientViewActivity extends AppCompatActivity {

    private Cliente client;

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    private DatabaseReference clientObj;

    //Cliente
    private TextView clientName;
    private TextView clientCpf;
    private TextView clientEmail;
    private TextView clientDDD;
    private TextView clientTelephone;

    //Financiamento
    private TextView parcelaFinanciamento;
    private TextView entrada;
    private TextView valorFinanciado;
    private TextView custoTotal;
    private TextView taxaJuros;
    private TextView numeroParcelas;

    //Gráfico
    GraphView graph;
    private int numeroParcel = 0;


    private Button buttonTimeLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_view);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        client = (Cliente) intent.getSerializableExtra("Client");
        initViews();

        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference().child("clients");
        clientObj = FirebaseDatabase.getInstance().getReference().child("clients").child(client.clienteId);



        clientObj.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Log.d("TAG", snapshot.getValue().toString());

                //Colocar informações mais interessantes na tela
                clientName.setText(snapshot.child("nome").getValue().toString());
                clientEmail.setText(snapshot.child("email").getValue().toString());
                clientCpf.setText(snapshot.child("cpf").getValue().toString());
                clientDDD.setText(snapshot.child("ddd").getValue().toString());
                clientTelephone.setText(snapshot.child("telephone").getValue().toString());



            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        buttonTimeLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new  Intent(getApplicationContext(), TestActivity.class));
            }
        });


        setTitle(client.getNome());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void initViews() {

        clientName = (TextView) findViewById(R.id.client_name);
        clientEmail = (TextView) findViewById(R.id.client_email);
        clientCpf = (TextView) findViewById(R.id.client_cpf);
        clientDDD = (TextView) findViewById(R.id.client_telephone_ddd);
        clientTelephone = (TextView) findViewById(R.id.client_telephone_number);

        parcelaFinanciamento = (TextView) findViewById(R.id.parcela_financiamento);
        entrada = (TextView) findViewById(R.id.entrada);
        valorFinanciado = (TextView) findViewById(R.id.valor_financiamento);
        custoTotal = (TextView) findViewById(R.id.custo_total);
        taxaJuros = (TextView) findViewById(R.id.taxa_juros);
        numeroParcelas = (TextView) findViewById(R.id.numero_parcelas);


        buttonTimeLine = (Button) findViewById(R.id.btn_time_line);


    }

    public void deleteTicket(){
        clientObj.removeValue();
    }
}
