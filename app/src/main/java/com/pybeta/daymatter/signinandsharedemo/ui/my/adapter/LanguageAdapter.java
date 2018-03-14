package com.pybeta.daymatter.signinandsharedemo.ui.my.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.pybeta.daymatter.signinandsharedemo.R;
import com.pybeta.daymatter.signinandsharedemo.bean.LanguageBean;
import com.pybeta.daymatter.signinandsharedemo.interface_s.RecyclerViewItemOnClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 语言种类适配器
 * Created by luogj on 2018/2/9.
 */

public class LanguageAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<LanguageBean> languageBeanList = new ArrayList<>();
    private RecyclerViewItemOnClickListener itemOnClickListener;

    public LanguageAdapter(Context context){
        this.context = context;
    }

    public void setLanguageBeanList(List<LanguageBean> list){
        if (languageBeanList != null){
            languageBeanList.clear();
            languageBeanList.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(RecyclerViewItemOnClickListener listener){
        this.itemOnClickListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_language_adapter_item_view,null);
        return new LanguageHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        LanguageHolder languageHolder = (LanguageHolder) holder;
        LanguageBean languageBean = languageBeanList.get(position);
        languageHolder.tv_languageName.setText(languageBean.getLanguageName());
        boolean select = languageBean.isSelect();
        if (select){
            languageHolder.tv_languageSelect.setSelected(true);
        }else {
            languageHolder.tv_languageSelect.setSelected(false);
        }
        if (position == getItemCount() - 1){
            languageHolder.tv_itemLine.setVisibility(View.GONE);
        }else {
            languageHolder.tv_itemLine.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return languageBeanList == null ? 0 : languageBeanList.size();
    }

    class LanguageHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView tv_languageName;
        private final TextView tv_languageSelect;
        private final TextView tv_itemLine;

        public LanguageHolder(View itemView) {
            super(itemView);
            tv_languageName = (TextView) itemView.findViewById(R.id.tv_languageName);
            tv_languageSelect = (TextView) itemView.findViewById(R.id.tv_languageSelect);
            tv_itemLine = (TextView) itemView.findViewById(R.id.tv_itemLine);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemOnClickListener.onItemClick(v,getPosition());
        }
    }
}
