/**  
        * @title Share2SinaWeibo.java  
        * @package com.damuzhi.travel.activity.common  
        * @description   
        * @author liuxiaokun  
        * @update 2012-6-26 下午2:43:40  
        * @version V1.0  
 */
package com.damuzhi.travel.activity.common;


import java.io.IOException;
import java.net.MalformedURLException;

import com.android.utils.TokenStore;
import com.damuzhi.travel.R;
import com.damuzhi.travel.activity.common.qweibo.MyWebView;
import com.tencent.weibo.api.T_API;
import com.tencent.weibo.beans.OAuth;
import com.tencent.weibo.utils.OAuthClient;
import com.weibo.net.AccessToken;
import com.weibo.net.AsyncWeiboRunner;
import com.weibo.net.AsyncWeiboRunner.RequestListener;
import com.weibo.net.DialogError;
import com.weibo.net.ShareActivity;
import com.weibo.net.Utility;
import com.weibo.net.Weibo;
import com.weibo.net.WeiboDialogListener;
import com.weibo.net.WeiboException;
import com.weibo.net.WeiboParameters;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**  
 * @description   
 * @version 1.0  
 * @author liuxiaokun  
 * @update 2012-6-26 下午2:43:40  
 */

public class Share2Weibo extends Activity implements RequestListener
{
	/** Called when the activity is first created. */
	private ImageButton sendButton;
	private ImageButton cancelButton;
	private EditText shareContent;

	private static final String SINA_CONSUMER_KEY = "1119244700";
	private static final String SINA_CONSUMER_SECRET = "b396a07bb97c4b92c9476896a5a76c66";
	private static final String TAG = "Share2SinaWeibo";
	

	
	//QQ
	String[] qq_oauth_token_array;
	String qq_oauth_token_secret;
	String qq_oauth_token;

