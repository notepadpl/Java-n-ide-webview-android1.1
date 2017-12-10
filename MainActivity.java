package com.example.myapplication;


 import java.net.*; 
 import java.io.*; 

 import java.net.URL;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.webkit.WebChromeClient;  
public class MainActivity extends Activity  {
   Button b1;
   EditText ed1;
   WebView wv1;
   Button backButton;
      Button enableButton,disableButton;
  

      
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      b1=(Button)findViewById(R.id.button);
      ed1=(EditText)findViewById(R.id.editText);
      backButton =(Button) findViewById(R.id.backButton);
 final Button forwardButton =(Button) findViewById(R.id.forwardButton);
 enableButton=(Button)findViewById(R.id.button1);
      disableButton=(Button)findViewById(R.id.button2);
      wv1=(WebView)findViewById(R.id.webView);
      wv1.setWebViewClient(new MyBrowser());
     String YouTubeVideoEmbedCode = "<html><body><iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/-fEIzQ5JD84\" frameborder=\"0\" allowfullscreen></iframe></body></html>";
     //   wv1.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
    wv1.getSettings().setBuiltInZoomControls(true);
   wv1.getSettings().setAllowContentAccess(true);
   wv1.getSettings().setEnableSmoothTransition(true);
  wv1.setWebContentsDebuggingEnabled(true);
    wv1.getSettings().setLoadsImagesAutomatically(true);
  //  wv1.setWebChromeClient();
        wv1.setWebViewClient(new WebViewClient());//否则在手机自带浏览器打开  
      wv1.setWebChromeClient(new WebChromeClient());  
    //    wv1.setWebChromeClient(new MyWebChromeClient());  
     wv1.getSettings().setLoadWithOverviewMode(true);
     wv1.getSettings().setSupportZoom(false);
     wv1.getSettings().setUseWideViewPort(true);
     wv1.getSettings().setAppCacheEnabled(true);
 wv1.getSettings().setSupportMultipleWindows(true);
//wv1..getSettings().setUserAgentString(" Mozilla/5.0 (iPhone; U; CPU like Mac OS X; en) AppleWebKit/420+ (KHTML, like Gecko) Version/3.0 Mobile/1A543a "); 
 // wv1.getSettings().setPluginState(PluginState.ON);
 wv1.getSettings().setAllowFileAccess(true);
    wv1.getSettings().getPluginState();
   // wv1.getSettings().setPluginState(PluginState.ON);
    wv1.getSettings().setJavaScriptEnabled(true);
  //  wv1.getSettings().setUserAgent(0);
    wv1.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
 //  wv1.loadUrl("http://www.youtube.com/embed/" + video.youtube_id);
  wv1.loadUrl("http://www.google.pl");
   //wv1.loadData(YouTubeVideoEmbedCode, "text/html", "utf-8"); 
       b1.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            String url = ed1.getText().toString();
        //  WebSettings webSettings = displayVideo.getSettings();
            wv1.getSettings().setLoadsImagesAutomatically(true);
            wv1.getSettings().setJavaScriptEnabled(true);
            wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            wv1.loadUrl(url);
         }
      });
      
backButton.setOnClickListener(new View.OnClickListener() {

    @Override
    public void onClick(View v) {

           // Going back if canGoBack true
           if(wv1.canGoBack()){
               wv1.goBack();
            }
    }
});
//Forward Button Action
forwardButton.setOnClickListener(new View.OnClickListener() {

    @Override
    public void onClick(View v) {
        // Go Forward if canGoForward is frue 

           if(wv1.canGoForward()){
               wv1.goForward();
           }
    }
});
wv1.setWebViewClient( new WebViewClient() {
 
      @Override 
      public void onPageFinished( WebView view, String url )
       { 
         super.onPageFinished(wv1, url ); //Make Enable or Disable buttons 
       backButton.setEnabled(view.canGoBack()); 
       forwardButton.setEnabled(view.canGoForward()); 
       } 

   });


      enableButton.setOnClickListener(new OnClickListener(){
         public void onClick(View v){
           try {
           Process process; process = Runtime.getRuntime().exec("/system/bin/du");
           }
          catch (IOException e) { throw new RuntimeException(e); }
            WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
            wifi.setWifiEnabled(true);
         }
      });
		
      disableButton.setOnClickListener(new OnClickListener(){
         public void onClick(View v){
            WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
            wifi.setWifiEnabled(false);
         }
      });
   }
    

   private class MyBrowser extends WebViewClient {
      @Override
      public boolean shouldOverrideUrlLoading(WebView view, String url) {
         view.loadUrl(url);
         return true;
      }
   }


}

