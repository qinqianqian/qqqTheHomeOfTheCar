package com.qqq.thehomeofthecar.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;


import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.qqq.thehomeofthecar.R;

/**
 * ImageView创建工厂
 */
public class ViewFactory {

	/**
	 * 获取ImageView视图的同时加载显示url
	 * 
	 * @param
	 * @return
	 */
	public static ImageView getImageView(Context context, String url) {
		ImageView imageView = (ImageView)LayoutInflater.from(context).inflate(
				R.layout.cycle_view_banner, null);
		ImageLoader imageLoader=ImageLoader.getInstance();
		imageLoader.init(ImageLoaderConfiguration.createDefault(context));
		ImageLoader.getInstance().displayImage(url, imageView);
		return imageView;
	}
}
