package com.qqq.thehomeofthecar.fragment.findcarfragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.qqq.thehomeofthecar.R;
import com.qqq.thehomeofthecar.adapter.findcaradapter.BrandAdapter;
import com.qqq.thehomeofthecar.base.BaseFragment;
import com.qqq.thehomeofthecar.bean.findcar.BrandListViewBean;
import com.qqq.thehomeofthecar.bean.findcar.HotBrandBean;
import com.qqq.thehomeofthecar.bean.findcar.MainCarBean;
import com.qqq.thehomeofthecar.util.PathValue;
import com.qqq.thehomeofthecar.util.VolleySingle;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by 秦谦谦 on 16/5/10 10:55.
 */
public class BrandFindCarFragment extends BaseFragment {
    private ExpandableListView listView;
    private HotBrandBean hotBrandBean;
    private MainCarBean mainCarBean;
    private BrandListViewBean brandListViewBean;
    private View heardLayout;
    private BrandAdapter brandAdapter;


    /**
     * 热门品牌
     **/
    private ImageView imageView0;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private ImageView imageView5;
    private ImageView imageView6;
    private ImageView imageView7;
    private ImageView imageView8;
    private ImageView imageView9;
    private TextView textView0;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;
    private TextView textView7;
    private TextView textView8;
    private TextView textView9;


    /**
     * 主打品牌
     **/
    private TextView mainText0;
    private TextView mainText1;
    private TextView mainText2;
    private ImageView mainImg0;
    private ImageView mainImg1;
    private ImageView mainImg2;


    @Override
    public int setLayout() {
        return R.layout.fragment_findcar_brand;
    }

    @Override
    protected void initView(View view) {

        listView = bindView(R.id.findCar_lv);
        listView.setGroupIndicator(null);//隐藏组旁边的下拉菜单按钮
        heardLayout = LayoutInflater.from(context).inflate(R.layout.item_findcar_brand_header, null);


        /****热门品牌**/
        imageView0 = (ImageView) heardLayout.findViewById(R.id.findcar_brand_hot_img0);
        imageView1 = (ImageView) heardLayout.findViewById(R.id.findcar_brand_hot_img1);
        imageView2 = (ImageView) heardLayout.findViewById(R.id.findcar_brand_hot_img2);
        imageView3 = (ImageView) heardLayout.findViewById(R.id.findcar_brand_hot_img3);
        imageView4 = (ImageView) heardLayout.findViewById(R.id.findcar_brand_hot_img4);
        imageView5 = (ImageView) heardLayout.findViewById(R.id.findcar_brand_hot_img5);
        imageView6 = (ImageView) heardLayout.findViewById(R.id.findcar_brand_hot_img6);
        imageView7 = (ImageView) heardLayout.findViewById(R.id.findcar_brand_hot_img7);
        imageView8 = (ImageView) heardLayout.findViewById(R.id.findcar_brand_hot_img8);
        imageView9 = (ImageView) heardLayout.findViewById(R.id.findcar_brand_hot_img9);

        textView0 = (TextView) heardLayout.findViewById(R.id.findcar_brand_hot_name0);
        textView1 = (TextView) heardLayout.findViewById(R.id.findcar_brand_hot_name1);
        textView2 = (TextView) heardLayout.findViewById(R.id.findcar_brand_hot_name2);
        textView3 = (TextView) heardLayout.findViewById(R.id.findcar_brand_hot_name3);
        textView4 = (TextView) heardLayout.findViewById(R.id.findcar_brand_hot_name4);
        textView5 = (TextView) heardLayout.findViewById(R.id.findcar_brand_hot_name5);
        textView6 = (TextView) heardLayout.findViewById(R.id.findcar_brand_hot_name6);
        textView7 = (TextView) heardLayout.findViewById(R.id.findcar_brand_hot_name7);
        textView8 = (TextView) heardLayout.findViewById(R.id.findcar_brand_hot_name8);
        textView9 = (TextView) heardLayout.findViewById(R.id.findcar_brand_hot_name8);

        /***主打品牌***/

        mainText0 = (TextView) heardLayout.findViewById(R.id.secondLv_tv0);
        mainText1 = (TextView) heardLayout.findViewById(R.id.secondLv_tv1);
        mainText2 = (TextView) heardLayout.findViewById(R.id.secondLv_tv2);
        mainImg0 = (ImageView) heardLayout.findViewById(R.id.secondLv_img0);
        mainImg1 = (ImageView) heardLayout.findViewById(R.id.secondLv_img1);
        mainImg2 = (ImageView) heardLayout.findViewById(R.id.secondLv_img2);


        brandAdapter = new BrandAdapter(context);
        listView.addHeaderView(heardLayout);

    }

