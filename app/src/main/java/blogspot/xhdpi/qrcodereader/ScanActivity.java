package blogspot.xhdpi.qrcodereader;

import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.dlazaro66.qrcodereaderview.QRCodeReaderView;

public class ScanActivity extends AppCompatActivity
    implements QRCodeReaderView.OnQRCodeReadListener {

  private QRCodeReaderView mydecoderview;
  private TextView tvResult;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_scan);

    mydecoderview = (QRCodeReaderView) findViewById(R.id.qrdecoderview);
    tvResult = (TextView) findViewById(R.id.tvResult);
    mydecoderview.setOnQRCodeReadListener(this);
    // Use this function to set back camera preview
    mydecoderview.setBackCamera();
  }

  @Override public void onQRCodeRead(String text, PointF[] points) {
    tvResult.setText(text);
  }
}
