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
import com.example.shaadimatch.room.entity.InvitationsModel;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import static com.example.shaadimatch.app.Constants.ACCEPTED;
import static com.example.shaadimatch.app.Constants.REJECTED;

/**
 * Created by SAKET on 11/08/2020
 * Adapter class for recyclerview item
 */
public class InvitationsAdapter extends RecyclerView.Adapter<InvitationsAdapter.ViewHolder> {

    private Context context;
    private List<InvitationsModel> invitationsModelList;
    private OnItemClickListener listener;

    public InvitationsAdapter(List<InvitationsModel> invitationsModelList) {
        this.invitationsModelList = invitationsModelList;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context=parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_invitations,(ViewGroup) null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final InvitationsModel invitationsModel = invitationsModelList.get(position);
        Glide.with(context).load(invitationsModel.getProfilePicUrl()).placeholder(invitationsModel.getProfilePlaceholderIcon()).into(holder.imageViewProfile);
        holder.textViewName.setText(context.getString(R.string.str_name,invitationsModel.getFirstName(),invitationsModel.getLastName()));
        holder.textViewDescription.setText(context.getString(R.string.str_description,invitationsModel.getAge(),invitationsModel.getState(),invitationsModel.getCountry()));
        switch (invitationsModel.getInvitationStatus()) {
            case ACCEPTED:
                holder.relativeLayoutAction.setVisibility(View.GONE);
                holder.textViewStatus.setVisibility(View.VISIBLE);
                holder.textViewStatus.setText(context.getString(R.string.str_accept_status));
                break;
            case REJECTED:
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
                    listener.onAcceptClick(invitationsModel);
                }
            }
        });
        holder.imageViewDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null) {
                    listener.onDeclineClick(invitationsModel);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return invitationsModelList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.image_view_profile) ImageView imageViewProfile;
        @BindView(R.id.text_view_name) TextView textViewName;
        @BindView(R.id.text_view_description) TextView textViewDescription;
        @BindView(R.id.text_view_status) TextView textViewStatus;
        @BindView(R.id.relative_action_layout) RelativeLayout relativeLayoutAction;
        @BindView(R.id.image_view_accept) ImageView imageViewAccept;
        @BindView(R.id.image_view_decline) ImageView imageViewDecline;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface OnItemClickListener {
        void onAcceptClick(InvitationsModel shadiMatchesModel);
        void onDeclineClick(InvitationsModel shadiMatchesModel);
    }

}
