package com.appvie.appvie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.appvie.appvie.model.Cliente;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NewClientActivity extends AppCompatActivity {

    private static final String TAG = "New Client" ;

    private EditText clientName;
    private EditText clientCpf;
    private EditText clientEmail;
    private EditText clientDDD;
    private EditText clientTelephone;

    private EditText clientBill;
    private EditText clientDeadline;

    private Spinner clientSexo;
    private Spinner clientEstadoCivil;
    private Spinner clientTipoSanguineo;

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    private String clientId;

    Building placeObj = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_client);

        setTitle("Novo Paciente");

        clientName = (EditText) findViewById(R.id.client_name);
        clientCpf = (EditText) findViewById(R.id.client_cpf);
        clientEmail = (EditText) findViewById(R.id.client_email);
        clientDDD = (EditText) findViewById(R.id.client_telephone_ddd);
        clientTelephone = (EditText) findViewById(R.id.client_telephone_number);

        clientBill = (EditText) findViewById(R.id.client_bill);
        clientDeadline = (EditText) findViewById(R.id.client_deadline);

        clientSexo = (Spinner) findViewById(R.id.spinner_sexo);
        clientEstadoCivil = (Spinner) findViewById(R.id.spinner_estado_civil);
        clientTipoSanguineo = (Spinner) findViewById(R.id.spinner_tipo_sanquineo);



        initFirebase();
        addItemsOnSpinners();



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: obter informações sobre o local selecionado.
                Log.i(TAG, "Building: " + place.getName());
                placeObj = new Building(place.getName(), place.getAddress(), place.getLatLng());
            }

            @Override
            public void onError(Status status) {
                // TODO: Solucionar o erro.
                Log.i(TAG, "Ocorreu um erro: " + status);
            }
        });



    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void addItemsOnSpinners() {


        List<String> listSexo = new ArrayList<String>();
        listSexo.add("Masculino");
        listSexo.add("Feminino");

        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listSexo);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clientSexo.setAdapter(dataAdapter1);



        List<String> listEstadoCivil = new ArrayList<String>();
        listEstadoCivil.add("Solteiro");
        listEstadoCivil.add("Casado");
        listEstadoCivil.add("Divorciado");
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listEstadoCivil);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clientEstadoCivil.setAdapter(dataAdapter2);




        List<String> listTipoSanguineo = new ArrayList<String>();
        listTipoSanguineo.add("A+");
        listTipoSanguineo.add("A-");
        listTipoSanguineo.add("B+");
        listTipoSanguineo.add("B-");
        listTipoSanguineo.add("AB+");
        listTipoSanguineo.add("AB-");
        listTipoSanguineo.add("O+");
        listTipoSanguineo.add("O-");

        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listTipoSanguineo);
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clientTipoSanguineo.setAdapter(dataAdapter3);
    }






    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_new_client, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.done:

                //verifys and send to firebase
                //startActivity(new Intent(this, About.class));

                //Dimensionamento dimensionamento = new Dimensionamento(Double.parseDouble(clientBill.getText().toString()) , Integer.parseInt(clientDeadline.getText().toString()));
                final FirebaseUser integrator = FirebaseAuth.getInstance().getCurrentUser();

                //Date Creates
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy  HH:mm");
                SimpleDateFormat dateFormat_hora = new SimpleDateFormat("HH:mm:ss");
                Date data = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(data);

                Date data_atual = cal.getTime();
                String dateCreation = dateFormat.format(data_atual);
                String hora_atual = dateFormat_hora.format(data_atual);

                //String yearStr = String.valueOf((year));


                if (TextUtils.isEmpty(clientId)) {
                    clientId = mFirebaseDatabase.push().getKey();
                }
                Cliente client = new Cliente(clientName.getText().toString(),
                                                clientCpf.getText().toString(),
                                                clientEmail.getText().toString(),
                                                clientDDD.getText().toString(),
                                                clientTelephone.getText().toString(),
                                                clientId,
                                                integrator.getUid(),
                                                dateCreation,
                                                placeObj
                                                );

                mFirebaseDatabase.child(clientId).setValue(client);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void initFirebase(){


        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("clients");
        //mFirebaseInstance.getReference("bdmg-cacef");
        // app_title change listener
//        mFirebaseInstance.getReference("app_title").addValueEventListener(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                //Log.e(TAG, "App title updated");
//
//                //String appTitle = dataSnapshot.getValue(String.class);
//
//                // update toolbar title
//                //getSupportActionBar().setTitle(appTitle);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                //  Log.e(TAG, "Failed to read app title value.", error.toException());
//            }
//        });
    }
}


