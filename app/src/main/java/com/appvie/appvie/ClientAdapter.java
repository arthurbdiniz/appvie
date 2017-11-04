package com.appvie.appvie;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.appvie.appvie.model.Cliente;

import java.util.ArrayList;


public class ClientAdapter extends RecyclerView.Adapter implements View.OnClickListener {


    private ArrayList<Cliente> tickets;
    private ArrayList<Cliente> tempTicketsText;
    private ArrayList<Cliente> tempTicketsCategory;
    private ArrayList<Cliente> tempTicketsLocation;

    private ArrayList<Cliente> filteredTickets;
    private ArrayList<Cliente> tempList;

    private RecyclerView recyclerView;
    private Context context;
    private ClientViewHolder ticketViewHolder;
    private boolean imageTask = false;

    public ClientAdapter(ArrayList<Cliente> tickets, Context context, RecyclerView recyclerView) {
        this.tickets = tickets;
        this.filteredTickets = tickets;
        this.context = context;
        this.recyclerView = recyclerView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.client_item, parent, false);

        ClientViewHolder ticketViewHolder = new ClientViewHolder(view);
        view.setOnClickListener(this);

        return ticketViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ticketViewHolder = (ClientViewHolder) holder;

        final Cliente client  = filteredTickets.get(position) ;

        ticketViewHolder.name.setText(client.getNome());
        ticketViewHolder.email.setText(client.getEmail());
        ticketViewHolder.ddd.setText(client.getDdd());
        ticketViewHolder.telephone.setText(client.getTelephone());
        ticketViewHolder.dateCreation.setText(client.getDateCreation());

    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return filteredTickets.size();
    }

    @Override
    public void onClick(View v) {
        int itemPosition = recyclerView.getChildLayoutPosition(v);
        final Cliente cliente = filteredTickets.get(itemPosition);

        // custom dialog
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_patient_password);
        dialog.setTitle("Title...");

//        // set the custom dialog components - text, image and button
//        TextView text = (TextView) dialog.findViewById(R.id.text);
//        text.setText("Android custom dialog example!");
//        ImageView image = (ImageView) dialog.findViewById(R.id.image);
//        image.setImageResource(R.drawable.ic_add_black_24dp);

        Button dialogButtonOk = (Button) dialog.findViewById(R.id.dialogButtonOK);
        Button dialogButtonCancel = (Button) dialog.findViewById(R.id.dialogButtonCANCEL);
        // if button is clicked, close the custom dialog
        dialogButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent goTicket = new Intent(context, ClientViewActivity.class);
                goTicket.putExtra("Client", cliente);
                goTicket.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(goTicket);
            }
        });

        dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();




    }



}

