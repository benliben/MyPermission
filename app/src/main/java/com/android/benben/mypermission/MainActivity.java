package com.android.benben.mypermission;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissonItem;


public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        mContext = this;
    }


    @OnClick({R.id.button1, R.id.button2, R.id.button3, R.id.button4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button1:
                /*多个权限默认正常风格*/
                HiPermission.create(mContext)
                        .animStyle(R.style.PermissionAnimFade)
                        .checkMutiPermission(new PermissionCallback() {
                            @Override
                            public void onClose() {
                                Log.i(TAG, "onClose");
                                Toast.makeText(mContext, getString(R.string.permission_on_close),
                                        Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFinish() {
                                Toast.makeText(mContext, getString(R.string.permission_completed),
                                        Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onDeny(String permisson, int position) {
                                Log.i(TAG, "onDeny");
                            }

                            @Override
                            public void onGuarantee(String permisson, int position) {
                                Log.i(TAG, "onGuarantee");
                            }
                        });
                break;
            case R.id.button2:
                /*单个权限默认风格*/
                /*设置主题后必须调用filterColor()来设置图标的颜色，否则默认为黑色*/
                List<PermissonItem> permissonItem = new ArrayList<>();
                permissonItem.add(new PermissonItem(Manifest.permission.CAMERA, getString(R.string.permission_cus_item_camera),
                        R.drawable.permission_ic_camera));
                HiPermission.create(mContext)
                        .title(getString(R.string.permission_cus_title))
                        .permissions(permissonItem)
                        .msg(getString(R.string.permission_cus_msg))
                        .animStyle(R.style.PermissionAnimScale)
                        .checkMutiPermission(new PermissionCallback() {
                            @Override
                            public void onClose() {
                                Log.i(TAG, "onClose");
                                Toast.makeText(mContext, getString(R.string.permission_on_close),
                                        Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFinish() {
                                Toast.makeText(mContext, getString(R.string.permission_completed),
                                        Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onDeny(String permisson, int position) {
                                Log.i(TAG, "onDeny");
                            }

                            @Override
                            public void onGuarantee(String permisson, int position) {
                                Log.i(TAG, "onGuarantee");
                            }
                        });
                break;
            case R.id.button3:
                /*单个权限修改风格*/
                List<PermissonItem> permissons = new ArrayList<>();
                /*使用图标*/
                permissons.add(new PermissonItem(Manifest.permission.CALL_PHONE, getString(R.string.permission_cus_item_phone), R.drawable.permission_ic_phone));
                HiPermission.create(MainActivity.this)
                        .title(getString(R.string.permission_cus_title))
                        .permissions(permissons)
                        .msg(getString(R.string.permission_cus_msg))
                        .animStyle(R.style.PermissionAnimModal)
                        .style(R.style.PermissionDefaultGreenStyle)
//                        .style(R.style.CusStyle)
                        .checkMutiPermission(new PermissionCallback() {
                            @Override
                            public void onClose() {
                                Log.i(TAG, "onClose");
                                Toast.makeText(mContext, getString(R.string.permission_on_close),
                                        Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFinish() {
                                Toast.makeText(mContext, getString(R.string.permission_completed),
                                        Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onDeny(String permisson, int position) {
                                Log.i(TAG, "onDeny");
                            }

                            @Override
                            public void onGuarantee(String permisson, int position) {
                                Log.i(TAG, "onGuarantee");
                            }
                        });
                break;
            case R.id.button4:
                /*单一权限，不使用主题*/
                HiPermission.create(MainActivity.this).checkSinglePermission(Manifest.permission.CAMERA, new PermissionCallback() {
                    @Override
                    public void onClose() {

                    }

                    @Override
                    public void onFinish() {

                    }

                    @Override
                    public void onDeny(String permisson, int position) {
                        Toast.makeText(mContext, "onDeny", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onGuarantee(String permisson, int position) {
                        Toast.makeText(mContext, "onGuarantee", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }
}