	public static OAuthClient qq_auth;
	public static OAuth qq_oauth;

	
	private static final String SHARE_CONFIG = "share_config";
	private static final  int SHARE_2_SINA = 1;
	private static final  int SHARE_2_QQ = 2;
	int shareConfig;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.share_2_weibo);
		shareConfig = getIntent().getIntExtra(SHARE_CONFIG,0);
		String title ;
		if (shareConfig == SHARE_2_SINA)
		{
			title = getString(R.string.share_2_sina_title);
		}else
		{
			title = getString(R.string.share_2_qq_title);
		}
		TextView shareTitle = (TextView) findViewById(R.id.share_title);
		shareTitle.setText(title);
		sendButton = (ImageButton) this.findViewById(R.id.send_button);
		cancelButton = (ImageButton) this.findViewById(R.id.cancel_button);
		shareContent = (EditText) this.findViewById(R.id.share_content);		
		sendButton.setOnClickListener(sendOnClickListener);
	}

	public void onResume() {
		super.onResume();
	}

	private OnClickListener sendOnClickListener = new OnClickListener()
	{

		@Override
		public void onClick(View v)
		{
			String content = shareContent.getText().toString();
			if (shareConfig == SHARE_2_SINA)
			{
				getOauthToken();
				share2sinaWeibo(content);
			}else
			{
				qq_oauth = new OAuth("801094267", "17a7d99b68bc2e786313319a3fc25b80","null");
				share2qqWeibo(content);
			}
			
		}
	};
	
	
	private void getOauthToken()
	{
		Weibo weibo = Weibo.getInstance();
		weibo.setupConsumerConfig(SINA_CONSUMER_KEY, SINA_CONSUMER_SECRET);						
		weibo.setRedirectUrl("http://trip8888.com");
		weibo.authorize(Share2Weibo.this,new AuthDialogListener());
	}
	
	
	
	class AuthDialogListener implements WeiboDialogListener {

		@Override
		public void onComplete(Bundle values) {
			String token = values.getString("access_token");
			String expires_in = values.getString("expires_in");
			AccessToken accessToken = new AccessToken(token, SINA_CONSUMER_SECRET);
			accessToken.setExpiresIn(expires_in);
			Weibo.getInstance().setAccessToken(accessToken);
		}

		@Override
		public void onError(DialogError e) {
			Toast.makeText(getApplicationContext(),"Auth error : " + e.getMessage(), Toast.LENGTH_LONG).show();
		}

		@Override
		public void onCancel() {
			Toast.makeText(getApplicationContext(), "Auth cancel",Toast.LENGTH_LONG).show();
		}

		@Override
		public void onWeiboException(WeiboException e) {
			Toast.makeText(getApplicationContext(),"Auth exception : " + e.getMessage(), Toast.LENGTH_LONG).show();
		}

	}

	private void share2sinaWeibo(String content) {
        Weibo weibo = Weibo.getInstance();
        update(weibo, Weibo.getAppKey(), content, "", "");
		
    }
	
	
	private String update(Weibo weibo, String source, String status, String lon, String lat){
        WeiboParameters bundle = new WeiboParameters();
        bundle.add("source", source);
        bundle.add("status", status);
        if (!TextUtils.isEmpty(lon)) {
            bundle.add("lon", lon);
        }
        if (!TextUtils.isEmpty(lat)) {
            bundle.add("lat", lat);
        }
        String rlt = "";
        String url = Weibo.SERVER + "statuses/update.json";
        AsyncWeiboRunner weiboRunner = new AsyncWeiboRunner(weibo);
        weiboRunner.request(this, url, bundle, Utility.HTTPMETHOD_POST, this);
        return rlt;
    }

	@Override
	public void onComplete(String response)
	{
		 runOnUiThread(new Runnable() {

	            @Override
	            public void run() {
	                Toast.makeText(Share2Weibo.this, R.string.share_success, Toast.LENGTH_LONG).show();
	            }
	        });

	        this.finish();	
	}

	@Override
	public void onIOException(IOException e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(final WeiboException e)
	{
		 runOnUiThread(new Runnable() {

	            @Override
	            public void run() {
	                Toast.makeText(Share2Weibo.this,String.format(Share2Weibo.this.getString(R.string.send_failed) + ":%s",
	                                e.getMessage()), Toast.LENGTH_LONG).show();
	            }
	        });
		
	}
	
	
	
	
	
	

	
	
	
	private void share2qqWeibo(String content)
	{

		qq_oauth_token_array = TokenStore.fetch(Share2Weibo.this);
		qq_oauth_token = qq_oauth_token_array[0];
		qq_oauth_token_secret = qq_oauth_token_array[1];

		if (qq_oauth_token != null && qq_oauth_token_secret != null) { 
																	
			qq_oauth.setOauth_token(qq_oauth_token);
			qq_oauth.setOauth_token_secret(qq_oauth_token_secret);
			T_API tapi = new T_API();
			try {
				if (!content.equals("")) {
					tapi.add(qq_oauth, "json", content, "", "", "");
					Toast.makeText(Share2Weibo.this, R.string.share_success,Toast.LENGTH_LONG).show();
				}
			} catch (Exception e) {
				Log.e(TAG, "<share2qqWeibo> but catch exception :"+e.toString(),e);
			}
			return;
		}
		Toast.makeText(Share2Weibo.this, R.string.share_fail, Toast.LENGTH_LONG).show();
		try {

			qq_auth = new OAuthClient();
			qq_oauth = qq_auth.requestToken(qq_oauth);

			if (qq_oauth.getStatus() == 1) {
				Log.i(TAG, "Get Request Token failed!");
				return;
			} else {
				qq_oauth_token = qq_oauth.getOauth_token();
				String url = "http://open.t.qq.com/cgi-bin/authorize?oauth_token="+ qq_oauth_token;
				Intent intent = new Intent(Share2Weibo.this,MyWebView.class);
				intent.putExtra("URL", url);
				startActivity(intent);

			}

		} catch (Exception e) {
			Log.e(TAG, "<share2qqWeibo> but catch exception :"+e.toString(),e);
		}
	
	}
	
	public void setToken(String oauth_token, String oauth_token_secret) {

		qq_oauth.setOauth_token(oauth_token);
		qq_oauth.setOauth_token_secret(oauth_token_secret);

	}
}
