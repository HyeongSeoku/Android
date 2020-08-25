package com.example.publicdb;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.icu.text.IDNA;
import android.os.AsyncTask;
import android.widget.TextView;
import android.os.Bundle;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.InfoWindow;
import com.naver.maps.map.overlay.Marker;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okio.Buffer;
import okio.BufferedSink;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    NaverMap mMap;

    //도시명
    String[] city = {"서울", "부산","대구","인천","광주","대전","울산","경기","강원","충북","충남","전북","전남","경북","경남","제주","세종"};
    //좌표
    LatLng[] latlng = new LatLng[17];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MapFragment mapFragment = (MapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.map, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);
    }

    @UiThread
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        mMap = naverMap;
        naverMap.setMapType(NaverMap.MapType.Hybrid);

        //서울 좌표
        latlng[0] = new LatLng(37.566506, 126.977977);
        //부산 좌표
        latlng[1] = new LatLng(35.1849121, 129.076744);

        //대구
        latlng[2]= new LatLng(35.871389, 128.601389);
        //인천
        latlng[3]= new LatLng(37.566535, 126.97796919);
        //광주
        latlng[4]= new LatLng(35.159444, 126.8525);
        //대전
        latlng[5]= new LatLng(36.340046, 127.394607);
        //울산
        latlng[6]= new LatLng(35.536221, 129.255628);
        //경기
        latlng[7]= new LatLng(37.509370, 127.271588);
        //강원
        latlng[8]= new LatLng(37.826502, 128.219826);
        //충북
        latlng[9]= new LatLng(36.841256, 127.787895);
        //충남
        latlng[10]= new LatLng(36.596462, 126.798449);
        //전북
        latlng[11]= new LatLng(35.798163, 127.149262);
        //전남
        latlng[12]= new LatLng(34.919168, 126.880559);
        //경북
        latlng[13]= new LatLng(36.477726, 129.025801);
        //경남
        latlng[14]= new LatLng(35.431917, 128.225763);
        //제주
        latlng[15]= new LatLng(33.409754, 126.534453);
        //세종
        latlng[16]= new LatLng(36.481511, 127.266667);

        //지도 중심: 서울과 부산 사이
        LatLng latlng_center = new LatLng((latlng[0].latitude + latlng[1].latitude) / 2, (latlng[0].longitude + latlng[1].longitude) / 2);
        CameraUpdate cameraUpdate1 = CameraUpdate.scrollTo(latlng_center);
        naverMap.moveCamera(cameraUpdate1);

        //지도 크기
        CameraUpdate cameraUpdate2 = CameraUpdate.zoomTo(6);
        naverMap.moveCamera(cameraUpdate2);

        //대기 오염 정보
        String api = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnMesureLIst?serviceKey=vxoRhlyaTc97u5VNc%2BFmzAvoh5pYue7e%2Fws5XfDMB%2BlApx1%2BZcLqUT7oqbbw4kNAJWHtemdaGwLVuP9Gl%2FpSnQ%3D%3D&numOfRows=10&pageNo=1&itemCode=PM10&dataGubun=HOUR&searchCondition=MONTH";

        //API를 이용한 데이터 다운로드 객체
        DownloadWebpageTask task = new DownloadWebpageTask();
        //데이터 다운로드 및 처리
        task.execute(api);
    }

    //데이터 다운로드 클래스 정의
    private class DownloadWebpageTask extends AsyncTask<String, Void, String> {

        //문서 다운로드(백그라운드 실행)
        @Override
        protected String doInBackground(String... urls) {
            try {
                //API에 해당하는 문서 다운로드
                String txt = (String) downloadUrl((String) urls[0]);
                return txt;
            } catch (IOException e) {
                return "다운로드 실패";
            }
        }

        //문서 다운로드 후 자동 호출:XML 문서 파싱
        protected void onPostExecute(String result) {
            boolean bSet_itemCode = false;
            boolean bSet_city = false;

            String itemCode = "";
            String pollution_degree = "";
            String tag_name = "";

            int cnt = 0;
            int city_no = 0;

            try {
                //XML Pull Parser 객체 생성
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();
                //파싱할 문서 설정
                xpp.setInput(new StringReader(result));
                //현재 이벤트 유형 변환(START_DOUCUMENT , START_TAG, TEXT, END_TAG,END_DOCUMENT)
                int eventType = xpp.getEventType();
                //이벤트 유형이 문서 마지막이 될 때 까지 반복
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    //문서의 시작인 경우
                    if (eventType == XmlPullParser.START_DOCUMENT) {
                        ;
                    } else if (eventType == XmlPullParser.START_TAG) {
                        tag_name = xpp.getName();
                        if (bSet_itemCode == false && tag_name.equals("itemCode"))
                            bSet_itemCode = true;
                        if (itemCode.equals("PM10") && (tag_name.equals("seoul") || tag_name.equals("busan")|| tag_name.equals("daegu")|| tag_name.equals("incheon")
                                || tag_name.equals("gwangju")|| tag_name.equals("Daejeon")|| tag_name.equals("ulsan")|| tag_name.equals("gyeonggi")|| tag_name.equals("gangwon")
                                || tag_name.equals("chungbuk")|| tag_name.equals("chungnam")|| tag_name.equals("jeonbuk")|| tag_name.equals("jeonnam")|| tag_name.equals("gyeongbuk")|| tag_name.equals("gyeongnam")
                                || tag_name.equals("jeju")|| tag_name.equals("sejong")))
                            bSet_city = true;

                        //태그 사이의 문자 확인
                    } else if (eventType == XmlPullParser.TEXT) {
                        if (bSet_itemCode) {
                            itemCode = xpp.getText();

                            if (itemCode.equals("PM10")) {
                                cnt++;
                                bSet_itemCode = false;

                                //PM10에 대한 가장 가까운 시간 대의 도시 대기오염 정보 추출 후에 반복 종료
                                if (cnt > 1)
                                    break;
                            }
                        }
                        if (bSet_city) {
                            pollution_degree = xpp.getText();

                            //지도 위에 해당 도시의 미세먼지 농도 표시
                            addInfo(latlng[city_no], city[city_no], pollution_degree);
                            city_no++;
                            bSet_city = false;
                        }
                        //마침 태그인 경우
                    } else if (eventType == XmlPullParser.END_TAG) {

                    }
                    //다음 이벤트 유형 할당
                    eventType = xpp.next();
                }
            } catch (Exception e) {
            }
        }

        //전달받은 API에 해당하는 문서 다운로드
        private String downloadUrl(String api) throws IOException {
            HttpURLConnection conn = null;
            try {
                URL url = new URL(api);
                conn = (HttpURLConnection) url.openConnection();
                BufferedInputStream buf = new BufferedInputStream(conn.getInputStream());
                BufferedReader bufreader = new BufferedReader(new InputStreamReader(buf, "utf-8"));
                // 줄 단위로 읽어 문자로 저장
                String line = null;
                String page = "";
                while ((line = bufreader.readLine()) != null) {
                    page += line;
                }
                //다운로드 문서 반환
                return page;
            } finally {
                conn.disconnect();
            }
        }
    }
    //지도 위에 미세먼지 농도 표시
    public void addInfo(LatLng latlng, String city,String pollution_degree){
        final String pollution = pollution_degree;

        Marker marker = new Marker();
        marker.setPosition(latlng);
        marker.setMap(mMap);

        marker.setSubCaptionText(city);
        marker.setSubCaptionColor(Color.RED);
        marker.setSubCaptionHaloColor(Color.YELLOW);
        marker.setSubCaptionTextSize(20);

        InfoWindow infoWindow1 = new InfoWindow();
        infoWindow1.setAdapter(new InfoWindow.DefaultTextAdapter(this){
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow){
                return pollution;
            }
        });
        infoWindow1.open(marker);
    }
}
