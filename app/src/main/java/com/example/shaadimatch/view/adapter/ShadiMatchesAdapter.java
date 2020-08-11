package com.example.shaadimatch.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shaadimatch.R;
import com.example.shaadimatch.app.Constants;
import com.example.shaadimatch.room.entity.ShadiMatchesModel;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SAKET on 11/08/2020
 */
public class ShadiMatchesAdapter extends RecyclerView.Adapter<ShadiMatchesAdapter.ViewHolder> {

    private Context context;
    private List<ShadiMatchesModel> shadiMatchesModelList;
    private OnItemClickListener listener;

    public ShadiMatchesAdapter(List<ShadiMatchesModel> shadiMatchesModelList) {
        this.shadiMatchesModelList = shadiMatchesModelList;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context=parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_shadi_matches,(ViewGroup) null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ShadiMatchesModel shadiMatchesModel = shadiMatchesModelList.get(position);
        Glide.with(context).load(shadiMatchesModel.getProfilePicUrl()).placeholder(shadiMatchesModel.getProfilePlaceholderIcon()).into(holder.imageViewProfile);
        holder.textViewName.setText(context.getString(R.string.str_name,shadiMatchesModel.getFirstName(),shadiMatchesModel.getLastName()));
        holder.textViewDescription.setText(context.getString(R.string.str_description,shadiMatchesModel.getAge(),shadiMatchesModel.getState(),shadiMatchesModel.getCountry()));
        switch (shadiMatchesModel.getStatus()) {
            case Constants.ACCEPTED:
                holder.relativeLayoutAction.setVisibility(View.GONE);
                holder.textViewStatus.setVisibility(View.VISIBLE);
                holder.textViewStatus.setText(context.getString(R.string.str_accept_status));
                break;
            case Constants.REJECTED:
                holder.relativeLayoutAction.setVisibility(View.GONE);
                holder.textViewStatus.setVisibility(View.VISIBLE);
                holder.textViewStatus.setText(context.getString(R.string.str_reject_status));
                break;
            default:
                holder.relativeLayoutAction.setVisibility(View.VISIBLE);
                holder.textViewStatus.setVisibility(View.GONE);
                break;
        }
        holder.imageViewAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null) {
                    listener.onAcceptClick(shadiMatchesModel);
                }
            }
        });
        holder.imageViewDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null) {
                    listener.onDeclineClick(shadiMatchesModel);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return shadiMatchesModelList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.image_view_profile) ImageView imageViewProfile;
        @BindView(R.id.text_view_name) TextView textViewName;
        @BindView(R.id.text_view_description) TextView textViewDescription;
        @BindView(R.id.text_view_status) TextView textViewStatus;
        @BindView(R.id.relative_action_layout) RelativeLayout relativeLayoutAction;
        @BindView(R.id.image_view_accept) ImageView imageViewAccept;
        @BindView(R.id.image_view_decline) ImageView imageViewDecline;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface OnItemClickListener {
        void onAcceptClick(ShadiMatchesModel shadiMatchesModel);
        void onDeclineClick(ShadiMatchesModel shadiMatchesModel);
    }

}
