package com.repoimage;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements Adapter.OnItemClickListener{

   public static  final String Extra_Url="Flag";
     RecyclerView recyclerView;
    List<DataItem> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("e","g");
        recyclerView=findViewById(R.id.recyclerview);
        loadJson();
    }

    private void loadJson() {
        Retrofit retrofit=new Retrofit.Builder().baseUrl(RequestInterface.Url).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        RequestInterface requestInterface=retrofit.create(RequestInterface.class);

        rx.Observable <JSONResponse>  observable=requestInterface.getdata().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<JSONResponse>() {
            @Override
             public void onCompleted() {
                Log.d("hello","hello");
                Toast.makeText(getApplicationContext(),"Completed",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Throwable e) {
                Log.d(e.toString(),""+e.toString());
                Toast.makeText(getApplicationContext(),"PLEASE CHECK YOUR INTERNET CONNECTION"+e.toString(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNext(JSONResponse response) {
                list=new ArrayList<DataItem>();
                List<DataItem> worldpopulation=response.getWorldpopulation();
                Log.d("response size",String.valueOf(worldpopulation.size()));
                for(int i=0;i<worldpopulation.size();i++)
                {
            /*        DataItem dataItem=new DataItem();
                    dataItem.setFlag(worldpopulation.get(i).getFlag());
                    dataItem.setCountry(worldpopulation.get(i).getCountry());
                    dataItem.setPopulation(worldpopulation.get(i).getPopulation());
                    dataItem.setFlag(worldpopulation.get(i).getFlag());
*/
                   String Flag=worldpopulation.get(i).getFlag();
                   String Population=worldpopulation.get(i).getPopulation();
                    String Country=worldpopulation.get(i).getCountry();
                    int Rank=worldpopulation.get(i).getRank();
                     list.add(new DataItem(Country,Flag,Population,Rank));

                   // list.add(dataItem);
                    Log.d("data",""+Flag);
                    Log.d("data",""+Population);
                    Log.d("data",""+Country);

                }


                Adapter adapter=new Adapter(MainActivity.this,list);

                RecyclerView.LayoutManager recycle=new GridLayoutManager(MainActivity.this,2);
               recyclerView.addItemDecoration(new GridSpacingdecoration(2,dpToPx(10),true));
                recyclerView.setLayoutManager(recycle);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                 recyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(MainActivity.this);

            }
        });

    }

    @Override
    public void onItemClick(int position) {
        Log.d("click","click");
    Intent dataintent=new Intent(this,SecondActivity.class);
    DataItem clickeditem=list.get(position);
    dataintent.putExtra(Extra_Url,clickeditem.getFlag());

    startActivity(dataintent);
    }




    private class GridSpacingdecoration extends RecyclerView.ItemDecoration {

            private int span;
            private int space;
            private boolean include;

            public GridSpacingdecoration(int span, int space, boolean include) {
                this.span = span;
                this.space = space;
                this.include = include;
            }

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int position = parent.getChildAdapterPosition(view);
                int column = position % span;

                if (include) {
                    outRect.left = space - column * space / span;
                    outRect.right = (column + 1) * space / span;

                    if (position < span) {
                        outRect.top = space;
                    }
                    outRect.bottom = space;
                } else {
                    outRect.left = column * space / span;
                    outRect.right = space - (column + 1) * space / span;
                    if (position >= span) {
                        outRect.top = space;
                    }
                }
            }

        }

        private int dpToPx(int dp) {
            Resources r = getResources();
            return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
        }

    }

