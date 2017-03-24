package blogspot.xhdpi.qrcodereader;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import java.util.List;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static blogspot.xhdpi.qrcodereader.R.id.scanQR;

public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

  private static final int RC_CAMERA = 1;
  private Button btnScan;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    btnScan = (Button) findViewById(scanQR);

    checkCameraPermission();
  }

  @AfterPermissionGranted(RC_CAMERA) private void checkCameraPermission() {
    // cek permission
    String perm = Manifest.permission.CAMERA;

    if (EasyPermissions.hasPermissions(this, perm)) {
      // sudah pnya permission
      scanQR();
    } else {
      EasyPermissions.requestPermissions(this, "Butuh permission camera", RC_CAMERA, perm);
    }
  }

  private void scanQR() {
    btnScan.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        startActivity(new Intent(MainActivity.this, ScanActivity.class));
      }
    });
  }

  @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    // Forward results to EasyPermissions
    EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
  }

  @Override public void onPermissionsGranted(int requestCode, List<String> perms) {
    for (String permission : perms) {
      if (permission.equals(Manifest.permission.CAMERA)) {
        // check jika permission granted
        scanQR();
      }
    }
  }

  @Override public void onPermissionsDenied(int requestCode, List<String> perms) {

  }
}