    @Override
    protected void initData() {

        //热门品牌解析数据
        VolleySingle.addRequest(PathValue.FINDCAR_BRAND_HOTBRANG, HotBrandBean.class, new Response.Listener<HotBrandBean>() {
            @Override
            public void onResponse(HotBrandBean response) {
                Log.d("BrandFindCarFragment", "热门品牌解析数据成功");
                hotBrandBean = response;
                setImage(imageView0, response.getResult().getList().get(0).getImg());
                setImage(imageView1, response.getResult().getList().get(1).getImg());
                setImage(imageView2, response.getResult().getList().get(2).getImg());
                setImage(imageView3, response.getResult().getList().get(3).getImg());
                setImage(imageView4, response.getResult().getList().get(4).getImg());
                setImage(imageView5, response.getResult().getList().get(5).getImg());
                setImage(imageView6, response.getResult().getList().get(6).getImg());
                setImage(imageView7, response.getResult().getList().get(7).getImg());
                setImage(imageView8, response.getResult().getList().get(8).getImg());
                setImage(imageView9, response.getResult().getList().get(9).getImg());
                textView0.setText(response.getResult().getList().get(0).getName());
                textView1.setText(response.getResult().getList().get(1).getName());
                textView2.setText(response.getResult().getList().get(2).getName());
                textView3.setText(response.getResult().getList().get(3).getName());
                textView4.setText(response.getResult().getList().get(4).getName());
                textView5.setText(response.getResult().getList().get(5).getName());
                textView6.setText(response.getResult().getList().get(6).getName());
                textView7.setText(response.getResult().getList().get(7).getName());
                textView8.setText(response.getResult().getList().get(8).getName());
                textView9.setText(response.getResult().getList().get(9).getName());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("BrandFindCarFragment", "热门品牌解析数据失败");
            }
        });

        //主打车解析
        VolleySingle.addRequest(PathValue.FINDCAR_BRAND_MAINCAR, MainCarBean.class, new Response.Listener<MainCarBean>() {
            @Override
            public void onResponse(MainCarBean response) {
                Log.d("BrandFindCarFragment", "主打车解析成功");
                setImage(mainImg0, response.getResult().getList().get(0).getImg());
                setImage(mainImg1, response.getResult().getList().get(1).getImg());
                setImage(mainImg2, response.getResult().getList().get(2).getImg());
                mainText0.setText(response.getResult().getList().get(0).getSeriesname());
                mainText1.setText(response.getResult().getList().get(1).getSeriesname());
                mainText2.setText(response.getResult().getList().get(2).getSeriesname());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("BrandFindCarFragment", "主打车解析失败");
            }
        });

        //listView的数据解析
        VolleySingle.addRequest(PathValue.FINDCAR_BRAND_LISTVIEW, BrandListViewBean.class, new Response.Listener<BrandListViewBean>() {
            @Override
            public void onResponse(BrandListViewBean response) {
                Log.d("BrandFindCarFragment", "listView的数据解析成功");
                brandListViewBean = response;
                brandAdapter.setBrandListViewBean(response);
                listView.setAdapter(brandAdapter);

                //首次加载数据全部展开
                if (response.getResult().getBrandlist().size()>0){
                for(int i = 0; i<response.getResult().getBrandlist().size();i++){
                    listView.expandGroup(i);
                }
                    //不能点击收缩
                    listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                        @Override
                        public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                            return true;
                        }
                    });


                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("BrandFindCarFragment", "listView的数据解析失败");
            }
        });


    }




    private void setImage(ImageView image, String path) {
        Picasso.with(context).load(path).placeholder(R.mipmap.ahlib_carback).error(R.mipmap.ahlib_page_icon_empty).into(image);
    }

}
