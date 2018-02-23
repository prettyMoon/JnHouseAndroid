package jnhouse.topwellsoft.com.jnhouse_android.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import jnhouse.topwellsoft.com.jnhouse_android.R;
import jnhouse.topwellsoft.com.jnhouse_android.model.NewHouseEntity;
import jnhouse.topwellsoft.com.jnhouse_android.view.FluidLayout;

public class NewHouseAdapter extends BaseListAdapter<NewHouseEntity> {
   private List<NewHouseEntity>mList;
    private boolean isNoData;
    private int mHeight;
    public static final int ONE_SCREEN_COUNT = 20;
    public static final int ONE_REQUEST_COUNT = 19;

    public  NewHouseAdapter(Context context){
        super(context);
    }
    public NewHouseAdapter( Context context,List<NewHouseEntity>list){
        super(context,list);
        this.mList=list;
    }
    //设置数据
  public  void setData(List<NewHouseEntity>list){
      addALL(list);
      isNoData=false;
      if (list.size()==1&&list.get(0).isNoData()){
          isNoData=list.get(0).isNoData();
          mHeight=list.get(0).getHeight();
      }
      notifyDataSetChanged();
  }

    public void setDataFilter(List<NewHouseEntity>list){
        clearAll();
        addALL(list);
        isNoData=false;
        if (list.size()==1&&list.get(0).isNoData()){
           isNoData=list.get(0).isNoData();
            mHeight=list.get(0).getHeight();
        }
        notifyDataSetChanged();
    }
   public  List<NewHouseEntity> createEmptyList(int size){
       List<NewHouseEntity> emptylist=new ArrayList<>();
       if (size<=0)return emptylist;
       for (int i=0;i<size;i++){
           emptylist.add(new NewHouseEntity());
       }
       return emptylist;
   }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

   if (isNoData){
       view=mInflater.inflate(R.layout.item_no_data_layout,null);
       AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mHeight);
       RelativeLayout rootView = ButterKnife.findById(view, R.id.rl_root_view);
       rootView.setLayoutParams(params);
       return view;
   }
    final ViewHolder holder;
        if (view!=null&& view instanceof LinearLayout){
            holder=(ViewHolder) view.getTag();
        }else {
            view=mInflater.inflate(R.layout.tp_new_house_list_item,null);
             holder=new ViewHolder(view);
             view.setTag(holder);

        }
        NewHouseEntity entity=mList.get(i);
        mImageManager.loadUrlImage(entity.getBorough_thumb(),holder.imageView);
        holder.tvTitle.setText(entity.getBorough_title());
        holder.tvAddress.setText(entity.getBorough_address());

        if(entity.getBorough_avgprice().length()>0&&!entity.getBorough_avgprice().equals("0"))
            //Integer.parseInt(entity.getBorough_avgprice().split("\\.")[0])字符串取整
        holder.tvPrice.setText( Integer.parseInt(entity.getBorough_avgprice().split("\\.")[0])+"元/m²");
        else holder.tvPrice.setText("价格未知");
        holder.tvTotalerea.setText(entity.getSell_time());
        genTag(entity.getHouse_feature(),holder.fluidLayout);
        return view;
    }
    private void genTag(String laber, FluidLayout fluidLayout) {
        fluidLayout.removeAllViews();
        fluidLayout.setGravity(Gravity.CENTER);
        List<String> tags = new ArrayList<>();
        if (laber != null && !laber.equals("")) {
            String[] s = laber.split(",");
            for (int i = 0; i < s.length; i++) {
                tags.add(s[i]);
            }
        }

        if (tags != null && tags.size() > 0) {
            for (int i = 0; i < tags.size(); i++) {
                TextView tv = new TextView(mContext);
                tv.setText(tags.get(i));
                tv.setTextSize(10);

                if (i == 12) {
                    if (!true) {
                        tv.setHeight(100);
                        tv.setGravity(Gravity.CENTER);
                    }
                    tv.setBackgroundResource(R.drawable.text_bg_highlight);

                } else {
                    if (true) {
                        tv.setBackgroundResource(R.drawable.text_bg);
                    }
                }

                if (i % 8 == 0) {
                    tv.setTextColor(Color.parseColor("#FF0000"));
                } else if (i % 28 == 0) {
                    tv.setTextColor(Color.parseColor("#66CD00"));
                } else {
                    tv.setTextColor(Color.parseColor("#666666"));
                }

                FluidLayout.LayoutParams params = new FluidLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(3,3,3,3);
                fluidLayout.addView(tv, params);
            }
        }

    }

    static class ViewHolder{
        @Bind(R.id.new_house_item_thumb)ImageView imageView;
        @Bind(R.id.new_house_item_address)TextView tvAddress;
        @Bind(R.id.new_house_item_price)TextView tvPrice;
        @Bind(R.id.new_house_item_totalarea)TextView tvTotalerea;
        @Bind(R.id.new_house_item_title)TextView tvTitle;
        @Bind(R.id.newhouse_fluid_layout)FluidLayout fluidLayout;
        ViewHolder(View view){ButterKnife.bind(this,view);}
    }
}
