package com.hs.administrator.test.view.activity;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapBaseIndoorMapInfo;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.hs.administrator.test.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class baidumapActivity extends AppCompatActivity {

    @Bind(R.id.but_oneType)
    Button mButType;
    @Bind(R.id.but_twoType)
    Button mButTwoType;
    @Bind(R.id.but_threeType)
    Button mButThreeType;
    @Bind(R.id.but_fourType)
    Button mButFourType;
    @Bind(R.id.but_fiveType)
    Button mButFiveType;

    private MapView mapView;
    private BaiduMap baiduMap;

    //初始化定位类
    public LocationClient mLocationClient;
    public BDLocationListener myListener = new MyLocationListener();
    private LatLng latLng;

    private boolean isfour = true;
    private boolean isfive = true;
    //防止每次定位都重新设置中心点
    private boolean isFirstloc = true;
    //经纬度
    private double lat;
    private double lon;

    private final int PERMISSIONS_ALL_REQUEST_CODE = 1000;

    private String[] permissions = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE
            , Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_baidumap);
        ButterKnife.bind(this);
        mapView = (MapView) findViewById(R.id.text_map);
        baiduMap = mapView.getMap();
        baiduMap.setIndoorEnable(true);//打开室内地图默认为关闭
        baiduMap.setOnBaseIndoorMapListener(new BaiduMap.OnBaseIndoorMapListener() {
            @Override
            public void onBaseIndoorMapMode(boolean b, MapBaseIndoorMapInfo mapBaseIndoorMapInfo) {
                if (b){
                    MapBaseIndoorMapInfo.SwitchFloorError  switchFloorError = baiduMap.switchBaseIndoorMapFloor("F1",mapBaseIndoorMapInfo.getID());

                }else {

                }
            }
        });
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            initMap();
        } else {
              baidumapActivityPermissionsDispatcher.AppIySuccessWithCheck(baidumapActivity.this);

        }

        mButType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
            }
        });
        mButTwoType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                //     MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(latLng);
                //  baiduMap.animateMapStatus(mapStatusUpdate);
            }
        });
        mButThreeType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baiduMap.setMapType(BaiduMap.MAP_TYPE_NONE);
            }
        });
        mButFourType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isfour) {
                    baiduMap.setTrafficEnabled(true);
                    isfour = false;
                } else {
                    baiduMap.setTrafficEnabled(false);
                    isfour = true;
                }
            }

        });
        mButFiveType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isfive) {
                    baiduMap.setBaiduHeatMapEnabled(true);
                    isfive = false;
                } else {
                    baiduMap.setBaiduHeatMapEnabled(false);
                    isfive = true;
                }

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        baidumapActivityPermissionsDispatcher.onRequestPermissionsResult(baidumapActivity.this, requestCode, grantResults);

    }


    /*
    * 申请权限成功时
    * */
    @NeedsPermission({Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE
            , Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public void AppIySuccess() {
        initMap();
    }

    /**
     * 申请权限时告诉用户原因
     */
    @NeedsPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
    void showRationaleForMap(PermissionRequest request) {
        showRationaleDialog("使用此功能需要打开定位权限", request);
    }
    @OnNeverAskAgain({Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.READ_PHONE_STATE
            ,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onMapNeverAskAgain() {
        AskForPermission();
    }


    private void AskForPermission() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("当前应用缺少部分权限,请去设置界面打开\n打开之后按两次返回键可回到该应用哦");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });
        builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.setData(Uri.parse("package:" + baidumapActivity.this.getPackageName())); // 根据包名打开对应的设置界面
                startActivity(intent);
            }
        });
        builder.create().show();
    }


    /**
     * 告知用户具体需要权限的原因 * @param messageResId * @param request
     */

    private void showRationaleDialog(String messageResId, final PermissionRequest request) {
        new AlertDialog.Builder(baidumapActivity.this).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(@NonNull DialogInterface dialog, int which) {
                request.proceed();//请求权限
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(@NonNull DialogInterface dialog, int which) {
                request.cancel();
            }
        }).setCancelable(false).setMessage(messageResId).show();
    }


    private void initMap() {
        baiduMap.setMyLocationEnabled(true);
        mLocationClient = new LocationClient(this);//声明LocationClient类
        //配置SDK参数

        mLocationClient.registerLocationListener(myListener);//注册监听函数
        initLocation();
        mLocationClient.start();//开启定位
        //图片点击事件 回到定位点
        mLocationClient.requestLocation();
    }

    //SDK参数配置
    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选默认高精度定位模式
        option.setCoorType("bd0911");//可选默认gcj02设置返回的定位结果坐标系
        int span = 1000;
        option.setScanSpan(span);//可选默认0 为定位一次设置发起定位请求的间隔需要大于等于 span ms才是有效的
        option.setIsNeedAddress(true);//可选设置是否需要地址信息  默认为不要
        option.setOpenGps(true);//默认为false 设置是否可用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选 默认false设置是否需要位置语义话结果，可以在BDLocation
        option.setIsNeedLocationPoiList(true);//可选默认false设置是否需要POI结果可以再BDLocation.getPoilist里得到
        option.setIgnoreKillProcess(false);
        option.setPriority(LocationClientOption.GpsFirst);
        option.SetIgnoreCacheException(false);
        option.setEnableSimulateGps(false);
        mLocationClient.setLocOption(option);
    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            location.getTime();//获取定位时间
            location.getLocationID();//获取定位weiyiId用于排查定位问题
            location.getLocType();//获取定位类型
            location.getLatitude();//获取纬度信息
            location.getLongitude();//获取经度信息
            location.getRadius();//获取精准定位
            location.getAddrStr();//获取地址信息
            location.getCountry();//获取国家信息
            location.getCountryCode();//获取国家码
            location.getCity();    //获取城市信息
            location.getCityCode();    //获取城市码
            location.getDistrict();    //获取区县信息
            location.getStreet();    //获取街道信息
            location.getStreetNumber();    //获取街道码
            location.getLocationDescribe();    //获取当前位置描述信息
            location.getPoiList();    //获取当前位置周边POI信息
            location.getBuildingID();    //室内精准定位下，获取楼宇ID
            location.getBuildingName();    //室内精准定位下，获取楼宇名称
            location.getFloor();    //室内精准定位下，获取当前位置所处的楼层信息
            //经纬度
            lat = location.getLatitude();//纬度

            lon = location.getLongitude();//经度

       /*     mButFiveType.setText(lat+"");
            mButType.setText(lon+"");*/

            MyLocationData locationData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude())
                    .build();
            baiduMap.setMyLocationData(locationData);

            if (isFirstloc){
                isFirstloc = false;
                LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                baiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
        }
    }

    /**
     * 设置中心点和添加
     * marker *
     * * @param map *
     *
     * @param bdLocation * @param isShowLoc
     */
    public void setPosition2Center(BaiduMap map, BDLocation bdLocation, Boolean isShowLoc) {
        MyLocationData locData = new MyLocationData.Builder().accuracy(bdLocation.getRadius()).direction(bdLocation.getRadius()).latitude(bdLocation.getLatitude()).longitude(bdLocation.getLongitude()).build();
        map.setMyLocationData(locData);
        if (isShowLoc) {
            LatLng ll = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
            MapStatus.Builder builder = new MapStatus.Builder();
            builder.target(ll).zoom(18.0f);
            map.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        mapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }
}
