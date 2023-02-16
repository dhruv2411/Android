package apps.shehryar.com.cricketscroingappPro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import apps.shehryar.com.cricketscroingapp.R;

public class SplashScreenActivity extends Activity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_splash_screen);
        new DBHelper(this);
        findViewById(R.id.imageView).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SplashScreenActivity.this.startActivity(new Intent(SplashScreenActivity.this, FirstPage.class));
            }
        });
        new Thread(new Runnable() {
            public void run() {
                SplashScreenActivity splashScreenActivity;
                Intent intent;
                try {
                    Thread.sleep(2000);
                    splashScreenActivity = SplashScreenActivity.this;
                    intent = new Intent(SplashScreenActivity.this, FirstPage.class);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    splashScreenActivity = SplashScreenActivity.this;
                    intent = new Intent(SplashScreenActivity.this, FirstPage.class);
                } catch (Throwable th) {
                    SplashScreenActivity.this.startActivity(new Intent(SplashScreenActivity.this, FirstPage.class));
                    throw th;
                }
                splashScreenActivity.startActivity(intent);
            }
        });
    }
}
